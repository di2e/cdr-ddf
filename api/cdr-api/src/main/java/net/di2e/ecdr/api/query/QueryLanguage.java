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
package net.di2e.ecdr.api.query;

import javax.ws.rs.core.MultivaluedMap;

import ddf.catalog.source.UnsupportedQueryException;

public interface QueryLanguage {

    String DEFAULT_FUZZY_SEARCH = "default-fuzzy-search";
    String DEFAULT_RADIUS = "default-radius";
    String DEFAULT_DATE_TYPE = "default-date-type";

    QueryCriteria getQueryCriteria( MultivaluedMap<String, String> queryParameters, QueryConfiguration queryConfig ) throws UnsupportedQueryException;

    boolean isValidQuery( MultivaluedMap<String, String> queryParameters );

    String getName();

    String getLanguageDescription( QueryConfiguration queryConfig );

    String getUrlTemplateParameters();

}
