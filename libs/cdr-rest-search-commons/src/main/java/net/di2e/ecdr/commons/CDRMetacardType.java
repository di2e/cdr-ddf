/**
 * Copyright (c) Cohesive Integrations, LLC
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or any later version. 
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details. A copy of the GNU Lesser General Public License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 * 
 **/
package net.di2e.ecdr.commons;

import java.util.HashSet;

import ddf.catalog.data.AttributeDescriptor;
import ddf.catalog.data.Metacard;
import ddf.catalog.data.MetacardType;
import ddf.catalog.data.impl.AttributeDescriptorImpl;
import ddf.catalog.data.impl.BasicTypes;
import ddf.catalog.data.impl.MetacardTypeImpl;

public final class CDRMetacardType {

    public static final MetacardType CDR_METACARD;

    private static final String CDR_NAMESPACE = "cdr.";

    private static final String COLLECTIONS = CDR_NAMESPACE + "collections";

    private static final String POINT_OF_CONTACT = "point-of-contact";

    private static final String RESOURCE_DOWNLOAD_URL = "resource-download-url";

    static {
        MetacardType cdr = null;
        HashSet<AttributeDescriptor> descriptors = new HashSet<AttributeDescriptor>();
        descriptors.add( new AttributeDescriptorImpl( Metacard.MODIFIED, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.DATE_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( Metacard.EXPIRATION, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.DATE_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( Metacard.EFFECTIVE, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.DATE_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( Metacard.CREATED, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.DATE_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( Metacard.ID, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( Metacard.TITLE, true /* indexed */, true /* stored */, true /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( POINT_OF_CONTACT, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( Metacard.CONTENT_TYPE, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );
        descriptors
                .add( new AttributeDescriptorImpl( Metacard.CONTENT_TYPE_VERSION, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( Metacard.TARGET_NAMESPACE, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( Metacard.METADATA, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.XML_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( Metacard.RESOURCE_URI, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( RESOURCE_DOWNLOAD_URL, false /* indexed */, false /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( Metacard.RESOURCE_SIZE, false /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( Metacard.THUMBNAIL, false /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.BINARY_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( Metacard.GEOGRAPHY, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.GEO_TYPE ) );

        cdr = new MetacardTypeImpl( "cdr.metacard", descriptors );

        descriptors.add( new AttributeDescriptorImpl( COLLECTIONS, true /* indexed */, false /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );

        descriptors.add( new AttributeDescriptorImpl( CDRMetacard.METADATA_LINK, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( CDRMetacard.WRAP_METADATA, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.BOOLEAN_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( CDRMetacard.RESOURCE_TITLE, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );
        descriptors
                .add( new AttributeDescriptorImpl( CDRMetacard.RESOURCE_MIME_TYPE, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );

        descriptors.add( new AttributeDescriptorImpl( CDRMetacard.THUMBNAIL_LINK, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );
        descriptors.add( new AttributeDescriptorImpl( CDRMetacard.THUMBNAIL_LINK_TITLE, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */,
                BasicTypes.STRING_TYPE ) );
        descriptors
                .add( new AttributeDescriptorImpl( CDRMetacard.THUMBNAIL_LENGTH, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );
        descriptors
                .add( new AttributeDescriptorImpl( CDRMetacard.THUMBNAIL_MIMETYPE, true /* indexed */, true /* stored */, false /* tokenized */, false /* multivalued */, BasicTypes.STRING_TYPE ) );

        CDR_METACARD = cdr;
    }

    private CDRMetacardType() {
    }

}
