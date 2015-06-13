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
package net.di2e.ecdr.commons.util;

import java.io.IOException;

import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigAdminUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger( ConfigAdminUtils.class );

    private ConfigAdminUtils() {
    }

    public static boolean configurationPidExists( ConfigurationAdmin configAdmin, String pid ) throws IOException, InvalidSyntaxException {
        boolean configExists = false;
        String filter = "(" + ConfigurationAdmin.SERVICE_FACTORYPID + "=" + pid + ")";
        Configuration[] config = configAdmin.listConfigurations( filter );
        if ( config != null && config.length > 0 ) {
            configExists = true;
        }
        LOGGER.debug( "The {} configuration returned {} services so return {} for configExists method", pid, config == null ? 0 : config.length, configExists );
        return configExists;
    }

}
