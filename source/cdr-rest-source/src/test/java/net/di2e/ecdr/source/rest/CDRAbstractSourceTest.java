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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import net.di2e.ecdr.commons.constants.SearchConstants;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.geotools.filter.text.cql2.CQL;
import org.junit.Before;
import org.junit.Test;
import org.opengis.filter.sort.SortBy;
import org.opengis.filter.sort.SortOrder;

import ddf.catalog.data.Result;
import ddf.catalog.filter.impl.SortByImpl;
import ddf.catalog.filter.proxy.adapter.GeotoolsFilterAdapterImpl;
import ddf.catalog.operation.QueryRequest;
import ddf.catalog.operation.SourceResponse;
import ddf.catalog.operation.impl.QueryImpl;
import ddf.catalog.operation.impl.QueryRequestImpl;
import ddf.catalog.source.SourceMonitor;
import ddf.catalog.source.UnsupportedQueryException;

public abstract class CDRAbstractSourceTest {

    private static final String SERVICE_URL = "http://localhost:8181/services/cdr/test";
    private static final String PING_URL = "http://localhost:8181/services/cdr/ping";
    private static final String RESOURCE_URL = "http://localhost:8181/services/catalog/sources/SELF/553b8ce5718c40ac9e093823817bbb98?transform=resource";

    private static final String RESPONSE_FILE = "/exampleResponse.xml";

    protected static final GeotoolsFilterAdapterImpl FILTER_ADAPTER = new GeotoolsFilterAdapterImpl();

    private WebClient client;

    @Before
    public void setUp() {
        client = mock( WebClient.class );
    }

    /**
     * Tests that a site comes back as available using a GET request when the response is OK.
     */
    @Test
    public void testGetPing() {
        WebClient pingClient = mock( WebClient.class );
        CDROpenSearchSource source = configureSource();
        source.setCdrAvailabilityCheckClient( pingClient );
        Response response = mock( Response.class );
        when( response.getStatus() ).thenReturn( Response.Status.OK.getStatusCode() );
        when( pingClient.get() ).thenReturn( response );
        assertTrue( source.isAvailable() );
        // test cache
        assertTrue( source.isAvailable() );
        // verify get was only called once
        verify( pingClient ).get();
    }

    /**
     * Tests that a site comes back as available using a HEAD request when the response is OK.
     */
    @Test
    public void testHeadPing() {
        SourceMonitor monitor = mock( SourceMonitor.class );
        WebClient pingClient = mock( WebClient.class );
        CDROpenSearchSource source = configureSource();
        source.setCdrAvailabilityCheckClient( pingClient );
        Response response = mock( Response.class );
        when( response.getStatus() ).thenReturn( Response.Status.OK.getStatusCode() );
        when( pingClient.head() ).thenReturn( response );
        source.setPingMethodString( CDRSourceConfiguration.PingMethod.HEAD.toString() );
        assertTrue( source.isAvailable( monitor ) );
        // test cache
        assertTrue( source.isAvailable() );
        // verify head was only called once
        verify( pingClient ).head();
        // verify monitor was called available and not unavailable
        verify( monitor, times( 2 ) ).setAvailable();
        verify( monitor, never() ).setUnavailable();
    }

    /**
     * Tests that a site comes back correctly as not available if the ping returns a non-OK response
     */
    @Test
    public void testPingNotAvailable() {
        SourceMonitor monitor = mock( SourceMonitor.class );
        WebClient pingClient = mock( WebClient.class );
        CDROpenSearchSource source = configureSource();
        source.setCdrAvailabilityCheckClient( pingClient );
        Response response = mock( Response.class );
        when( response.getStatus() ).thenReturn( Response.Status.NOT_FOUND.getStatusCode() );
        when( pingClient.head() ).thenReturn( response );
        source.setPingMethodString( CDRSourceConfiguration.PingMethod.HEAD.toString() );
        assertFalse( source.isAvailable( monitor ) );
        verify( pingClient ).head();
        // verify monitor was called unavailable and not available
        verify( monitor ).setUnavailable();
        verify( monitor, never() ).setAvailable();
    }

    /**
     * Tests that a site comes back as available using a HEAD request when the response is OK.
     */
    @Test
    public void testNoPingAvailable() {
        SourceMonitor monitor = mock( SourceMonitor.class );
        WebClient pingClient = mock( WebClient.class );
        CDROpenSearchSource source = configureSource();
        source.setCdrAvailabilityCheckClient( pingClient );
        Response response = mock( Response.class );
        when( response.getStatus() ).thenReturn( Response.Status.OK.getStatusCode() );
        when( pingClient.head() ).thenReturn( response );
        source.setPingMethodString( CDRSourceConfiguration.PingMethod.NONE.toString() );
        assertTrue( source.isAvailable( monitor ) );
        // test cache
        assertTrue( source.isAvailable() );
        // verify ping url was never called
        verify( pingClient, never() ).head();
        // verify monitor was called available and not unavailable
        verify( monitor, times( 2 ) ).setAvailable();
        verify( monitor, never() ).setUnavailable();
    }

    @Test( expected = UnsupportedQueryException.class )
    public void testBadQueryResponse() throws Exception {
        CDROpenSearchSource source = configureSource();
        QueryRequest request = new QueryRequestImpl( new QueryImpl( CQL.toFilter( "id = '12345678910'" ) ) );
        when( client.getCurrentURI() ).thenReturn( new URI( SERVICE_URL ) );
        Response webResponse = mock( Response.class );
        when( webResponse.getStatus() ).thenReturn( Response.Status.NOT_FOUND.getStatusCode() );
        when( webResponse.getEntity() ).thenReturn( IOUtils.toInputStream( "NOT FOUND" ) );
        when( client.get() ).thenReturn( webResponse );
        source.query( request );
        fail( "Should have thrown an error during the query." );
    }

    @Test
    public void testUidQuery() throws Exception {
        performQuery( "id = '12345678910'" );
    }

    @Test
    public void testKeywordQuery() throws Exception {
        performQuery( "metadata like 'example'" );
    }

    @Test
    public void testTemporalQuery() throws Exception {
        performQuery( "created before 2014-05-05T00:00:00" );
    }

    @Test( expected = UnsupportedQueryException.class )
    public void testInvalidResponseTransformer() throws Exception {
        performQuery( "created before 2014-05-05T00:00:00", "blah" );
    }

    @Test
    public void testdoRetrieval() throws Exception {
        CDROpenSearchSource source = configureSource();
        WebClient retrievalClient = mock( WebClient.class );
        Response retrievalResponse = mock( Response.class );
        when( retrievalResponse.getStatus() ).thenReturn( Response.Status.OK.getStatusCode() );
        when( retrievalClient.get() ).thenReturn( retrievalResponse );
        when( client.getCurrentURI() ).thenReturn( new URI( RESOURCE_URL ) );
        when( retrievalResponse.getEntity() ).thenReturn( getClass().getResourceAsStream( RESPONSE_FILE ) );
        source.doRetrieval( retrievalClient, Collections.<String, Serializable>emptyMap() );
    }

    @Test
    public void testdoRetrievalSkipBytes() throws Exception {
        CDROpenSearchSource source = configureSource();
        WebClient retrievalClient = mock( WebClient.class );
        Response retrievalResponse = mock( Response.class );
        when( retrievalResponse.getStatus() ).thenReturn( Response.Status.OK.getStatusCode() );
        when( retrievalClient.get() ).thenReturn( retrievalResponse );
        when( client.getCurrentURI() ).thenReturn( new URI( RESOURCE_URL ) );
        when( retrievalResponse.getEntity() ).thenReturn( getClass().getResourceAsStream( RESPONSE_FILE ) );
        Map<String, Serializable> requestProperties = new HashMap<>();
        requestProperties.put( "BytesToSkip", new Long( 1000 ) );
        // no range header
        source.doRetrieval( retrievalClient, requestProperties );
        // with range header and disposition
        when( retrievalResponse.getHeaderString( "Content-Disposition" ) ).thenReturn( "attachment;filename=test.example" );
        when( retrievalResponse.getHeaderString( "Accept-Ranges" ) ).thenReturn( "bytes" );
        source.doRetrieval( retrievalClient, requestProperties );
    }

    private void performQuery( String CQLStr ) throws Exception {
        this.performQuery( CQLStr, null );
    }

    private void performQuery( String CQLStr, String responseTransformer ) throws Exception {
        CDROpenSearchSource source = configureSource();
        if ( StringUtils.isNotBlank( responseTransformer ) ) {
            source.setResponseTransformer( responseTransformer );
        }
        SortBy sort = new SortByImpl( Result.RELEVANCE, SortOrder.DESCENDING );
        QueryRequestImpl request = new QueryRequestImpl( new QueryImpl( CQL.toFilter( CQLStr ), 0, 20, sort, true, 10000 ) );
        Map<String, Serializable> properties = new HashMap<String, Serializable>();
        properties.put( SearchConstants.STRICTMODE_PARAMETER, Boolean.TRUE );
        request.setProperties( properties );
        when( client.getCurrentURI() ).thenReturn( new URI( SERVICE_URL ) );
        Response webResponse = mock( Response.class );
        when( webResponse.getStatus() ).thenReturn( Response.Status.OK.getStatusCode() );
        when( webResponse.getEntity() ).thenReturn( getClass().getResourceAsStream( RESPONSE_FILE ) );
        when( client.get() ).thenReturn( webResponse );
        SourceResponse response = source.query( request );
        assertEquals( 19, response.getHits() );
        assertFalse( response.getResults().isEmpty() );
        assertEquals( 19, response.getResults().size() );
    }

    private CDROpenSearchSource configureSource() {
        CDROpenSearchSource source = createSource();
        source.setId( "example_site" );
        source.setPingUrl( PING_URL );
        source.setPingMethodString( CDRSourceConfiguration.PingMethod.GET.toString() );
        source.setAvailableCheckCacheTime( 60 );
        source.setMaxResultCount( 10 );
        source.setUrl( SERVICE_URL );
        source.setReceiveTimeoutSeconds( 10 );
        source.setConnectionTimeoutSeconds( 1 );
        source.setCdrRestClient( client );

        return source;
    }

    abstract CDROpenSearchSource createSource();

}
