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
package net.di2e.ecdr.search.transform.atom;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.activation.MimeType;

import net.di2e.ecdr.api.security.SecurityConfiguration;
import net.di2e.ecdr.commons.CDRMetacard;

import org.apache.abdera.model.Content;
import org.apache.abdera.model.Entry;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.action.ActionProvider;

public class AtomTransformerWithContent extends AtomTransformer {

    private static final Logger LOGGER = LoggerFactory.getLogger( AtomTransformerWithContent.class );

    public AtomTransformerWithContent( ActionProvider viewMetacardProvider, ActionProvider metadataProvider,
            ActionProvider resourceProvider, ActionProvider thumbnailProvider, MimeType thumbnailMime, MimeType viewMime,
            List<SecurityConfiguration> securityConfig ) {
        super( viewMetacardProvider, metadataProvider, resourceProvider, thumbnailProvider, thumbnailMime, viewMime, securityConfig );
        LOGGER.debug( "Created new AtomTransformerWithContent for returning metadata with atom entry content element populated" );
    }

    @Override
    public void addEntryElements( Entry entry, CDRMetacard metacard, Map<String, Serializable> properties ) {
        // Abdera.getParser() spins up a new thread so must do this
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader( AtomTransformerWithContent.class.getClassLoader() );
            String metadata = metacard.getMetadata();
            if ( StringUtils.isNotEmpty( metadata ) ) {
                entry.setContent( metadata, Content.Type.XML );
                LOGGER.trace( "Setting the Atom Entry content element to the metadata of the Metacard" );
            }
            setEntrySecurity( entry, metacard );
        } finally {
            Thread.currentThread().setContextClassLoader( currentClassLoader );
        }
    }

}
