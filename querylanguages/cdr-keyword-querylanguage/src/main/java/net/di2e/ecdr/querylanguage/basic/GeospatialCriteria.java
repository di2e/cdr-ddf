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
package net.di2e.ecdr.querylanguage.basic;

public class GeospatialCriteria {
    
    public enum SpatialOperator {
        Contains, Overlaps, Disjoint, Within
    }

    private SpatialOperator spatialOp = null;

    private Double radius;
    private Double longitude;
    private Double latitude;
    private String geometryWKT;

    private boolean isBBox = false;
    private boolean isPointRadius = false;

    public GeospatialCriteria( double lat, double lon, double rad ) throws IllegalArgumentException{
        if ( rad < 0 || ( lon < -180 || lon > 180 ) || ( lat < -90 || lat > 90 ) ) {
            throw new IllegalArgumentException( "Invalid value for one of lat [" + lat + "], lon [" + lon + "], and/or radius [" + rad + "]" );
        }
        this.radius = rad;
        this.longitude = lon;
        this.latitude = lat;
        spatialOp = SpatialOperator.Overlaps;
        isPointRadius = true;
    }

    public GeospatialCriteria( double minX, double minY, double maxX, double maxY, SpatialOperator operator ) {
        if ( operator == null ) {
            throw new IllegalArgumentException( "SpatialOperator cannot be null when creating GeospatialCriteria" );
        }
        spatialOp = operator;
        StringBuilder wktBuilder = new StringBuilder( "POLYGON((" );
        wktBuilder.append( minX + " " + minY );
        wktBuilder.append( "," + minX + " " + maxY );
        wktBuilder.append( "," + maxX + " " + maxY );
        wktBuilder.append( "," + maxX + " " + minY );
        wktBuilder.append( "," + minX + " " + minY );
        wktBuilder.append( "))" );
        geometryWKT = wktBuilder.toString();
        isBBox = true;
        
    }

    public GeospatialCriteria( double minX, double minY, double maxX, double maxY ) {
        this( minX, minY, maxX, maxY, SpatialOperator.Overlaps );
    }

    public GeospatialCriteria( String wkt, SpatialOperator operator ) {
        if ( operator == null ) {
            throw new IllegalArgumentException( "SpatialOperator cannot be null when creating GeospatialCriteria" );
        }
        spatialOp = operator;
        this.geometryWKT = wkt;
    }

    public GeospatialCriteria( String wkt ) {
        this( wkt, SpatialOperator.Overlaps );
    }

    public Double getRadius() {
        return radius;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getGeometryWKT() {
        return geometryWKT;
    }

    public boolean isBBox() {
        return isBBox;
    }

    public boolean isPointRadius() {
        return isPointRadius;
    }

    public SpatialOperator getSpatialOperator() {
        return spatialOp == null ? SpatialOperator.Overlaps : spatialOp;
    }

    public void setSpatialOperator( SpatialOperator operator ) {
        spatialOp = operator;
    }

}
