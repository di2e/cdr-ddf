//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package org.isotc211._2005.gco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jvnet.jaxb2_commons.lang.CopyStrategy2;
import org.jvnet.jaxb2_commons.lang.CopyTo2;
import org.jvnet.jaxb2_commons.lang.Equals2;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy2;
import org.jvnet.jaxb2_commons.lang.HashCode2;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy2;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBMergeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.MergeFrom2;
import org.jvnet.jaxb2_commons.lang.MergeStrategy2;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for Date_PropertyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Date_PropertyType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice minOccurs="0"&gt;
 *         &lt;element ref="{http://www.isotc211.org/2005/gco}Date"/&gt;
 *         &lt;element ref="{http://www.isotc211.org/2005/gco}DateTime"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute ref="{http://www.isotc211.org/2005/gco}nilReason"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Date_PropertyType", propOrder = {
    "date",
    "dateTime"
})
public class DatePropertyType implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Date", nillable = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String date;
    @XmlElement(name = "DateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateTime;
    @XmlAttribute(name = "nilReason", namespace = "http://www.isotc211.org/2005/gco")
    protected List<String> nilReason;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    public boolean isSetDate() {
        return (this.date!= null);
    }

    /**
     * Gets the value of the dateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateTime() {
        return dateTime;
    }

    /**
     * Sets the value of the dateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateTime(XMLGregorianCalendar value) {
        this.dateTime = value;
    }

    public boolean isSetDateTime() {
        return (this.dateTime!= null);
    }

    /**
     * Gets the value of the nilReason property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nilReason property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNilReason().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNilReason() {
        if (nilReason == null) {
            nilReason = new ArrayList<String>();
        }
        return this.nilReason;
    }

    public boolean isSetNilReason() {
        return ((this.nilReason!= null)&&(!this.nilReason.isEmpty()));
    }

    public void unsetNilReason() {
        this.nilReason = null;
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        {
            String theDate;
            theDate = this.getDate();
            strategy.appendField(locator, this, "date", buffer, theDate, this.isSetDate());
        }
        {
            XMLGregorianCalendar theDateTime;
            theDateTime = this.getDateTime();
            strategy.appendField(locator, this, "dateTime", buffer, theDateTime, this.isSetDateTime());
        }
        {
            List<String> theNilReason;
            theNilReason = (this.isSetNilReason()?this.getNilReason():null);
            strategy.appendField(locator, this, "nilReason", buffer, theNilReason, this.isSetNilReason());
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy2 strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final DatePropertyType that = ((DatePropertyType) object);
        {
            String lhsDate;
            lhsDate = this.getDate();
            String rhsDate;
            rhsDate = that.getDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "date", lhsDate), LocatorUtils.property(thatLocator, "date", rhsDate), lhsDate, rhsDate, this.isSetDate(), that.isSetDate())) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsDateTime;
            lhsDateTime = this.getDateTime();
            XMLGregorianCalendar rhsDateTime;
            rhsDateTime = that.getDateTime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dateTime", lhsDateTime), LocatorUtils.property(thatLocator, "dateTime", rhsDateTime), lhsDateTime, rhsDateTime, this.isSetDateTime(), that.isSetDateTime())) {
                return false;
            }
        }
        {
            List<String> lhsNilReason;
            lhsNilReason = (this.isSetNilReason()?this.getNilReason():null);
            List<String> rhsNilReason;
            rhsNilReason = (that.isSetNilReason()?that.getNilReason():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "nilReason", lhsNilReason), LocatorUtils.property(thatLocator, "nilReason", rhsNilReason), lhsNilReason, rhsNilReason, this.isSetNilReason(), that.isSetNilReason())) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy2 strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy2 strategy) {
        int currentHashCode = 1;
        {
            String theDate;
            theDate = this.getDate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "date", theDate), currentHashCode, theDate, this.isSetDate());
        }
        {
            XMLGregorianCalendar theDateTime;
            theDateTime = this.getDateTime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dateTime", theDateTime), currentHashCode, theDateTime, this.isSetDateTime());
        }
        {
            List<String> theNilReason;
            theNilReason = (this.isSetNilReason()?this.getNilReason():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "nilReason", theNilReason), currentHashCode, theNilReason, this.isSetNilReason());
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy2 strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy2 strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy2 strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof DatePropertyType) {
            final DatePropertyType copy = ((DatePropertyType) draftCopy);
            {
                Boolean dateShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDate());
                if (dateShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceDate;
                    sourceDate = this.getDate();
                    String copyDate = ((String) strategy.copy(LocatorUtils.property(locator, "date", sourceDate), sourceDate, this.isSetDate()));
                    copy.setDate(copyDate);
                } else {
                    if (dateShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.date = null;
                    }
                }
            }
            {
                Boolean dateTimeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDateTime());
                if (dateTimeShouldBeCopiedAndSet == Boolean.TRUE) {
                    XMLGregorianCalendar sourceDateTime;
                    sourceDateTime = this.getDateTime();
                    XMLGregorianCalendar copyDateTime = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "dateTime", sourceDateTime), sourceDateTime, this.isSetDateTime()));
                    copy.setDateTime(copyDateTime);
                } else {
                    if (dateTimeShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.dateTime = null;
                    }
                }
            }
            {
                Boolean nilReasonShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetNilReason());
                if (nilReasonShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<String> sourceNilReason;
                    sourceNilReason = (this.isSetNilReason()?this.getNilReason():null);
                    @SuppressWarnings("unchecked")
                    List<String> copyNilReason = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "nilReason", sourceNilReason), sourceNilReason, this.isSetNilReason()));
                    copy.unsetNilReason();
                    if (copyNilReason!= null) {
                        List<String> uniqueNilReasonl = copy.getNilReason();
                        uniqueNilReasonl.addAll(copyNilReason);
                    }
                } else {
                    if (nilReasonShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetNilReason();
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new DatePropertyType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        if (right instanceof DatePropertyType) {
            final DatePropertyType target = this;
            final DatePropertyType leftObject = ((DatePropertyType) left);
            final DatePropertyType rightObject = ((DatePropertyType) right);
            {
                Boolean dateShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDate(), rightObject.isSetDate());
                if (dateShouldBeMergedAndSet == Boolean.TRUE) {
                    String lhsDate;
                    lhsDate = leftObject.getDate();
                    String rhsDate;
                    rhsDate = rightObject.getDate();
                    String mergedDate = ((String) strategy.merge(LocatorUtils.property(leftLocator, "date", lhsDate), LocatorUtils.property(rightLocator, "date", rhsDate), lhsDate, rhsDate, leftObject.isSetDate(), rightObject.isSetDate()));
                    target.setDate(mergedDate);
                } else {
                    if (dateShouldBeMergedAndSet == Boolean.FALSE) {
                        target.date = null;
                    }
                }
            }
            {
                Boolean dateTimeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDateTime(), rightObject.isSetDateTime());
                if (dateTimeShouldBeMergedAndSet == Boolean.TRUE) {
                    XMLGregorianCalendar lhsDateTime;
                    lhsDateTime = leftObject.getDateTime();
                    XMLGregorianCalendar rhsDateTime;
                    rhsDateTime = rightObject.getDateTime();
                    XMLGregorianCalendar mergedDateTime = ((XMLGregorianCalendar) strategy.merge(LocatorUtils.property(leftLocator, "dateTime", lhsDateTime), LocatorUtils.property(rightLocator, "dateTime", rhsDateTime), lhsDateTime, rhsDateTime, leftObject.isSetDateTime(), rightObject.isSetDateTime()));
                    target.setDateTime(mergedDateTime);
                } else {
                    if (dateTimeShouldBeMergedAndSet == Boolean.FALSE) {
                        target.dateTime = null;
                    }
                }
            }
            {
                Boolean nilReasonShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetNilReason(), rightObject.isSetNilReason());
                if (nilReasonShouldBeMergedAndSet == Boolean.TRUE) {
                    List<String> lhsNilReason;
                    lhsNilReason = (leftObject.isSetNilReason()?leftObject.getNilReason():null);
                    List<String> rhsNilReason;
                    rhsNilReason = (rightObject.isSetNilReason()?rightObject.getNilReason():null);
                    List<String> mergedNilReason = ((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "nilReason", lhsNilReason), LocatorUtils.property(rightLocator, "nilReason", rhsNilReason), lhsNilReason, rhsNilReason, leftObject.isSetNilReason(), rightObject.isSetNilReason()));
                    target.unsetNilReason();
                    if (mergedNilReason!= null) {
                        List<String> uniqueNilReasonl = target.getNilReason();
                        uniqueNilReasonl.addAll(mergedNilReason);
                    }
                } else {
                    if (nilReasonShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetNilReason();
                    }
                }
            }
        }
    }

    public void setNilReason(List<String> value) {
        this.nilReason = null;
        if (value!= null) {
            List<String> draftl = this.getNilReason();
            draftl.addAll(value);
        }
    }

}
