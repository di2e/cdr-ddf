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
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.geometry.BoundingBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;

import java.util.List;

/**
 * Utility Class for geospatial related functions
 */
public final class GeospatialUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger( GeospatialUtils.class );
    private static final Integer MAX_LAT = 90;
    private static final Integer MIN_LAT = -90;
    private static final Integer MAX_LON = 180;
    private static final Integer MIN_LON = -180;
    private static final Integer MAX_ROTATION = 360;
    private static final double LAT_DEGREE_M = 111325;

    private GeospatialUtils() {
    }

    /**
     * Converts from a opensearch geo extension polygon which is defined in latitude, longitude pairs, in clockwise
     * order around the polygon, with the last point being the same as the first in order to close the polygon.
     * 
     * @param polygon
     * @return WKT String representation of the polygon
     */
    public static String polygonToWKT( String polygon ) {
        StringBuilder wkt = new StringBuilder( "POLYGON((" );
        int coordinatePair = 0;
        String[] coords = polygon.split( "," );
        int size = coords.length;
        LOGGER.trace( "Trying to convert polygon with value [{}] to WKT", polygon );
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
        LOGGER.trace( "Converted polygon with value [{}] to WKT [{}]", polygon, wkt );
        return wkt.toString();
    }

    public static String polygonToBBox( String wkt ) throws ParseException {
        LOGGER.trace( "Trying to convert the wkt [{}] into a bounding box", wkt );
        WKTReader reader = new WKTReader();
        Geometry geo = reader.read( wkt );
        if ( !geo.isRectangle() ) {
            geo = geo.getEnvelope();
            WKTWriter writer = new WKTWriter();
            String bbox = writer.write( geo );
            LOGGER.debug( "Convert the following wkt [{}] into a bounding box wkt [{}]", wkt, bbox );
            wkt = bbox;
        }
        return wkt;
    }

    public static String circleToBBox( String wktPoint, double radius ) throws ParseException {
        WKTReader reader = new WKTReader();
        Geometry geo = reader.read( wktPoint );
        Point point = geo.getCentroid();
        GeoLocation geoLocation = GeoLocation.fromDegrees( point.getY(), point.getX() );
        GeoLocation[] boxLocs = geoLocation.boundingCoordinates( radius );
        return "POLYGON((" + boxLocs[0].getLongitudeInDegrees() + " " + boxLocs[0].getLatitudeInDegrees() + ", "
                           + boxLocs[1].getLongitudeInDegrees() + " " + boxLocs[0].getLatitudeInDegrees() + ", "
                           + boxLocs[1].getLongitudeInDegrees() + " " + boxLocs[1].getLatitudeInDegrees() + ", " 
                           + boxLocs[0].getLongitudeInDegrees() + " " + boxLocs[1].getLatitudeInDegrees() + ", " 
                           + boxLocs[0].getLongitudeInDegrees() + " " + boxLocs[0].getLatitudeInDegrees() + "))";
    }

    /**
     * Converts a point to a WKT-based string.
     *
     * @param point Point to convert to WKT.
     * @return WKT String.
     */
    public static String pointToWKT(org.opengis.geometry.primitive.Point point) {
        return JTS.toGeometry(point.getDirectPosition()).toText();
    }

    /**
     * Converts a boundingbox to a WKT-based string.
     *
     * @param bbox BoundingBox to convert to WKT.
     * @return WKT String.
     */
    public static String bboxToWKT(BoundingBox bbox) {
        return JTS.toGeometry(bbox).toText();
    }

    /**
     * Converts a list of points into a geotools-based bounding box.
     *
     * @param points List of points, should only include the 'upper-left' and 'lower-right' points in that order.
     *               Code will not look at any other points that are passed in.
     * @return BoundingBox if transformation works, null otherwise.
     */
    public static BoundingBox pointsToBBox(List<org.opengis.geometry.primitive.Point> points) {
        BoundingBox bbox = null;
        if (!points.isEmpty()) {
            org.opengis.geometry.primitive.Point point1 = points.get( 0 );
            org.opengis.geometry.primitive.Point point2 = points.get( 1 );
            bbox = new ReferencedEnvelope(point1.getDirectPosition().getCoordinate()[0],
                    point2.getDirectPosition().getCoordinate()[0], point1.getDirectPosition().getCoordinate()[1],
                    point2.getDirectPosition().getCoordinate()[1], DefaultGeographicCRS.WGS84 );
        }
        return bbox;
    }


}