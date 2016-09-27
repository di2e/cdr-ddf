/**
 * Copyright (C) 2016 Pink Summit, LLC (info@pinksummit.com)
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
package net.di2e.ecdr.describe.publisher;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.util.ThreadContext;
import org.codice.ddf.security.common.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.catalog.CatalogFramework;
import ddf.catalog.data.Metacard;
import ddf.catalog.operation.CreateResponse;
import ddf.catalog.operation.impl.CreateRequestImpl;
import ddf.catalog.source.IngestException;
import ddf.catalog.source.SourceUnavailableException;
import ddf.security.Subject;

public class DescribePublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger( DescribePublisher.class );

    private CatalogFramework framework = null;

    public DescribePublisher( CatalogFramework fw ) {
        framework = fw;
    }

    public void publish( Collection<Metacard> describeMetacards ) {
        ThreadContext.bind( getSystemSubject() );
        CreateRequestImpl create = new CreateRequestImpl( Arrays.asList( describeMetacards.toArray( new Metacard[describeMetacards.size()] ) ), null );

        try {
            CreateResponse response = framework.create( create );
            LOGGER.debug( "Published {} describe metacards successfully with tag 'describe'", response.getCreatedMetacards().size() );

        } catch ( IngestException | SourceUnavailableException e ) {
            LOGGER.error( "Publishing describe records to catalog failed", e );
        }

    }

    protected Subject getSystemSubject() {
        return org.codice.ddf.security.common.Security.runAsAdmin( () -> Security.getInstance().getSystemSubject() );
    }

}
