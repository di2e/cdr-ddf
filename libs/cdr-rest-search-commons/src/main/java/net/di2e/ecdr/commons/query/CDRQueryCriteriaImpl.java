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
