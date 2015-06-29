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
package net.di2e.ecdr.source.rest;

import java.util.Arrays;

import net.di2e.ecdr.api.cache.CacheManager;
import net.di2e.ecdr.commons.filter.config.AtomSearchResponseTransformerConfig;
import net.di2e.ecdr.libs.cache.impl.MetacardMemoryCacheManager;

import org.junit.Test;

import ddf.catalog.data.Metacard;

public class CDROpenSearchSourceTest extends CDRAbstractSourceTest {

    private CacheManager<Metacard> cacheManager = new MetacardMemoryCacheManager();

    @Override
    CDROpenSearchSource createSource() {
        CDROpenSearchSource source = new CDROpenSearchSource( FILTER_ADAPTER, cacheManager );
        source.setCacheExpirationMinutes( new Long(1) );
        source.setParameterMap( Arrays.asList( new String[] {"os:searchTerms=q","os:count=count","os:startIndex=startIndex","time:start=dtStart","time:end=dtEnd",
                "geo:uid=blah","geo:box=box","geo:lat=lat","geo:lon=lon","geo:radius=radius","geo:geometry=geometry","sru:sortKeys=sortKeys" } ) );
        source.setHttpHeaders( Arrays.asList( new String[] { "EMID" } ) );
        source.setStartIndexStartNumber( "1" );
        source.setMetadataLinkRelation( "alternate" );
        source.setProductLinkRelation( "enclosure" );
        source.setThumbnailLinkRelation( "preview" );
        source.setProxyProductUrls( true );
        source.setWrapContentWithXmlOption( AtomSearchResponseTransformerConfig.AtomContentXmlWrapOption.NEVER_WRAP.toString() );
        source.setHardCodedParameters( Arrays.asList( new String[] { "test=example" } ) );
        return source;
    }

    @Test
    public void testCleanUp() {
        CacheManager<Metacard> caches = new MetacardMemoryCacheManager();
        CDROpenSearchSource source = new CDROpenSearchSource( FILTER_ADAPTER, caches );
        source.cleanUp();
    }

}
