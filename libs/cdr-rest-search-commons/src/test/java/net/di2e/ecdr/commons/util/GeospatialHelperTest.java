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

import org.junit.Assert;
import org.junit.Test;

public class GeospatialHelperTest {

    @Test
    public void testPolygonToWKT() {
        String wkt = GeospatialHelper.polygonToWKT( "45.256,-110.45,46.46,-109.48,43.84,-109.86,45.256,-110.45" );
        Assert.assertEquals( "WKT not expected value from polygon", "POLYGON((-110.45 45.256,-109.86 43.84,-109.48 46.46,-110.45 45.256))", wkt );
    }

}
