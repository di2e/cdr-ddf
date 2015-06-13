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
package net.di2e.ecdr.commons.filter;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import net.di2e.ecdr.commons.constants.SearchConstants;
import net.di2e.ecdr.commons.filter.config.FilterConfig;

import org.geotools.filter.text.cql2.CQL;
import org.junit.Test;

import ddf.catalog.filter.proxy.adapter.GeotoolsFilterAdapterImpl;
import ddf.catalog.operation.impl.QueryImpl;

public class StrictFilterDelegateTest {

    @Test
    public void testFilterDelegate() throws Exception {
        GeotoolsFilterAdapterImpl filterAdapter = new GeotoolsFilterAdapterImpl();
        QueryImpl query = new QueryImpl( CQL.toFilter("q like 'test' AND (created before 2014-05-05T00:00:00 AND created after 2014-06-05T00:00:00) OR created after 2014-07-05T00:00:00") );
        Map<String, String> filterParameters = filterAdapter.adapt( query, new StrictFilterDelegate( false, 50000.00, new FilterConfig() ) );
        assertEquals("test", filterParameters.get( SearchConstants.KEYWORD_PARAMETER ));
    }

}
