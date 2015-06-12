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
package net.di2e.ecdr.querylanguage.basic;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

import net.di2e.ecdr.api.query.QueryConfiguration;
import net.di2e.ecdr.api.query.QueryCriteria;
import net.di2e.ecdr.api.query.QueryLanguage;
import net.di2e.ecdr.commons.CDRMetacard;
import net.di2e.ecdr.commons.constants.SearchConstants;
import net.di2e.ecdr.commons.query.CDRQueryCriteriaImpl;
import net.di2e.ecdr.commons.sort.SortTypeConfiguration;
import net.di2e.ecdr.commons.util.GeospatialHelper;
import net.di2e.ecdr.commons.util.SearchUtils;
import net.di2e.ecdr.querylanguage.basic.GeospatialCriteria.SpatialOperator;
import net.di2e.ecdr.querylanguage.basic.PropertyCriteria.Operator;
import net.di2e.ecdr.querylanguage.basic.keywordparser.ASTNode;
import net.di2e.ecdr.querylanguage.basic.keywordparser.KeywordTextParser;

import org.apache.commons.lang.StringUtils;
import org.opengis.filter.Filter;
import org.opengis.filter.sort.SortBy;
import org.opengis.filter.sort.SortOrder;
import org.parboiled.Parboiled;
import org.parboiled.parserunners.ParseRunner;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;

import ddf.catalog.data.Metacard;
import ddf.catalog.data.Result;
import ddf.catalog.filter.FilterBuilder;
import ddf.catalog.filter.impl.SortByImpl;
import ddf.catalog.source.UnsupportedQueryException;

public class CDRKeywordQueryLanguage implements QueryLanguage {

    private static final Logger LOGGER = LoggerFactory.getLogger( CDRKeywordQueryLanguage.class );
    private FilterBuilder filterBuilder = null;
    private List<SortTypeConfiguration> sortTypeConfigurationList = null;

    private static final Map<String, String> DATETYPE_MAP = new HashMap<String, String>();

    static {
        DATETYPE_MAP.put( "created", Metacard.CREATED );
        DATETYPE_MAP.put( "updated", Metacard.MODIFIED );
        DATETYPE_MAP.put( "posted", SearchConstants.POSTED );
        DATETYPE_MAP.put( "infoCutOff", SearchConstants.INFO_CUT_OFF );
        DATETYPE_MAP.put( "validTil", SearchConstants.VALID_TIL );
        DATETYPE_MAP.put( "temporalCoverage", SearchConstants.TEMPORAL_COVERAGE );
        DATETYPE_MAP.put( "effective", Metacard.EFFECTIVE );

    }

    public CDRKeywordQueryLanguage( FilterBuilder builder, List<SortTypeConfiguration> sortTypeConfigurations ) {
        filterBuilder = builder;
        sortTypeConfigurationList = sortTypeConfigurations;
    }

    @Override
    public String getName() {
        return SearchConstants.CDR_KEYWORD_QUERY_LANGUAGE;
    }

    @Override
    public String getLanguageDescription( QueryConfiguration queryConfig ) {
        // @formatter:off
        String description =  "CDR Keyword Basic Query Language" + System.lineSeparator()
               + "****************************" + System.lineSeparator()
               + "Usage: To use the CQL query language specify the '" + getName() + "' in the {cdrs:queryLanguage} parameter placeholder." + System.lineSeparator()
               + "       The CDR Keyword Basic query language supports booleans (AND, OR, NOT) and parenthesis in the {os:searchTerms} parameter value" + System.lineSeparator()
               + "       Additionally the parameters below can be used for temporal, geospatial, property, or enhanced keyword searches" + System.lineSeparator()
               + System.lineSeparator()
               + "The examples below are only for the keywords that can be used in the {os:searchTerms}.  They can be combined with any of the "
               + "additional parameters defined in the sections that follow.       " + System.lineSeparator()
               + "Examples:  ballpark" + System.lineSeparator()
               + "           ballpark AND goodyear" + System.lineSeparator()
               + "           bacllpark AND (goodyear or peoria)" + System.lineSeparator()
               + "           " + System.lineSeparator()
               + "           " + System.lineSeparator()
               + "**** ID/URI Search Parameters ****" + System.lineSeparator()
               + System.lineSeparator()
               + "geo:uid - unique identifier of the record, matches the Metacard.ID field" + System.lineSeparator()
               + System.lineSeparator()
               + "ddf:resource-uri - URL encoded resource URI value that will be directly matched on, matches the Metacard.RESOURCE_URI field" + System.lineSeparator()
               + System.lineSeparator()
               + System.lineSeparator()
               + "**** Contextual Search Parameters ****" + System.lineSeparator()
               + System.lineSeparator()
               + "cdrsx:caseSensitive - boolean (1 or 0) specifying whether or not the keyword search should be case sensitive" + System.lineSeparator()
               + "            default: 0 (false - case insensitive) " + System.lineSeparator()
               + System.lineSeparator()
               + "ecdr:fuzzy - boolean (1 or 0) specifying whether or not the keyword search should be fuzzy (fuzzy allows for slight misspellings or derivations to be found)" + System.lineSeparator()
               + "            default: ${defaultFuzzyCustom} (${defaultFuzzy}) " + System.lineSeparator()
               + System.lineSeparator()
               + System.lineSeparator()
               + "**** Geospatial Search Parameters ****" + System.lineSeparator()
               + System.lineSeparator()
               + "geo:box - comma delimited list of lat/lon (deg) bounding box coordinates (geo format: geo:bbox ~ west,south,east,north). "
               + "This is also commonly referred to by minX, minY, maxX, maxY (where longitude is the X-axis, and latitude is the Y-axis)." + System.lineSeparator()
               + System.lineSeparator()
               + "geo:lat/lon - latitude and longitude, respectively, in decimal degrees (typical GPS receiver WGS84 coordinates). Should include a 'radius' parameter "
               + "that specifies the search radius in meters." + System.lineSeparator()
               + System.lineSeparator()
               + "geo:radius - the radius (in meters) parameter, used with the lat and lon parameters, specifies the search distance from this point." + System.lineSeparator()
               + "            default: ${defaultRadius}" + System.lineSeparator()
               + System.lineSeparator()
               + "geo:geometry - The geometry is defined using the Well Known Text and supports the following 2D geographic shapes: POINT, LINESTRING, POLYGON, MULTIPOINT, "
               + "MULTILINESTRING, MULTIPOLYGON (the Geometry shall be expressed using the EPSG:4326e)" + System.lineSeparator()
               + "            examples: POINT(1 5)" + System.lineSeparator()
               + "                      POLYGON((1 1,5 1,5 5,1 5,1 1),(2 2,2 3,3 3,3 2,2 2))" + System.lineSeparator()
               + System.lineSeparator()
               + "geo:polygon - (deprecated) polygon defined as comma separated latitude, longitude pairs, in clockwise order, with the last point being the same as the first "
               + "in order to close the polygon." + System.lineSeparator()
               + "            example: 45.256,-110.45,46.46,-109.48,43.84,-109.86,45.256,-110.45" + System.lineSeparator()
               + System.lineSeparator()
               + "geo:relation - spatial operator for the relation to the result set " + System.lineSeparator()
               + "            default: intersects" + System.lineSeparator()
               + "            allowedValues: 'intersects', 'contains', 'disjoint'" + System.lineSeparator()
               + System.lineSeparator()
               + "**** Temporal Search Parameters ****" + System.lineSeparator()
               + System.lineSeparator()
               + "time:start - replaced with a string of the beginning of the time slice of the search (RFC-3339 - Date and Time format, i.e. YYYY-MM-DDTHH:mm:ssZ). "
               + "Default value of \"1970-01-01T00:00:00Z\" is used when {time:end} is indicated but {time:start} is not specified." + System.lineSeparator()
               + System.lineSeparator()
               + "time:end - replaced with a string of the ending of the time slice of the search (RFC-3339 - Date and Time format, i.e. YYYY-MM-DDTHH:mm:ssZ). "
               + "Current GMT date/time is used when {time:start} is specified but not {time:end}." + System.lineSeparator()
               + System.lineSeparator()
               + "time:relation - temporal operation for the relation to the result set" + System.lineSeparator()
               + "            default: intersects" + System.lineSeparator()
               + "            allowedValues: 'intersects', 'contains', 'during', 'disjoint', 'equals'" + System.lineSeparator()
               + System.lineSeparator()
               + "cdrsx:dateType - the date type to compare against" + System.lineSeparator()
               + "            default: ${defaultDateType}" + System.lineSeparator()
               + "            allowedValues: ${dateTypeValues}" + System.lineSeparator()
               + System.lineSeparator()
               + System.lineSeparator()
               + "**** Content Collections Search Parameters ****" + System.lineSeparator()
               + System.lineSeparator()
               + "ecdr:collections - a comma separated list of content collections to search over.  list of content collections can be retrieved by using the Describe spec" + System.lineSeparator()
               + System.lineSeparator()
               + System.lineSeparator()
               + "**** Other Parameters ****" + System.lineSeparator()
               + System.lineSeparator()
               + "ecdr:georssFormat - specifies how to return the results that include geospatial data, can be as GML or as Simple GeoRSS" + System.lineSeparator()
               + "            allowedValues: 'simple', 'gml'" + System.lineSeparator()
               + System.lineSeparator()
               + "ddf:metadata-content-type - comma separate list that maps to the Metacard.CONTENT_TYPE attribute" + System.lineSeparator()
               + System.lineSeparator()
               + "ddf:textPath - comma separated list of text paths (XPath-like) values to be searched over" + System.lineSeparator()
               + "            example: /ddms:Resource/subtitle  (this would return all records that contain an element of subtitle under the ddms:Resource root element" + System.lineSeparator()
               + System.lineSeparator()
               + System.lineSeparator()
               + "**** Sort Order ****" + System.lineSeparator()
               + System.lineSeparator()
               + "sru:sortKeys - space-separated list of sort keys, with individual sort keys comprised of a comma-separated sequence of "
               + "sub-parameters in the order listed below." + System.lineSeparator()
               + "    path - Mandatory. An XPath expression for a tagpath to be used in the sort." + System.lineSeparator()
               + "    sortSchema - Optional. A short name for a URI identifying an XML schema to which the XPath expression applies" + System.lineSeparator()
               + "    ascending - Optional. Boolean, default 'true'." + System.lineSeparator()
               + "    caseSensitive - Optional. Boolean, default 'false'." + System.lineSeparator()
               + "    missingValue - Optional. Default is 'highValue'." + System.lineSeparator()
               + "            examples: Sort by relevance - score,relevance" + System.lineSeparator()
               + "                      Sort by updated time descending - entry/date,,false " + System.lineSeparator()
               + "                      Sort by distance - distance,cdrsx" + System.lineSeparator();
        // @formatter:on
        boolean fuzzy = queryConfig.isDefaultFuzzySearch();
        description = StringUtils.replace( description, "${defaultFuzzy}", String.valueOf( fuzzy ), 1 );
        description = StringUtils.replace( description, "${defaultFuzzyCustom}", fuzzy ? SearchConstants.TRUE_STRING : SearchConstants.FALSE_STRING, 1 );
        description = StringUtils.replace( description, "${defaultRadius}", String.valueOf( queryConfig.getDefaultRadius() ), 1 );
        description = StringUtils.replace( description, "${defaultDateType}", queryConfig.getDefaultDateType(), 1 );
        description = StringUtils.replace( description, "${dateTypeValues}", DATETYPE_MAP.keySet().toString(), 1 );
        return description;
    }

    @Override
    public boolean isValidQuery( MultivaluedMap<String, String> queryParameters ) {
        return true;
    }

    @Override
    public QueryCriteria getQueryCriteria( MultivaluedMap<String, String> queryParameters, QueryConfiguration queryConfig ) throws UnsupportedQueryException {
        LOGGER.debug( "Parsing query using the CDRKeywordQueryLanguage" );
        List<Filter> filters = new ArrayList<Filter>();

        SortBy sortBy = SearchUtils.getSortBy( queryParameters, sortTypeConfigurationList );
        StringBuilder humanReadableQuery = new StringBuilder();

        boolean defaultFuzzySearch = queryConfig.isDefaultFuzzySearch();
        double defaultRadius = queryConfig.getDefaultRadius();
        String defaultDateType = queryConfig.getDefaultDateType();

        // keyword parameters
        TextualCriteria textualCriteria = getTextualCriteria( queryParameters, defaultFuzzySearch );
        if ( textualCriteria != null ) {
            boolean fuzzy = textualCriteria.isFuzzy();
            LOGGER.debug( "Attempting to create a Contextual filter with params keywords=[{}], isCaseSensitive=[{}], fuzzy=[{}]", textualCriteria.getKeywords(), textualCriteria.isCaseSensitive(),
                    fuzzy );
            Filter filter = getContextualFilter( textualCriteria.getKeywords(), textualCriteria.isCaseSensitive(), fuzzy, humanReadableQuery );
            SearchUtils.addFilter( filters, filter );
            if ( sortBy == null ) {
                sortBy = new SortByImpl( Result.RELEVANCE, SortOrder.DESCENDING );
            }
        }

        // Geospatial query parameters
        GeospatialCriteria geoCriteria = createGeospatialCriteria( queryParameters.getFirst( SearchConstants.RADIUS_PARAMETER ), queryParameters.getFirst( SearchConstants.LATITUDE_PARAMETER ),
                queryParameters.getFirst( SearchConstants.LONGITUDE_PARAMETER ), queryParameters.getFirst( SearchConstants.BOX_PARAMETER ),
                queryParameters.getFirst( SearchConstants.GEOMETRY_PARAMETER ), queryParameters.getFirst( SearchConstants.POLYGON_PARAMETER ),
                queryParameters.getFirst( SearchConstants.GEO_RELATION_PARAMETER ), defaultRadius );
        if ( geoCriteria != null ) {
            LOGGER.debug( "Attempting to create a Geospatial filter with params radius=[{}], latitude=[{}], longitude=[{}], geometry=[{}]", geoCriteria.getRadius(), geoCriteria.getLatitude(),
                    geoCriteria.getLongitude(), geoCriteria.getGeometryWKT() );
            Filter filter = getGeoFilter( geoCriteria.getRadius(), geoCriteria.getLatitude(), geoCriteria.getLongitude(), geoCriteria.isBBox(), geoCriteria.getGeometryWKT(),
                    geoCriteria.getSpatialOperator(), humanReadableQuery );
            SearchUtils.addFilter( filters, filter );
            if ( sortBy == null ) {
                sortBy = new SortByImpl( Result.DISTANCE, SortOrder.ASCENDING );
            }
        }

        // Temporal Criteria
        TemporalCriteria temporalCriteria = createTemporalCriteria( queryParameters.getFirst( SearchConstants.STARTDATE_PARAMETER ), queryParameters.getFirst( SearchConstants.ENDDATE_PARAMETER ),
                queryParameters.getFirst( SearchConstants.DATETYPE_PARAMETER ), humanReadableQuery, defaultDateType );
        if ( temporalCriteria != null ) {
            LOGGER.debug( "Attempting to create a Temporal filter with params startDate=[{}], endDate=[{}], dateType=[{}]", temporalCriteria.getStartDate(), temporalCriteria.getEndDate(),
                    temporalCriteria.getDateType() );
            Filter filter = getTemporalFilter( temporalCriteria.getStartDate(), temporalCriteria.getEndDate(), temporalCriteria.getDateType(), humanReadableQuery );
            SearchUtils.addFilter( filters, filter );
        }

        // Property Criteria
        List<PropertyCriteria> propertyCriteriaList = getPropertyCriteria( queryParameters, queryConfig.getParameterExtensionMap() );
        if ( propertyCriteriaList != null && !propertyCriteriaList.isEmpty() ) {
            for ( PropertyCriteria propCriteria : propertyCriteriaList ) {
                LOGGER.debug( "Attempting to create a Property filter with params property=[{}], value=[{}], operator=[{}]", propCriteria.getProperty(), propCriteria.getValue(),
                        propCriteria.getOperator() );
                Filter filter = getPropertyFilter( propCriteria.getProperty(), propCriteria.getValue(), propCriteria.getOperator(), humanReadableQuery );
                SearchUtils.addFilter( filters, filter );
            }
        }

        if ( filters.isEmpty() ) {
            throw new UnsupportedQueryException( "There was no valid search criteria presented from the user, cannot complete search" );
        }

        return new CDRQueryCriteriaImpl( filterBuilder.allOf( filters ), sortBy, humanReadableQuery.toString(), true, queryParameters, new HashMap<String, String>(), queryConfig );
    }

    protected TextualCriteria getTextualCriteria( MultivaluedMap<String, String> queryParameters, boolean defaultFuzzySearch ) throws UnsupportedQueryException {
        String words = queryParameters.getFirst( SearchConstants.KEYWORD_PARAMETER );

        TextualCriteria textualCriteria = null;
        if ( StringUtils.isNotBlank( words ) ) {
            String stringFuzzy = queryParameters.getFirst( SearchConstants.FUZZY_PARAMETER );
            LOGGER.debug( "Attempting to set 'fuzzy' value from request [{}]", stringFuzzy );
            Boolean fuzzy = SearchUtils.getBoolean( stringFuzzy, null );
            if ( fuzzy == null ) {
                LOGGER.debug( "The 'fuzzy' parameter was not specified, defaulting value to [{}]", defaultFuzzySearch );
                fuzzy = defaultFuzzySearch;
            }

            String caseSensitiveString = queryParameters.getFirst( SearchConstants.CASESENSITIVE_PARAMETER );
            LOGGER.debug( "Attempting to set '{}' value from request [{}], will default to false if not boolean", SearchConstants.CASESENSITIVE_PARAMETER, caseSensitiveString );

            textualCriteria = new TextualCriteria( words, SearchUtils.getBoolean( caseSensitiveString, Boolean.FALSE ), fuzzy );
        }
        return textualCriteria;
    }

    protected Filter getContextualFilter( String keywords, boolean caseSensitive, boolean fuzzy, StringBuilder humanReadableQuery ) throws UnsupportedQueryException {
        Filter filter = null;
        if ( keywords != null ) {
            KeywordTextParser keywordParser = Parboiled.createParser( KeywordTextParser.class );
            ParseRunner<ASTNode> runner = new RecoveringParseRunner<ASTNode>( keywordParser.inputPhrase() );
            ParsingResult<ASTNode> parsingResult = runner.run( keywords );

            if ( !parsingResult.hasErrors() ) {
                try {
                    filter = getFilterFromASTNode( parsingResult.resultValue, caseSensitive, fuzzy );
                } catch ( IllegalStateException e ) {
                    throw new UnsupportedQueryException( "searchTerms parameter [" + keywords + "] was invalid and resulted in the error: " + e.getMessage() );
                }
            } else {
                throw new UnsupportedQueryException( "searchTerms parameter [" + keywords + "] was invalid and resulted in the error: " + parsingResult.parseErrors.get( 0 ).getErrorMessage() );
            }
            humanReadableQuery.append( " " + SearchConstants.KEYWORD_PARAMETER + "=[" + keywords + "] " + SearchConstants.CASESENSITIVE_PARAMETER + "=[" + caseSensitive + "] "
                    + SearchConstants.FUZZY_PARAMETER + "=[" + fuzzy + "]" );
        }
        return filter;
    }

    protected Filter getFilterFromASTNode( ASTNode astNode, boolean caseSensitive, boolean fuzzy ) {

        if ( astNode.isKeyword() ) {
            String keyword = astNode.getKeyword();
            // this means it is an Text Path
            if ( keyword.startsWith( "{" ) && keyword.contains( "}:" ) ) {
                int endXpath = keyword.lastIndexOf( "}:" );
                String xpath = keyword.substring( 1, endXpath );
                String literal = keyword.substring( endXpath + 2 );
                if ( literal.trim().isEmpty() ) {
                    return filterBuilder.xpath( xpath ).exists();
                } else {
                    if ( fuzzy ) {
                        return filterBuilder.xpath( xpath ).like().fuzzyText( literal );
                    } else if ( caseSensitive ) {
                        return filterBuilder.xpath( xpath ).like().caseSensitiveText( literal );
                    } else {
                        return filterBuilder.xpath( xpath ).like().text( literal );
                    }
                }
            } else {
                if ( fuzzy ) {
                    return filterBuilder.attribute( Metacard.ANY_TEXT ).like().fuzzyText( astNode.getKeyword() );
                } else if ( caseSensitive ) {
                    return filterBuilder.attribute( Metacard.ANY_TEXT ).like().caseSensitiveText( astNode.getKeyword() );
                } else {
                    return filterBuilder.attribute( Metacard.ANY_TEXT ).like().text( astNode.getKeyword() );
                }
            }
        } else if ( astNode.isOperator() ) {
            switch ( astNode.getOperator() ) {
            case AND:
                return filterBuilder.allOf( getFilterFromASTNode( astNode.left(), caseSensitive, fuzzy ), getFilterFromASTNode( astNode.right(), caseSensitive, fuzzy ) );
            case OR:

                return filterBuilder.anyOf( getFilterFromASTNode( astNode.left(), caseSensitive, fuzzy ), getFilterFromASTNode( astNode.right(), caseSensitive, fuzzy ) );
            case NOT: // since NOT really means AND NOT
                return filterBuilder.allOf( getFilterFromASTNode( astNode.left(), caseSensitive, fuzzy ), filterBuilder.not( getFilterFromASTNode( astNode.right(), caseSensitive, fuzzy ) ) );
            default:
                throw new IllegalStateException( "Unable to generate Filter from invalid OperatorASTNode." );
            }
        }

        throw new IllegalStateException( "Unable to generate Filter from ASTNode. Found invalid ASTNode in the tree" );
    }

    protected GeospatialCriteria createGeospatialCriteria( String rad, String lat, String lon, String box, String geom, String polygon, String geoRelation, double defaultRadius )
            throws UnsupportedQueryException {
        GeospatialCriteria geoCriteria = null;
        if ( StringUtils.isNotBlank( box ) ) {
            try {
                String[] bboxArray = box.split( " |,\\p{Space}?" );

                if ( bboxArray.length != 3 ) {
                    double minX = Double.parseDouble( bboxArray[0] );
                    double minY = Double.parseDouble( bboxArray[1] );
                    double maxX = Double.parseDouble( bboxArray[2] );
                    double maxY = Double.parseDouble( bboxArray[3] );
                    geoCriteria = new GeospatialCriteria( minX, minY, maxX, maxY );
                } else {
                    throw new UnsupportedQueryException( "Invalid values found for bbox [" + box + "]" );
                }

            } catch ( NumberFormatException e ) {
                LOGGER.warn( "Invalid values found for bbox [{}].  Resulted in exception: {}", box, e.getMessage() );
                throw new UnsupportedQueryException( "Invalid values found for bbox [" + box + "], values must be numeric." );
            }
            // Only check lat and lon. If Radius is blank is should be defaulted
        } else if ( StringUtils.isNotBlank( lat ) && StringUtils.isNotBlank( lon ) ) {
            try {
                double longitude = Double.parseDouble( lon );
                double latitude = Double.parseDouble( lat );
                double radius = StringUtils.isNotBlank( rad ) ? Double.parseDouble( rad ) : defaultRadius;
                geoCriteria = new GeospatialCriteria( latitude, longitude, radius );

            } catch ( NumberFormatException e ) {
                LOGGER.warn( "Invalid Number found for lat [{}], lon [{}], and/or radius [{}].  Resulted in exception: {}", lat, lon, rad, e.getMessage() );
                throw new UnsupportedQueryException( "Invalid Number found for lat [" + lat + "], lon [" + lon + "], and/or radius [" + rad + "]." );
            }

        } else if ( StringUtils.isNotBlank( geom ) ) {
            try {
                WKTReader reader = new WKTReader();
                reader.read( geom );
            } catch ( ParseException e ) {
                LOGGER.warn( "The following is not a valid WKT String: {}", geom );
                throw new UnsupportedQueryException( "Invalid WKT, cannot create geospatial query." );
            }
            geoCriteria = new GeospatialCriteria( geom );
        } else if ( StringUtils.isNotBlank( polygon ) ) {
            String wkt = GeospatialHelper.polygonToWKT( polygon );
            try {
                WKTReader reader = new WKTReader();
                reader.read( wkt );
            } catch ( ParseException e ) {
                LOGGER.warn( "The following is not a valid WKT String: {}", wkt );
                throw new UnsupportedQueryException( "Invalid WKT, cannot create geospatial query." );
            }
            geoCriteria = new GeospatialCriteria( wkt );
        }
        if ( geoCriteria != null && geoRelation != null ) {
            SpatialOperator spatialOp = SearchUtils.enumEqualsIgnoreCase( SpatialOperator.class, geoRelation );
            if ( spatialOp != null ) {
                geoCriteria.setSpatialOperator( spatialOp );
            }
        }

        return geoCriteria;
    }

    protected Filter getGeoFilter( Double radius, Double latitude, Double longitude, boolean isBbox, String geometry, SpatialOperator operator, StringBuilder humanReadableQuery )
            throws UnsupportedQueryException {
        Filter filter = null;
        if ( latitude != null && longitude != null && radius != null ) {
            String wkt = WKTWriter.toPoint( new Coordinate( longitude, latitude ) );
            filter = filterBuilder.attribute( Metacard.ANY_GEO ).withinBuffer().wkt( wkt, radius );
            humanReadableQuery.append( " " + SearchConstants.LATITUDE_PARAMETER + "=[" + latitude + "] " + SearchConstants.LONGITUDE_PARAMETER + "=[" + longitude + "] "
                    + SearchConstants.RADIUS_PARAMETER + "=[" + radius + "]" );
        } else {
            filter = getGeoFilter( operator, geometry, isBbox ? SearchConstants.BOX_PARAMETER : SearchConstants.GEOMETRY_PARAMETER, humanReadableQuery );
        }
        return filter;
    }

    protected Filter getGeoFilter( SpatialOperator operator, String wkt, String geoParameter, StringBuilder humanReadableQuery ) throws UnsupportedQueryException {
        Filter filter = null;
        if ( wkt != null ) {
            if ( operator != null ) {
                switch ( operator ) {
                case Contains:
                    filter = filterBuilder.attribute( Metacard.ANY_GEO ).within().wkt( wkt );
                    break;
                case Disjoint:
                    throw new UnsupportedQueryException( "Geospatial disjoint query is not currently supported" );
                case Within:
                    filter = filterBuilder.attribute( Metacard.ANY_GEO ).containing().wkt( wkt );
                    break;
                case Overlaps:
                default:
                    filter = filterBuilder.attribute( Metacard.ANY_GEO ).intersecting().wkt( wkt );
                    break;
                }
                humanReadableQuery.append( " " + geoParameter + "=[" + wkt + "] " + SearchConstants.GEO_RELATION_PARAMETER + "=[" + operator.toString().toLowerCase() + "]" );
            } else {
                filter = filterBuilder.attribute( Metacard.ANY_GEO ).intersecting().wkt( wkt );
                humanReadableQuery.append( " " + geoParameter + "=[" + wkt + "]" );
            }
        }
        return filter;

    }

    protected TemporalCriteria createTemporalCriteria( String start, String end, String type, StringBuilder humanReadableQuery, String defaultDateType ) throws UnsupportedQueryException {
        TemporalCriteria temporalCriteria = null;

        if ( StringUtils.isNotBlank( start ) || StringUtils.isNotBlank( end ) ) {
            Date startDate = SearchUtils.parseDate( start );
            Date endDate = SearchUtils.parseDate( end );
            if ( startDate != null && endDate != null ) {
                if ( startDate.after( endDate ) ) {
                    throw new UnsupportedQueryException( "Start date value [" + startDate + "] cannot be after endDate [" + endDate + "]" );
                }
            }
            String dateType = null;
            LOGGER.debug( "Getting date type name for type [{}]", type );
            if ( StringUtils.isNotBlank( type ) ) {
                if ( DATETYPE_MAP.containsKey( type ) ) {
                    dateType = DATETYPE_MAP.get( type );

                    LOGGER.debug( "Date type value received in map for request value [{}], setting internal query value to [{}]", type, dateType );
                } else {
                    String message = "Date type value not found in map for type [" + type + "], defaulting internal query value to [" + dateType + "]";
                    LOGGER.warn( message );
                    throw new UnsupportedQueryException( message );
                }
            } else {
                dateType = DATETYPE_MAP.get( defaultDateType );
                LOGGER.debug( "Date type value was not specified in request, defaulting internal query value to [{}]", dateType );
            }
            temporalCriteria = new TemporalCriteria( startDate, endDate, dateType );
        }
        return temporalCriteria;

    }

    protected Filter getTemporalFilter( Date startDate, Date endDate, String type, StringBuilder humanReadableQueryBuilder ) throws UnsupportedQueryException {
        Filter filter = null;
        if ( startDate != null || endDate != null ) {
            if ( startDate != null && endDate != null ) {
                if ( startDate.after( endDate ) ) {
                    throw new UnsupportedQueryException( "Start date value [" + startDate + "] cannot be after endDate [" + endDate + "]" );
                }
                filter = filterBuilder.attribute( type ).during().dates( startDate, endDate );
                humanReadableQueryBuilder.append( " " + SearchConstants.STARTDATE_PARAMETER + "=[" + startDate + "] " + SearchConstants.ENDDATE_PARAMETER + "=[" + endDate + "] "
                        + SearchConstants.DATETYPE_PARAMETER + "=[" + type + "]" );
            } else if ( startDate != null ) {
                filter = filterBuilder.attribute( type ).after().date( startDate );
                humanReadableQueryBuilder.append( " " + SearchConstants.STARTDATE_PARAMETER + "=[" + startDate + "] " + SearchConstants.DATETYPE_PARAMETER + "=[" + type + "]" );
            } else if ( endDate != null ) {
                filter = filterBuilder.attribute( type ).before().date( endDate );
                humanReadableQueryBuilder.append( " " + SearchConstants.ENDDATE_PARAMETER + "=[" + endDate + "] " + SearchConstants.DATETYPE_PARAMETER + "=[" + type + "]" );
            }
        }

        return filter;
    }

    protected List<PropertyCriteria> getPropertyCriteria( MultivaluedMap<String, String> queryParameters, Map<String, String> parameterExtensionMap ) {
        List<PropertyCriteria> criteriaList = new ArrayList<PropertyCriteria>();
        Set<String> keySet = queryParameters.keySet();
        for ( String key : keySet ) {
            String value = queryParameters.getFirst( key );
            if ( StringUtils.isNotBlank( value ) && parameterExtensionMap.containsKey( key ) ) {
                criteriaList.add( new PropertyCriteria( parameterExtensionMap.get( key ), value, Operator.LIKE ) );
            }
        }
        if ( queryParameters.containsKey( SearchConstants.CONTENT_COLLECTIONS_PARAMETER ) ) {
            String contentCollections = queryParameters.getFirst( SearchConstants.CONTENT_COLLECTIONS_PARAMETER );
            if ( StringUtils.isNotEmpty( contentCollections ) ) {
                criteriaList.add( new PropertyCriteria( CDRMetacard.METACARD_CONTENT_COLLECTION_ATTRIBUTE, contentCollections, Operator.LIKE ) );
            }
        }

        if ( queryParameters.containsKey( SearchConstants.RESOURCE_URI_PARAMETER ) ) {
            String uriString = queryParameters.getFirst( SearchConstants.RESOURCE_URI_PARAMETER );

            if ( StringUtils.isNotEmpty( uriString ) ) {
                if ( uriString.startsWith( SearchConstants.DAD_SCHEME ) ) {
                    try {
                        String uriSubstring = uriString;
                        StringBuilder sb = new StringBuilder( SearchConstants.DAD_SCHEME );
                        uriSubstring = uriSubstring.substring( SearchConstants.DAD_SCHEME.length() );
                        int index = uriSubstring.indexOf( '?' );
                        sb.append( URLEncoder.encode( uriSubstring.substring( 0, index ), "UTF-8" ) );
                        sb.append( "?" );
                        uriSubstring = uriSubstring.substring( index + 1 );
                        index = uriSubstring.indexOf( '#' );
                        sb.append( URLEncoder.encode( uriSubstring.substring( 0, index ), "UTF-8" ) );
                        sb.append( "#" );
                        uriSubstring = uriSubstring.substring( index + 1 );
                        sb.append( URLEncoder.encode( uriSubstring.substring( 0 ), "UTF-8" ) );

                        uriString = sb.toString();
                    } catch ( UnsupportedEncodingException | RuntimeException e ) {
                        LOGGER.warn( "Could parse the 'resource-uri' due to exception so falling back to not parsing: " + e.getMessage() );
                    }
                }
                criteriaList.add( new PropertyCriteria( Metacard.RESOURCE_URI, uriString, Operator.EQUALS ) );
            }
        }
        if ( queryParameters.containsKey( SearchConstants.CONTENT_TYPE_PARAMETER ) ) {
            String contentTypesString = queryParameters.getFirst( SearchConstants.CONTENT_TYPE_PARAMETER );
            if ( StringUtils.isNotEmpty( contentTypesString ) ) {
                criteriaList.add( new PropertyCriteria( Metacard.CONTENT_TYPE, contentTypesString, Operator.EQUALS ) );
            }
        }
        return criteriaList;
    }

    protected Filter getPropertyFilter( String property, String value, Operator operator, StringBuilder humanReadableQueryBuilder ) {
        Filter filter = null;
        if ( property != null && operator != null ) {

            if ( property.equals( Metacard.CONTENT_TYPE ) ) {
                filter = getContentTypeFilter( value );
                humanReadableQueryBuilder.append( " " + property + "=like[" + value + "] " );
            } else if ( property.equals( CDRMetacard.METACARD_CONTENT_COLLECTION_ATTRIBUTE ) ) {
                filter = getContentCollectionsFilter( property, value );
                humanReadableQueryBuilder.append( " " + property + "=like[" + value + "] " );
            } else {
                if ( Operator.EQUALS.equals( operator ) ) {
                    filter = filterBuilder.attribute( property ).equalTo().text( value );
                    humanReadableQueryBuilder.append( " " + property + "=[" + value + "] " );
                } else if ( Operator.LIKE.equals( operator ) ) {
                    filter = filterBuilder.attribute( property ).like().text( value );
                    humanReadableQueryBuilder.append( " " + property + "=like[" + value + "] " );
                }
            }
        }
        return filter;
    }

    protected Filter getContentCollectionsFilter( String property, String value ) {
        List<Filter> filterList = new ArrayList<Filter>();
        String[] collections = value.split( "," );
        for ( String collection : collections ) {
            filterList.add( filterBuilder.attribute( property ).like().text( collection ) );
        }

        return filterBuilder.anyOf( filterList );
    }

    protected Filter getContentTypeFilter( String value ) {
        List<Filter> filterList = new ArrayList<Filter>();
        String[] contentTypes = value.split( "," );
        for ( String contentType : contentTypes ) {
            String[] typeAndVersion = contentType.split( ":" );
            String type = typeAndVersion[0];
            if ( typeAndVersion.length == 1 ) {
                filterList.add( filterBuilder.attribute( Metacard.CONTENT_TYPE ).like().text( type ) );
            } else {
                List<Filter> typeVersionPairs = new ArrayList<Filter>();
                String[] versions = typeAndVersion[1].split( "\\|" );
                for ( String version : versions ) {
                    Filter typeFilter = filterBuilder.attribute( Metacard.CONTENT_TYPE ).like().text( type );
                    Filter versionFilter = filterBuilder.attribute( Metacard.CONTENT_TYPE_VERSION ).like().text( version );
                    typeVersionPairs.add( filterBuilder.allOf( typeFilter, versionFilter ) );
                }

                // Check if we had any type/version pairs and 'OR' them together.
                if ( !typeVersionPairs.isEmpty() ) {
                    filterList.add( filterBuilder.anyOf( typeVersionPairs ) );
                }
            }
        }

        return filterBuilder.anyOf( filterList );
    }

}
