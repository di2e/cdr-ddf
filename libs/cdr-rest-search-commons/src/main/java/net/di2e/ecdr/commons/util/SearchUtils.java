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
package net.di2e.ecdr.commons.util;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import net.di2e.ecdr.commons.constants.SearchConstants;
import net.di2e.ecdr.commons.sort.SortTypeConfiguration;

import org.apache.commons.lang.StringUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.ISODateTimeFormat;
import org.opengis.filter.Filter;
import org.opengis.filter.sort.SortBy;
import org.opengis.filter.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.catalog.filter.impl.SortByImpl;
import ddf.catalog.source.UnsupportedQueryException;

/**
 * Utility class that contains helper methods.
 */
public final class SearchUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger( SearchUtils.class );

    private static final String MAP_ENTRY_DELIMITER = "=";

    private static DateTimeFormatter formatter;

    /*
     * The OpenSearch specification uses RFC 3339, which is a specific profile of the ISO 8601 standard and corresponds
     * to the second and (as a "rarely used option") the first parser below. We additionally support the corresponding
     * ISO 8601 Basic profiles.
     */
    static {
        DateTimeParser[] parsers = { ISODateTimeFormat.dateTime().getParser(), ISODateTimeFormat.dateTimeNoMillis().getParser(), ISODateTimeFormat.basicDateTime().getParser(),
                ISODateTimeFormat.basicDateTimeNoMillis().getParser() };
        formatter = new DateTimeFormatterBuilder().append( null, parsers ).toFormatter();
    }

    private SearchUtils() {
    }

    public static Boolean getBoolean( String booleanString, Boolean defaultValue ) {
        Boolean bool = null;
        if ( booleanString != null ) {
            booleanString = booleanString.trim();
            if ( "1".equals( booleanString ) || Boolean.TRUE.toString().equalsIgnoreCase( booleanString ) ) {
                bool = Boolean.TRUE;
            } else if ( "0".equals( booleanString ) || Boolean.FALSE.toString().equalsIgnoreCase( booleanString ) ) {
                bool = Boolean.FALSE;
            }
        }
        return bool != null ? bool : defaultValue;
    }

    public static Map<String, String> convertToMap( String mapStr ) {
        Map<String, String> inputMap = new HashMap<String, String>();
        if ( StringUtils.isNotBlank( mapStr ) ) {
            inputMap = convertToMap( Arrays.asList(mapStr.split( "," )));
        }
        return inputMap;
    }

    public static Map<String, String> convertToMap( List<String> mapList ) {
        Map<String, String> inputMap = new HashMap<String, String>();
        for ( String sortPair : mapList ) {
            String[] pairAry = sortPair.split( MAP_ENTRY_DELIMITER );
            if ( pairAry.length == 2 ) {
                inputMap.put( pairAry[0], pairAry[1] );
            } else {
                LOGGER.warn( "Could not parse out map entry from {}, skipping this item.", sortPair );
            }
        }
        return inputMap;
    }

    public static <T extends Enum<?>> T enumEqualsIgnoreCase( Class<T> enumeration, String search ) {
        for ( T each : enumeration.getEnumConstants() ) {
            if ( StringUtils.equalsIgnoreCase( each.name(), search ) ) {
                return each;
            }
        }
        return null;
    }

    public static Date parseDate( String date ) throws UnsupportedQueryException {
        Date returnDate = null;
        if ( StringUtils.isNotBlank( date ) ) {
            try {
                returnDate = formatter.parseDateTime( date ).toDate();
            } catch ( IllegalArgumentException e ) {
                LOGGER.warn( "Could not process date because of invalid format [{}]", date );
                throw new UnsupportedQueryException( "Invalid date format [" + date + "]" );
            }
        }
        return returnDate;
    }

    public static SortBy getSortBy( MultivaluedMap<String, String> queryParameters, List<SortTypeConfiguration> sortTypeConfigurationList ) {
        String sortByString = queryParameters.getFirst( SearchConstants.SORTKEYS_PARAMETER );
        SortBy sortBy = null;
        if ( StringUtils.isNotBlank( sortByString ) ) {
            String[] sortValues = sortByString.split( "," );
            String sortKey = sortValues[0];
            SortTypeConfiguration sortType = getSortConfiguration( sortKey, sortTypeConfigurationList );
            if ( sortType != null ) {
                String sortAttribute = sortType.getSortAttribute();
                SortOrder sortOrder = null;
                if ( sortValues.length >= 3 ) {
                    if ( Boolean.FALSE.toString().equalsIgnoreCase( sortValues[2] ) ) {
                        sortOrder = SortOrder.DESCENDING;
                    } else {
                        sortOrder = SortOrder.ASCENDING;
                    }
                } else {
                    sortOrder = SortOrder.valueOf( sortType.getSortOrder() );
                }
                sortBy = new SortByImpl( sortAttribute, sortOrder );
            }
        }
        return sortBy;
    }

    private static SortTypeConfiguration getSortConfiguration( String sortKey, List<SortTypeConfiguration> sortTypeConfigurationList ) {
        for ( SortTypeConfiguration sortType : sortTypeConfigurationList ) {
            LOGGER.debug( "Comparing incoming sort key of {} with configuration of key {}", sortKey, sortType.getSortKey() );
            if ( sortType.getSortKey().equals( sortKey ) ) {
                return sortType;
            }
        }
        return null;
    }

    protected static boolean isBoolean( String value ) {
        boolean isBoolean = false;
        if ( StringUtils.isNotBlank( value ) ) {
            value = value.toLowerCase();
            isBoolean = value.equals( "false" ) || value.equals( "true" ) || value.equals( "0" ) || value.equals( "1" );
        }

        return isBoolean;
    }

    public static boolean isBooleanNullOrBlank( String value ) {
        boolean isBoolOrNull = true;
        if ( StringUtils.isNotBlank( value ) ) {
            isBoolOrNull = isBoolean( value );
        }
        return isBoolOrNull;
    }

    public static Boolean getBoolean( String booleanString ) {
        Boolean bool = null;
        if ( booleanString != null ) {
            booleanString = booleanString.trim();
            if ( "1".equals( booleanString ) || Boolean.TRUE.toString().equalsIgnoreCase( booleanString ) ) {
                bool = Boolean.TRUE;
            } else if ( "0".equals( booleanString ) || Boolean.FALSE.toString().equalsIgnoreCase( booleanString ) ) {
                bool = Boolean.FALSE;
            }
        }
        return bool;
    }

    public static void addFilter( List<Filter> filters, Filter filter ) {
        if ( filter != null ) {
            LOGGER.debug( "Filter was not null, and will be added to the list of valid filters" );
            filters.add( filter );
        } else {
            LOGGER.debug( "Filter was null, not adding to the Filter list" );
        }
    }

}
