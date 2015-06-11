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
package net.di2e.ecdr.querylanguage.cql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import net.di2e.ecdr.api.query.QueryConfiguration;
import net.di2e.ecdr.api.query.QueryCriteria;
import net.di2e.ecdr.api.query.QueryLanguage;
import net.di2e.ecdr.commons.constants.SearchConstants;
import net.di2e.ecdr.commons.query.CDRQueryCriteriaImpl;
import net.di2e.ecdr.commons.sort.SortTypeConfiguration;
import net.di2e.ecdr.commons.util.SearchUtils;

import org.geotools.filter.text.cql2.CQLException;
import org.geotools.filter.text.ecql.ECQL;
import org.opengis.filter.Filter;
import org.opengis.filter.sort.SortBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.catalog.filter.FilterBuilder;
import ddf.catalog.source.UnsupportedQueryException;

public class CQLQueryLanguage implements QueryLanguage {

    private static final Logger LOGGER = LoggerFactory.getLogger( CQLQueryLanguage.class );
    private FilterBuilder filterBuilder = null;
    private List<SortTypeConfiguration> sortTypeConfigurationList = null;

    public CQLQueryLanguage( FilterBuilder builder, List<SortTypeConfiguration> sortTypeConfigurations ) {
        filterBuilder = builder;
        sortTypeConfigurationList = sortTypeConfigurations;
    }

    @Override
    public String getName() {
        return SearchConstants.CDR_CQL_QUERY_LANGUAGE;
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

        LOGGER.debug( "Received CQL-based query." );
        String cqlStr = queryParameters.getFirst( SearchConstants.KEYWORD_PARAMETER );
        try {
            Filter filter = ECQL.toFilter( cqlStr );
            SearchUtils.addFilter( filters, filter );
        } catch ( CQLException cqlException ) {
            throw new UnsupportedQueryException( "Invalid CQL predicate provided.", cqlException );
        }

        return new CDRQueryCriteriaImpl( filterBuilder.allOf( filters ), sortBy, cqlStr, true, queryParameters, new HashMap<String, String>(), queryConfig );
    }

}
