/**
 * Copyright (C) 2016 Pink Summit, LLC (info@pinksummit.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.di2e.ecdr.commons.endpoint.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.codice.ddf.configuration.SystemBaseUrl;
import org.codice.ddf.configuration.SystemInfo;
import org.codice.ddf.spatial.geocoder.GeoCoder;
import org.codice.ddf.spatial.geocoder.GeoResult;
import org.opengis.geometry.BoundingBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.catalog.CatalogFramework;
import ddf.catalog.data.BinaryContent;
import ddf.catalog.federation.FederationException;
import ddf.catalog.operation.QueryResponse;
import ddf.catalog.source.SourceUnavailableException;
import ddf.catalog.source.UnsupportedQueryException;
import ddf.catalog.transform.CatalogTransformerException;
import ddf.registry.api.RegistrableService;
import net.di2e.ecdr.api.auditor.SearchAuditor;
import net.di2e.ecdr.api.cache.QueryRequestCache;
import net.di2e.ecdr.api.query.QueryConfiguration;
import net.di2e.ecdr.api.query.QueryCriteria;
import net.di2e.ecdr.api.query.QueryLanguage;
import net.di2e.ecdr.api.transform.TransformIdMapper;
import net.di2e.ecdr.commons.constants.SearchConstants;
import net.di2e.ecdr.commons.query.CDRQueryImpl;
import net.di2e.ecdr.commons.util.GeospatialUtils;
import net.di2e.ecdr.commons.util.SearchUtils;
import net.di2e.ecdr.commons.xml.fs.SourceDescription;
import net.di2e.ecdr.commons.xml.osd.OpenSearchDescription;
import net.di2e.ecdr.commons.xml.osd.Query;
import net.di2e.ecdr.commons.xml.osd.SyndicationRight;
import net.di2e.ecdr.commons.xml.osd.Url;

public abstract class AbstractRestSearchEndpoint implements RegistrableService {

    private static final Logger LOGGER = LoggerFactory.getLogger( AbstractRestSearchEndpoint.class );
    
    private static Map<String, String> baseQueryParamsMap = null;
    static {
        baseQueryParamsMap = new HashMap<String, String>();
        baseQueryParamsMap.put( SearchConstants.KEYWORD_PARAMETER, "os:searchTerms" );
        baseQueryParamsMap.put( SearchConstants.COUNT_PARAMETER, "os:count" );
        baseQueryParamsMap.put( SearchConstants.STARTINDEX_PARAMETER, "os:startIndex" );
        baseQueryParamsMap.put( SearchConstants.FORMAT_PARAMETER, "cdrs:responseFormat" );
        baseQueryParamsMap.put( SearchConstants.TIMEOUT_PARAMETER, "cdrs:timeout" );
        baseQueryParamsMap.put( SearchConstants.STATUS_PARAMETER, "cdrb:includeStatus" );
        baseQueryParamsMap.put( SearchConstants.OID_PARAMETER, "cdrsx:originQueryID" );
        baseQueryParamsMap.put( SearchConstants.STRICTMODE_PARAMETER, "cdrsx:strictMode" );
        baseQueryParamsMap.put( SearchConstants.PATH_PARAMETER, "cdrb:path" );
    }

    private QueryRequestCache queryRequestCache = null;

    private CatalogFramework catalogFramework = null;
    private List<SearchAuditor> auditors = null;
    // private Map<String, QueryLanguage> queryLanguageMap = null;
    private List<QueryLanguage> queryLanguageList = null;
    private QueryConfiguration queryConfiguration = null;

    private TransformIdMapper transformMapper = null;
    // using an object reference here so that this will be deployable on older DDF systems that do not have the class
    private List<Object> geoCoderList;

    /**
     * Constructor for JAX RS CDR Search Service. Values should ideally be passed into the constructor using a
     * dependency injection framework like blueprint
     *
     * @param framework Catalog Framework which will be used for search
     * @param queryLangs
     * @param mapper
     * @param auditorList
     * @param queryConfig
     * @param queryReqCache
     * @param geoCoderList
     */
    public AbstractRestSearchEndpoint( CatalogFramework framework, List<QueryLanguage> queryLangs, TransformIdMapper mapper, List<SearchAuditor> auditorList,
            QueryConfiguration queryConfig, QueryRequestCache queryReqCache, List<Object> geoCoderList ) {
        this.catalogFramework = framework;
        // this.queryLanguageMap = queryLangs;
        this.queryLanguageList = queryLangs;
        this.transformMapper = mapper;
        this.auditors = auditorList;
        this.queryConfiguration = queryConfig;
        this.queryRequestCache = queryReqCache;
        this.geoCoderList = geoCoderList;

    }

    public Response executePing( UriInfo uriInfo, String encodingHeader, String authHeader ) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        boolean isValid = isValidQuery( queryParams, SystemInfo.getSiteName() );
        return isValid ? Response.ok().build() : Response.status( Response.Status.BAD_REQUEST ).build();
    }

    /**
     * Search method that gets called when issuing an HTTP GET to the corresponding URL. HTTP GET URL query parameters
     * contain the query criteria values
     *
     * @param uriInfo
     *            Query parameters obtained by e
     * @param encoding
     *            accept-encoding from the client
     * @param auth
     *            Authorization header
     * @return Response to send back to the calling client
     */
    public Response executeSearch( HttpServletRequest servletRequest, UriInfo uriInfo, String encoding, String auth ) {
        Response response;
        QueryResponse queryResponse = null;
        try {
            String localSourceId = SystemInfo.getSiteName();
            MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
            addHeaderParameters( servletRequest, queryParameters );

            if ( !isValidQuery( queryParameters, localSourceId ) ) {
                throw new UnsupportedQueryException( "Invalid query parameters passed in" );
            }

            QueryLanguage queryLanguage = getQueryLanguage( queryParameters );
            if ( queryLanguage == null ) {
                throw new UnsupportedQueryException(
                        "A Query language could not be determined, please check the default query language in the Admin Console ECDR Application Search Endpoint settings" );
            }
            translateGeoNames( queryParameters );
            QueryCriteria queryCriteria = queryLanguage.getQueryCriteria( queryParameters, queryConfiguration );
            CDRQueryImpl query = new CDRQueryImpl( queryCriteria, localSourceId );

            queryResponse = executeQuery( localSourceId, queryParameters, query );

            // Move the specific links into Atom Transformer if possible
            Map<String, Serializable> transformProperties = SearchUtils.getTransformLinkProperties( uriInfo, query, queryResponse, getURLScheme(),
                    SystemBaseUrl.getHost(), Integer.parseInt( SystemBaseUrl.getPort() ) );
            transformProperties.put( SearchConstants.FEED_TITLE, "Atom Search Results from '" + localSourceId + "' for Query: " + query.getHumanReadableQuery().trim() );
            transformProperties.put( SearchConstants.FORMAT_PARAMETER, query.getResponseFormat() );
            transformProperties.put( SearchConstants.STATUS_PARAMETER, isIncludeStatus( queryParameters ) );
            transformProperties.put( SearchConstants.LOCAL_SOURCE_ID, catalogFramework.getId() );
            transformProperties.put( SearchConstants.GEORSS_RESULT_FORMAT_PARAMETER, getGeoRSSFormat( queryParameters ) );

            String format = query.getResponseFormat();

            String internalTransformerFormat = transformMapper.getQueryResponseTransformValue( format );
            transformProperties.put( SearchConstants.METACARD_TRANSFORMER_NAME, transformMapper.getMetacardTransformValue( format ) );
            BinaryContent content = catalogFramework.transform( queryResponse, internalTransformerFormat, transformProperties );

            try ( InputStream is = content.getInputStream() ) {
                response = Response.ok( is, content.getMimeTypeValue() ).build();
            } catch ( IOException e ) {
                LOGGER.error( "Error reading response [" + e.getMessage() + "]", e );
                response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
            }

        } catch ( UnsupportedQueryException e ) {
            LOGGER.error( e.getMessage(), e );
            response = Response.status( Response.Status.BAD_REQUEST ).build();
        } catch ( SourceUnavailableException e ) {
            LOGGER.error( e.getMessage(), e );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
        } catch ( FederationException e ) {
            LOGGER.error( e.getMessage(), e );
            response = Response.status( Response.Status.BAD_REQUEST ).build();
            // These exceptions happen when the transform is not available via
            // the framework or an exception occurs in translation
        } catch ( CatalogTransformerException | IllegalArgumentException e ) {
            LOGGER.error( e.getMessage(), e );
            response = Response.status( Response.Status.BAD_REQUEST ).build();
        } catch ( RuntimeException e ) {
            LOGGER.error( "Unexpected exception received [" + e.getMessage() + "]", e );
            response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
        }

        for ( SearchAuditor auditor : auditors ) {
            auditor.auditRESTQuery( servletRequest, queryResponse, response );
        }
        return response;
    }

    @GET
    @Path( "/osd.xml" )
    @Produces( "application/opensearchdescription+xml" )
    public Response getOSD() {
        OpenSearchDescription osd = new OpenSearchDescription();
        osd.setShortName( SystemInfo.getSiteName() );
        osd.setDescription( getServiceDescription() );
        osd.setTags( "ecdr opensearch cdr ddf" );
        if ( StringUtils.isNotBlank( SystemInfo.getOrganization() ) ) {
            osd.setDeveloper( SystemInfo.getOrganization() );
        }
        if ( StringUtils.isNotBlank( SystemInfo.getSiteContatct() ) ) {
            osd.setContact( SystemInfo.getSiteContatct() );
        }
        Query query = new Query();
        query.setRole( "example" );
        query.setSearchTerms( "test" );
        osd.getQuery().add( query );
        osd.setSyndicationRight( SyndicationRight.OPEN );
        osd.getLanguage().add( MediaType.MEDIA_TYPE_WILDCARD );
        osd.getInputEncoding().add( StandardCharsets.UTF_8.name() );
        osd.getOutputEncoding().add( StandardCharsets.UTF_8.name() );

        // url example
        for ( QueryLanguage lang : queryLanguageList ) {
            Url url = new Url();
            url.setType( MediaType.APPLICATION_ATOM_XML );
            url.setTemplate( generateTemplateUrl( lang ) );
            osd.getUrl().add( url );
        }

        addSourceDescriptions( osd );

        StringWriter writer = new StringWriter();
        InputStream is = null;
        try {
            JAXBContext context = JAXBContext.newInstance( OpenSearchDescription.class, SourceDescription.class );
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
            marshaller.setProperty( Marshaller.JAXB_FRAGMENT, true );
            marshaller.marshal( osd, writer );
            is = getClass().getResourceAsStream( "/templates/osd_info.template" );
            if ( is != null ) {
                String osdTemplate = IOUtils.toString( is );
                osdTemplate = replaceTemplateValues( osdTemplate );

                String responseStr = osdTemplate + writer.toString();
                return Response.ok( responseStr, MediaType.APPLICATION_XML_TYPE ).build();
            } else {
                return Response.serverError().entity( "COULD NOT LOAD OSD TEMPLATE." ).build();
            }
        } catch ( JAXBException | IOException e ) {
            LOGGER.warn( "Could not create OSD for client due to exception.", e );
            return Response.serverError().build();
        } finally {
            IOUtils.closeQuietly( is );
        }
    }
    
    protected QueryLanguage getQueryLanguage( MultivaluedMap<String, String> queryParams ) {
        String lang = StringUtils.defaultIfBlank( queryParams.getFirst( SearchConstants.QUERYLANGUAGE_PARAMETER ), queryConfiguration.getDefaultQueryLanguage() );
        LOGGER.debug( "Using query language that is associated with the name [{}]", lang );
        for ( QueryLanguage queryLang : queryLanguageList ) {
            if ( StringUtils.equalsIgnoreCase( queryLang.getName(), lang ) ) {
                return queryLang;
            }
        }
        return null;
    }

    private void translateGeoNames( MultivaluedMap<String, String> queryParameters ) {

        String geoName = queryParameters.getFirst( SearchConstants.GEO_NAME_PARAMETER );
        if ( StringUtils.isNotBlank( geoName )) {
            for (Object curObject : geoCoderList) {
                GeoCoder geoCoder = (GeoCoder) curObject;
                GeoResult result = geoCoder.getLocation( geoName );
                if (result != null) {
                    if (result.getBbox() != null) {
                        BoundingBox boundingBox = GeospatialUtils.pointsToBBox(result.getBbox());
                        if (boundingBox != null) {
                            String wktStr = GeospatialUtils.bboxToWKT(boundingBox);
                            queryParameters.add( SearchConstants.GEOMETRY_PARAMETER, wktStr );
                        } else {
                            LOGGER.debug("Was not able to convert geoname result to boundingbox, checking next geocoder.");
                            continue;
                        }
                    } else if (result.getPoint() != null) {
                        String wktStr = GeospatialUtils.pointToWKT(result.getPoint());
                        queryParameters.add( SearchConstants.GEOMETRY_PARAMETER, wktStr );
                    } else {
                        // issue within the geocoder, it had a result but nothing converted in it
                        continue;
                    }
                    return;
                }
            }
        }

    }

    protected void addSourceDescriptions( OpenSearchDescription osd ) {
        // federated sites
        for ( String curSource : catalogFramework.getSourceIds() ) {
            SourceDescription description = new SourceDescription();
            description.setSourceId( curSource );
            description.setShortName( curSource );
            osd.getAny().add( description );
        }
    }

    protected String replaceTemplateValues( String osdTemplate ) {
        osdTemplate = StringUtils.replace( osdTemplate, "${defaultCount}", String.valueOf( queryConfiguration.getDefaultCount() ), 1 );
        osdTemplate = StringUtils.replace( osdTemplate, "${defaultQueryLanguage}", queryConfiguration.getDefaultQueryLanguage(), 1 );
        osdTemplate = StringUtils.replace( osdTemplate, "${queryLanguages}", getQueryLanguagesString(), 1 );
        osdTemplate = StringUtils.replace( osdTemplate, "${defaultResponseFormat}", queryConfiguration.getDefaultResponseFormat(), 1 );
        osdTemplate = StringUtils.replace( osdTemplate, "${defaultTimeout}", String.valueOf( queryConfiguration.getDefaultTimeoutMillis() ), 1 );
        osdTemplate = StringUtils.replace( osdTemplate, "${additionalBasicParameters}", "", 1 );
        osdTemplate = StringUtils.replace( osdTemplate, "${queryLanguageDocumentation}", getQueryLanguageDescriptions(), 1 );
        return osdTemplate;
    }

    protected String getQueryLanguageDescriptions() {
        StringBuilder sb = new StringBuilder();
        Iterator<QueryLanguage> langIter = queryLanguageList.iterator();
        while ( langIter.hasNext() ) {
            sb.append( langIter.next().getLanguageDescription( queryConfiguration ) );
            if ( langIter.hasNext() ) {
                sb.append( System.lineSeparator() );
                sb.append( System.lineSeparator() );
                sb.append( System.lineSeparator() );
            }
        }
        return sb.toString();
    }

    protected void addHeaderParameters( HttpServletRequest servletRequest, MultivaluedMap<String, String> queryParameters ) {
        List<String> headerProperties = queryConfiguration.getHeaderPropertyList();
        if ( CollectionUtils.isNotEmpty( headerProperties ) ) {
            for ( String headerProp : headerProperties ) {
                String value = servletRequest.getHeader( headerProp );
                if ( StringUtils.isNotBlank( value ) ) {
                    LOGGER.trace( "Matching HTTP Header key/value pair found, adding [{}]=[{}] to queryParameters", headerProp, value );
                    queryParameters.putSingle( headerProp, value );
                }
            }
        }
    }

    private String getQueryLanguagesString() {
        StringBuilder builder = new StringBuilder();
        for ( QueryLanguage lang : queryLanguageList ) {
            builder.append( "'" + lang.getName() + "' " );
        }
        return builder.toString().trim();
    }

    public String getParameterTemplate( String languageName ) {
        StringBuilder sb = new StringBuilder( "?" );
        for ( Entry<String, String> entry : baseQueryParamsMap.entrySet() ) {
            sb.append( entry.getKey() + "={" + entry.getValue() + "?}&" );
        }
        // Query Language isn't listed in the default set of values
        sb.append( SearchConstants.QUERYLANGUAGE_PARAMETER + "=" + languageName );
        return sb.toString();
    }

    public abstract QueryResponse executeQuery( String localSourceId, MultivaluedMap<String, String> queryParameters, CDRQueryImpl query ) throws SourceUnavailableException,
            UnsupportedQueryException, FederationException;

    @Override
    public Map<String, String> getProperties() {
        return Collections.emptyMap();
    }

    protected CatalogFramework getCatalogFramework() {
        return catalogFramework;
    }

    protected boolean isIncludeStatus( MultivaluedMap<String, String> queryParameters ) {
        // Include status is true unless explicitly set to false
        return BooleanUtils.toBooleanDefaultIfNull( SearchUtils.getBoolean( queryParameters.getFirst( SearchConstants.STATUS_PARAMETER ) ), true );
    }

    protected String getGeoRSSFormat( MultivaluedMap<String, String> queryParameters ) {
        return StringUtils.defaultIfBlank( queryParameters.getFirst( SearchConstants.GEORSS_RESULT_FORMAT_PARAMETER ), null );
    }

    public Map<String, Serializable> getQueryProperties( MultivaluedMap<String, String> queryParameters, String sourceId ) {
        Map<String, Serializable> queryProperties = new HashMap<String, Serializable>();

        queryProperties.put( SearchConstants.FORMAT_PARAMETER,
                StringUtils.defaultIfBlank( queryParameters.getFirst( SearchConstants.FORMAT_PARAMETER ), queryConfiguration.getDefaultResponseFormat() ) );
        queryProperties.put( SearchConstants.STATUS_PARAMETER, SearchUtils.getBoolean( queryParameters.getFirst( SearchConstants.STATUS_PARAMETER ), true ) );
        queryProperties.put( SearchConstants.DEDUP_PARAMETER, SearchUtils.getBoolean( queryParameters.getFirst( SearchConstants.DEDUP_PARAMETER ), queryConfiguration.isDefaultDeduplication() ) );

        for ( String key : queryParameters.keySet() ) {
            String value = queryParameters.getFirst( key );
            if ( StringUtils.isNotBlank( value ) && (queryConfiguration.getParameterPropertyList().contains( key ) || queryConfiguration.getHeaderPropertyList().contains( key )) ) {
                LOGGER.trace( "Adding key/value pair  [{}]=[{}] to queryProperties that get sent in with query request", key, value );
                queryProperties.put( key, value );
            }
        }
        LOGGER.trace( "Setting the query properties to {} based on values in query parameters {}", queryProperties, queryParameters );
        return queryProperties;
    }

    protected String generateTemplateUrl( QueryLanguage lang ) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append( SystemBaseUrl.getProtocol() );
        urlBuilder.append( SystemBaseUrl.getHost() );
        urlBuilder.append( ":" );
        urlBuilder.append( SystemBaseUrl.getPort() );
        urlBuilder.append( getServiceRelativeUrl() );
        urlBuilder.append( getParameterTemplate( lang.getName() ) );
        urlBuilder.append( lang.getUrlTemplateParameters() );
        LOGGER.debug( "Generating the following template URL for OSDD: {}", urlBuilder );
        return urlBuilder.toString();
    }

    protected boolean isValidQuery( MultivaluedMap<String, String> queryParameters, String sourceId ) {
        boolean isValidQuery;
        String queryLang = queryParameters.getFirst( SearchConstants.QUERYLANGUAGE_PARAMETER );
        // if ( StringUtils.isNotBlank( queryLang ) && !queryLanguageMap.containsKey( queryLang ) ) {
        if ( getQueryLanguage( queryParameters ) == null ) {
            isValidQuery = false;
            LOGGER.debug( "The query is not valid because the {} parameter with value {} is not in the allowed values {}", SearchConstants.QUERYLANGUAGE_PARAMETER, queryLang, queryLanguageList );
        } else if ( !SearchUtils.isBooleanNullOrBlank( queryParameters.getFirst( SearchConstants.CASESENSITIVE_PARAMETER ) ) ) {
            isValidQuery = false;
            LOGGER.debug( "The query is not valid because the {} parameter with value {} is not valid", SearchConstants.CASESENSITIVE_PARAMETER,
                    queryParameters.getFirst( SearchConstants.CASESENSITIVE_PARAMETER ) );
        } else if ( !SearchUtils.isBooleanNullOrBlank( queryParameters.getFirst( SearchConstants.STRICTMODE_PARAMETER ) ) ) {
            isValidQuery = false;
            LOGGER.debug( "The query is not valid because the {} parameter with value {} is not valid", SearchConstants.STRICTMODE_PARAMETER,
                    queryParameters.getFirst( SearchConstants.STRICTMODE_PARAMETER ) );
        } else if ( !SearchUtils.isBooleanNullOrBlank( queryParameters.getFirst( SearchConstants.STATUS_PARAMETER ) ) ) {
            isValidQuery = false;
            LOGGER.debug( "The query is not valid because the {} parameter with value {} is not valid", SearchConstants.STATUS_PARAMETER,
                    queryParameters.getFirst( SearchConstants.STATUS_PARAMETER ) );
        } else if ( !SearchUtils.isBooleanNullOrBlank( queryParameters.getFirst( SearchConstants.FUZZY_PARAMETER ) ) ) {
            isValidQuery = false;
            LOGGER.debug( "The query is not valid because the {} parameter with value {} is not valid", SearchConstants.FUZZY_PARAMETER, queryParameters.getFirst( SearchConstants.FUZZY_PARAMETER ) );
        } else if ( !SearchUtils.isBooleanNullOrBlank( queryParameters.getFirst( SearchConstants.DEDUP_PARAMETER ) ) ) {
            isValidQuery = false;
            LOGGER.debug( "The query is not valid because the {} parameter with value {} is not valid", SearchConstants.DEDUP_PARAMETER, queryParameters.getFirst( SearchConstants.DEDUP_PARAMETER ) );
        } else {
            isValidQuery = isUniqueQuery( queryParameters, sourceId );
            LOGGER.debug( "Checking if the query is valid: {}", isValidQuery );
        }

        return isValidQuery;
    }

    protected boolean isUniqueQuery( MultivaluedMap<String, String> queryParameters, String sourceId ) {
        boolean isUniqueQuery = true;
        String oid = queryParameters.getFirst( SearchConstants.OID_PARAMETER );

        if ( StringUtils.isNotBlank( oid ) ) {
            isUniqueQuery = queryRequestCache.isQueryIdUnique( oid );
        } else {
            String uuid = UUID.randomUUID().toString();
            queryParameters.putSingle( SearchConstants.OID_PARAMETER, uuid );
            queryRequestCache.add( uuid );
        }

        String path = queryParameters.getFirst( SearchConstants.PATH_PARAMETER );
        if ( StringUtils.isNotBlank( path ) ) {
            String[] pathValues = path.split( "," );
            if ( ArrayUtils.contains( pathValues, sourceId ) ) {
                isUniqueQuery = false;
                LOGGER.debug( "The '{}' with value '{}' contains the local source id {}", SearchConstants.PATH_PARAMETER, path, sourceId );
            }
        } else {
            queryParameters.putSingle( SearchConstants.PATH_PARAMETER, catalogFramework.getId() );
        }
        return isUniqueQuery;
    }
    
    protected String getURLScheme() {
        return StringUtils.substringBefore( SystemBaseUrl.getProtocol(), ":" );
    }

}
