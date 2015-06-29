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
package net.di2e.ecdr.endpoint.date;

import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import net.di2e.ecdr.commons.util.ConfigAdminUtils;

import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.catalog.data.Metacard;

public class DefaultDateTypeConfigurations {

    private static final String MAPPING_PID = "cdr-endpoint-date-mapping";

    private static final Logger LOGGER = LoggerFactory.getLogger( DefaultDateTypeConfigurations.class );

    private static final Map<String, String> DATETYPE_MAP = new HashMap<String, String>();

    static {
        DATETYPE_MAP.put( "created", Metacard.CREATED );
        DATETYPE_MAP.put( "effective", Metacard.EFFECTIVE );
        DATETYPE_MAP.put( "published", Metacard.EFFECTIVE );
        DATETYPE_MAP.put( "updated", Metacard.MODIFIED );
    }

    private ConfigurationAdmin configAdmin;

    public DefaultDateTypeConfigurations( ConfigurationAdmin configurationAdmin ) {
        configAdmin = configurationAdmin;
    }

    public void init() throws IOException, InvalidSyntaxException {
        if ( !ConfigAdminUtils.configurationPidExists( configAdmin, MAPPING_PID ) ) {
            for ( Entry<String, String> entry : DATETYPE_MAP.entrySet() ) {
                LOGGER.debug( "Adding configuration for date type with mapping {}={}", entry.getKey(), entry.getValue() );
                Configuration configuration = configAdmin.createFactoryConfiguration( MAPPING_PID );
                Dictionary<String, String> properties = new Hashtable<>();
                properties.put( "cdrDateType", entry.getKey() );
                properties.put( "internalDateType", entry.getValue() );
                configuration.update( properties );
            }
        }
    }

}
