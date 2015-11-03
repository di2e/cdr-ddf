/**
 * Copyright (C) 2014 Cohesive Integrations, LLC (info@cohesiveintegrations.com)
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

import net.di2e.ecdr.api.auditor.SearchAuditor;
import net.di2e.ecdr.api.cache.QueryRequestCache;
import net.di2e.ecdr.api.query.QueryConfiguration;
import net.di2e.ecdr.api.query.QueryCriteria;
import net.di2e.ecdr.api.query.QueryLanguage;
import net.di2e.ecdr.api.transform.TransformIdMapper;
import net.di2e.ecdr.commons.constants.SearchConstants;
import net.di2e.ecdr.commons.query.CDRQueryImpl;
import net.di2e.ecdr.commons.util.SearchUtils;
import net.di2e.ecdr.commons.xml.fs.SourceDescription;
import net.di2e.ecdr.commons.xml.osd.OpenSearchDescription;
import net.di2e.ecdr.commons.xml.osd.Query;
import net.di2e.ecdr.commons.xml.osd.SyndicationRight;
import net.di2e.ecdr.commons.xml.osd.Url;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.codice.ddf.configuration.impl.ConfigurationWatcherImpl;
import org.codice.ddf.spatial.geocoder.GeoCoder;
import org.codice.ddf.spatial.geocoder.GeoResult;
import org.opengis.geometry.primitive.Point;
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

public abstract class AbstractRestSearchEndpoint implements RegistrableService {

    private static final Logger LOGGER = LoggerFactory.getLogger( AbstractRestSearchEndpoint.class );

    private QueryRequestCache queryRequestCache = null;

    private CatalogFramework catalogFramework = null;
    private ConfigurationWatcherImpl platformConfig = null;
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
     * @param config ConfigurationWatcherImpl used to get the platform configuration values
     * @param queryLangs
     * @param mapper
     * @param auditorList
     * @param queryConfig
     * @param queryReqCache
     * @param geoCoderList
     */
    public AbstractRestSearchEndpoint( CatalogFramework framework, ConfigurationWatcherImpl config, List<QueryLanguage> queryLangs, TransformIdMapper mapper, List<SearchAuditor> auditorList,
            QueryConfiguration queryConfig, QueryRequestCache queryReqCache, List<Object> geoCoderList ) {
        this.catalogFramework = framework;
        this.platformConfig = config;
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
        boolean isValid = isValidQuery( queryParams, platformConfig.getSiteName() );
        return isValid ? Response.ok().build() : Response.status( Response.Status.BAD_REQUEST ).build();
    }

    /*
     * protected QueryLanguage getQueryLanguage( MultivaluedMap<String, String> queryParams ) { String lang =
     * StringUtils.defaultIfBlank( queryParams.getFirst( SearchConstants.QUERYLANGUAGE_PARAMETER ),
     * queryConfiguration.getDefaultQueryLanguage() ); LOGGER.debug(
     * "Using query language that is associated with the name [{}]", lang ); return queryLanguageMap.get( lang ); }
     */

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
                        List<Point> bbox = result.getBbox();
                        if (!bbox.isEmpty()) {
                            Point upperLeft = bbox.get( 0 );
                            Point lowerRight = bbox.get( 1 );
                            // box is in the format west, south, east, north
                            // if upperLeft is (x1,y1) and lowerRight is (x2,y2) this translates to x1, y2, x2, y1
                            String boxStr = upperLeft.getDirectPosition().getCoordinate()[0] + "," + lowerRight.getDirectPosition().getCoordinate()[1] + ","
                                + lowerRight.getDirectPosition().getCoordinate()[0] + "," + upperLeft.getDirectPosition().getCoordinate()[1];
                            queryParameters.add( SearchConstants.BOX_PARAMETER, boxStr );
                        }
                    } else if (result.getPoint() != null) {
                        Point point = result.getPoint();
                        StringWriter wktStr = new StringWriter();
                        wktStr.append("POINT (");
                        wktStr.append(Double.toString( point.getDirectPosition().getCoordinate()[0]) );
                        wktStr.append(" ");
                        wktStr.append(Double.toString( point.getDirectPosition().getCoordinate()[1]) );
                        wktStr.append(")");
                        queryParameters.add( SearchConstants.GEOMETRY_PARAMETER, wktStr.toString() );
                    } else {
                        // issue within the geocoder, it had a result but nothing converted in it
                        continue;
                    }
                    return;
                }
            }
        }

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
            String localSourceId = platformConfig.getSiteName();
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
            Map<String, Serializable> transformProperties = SearchUtils.getTransformLinkProperties( uriInfo, query, queryResponse, platformConfig.getSchemeFromProtocol(),
                    platformConfig.getHostname(), platformConfig.getPort() );
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
        osd.setShortName( platformConfig.getSiteName() );
        osd.setDescription( getServiceDescription() );
        osd.setTags( "ecdr opensearch cdr ddf" );
        if ( StringUtils.isNotBlank( platformConfig.getOrganization() ) ) {
            osd.setDeveloper( platformConfig.getOrganization() );
        }
        if ( StringUtils.isNotBlank( platformConfig.getContactEmailAddress() ) ) {
            osd.setContact( platformConfig.getContactEmailAddress() );
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
        // @formatter:off
        return "?q={os:searchTerms?}"
                + "&count={os:count?}"
                + "&startIndex={os:startIndex?}"
                + "&queryLanguage=" + languageName
                + "&format={cdrs:responseFormat?}"
                + "&timeout={cdrs:timeout?}"
                + "&status={cdrb:includeStatus?}"
                + "&oid={cdrsx:originQueryID?}"
                + "&strictMode={cdrsx:strictMode?}"
                + "&path={cdrb:path?}";
        // @formatter:on
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
        urlBuilder.append( platformConfig.getProtocol() );
        urlBuilder.append( platformConfig.getHostname() );
        urlBuilder.append( ":" );
        urlBuilder.append( platformConfig.getPort() );
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

}
