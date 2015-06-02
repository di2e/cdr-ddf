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

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimeType;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.slf4j.LoggerFactory;
import org.slf4j.ext.XLogger;

import ddf.catalog.data.Attribute;
import ddf.catalog.data.AttributeDescriptor;
import ddf.catalog.data.BinaryContent;
import ddf.catalog.data.Metacard;
import ddf.catalog.data.MetacardType;
import ddf.catalog.data.impl.AttributeImpl;
import ddf.catalog.data.impl.MetacardTypeImpl;

public class CDRMetacard implements Metacard, Serializable {

    private static final long serialVersionUID = 1L;

    private transient Map<String, Attribute> map = null;
    private transient Metacard wrappedMetacard;
    private transient MetacardType type;
    private String sourceId;

    public static final String METACARD_CONTENT_COLLECTION_ATTRIBUTE = "content-collections";

    public static final String LINK_REL_PREVIEW = "preview";

    public static final String RESOURCE_MIME_TYPE = "resource-mime-type";
    public static final String RESOURCE_TITLE = "resource-title";

    static final String THUMBNAIL_LINK = "thumbnail-link";
    public static final String THUMBNAIL_LENGTH = "thumbnail-length";
    public static final String THUMBNAIL_MIMETYPE = "thumbnail-mimetype";
    public static final String THUMBNAIL_LINK_TITLE = "thumbnail-link-title";

    static final String METADATA_LINK = "metadata-link";
    public static final String WRAP_METADATA = "wrap-metadata";

    private static final String THUMBNAIL_N_A = "N/A";

    private static final XLogger LOGGER = new XLogger( LoggerFactory.getLogger( CDRMetacard.class ) );

    /**
     * Creates a {@link Metacard} with a and empty {@link Attribute}s.
     */
    public CDRMetacard() {
        this( CDRMetacardType.CDR_METACARD );
    }

    /**
     * Creates a {@link Metacard} with the provided {@link MetacardType} and empty {@link Attribute} s.
     * 
     * @param type
     *            the {@link MetacardType}
     */
    public CDRMetacard( MetacardType mt ) {
        map = new HashMap<String, Attribute>();
        if ( mt != null ) {
            this.type = mt;
        } else {
            throw new IllegalArgumentException( MetacardType.class.getName() + " instance should not be null." );
        }
    }

    /**
     * Creates a {@link Metacard} with the provided {@link Metacard}.
     * 
     * @param metacard
     *            the {@link Metacard} to create this new {@code Metacard} from
     */
    public CDRMetacard( Metacard metacard ) {
        this( CDRMetacardType.CDR_METACARD );
        if ( metacard instanceof CDRMetacard ) {
            map.putAll( ((CDRMetacard) metacard).getMap() );
            setSourceId( metacard.getSourceId() );
            System.out.println( "CDRMetacard " + getSourceId() );
        } else {
            this.wrappedMetacard = metacard;
            if ( metacard.getMetacardType() != null ) {
                this.type = metacard.getMetacardType();
            } else {
                throw new IllegalArgumentException( MetacardType.class.getName() + " instance should not be null." );
            }
        }

    }

    public Map<String, Attribute> getMap() {
        return map;
    }

    public boolean hasLocation() {
        return getLocation() != null;
    }

    public boolean hasResource() {
        return getResourceURI() != null;
    }

    public String getResourceMIMETypeString() {
        Attribute attribute = getAttribute( RESOURCE_MIME_TYPE );
        if ( attribute != null ) {
            Serializable mimeType = attribute.getValue();
            if ( mimeType != null && mimeType instanceof String ) {
                return (String) mimeType;
            }
        }
        return "application/unknown";

    }

    public long getResourceSizeLong() {
        String size = getResourceSize();
        if ( StringUtils.isNotBlank( size ) ) {
            try {
                return Long.parseLong( getResourceSize() );
            } catch ( NumberFormatException e ) {
                LOGGER.debug( "Could not parse resource size into integer from Metacard: " + size );
            }
        }
        return -1;
    }

    public boolean hasThumbnail() {
        // Order is import here especially if the original Metacard is using
        // Thumbnail links and doesn't pull the thumbnail until the getThumbnail
        // method is called
        return getAttribute( THUMBNAIL_LINK ) != null || getThumbnail() != null;
    }

    public long getThumbnailLength() {
        long thumbnailSize = -1;
        Attribute attribute = getAttribute( THUMBNAIL_LENGTH );
        if ( attribute != null ) {
            thumbnailSize = (Long) attribute.getValue();
        }
        if ( thumbnailSize < 0 ) {
            byte[] thumbnail = getThumbnail();
            thumbnailSize = thumbnail == null ? -1 : thumbnail.length;
        }
        return thumbnailSize;
    }

    public String getAtomId() {
        return "urn:catalog:id:" + getId();
    }

    public URI getMetadataURL() {
        URI uri = null;
        String data = requestString( METADATA_LINK );
        if ( data != null ) {
            try {
                uri = new URI( data );
            } catch ( URISyntaxException e ) {
                LOGGER.warn( "failed parsing URI string, returning null" );
            }
        }
        return uri;
    }

    public URI getThumbnailURL() {
        URI uri = null;
        String data = requestString( THUMBNAIL_LINK );
        if ( data != null ) {
            try {
                uri = new URI( data );
            } catch ( URISyntaxException e ) {
                LOGGER.warn( "failed parsing URI string, returning null" );
            }
        }
        return uri;
    }

    public MimeType getThumbnailMIMEType() {
        Attribute attribute = getAttribute( THUMBNAIL_MIMETYPE );
        if ( attribute != null ) {
            return (MimeType) attribute.getValue();
        }
        return null;
    }

    public String getThumbnailLinkTitle() {
        Attribute attribute = getAttribute( THUMBNAIL_LINK_TITLE );
        if ( attribute != null ) {
            return (String) attribute.getValue();
        }
        return null;
    }

    @Override
    public String getMetadata() {
        String metadata = requestString( Metacard.METADATA );
        if ( StringUtils.isBlank( metadata ) ) {
            URI metadataURI = getMetadataURL();
            if ( metadataURI != null ) {
                try ( InputStream in = metadataURI.toURL().openStream() ) {
                    metadata = IOUtils.toString( in );
                    if ( getAttribute( CDRMetacard.WRAP_METADATA ) != null ) {
                        StringBuilder sb = new StringBuilder();
                        sb.append( "<xml>" );
                        sb.append( metadata );
                        sb.append( "</xml>" );
                        metadata = sb.toString();
                    }
                    setAttribute( new AttributeImpl( Metacard.METADATA, metadata ) );
                } catch ( MalformedURLException e ) {
                    LOGGER.warn( "Cannot read metadata due to Invalid metadata URL[" + metadataURI + "]: " + e.getMessage(), e );
                } catch ( IOException e ) {
                    LOGGER.warn( "Could not read metadata from remote URL[" + metadataURI + "] due to: " + e.getMessage(), e );
                }
            }
        }
        return metadata;
    }

    @Override
    public Date getModifiedDate() {
        Date modified = requestDate( Metacard.MODIFIED );
        return modified == null ? new Date() : modified;
    }

    @Override
    public String getResourceSize() {
        String size = requestString( Metacard.RESOURCE_SIZE );
        return StringUtils.isNotBlank( size ) && !THUMBNAIL_N_A.equalsIgnoreCase( size ) ? size : null;
    }

    @Override
    public byte[] getThumbnail() {
        byte[] thumbnail = requestBytes( Metacard.THUMBNAIL );
        if ( thumbnail == null ) {
            URI thumbnailURI = getThumbnailURL();
            if ( thumbnailURI != null ) {
                try ( InputStream in = thumbnailURI.toURL().openStream() ) {
                    thumbnail = IOUtils.toByteArray( in );
                    setAttribute( new AttributeImpl( Metacard.THUMBNAIL, thumbnail ) );
                } catch ( MalformedURLException e ) {
                    LOGGER.warn( "Cannot read thumbnail due to invalid thumbnail URL[" + thumbnailURI + "]: " + e.getMessage(), e );
                } catch ( IOException e ) {
                    LOGGER.warn( "Could not read thumbnail from remote URL[" + thumbnailURI + "] due to: " + e.getMessage(), e );
                }
            }
        }
        return thumbnail;
    }

    @Override
    public String getTitle() {
        String title = requestString( Metacard.TITLE );
        return title == null ? "No Title" : title;
    }

    @Override
    public Date getCreatedDate() {
        return requestDate( Metacard.CREATED );
    }

    /**
     * Sets the date/time the {@link Metacard} was created. <br/>
     * Convenience method for <code>
     * {@link #setAttribute setAttribute}(new {@link AttributeImpl}({@link Metacard#CREATED}, created))
     * </code>
     * 
     * @param created
     *            {@link Date} when this {@link Metacard} was created.
     * 
     * @see Metacard#CREATED
     */
    public void setCreatedDate( Date created ) {
        setAttribute( Metacard.CREATED, created );
    }

    /**
     * Sets the date/time the {@link Metacard} was last modifed. <br/>
     * Convenience method for <code>
     * {@link #setAttribute setAttribute}(new {@link AttributeImpl}({@link Metacard#MODIFIED}, modified))
     * </code>
     * 
     * @param modified
     *            {@link Date} when this {@link Metacard} was last modified.
     * 
     * @see Metacard#MODIFIED
     */
    public void setModifiedDate( Date modified ) {
        setAttribute( Metacard.MODIFIED, modified );
    }

    @Override
    public Date getExpirationDate() {
        return requestDate( Metacard.EXPIRATION );
    }

    /**
     * Sets the date/time this {@link Metacard} is no longer valid and could be removed. <br/>
     * Convenience method for <code>
     * {@link #setAttribute setAttribute}(new {@link AttributeImpl}({@link Metacard#EXPIRATION}, expiration))
     * </code>
     * 
     * @param expiration
     *            {@link Date} when the {@link Metacard} expires and should be removed from any stores.
     * 
     * @see Metacard#EXPIRATION
     */
    public void setExpirationDate( Date expiration ) {
        setAttribute( Metacard.EXPIRATION, expiration );
    }

    @Override
    public Date getEffectiveDate() {
        return requestDate( Metacard.EFFECTIVE );
    }

    /**
     * Sets the date/time the {@link Metacard} was last known to be valid. <br/>
     * Convenience method for <code>
     * {@link #setAttribute setAttribute}(new {@link AttributeImpl}({@link Metacard#EFFECTIVE}, effective))
     * </code>
     * 
     * @param effective
     *            {@link Date} when the information represented by the {@link Metacard} was last known to be valid.
     * 
     * @see Metacard#EFFECTIVE
     */
    public void setEffectiveDate( Date effective ) {
        setAttribute( Metacard.EFFECTIVE, effective );
    }

    @Override
    public String getId() {
        return requestString( Metacard.ID );
    }

    /**
     * Sets the ID of the {@link Metacard}. <br/>
     * Convenience method for <code>
     * {@link #setAttribute setAttribute}(new {@link AttributeImpl}({@link Metacard#ID}, id))
     * </code>
     * 
     * @param id
     *            unique identifier of the Metacard.
     * 
     * @see Metacard#ID
     */
    public void setId( String id ) {
        setAttribute( Metacard.ID, id );
    }

    @Override
    public String getLocation() {
        return requestString( Metacard.GEOGRAPHY );
    }

    /**
     * Sets the WKT representation of the geometry. <br/>
     * Convenience method for <code>
     * {@link #setAttribute setAttribute}(new {@link AttributeImpl}({@link Metacard#GEOGRAPHY}, wkt))
     * </code>
     * 
     * @param wkt
     *            WKT-defined geospatial {@link String}, returns null if not applicable
     * 
     * @see Metacard#GEOGRAPHY
     */
    public void setLocation( String wkt ) {
        setAttribute( Metacard.GEOGRAPHY, wkt );
    }

    @Override
    public String getSourceId() {
        return wrappedMetacard != null ? wrappedMetacard.getSourceId() : sourceId;
    }

    public void setSourceId( String id ) {
        if ( wrappedMetacard != null ) {
            wrappedMetacard.setSourceId( id );
        } else {
            this.sourceId = id;
        }
    }

    /**
     * Sets the thumbnail associated with this {@link Metacard}. <br/>
     * Convenience method for <code>
     * {@link #setAttribute setAttribute}(new {@link AttributeImpl}({@link Metacard#THUMBNAIL}, bytes))
     * </code>
     * 
     * @param bytes
     *            thumbnail for the {@link Metacard}.
     * 
     * @see Metacard#THUMBNAIL
     */
    public void setThumbnail( byte[] bytes ) {
        setAttribute( Metacard.THUMBNAIL, bytes );
    }

    /**
     * Sets the title of this {@link Metacard}. <br/>
     * Convenience method for <code>
     * {@link #setAttribute setAttribute}(new {@link AttributeImpl}({@link Metacard#TITLE}, id))
     * </code>
     * 
     * @param title
     *            Title of the {@link Metacard}
     * 
     * @see Metacard#TITLE
     */
    public void setTitle( String title ) {
        setAttribute( Metacard.TITLE, title );
    }

    /**
     * Sets the metadata associated with this {@link Metacard}. <br/>
     * Convenience method for <code>
     * {@link #setAttribute setAttribute}(new {@link AttributeImpl}({@link Metacard#METADATA}, metadata))
     * </code>
     * 
     * @param metadata
     *            Associated metadata of the {@link Metacard}
     * 
     * @see Metacard#METADATA
     */
    public void setMetadata( String metadata ) {
        setAttribute( Metacard.METADATA, metadata );
    }

    @Override
    public MetacardType getMetacardType() {
        return type;
    }

    /**
     * Sets the {@link MetacardType} of the {@link Metacard}.
     * 
     * @param type
     *            {@link MetacardType} of the {@link Metacard}
     * 
     * @see MetacardType
     */
    public void setType( MetacardType mt ) {
        this.type = mt;
    }

    @Override
    public URI getContentTypeNamespace() {
        URI uri = null;
        String uriString = requestString( Metacard.TARGET_NAMESPACE );
        if ( uriString != null && !uriString.isEmpty() ) {
            uri = URI.create( uriString );
        }
        return uri;
    }

    /**
     * Some types of metadata use different content types. If utilized, sets the {@link URI} of the content type. <br/>
     * Convenience method for <code>
     * {@link #setAttribute setAttribute}(new {@link AttributeImpl}({@link Metacard#TARGET_NAMESPACE}, targetNamespace))
     * </code>
     * 
     * @param targetNamespace
     *            {@link URI} of the sub-type, null if unused
     * 
     * @see Metacard#TARGET_NAMESPACE
     */
    public void setTargetNamespace( URI targetNamespace ) {
        setAttribute( Metacard.TARGET_NAMESPACE, targetNamespace.toASCIIString() );
    }

    @Override
    public String getContentTypeName() {
        String content = requestString( Metacard.CONTENT_TYPE );
        return StringUtils.isNotBlank( content ) ? content : "Unknown";
    }

    /**
     * Sets the name of the content type of the {@link Metacard}. <br/>
     * Convenience method for <code>
     * {@link #setAttribute setAttribute}(new {@link AttributeImpl}({@link Metacard#CONTENT_TYPE}, contentType))
     * </code>
     * 
     * @param contentType
     *            name of content type of the {@link Metacard}
     * 
     * @see Metacard#CONTENT_TYPE
     */
    public void setContentTypeName( String contentType ) {
        setAttribute( Metacard.CONTENT_TYPE, contentType );
    }

    @Override
    public String getContentTypeVersion() {
        String version = requestString( Metacard.CONTENT_TYPE_VERSION );
        return StringUtils.isNotBlank( version ) ? version : "Unknown";
    }

    /**
     * Sets the version of the content type of the {@link Metacard}. <br/>
     * Convenience method for <code>
     * {@link #setAttribute setAttribute}(new {@link AttributeImpl}({@link Metacard#CONTENT_TYPE_VERSION}, contentTypeVersion))
     * </code>
     * 
     * @param contentTypeVersion
     *            version of content type of the {@link Metacard}
     * 
     * @see Metacard#CONTENT_TYPE_VERSION
     */
    public void setContentTypeVersion( String contentTypeVersion ) {
        setAttribute( Metacard.CONTENT_TYPE_VERSION, contentTypeVersion );
    }

    @Override
    public URI getResourceURI() {
        URI uri = null;
        String data = requestString( Metacard.RESOURCE_URI );
        if ( data != null ) {
            try {
                uri = new URI( data );
            } catch ( URISyntaxException e ) {
                LOGGER.warn( "failed parsing URI string, returning null" );
            }
        }
        return uri;
    }

    /**
     * 
     * 
     * @see Metacard#RESOURCE_URI
     */
    public void setResourceURI( URI uri ) {
        if ( uri == null ) {
            return;
        }
        setAttribute( RESOURCE_URI, uri.toString() );
    }

    public void setMetadataLinkURI( URI uri ) {
        if ( uri == null ) {
            return;
        }
        setAttribute( METADATA_LINK, uri.toString() );
    }

    public void setThumbnailLinkURI( URI uri ) {
        if ( uri == null ) {
            return;
        }
        setAttribute( THUMBNAIL_LINK, uri.toString() );
    }

    /**
     * Sets the size of the resource which may or may not contain a unit. <br/>
     * Convenience method for <code>
     * {@link #setAttribute setAttribute}(new {@link AttributeImpl}({@link Metacard#RESOURCE_SIZE}, dadSize))
     * </code>
     * 
     * @param dadSize
     *            {@link String} representation of the size
     * 
     * @see Metacard#RESOURCE_SIZE
     */
    public void setResourceSize( String dadSize ) {
        setAttribute( RESOURCE_SIZE, dadSize );
    }

    /**
     * Returns the security relevant markings on the {@link ddf.catalog.data.Metacard}.
     * 
     * @return security markings
     */
    public Map<String, List<String>> getSecurity() {
        return requestData( Metacard.SECURITY, HashMap.class );
    }

    /**
     * Sets the security markings on this {@link Metacard}. <br />
     * Convenience method for <code>
     * {@link #setAttribute setAttribute}(new {@link AttributeImpl}({@link Metacard#SECURITY}, security))
     * </code>
     * 
     * @param security
     */
    public void setSecurity( HashMap<String, List<String>> security ) {
        setAttribute( Metacard.SECURITY, security );
    }

    /**
     * The brains of the operation -- does the interaction with the map or the wrapped metacard.
     * 
     * @param <T>
     *            the type of the Attribute value expected
     * @param attributeName
     *            the name of the {@link Attribute} to retrieve
     * @param returnType
     *            the class that the value of the {@link AttributeType} is expected to be bound to
     * @return the value of the requested {@link Attribute} name
     */
    protected <T> T requestData( String attributeName, Class<T> returnType ) {

        Attribute attribute = getAttribute( attributeName );

        if ( attribute == null ) {
            if ( LOGGER.isDebugEnabled() ) {
                LOGGER.debug( "Attribute " + attributeName + " was not found, returning null" );
            }
            return null;
        }

        Serializable data = attribute.getValue();

        if ( returnType.isAssignableFrom( data.getClass() ) ) {
            return returnType.cast( data );
        } else {
            if ( LOGGER.isDebugEnabled() ) {
                LOGGER.debug( data.getClass().toString() + " can not be assigned to " + returnType.toString() );
            }
        }

        return null;
    }

    /**
     * Get {@link Date} data from the map or wrapped metacard. <br/>
     * Convenience method for <code>
     * {@link #requestData requestData}(key, Date.class))
     * </code>
     * 
     * @param key
     *            the name of the {@link Attribute} to retrieve
     * @return the value of the requested {@link Attribute} name
     * 
     */
    protected Date requestDate( String key ) {
        return requestData( key, Date.class );
    }

    /**
     * Get {@link Double} data from the map or wrapped metacard. <br/>
     * Convenience method for <code>
     * {@link #requestData requestData}(key, Double.class))
     * </code>
     * 
     * @param key
     *            the name of the {@link Attribute} to retrieve
     * @return the value of the requested {@link Attribute} name
     * 
     */
    protected Double requestDouble( String key ) {
        return requestData( key, Double.class );
    }

    /**
     * Get {@link BinaryContent} data from the map or wrapped metacard. <br/>
     * Convenience method for <code>
     * {@link #requestData requestData}(key, BinaryContent.class))
     * </code>
     * 
     * @param key
     *            the name of the {@link Attribute} to retrieve
     * @return the value of the requested {@link Attribute} name
     * 
     */
    protected InputStream requestInputStream( String key ) {
        BinaryContent data = requestData( key, BinaryContent.class );
        if ( data != null ) {
            return data.getInputStream();
        }
        return null;
    }

    /**
     * Get {@link Integer} data from the map or wrapped metacard. <br/>
     * Convenience method for <code>
     * {@link #requestData requestData}(key, Integer.class))
     * </code>
     * 
     * @param key
     *            the name of the {@link Attribute} to retrieve
     * @return the value of the requested {@link Attribute} name
     * 
     */
    protected Integer requestInteger( String key ) {
        return requestData( key, Integer.class );
    }

    /**
     * Get {@link Long} data from the map or wrapped metacard. <br/>
     * Convenience method for <code>
     * {@link #requestData requestData}(key, Long.class))
     * </code>
     * 
     * @param key
     *            the name of the {@link Attribute} to retrieve
     * @return the value of the requested {@link Attribute} name
     * 
     */
    protected Long requestLong( String key ) {
        return requestData( key, Long.class );
    }

    /**
     * Get {@link String} data from the map or wrapped metacard. <br/>
     * Convenience method for <code>
     * {@link #requestData requestData}(key, String.class))
     * </code>
     * 
     * @param key
     *            the name of the {@link Attribute} to retrieve
     * @return the value of the requested {@link Attribute} name
     * 
     */
    protected String requestString( String key ) {
        return requestData( key, String.class );
    }

    /**
     * Get {@link byte[]} data from the map or wrapped metacard. <br/>
     * Convenience method for <code>
     * {@link #requestData requestData}(key, byte[].class))
     * </code>
     * 
     * @param key
     *            the name of the {@link Attribute} to retrieve
     * @return the value of the requested {@link Attribute} name
     * 
     */
    protected byte[] requestBytes( String key ) {
        return requestData( key, byte[].class );
    }

    @Override
    public Attribute getAttribute( String name ) {
        return (wrappedMetacard != null) ? wrappedMetacard.getAttribute( name ) : map.get( name );
    }

    /**
     * Set an attribute via a name/value pair.
     * 
     * @param name
     *            the name of the {@link Attribute}
     * @param value
     *            the value of the {@link Attribute}
     */
    public void setAttribute( String name, Serializable value ) {
        setAttribute( new AttributeImpl( name, value ) );
    }

    @Override
    public void setAttribute( Attribute attribute ) {

        if ( attribute == null ) {
            return;
        }

        if ( wrappedMetacard != null ) {
            wrappedMetacard.setAttribute( attribute );
        } else {
            String name = attribute.getName();
            Serializable value = attribute.getValue();
            if ( name != null ) {
                if ( value != null ) {
                    map.put( name, attribute );
                } else {
                    map.remove( name );
                }
            }
        }
    }

    private void writeObject( ObjectOutputStream stream ) throws IOException {

        /*
         * defaultWriteObject() is invoked for greater flexibility and compatibility. See the *Serialization Note* in
         * class Javadoc.
         */
        stream.defaultWriteObject();

        /*
         * Cannot allow unknown implementations of MetacardType to be serialized. Must convert them to our
         * implementation to guarantee it is serializing the logical representation and not the physical representation.
         */
        if ( type instanceof MetacardTypeImpl ) {
            stream.writeObject( type );
        } else {
            MetacardTypeImpl mt = new MetacardTypeImpl( type.getName(), type.getAttributeDescriptors() );
            stream.writeObject( mt );
        }

        if ( map != null ) {
            stream.writeInt( map.size() );

            for ( Attribute attribute : this.map.values() ) {
                stream.writeObject( attribute );
            }
        } else {
            if ( wrappedMetacard != null && wrappedMetacard.getMetacardType() != null ) {

                MetacardType metacardType = wrappedMetacard.getMetacardType();

                List<Attribute> attributes = new ArrayList<Attribute>();

                if ( metacardType.getAttributeDescriptors() == null ) {
                    // no descriptors, means no attributes can be defined.
                    // no attributes defined, means no attributes written to
                    // disk
                    stream.writeInt( 0 );
                } else {

                    for ( AttributeDescriptor ad : metacardType.getAttributeDescriptors() ) {

                        Attribute attribute = wrappedMetacard.getAttribute( ad.getName() );

                        if ( attribute != null ) {
                            attributes.add( attribute );
                        }
                    }

                    // Must loop again because the size of the attributes list
                    // is not known until list has been fully populated.
                    stream.writeInt( attributes.size() );

                    for ( Attribute attribute : attributes ) {
                        stream.writeObject( attribute );
                    }
                }
            }
        }

    }

    /**
     * Deserializes this instance.
     * 
     * @param stream
     *            the {@link ObjectInputStream} that contains the bytes of the object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject( ObjectInputStream stream ) throws IOException, ClassNotFoundException {

        /*
         * defaultReadObject() is invoked for greater flexibility and compatibility. See the *Serialization Note* in
         * class Javadoc.
         */
        stream.defaultReadObject();

        map = new HashMap<String, Attribute>();

        wrappedMetacard = null;

        type = (MetacardType) stream.readObject();

        if ( type == null ) {
            throw new InvalidObjectException( MetacardType.class.getName() + " instance cannot be null." );
        }

        int numElements = stream.readInt();

        for ( int i = 0; i < numElements; i++ ) {

            Attribute attribute = (Attribute) stream.readObject();

            if ( attribute != null ) {

                AttributeDescriptor attributeDescriptor = getMetacardType().getAttributeDescriptor( attribute.getName() );

                if ( attributeDescriptor != null && attribute.getValue() != null ) {
                    attributeDescriptor.getType().getAttributeFormat();
                    attributeDescriptor.getType().getClass();
                }

            }

            setAttribute( attribute );
        }

    }

    public int hashCode() {
        return new HashCodeBuilder( 17, 37 ).append( this.getId() ).append( this.getMetacardType() ).append( this.getMetadata() ).toHashCode();
    }

}
