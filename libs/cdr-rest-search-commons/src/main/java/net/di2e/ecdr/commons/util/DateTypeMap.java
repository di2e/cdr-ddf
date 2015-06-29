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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.di2e.ecdr.api.config.DateTypeConfiguration;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class DateTypeMap {

    private Map<String, DateTypeConfiguration> dateMap = null;
    private BundleContext context = null;

    public DateTypeMap( BundleContext bc ) {
        dateMap = new HashMap<>();
        context = bc;
    }

    public DateTypeConfiguration getConfiguration( String key ) {
        return dateMap.get( key );
    }

    public String getMappedValue( String key ) {
        DateTypeConfiguration config = dateMap.get( key );
        if ( config != null ) {
            return config.getInternalDateType();
        }
        return null;
    }

    public void addConfiguration( ServiceReference<DateTypeConfiguration> ref ) {
        DateTypeConfiguration config = context.getService( ref );
        dateMap.put( config.getCDRDateType(), config );
    }

    public void removeConfiguration( ServiceReference<DateTypeConfiguration> ref ) {
        DateTypeConfiguration config = context.getService( ref );
        dateMap.remove( config.getCDRDateType() );
    }

    public Set<String> keySet() {
        return dateMap.keySet();
    }

    public boolean containsKey( String key ) {
        return dateMap.containsKey( key );
    }

}
