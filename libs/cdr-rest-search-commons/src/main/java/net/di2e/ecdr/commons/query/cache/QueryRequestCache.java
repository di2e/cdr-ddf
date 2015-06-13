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
package net.di2e.ecdr.commons.query.cache;

import java.util.LinkedHashMap;
import java.util.Map;

import net.di2e.ecdr.commons.constants.SearchConstants;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryRequestCache {

    private static final Logger LOGGER = LoggerFactory.getLogger( QueryRequestCache.class );

    private LRUCache<String, Boolean> cache = null;

    public QueryRequestCache( int cacheSize ) {
        cache = new LRUCache<String, Boolean>( cacheSize );
    }

    public boolean isQueryIdUnique( String id ) {
        boolean unique = true;
        if ( StringUtils.isNotBlank( id ) ) {
            if ( cache.containsKey( id ) ) {
                unique = false;
            } else {
                cache.put( id, Boolean.TRUE );
            }
        }
        LOGGER.debug( "Checking uniqueness of query with {}={} and isUnique={}", SearchConstants.OID_PARAMETER, id, unique );
        return unique;
    }

    public void updateCacheSize( int capacity ) {
        cache.updateCacheSize( capacity );
    }

    public void add( String id ) {
        cache.put( id, Boolean.TRUE );
    }

    private static final class LRUCache<K, V> extends LinkedHashMap<K, V> {

        private static final long serialVersionUID = 1L;
        private int cacheSize = 0;

        public LRUCache( int capacity ) {
            super( capacity + 1, 1.1f, true );
            this.cacheSize = capacity;
        }

        @Override
        protected boolean removeEldestEntry( Map.Entry<K, V> eldest ) {
            return size() > cacheSize;
        }

        @Override
        public V put( K k, V v ) {
            if ( cacheSize > 0 ) {
                return super.put( k, v );
            }
            return null;
        }

        public void updateCacheSize( int size ) {
            this.clear();
            cacheSize = size;
        }

    }

}
