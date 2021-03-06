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
package net.di2e.ecdr.search.transform.atom.response;

public class AtomTest {

    protected static final String LOCATION_MARKER = "$LOCATION";

    // test files
    protected static final String ATOM_TEMPLATE_FILE = "/atom-template.xml";
    protected static final String ATOM_INVALID_FILE = "/atom-invalid.xml";

    // WKT shapes
    public static final String POINT_WKT = "POINT (-77.0366 38.8977)";
    public static final String BOX_WKT = "POLYGON ((-71.032 42.943, -69.856 42.943, -69.856 43.039, -71.032 43.039, -71.032 42.943))";
    public static final String LINE_WKT = "LINESTRING (-110.45 45.256, -109.48 46.46, -109.86 43.84)";
    public static final String POLYGON_WKT = "POLYGON ((-110.45 45.256, -109.48 46.46, -109.86 43.84, -110.45 45.256))";

    public static final String MULTIPOINT_WKT = "MULTIPOINT ((-66.03 48.89), (-77.03 38.89))";
    public static final String MULTILINESTRING_WKT = "MULTILINESTRING ((40 40, 30 30, 40 20, 30 10), (10 10, 20 20, 10 40))";
    public static final String MULTIPOLYGON_WKT = "MULTIPOLYGON (((30 20, 45 40, 10 40, 30 20)), ((15 5, 40 10, 10 20, 5 10, 15 5)))";
    public static final String MULTIBOX_WKT = "MULTIPOLYGON (((10 5, 20 5, 20 10, 10 10, 10 5)), ((-71.032 42.943, -69.856 42.943, -69.856 43.039, -71.032 43.039, -71.032 42.943)))";
    public static final String GEOMETRYCOLLECTION_WKT = "GEOMETRYCOLLECTION (LINESTRING (-110.45 45.256, -109.48 46.46, -109.86 43.84), "
            + "POLYGON ((-71.032 42.943, -69.856 42.943, -69.856 43.039, -71.032 43.039, -71.032 42.943)), POINT (-77.0366 38.8977), "
            + "POLYGON ((-110.45 45.256, -109.48 46.46, -109.86 43.84, -110.45 45.256)))";

    // XML shapes
    public static final String SIMPLE_BOX = "<georss:box xmlns:georss=\"http://www.georss.org/georss\">42.943 -71.032 43.039 -69.856</georss:box>";
    public static final String SIMPLE_POINT = "<georss:point xmlns:georss=\"http://www.georss.org/georss\">38.8977 -77.0366</georss:point>";
    public static final String SIMPLE_POLYGON = "<georss:polygon xmlns:georss=\"http://www.georss.org/georss\">45.256 -110.45 46.46 -109.48 43.84 -109.86 45.256 -110.45</georss:polygon>";
    public static final String SIMPLE_LINE = "<georss:line xmlns:georss=\"http://www.georss.org/georss\">45.256 -110.45 46.46 -109.48 43.84 -109.86</georss:line>";

    public static final String GML_BOX = "<georss:where xmlns:georss=\"http://www.georss.org/georss\" xmlns:gml=\"http://www.opengis.net/gml\"><gml:Envelope><gml:lowerCorner>42.943 -71.032</gml:lowerCorner><gml:upperCorner>43.039 -69.856</gml:upperCorner></gml:Envelope></georss:where>";
    public static final String GML_POINT = "<georss:where xmlns:georss=\"http://www.georss.org/georss\" xmlns:gml=\"http://www.opengis.net/gml\"><gml:Point><gml:pos>38.8977 -77.0366</gml:pos></gml:Point></georss:where>";
    public static final String GML_LINE = "<georss:where xmlns:georss=\"http://www.georss.org/georss\" xmlns:gml=\"http://www.opengis.net/gml\"><gml:LineString><gml:posList>45.256 -110.45 46.46 -109.48 43.84 -109.86</gml:posList></gml:LineString></georss:where>";
    public static final String GML_POLYGON = "<georss:where xmlns:georss=\"http://www.georss.org/georss\" xmlns:gml=\"http://www.opengis.net/gml\"><gml:Polygon><gml:exterior><gml:LinearRing><gml:posList>45.256 -110.45 46.46 -109.48 43.84 -109.86 45.256 -110.45</gml:posList></gml:LinearRing></gml:exterior></gml:Polygon></georss:where>";

    public static final String MULTIPOINT = "<georss:where xmlns:georss=\"http://www.georss.org/georss\" xmlns:gml=\"http://www.opengis.net/gml\"><gml:Point><gml:pos>38.89 -77.03</gml:pos></gml:Point></georss:where>"
            + "<georss:point xmlns:georss=\"http://www.georss.org/georss\">48.89 -66.03</georss:point>";
    public static final String MULTILINESTRING = "<georss:where xmlns:georss=\"http://www.georss.org/georss\" xmlns:gml=\"http://www.opengis.net/gml\"><gml:LineString><gml:posList>10 10 20 20 40 10</gml:posList></gml:LineString></georss:where>"
            + "<georss:line xmlns:georss=\"http://www.georss.org/georss\">40 40 30 30 20 40 10 30</georss:line>";
    public static final String MULTIPOLYGON = "<georss:where xmlns:georss=\"http://www.georss.org/georss\" xmlns:gml=\"http://www.opengis.net/gml\"><gml:Polygon><gml:exterior><gml:LinearRing><gml:posList>5 15 10 40 20 10 10 5 5 15</gml:posList></gml:LinearRing></gml:exterior></gml:Polygon></georss:where>"
            + "<georss:polygon xmlns:georss=\"http://www.georss.org/georss\">20 30 40 45 40 10 20 30</georss:polygon>";
    public static final String MULTIBOX = "<georss:where xmlns:georss=\"http://www.georss.org/georss\" xmlns:gml=\"http://www.opengis.net/gml\"><gml:Envelope><gml:lowerCorner>42.943 -71.032</gml:lowerCorner><gml:upperCorner>43.039 -69.856</gml:upperCorner></gml:Envelope></georss:where>"
            + "<georss:box xmlns:georss=\"http://www.georss.org/georss\">5 10 10 20</georss:box>";
    public static final String GEOMETRYCOLLECTION = "<georss:where xmlns:georss=\"http://www.georss.org/georss\" xmlns:gml=\"http://www.opengis.net/gml\"><gml:Point><gml:pos>38.8977 -77.0366</gml:pos></gml:Point></georss:where>"
            + "<georss:line xmlns:georss=\"http://www.georss.org/georss\">45.256 -110.45 46.46 -109.48 43.84 -109.86</georss:line>"
            + "<georss:box xmlns:georss=\"http://www.georss.org/georss\">42.943 -71.032 43.039 -69.856</georss:box>"
            + "<georss:where xmlns:georss=\"http://www.georss.org/georss\" xmlns:gml=\"http://www.opengis.net/gml\"><gml:Polygon><gml:exterior><gml:LinearRing><gml:posList>45.256 -110.45 46.46 -109.48 43.84 -109.86 45.256 -110.45</gml:posList></gml:LinearRing></gml:exterior></gml:Polygon></georss:where>";
    protected static final String SITE_NAME = "ddf.distribution";
}
