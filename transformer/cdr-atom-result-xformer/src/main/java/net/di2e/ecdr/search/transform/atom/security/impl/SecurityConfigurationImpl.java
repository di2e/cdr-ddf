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
package net.di2e.ecdr.search.transform.atom.security.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.di2e.ecdr.api.security.SecurityConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityConfigurationImpl implements SecurityConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger( SecurityConfigurationImpl.class );

    private Set<String> formats = Collections.emptySet();
    private String namespace = "urn:us:gov:ic:ism:v2";
    private Map<String, String> attributes = null;

    public void setConfigFormats( List<String> newFormats ) {
        LOGGER.debug( "ConfigUpdate: Updating the Security formats from {} to {}", formats, newFormats );
        formats = new HashSet<>( newFormats );
    }

    public void setNamespace( String newNamespace ) {
        LOGGER.debug( "ConfigUpdate: Updating the Security namespace from [{}] to [{}]", namespace, newNamespace );
        namespace = newNamespace;
    }

    public void setAttributeList( List<String> attrList ) {
        LOGGER.debug( "ConfigUpdate: Updating the Security attribute list from {} to {}", attributes, attrList );
        attributes = new HashMap<String, String>();
        if ( attrList != null ) {
            for ( String attribute : attrList ) {
                String[] values = attribute.split( "=" );
                if ( values.length == 2 ) {
                    attributes.put( values[0], values[1] );
                }
            }
        }
    }

    @Override
    public Set<String> getFormats() {
        return formats;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    @Override
    public Map<String, String> getAttributes() {
        return attributes;
    }
}
