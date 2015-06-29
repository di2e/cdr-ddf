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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.di2e.ecdr.api.config.SortTypeConfiguration;

import org.junit.Test;
import org.opengis.filter.sort.SortBy;
import org.opengis.filter.sort.SortOrder;

import ddf.catalog.data.Metacard;

public class SearchUtilsTest {

    private static final String MAP_STRING = "1=2,2=3,3=4";
    private static final List<String> MAP_LIST = Arrays.asList(MAP_STRING.split( "," ));

    @Test
    public void testConvertStringToMap() {
        Map<String, String> map = SearchUtils.convertToMap( MAP_STRING );
        assertEquals(3, map.size());
        assertTrue(map.containsKey( "1" ));
        assertTrue(map.containsKey( "2" ));
        assertTrue(map.containsKey( "3" ));
        assertEquals("2", map.get( "1" ));
        assertEquals("3", map.get( "2" ));
        assertEquals("4", map.get( "3" ));
    }

    @Test
    public void testConvertListToMap() {
        Map<String, String> map = SearchUtils.convertToMap( MAP_LIST );
        assertEquals(3, map.size());
        assertTrue(map.containsKey( "1" ));
        assertTrue(map.containsKey( "2" ));
        assertTrue(map.containsKey( "3" ));
        assertEquals("2", map.get( "1" ));
        assertEquals("3", map.get( "2" ));
        assertEquals("4", map.get( "3" ));
    }

    @Test
    public void testIsBoolean() {
        assertTrue( SearchUtils.isBoolean( Boolean.TRUE.toString() ) );
        assertTrue( SearchUtils.isBoolean( "1" ) );
    }

    @Test
    public void testGetSortBy(){
        SortTypeConfiguration sortConfig = new SortTypeConfiguration() {
            @Override
            public String getSortOrder() {
                return SortOrder.ASCENDING.name();
            }
            
            @Override
            public String getSortKey() {
                return "*/title";
            }
            
            @Override
            public String getSortAttribute() {
                return Metacard.TITLE;
            }
        };
        SortBy sortBy = SearchUtils.getSortBy( "title", Arrays.asList( new SortTypeConfiguration[] { sortConfig } ), false );
        assertNull( sortBy );

        sortBy = SearchUtils.getSortBy( "title", Arrays.asList( new SortTypeConfiguration[] { sortConfig } ), true );
        assertEquals( sortBy.getSortOrder().name(), SortOrder.ASCENDING.name() );
        assertEquals( sortBy.getPropertyName().getPropertyName(), Metacard.TITLE );

        sortBy = SearchUtils.getSortBy( "entry/title", Arrays.asList( new SortTypeConfiguration[] { sortConfig } ), true );
        assertEquals( sortBy.getSortOrder().name(), SortOrder.ASCENDING.name() );
        assertEquals( sortBy.getPropertyName().getPropertyName(), Metacard.TITLE );

        sortBy = SearchUtils.getSortBy( "entry/blah/title", Arrays.asList( new SortTypeConfiguration[] { sortConfig } ), true );
        assertEquals( sortBy.getSortOrder().name(), SortOrder.ASCENDING.name() );
        assertEquals( sortBy.getPropertyName().getPropertyName(), Metacard.TITLE );

        sortConfig = new SortTypeConfiguration() {
            @Override
            public String getSortOrder() {
                return SortOrder.ASCENDING.name();
            }

            @Override
            public String getSortKey() {
                return "entry/title";
            }

            @Override
            public String getSortAttribute() {
                return Metacard.TITLE;
            }
        };
        sortBy = SearchUtils.getSortBy( "title", Arrays.asList( new SortTypeConfiguration[] { sortConfig } ), true );
        assertNull( sortBy );
        sortBy = SearchUtils.getSortBy( "entry/blah/title", Arrays.asList( new SortTypeConfiguration[] { sortConfig } ), true );
        assertNull( sortBy );
        sortBy = SearchUtils.getSortBy( "entry/title", Arrays.asList( new SortTypeConfiguration[] { sortConfig } ), false );
        assertEquals( sortBy.getSortOrder().name(), SortOrder.ASCENDING.name() );
        assertEquals( sortBy.getPropertyName().getPropertyName(), Metacard.TITLE );
        sortBy = SearchUtils.getSortBy( "entry/title", Arrays.asList( new SortTypeConfiguration[] { sortConfig } ), true );
        assertEquals( sortBy.getSortOrder().name(), SortOrder.ASCENDING.name() );
        assertEquals( sortBy.getPropertyName().getPropertyName(), Metacard.TITLE );
    }
}
