//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package org.isotc211._19112;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.isotc211._19112 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SICollection_QNAME = new QName("http://www.isotc211.org/19112", "SI_Collection");
    private final static QName _SISpatialReferenceSystemUsingGeographicIdentifiers_QNAME = new QName("http://www.isotc211.org/19112", "SI_SpatialReferenceSystemUsingGeographicIdentifiers");
    private final static QName _SILocationType_QNAME = new QName("http://www.isotc211.org/19112", "SI_LocationType");
    private final static QName _SIGazetteer_QNAME = new QName("http://www.isotc211.org/19112", "SI_Gazetteer");
    private final static QName _SILocationInstance_QNAME = new QName("http://www.isotc211.org/19112", "SI_LocationInstance");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.isotc211._19112
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SICollectionType }
     * 
     */
    public SICollectionType createSICollectionType() {
        return new SICollectionType();
    }

    /**
     * Create an instance of {@link SILocationInstanceType }
     * 
     */
    public SILocationInstanceType createSILocationInstanceType() {
        return new SILocationInstanceType();
    }

    /**
     * Create an instance of {@link SISpatialReferenceSystemUsingGeographicIdentifiersType }
     * 
     */
    public SISpatialReferenceSystemUsingGeographicIdentifiersType createSISpatialReferenceSystemUsingGeographicIdentifiersType() {
        return new SISpatialReferenceSystemUsingGeographicIdentifiersType();
    }

    /**
     * Create an instance of {@link SILocationTypeType }
     * 
     */
    public SILocationTypeType createSILocationTypeType() {
        return new SILocationTypeType();
    }

    /**
     * Create an instance of {@link SIGazetteerType }
     * 
     */
    public SIGazetteerType createSIGazetteerType() {
        return new SIGazetteerType();
    }

    /**
     * Create an instance of {@link AlternativeGeographicIdentifier }
     * 
     */
    public AlternativeGeographicIdentifier createAlternativeGeographicIdentifier() {
        return new AlternativeGeographicIdentifier();
    }

    /**
     * Create an instance of {@link LanguageStringType }
     * 
     */
    public LanguageStringType createLanguageStringType() {
        return new LanguageStringType();
    }

    /**
     * Create an instance of {@link AlternativeGeographicIdentifiersPropertyType }
     * 
     */
    public AlternativeGeographicIdentifiersPropertyType createAlternativeGeographicIdentifiersPropertyType() {
        return new AlternativeGeographicIdentifiersPropertyType();
    }

    /**
     * Create an instance of {@link SICollectionType.FeatureMember }
     * 
     */
    public SICollectionType.FeatureMember createSICollectionTypeFeatureMember() {
        return new SICollectionType.FeatureMember();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SICollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/19112", name = "SI_Collection", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "AbstractFeature")
    public JAXBElement<SICollectionType> createSICollection(SICollectionType value) {
        return new JAXBElement<SICollectionType>(_SICollection_QNAME, SICollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SISpatialReferenceSystemUsingGeographicIdentifiersType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/19112", name = "SI_SpatialReferenceSystemUsingGeographicIdentifiers", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "AbstractFeature")
    public JAXBElement<SISpatialReferenceSystemUsingGeographicIdentifiersType> createSISpatialReferenceSystemUsingGeographicIdentifiers(SISpatialReferenceSystemUsingGeographicIdentifiersType value) {
        return new JAXBElement<SISpatialReferenceSystemUsingGeographicIdentifiersType>(_SISpatialReferenceSystemUsingGeographicIdentifiers_QNAME, SISpatialReferenceSystemUsingGeographicIdentifiersType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SILocationTypeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/19112", name = "SI_LocationType", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "AbstractFeature")
    public JAXBElement<SILocationTypeType> createSILocationType(SILocationTypeType value) {
        return new JAXBElement<SILocationTypeType>(_SILocationType_QNAME, SILocationTypeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIGazetteerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/19112", name = "SI_Gazetteer", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "AbstractFeature")
    public JAXBElement<SIGazetteerType> createSIGazetteer(SIGazetteerType value) {
        return new JAXBElement<SIGazetteerType>(_SIGazetteer_QNAME, SIGazetteerType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SILocationInstanceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/19112", name = "SI_LocationInstance", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "AbstractFeature")
    public JAXBElement<SILocationInstanceType> createSILocationInstance(SILocationInstanceType value) {
        return new JAXBElement<SILocationInstanceType>(_SILocationInstance_QNAME, SILocationInstanceType.class, null, value);
    }

}
