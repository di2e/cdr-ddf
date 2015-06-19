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

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimeType;

import net.di2e.ecdr.api.security.SecurityConfiguration;
import net.di2e.ecdr.commons.CDRMetacard;
import net.di2e.ecdr.commons.constants.SearchConstants;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Entry;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codice.ddf.configuration.impl.ConfigurationWatcherImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.action.ActionProvider;
import ddf.catalog.Constants;
import ddf.catalog.data.BinaryContent;
import ddf.catalog.data.Metacard;
import ddf.catalog.transform.CatalogTransformerException;
import ddf.catalog.transform.MetacardTransformer;

public class AtomTransformerWithPayload extends AtomTransformer {

    private static final String TRANSFORMER_ID = "atom-with-payload";

    private static final Logger LOGGER = LoggerFactory.getLogger( AtomTransformerWithPayload.class );

    private Map<String, MetacardTransformer> metacardTransformerMap = null;

    public AtomTransformerWithPayload( ConfigurationWatcherImpl configWatcher, ActionProvider viewMetacardProvider, ActionProvider metadataProvider,
            ActionProvider resourceProvider, ActionProvider thumbnailProvider, MimeType thumbnailMime, MimeType viewMime,
            List<SecurityConfiguration> securityConfig ) {
        super( configWatcher, viewMetacardProvider, metadataProvider, resourceProvider, thumbnailProvider, thumbnailMime, viewMime, securityConfig );
        metacardTransformerMap = new HashMap<String, MetacardTransformer>();
    }

    @Override
    public void addEntryElements( Entry entry, CDRMetacard metacard, Map<String, Serializable> properties ) {
        // Abdera.getParser() spins up a new thread so must do this
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader( AtomTransformerWithPayload.class.getClassLoader() );
            String metadata = getMetadataXML( metacard, (String) properties.get( SearchConstants.METACARD_TRANSFORMER_NAME ) );
            entry.addExtension( Abdera.getNewParser().parse( new StringReader( metadata ) ).getRoot() );
            CDRMetacard newMetacard = new CDRMetacard( metacard );
            newMetacard.setMetadata( metadata );

            super.setEntrySecurity( entry, newMetacard );
        } finally {
            Thread.currentThread().setContextClassLoader( currentClassLoader );
        }
    }

    /**
     * Method responsible for getting the metadata XML String that is associated
     * with the Metacard. The metadata is retrieved by calling the
     * MetadataTransformer that is registered with the id that matches the
     * format String that is passed into the method. If a MetacardTransformer
     * doesn't exist, or the result of the Transform is not XML, or if there is
     * an error while transforming, the Metacard.METADATA will be returned
     * 
     * @param metacard
     *            the Metacard to get the Metacard from
     * @param format
     *            the format of the MetacardTransformer to use (which is looked
     *            up by MetacardTransformer id)
     * @return the XML String
     */
    protected String getMetadataXML( Metacard metacard, String format ) {
        String metadata = null;
        LOGGER.debug( "Getting metadata to include in atom response in the format [{}] from metacardTransformerMap {}", format, metacardTransformerMap.keySet() );
        if ( StringUtils.isNotBlank( format ) ) {
            MetacardTransformer metacardTransformer = metacardTransformerMap.get( format );
            if ( metacardTransformer != null ) {
                try {
                    LOGGER.debug( "Calling the MetacardTransformer with id [{}] to transform the Metacard into XML Metadata", format );
                    BinaryContent binaryContent = metacardTransformer.transform( metacard, null );
                    String mimeValue = binaryContent.getMimeTypeValue();
                    if ( StringUtils.isNotBlank( mimeValue ) && mimeValue.contains( "xml" ) ) {
                        try ( InputStream inputStream = binaryContent.getInputStream() ) {
                            metadata = IOUtils.toString( inputStream );
                        } catch ( IOException e ) {
                            LOGGER.warn( "Error while writing transformed Metacard into a String: " + e.getMessage(), e );
                        }
                    }
                } catch ( CatalogTransformerException e ) {
                    LOGGER.warn( "Error while transforming metacard using the [{}] MetacardTransformer", format );
                    LOGGER.warn( e.getMessage(), e );
                }
            }
        }
        if ( metadata == null ) {
            LOGGER.debug(
                    "A MetacardTransform didn't exist for format [{}] or ran into problems when transforming Metacard, so falling back to using the Metadata in the Metacard",
                    format );
            metadata = metacard.getMetadata();

        }
        LOGGER.trace( "Atom Transformer setting Metacard.METADATA to:{}{}", System.lineSeparator(), metadata );
        return metadata;
    }

    /**
     * Method called by the OSGi container, managed by blueprint whenever a new
     * MetacardTransformer service is exposed to the OSGi Registry
     * 
     * @param transformer
     *            the MetacardTransformer that was added
     * @param map
     *            the service properties for the corresponding
     *            MetacardTransformer
     */
    public void metacardTransformerAdded( MetacardTransformer transformer, Map<String, Object> map ) {
        String id = (String) map.get( Constants.SERVICE_ID );
        // We need to filter out this transformer from being included in the
        // Metacard Transformers used to produce metadata records
        if ( !StringUtils.equalsIgnoreCase( id, TRANSFORMER_ID ) ) {
            metacardTransformerMap.put( id, transformer );
            LOGGER.debug( "Adding MetacardTransformer with id [{}] to transformer map.", id );
        }
    }

    /**
     * Method is called when a MetacardTransformer is removed from the OSGi
     * registry (called by OSGi container, managed by blueprint)
     * 
     * @param transformer
     *            the MetacardTransformer service that was removed
     * @param map
     *            the service properties for the corresponding
     *            MetacardTransformer
     */
    public void metacardTransformerRemoved( MetacardTransformer transformer, Map<String, Object> map ) {
        String id = (String) map.get( Constants.SERVICE_ID );
        metacardTransformerMap.remove( id );
        LOGGER.debug( "Removing MetacardTransformer with id [{}] from transformer map.", id );
    }
}
