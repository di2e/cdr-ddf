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

import net.di2e.ecdr.api.config.DateTypeConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTypeConfigurationImpl implements DateTypeConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger( DateTypeConfigurationImpl.class );

    private String cdrDateType;
    private String internalDateType;

    public DateTypeConfigurationImpl() {
        LOGGER.debug( "Creating a new date type configuration." );
    }

    public void setCdrDateType( String type ) {
        LOGGER.debug( "ConfigUpdate: Updating the cdrDateType Value to [{}] from [{}]", type, cdrDateType );
        this.cdrDateType = type;
    }

    public void setInternalDateType( String type ) {
        LOGGER.debug( "ConfigUpdate: Updating the internalDateType to [{}] from [{}]", type, internalDateType );
        this.internalDateType = type;
    }


    @Override
    public String getCDRDateType() {
        return cdrDateType;
    }

    @Override
    public String getInternalDateType() {
        return internalDateType;
    }
}
