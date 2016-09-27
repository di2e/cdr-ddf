//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package us.gov.ic.ism;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the us.gov.ic.ism package. 
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

    private final static QName _Notice_QNAME = new QName("urn:us:gov:ic:ism", "Notice");
    private final static QName _NoticeExternal_QNAME = new QName("urn:us:gov:ic:ism", "NoticeExternal");
    private final static QName _NoticeList_QNAME = new QName("urn:us:gov:ic:ism", "NoticeList");
    private final static QName _NoticeExternalList_QNAME = new QName("urn:us:gov:ic:ism", "NoticeExternalList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: us.gov.ic.ism
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NoticeType }
     * 
     */
    public NoticeType createNoticeType() {
        return new NoticeType();
    }

    /**
     * Create an instance of {@link NoticeExternalType }
     * 
     */
    public NoticeExternalType createNoticeExternalType() {
        return new NoticeExternalType();
    }

    /**
     * Create an instance of {@link NoticeListType }
     * 
     */
    public NoticeListType createNoticeListType() {
        return new NoticeListType();
    }

    /**
     * Create an instance of {@link NoticeExternalListType }
     * 
     */
    public NoticeExternalListType createNoticeExternalListType() {
        return new NoticeExternalListType();
    }

    /**
     * Create an instance of {@link NoticeText }
     * 
     */
    public NoticeText createNoticeText() {
        return new NoticeText();
    }

    /**
     * Create an instance of {@link LongStringWithSecurityType }
     * 
     */
    public LongStringWithSecurityType createLongStringWithSecurityType() {
        return new LongStringWithSecurityType();
    }

    /**
     * Create an instance of {@link NoticeBaseType }
     * 
     */
    public NoticeBaseType createNoticeBaseType() {
        return new NoticeBaseType();
    }

    /**
     * Create an instance of {@link ShortStringWithSecurityType }
     * 
     */
    public ShortStringWithSecurityType createShortStringWithSecurityType() {
        return new ShortStringWithSecurityType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoticeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:us:gov:ic:ism", name = "Notice")
    public JAXBElement<NoticeType> createNotice(NoticeType value) {
        return new JAXBElement<NoticeType>(_Notice_QNAME, NoticeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoticeExternalType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:us:gov:ic:ism", name = "NoticeExternal")
    public JAXBElement<NoticeExternalType> createNoticeExternal(NoticeExternalType value) {
        return new JAXBElement<NoticeExternalType>(_NoticeExternal_QNAME, NoticeExternalType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoticeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:us:gov:ic:ism", name = "NoticeList")
    public JAXBElement<NoticeListType> createNoticeList(NoticeListType value) {
        return new JAXBElement<NoticeListType>(_NoticeList_QNAME, NoticeListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoticeExternalListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:us:gov:ic:ism", name = "NoticeExternalList")
    public JAXBElement<NoticeExternalListType> createNoticeExternalList(NoticeExternalListType value) {
        return new JAXBElement<NoticeExternalListType>(_NoticeExternalList_QNAME, NoticeExternalListType.class, null, value);
    }

}
