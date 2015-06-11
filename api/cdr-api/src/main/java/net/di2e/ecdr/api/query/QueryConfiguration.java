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

import java.util.List;
import java.util.Map;

public interface QueryConfiguration {

    int getDefaultCount();

    long getDefaultTimeoutMillis();

    String getDefaultDateType();

    double getDefaultRadius();

    String getDefaultResponseFormat();

    boolean isDefaultFuzzySearch();

    boolean isDefaultDeduplication();

    Map<String, String> getParameterExtensionMap();

    List<String> getParameterPropertyList();

    String getDefaultQueryLanguage();

    int getQueryRequestCacheSize();

}
