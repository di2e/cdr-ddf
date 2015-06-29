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
package net.di2e.ecdr.commons.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vividsolutions.jts.io.ParseException;

public class GeospatialHelperTest {

    @Test
    public void testPolygonToWKT() {
        String wkt = GeospatialUtils.polygonToWKT( "45.256,-110.45,46.46,-109.48,43.84,-109.86,45.256,-110.45" );
        assertEquals( "WKT not expected value from polygon", "POLYGON((-110.45 45.256,-109.86 43.84,-109.48 46.46,-110.45 45.256))", wkt );
    }

    @Test
    public void testPolygonToBBox() throws ParseException {
        String wkt = "POLYGON((-1.93359375 24.206889622398023,0.615234375 26.745610382199022,4.21875 26.03704188651584,6.50390625 28.07198030177986,6.328125 23.88583769986199,3.603515625 21.453068633086783,1.23046875 24.046463999666567,-2.8125 22.187404991398775,-1.93359375 24.206889622398023))";
        String bbox = GeospatialUtils.polygonToBBox( wkt );
        assertEquals( "POLYGON ((-2.8125 21.453068633086783, -2.8125 28.07198030177986, 6.50390625 28.07198030177986, 6.50390625 21.453068633086783, -2.8125 21.453068633086783))", bbox );
    }

    @Test
    public void testCircleToBBox() throws ParseException {
        String wkt = GeospatialUtils.circleToBBox( "POINT(1 0)", 111000 );
        System.out.println( wkt );
        assertEquals(
                "POLYGON((0.0017545842884977465 -0.9982454157115022, 1.9982454157115026 -0.9982454157115022, 1.9982454157115026 0.9982454157115022, 0.0017545842884977465 0.9982454157115022, 0.0017545842884977465 -0.9982454157115022))",
                wkt );
    }

}
