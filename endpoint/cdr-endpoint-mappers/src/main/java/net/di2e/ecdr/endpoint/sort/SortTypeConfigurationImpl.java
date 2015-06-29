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
package net.di2e.ecdr.endpoint.sort;

import net.di2e.ecdr.api.config.SortTypeConfiguration;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortTypeConfigurationImpl implements SortTypeConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger( SortTypeConfigurationImpl.class );

    private String sortKey;

    private String sortAttribute;

    private String customAttribute;

    private String sortOrder;

    public SortTypeConfigurationImpl() {
        LOGGER.info( "Creating a new sort type configuration." );
    }

    public void setSortKey( String key ) {
        LOGGER.debug( "ConfigUpdate: Updating the SortKey to [{}] from [{}]", key, sortKey );
        this.sortKey = key;
    }

    public void setSortAttribute( String attribute ) {
        if ( StringUtils.isNotBlank( attribute ) ) {
            LOGGER.debug( "ConfigUpdate: Updating the sortAttribute to [{}] from [{}]", attribute, sortAttribute );
            this.sortAttribute = attribute;
        } else {
            LOGGER.debug( "ConfigUpdateError: Configuration update for SortAttribute in sortMapper for key=[{}] was empty or null so leaving at existing value for [{}]", sortKey, sortAttribute );
        }
    }

    public void setCustomSortAttribute( String attribute ) {
        LOGGER.debug( "ConfigUpdate: Updating the customSortAttribute to [{}] from [{}]", attribute, customAttribute );
        this.customAttribute = attribute;
    }

    public void setSortOrder( String order ) {
        if ( StringUtils.isNotBlank( order ) ) {
            LOGGER.debug( "ConfigUpdate: Updating the SortOrder to [{}] from [{}]", order, sortOrder );
            this.sortOrder = order;
        } else {
            LOGGER.debug( "ConfigUpdateError: Configuration update for SortOrder in sortMapper for key=[{}] was empty or null so leaving at existing value for [{}]", sortKey, sortOrder );
        }
    }

    @Override
    public String getSortKey() {
        return sortKey;
    }

    @Override
    public String getSortAttribute() {
        if ( StringUtils.isNotBlank( customAttribute ) ) {
            return customAttribute;
        } else {
            return sortAttribute;
        }
    }

    @Override
    public String getSortOrder() {
        return sortOrder;
    }

}
