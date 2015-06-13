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
package net.di2e.ecdr.commons.constants;

import ddf.catalog.data.Metacard;

public final class SearchConstants {
    
    private SearchConstants() {
    }

    public static final String ATOM_RESPONSE_FORMAT = "cdr-atom";

    public static final String NO_QUERY_PARAMETERS_MESSAGE = "The query did not contain any of the required critera, one of the following is required [searchTerms, geospatial, or temporal]";

    // CONSTANTS for supported date types
    public static final String POSTED = "posted";
    public static final String INFO_CUT_OFF = "infoCutOff";
    public static final String VALID_TIL = "validTil";
    public static final String TEMPORAL_COVERAGE = "temporalCoverage";
    
    // CONSTANTS for Atom links
    public static final String NEXT_LINK_REL = "next";
    public static final String PREV_LINK_REL = "previous";
    public static final String SELF_LINK_REL = "self";
    public static final String FIRST_LINK_REL = "first";
    public static final String LAST_LINK_REL = "last";
    public static final String SEARCH_LINK_REL = "search";
    
    public static final String FEED_TITLE = "feed-title";
    
    // TEXTUAL Parameters
    public static final String KEYWORD_PARAMETER = "q";
    public static final String CASESENSITIVE_PARAMETER = "caseSensitive";
    public static final String FUZZY_PARAMETER = "fuzzy";
    
    // GEOSPATIAL Parameters
    public static final String RADIUS_PARAMETER = "radius";
    public static final String LATITUDE_PARAMETER = "lat";
    public static final String LONGITUDE_PARAMETER = "lon";
    public static final String BOX_PARAMETER = "box";
    public static final String GEOMETRY_PARAMETER = "geometry";
    public static final String POLYGON_PARAMETER = "polygon";
    public static final String UID_PARAMETER = "uid";
    public static final String GEO_RELATION_PARAMETER = "spatialOp";
    
    // TEMPORAL Parameters
    public static final String STARTDATE_PARAMETER = "dtStart";
    public static final String ENDDATE_PARAMETER = "dtEnd";
    public static final String DATETYPE_PARAMETER = "dtType";
    public static final String DATE_RELATION_PARAMETER = "dtRelation";
    
    // RELATION Values
    public static final String RELATION_CONTAINS = "contains";
    public static final String RELATION_INTERSECTS = "intersects";
    public static final String RELATION_DISJOINT = "disjoint";
    
    public static final String RELATION_DURING = "during";
    public static final String RELATION_EQUALS = "equals";
    
    public static final String STARTINDEX_PARAMETER = "startIndex";
    public static final String COUNT_PARAMETER = "count";
    public static final String SOURCE_PARAMETER = "source";
    public static final String PATH_PARAMETER = "path";
    public static final String DEDUP_PARAMETER = "dedup";
    
    public static final String SORTKEYS_PARAMETER = "sortKeys";

    public static final String FORMAT_PARAMETER = "format";
    public static final String STRICTMODE_PARAMETER = "strict";
    public static final String QUERYLANGUAGE_PARAMETER = "queryLanguage";
    
    public static final String TIMEOUT_PARAMETER = "timeout";
    public static final String STATUS_PARAMETER = "status";
    public static final String OID_PARAMETER = "oid";
    
    public static final String GEORSS_RESULT_FORMAT_PARAMETER = "georssFormat";
    public static final String GEORSS_SIMPLE_FORMAT = "simple";
    public static final String GEORSS_GML_FORMAT = "gml";

    // Parameter that are part of the Basic Plus query profile

    public static final String TEXTPATH_PARAMETER = "textPath";
    public static final String RESOURCE_URI_PARAMETER = Metacard.RESOURCE_URI;
    public static final String CONTENT_TYPE_PARAMETER = Metacard.CONTENT_TYPE;

    public static final String CONTENT_COLLECTIONS_PARAMETER = "collections";

    public static final String CDR_KEYWORD_QUERY_LANGUAGE = "cdr-kw-basic-2.0";
    public static final String CDR_CQL_QUERY_LANGUAGE = "cql-1.2";

    public static final String LOCAL_SOURCE_ID = "local-source-id";

    public static final String TRUE_STRING = "1";
    public static final String FALSE_STRING = "0";

    // Constants for Strategies
    public static final String TOTAL_HITS = "total-hits";
    public static final String SITE_LIST = "site-list";

    public static final String TOTAL_RESULTS_RETURNED = "total-results-returned";
    public static final String ELAPSED_TIME = "elapsed-time";
    
    public static final String METACARD_TRANSFORMER_NAME = "metacard-transformer-name";

    public static final String DAD_SCHEME = "dad:///";
    public static final String NOT_APPLICABLE = "N/A";

}
