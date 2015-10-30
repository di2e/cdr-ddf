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
package net.di2e.ecdr.search.endpoint.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import net.di2e.ecdr.api.auditor.SearchAuditor;
import net.di2e.ecdr.api.cache.QueryRequestCache;
import net.di2e.ecdr.api.query.QueryConfiguration;
import net.di2e.ecdr.api.query.QueryLanguage;
import net.di2e.ecdr.api.transform.TransformIdMapper;
import net.di2e.ecdr.commons.endpoint.rest.AbstractRestSearchEndpoint;
import net.di2e.ecdr.commons.query.CDRQueryImpl;
import net.di2e.ecdr.commons.xml.fs.SourceDescription;
import net.di2e.ecdr.commons.xml.osd.OpenSearchDescription;
import net.di2e.ecdr.federation.FifoFederationStrategy;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.codice.ddf.configuration.impl.ConfigurationWatcherImpl;
import org.codice.ddf.spatial.geocoder.GeoCoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.catalog.CatalogFramework;
import ddf.catalog.federation.FederationException;
import ddf.catalog.operation.QueryRequest;
import ddf.catalog.operation.QueryResponse;
import ddf.catalog.operation.impl.QueryRequestImpl;
import ddf.catalog.source.SourceUnavailableException;
import ddf.catalog.source.UnsupportedQueryException;

/**
 * JAX-RS Web Service which implements the CDR REST Search Specification which is based on Open Search
 *
 * @author Jeff Vettraino
 */
@Path( "/" )
public class CDRRestSearchServiceImpl extends AbstractRestSearchEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger( CDRRestSearchServiceImpl.class );

    private static final String RELATIVE_URL = "/services/cdr/search/rest";
    private static final String SERVICE_TYPE = "CDR REST Search Service";

    private FifoFederationStrategy fifoFederationStratgey = null;

    private static final Map<String, String> REGISTRABLE_PROPERTIES = new HashMap<String, String>();
    static {
        REGISTRABLE_PROPERTIES.put( "receiveTimeoutSeconds", "0" );
        REGISTRABLE_PROPERTIES.put( "connectionTimeoutSeconds", "30" );
        REGISTRABLE_PROPERTIES.put( "maxResultCount", "0" );
        REGISTRABLE_PROPERTIES.put( "doSourcePing", "true" );
        REGISTRABLE_PROPERTIES.put( "availableCheckCacheTime", "60" );
    }

    /**
     * Constructor for JAX RS CDR Search Service. Values should ideally be passed into the constructor using a
     * dependency injection framework like blueprint
     *
     * @param framework
     *            Catalog Framework which will be used for search
     * @param config
     *            ConfigurationWatcherImpl used to get the platform configuration values
     * @param builder
     *            FilterBuilder implementation
     * @param parser
     *            The instance of the QueryParser to use which will determine how to parse the parameters from the queyr
     *            String. Query parsers are tied to different versions of a query profile
     * @param mapper
     *            The transformation mapper for handling mapping the external CDR transform name to the internal DDF
     *            transform name
     */
    public CDRRestSearchServiceImpl( CatalogFramework framework, ConfigurationWatcherImpl config, List<QueryLanguage> queryLangs, TransformIdMapper mapper, List<SearchAuditor> auditorList,
            QueryConfiguration queryConfig, QueryRequestCache queryCache, FifoFederationStrategy fedStrategy, List<GeoCoder> geoCoderList ) {
        super( framework, config, queryLangs, mapper, auditorList, queryConfig, queryCache, geoCoderList );
        fifoFederationStratgey = fedStrategy;
    }

    @HEAD
    public Response ping( @Context UriInfo uriInfo, @HeaderParam( "Accept-Encoding" ) String encoding, @HeaderParam( "Authorization" ) String auth ) {
        Response response = executePing( uriInfo, encoding, auth );
        LOGGER.debug( "Ping (HTTP HEAD) was called to check if the CDR Search Endpoint is available, result is [{}]", response.getStatus() );
        return response;
    }

    @GET
    public Response search( @Context MessageContext context, @HeaderParam( "Accept-Encoding" ) String encoding, @HeaderParam( "Authorization" ) String auth ) {
        UriInfo uriInfo = context.getUriInfo();
        LOGGER.debug( "Query received on CDR Search Endpoint: {}", uriInfo.getRequestUri() );
        return executeSearch( context.getHttpServletRequest(), uriInfo, encoding, auth );
    }

    @Override
    public String getServiceType() {
        return SERVICE_TYPE;
    }

    @Override
    public String getServiceRelativeUrl() {
        return RELATIVE_URL;
    }

    @Override
    public String getServiceDescription() {
        return "Provides a RESTful search service using the CDR Search specification.";
    }

    @Override
    public Map<String, String> getProperties() {
        return REGISTRABLE_PROPERTIES;
    }

    @Override
    protected void addSourceDescriptions( OpenSearchDescription osd ) {
        // federated sites
        String curSource = getCatalogFramework().getId();
        SourceDescription description = new SourceDescription();
        description.setSourceId( curSource );
        description.setShortName( curSource );
        osd.getAny().add( description );
    }

    @Override
    public QueryResponse executeQuery( String localSourceId, MultivaluedMap<String, String> queryParameters, CDRQueryImpl query ) throws SourceUnavailableException, UnsupportedQueryException,
            FederationException {
        QueryRequest queryRequest = new QueryRequestImpl( query, false, Arrays.asList( localSourceId ), getQueryProperties( queryParameters, localSourceId ) );
        QueryResponse queryResponse = getCatalogFramework().query( queryRequest, fifoFederationStratgey );
        return queryResponse;
    }

}
