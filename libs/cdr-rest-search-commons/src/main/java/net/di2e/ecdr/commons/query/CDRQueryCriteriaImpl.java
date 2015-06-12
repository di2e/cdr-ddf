package net.di2e.ecdr.commons.query;

import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import net.di2e.ecdr.api.query.QueryConfiguration;
import net.di2e.ecdr.api.query.QueryCriteria;

import org.opengis.filter.Filter;
import org.opengis.filter.sort.SortBy;

public class CDRQueryCriteriaImpl implements QueryCriteria {

    private Filter filter = null;
    private SortBy sortBy = null;
    private String humanReadableQuery = null;
    private boolean isWellFormed = true;
    private MultivaluedMap<String, String> parameterValues = null;
    private Map<String, String> queryProperties = null;
    private QueryConfiguration queryConfiguration = null;

    public CDRQueryCriteriaImpl( Filter filter, SortBy sortBy, String humanReadableQuery, boolean isWellFormed, MultivaluedMap<String, String> parameterValues,
            Map<String, String> queryProperties, QueryConfiguration queryConfig ) {
        super();
        this.filter = filter;
        this.sortBy = sortBy;
        this.humanReadableQuery = humanReadableQuery;
        this.isWellFormed = isWellFormed;
        this.parameterValues = parameterValues;
        this.queryProperties = queryProperties;
        this.queryConfiguration = queryConfig;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    @Override
    public SortBy getSortBy() {
        return sortBy;
    }

    @Override
    public String getHumanReadableQuery() {
        return humanReadableQuery;
    }

    @Override
    public boolean isWellFormed() {
        return isWellFormed;
    }

    @Override
    public MultivaluedMap<String, String> getParameterValues() {
        return parameterValues;
    }

    @Override
    public Map<String, String> getQueryProperties() {
        return queryProperties;
    }

    @Override
    public QueryConfiguration getQueryConfiguration() {
        return queryConfiguration;
    }

}
