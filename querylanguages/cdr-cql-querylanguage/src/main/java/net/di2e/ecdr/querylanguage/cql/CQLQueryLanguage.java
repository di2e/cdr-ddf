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
import net.di2e.ecdr.api.sort.SortTypeConfiguration;
import net.di2e.ecdr.commons.constants.SearchConstants;
import net.di2e.ecdr.commons.query.CDRQueryCriteriaImpl;
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
    public String getUrlTemplateParameters() {
        return "&sortKeys={sru:sortKeys?}";
    }

    @Override
    public String getName() {
        return SearchConstants.CDR_CQL_QUERY_LANGUAGE;
    }

    @Override
    public String getLanguageDescription( QueryConfiguration queryConfig ) {
        // @formatter:off
        return "CQL Query Language\n****************************" + System.lineSeparator()
             + "Usage: To use the CQL query language specify '" + getName() + "' in the {cdrs:queryLanguage} parameter placeholder."  + System.lineSeparator()
             + "       The CQL query string should be put the contents of the CQL query in the {os:searchTerms} parameter placeholder."  + System.lineSeparator()
             + System.lineSeparator()
             + "Examples:  metadata like 'test'" + System.lineSeparator()
             + "           created <> '2014-01-03'" + System.lineSeparator()
             + "           metadata = 'England' and (title = 'London' and created before 2014-05-05T00:00:00) or (title = 'Cambridge' and created between '2014-01-03' and '2014-03-03')"
             + System.lineSeparator()
             + System.lineSeparator()
             + "**** Sort Order ****" + System.lineSeparator()
             + System.lineSeparator()
             + "sru:sortKeys - space-separated list of sort keys, with individual sort keys comprised of a comma-separated "
             + "sequence of sub-parameters in the order listed below." + System.lineSeparator()
             + "    path - Mandatory. An XPath expression for a tagpath to be used in the sort (wildcards '*' may be supported, see allowed values)" + System.lineSeparator()
             + "    sortSchema - Optional. A short name for a URI identifying an XML schema to which the XPath expression applies" + System.lineSeparator()
             + "    ascending - Optional. Boolean, default 'true'." + System.lineSeparator()
             + "    caseSensitive - Optional. Boolean, default 'false'." + System.lineSeparator()
             + "    missingValue - Optional. Default is 'highValue'." + System.lineSeparator()
             + "            examples: Sort by relevance - score" + System.lineSeparator()
             + "                      Sort by updated time descending - date,,false " + System.lineSeparator()
             + "                      Sort by distance - distance" + System.lineSeparator()
             + "            'path' allowedValues: " + SearchUtils.getAllowedSortValues( sortTypeConfigurationList ) + System.lineSeparator();
                

        
        // @formatter:on
    }

    @Override
    public boolean isValidQuery( MultivaluedMap<String, String> queryParameters ) {
        return true;
    }

    @Override
    public QueryCriteria getQueryCriteria( MultivaluedMap<String, String> queryParameters, QueryConfiguration queryConfig ) throws UnsupportedQueryException {
        LOGGER.debug( "Parsing query using the CDRKeywordQueryLanguage" );
        List<Filter> filters = new ArrayList<Filter>();

        SortBy sortBy = SearchUtils.getSortBy( queryParameters.getFirst( SearchConstants.SORTKEYS_PARAMETER ), sortTypeConfigurationList, true );
        SearchUtils.logSort( sortBy );

        String cqlStr = queryParameters.getFirst( SearchConstants.KEYWORD_PARAMETER );
        LOGGER.debug( "Received CQL-based query [{}]", cqlStr );

        try {
            Filter filter = ECQL.toFilter( cqlStr );
            SearchUtils.addFilter( filters, filter );
        } catch ( CQLException cqlException ) {
            throw new UnsupportedQueryException( "Invalid CQL predicate provided.", cqlException );
        }

        return new CDRQueryCriteriaImpl( filterBuilder.allOf( filters ), sortBy, cqlStr, true, queryParameters, new HashMap<String, String>(), queryConfig );
    }

}
