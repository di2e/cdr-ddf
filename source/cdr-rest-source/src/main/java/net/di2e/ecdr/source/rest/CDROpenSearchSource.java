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
package net.di2e.ecdr.source.rest;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.di2e.ecdr.api.cache.CacheManager;
import net.di2e.ecdr.api.queryresponse.SearchResponseTransformer;
import net.di2e.ecdr.commons.constants.SearchConstants;
import net.di2e.ecdr.commons.filter.StrictFilterDelegate;
import net.di2e.ecdr.search.transform.atom.response.AtomResponseTransformer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.cxf.transport.http.HTTPConduit;
import org.codice.ddf.security.common.jaxrs.RestSecurity;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.catalog.data.ContentType;
import ddf.catalog.data.Metacard;
import ddf.catalog.data.Result;
import ddf.catalog.data.impl.ResultImpl;
import ddf.catalog.filter.FilterAdapter;
import ddf.catalog.operation.Query;
import ddf.catalog.operation.QueryRequest;
import ddf.catalog.operation.ResourceResponse;
import ddf.catalog.operation.SourceResponse;
import ddf.catalog.operation.impl.ResourceRequestByProductUri;
import ddf.catalog.operation.impl.ResourceResponseImpl;
import ddf.catalog.operation.impl.SourceResponseImpl;
import ddf.catalog.resource.ResourceNotFoundException;
import ddf.catalog.resource.ResourceNotSupportedException;
import ddf.catalog.resource.impl.ResourceImpl;
import ddf.catalog.source.ConnectedSource;
import ddf.catalog.source.FederatedSource;
import ddf.catalog.source.SourceMonitor;
import ddf.catalog.source.UnsupportedQueryException;
import ddf.security.SecurityConstants;
import ddf.security.Subject;

public class CDROpenSearchSource extends CDRSourceConfiguration implements FederatedSource, ConnectedSource {

    private static final Logger LOGGER = LoggerFactory.getLogger( CDROpenSearchSource.class );
    private static final String HEADER_ACCEPT_RANGES = "Accept-Ranges";
    private static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
    private static final String HEADER_RANGE = "Range";
    private static final String BYTES_TO_SKIP = "BytesToSkip";
    private static final String BYTES_SKIPPED_RESPONSE = "BytesSkipped";
    private static final String BYTES = "bytes";
    private static final String BYTES_EQUAL = "bytes=";

    private SourceMonitor siteAvailabilityCallback = null;
    private FilterAdapter filterAdapter = null;

    private Date lastAvailableCheckDate = null;
    private boolean isCurrentlyAvailable = false;
    private String localId = null;

    private WebClient cdrRestClient = null;
    private WebClient cdrAvailabilityCheckClient = null;

    public CDROpenSearchSource( FilterAdapter adapter, CacheManager<Metacard> manager ) {
        super( manager );
        this.filterAdapter = adapter;
    }

    @Override
    public SourceResponse query( QueryRequest queryRequest ) throws UnsupportedQueryException {
        try {
            Query query = queryRequest.getQuery();
            SourceResponse sourceResponse;
            // ECDR-72 Add in default radius
            Map<String, String> filterParameters = filterAdapter.adapt( query, new StrictFilterDelegate( false, getSupportedGeoOption(), getPropertyMap(), getDateTypeMap() ) );
            String id = filterParameters.get( SearchConstants.UID_PARAMETER );
            // check if this is an id-only query
            if ( StringUtils.isBlank( id ) ) {
                // non-id query, perform normal search
                sourceResponse = doQuery( filterParameters, queryRequest );
            } else {
                // id-only query, check if remote source supports it
                if ( supportsQueryById() ) {
                    sourceResponse = doQuery( filterParameters, queryRequest );
                } else {
                    sourceResponse = lookupById( queryRequest, id );
                }
            }
            return sourceResponse;

        } catch ( Exception e ) {
            LOGGER.error( e.getMessage(), e );
            throw new UnsupportedQueryException( "Could not complete query to site [" + localId + "] due to: " + e.getMessage(), e );
        }
    }

    protected SourceResponse doQuery( Map<String, String> filterParameters, QueryRequest queryRequest ) throws UnsupportedQueryException {
        SourceResponse sourceResponse;
        SearchResponseTransformer transformer = lookupSearchResponseTransformer();

        setSecurityCredentials( cdrRestClient, queryRequest.getProperties() );
        filterParameters.putAll( getInitialFilterParameters( queryRequest ) );
        setURLQueryString( filterParameters );
        setHttpHeaders( filterParameters, cdrRestClient );
        LOGGER.debug( "Executing http GET query to source [{}] with url [{}]", localId, cdrRestClient.getCurrentURI().toString() );
        // TLSUtil.setTLSOptions( cdrRestClient );
        Response response = cdrRestClient.get();
        LOGGER.debug( "Query to source [{}] returned http status code [{}] and media type [{}]", localId, response.getStatus(), response.getMediaType() );

        if ( response.getStatus() == Status.OK.getStatusCode() ) {
            // Be sure to pass in the getId() instead of the localId so Connected sources populate the Metacard with the
            // right Id
            sourceResponse = transformer.processSearchResponse( (InputStream) response.getEntity(), queryRequest, getId() );
            if ( !supportsQueryById() ) {
                sourceResponse = cacheResults( sourceResponse );
            }
        } else {
            Object entity = response.getEntity();
            if ( entity != null ) {
                try {
                    LOGGER.warn( "Error status code received [{}] when querying site [{}]:{}[{}]", response.getStatus(), localId, System.lineSeparator(), IOUtils.toString( (InputStream) entity ) );
                } catch ( IOException e ) {
                    LOGGER.warn( "Error status code received [{}] when querying site [{}]", response.getStatus(), localId );
                }
            } else {
                LOGGER.warn( "Error status code received [{}] when querying site [{}]", response.getStatus(), localId );
            }
            throw new UnsupportedQueryException( "Query to remote source returned http status code " + response.getStatus() );
        }
        return sourceResponse;
    }

    @Override
    public boolean isAvailable() {
        LOGGER.debug( "isAvailable method called on CDR Rest Source named [{}], determining whether to check availability or pull from cache", localId );
        if ( getPingMethod() != null && !PingMethod.NONE.equals( getPingMethod() ) && cdrAvailabilityCheckClient != null ) {
            if ( !isCurrentlyAvailable || (lastAvailableCheckDate.getTime() < System.currentTimeMillis() - getAvailableCheckCacheTime()) ) {
                LOGGER.debug( "Checking availability on CDR Rest Source named [{}] in real time by calling endpoint [{}]", localId, cdrAvailabilityCheckClient.getBaseURI() );
                try {

                    Response response = PingMethod.HEAD.equals( getPingMethod() ) ? getPingClient().head() : getPingClient().get();
                    if ( response.getStatus() == Status.OK.getStatusCode() || response.getStatus() == Status.ACCEPTED.getStatusCode() ) {
                        isCurrentlyAvailable = true;
                        lastAvailableCheckDate = new Date();
                    } else {
                        isCurrentlyAvailable = false;
                    }
                } catch ( RuntimeException e ) {
                    LOGGER.warn( "CDR Rest Source named [" + localId + "] encountered an unexpected error while executing HTTP Head at URL [" + cdrAvailabilityCheckClient.getBaseURI() + "]:"
                            + e.getMessage() );
                    LOGGER.debug( "Exception while trying to check avilability of site {}", localId, e );
                    isCurrentlyAvailable = false;
                }

            } else {
                LOGGER.debug( "Pulling availability of CDR Rest Federated Source named [{}] from cache, isAvailable=[{}]", localId, isCurrentlyAvailable );
            }
            if ( siteAvailabilityCallback != null ) {
                if ( isCurrentlyAvailable ) {
                    siteAvailabilityCallback.setAvailable();
                } else {
                    siteAvailabilityCallback.setUnavailable();
                }
            }
        } else {
            LOGGER.debug( "HTTP Ping is set to false so not checking the sites availability, just setting to available" );
            isCurrentlyAvailable = true;
            if ( siteAvailabilityCallback != null ) {
                siteAvailabilityCallback.setAvailable();
            }
        }
        return isCurrentlyAvailable;
    }

    @Override
    public void setId( String id ) {
        LOGGER.debug( "ConfigUpdate: Updating site name to [{}] by setId method", id );
        super.setId( id );
        localId = id;
    }

    @Override
    public void setShortname( String id ) {
        LOGGER.debug( "ConfigUpdate: Updating site name to [{}] by setShortname method", id );
        super.setId( id );
        localId = id;
    }

    @Override
    public boolean isAvailable( SourceMonitor callback ) {
        this.siteAvailabilityCallback = callback;
        return isAvailable();
    }

    @Override
    public Set<ContentType> getContentTypes() {
        return Collections.emptySet();
    }

    @Override
    public Set<String> getOptions( Metacard paramMetacard ) {
        return Collections.emptySet();
    }

    @Override
    public Set<String> getSupportedSchemes() {
        return Collections.emptySet();
    }

    @Override
    public ResourceResponse retrieveResource( URI uri, Map<String, Serializable> requestProperties ) throws ResourceNotFoundException, ResourceNotSupportedException, IOException {
        LOGGER.debug( "Retrieving Resource from remote CDR Source named [{}] using URI [{}]", localId, uri );

        // Check to see if the resource-uri value was passed through which is
        // the original metacard uri which
        // can be different from what was returned or used by the client
        Serializable resourceUriProperty = requestProperties.get( Metacard.RESOURCE_URI );
        if ( resourceUriProperty != null && resourceUriProperty instanceof URI ) {
            URI resourceUri = (URI) resourceUriProperty;
            if ( !resourceUri.equals( uri ) ) {
                LOGGER.debug( "Overriding the passed in resourceUri [{}] with the value found in the request properties [{}]", uri, resourceUri );
                uri = resourceUri;
            }

        } else if ( uri != null ) {
            String scheme = uri.getScheme();
            if ( !"http".equalsIgnoreCase( scheme ) && !"https".equalsIgnoreCase( scheme ) ) {
                uri = getURIFromMetacard( uri );
            }
        }

        ResourceResponse resourceResponse = null;
        if ( uri != null ) {
            LOGGER.debug( "Retrieving the remote resource using the uri [{}]", uri );
            WebClient retrieveWebClient = WebClient.create( uri );
            HTTPConduit conduit = WebClient.getConfig( retrieveWebClient ).getHttpConduit();
            TLSUtil.setTLSOptions( retrieveWebClient, getDisableCNCheck() );
            resourceResponse = doRetrieval( retrieveWebClient, requestProperties );
        }

        if ( resourceResponse == null ) {
            LOGGER.warn( "Could not retrieve resource from CDR Source named [{}] using uri [{}]", localId, uri );
            throw new ResourceNotFoundException( "Could not retrieve resource from source [" + localId + "] and uri [" + uri + "]" );
        }
        return resourceResponse;
    }

    protected ResourceResponse doRetrieval( WebClient retrieveWebClient, Map<String, Serializable> requestProperties ) throws ResourceNotFoundException, IOException {
        ResourceResponse resourceResponse = null;
        setSecurityCredentials( retrieveWebClient, requestProperties );
        URI uri = retrieveWebClient.getCurrentURI();
        try {

            Long bytesToSkip = null;
            // If a bytesToSkip property is present add range header
            if ( requestProperties != null && requestProperties.containsKey( BYTES_TO_SKIP ) ) {
                bytesToSkip = (Long) requestProperties.get( BYTES_TO_SKIP );
                if ( bytesToSkip != null ) {
                    LOGGER.debug( "Setting Range header on retrieve request from remote CDR Source [{}] with bytes to skip [{}]", localId, bytesToSkip );
                    // This creates a Range header in the following manner if
                    // 100 bytes were to be skipped. The end - means its open
                    // ended
                    // Range: bytes=100-
                    retrieveWebClient.header( HEADER_RANGE, BYTES_EQUAL + bytesToSkip + "-" );
                }
            }

            Response clientResponse = retrieveWebClient.get();

            MediaType mediaType = clientResponse.getMediaType();
            MimeType mimeType = null;
            try {
                mimeType = (mediaType == null) ? new MimeType( "application/octet-stream" ) : new MimeType( mediaType.toString() );
                LOGGER.debug( "Creating mime type from CDR Source named [{}] using uri [{}] with value [{}] defaulting to [{}]", localId, uri, mediaType );
            } catch ( MimeTypeParseException e ) {
                try {
                    mimeType = new MimeType( "application/octet-stream" );
                    LOGGER.warn( "Creating mime type from CDR Source named [{}] using uri [{}] with value [{}] defaulting to [{}]", localId, uri, "application/octet-stream" );
                } catch ( MimeTypeParseException e1 ) {
                    LOGGER.error( "Could not create MIMEType for resource being retrieved", e1 );
                }

            }

            String dispositionString = clientResponse.getHeaderString( HEADER_CONTENT_DISPOSITION );

            String fileName = null;
            if ( dispositionString != null ) {
                ContentDisposition contentDisposition = new ContentDisposition( dispositionString );
                fileName = contentDisposition.getParameter( "filename" );
                if ( fileName == null ) {
                    fileName = contentDisposition.getParameter( "\"filename\"" );
                }
                if ( fileName == null ) {
                    // ECDR-74 use MIMEType parser to get the file extension in
                    fileName = getId() + "-" + System.currentTimeMillis();
                }
            } else {
                // ECDR-74 use MIMEType parser to get the file extension in this
                // case
                fileName = getId() + "-" + System.currentTimeMillis();
            }

            InputStream binaryStream = (InputStream) clientResponse.getEntity();
            if ( binaryStream != null ) {
                Map<String, Serializable> responseProperties = new HashMap<String, Serializable>();
                if ( bytesToSkip != null ) {
                    // Since we sent a range header an accept-ranges header
                    // should be returned if the
                    // remote endpoint support it. If is not present, the
                    // inputStream hasn't skipped ahead
                    // by the given number of bytes, so we need to take care of
                    // it here.
                    String rangeHeader = clientResponse.getHeaderString( HEADER_ACCEPT_RANGES );
                    if ( rangeHeader == null || !rangeHeader.equals( BYTES ) ) {
                        LOGGER.debug( "Skipping {} bytes in CDR Remote Source because endpoint didn't support Range Headers", bytesToSkip );
                        try {
                            // the Java inputStream.skip() method is not
                            // guaranteed to skip all the bytes so we use a
                            // utility method that is
                            IOUtils.skipFully( binaryStream, bytesToSkip );
                        } catch ( EOFException e ) {
                            LOGGER.warn( "Skipping the requested number of bytes [{}] for URI [{}] resulted in an End of File, so re-retrieving the complete file without skipping bytes: {}",
                                    bytesToSkip, uri, e.getMessage() );
                            try {
                                binaryStream.close();
                            } catch ( IOException e1 ) {
                                LOGGER.debug( "Error encountered while closing inputstream" );
                            }
                            return doRetrieval( retrieveWebClient, null );
                        }
                    } else if ( rangeHeader != null && rangeHeader.equals( BYTES ) ) {
                        LOGGER.debug( "CDR Remote source supports Range Headers, only retrieving part of file that has not been downloaded yet." );
                        responseProperties.put( BYTES_SKIPPED_RESPONSE, Boolean.TRUE );
                    }
                }
                resourceResponse = new ResourceResponseImpl( new ResourceRequestByProductUri( uri, requestProperties ), responseProperties, new ResourceImpl( binaryStream, mimeType, fileName ) );
            }
        } catch ( RuntimeException e ) {
            LOGGER.warn( "Expected exception encountered when trying to retrieve resource with URI [{}] from source [{}]", uri, localId );
        }
        return resourceResponse;
    }

    protected SearchResponseTransformer lookupSearchResponseTransformer() throws UnsupportedQueryException {
        SearchResponseTransformer transformer;
        if ( StringUtils.isBlank( getResponseTransformerName() ) ) {
            transformer = new AtomResponseTransformer( getAtomResponseTransformerConfig() );
            LOGGER.debug( "Using the default Atom Response Transformer to transform response from site [{}]", localId );
        } else {
            transformer = getSearchResponseTransformer( getResponseTransformerName() );
        }
        if ( transformer == null ) {
            throw new UnsupportedQueryException( "The query was not executed on the source " + localId + " because the response transformer was not a valid value [" + getResponseTransformerName()
                    + "]. Please check the source configuration value for 'Response Transformer Override'" );
        }
        return transformer;
    }

    protected void setURLQueryString( Map<String, String> filterParameters ) {
        cdrRestClient.resetQuery();
        for ( Entry<String, String> entry : filterParameters.entrySet() ) {
            String parameterName = getParameterMap().get( entry.getKey() );
            if ( StringUtils.isNotBlank( parameterName ) ) {
                cdrRestClient.replaceQueryParam( parameterName, entry.getValue() );
            }
        }

        Map<String, String> hardcodedQueryParams = getHardcodedQueryParameters();
        for ( Entry<String, String> entry : hardcodedQueryParams.entrySet() ) {
            cdrRestClient.replaceQueryParam( entry.getKey(), entry.getValue() );
        }
    }

    protected Map<String, String> getInitialFilterParameters( QueryRequest request ) {
        Map<String, String> filterParameters = new HashMap<String, String>();
        Map<String, Serializable> queryRequestProps = request.getProperties();

        if ( LOGGER.isDebugEnabled() ) {
            LOGGER.debug( "CDR REST Source received Query: " + ToStringBuilder.reflectionToString( request.getQuery() ) );
        }

        // include format parameter
        String format = (String) queryRequestProps.get( SearchConstants.FORMAT_PARAMETER );
        if ( StringUtils.isNotBlank( format ) ) {
            filterParameters.put( SearchConstants.FORMAT_PARAMETER, format );
        }

        // Strict Mode
        Boolean strictMode = (Boolean) queryRequestProps.get( SearchConstants.STRICTMODE_PARAMETER );
        if ( strictMode != null ) {
            filterParameters.put( SearchConstants.STRICTMODE_PARAMETER, String.valueOf( strictMode ) );
        }

        Query query = request.getQuery();

        // Include timeout
        long timeout = query.getTimeoutMillis();
        if ( timeout > 1000 ) {
            filterParameters.put( SearchConstants.TIMEOUT_PARAMETER, String.valueOf( timeout ) );
        }

        if ( getParameterMap().containsKey( SearchConstants.COUNT_PARAMETER ) ) {
            int pageSize = query.getPageSize();
            filterParameters
                    .put( SearchConstants.COUNT_PARAMETER, getMaxResultsCount() > 0 && pageSize > getMaxResultsCount() ? String.valueOf( getMaxResultsCount() ) : String.valueOf( pageSize ) );
        }

        if ( getParameterMap().containsKey( SearchConstants.STARTINDEX_PARAMETER ) ) {
            int startIndex = query.getStartIndex();
            filterParameters.put( SearchConstants.STARTINDEX_PARAMETER, String.valueOf( getAtomResponseTransformerConfig().isZeroBasedStartIndex() ? startIndex - 1 : startIndex ) );
        }

        String sortOrderString = getSortOrderString( query.getSortBy() );
        LOGGER.trace( "Getting sort order for query [{}]", sortOrderString );
        if ( sortOrderString != null ) {
            filterParameters.put( SearchConstants.SORTKEYS_PARAMETER, sortOrderString );
        }

        for ( Entry<String, Serializable> entry : queryRequestProps.entrySet() ) {
            String key = entry.getKey();
            if ( getParameterMap().containsKey( key ) || getHttpHeaderList().contains( key ) ) {
                String value = (String) entry.getValue();
                if ( StringUtils.isNotBlank( value ) ) {
                    filterParameters.put( key, value );
                }
            }
        }

        LOGGER.trace( "Filter Parameters being evaluated for inclusion in outgoing query {} which were parsed from", filterParameters );
        return filterParameters;
    }

    protected URI getURIFromMetacard( URI uri ) {
        URI returnUri = null;
        Map<String, String> uriMap = new HashMap<String, String>( 3 );
        uriMap.put( Metacard.RESOURCE_URI, uri.toString() );
        setURLQueryString( uriMap );
        Response response = cdrRestClient.get();
        AtomResponseTransformer transformer = new AtomResponseTransformer( getAtomResponseTransformerConfig() );
        SourceResponse sourceResponse = transformer.processSearchResponse( (InputStream) response.getEntity(), null, getId() );
        List<Result> results = sourceResponse.getResults();
        if ( !results.isEmpty() ) {
            returnUri = results.get( 0 ).getMetacard().getResourceURI();
        }
        return returnUri;
    }

    public SourceResponse cacheResults( SourceResponse sourceResponse ) {
        for ( Result result : sourceResponse.getResults() ) {
            Metacard metacard = result.getMetacard();
            getMetacardCache().put( metacard.getId(), metacard );
        }
        return sourceResponse;
    }

    private void setSecurityCredentials( WebClient client, Map<String, Serializable> requestProperties ) {
        if ( isSendSecurityCookie() ) {
            if ( requestProperties.containsKey( SecurityConstants.SECURITY_SUBJECT ) ) {
                Serializable property = requestProperties.get( SecurityConstants.SECURITY_SUBJECT );
                if ( property instanceof Subject ) {
                    Subject subject = (Subject) property;
                    RestSecurity.setUnsecuredSubjectOnClient( subject, client );
                }
            }
        }
    }

    protected SearchResponseTransformer getSearchResponseTransformer( String id ) {
        SearchResponseTransformer transformer = null;
        Bundle bundle = FrameworkUtil.getBundle( this.getClass() );
        if ( bundle != null ) {
            BundleContext context = bundle.getBundleContext();
            Collection<ServiceReference<SearchResponseTransformer>> transformers;
            try {
                transformers = context.getServiceReferences( SearchResponseTransformer.class, "(id=" + id + ")" );
                int size = transformers.size();
                if ( size > 0 ) {
                    transformer = context.getService( transformers.iterator().next() );
                    if ( size > 1 ) {
                        LOGGER.debug( "Multiple [{}] InputTransformers were returned when looking up InputTransformer with id [{}], using the first one {}", size, id, transformer.getClass()
                                .getName() );
                    }
                }
            } catch ( InvalidSyntaxException e ) {
                LOGGER.warn( "Could not lookup input transformer with id [{}]", id, e.getMessage() );
            }
        }
        return transformer;
    }

    protected SourceResponse lookupById( QueryRequest queryRequest, String id ) throws UnsupportedQueryException {
        SourceResponse sourceResponse = null;
        LOGGER.debug( "Checking cache for Result with id [{}].", id );
        Metacard metacard = getMetacardCache().get( id );
        if ( metacard != null ) {
            metacard.setSourceId( getId() );
            LOGGER.debug( "Cache hit found for id [{}], returning response", id );
            sourceResponse = new SourceResponseImpl( queryRequest, Arrays.asList( (Result) new ResultImpl( metacard ) ), 1L );
        } else {
            LOGGER.debug( "Could not find result id [{}] in cache", id );
            throw new UnsupportedQueryException( "Queries for parameter uid are not supported by source [" + localId + "]" );
        }
        return sourceResponse;
    }

    protected void setHttpHeaders( Map<String, String> filterParameters, WebClient client ) {
        Map<String, String> hardcodedHeaders = getHardcodedHttpHeaders();
        if ( MapUtils.isNotEmpty( hardcodedHeaders ) ) {
            for ( Entry<String, String> entry : hardcodedHeaders.entrySet() ) {
                String key = entry.getKey();
                String value = entry.getValue();
                client.header( key, value );
                LOGGER.trace( "Adding the following HTTP Header to outgoing request [{}]=[{}]", key, value );
            }
        }

        List<String> headers = getHttpHeaderList();
        if ( CollectionUtils.isNotEmpty( headers ) ) {
            for ( String header : headers ) {
                if ( filterParameters.containsKey( header ) ) {
                    String value = filterParameters.get( header );
                    client.header( header, value );
                    LOGGER.trace( "Adding the following HTTP Header to outgoing request [{}]=[{}]", header, value );
                }
            }
        }
    }

    protected boolean supportsQueryById() {
        return getParameterMap().containsKey( SearchConstants.UID_PARAMETER );
    }

    @Override
    protected WebClient getRestClient() {
        return cdrRestClient;
    }

    @Override
    protected void setRestClient( WebClient webClient ) {
        cdrRestClient = webClient;
    }

    @Override
    protected WebClient getPingClient() {
        return cdrAvailabilityCheckClient;
    }

    @Override
    protected void setPingClient( WebClient webClient ) {
        cdrAvailabilityCheckClient = webClient;
    }

}
