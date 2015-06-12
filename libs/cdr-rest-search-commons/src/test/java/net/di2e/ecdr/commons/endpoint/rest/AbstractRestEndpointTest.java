/**
 * Copyright (c) Cohesive Integrations, LLC
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or any later version. 
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details. A copy of the GNU Lesser General Public License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 * 
 **/
package net.di2e.ecdr.commons.endpoint.rest;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import javax.ws.rs.core.MultivaluedMap;

import net.di2e.ecdr.commons.constants.SearchConstants;

import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit tests for BasicQueryParser class
 */
public class AbstractRestEndpointTest {

    @Test
    public void testGetGeoRSSFormat() {

        AbstractRestSearchEndpoint endpoint = Mockito.mock( AbstractRestSearchEndpoint.class, Mockito.CALLS_REAL_METHODS );

        MultivaluedMap<String, String> props = new MetadataMap<String, String>();
        Assert.assertNull( endpoint.getGeoRSSFormat( props ) );
        
        props.putSingle( SearchConstants.GEORSS_RESULT_FORMAT_PARAMETER, null );
        Assert.assertNull( endpoint.getGeoRSSFormat( props ) );

        props.clear();
        props.putSingle( SearchConstants.GEORSS_RESULT_FORMAT_PARAMETER, "" );

        props.clear();
        props.putSingle( SearchConstants.GEORSS_RESULT_FORMAT_PARAMETER, "value" );
        assertEquals( "value", endpoint.getGeoRSSFormat( props ) );
        
        props.clear();
        props.put( SearchConstants.GEORSS_RESULT_FORMAT_PARAMETER, Arrays.asList( "value1", "value2" ) );
        assertEquals( "value1", endpoint.getGeoRSSFormat( props ) );
    }
}
