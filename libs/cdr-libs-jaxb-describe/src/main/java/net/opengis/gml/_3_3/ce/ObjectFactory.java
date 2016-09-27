//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package net.opengis.gml._3_3.ce;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import net.opengis.gml.v_3_2_1.AbstractCurveType;
import net.opengis.gml.v_3_2_1.AbstractSurfaceType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.opengis.gml._3_3.ce package. 
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

    private final static QName _SimplePolygon_QNAME = new QName("http://www.opengis.net/gml/3.3/ce", "SimplePolygon");
    private final static QName _AbstractSimplePolygon_QNAME = new QName("http://www.opengis.net/gml/3.3/ce", "AbstractSimplePolygon");
    private final static QName _SimpleRectangle_QNAME = new QName("http://www.opengis.net/gml/3.3/ce", "SimpleRectangle");
    private final static QName _SimpleTriangle_QNAME = new QName("http://www.opengis.net/gml/3.3/ce", "SimpleTriangle");
    private final static QName _SimpleArcString_QNAME = new QName("http://www.opengis.net/gml/3.3/ce", "SimpleArcString");
    private final static QName _AbstractSimpleArcString_QNAME = new QName("http://www.opengis.net/gml/3.3/ce", "AbstractSimpleArcString");
    private final static QName _SimpleArc_QNAME = new QName("http://www.opengis.net/gml/3.3/ce", "SimpleArc");
    private final static QName _SimpleArcByCenterPoint_QNAME = new QName("http://www.opengis.net/gml/3.3/ce", "SimpleArcByCenterPoint");
    private final static QName _SimpleArcStringByBulge_QNAME = new QName("http://www.opengis.net/gml/3.3/ce", "SimpleArcStringByBulge");
    private final static QName _SimpleArcByBulge_QNAME = new QName("http://www.opengis.net/gml/3.3/ce", "SimpleArcByBulge");
    private final static QName _SimpleCircle_QNAME = new QName("http://www.opengis.net/gml/3.3/ce", "SimpleCircle");
    private final static QName _SimpleCircleByCenterPoint_QNAME = new QName("http://www.opengis.net/gml/3.3/ce", "SimpleCircleByCenterPoint");
    private final static QName _SimpleMultiPoint_QNAME = new QName("http://www.opengis.net/gml/3.3/ce", "SimpleMultiPoint");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.opengis.gml._3_3.ce
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SimplePolygonType }
     * 
     */
    public SimplePolygonType createSimplePolygonType() {
        return new SimplePolygonType();
    }

    /**
     * Create an instance of {@link SimpleRectangleType }
     * 
     */
    public SimpleRectangleType createSimpleRectangleType() {
        return new SimpleRectangleType();
    }

    /**
     * Create an instance of {@link SimpleTriangleType }
     * 
     */
    public SimpleTriangleType createSimpleTriangleType() {
        return new SimpleTriangleType();
    }

    /**
     * Create an instance of {@link SimpleArcStringType }
     * 
     */
    public SimpleArcStringType createSimpleArcStringType() {
        return new SimpleArcStringType();
    }

    /**
     * Create an instance of {@link SimpleArcType }
     * 
     */
    public SimpleArcType createSimpleArcType() {
        return new SimpleArcType();
    }

    /**
     * Create an instance of {@link SimpleArcByCenterPointType }
     * 
     */
    public SimpleArcByCenterPointType createSimpleArcByCenterPointType() {
        return new SimpleArcByCenterPointType();
    }

    /**
     * Create an instance of {@link SimpleArcStringByBulgeType }
     * 
     */
    public SimpleArcStringByBulgeType createSimpleArcStringByBulgeType() {
        return new SimpleArcStringByBulgeType();
    }

    /**
     * Create an instance of {@link SimpleArcByBulgeType }
     * 
     */
    public SimpleArcByBulgeType createSimpleArcByBulgeType() {
        return new SimpleArcByBulgeType();
    }

    /**
     * Create an instance of {@link SimpleMultiPointType }
     * 
     */
    public SimpleMultiPointType createSimpleMultiPointType() {
        return new SimpleMultiPointType();
    }

    /**
     * Create an instance of {@link MultiPointPropertyType }
     * 
     */
    public MultiPointPropertyType createMultiPointPropertyType() {
        return new MultiPointPropertyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimplePolygonType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml/3.3/ce", name = "SimplePolygon", substitutionHeadNamespace = "http://www.opengis.net/gml/3.3/ce", substitutionHeadName = "AbstractSimplePolygon")
    public JAXBElement<SimplePolygonType> createSimplePolygon(SimplePolygonType value) {
        return new JAXBElement<SimplePolygonType>(_SimplePolygon_QNAME, SimplePolygonType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractSurfaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml/3.3/ce", name = "AbstractSimplePolygon", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "AbstractSurface")
    public JAXBElement<AbstractSurfaceType> createAbstractSimplePolygon(AbstractSurfaceType value) {
        return new JAXBElement<AbstractSurfaceType>(_AbstractSimplePolygon_QNAME, AbstractSurfaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleRectangleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml/3.3/ce", name = "SimpleRectangle", substitutionHeadNamespace = "http://www.opengis.net/gml/3.3/ce", substitutionHeadName = "AbstractSimplePolygon")
    public JAXBElement<SimpleRectangleType> createSimpleRectangle(SimpleRectangleType value) {
        return new JAXBElement<SimpleRectangleType>(_SimpleRectangle_QNAME, SimpleRectangleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleTriangleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml/3.3/ce", name = "SimpleTriangle", substitutionHeadNamespace = "http://www.opengis.net/gml/3.3/ce", substitutionHeadName = "AbstractSimplePolygon")
    public JAXBElement<SimpleTriangleType> createSimpleTriangle(SimpleTriangleType value) {
        return new JAXBElement<SimpleTriangleType>(_SimpleTriangle_QNAME, SimpleTriangleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleArcStringType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml/3.3/ce", name = "SimpleArcString", substitutionHeadNamespace = "http://www.opengis.net/gml/3.3/ce", substitutionHeadName = "AbstractSimpleArcString")
    public JAXBElement<SimpleArcStringType> createSimpleArcString(SimpleArcStringType value) {
        return new JAXBElement<SimpleArcStringType>(_SimpleArcString_QNAME, SimpleArcStringType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractCurveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml/3.3/ce", name = "AbstractSimpleArcString", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "AbstractCurve")
    public JAXBElement<AbstractCurveType> createAbstractSimpleArcString(AbstractCurveType value) {
        return new JAXBElement<AbstractCurveType>(_AbstractSimpleArcString_QNAME, AbstractCurveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleArcType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml/3.3/ce", name = "SimpleArc", substitutionHeadNamespace = "http://www.opengis.net/gml/3.3/ce", substitutionHeadName = "AbstractSimpleArcString")
    public JAXBElement<SimpleArcType> createSimpleArc(SimpleArcType value) {
        return new JAXBElement<SimpleArcType>(_SimpleArc_QNAME, SimpleArcType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleArcByCenterPointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml/3.3/ce", name = "SimpleArcByCenterPoint", substitutionHeadNamespace = "http://www.opengis.net/gml/3.3/ce", substitutionHeadName = "AbstractSimpleArcString")
    public JAXBElement<SimpleArcByCenterPointType> createSimpleArcByCenterPoint(SimpleArcByCenterPointType value) {
        return new JAXBElement<SimpleArcByCenterPointType>(_SimpleArcByCenterPoint_QNAME, SimpleArcByCenterPointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleArcStringByBulgeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml/3.3/ce", name = "SimpleArcStringByBulge", substitutionHeadNamespace = "http://www.opengis.net/gml/3.3/ce", substitutionHeadName = "AbstractSimpleArcString")
    public JAXBElement<SimpleArcStringByBulgeType> createSimpleArcStringByBulge(SimpleArcStringByBulgeType value) {
        return new JAXBElement<SimpleArcStringByBulgeType>(_SimpleArcStringByBulge_QNAME, SimpleArcStringByBulgeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleArcByBulgeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml/3.3/ce", name = "SimpleArcByBulge", substitutionHeadNamespace = "http://www.opengis.net/gml/3.3/ce", substitutionHeadName = "AbstractSimpleArcString")
    public JAXBElement<SimpleArcByBulgeType> createSimpleArcByBulge(SimpleArcByBulgeType value) {
        return new JAXBElement<SimpleArcByBulgeType>(_SimpleArcByBulge_QNAME, SimpleArcByBulgeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleArcType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml/3.3/ce", name = "SimpleCircle", substitutionHeadNamespace = "http://www.opengis.net/gml/3.3/ce", substitutionHeadName = "AbstractSimpleArcString")
    public JAXBElement<SimpleArcType> createSimpleCircle(SimpleArcType value) {
        return new JAXBElement<SimpleArcType>(_SimpleCircle_QNAME, SimpleArcType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleArcByCenterPointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml/3.3/ce", name = "SimpleCircleByCenterPoint", substitutionHeadNamespace = "http://www.opengis.net/gml/3.3/ce", substitutionHeadName = "AbstractSimpleArcString")
    public JAXBElement<SimpleArcByCenterPointType> createSimpleCircleByCenterPoint(SimpleArcByCenterPointType value) {
        return new JAXBElement<SimpleArcByCenterPointType>(_SimpleCircleByCenterPoint_QNAME, SimpleArcByCenterPointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleMultiPointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml/3.3/ce", name = "SimpleMultiPoint", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "AbstractGeometricAggregate")
    public JAXBElement<SimpleMultiPointType> createSimpleMultiPoint(SimpleMultiPointType value) {
        return new JAXBElement<SimpleMultiPointType>(_SimpleMultiPoint_QNAME, SimpleMultiPointType.class, null, value);
    }

}