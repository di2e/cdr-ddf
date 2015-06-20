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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.di2e.ecdr.api.query.QueryConfiguration;
import net.di2e.ecdr.commons.constants.SearchConstants;
import net.di2e.ecdr.commons.util.SearchUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryConfigurationImpl implements QueryConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger( QueryConfigurationImpl.class );

    private int defaultCount = 100;
    private long defaultTimeoutMillis = 300000;
    private String defaultDateType = "effective";
    private String defaultDateTypeCustom = null;
    private double defaultRadius = 50000;
    private String defaultResponseFormat = SearchConstants.ATOM_RESPONSE_FORMAT;
    private String defaultResponseFormatCustom = null;
    private boolean defaultFuzzySearch = true;
    private boolean defaultDeduplication = false;

    private String defaultQueryLanguage = SearchConstants.CDR_KEYWORD_QUERY_LANGUAGE;
    private String defaultQueryLanguageCustom = null;
    private int queryRequestCacheSize = 1000;

    private Map<String, String> parameterExtensionMap = SearchUtils.convertToMap( "uid=id" );
    private List<String> parameterPropertyList = Arrays.asList( new String[] { "oid", "path" } );
    private List<String> headerPropertyList = Arrays.asList( new String[] { "EMID" } );

    @Override
    public int getDefaultCount() {
        return defaultCount;
    }

    public void setDefaultCount( int count ) {
        LOGGER.debug( "ConfigUpdate: Updating the default count to [{}]", defaultCount );
        this.defaultCount = count;
    }

    @Override
    public long getDefaultTimeoutMillis() {
        return defaultTimeoutMillis;
    }

    public void setDefaultTimeoutSeconds( long seconds ) {
        LOGGER.debug( "ConfigUpdate: Updating the default timeout (in seconds) to [{}]", seconds );
        this.defaultTimeoutMillis = seconds * 1000L;
    }

    @Override
    public String getDefaultDateType() {
        return StringUtils.defaultIfBlank( defaultDateTypeCustom, defaultDateType );
    }

    public void setDefaultDateType( String dateType ) {
        LOGGER.debug( "ConfigUpdate: Updating the default dateType to [{}]", dateType );
        this.defaultDateType = dateType;
    }

    public void setDefaultDateTypeCustom( String dateType ) {
        LOGGER.debug( "ConfigUpdate: Updating the default dateType custom value to [{}]", dateType );
        this.defaultDateTypeCustom = dateType;
    }

    @Override
    public double getDefaultRadius() {
        return defaultRadius;
    }

    public void setDefaultRadiusMeters( double radius ) {
        LOGGER.debug( "ConfigUpdate: Updating the default radius (in meters) to [{}]", radius );
        this.defaultRadius = radius;
    }

    @Override
    public String getDefaultResponseFormat() {
        return StringUtils.defaultIfBlank( defaultResponseFormatCustom, defaultResponseFormat );
    }

    public void setDefaultResponseFormat( String format ) {
        LOGGER.debug( "ConfigUpdate: Updating the default response format to [{}]", format );
        this.defaultResponseFormat = format;
    }
    
    public void setDefaultResponseFormatCustom( String format ) {
        LOGGER.debug( "ConfigUpdate: Updating the default response format custom override value to [{}]", format );
        this.defaultResponseFormatCustom = format;
    }

    @Override
    public boolean isDefaultFuzzySearch() {
        return defaultFuzzySearch;
    }

    public void setDefaultFuzzySearch( boolean fuzzy ) {
        LOGGER.debug( "ConfigUpdate: Updating the default fuzzy search value to [{}]", fuzzy );
        this.defaultFuzzySearch = fuzzy;
    }

    @Override
    public boolean isDefaultDeduplication() {
        return defaultDeduplication;
    }

    public void setDefaultDeduplication( boolean dedup ) {
        LOGGER.debug( "ConfigUpdate: Updating the default deduplication value to [{}]", dedup );
        this.defaultDeduplication = dedup;
    }

    @Override
    public String getDefaultQueryLanguage() {
        return StringUtils.defaultIfBlank( defaultQueryLanguageCustom, defaultQueryLanguage );
    }

    public void setDefaultQueryLanguage( String lang ) {
        LOGGER.debug( "ConfigUpdate: Updating the default query language to [{}]", lang );
        this.defaultQueryLanguage = lang;
    }

    public void setDefaultQueryLanguageCustom( String lang ) {
        LOGGER.debug( "ConfigUpdate: Updating the default query language custom value to [{}]", lang );
        this.defaultQueryLanguageCustom = lang;
    }

    @Override
    public Map<String, String> getParameterExtensionMap() {
        return parameterExtensionMap;
    }

    public void setParameterExtensionMap( List<String> paramList ) {
        Map<String, String> paramMap = SearchUtils.convertToMap( paramList );
        LOGGER.debug( "ConfigUpdate: Updating the default parameter extension map value to [{}]", paramMap );
        this.parameterExtensionMap = paramMap;
    }

    @Override
    public List<String> getParameterPropertyList() {
        return parameterPropertyList;
    }

    public void setParameterPropertyList( List<String> propList ) {
        LOGGER.debug( "ConfigUpdate: Updating the default parameter property map value to [{}]", propList );
        this.parameterPropertyList = propList;
    }

    @Override
    public List<String> getHeaderPropertyList() {
        return headerPropertyList;
    }

    public void setHeaderPropertyList( List<String> propList ) {
        LOGGER.debug( "ConfigUpdate: Updating the default HTTP Header property map value to [{}]", propList );
        this.headerPropertyList = propList;
    }

    @Override
    public int getQueryRequestCacheSize() {
        return queryRequestCacheSize;
    }

    public void setQueryRequestCacheSize( int size ) {
        LOGGER.debug( "ConfigUpdate: Updating the query request cache size value to [{}]", size );
        queryRequestCacheSize = size;
    }

}
