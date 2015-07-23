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
package net.di2e.ecdr.libs.cache.impl;

import net.di2e.ecdr.api.cache.QueryRequestCache;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryRequestCacheImpl extends MemoryCache<Boolean> implements QueryRequestCache {

    private static final Logger LOGGER = LoggerFactory.getLogger( QueryRequestCacheImpl.class );

    public QueryRequestCacheImpl() {
        super( 1000 );
    }

    public QueryRequestCacheImpl( int cacheSize ) {
        super( cacheSize );
    }

    @Override
    public boolean isQueryIdUnique( String id ) {
        boolean unique = true;
        if ( StringUtils.isNotBlank( id ) ) {
            if ( containsKey( id ) ) {
                unique = false;
            } else {
                add( id );
            }
        }
        LOGGER.debug( "Checking uniqueness of query with oid=[{}] and returning value for isUnique={}", id, unique );
        return unique;
    }

    @Override
    public void setQueryRequestCacheSize( int capacity ) {
        updateCacheSize( capacity );
    }

    @Override
    public void add( String id ) {
        put( id, Boolean.TRUE );
    }

}
