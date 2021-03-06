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

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import net.di2e.ecdr.api.config.DateTypeConfiguration;
import net.di2e.ecdr.api.config.SortTypeConfiguration;
import net.di2e.ecdr.commons.constants.SearchConstants;

import org.apache.commons.collections.CollectionUtils;
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
import ddf.catalog.operation.Query;
import ddf.catalog.operation.QueryResponse;
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
        DateTimeParser[] parsers = { ISODateTimeFormat.date().getParser(), ISODateTimeFormat.dateTime().getParser(), ISODateTimeFormat.dateTimeNoMillis().getParser(),
                ISODateTimeFormat.basicDateTime().getParser(), ISODateTimeFormat.basicDateTimeNoMillis().getParser() };
        formatter = new DateTimeFormatterBuilder().append( null, parsers ).toFormatter();
    }

    private SearchUtils() {
    }

    public static Map<String, Serializable> getTransformLinkProperties( UriInfo uriInfo, Query query, QueryResponse response, String scheme, String host, Integer port ) {
        Map<String, Serializable> properties = new HashMap<String, Serializable>();
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();

        uriBuilder = updateURLWithPlatformValues( uriBuilder, scheme, host, port );

        String selfLink = uriBuilder.toTemplate();
        properties.put( SearchConstants.SELF_LINK_REL, selfLink );
        LOGGER.debug( "Adding self link parameter[{}] with value [{}] to transform properties to be sent to result transformer", SearchConstants.SELF_LINK_REL, selfLink );
        int startIndex = query.getStartIndex();
        int pageSize = query.getPageSize();
        long totalCount = response.getHits();

        if ( startIndex + pageSize <= totalCount ) {
            String template = uriBuilder.replaceQueryParam( SearchConstants.STARTINDEX_PARAMETER, String.valueOf( startIndex + pageSize ) ).toTemplate();
            properties.put( SearchConstants.NEXT_LINK_REL, template );
            LOGGER.debug( "Adding next link parameter[{}] with value [{}] to transform properties to be sent to result transformer", SearchConstants.NEXT_LINK_REL, template );
        }

        if ( startIndex > 1 ) {
            String template = uriBuilder.replaceQueryParam( SearchConstants.STARTINDEX_PARAMETER, String.valueOf( Math.max( 1, startIndex - pageSize ) ) ).toTemplate();
            properties.put( SearchConstants.PREV_LINK_REL, template );
            LOGGER.debug( "Adding previous link parameter[{}] with value [{}] to transform properties to be sent to result transformer", SearchConstants.PREV_LINK_REL, template );
        }
        return properties;
    }

    public static UriBuilder updateURLWithPlatformValues( UriBuilder builder, String scheme, String host, Integer port ) {
        if ( StringUtils.isNotBlank( scheme ) && StringUtils.isNotBlank( host ) ) {
            LOGGER.debug( "Using values from Platform Configuration for Atom Links host[" + host + "], scheme[" + scheme + "], and port[" + port + "]" );
            builder.scheme( scheme );
            builder.host( host );
            if ( port != null ) {
                builder.port( port );
            }
        }
        return builder;
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
            inputMap = convertToMap( Arrays.asList( mapStr.split( "," ) ) );
        }
        return inputMap;
    }

    public static Map<String, String> convertToMap( List<String> mapList ) {
        Map<String, String> inputMap = new HashMap<>();
        if ( CollectionUtils.isNotEmpty( mapList ) ) {
            for ( String sortPair : mapList ) {
                String[] pairAry = sortPair.split( MAP_ENTRY_DELIMITER );
                if ( pairAry.length == 2 ) {
                    inputMap.put( StringUtils.trimToEmpty( pairAry[0] ), StringUtils.trimToEmpty( pairAry[1] ) );
                } else {
                    LOGGER.warn( "Could not parse out map entry from {}, skipping this item.", sortPair );
                }
            }
        }
        return inputMap;
    }

    public static Map<String, String> convertDateTypeToMap( List<DateTypeConfiguration> dateTypeList ) {
        Map<String, String> inputMap = new HashMap<>();
        if ( CollectionUtils.isNotEmpty( dateTypeList ) ) {
            for ( DateTypeConfiguration dateType : dateTypeList ) {
                inputMap.put( dateType.getCDRDateType(), dateType.getInternalDateType() );
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

    public static SortBy getSortBy( String sortByString, List<SortTypeConfiguration> sortTypeConfigurationList, boolean supportWildcards ) {
        SortBy sortBy = null;
        if ( StringUtils.isNotBlank( sortByString ) ) {
            String[] sortValues = sortByString.split( "," );
            String sortKey = sortValues[0];
            if ( StringUtils.isNotBlank( sortKey ) ) {
                SortTypeConfiguration sortType = getSortConfiguration( sortKey, sortTypeConfigurationList, supportWildcards );
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
        }
        return sortBy;
    }

    public static void logSort( SortBy sortBy ) {
        if ( LOGGER.isDebugEnabled() ) {
            if ( sortBy != null ) {
                LOGGER.debug( "SortBy for query is set to [{}]", sortBy.getPropertyName().getPropertyName() + "/" + sortBy.getSortOrder() );
            } else {
                LOGGER.debug( "SortBy for query is set to null" );
            }
        }
    }

    private static SortTypeConfiguration getSortConfiguration( String sortKey, List<SortTypeConfiguration> sortTypeConfigurationList, boolean supportWildcard ) {
        for ( SortTypeConfiguration sortType : sortTypeConfigurationList ) {
            String configSortKey = sortType.getSortKey();
            LOGGER.trace( "Comparing incoming sort key of {} with configuration of key {}", sortKey, configSortKey );
            if ( supportWildcard ) {
                if ( configSortKey.startsWith( SortTypeConfiguration.SORT_WILDCARD ) ) {
                    configSortKey = StringUtils.substringAfterLast( configSortKey, SortTypeConfiguration.SORT_XPATH_DELIMITTER );
                    if ( sortKey.contains( SortTypeConfiguration.SORT_XPATH_DELIMITTER ) ) {
                        sortKey = StringUtils.substringAfterLast( sortKey, SortTypeConfiguration.SORT_XPATH_DELIMITTER );
                    }
                }
                LOGGER.trace( "SortKeys being compated after wildcard normalization query sortKey=[{}] and configuration sortKey=[{}]", sortKey, configSortKey );
            }
            if ( StringUtils.equalsIgnoreCase( configSortKey, sortKey ) ) {
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

    public static String getAllowedSortValues( List<SortTypeConfiguration> sortTypes ) {
        StringBuilder sb = new StringBuilder();
        for ( SortTypeConfiguration sortType : sortTypes ) {
            sb.append( "'" + sortType.getSortKey() + "' " );
        }
        return sb.toString();
    }

}
