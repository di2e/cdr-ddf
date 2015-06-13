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

import org.apache.commons.lang.StringUtils;

public final class GeospatialHelper {

    private GeospatialHelper() {
    }

    public static String polygonToWKT( String polygon ) {
        StringBuilder wkt = new StringBuilder( "POLYGON((" );
        int coordinatePair = 0;
        String[] coords = polygon.split( "," );
        int size = coords.length;
        for ( int i = size - 1; i >= 0; i-- ) {
            coordinatePair++;
            if ( coordinatePair == 2 ) {
                wkt.append( " " );
            } else if ( coordinatePair > 2 ) {
                wkt.append( "," );
                coordinatePair = 1;
            }
            wkt.append( StringUtils.trimToEmpty( coords[i] ) );
        }
        wkt.append( "))" );
        return wkt.toString();
    }

}
