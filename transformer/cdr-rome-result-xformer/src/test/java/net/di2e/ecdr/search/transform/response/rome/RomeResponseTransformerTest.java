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
package net.di2e.ecdr.search.transform.response.rome;

import ddf.catalog.operation.QueryRequest;
import ddf.catalog.operation.SourceResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class RomeResponseTransformerTest {

    private static final String SITE_NAME = "testSite";

    private static final int RESULT_NUM = 10;

    @Test
    public void testSharepointConversion() throws IOException {
        RomeResponseTransformer responseTransformer = new RomeResponseTransformer();
        QueryRequest queryRequest = mock( QueryRequest.class );
        SourceResponse response = responseTransformer.processSearchResponse( getClass().getResourceAsStream( "/sharepoint-rss.xml" ), queryRequest, SITE_NAME );
        assertNotNull(response);
        assertEquals(RESULT_NUM, response.getHits());
        assertNotNull(response.getResults());
        assertEquals(RESULT_NUM, response.getResults().size());
    }

}
