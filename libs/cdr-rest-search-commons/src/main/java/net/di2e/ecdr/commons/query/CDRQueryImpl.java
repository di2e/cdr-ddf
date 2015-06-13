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
package net.di2e.ecdr.commons.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.ws.rs.core.MultivaluedMap;

import net.di2e.ecdr.api.query.QueryCriteria;
import net.di2e.ecdr.commons.constants.SearchConstants;
import net.di2e.ecdr.commons.util.SearchUtils;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.opengis.filter.FilterVisitor;
import org.opengis.filter.sort.SortBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.catalog.operation.Query;
import ddf.catalog.source.UnsupportedQueryException;

public class CDRQueryImpl implements Query {

    private static final Logger LOGGER = LoggerFactory.getLogger( CDRQueryImpl.class );

    private QueryCriteria queryCriteria = null;
    private StringBuilder humanReadableQueryBuilder = null;

    private Collection<String> sources = null;

    private int count;
    private String responseFormat;
    private long timeoutMilliseconds;
    private boolean isStrictMode = false;
    private int startIndex;

    public CDRQueryImpl( QueryCriteria crit, String localSourceId )
            throws UnsupportedQueryException {
        queryCriteria = crit;

        MultivaluedMap<String, String> queryParameters = queryCriteria.getParameterValues();
        humanReadableQueryBuilder = new StringBuilder( StringUtils.defaultString( queryCriteria.getHumanReadableQuery() ) );

        populateStrictMode( queryParameters );
        appendParameter( SearchConstants.STRICTMODE_PARAMETER, isStrictMode );

        populateResponseFormat( queryParameters );
        appendParameter( SearchConstants.FORMAT_PARAMETER, responseFormat );

        populateStartIndex( queryParameters );
        appendParameter( SearchConstants.STARTINDEX_PARAMETER, startIndex );

        populateCount( queryParameters );
        appendParameter( SearchConstants.COUNT_PARAMETER, count );

        populateTimeoutMilliseconds( queryParameters );
        appendParameter( SearchConstants.TIMEOUT_PARAMETER, timeoutMilliseconds );

        boolean includeStatus = isIncludeStatus( queryParameters );
        appendParameter( SearchConstants.STATUS_PARAMETER, includeStatus );

        populateSourceList( queryCriteria.getParameterValues() );
        if ( !sources.isEmpty() ) {
            appendParameter( SearchConstants.SOURCE_PARAMETER, StringUtils.join( sources, ", " ) );
        }
    }

    protected void appendParameter( String parameter, Object value ) {
        humanReadableQueryBuilder.append( " " + parameter + "=[" + value + "] " );
    }

    @Override
    public boolean evaluate( Object object ) {
        return queryCriteria.getFilter().evaluate( object );
    }

    @Override
    public Object accept( FilterVisitor visitor, Object extraData ) {
        return queryCriteria.getFilter().accept( visitor, extraData );
    }

    @Override
    public int getPageSize() {
        return count;
    }

    @Override
    public SortBy getSortBy() {
        return queryCriteria.getSortBy();
    }

    @Override
    public int getStartIndex() {
        return startIndex;
    }

    @Override
    public long getTimeoutMillis() {
        return timeoutMilliseconds;
    }

    @Override
    public boolean requestsTotalResultsCount() {
        return true;
    }

    public boolean getStrictMode() {
        return isStrictMode;
    }

    public String getResponseFormat() {
        return responseFormat;
    }

    public String getHumanReadableQuery() {
        return humanReadableQueryBuilder.toString();
    }

    public Collection<String> getSiteNames() {
        return sources;
    }

    protected void populateSourceList( MultivaluedMap<String, String> queryParameters ) {
        sources = new ArrayList<String>();
        String sourceString = queryParameters.getFirst( SearchConstants.SOURCE_PARAMETER );
        if ( sourceString != null && !sourceString.isEmpty() ) {
            String[] sourceArray = sourceString.split( "," );
            sources = Arrays.asList( sourceArray );
        }
    }

    protected void populateTimeoutMilliseconds( MultivaluedMap<String, String> queryParameters ) throws UnsupportedQueryException {
        String timeout = queryParameters.getFirst( SearchConstants.TIMEOUT_PARAMETER );
        timeoutMilliseconds = queryCriteria.getQueryConfiguration().getDefaultTimeoutMillis();
        LOGGER.debug( "Attempting to set 'timeout' value from request [" + timeout + "] to long" );
        if ( StringUtils.isNotBlank( timeout ) ) {
            try {
                timeoutMilliseconds = Long.parseLong( timeout );
                if ( timeoutMilliseconds <= 0 ) {
                    throw new UnsupportedQueryException( "The [" + SearchConstants.TIMEOUT_PARAMETER + "] parameter cannot nbe less than 0 and was [" + timeout + "]" );
                }
            } catch ( NumberFormatException e ) {
                String message = "Invalid Number found for 'timeout' [" + timeout + "].  Resulted in exception: " + e.getMessage();
                LOGGER.warn( message );
                if ( isStrictMode ) {
                    throw new UnsupportedQueryException( message );
                }
            }
        } else {
            LOGGER.debug( "'timeout' parameter was not specified, defaulting value to [{}]", timeout );
        }

    }

    protected void populateResponseFormat( MultivaluedMap<String, String> queryParameters ) {
        String format = queryParameters.getFirst( SearchConstants.FORMAT_PARAMETER );
        responseFormat = StringUtils.defaultIfBlank( format, queryCriteria.getQueryConfiguration().getDefaultResponseFormat() );
    }

    protected void populateStartIndex( MultivaluedMap<String, String> queryParameters ) throws UnsupportedQueryException {
        String indexString = queryParameters.getFirst( SearchConstants.STARTINDEX_PARAMETER );
        int index = 1;
        LOGGER.debug( "Attempting to set 'startIndex' value from request [{}] to int", indexString );
        if ( StringUtils.isNotBlank( indexString ) ) {
            try {
                index = Integer.parseInt( indexString );
            } catch ( NumberFormatException e ) {
                String message = "Invalid Number found for 'startIndex' [" + indexString + "].  Resulted in exception: " + e.getMessage();
                LOGGER.warn( message );
                throw new UnsupportedQueryException( message );
            }
        } else {
            LOGGER.debug( "'startIndex' parameter was not specified, defaulting value to [{}]", index );
        }
        startIndex = index < 1 ? 1 : index;
    }

    protected void populateCount( MultivaluedMap<String, String> queryParameters ) throws UnsupportedQueryException {
        String stringCount = queryParameters.getFirst( SearchConstants.COUNT_PARAMETER );
        count = queryCriteria.getQueryConfiguration().getDefaultCount();
        LOGGER.debug( "Attempting to set 'count' value from request [{}] to int", stringCount );
        if ( StringUtils.isNotBlank( stringCount ) ) {
            try {
                count = Integer.parseInt( stringCount );
            } catch ( NumberFormatException e ) {
                String message = "Invalid Number found for 'count' [" + stringCount + "].  Resulted in exception: " + e.getMessage();
                LOGGER.warn( message );
                throw new UnsupportedQueryException( message );
            }
        } else {
            LOGGER.debug( "'count' parameter was not specified, defaulting value to [{}]", count );
        }
    }

    protected void populateStrictMode( MultivaluedMap<String, String> queryParameters ) {
        isStrictMode = BooleanUtils.toBooleanDefaultIfNull( SearchUtils.getBoolean( queryParameters.getFirst( SearchConstants.STRICTMODE_PARAMETER ) ), false );
    }

    protected boolean isIncludeStatus( MultivaluedMap<String, String> queryParameters ) {
        // Include status is true unless explicitly set to false
        return BooleanUtils.toBooleanDefaultIfNull( SearchUtils.getBoolean( queryParameters.getFirst( SearchConstants.STATUS_PARAMETER ) ), true );
    }




}
