package net.di2e.ecdr.commons.query;

import java.util.ArrayList;
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
    private double defaultRadius = 10000;
    private String defaultResponseFormat = SearchConstants.ATOM_RESPONSE_FORMAT;;
    private boolean defaultFuzzySearch = true;
    private boolean defaultDeduplication = false;
    private Map<String, String> parameterExtensionMap = SearchUtils.convertToMap( "uid=id" );
    private List<String> parameterPropertyList = new ArrayList<>();
    private String defaultQueryLanguage = SearchConstants.CDR_KEYWORD_QUERY_LANGUAGE;
    private String defaultQueryLanguageCustom = null;
    private int queryRequestCacheSize = 1000;

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

    public void setDefaultTimeoutMillis( long millis ) {
        LOGGER.debug( "ConfigUpdate: Updating the default timeout (in milliseconds) to [{}]", millis );
        this.defaultTimeoutMillis = millis;
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

    public void setDefaultRadius( double radius ) {
        LOGGER.debug( "ConfigUpdate: Updating the default radius (in meters) to [{}]", radius );
        this.defaultRadius = radius;
    }

    @Override
    public String getDefaultResponseFormat() {
        return defaultResponseFormat;
    }

    public void setDefaultResponseFormat( String format ) {
        LOGGER.debug( "ConfigUpdate: Updating the default response format to [{}]", format );
        this.defaultResponseFormat = format;
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

    public void setParameterExtensionMap( Map<String, String> paramMap ) {
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
    public int getQueryRequestCacheSize() {
        return queryRequestCacheSize;
    }

    public void setQueryRequestCacheSize( int size ) {
        LOGGER.debug( "ConfigUpdate: Updating the query request cache size value to [{}]", size );
        queryRequestCacheSize = size;
    }

}
