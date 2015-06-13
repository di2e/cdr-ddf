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
package net.di2e.ecdr.commons.xml;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import net.di2e.ecdr.commons.xml.fs.Link;
import net.di2e.ecdr.commons.xml.fs.Rel;
import net.di2e.ecdr.commons.xml.fs.SourceDescription;
import net.di2e.ecdr.commons.xml.osd.OpenSearchDescription;
import net.di2e.ecdr.commons.xml.osd.Query;
import net.di2e.ecdr.commons.xml.osd.SyndicationRight;

import org.junit.Test;

import ddf.catalog.CatalogFramework;

public class OpenSearchDescriptionTest {

    /**
     * Verify that JAXB object can be created and marshalled without exceptions.
     * @throws Exception
     */
    @Test
    public void testCreation() throws Exception {
        OpenSearchDescription osd = new OpenSearchDescription();
        CatalogFramework framework = null;
        osd.setShortName( "ECDR Opensearch" );
        osd.setDescription( "Opensearch endpoint that conforms to the Enterprise CDR specifications." );
        osd.setTags( "ecdr opensearch cdr ddf" );
        osd.setContact( "test@example.org" );
        osd.setDeveloper( "ECDR" );
        Query query = new Query();
        query.setRole( "example" );
        query.setSearchTerms( "test" );
        osd.getQuery().add( query );
        osd.setSyndicationRight( SyndicationRight.OPEN );
        osd.setAdultContent( false );
        osd.getLanguage().add( Locale.US.getLanguage() );
        osd.getInputEncoding().add( StandardCharsets.UTF_8.name() );
        osd.getOutputEncoding().add( StandardCharsets.UTF_8.name() );

        // federation type
        SourceDescription description = new SourceDescription();
        description.setShortName( "site1" );
        description.setDescription( "This is a federated site." );
        Link link = new Link();
        link.setHref( "http://example.com/description.xml" );
        link.setRel( Rel.SELF );
        link.setType( "text/xml" );
        description.setLink( link );

        osd.getAny().add( description );

        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance( OpenSearchDescription.class, SourceDescription.class );
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        marshaller.marshal(osd, writer);
    }


}
