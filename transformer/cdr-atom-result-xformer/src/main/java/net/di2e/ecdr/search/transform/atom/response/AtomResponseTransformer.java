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
package net.di2e.ecdr.search.transform.atom.response;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.activation.MimeType;
import javax.xml.namespace.QName;

import net.di2e.ecdr.commons.CDRMetacard;
import net.di2e.ecdr.commons.CDRMetacardType;
import net.di2e.ecdr.commons.filter.config.FilterConfig;
import net.di2e.ecdr.commons.filter.config.FilterConfig.AtomContentXmlWrapOption;
import net.di2e.ecdr.commons.response.SearchResponseTransformer;
import net.di2e.ecdr.search.transform.atom.constants.AtomResponseConstants;
import net.di2e.ecdr.search.transform.atom.geo.AbderaConverter;
import net.di2e.ecdr.search.transform.atom.response.security.SecurityMarkingParser;

import org.apache.abdera.Abdera;
import org.apache.abdera.ext.geo.Position;
import org.apache.abdera.ext.opensearch.OpenSearchConstants;
import org.apache.abdera.i18n.iri.IRI;
import org.apache.abdera.model.Category;
import org.apache.abdera.model.Content;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.apache.abdera.parser.Parser;
import org.apache.commons.lang.StringUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.catalog.data.Metacard;
import ddf.catalog.data.Result;
import ddf.catalog.data.impl.ResultImpl;
import ddf.catalog.operation.QueryRequest;
import ddf.catalog.operation.SourceResponse;
import ddf.catalog.operation.impl.SourceResponseImpl;

public class AtomResponseTransformer implements SearchResponseTransformer {

    private static final transient Logger LOGGER = LoggerFactory.getLogger( AtomResponseTransformer.class );
    private static final String METADATA_ELEMENT_NAME = "Resource";

    private static final DateTimeFormatter DATE_FORMATTER = ISODateTimeFormat.dateTimeParser();

    private static final Abdera ABDERA = Abdera.getInstance();

    private FilterConfig filterConfig = null;

    public AtomResponseTransformer( FilterConfig config ) {
        this.filterConfig = config;
    }

    @Override
    public SourceResponse processSearchResponse( InputStream inputStream, QueryRequest request, String siteName ) {
        List<Result> resultList = new ArrayList<Result>();

        ClassLoader tccl = Thread.currentThread().getContextClassLoader();
        Parser parser = null;
        Document<Feed> atomDoc;
        try {

            Thread.currentThread().setContextClassLoader( AtomResponseTransformer.class.getClassLoader() );
            parser = ABDERA.getParser();
            atomDoc = parser.parse( new InputStreamReader( inputStream ) );

        } finally {
            Thread.currentThread().setContextClassLoader( tccl );
        }

        Feed feed = atomDoc.getRoot();

        List<Entry> entries = feed.getEntries();
        int size = entries.size();
        for ( Entry entry : entries ) {
            if ( isValidEntry( entry ) ) {
                Metacard metacard = entryToMetacard( entry, siteName );
                resultList.add( metacardToResult( entry, metacard ) );
            } else {
                LOGGER.debug( "Skipping invalid entry: {}", entry );
                size--;
            }
        }

        long totalResults = size;
        Element totalResultsElement = atomDoc.getRoot().getExtension( OpenSearchConstants.TOTAL_RESULTS );

        if ( totalResultsElement != null ) {
            try {
                totalResults = Long.parseLong( totalResultsElement.getText() );
            } catch ( NumberFormatException e ) {
                LOGGER.warn( "Received invalid number of results from Atom response [" + totalResultsElement.getText() + "]", e );
            }
        }

        Map<String, Serializable> responseProperties = null;

        return new SourceResponseImpl( request, responseProperties, resultList, totalResults );
    }

    private Metacard entryToMetacard( Entry entry, String siteName ) {
        CDRMetacard metacard = new CDRMetacard( CDRMetacardType.CDR_METACARD );

        String id = entry.getIdElement().getText();
        // id may be formatted catalog:id:<id>, so we parse out the <id>
        if ( StringUtils.isNotBlank( id ) && (id.startsWith( "urn:uuid:" ) || id.startsWith( "urn:catalog:id:" )) ) {
            id = id.substring( id.lastIndexOf( ':' ) + 1 );
        }
        metacard.setId( id );

        System.out.println( "Setting source " + siteName );
        metacard.setSourceId( siteName );

        List<Category> categories = entry.getCategories();
        if ( categories != null && !categories.isEmpty() ) {
            Category category = categories.get( 0 );
            metacard.setContentTypeName( category.getTerm() );
            IRI scheme = category.getScheme();
            if ( scheme != null ) {
                metacard.setContentTypeVersion( scheme.toString() );
            }
        }

        try {
            metacard.setModifiedDate( entry.getUpdated() );
        } catch ( IllegalArgumentException e ) {
            LOGGER.warn( "InvalidDate found in atom reponse, setting Metacard modified time to now " );
            metacard.setEffectiveDate( new Date() );
        }
        try {
            metacard.setEffectiveDate( entry.getPublished() );
        } catch ( IllegalArgumentException e ) {
            LOGGER.warn( "InvalidDate found in atom reponse, setting Metacard Effective time to now " );
            metacard.setEffectiveDate( new Date() );
        }

        String createdDate = entry.getSimpleExtension( new QName( AtomResponseConstants.METACARD_ATOM_NAMESPACE, AtomResponseConstants.METACARD_CREATED_DATE_ELEMENT ) );
        if ( createdDate != null ) {
            metacard.setCreatedDate( new Date( DATE_FORMATTER.parseMillis( createdDate ) ) );
        }

        String expirationDate = entry.getSimpleExtension( new QName( AtomResponseConstants.METACARD_ATOM_NAMESPACE, AtomResponseConstants.METADATA_EXPIRATION_DATE_ELEMENT ) );
        if ( expirationDate != null ) {
            metacard.setExpirationDate( new Date( DATE_FORMATTER.parseMillis( expirationDate ) ) );
        }

        AtomContentXmlWrapOption wrap = filterConfig.getAtomContentXmlWrapOption();
        String metadata = entry.getContent();
        populateMetadata( entry, metacard, wrap, metadata );

        metacard.setLocation( getWKT( entry ) );

        Link productLink = entry.getLink( filterConfig.getProductLinkRelation() );
        if ( productLink != null ) {

            metacard.setResourceURI( URI.create( productLink.getHref().toASCIIString() ) );
            long resourceSize = productLink.getLength();
            if ( resourceSize > 0 ) {
                metacard.setResourceSize( String.valueOf( resourceSize ) );
            }
            String productTitle = productLink.getTitle();
            if ( productTitle != null ) {
                metacard.setAttribute( CDRMetacard.RESOURCE_TITLE, productTitle );
            }
            // ECDR-41 figure out MIMEType
            MimeType productType = productLink.getMimeType();
            if ( productType != null ) {
                metacard.setAttribute( CDRMetacard.RESOURCE_MIME_TYPE, productType.toString() );
            }
        }

        String thumbnailLinkRel = filterConfig.getThumbnailLinkRelation();
        if ( thumbnailLinkRel != null ) {
            List<Link> links = entry.getLinks( thumbnailLinkRel );
            if ( links != null && !links.isEmpty() ) {
                for ( Link link : links ) {
                    MimeType mimeType = link.getMimeType();
                    if ( mimeType == null || "image".equals( mimeType.getPrimaryType() ) ) {

                        metacard.setThumbnailLinkURI( URI.create( link.getHref().toASCIIString() ) );
                        long thumbnailSize = link.getLength();
                        if ( thumbnailSize > 0 ) {
                            metacard.setAttribute( CDRMetacard.THUMBNAIL_LENGTH, Long.valueOf( thumbnailSize ) );
                        }
                        // ECDR-41 figure out MIMEType
                        metacard.setAttribute( CDRMetacard.THUMBNAIL_MIMETYPE, link.getMimeType() );
                        metacard.setAttribute( CDRMetacard.THUMBNAIL_LINK_TITLE, link.getTitle() );
                        break;
                    }
                }
            }
        }
        metacard.setTitle( entry.getTitle() );

        boolean isMetadataSet = false;
        ClassLoader tccl = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader( AtomResponseTransformer.class.getClassLoader() );
            List<Element> extensions = entry.getExtensions();
            for ( Element element : extensions ) {
                if ( METADATA_ELEMENT_NAME.equalsIgnoreCase( element.getQName().getLocalPart() ) ) {
                    StringWriter writer = new StringWriter();
                    try {
                        element.writeTo( writer );
                        metacard.setMetadata( writer.toString() );
                        isMetadataSet = true;
                        break;
                    } catch ( IOException e ) {
                        LOGGER.error( "Could not convert Metadata String value from Atom to Metacard.METADATA attribute", e );
                    }

                }
            }
        } finally {
            Thread.currentThread().setContextClassLoader( tccl );
        }
        if ( !isMetadataSet ) {
            String metadataLinkRel = filterConfig.getMetadataLinkRelation();
            if ( metadataLinkRel != null ) {
                List<Link> metadataLinks = entry.getLinks( metadataLinkRel );
                String metadataLink = null;
                for ( Link link : metadataLinks ) {
                    MimeType mimeType = link.getMimeType();
                    if ( mimeType != null ) {
                        if ( mimeType.getSubType().contains( "xml" ) ) {
                            metadataLink = link.getHref().toASCIIString();
                            metacard.setMetadataLinkURI( URI.create( metadataLink ) );
                        } else if ( mimeType.getBaseType().contains( "text" ) ) {
                            metadataLink = link.getHref().toASCIIString();
                            metacard.setMetadataLinkURI( URI.create( metadataLink ) );
                            metacard.setAttribute( CDRMetacard.WRAP_METADATA, Boolean.TRUE );
                        }
                    }
                }
            }
        }
        Metacard returnMetacard = SecurityMarkingParser.addSecurityToMetacard( metacard, entry );
        return new CDRMetacard( returnMetacard );
    }

    protected void populateMetadata( Entry entry, CDRMetacard metacard, AtomContentXmlWrapOption wrap, String metadata ) {
        if ( metadata != null ) {
            if ( wrap != null && !wrap.equals( AtomContentXmlWrapOption.NEVER_WRAP ) ) {
                if ( wrap.equals( AtomContentXmlWrapOption.WRAP_HTML_AND_TEXT ) ) {
                    Content.Type contentType = entry.getContentType();
                    // certain content types may not follow XML structure
                    switch ( contentType ) {
                    case TEXT:
                    case HTML:
                        // add content element to make sure it has single root
                        metadata = "<xml-fragment>" + metadata + "</xml-fragment>";
                        break;
                    default:
                        // other items are xml-based
                        break;
                    }
                } else {
                    metadata = "<xml-fragment>" + metadata + "</xml-fragment>";
                }
            }
            metacard.setMetadata( metadata );
        }
    }

    protected Result metacardToResult( Entry entry, Metacard metacard ) {
        ResultImpl result = new ResultImpl( metacard );
        String relevance = entry.getSimpleExtension( AtomResponseConstants.RELEVANCE_NAMESPACE, AtomResponseConstants.RELEVANCE_ELEMENT, AtomResponseConstants.RELEVANCE_NAMESPACE_PREFIX );
        if ( relevance != null ) {
            try {
                result.setRelevanceScore( Double.parseDouble( relevance ) );
            } catch ( NumberFormatException e ) {
                LOGGER.warn( "Received invalid number for relevance from Atom response [" + relevance + "]", e );
            }
        }

        String distance = entry.getSimpleExtension( AtomResponseConstants.CDRS_EXT_NAMESPACE, AtomResponseConstants.DISTANCE_ELEMENT, AtomResponseConstants.CDRS_EXT_NAMESPACE_PREFIX );
        if ( distance != null ) {
            try {
                result.setDistanceInMeters( Double.parseDouble( distance ) );
            } catch ( NumberFormatException e ) {
                LOGGER.warn( "Received invalid number for distance from Atom response [" + distance + "]", e );
            }
        }

        return result;
    }

    protected String getWKT( Entry entry ) {
        String wkt = null;
        Position[] positions = net.di2e.ecdr.search.transform.atom.geo.GeoHelper.getPositions( entry );
        int length = positions.length;
        if ( length == 1 ) {
            LOGGER.debug( "Found one geometry in the current Atom entry, converting to WKT for inclusion in metacard" );
            return AbderaConverter.convertToWKT( positions[0] );
        } else if ( length > 1 ) {
            LOGGER.debug( "Found multiple geometries in the current Atom entry, converting to MULTI-WKT for inclusion in metacard" );
            return AbderaConverter.convertToWKT( positions );

        }
        return wkt;
    }

    /**
     * Check to see if entry is a valid ATOM Entry conforming to the specification.
     * 
     * @param entry
     * @return true if incoming entry conforms to the specification, false if it does not.
     */
    private boolean isValidEntry( Entry entry ) {
        if ( entry == null ) {
            return false;
        }
        // RFC4287 Section 4.1.2
        // atom:entry elements MUST contain exactly one atom:id element.
        // atom:entry elements MUST contain exactly one atom:title element.
        // atom:entry elements MUST contain exactly one atom:updated element.
        // quick check to make sure that the entry contains those elements.
        return (entry.getIdElement() != null && entry.getTitleElement() != null && entry.getUpdatedElement() != null);
    }

}
