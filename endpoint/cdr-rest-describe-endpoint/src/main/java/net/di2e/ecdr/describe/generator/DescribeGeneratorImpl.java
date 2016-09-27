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
package net.di2e.ecdr.describe.generator;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang.StringUtils;
import org.codice.ddf.security.common.Security;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.ISODateTimeFormat;
import org.opengis.filter.Filter;
import org.opengis.filter.sort.SortBy;
import org.opengis.filter.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;

import ddf.catalog.CatalogFramework;
import ddf.catalog.data.Metacard;
import ddf.catalog.data.Result;
import ddf.catalog.data.impl.MetacardImpl;
import ddf.catalog.filter.FilterBuilder;
import ddf.catalog.filter.impl.SortByImpl;
import ddf.catalog.operation.QueryResponse;
import ddf.catalog.operation.impl.QueryImpl;
import ddf.catalog.operation.impl.QueryRequestImpl;
import ddf.security.Subject;
import ddf.util.NamespaceMapImpl;
import ddf.util.XPathHelper;
import mil.ces.metadata.mdr.ns.gsip.tspi._2_0.core.EnvelopeType;
import mil.ces.metadata.mdr.ns.gsip.tspi._2_0.core.EnvelopeType.LowerCorner;
import mil.ces.metadata.mdr.ns.gsip.tspi._2_0.core.EnvelopeType.UpperCorner;
import net.di2e.ecdr.describe.generator.util.DescribeXMLParser;
import net.di2e.ecdr.describe.generator.util.TemporalCoverageHolder;
import net.di2e.jaxb.cdr.describe.cc.ContentCollectionType;
import net.di2e.jaxb.cdr.describe.cc.MIMETypeType;
import net.di2e.jaxb.cdr.describe.cc.MetricsType;
import net.di2e.jaxb.cdr.describe.cc.MimeTypes;
import net.di2e.jaxb.cdr.describe.cc.RecordRateType;
import net.di2e.jaxb.cdr.describe.cc.SecurityCoverage;
import net.di2e.jaxb.cdr.describe.cc.SecurityType;
import net.di2e.jaxb.cdr.describe.wrapper.Collection;
import net.di2e.jaxb.cdr.describe.wrapper.Describe;
import net.opengis.gml.v_3_2_1.DirectPositionType;
import us.gov.ic.cvenum.ism.classification.all.CVEnumISMClassificationAll;
import us.mil.ces.metadata.ddms._5.BoundingGeometryType;
import us.mil.ces.metadata.ddms._5.CompoundCategoryIdentifierType;
import us.mil.ces.metadata.ddms._5.CompoundKeywordIdentifierType;
import us.mil.ces.metadata.ddms._5.CompoundResourceIdentifierType;
import us.mil.ces.metadata.ddms._5.CompoundSourceIdentifierType;
import us.mil.ces.metadata.ddms._5.CompoundTypeIdentifierType;
import us.mil.ces.metadata.ddms._5.DescriptionType;
import us.mil.ces.metadata.ddms._5.PlaceType;
import us.mil.ces.metadata.ddms._5.ResourceType;
import us.mil.ces.metadata.ddms._5.SubjectType;
import us.mil.ces.metadata.ddms._5.TimePeriodType;
import us.mil.ces.metadata.ddms._5.TitleType;

public class DescribeGeneratorImpl implements DescribeGenerator {

    private static DateTimeFormatter formatter;

    static {
        DateTimeParser[] parsers = { ISODateTimeFormat.date().getParser(), ISODateTimeFormat.dateTime().getParser(),
                ISODateTimeFormat.dateTimeNoMillis().getParser(), ISODateTimeFormat.basicDateTime().getParser(),
                ISODateTimeFormat.basicDateTimeNoMillis().getParser() };
        formatter = new DateTimeFormatterBuilder().append( null, parsers ).toFormatter();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger( DescribeGeneratorImpl.class );
    private static DateFormat df = new SimpleDateFormat( "yyyy-MM-dd\'T\'HH:mm:ss.SSSZ" );
    private static final String DESCRIBE_EXT_NAMESPACE = "urn:cdr-ex:describe:ddms-ext:1.0";
    private static final String COUNT_ATTRIBUTE = "count";

    private static final String RESULT_WKT = "result-wkt";
    private static final String XPATH_KEYWORD = "//*[local-name()=\'keyword\']";
    private static final String XPATH_CATEGORY = "//*[local-name()=\'category\']";
    private static final String XPATH_TYPE = "//ddms:type";
    private static final String XPATH_SECURITY = "//*[local-name()=\'security\']";
    private static final String DDMS_NAMESPACE = "http://metadata.dod.mil/mdr/ns/DDMS/2.0/";
    private static final String ICISM_NAMESPACE = "urn:us:gov:ic:ism:v2";
    private static final String VALUE_ATTRIBUTE = "value";
    private static final String QUALIFIER_ATTRIBUTE = "qualifier";

    private Map<String, String> namespaceMap = new HashMap<>();

    private static DatatypeFactory dtf = null;
    private CatalogFramework framework = null;
    private FilterBuilder filterBuilder = null;
    private GeneratorConfiguration generatorConfig = null;
    private Map<String, Serializable> requestProperties;

    public DescribeGeneratorImpl( CatalogFramework fw, FilterBuilder builder, GeneratorConfiguration config ) {
        this.framework = fw;
        this.filterBuilder = builder;
        this.generatorConfig = config;

        try {
            dtf = DatatypeFactory.newInstance();
        } catch ( DatatypeConfigurationException e ) {
            LOGGER.error( e.getMessage(), e );
        }

        this.requestProperties = new HashMap<>();
        this.requestProperties.put( "ddf.security.subject", this.getSystemSubject() );
        namespaceMap.put( "ddms", DDMS_NAMESPACE );
        namespaceMap.put( "ICISM", ICISM_NAMESPACE );
    }

    public Metacard generate( String sourceId ) {
        HashMap<String, String> resultProperties = new HashMap<>();
        Describe describe = this.createDescribeRecord( sourceId, resultProperties );
        Metacard describeMetacard = this.toMetacard( describe, sourceId, resultProperties );
        return describeMetacard;
    }

    public Map<String, Metacard> generateAll() {
        HashMap<String, Metacard> describeXmls = new HashMap<>();
        this.framework.getSourceIds().forEach( ( sourceId ) -> {
            LOGGER.debug( "Creating Describe record for sourceId {}", sourceId );
            HashMap<String, String> resultProperties = new HashMap<>();
            Describe describe = this.createDescribeRecord( sourceId, resultProperties );
            Metacard describeMetacard = this.toMetacard( describe, sourceId, resultProperties );
            describeXmls.put( sourceId, describeMetacard );
        } );
        return describeXmls;
    }

    private Metacard toMetacard( Describe describe, String sourceId, Map<String, String> resultProperties ) {
        MetacardImpl metacard = new MetacardImpl();
        metacard.setMetadata( DescribeXMLParser.marshalDescribe( describe ) );
        metacard.setId( "guide://999715/" + sourceId );
        ResourceType resource = ((Collection) describe.getCollection().get( 0 )).getResource();
        metacard.setDescription( resource.getDescription().getValue() );
        metacard.setTitle( ((TitleType) resource.getTitle().get( 0 )).getValue() );
        Date now = new Date();
        metacard.setCreatedDate( now );
        metacard.setModifiedDate( now );
        metacard.setContentTypeName( "desribe" );
        metacard.setLocation( (String) resultProperties.get( RESULT_WKT ) );
        HashSet<String> tags = new HashSet<>();
        tags.add( generatorConfig.getMetacardTag() );
        metacard.setTags( tags );
        return metacard;
    }

    protected Describe createDescribeRecord( String sourceId, Map<String, String> resultProperties ) {
        Describe describe = new Describe();
        boolean hasMoreRecords = true;
        Date startDate = StringUtils.isBlank( generatorConfig.getStartDate() ) ? null : formatter.parseDateTime( generatorConfig.getStartDate() ).toDate();
        Date endDate = new Date();
        String queryKeywords = generatorConfig.getKeywords();
        Collection collection = new Collection();
        describe.getCollection().add( collection );

        ContentCollectionType contentCollection = new ContentCollectionType();
        collection.setContentCollection( contentCollection );
        contentCollection.setOriginator( this.generatorConfig.getCollectionOriginator() );
        contentCollection.setClassification( CVEnumISMClassificationAll.fromValue( this.generatorConfig.getClassification() ) );
        contentCollection.setOwnerProducer( this.generatorConfig.getOwnerProducer() );

        ResourceType resource = new ResourceType();
        collection.setResource( resource );

        CompoundResourceIdentifierType identifier = new CompoundResourceIdentifierType();
        identifier.setQualifier( "GUIDE" );
        identifier.setValue( "guide://999715/" + UUID.randomUUID() );
        resource.getIdentifier().add( identifier );

        TitleType title = new TitleType();
        title.setValue( "Describe Record: " + sourceId );
        resource.setTitle( Arrays.asList( new TitleType[] { title } ) );

        DescriptionType descType = new DescriptionType();
        descType.setValue( "Generated CDR Describe record for site \'" + sourceId + "\' - generated by " + this.generatorConfig.getCollectionOriginator() );
        resource.setDescription( descType );

        LOGGER.debug( "Generating Content Colleciton details for site {}", sourceId );
        HashMap<String, TemporalCoverageHolder> timeMap = new HashMap<>();

        Envelope geoEnvelope = new Envelope();

        int maxRecordCount = this.generatorConfig.getMaxRecordsPerPoll();

        ConcurrentHashMap<String, Long> keywordCounter = new ConcurrentHashMap<>();
        ConcurrentHashMap<QualifierValueHolder, Long> sourceCounter = new ConcurrentHashMap<>();
        ConcurrentHashMap<SecurityType, Long> secCounter = new ConcurrentHashMap<>();
        ConcurrentHashMap<CompoundCategoryIdentifierType, Long> categoryCounter = new ConcurrentHashMap<>();
        ConcurrentHashMap<CompoundTypeIdentifierType, Long> typeCounter = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Long> mimeCounter = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Long> contentTypeCounter = new ConcurrentHashMap<>();

        try {
            while ( hasMoreRecords ) {
                QueryImpl query = new QueryImpl( this.getFilter( startDate, endDate, queryKeywords ), 1, maxRecordCount, this.getSortBy(), true, 300000L );
                QueryRequestImpl queryRequest = new QueryRequestImpl( query, Arrays.asList( new String[] { sourceId } ) );
                queryRequest.setProperties( this.requestProperties );
                QueryResponse response = this.framework.query( queryRequest );
                contentCollection.setUpdated( dtf.newXMLGregorianCalendar( new GregorianCalendar() ) );
                MetricsType metrics = new MetricsType();
                contentCollection.setMetrics( metrics );
                if ( metrics.getCount() == 0 ) {
                    metrics.setCount( response.getHits() );
                }
                List<Result> results = response.getResults();
                hasMoreRecords = (results.size() == maxRecordCount) && startDate != null;
                LOGGER.debug( "Adding details from query results for {} records from site {} in the Content Collection date range [{} - {}] and keywords[{}]",
                        Integer.valueOf( results.size() ), sourceId, startDate, endDate, queryKeywords );

                for ( Result result : results ) {
                    try {
                        Metacard metacard = result.getMetacard();
                        populateTemporalCoverage( timeMap, metacard );

                        populateGeoCoverage( geoEnvelope, metacard );

                        populateContentType( contentTypeCounter, metacard );

                        String metadata = metacard.getMetadata();
                        if ( StringUtils.isNotBlank( metadata ) ) {
                            // Populate MIME Types
                            XPathHelper xpathHelper = new XPathHelper( metadata );
                            populateMIMEType( sourceId, mimeCounter, result, metacard, xpathHelper );
                            // populate source
                            populateSource( sourceId, sourceCounter, result, metacard, xpathHelper );
                            // Populate Keywords
                            populateKeywords( sourceId, keywordCounter, metacard, xpathHelper );
                            // Populate Category
                            populateCategory( sourceId, categoryCounter, metacard, xpathHelper );
                            // Populate Type
                            populateType( sourceId, typeCounter, metacard, xpathHelper );
                            // Populate Security
                            populateSecurityCoverage( sourceId, secCounter, metacard, xpathHelper );
                        }

                        TemporalCoverageHolder tch = (TemporalCoverageHolder) timeMap.get( generatorConfig.getDateType() );
                        if ( tch != null ) {
                            startDate = tch.getEndDate();
                        }
                    } catch ( Exception e ) {
                        LOGGER.error( "Error handling result {} ", result.getMetacard().getId(), e );
                    }
                }
            }

            if ( !geoEnvelope.isNull() ) {
                resultProperties.put( RESULT_WKT, (new WKTWriter()).write( (new GeometryFactory()).toGeometry( geoEnvelope ) ) );
            }

            List<Entry<String, Long>> keywords = keywordCounter.entrySet().stream().sorted( Entry.comparingByValue( Comparator.reverseOrder() ) )
                    .limit( (long) this.generatorConfig.getMaxKeywords() ).collect( Collectors.toList() );

            this.setKeywords( keywords, resource.getSubjectCoverage() );
            this.setGeospatialCoverage( resource.getGeospatialCoverage(), geoEnvelope );
            this.setTemporalCoverage( resource.getTemporalCoverage(), timeMap );
            this.setRecordRate( contentCollection.getMetrics(), timeMap );

            List<Entry<String, Long>> contentTypeDetails = contentTypeCounter.entrySet().stream().sorted( Entry.comparingByValue( Comparator.reverseOrder() ) )
                    .limit( (long) this.generatorConfig.getMaxContentTypes() ).collect( Collectors.toList() );
            this.setContentTypes( resource, contentTypeDetails );

            List<Entry<String, Long>> mimeTypeDetails = mimeCounter.entrySet().stream().sorted( Entry.comparingByValue( Comparator.reverseOrder() ) )
                    .limit( (long) this.generatorConfig.getMaxMimeTypes() ).collect( Collectors.toList() );
            this.setMimeTypes( contentCollection, mimeTypeDetails );

            List<Entry<CompoundCategoryIdentifierType, Long>> categoryDetails = categoryCounter.entrySet().stream()
                    .sorted( Entry.comparingByValue( Comparator.reverseOrder() ) ).limit( (long) this.generatorConfig.getMaxCategories() )
                    .collect( Collectors.toList() );
            this.setCategoryDetails( resource, categoryDetails );

            List<Entry<CompoundTypeIdentifierType, Long>> typeDetails = typeCounter.entrySet().stream()
                    .sorted( Entry.comparingByValue( Comparator.reverseOrder() ) ).limit( (long) this.generatorConfig.getMaxTypes() )
                    .collect( Collectors.toList() );
            this.setTypeDetails( resource, typeDetails );

            List<Entry<QualifierValueHolder, Long>> sources = sourceCounter.entrySet().stream().sorted( Entry.comparingByValue( Comparator.reverseOrder() ) )
                    .limit( (long) this.generatorConfig.getMaxSources() ).collect( Collectors.toList() );
            this.setSourceTypes( resource, sources );

            List<Entry<SecurityType, Long>> securityDetails = secCounter.entrySet().stream().sorted( Entry.comparingByValue( Comparator.reverseOrder() ) )
                    .limit( (long) this.generatorConfig.getMaxSecurity() ).collect( Collectors.toList() );
            this.setSecurityDetails( contentCollection, securityDetails );
        } catch ( Exception arg34 ) {
            LOGGER.warn( "Query failed against source {}", sourceId, arg34 );
        }

        return describe;
    }

    private void populateTemporalCoverage( HashMap<String, TemporalCoverageHolder> timeMap, Metacard metacard ) {
        this.updateDate( Metacard.EFFECTIVE, metacard.getEffectiveDate(), timeMap );
        this.updateDate( Metacard.CREATED, metacard.getCreatedDate(), timeMap );
        this.updateDate( Metacard.MODIFIED, metacard.getModifiedDate(), timeMap );
        this.updateDate( Metacard.EXPIRATION, metacard.getExpirationDate(), timeMap );
    }

    private void populateGeoCoverage( Envelope geoEnvelope, Metacard metacard ) throws ParseException {
        String wkt = metacard.getLocation();
        if ( StringUtils.isNotBlank( wkt ) ) {
            geoEnvelope.expandToInclude( (new WKTReader()).read( wkt ).getEnvelopeInternal() );
        }
    }

    private void populateContentType( ConcurrentHashMap<String, Long> contentTypeCounter, Metacard metacard ) {
        String contentType = metacard.getContentTypeName();
        if ( StringUtils.isNotBlank( contentType ) ) {
            contentTypeCounter.compute( contentType, ( k, v ) -> {
                return Long.valueOf( v == null ? 1L : v.longValue() + 1L );
            } );
        }
    }

    private void populateMIMEType( String sourceId, ConcurrentHashMap<String, Long> mimeCounter, Result result, Metacard metacard, XPathHelper xpathHelper ) {
        generatorConfig.getMimeTypesXPaths().forEach( xpath -> {
            try {
                String mimeType = xpathHelper.evaluate( xpath );
                LOGGER.trace( "MIME Type info from site {} record {}: {}", sourceId, metacard.getId(), mimeType );
                if ( StringUtils.isNotBlank( mimeType ) ) {
                    mimeCounter.compute( mimeType, ( k, v ) -> {
                        return Long.valueOf( v == null ? 1L : v.longValue() + 1L );
                    } );
                }
            } catch ( Exception e ) {
                LOGGER.error( "Error extracting MIMETypes {} ", result.getMetacard().getId(), e );
            }
        } );
    }

    private void populateSource( String sourceId, ConcurrentHashMap<QualifierValueHolder, Long> sourceCounter, Result result, Metacard metacard,
            XPathHelper xpathHelper ) {
        generatorConfig.getSourceXPaths().forEach( xpath -> {
            try {
                NodeList sourceNodes = (NodeList) xpathHelper.evaluate( xpath, XPathConstants.NODESET );
                LOGGER.trace( "Source Element info from site {} record {}: {}", sourceId, metacard.getId(), sourceNodes );
                if ( sourceNodes != null ) {
                    for ( int i = 0; i < sourceNodes.getLength(); i++ ) {
                        String qual = getAttributeValue( sourceNodes.item( i ), DDMS_NAMESPACE, QUALIFIER_ATTRIBUTE );
                        String val = getAttributeValue( sourceNodes.item( i ), DDMS_NAMESPACE, VALUE_ATTRIBUTE );
                        sourceCounter.compute( new QualifierValueHolder( qual, val ), ( k, v ) -> {
                            return Long.valueOf( v == null ? 1L : v.longValue() + 1L );
                        } );
                    }
                }
            } catch ( Exception e ) {
                LOGGER.error( "Error extracting Sources {} ", result.getMetacard().getId(), e );
            }
        } );
    }

    private void populateKeywords( String sourceId, ConcurrentHashMap<String, Long> keywordCounter, Metacard metacard, XPathHelper xpathHelper )
            throws XPathExpressionException {
        NodeList keywordNodes = (NodeList) xpathHelper.evaluate( XPATH_KEYWORD, XPathConstants.NODESET );
        LOGGER.trace( "Keyword info from site {} record {}: {}", sourceId, metacard.getId(), keywordNodes );
        if ( keywordNodes != null ) {
            for ( int i = 0; i < keywordNodes.getLength(); i++ ) {
                String word = getAttributeValue( keywordNodes.item( i ), DDMS_NAMESPACE, VALUE_ATTRIBUTE );
                if ( StringUtils.isNotBlank( word ) ) {
                    keywordCounter.compute( word, ( k, v ) -> {
                        return Long.valueOf( v == null ? 1L : v.longValue() + 1L );
                    } );
                }
            }
        }
    }

    private void populateCategory( String sourceId, ConcurrentHashMap<CompoundCategoryIdentifierType, Long> categoryCounter, Metacard metacard,
            XPathHelper xpathHelper ) throws XPathExpressionException {
        NodeList categoryNode = (NodeList) xpathHelper.evaluate( XPATH_CATEGORY, XPathConstants.NODESET );
        LOGGER.trace( "Category info from site {} record {}: {}", sourceId, metacard.getId(), categoryNode );
        if ( categoryNode != null ) {
            for ( int i = 0; i < categoryNode.getLength(); i++ ) {
                CompoundCategoryIdentifierType catType = new CompoundCategoryIdentifierType();

                catType.setCode( getAttributeValue( categoryNode.item( i ), DDMS_NAMESPACE, "code" ) );
                catType.setQualifier( getAttributeValue( categoryNode.item( i ), DDMS_NAMESPACE, "qualifier" ) );
                catType.setLabel( getAttributeValue( categoryNode.item( i ), DDMS_NAMESPACE, "label" ) );

                categoryCounter.compute( catType, ( k, v ) -> {
                    return Long.valueOf( v == null ? 1L : v.longValue() + 1L );
                } );
            }
        }
    }

    private void populateType( String sourceId, ConcurrentHashMap<CompoundTypeIdentifierType, Long> typeCounter, Metacard metacard, XPathHelper xpathHelper )
            throws XPathExpressionException {
        NodeList typeNode = (NodeList) xpathHelper.evaluate( XPATH_TYPE, XPathConstants.NODESET, new NamespaceMapImpl( namespaceMap ) );
        LOGGER.trace( "Type info from site {} record {}: {}", sourceId, metacard.getId(), typeNode );
        if ( typeNode != null ) {
            for ( int i = 0; i < typeNode.getLength(); i++ ) {
                CompoundTypeIdentifierType type = new CompoundTypeIdentifierType();

                type.setQualifier( getAttributeValue( typeNode.item( i ), DDMS_NAMESPACE, QUALIFIER_ATTRIBUTE ) );
                type.setValue( getAttributeValue( typeNode.item( i ), DDMS_NAMESPACE, VALUE_ATTRIBUTE ) );

                typeCounter.compute( type, ( k, v ) -> {
                    return Long.valueOf( v == null ? 1L : v.longValue() + 1L );
                } );
            }
        }
    }

    private void populateSecurityCoverage( String sourceId, ConcurrentHashMap<SecurityType, Long> secCounter, Metacard metacard, XPathHelper xpathHelper )
            throws XPathExpressionException {
        Node securityNode = (Node) xpathHelper.evaluate( XPATH_SECURITY, XPathConstants.NODE );
        LOGGER.trace( "Security info from site {} record {}: {}", sourceId, metacard.getId(), securityNode );
        if ( securityNode != null ) {
            SecurityType secType = new SecurityType();
            String value = getAttributeValue( securityNode, ICISM_NAMESPACE, "classification" );
            if ( value != null ) {
                secType.setClassification( CVEnumISMClassificationAll.fromValue( value ) );
            }

            value = getAttributeValue( securityNode, ICISM_NAMESPACE, "ownerProducer" );
            if ( value != null ) {
                secType.setOwnerProducer( Arrays.asList( value.split( " " ) ) );
            }

            value = getAttributeValue( securityNode, ICISM_NAMESPACE, "releasableTo" );
            if ( value != null ) {
                secType.setReleasableTo( Arrays.asList( value.split( " " ) ) );
            }

            value = getAttributeValue( securityNode, ICISM_NAMESPACE, "disseminationControls" );
            if ( value != null ) {
                secType.setDisseminationControls( Arrays.asList( value.split( " " ) ) );
            }

            secCounter.compute( secType, ( k, v ) -> {
                return Long.valueOf( v == null ? 1L : v.longValue() + 1L );
            } );
        }
    }

    private String getAttributeValue( Node item, String namespace, String attribute ) {
        String attributeValue = null;
        Node node = item.getAttributes().getNamedItemNS( namespace, attribute );
        if ( node != null ) {
            attributeValue = node.getTextContent();
        }
        return attributeValue;
    }

    private void setTypeDetails( ResourceType resource, List<Entry<CompoundTypeIdentifierType, Long>> typeDetails ) {
        List<CompoundTypeIdentifierType> typeList = resource.getType();
        typeDetails.forEach( entry -> {
            CompoundTypeIdentifierType type = entry.getKey();
            type.getOtherAttributes().put( new QName( DESCRIBE_EXT_NAMESPACE, COUNT_ATTRIBUTE ), ((Long) entry.getValue()).toString() );
            typeList.add( type );
        } );

    }

    private void setCategoryDetails( ResourceType resource, List<Entry<CompoundCategoryIdentifierType, Long>> categoryDetails ) {
        List<SubjectType> subjectList = resource.getSubjectCoverage();
        if ( subjectList.isEmpty() ) {
            subjectList.add( new SubjectType() );
        }
        SubjectType subject = subjectList.get( 0 );
        List<Serializable> categoryList = subject.getCategoryOrKeywordOrProductionMetric();
        categoryDetails.forEach( ( entry ) -> {
            CompoundCategoryIdentifierType cat = entry.getKey();
            cat.getOtherAttributes().put( new QName( DESCRIBE_EXT_NAMESPACE, COUNT_ATTRIBUTE ), ((Long) entry.getValue()).toString() );
            categoryList.add( cat );
        } );
    }

    private void setSecurityDetails( ContentCollectionType collection, List<Entry<SecurityType, Long>> securityDetails ) {
        SecurityCoverage secCoverage = new SecurityCoverage();
        collection.setSecurityCoverage( secCoverage );
        List<SecurityType> secList = secCoverage.getSecurityMarkings();

        securityDetails.forEach( entry -> {
            SecurityType secType = entry.getKey();
            secType.setCount( entry.getValue() );
            secList.add( secType );
        } );
    }

    private void setSourceTypes( ResourceType resource, List<Entry<QualifierValueHolder, Long>> sources ) {
        sources.forEach( entry -> {
            CompoundSourceIdentifierType sourceType = new CompoundSourceIdentifierType();
            QualifierValueHolder qual = entry.getKey();
            sourceType.setQualifier( qual.getQualifier() );
            sourceType.setValue( qual.getValue() );
            sourceType.getOtherAttributes().put( new QName( DESCRIBE_EXT_NAMESPACE, COUNT_ATTRIBUTE ), ((Long) entry.getValue()).toString() );
            resource.getSource().add( sourceType );
        } );
    }

    private void setKeywords( List<Entry<String, Long>> topResults, List<SubjectType> subjectCoverage ) {
        if ( subjectCoverage.isEmpty() ) {
            subjectCoverage.add( new SubjectType() );
        }
        SubjectType subject = subjectCoverage.get( 0 );
        List<Serializable> keywordList = subject.getCategoryOrKeywordOrProductionMetric();
        topResults.forEach( ( entry ) -> {
            CompoundKeywordIdentifierType keyword = new CompoundKeywordIdentifierType();
            keyword.setValue( (String) entry.getKey() );
            keyword.getOtherAttributes().put( new QName( DESCRIBE_EXT_NAMESPACE, COUNT_ATTRIBUTE ), ((Long) entry.getValue()).toString() );
            keywordList.add( keyword );
        } );
    }

    private void setGeospatialCoverage( List<PlaceType> geospatialCoverage, Envelope geoEnvelope ) {
        if ( !geoEnvelope.isNull() ) {
            PlaceType placeType = new PlaceType();
            geospatialCoverage.add( placeType );
            BoundingGeometryType boundingGeo = new BoundingGeometryType();
            placeType.setGeographicIdentifierOrBoundingGeometryOrPostalAddress( Arrays.asList( new Serializable[] { boundingGeo } ) );
            EnvelopeType envelope = new EnvelopeType();
            boundingGeo.setPolygonOrPointOrEnvelope( Arrays.asList( new Serializable[] { envelope } ) );
            envelope.setId( UUID.randomUUID().toString() );
            LowerCorner lowerCorner = new LowerCorner();
            DirectPositionType lcPosition = new DirectPositionType();
            lcPosition.setSrsName( "http://metadata.dod.mil/mdr/ns/GSIP/crs/WGS84E_2D" );
            lcPosition.setValue( Arrays.asList( new Double[] { Double.valueOf( geoEnvelope.getMinY() ), Double.valueOf( geoEnvelope.getMinX() ) } ) );
            lowerCorner.setPos( lcPosition );
            UpperCorner upperCorner = new UpperCorner();
            DirectPositionType ucPosition = new DirectPositionType();
            ucPosition.setSrsName( "http://metadata.dod.mil/mdr/ns/GSIP/crs/WGS84E_2D" );
            ucPosition.setValue( Arrays.asList( new Double[] { Double.valueOf( geoEnvelope.getMaxY() ), Double.valueOf( geoEnvelope.getMaxX() ) } ) );
            upperCorner.setPos( ucPosition );
            envelope.setUpperCorner( upperCorner );
            envelope.setLowerCorner( lowerCorner );
        }

    }

    protected void setContentTypes( ResourceType resource, List<Entry<String, Long>> contentTypeDetails ) {
        if ( !contentTypeDetails.isEmpty() ) {
            contentTypeDetails.forEach( entry -> {
                CompoundTypeIdentifierType type = new CompoundTypeIdentifierType();
                type.setQualifier( "dib-content-type" );
                type.setValue( entry.getKey() );
                type.getOtherAttributes().put( new QName( DESCRIBE_EXT_NAMESPACE, COUNT_ATTRIBUTE ), ((Long) entry.getValue()).toString() );
                resource.getType().add( type );
            } );
        }
    }

    protected void setMimeTypes( ContentCollectionType collection, List<Entry<String, Long>> mimeTypeDetails ) {
        if ( !mimeTypeDetails.isEmpty() ) {
            MimeTypes types = new MimeTypes();
            mimeTypeDetails.forEach( entry -> {
                MIMETypeType mimeType = new MIMETypeType();
                mimeType.setValue( entry.getKey() );
                mimeType.setCount( entry.getValue() );
                types.getMimeType().add( mimeType );
            } );
            collection.setMimeTypes( types );
        }

    }

    protected void setTemporalCoverage( List<TimePeriodType> timeList, Map<String, TemporalCoverageHolder> timeMap ) {
        timeMap.forEach( ( k, v ) -> {
            TimePeriodType tp = new TimePeriodType();
            TemporalCoverageHolder tcHolder = (TemporalCoverageHolder) v;
            tp.setName( tcHolder.getLabel() );
            tp.setStart( Arrays.asList( new String[] { df.format( tcHolder.getStartDate() ) } ) );
            tp.setEnd( Arrays.asList( new String[] { df.format( tcHolder.getEndDate() ) } ) );
            timeList.add( tp );
        } );
    }

    protected void setRecordRate( MetricsType metrics, Map<String, TemporalCoverageHolder> timeMap ) {
        TemporalCoverageHolder tc = timeMap.containsKey( "modified" ) ? (TemporalCoverageHolder) timeMap.get( "modified" )
                : (timeMap.containsKey( "effective" ) ? (TemporalCoverageHolder) timeMap.get( "effective" )
                        : (TemporalCoverageHolder) timeMap.get( "created" ));
        try {
            if ( tc != null ) {
                Date startDate = tc.getStartDate();
                if ( startDate != null ) {
                    long totalHits = metrics.getCount();
                    LocalDateTime start = LocalDateTime.ofInstant( startDate.toInstant(), ZoneId.systemDefault() );
                    Duration duration = Duration.between( start, LocalDateTime.now() );
                    RecordRateType rate = new RecordRateType();
                    metrics.setRecordRate( rate );
                    long dur = totalHits / duration.toHours();
                    if ( dur < 15L ) {
                        dur = totalHits / duration.toDays();
                        if ( dur < 4L ) {
                            dur = totalHits * 30L / duration.toDays();
                            if ( dur < 10L ) {
                                dur = totalHits * 365L / duration.toDays();
                                rate.setFrequency( "Yearly" );
                            } else {
                                rate.setFrequency( "Monthly" );
                            }
                        } else {
                            rate.setFrequency( "Daily" );
                        }
                    } else if ( totalHits > 1000L ) {
                        dur = duration.toMinutes();
                        if ( totalHits > 1000L ) {
                            dur = duration.toMillis() / 1000L;
                            rate.setFrequency( "Second" );
                        } else {
                            rate.setFrequency( "Minute" );
                        }
                    } else {
                        rate.setFrequency( "Hourly" );
                    }

                    rate.setValue( (int) dur );
                }
            }
        } catch ( Exception e ) {
            LOGGER.warn( "Could not set record rate: {}", e.getMessage(), e );
        }
    }

    protected Filter getFilter( Date startDate, Date endDate, String keywords ) {
        LOGGER.debug( "Creating query for date type {} and date range {} through {} and keywords {}", generatorConfig.getDateType(), startDate, endDate,
                keywords );
        Filter filter = null;
        if ( startDate != null ) {
            filter = this.filterBuilder.attribute( generatorConfig.getDateType() ).during().dates( startDate, endDate );
        }
        if ( StringUtils.isNotBlank( keywords ) ) {
            filter = filter == null ? filterBuilder.attribute( Metacard.ANY_TEXT ).like().text( keywords )
                    : filterBuilder.allOf( filter, filterBuilder.attribute( Metacard.ANY_TEXT ).like().text( keywords ) );
        }
        return filter;
    }

    protected SortBy getSortBy() {
        SortByImpl sortBy = new SortByImpl( generatorConfig.getSortDateType(), SortOrder.ASCENDING );
        return sortBy;
    }

    protected void updateDate( String label, Date date, Map<String, TemporalCoverageHolder> map ) {
        if ( date != null ) {
            TemporalCoverageHolder tCoverage = (TemporalCoverageHolder) map.get( label );
            if ( tCoverage == null ) {
                tCoverage = new TemporalCoverageHolder();
                tCoverage.setLabel( label );
                map.put( label, tCoverage );
            }

            tCoverage.updateDate( date );
        }

    }

    protected Subject getSystemSubject() {
        return (Subject) Security.runAsAdmin( () -> {
            return Security.getInstance().getSystemSubject();
        } );
    }

    private class QualifierValueHolder {
        private String qualifier = null;
        private String value = null;

        QualifierValueHolder( String q, String v ) {
            this.qualifier = q;
            this.value = v;
        }

        public String getQualifier() {
            return qualifier;
        }

        public String getValue() {
            return value;
        }

        @Override
        public int hashCode() {
            return Objects.hash( this.qualifier, this.value );
        }

        @Override
        public boolean equals( Object obj ) {
            if ( obj instanceof QualifierValueHolder ) {
                QualifierValueHolder qual2 = (QualifierValueHolder) obj;
                return StringUtils.equals( qual2.getQualifier(), this.qualifier ) && StringUtils.equals( qual2.getValue(), this.value );
            } else {
                return false;
            }
        }
    }

}