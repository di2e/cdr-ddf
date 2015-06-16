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

import static org.mockito.Mockito.mock;

import java.util.Collections;

import javax.ws.rs.core.MultivaluedMap;

import net.di2e.ecdr.api.sort.SortTypeConfiguration;
import net.di2e.ecdr.commons.constants.SearchConstants;
import net.di2e.ecdr.commons.query.QueryConfigurationImpl;

import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.junit.Test;

import ddf.catalog.filter.FilterBuilder;
import ddf.catalog.source.UnsupportedQueryException;

public class CDRQueryImplCQLTest {

    private static final String FIELD_CQL = "created <> '2014-01-03'";
    private static final String LIKE_CQL = "title like 'test'";
    private static final String BOOLEAN_CQL = "title = 'test' and author = 'example'";
    private static final String PARENTHESIS_CQL = "country = 'England' and (city = 'London' and created before 2014-05-05T00:00:00) or (city = 'Cambridge' and created between '2014-01-03' and '2014-03-03')";
    private static final String BAD_CQL = "BAD!!!CQL";

    /**
     * Tests that a filter is created that targets a specific field
     *
     * @throws UnsupportedQueryException
     */
    @Test
    public void testField() throws UnsupportedQueryException {
        testQuery( FIELD_CQL );
    }

    /**
     * Tests that a filter is created for a targeted 'like' query
     *
     * @throws UnsupportedQueryException
     */
    @Test
    public void testLike() throws UnsupportedQueryException {
        testQuery( LIKE_CQL );
    }

    /**
     * Tests that a filter is created with a boolean predicate.
     *
     * @throws UnsupportedQueryException
     */
    @Test
    public void testBoolean() throws UnsupportedQueryException {
        testQuery( BOOLEAN_CQL );
    }

    /**
     * Tests that a filter is created with a boolean predicate.
     *
     * @throws UnsupportedQueryException
     */
    @Test(expected = UnsupportedQueryException.class)
    public void testBad() throws UnsupportedQueryException {
        testQuery( BAD_CQL );
    }

    /**
     * Tests that a filter is created from a complex statement that has parenthesis.
     *
     * @throws UnsupportedQueryException
     */
    @Test
    public void testParenthesis() throws UnsupportedQueryException {
        testQuery( PARENTHESIS_CQL );
    }

    private void testQuery( String CQL ) throws UnsupportedQueryException {
        FilterBuilder filterBuilder = mock( FilterBuilder.class );
        CQLQueryLanguage lang = new CQLQueryLanguage( filterBuilder, Collections.<SortTypeConfiguration> emptyList() );
        MultivaluedMap<String, String> queryParameters = new MetadataMap<>();
        queryParameters.add( SearchConstants.KEYWORD_PARAMETER, CQL );
        lang.getQueryCriteria( queryParameters, new QueryConfigurationImpl() );
    }

}
