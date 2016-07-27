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
import net.di2e.ecdr.commons.constants.SearchConstants;
import net.di2e.ecdr.search.transform.atom.constants.AtomResponseConstants;

import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Link;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.action.Action;
import ddf.action.ActionProvider;

public class AtomOGCTransformer extends AtomTransformer {

    private static final Logger LOGGER = LoggerFactory.getLogger( AtomTransformerWithContent.class );

    public AtomOGCTransformer( ActionProvider viewMetacardProvider, ActionProvider metadataProvider, ActionProvider resourceProvider,
            ActionProvider thumbnailProvider, MimeType thumbnailMime, MimeType viewMime, List<SecurityConfiguration> securityConfig ) {
        super( viewMetacardProvider, metadataProvider, resourceProvider, thumbnailProvider, thumbnailMime, viewMime, securityConfig );
        LOGGER.debug( "Created new AtomOGCTransformer for returning metadata with atom entry content element populated" );
    }

    protected void addLinksToEntry( Entry entry, CDRMetacard metacard, String format, Map<String, Serializable> properties ) {
        if ( metacard.hasThumbnail() ) {

            if ( getThumbnailActionProvider() != null ) {
                Action action = getThumbnailActionProvider().getAction( metacard );
                if ( action != null && action.getUrl() != null ) {
                    entry.addLink( action.getUrl().toString(), SearchConstants.LINK_REL_ICON, getThumbnailMimeType().getBaseType(), action.getTitle(), null, metacard.getThumbnailLength() );
                }
            }
        }
        if ( getResourceActionProvider() != null && metacard.hasResource() ) {
            Action action = getResourceActionProvider().getAction( metacard );
            if ( action != null && action.getUrl() != null ) {
                entry.addLink( action.getUrl().toString(), Link.REL_ENCLOSURE, metacard.getResourceMIMETypeString(), action.getTitle(), null, metacard.getResourceSizeLong() );
            }
            // If there is no explicit resource then the metadata serves as
            // the product/resource so include a link to it here
        } else if ( getMetadataActionProvider() != null ) {

            Action action = getMetadataActionProvider().getAction( metacard );
            if ( action != null && action.getUrl() != null ) {
                entry.addLink( action.getUrl().toString(), Link.REL_ENCLOSURE, "text/xml", "View Product", null, -1 );
            }
        }
        if ( getViewMetacardActionProvider() != null ) {
            Action action = getViewMetacardActionProvider().getAction( metacard );
            if ( action != null && action.getUrl() != null ) {
                String transformFormat = (String) properties.get( SearchConstants.METACARD_TRANSFORMER_NAME );
                if ( StringUtils.isBlank( transformFormat ) ) {
                    transformFormat = StringUtils.defaultIfBlank( format, CDR_ATOM_TRANSFORMER_ID );
                }
                entry.addLink( action.getUrl().toString() + "?transform=" + transformFormat, Link.REL_SELF, AtomResponseConstants.ATOM_MIME_TYPE, "View Atom Entry", null, -1 );
                entry.addLink( action.getUrl().toString(), Link.REL_ALTERNATE, "text/xml", action.getTitle(), null, -1 );
            }
        }
    }

}
