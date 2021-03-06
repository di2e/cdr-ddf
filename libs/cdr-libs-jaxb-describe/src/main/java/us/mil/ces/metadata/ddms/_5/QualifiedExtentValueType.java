//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package us.mil.ces.metadata.ddms._5;

import java.io.Serializable;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 * <p>Java class for QualifiedExtentValueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QualifiedExtentValueType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="qualifier" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="value" type="{urn:us:mil:ces:metadata:ddms:5}simpleTokenType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QualifiedExtentValueType")
public class QualifiedExtentValueType implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlAttribute(name = "qualifier", namespace = "urn:us:mil:ces:metadata:ddms:5")
    @XmlSchemaType(name = "anyURI")
    protected String qualifier;
    @XmlAttribute(name = "value", namespace = "urn:us:mil:ces:metadata:ddms:5")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @Size(min = 1)
    protected String value;

    /**
     * Gets the value of the qualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualifier() {
        return qualifier;
    }

    /**
     * Sets the value of the qualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualifier(String value) {
        this.qualifier = value;
    }

    public boolean isSetQualifier() {
        return (this.qualifier!= null);
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return (this.value!= null);
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
            String theQualifier;
            theQualifier = this.getQualifier();
            strategy.appendField(locator, this, "qualifier", buffer, theQualifier, this.isSetQualifier());
        }
        {
            String theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue, this.isSetValue());
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
        final QualifiedExtentValueType that = ((QualifiedExtentValueType) object);
        {
            String lhsQualifier;
            lhsQualifier = this.getQualifier();
            String rhsQualifier;
            rhsQualifier = that.getQualifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "qualifier", lhsQualifier), LocatorUtils.property(thatLocator, "qualifier", rhsQualifier), lhsQualifier, rhsQualifier, this.isSetQualifier(), that.isSetQualifier())) {
                return false;
            }
        }
        {
            String lhsValue;
            lhsValue = this.getValue();
            String rhsValue;
            rhsValue = that.getValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue, this.isSetValue(), that.isSetValue())) {
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
            String theQualifier;
            theQualifier = this.getQualifier();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "qualifier", theQualifier), currentHashCode, theQualifier, this.isSetQualifier());
        }
        {
            String theValue;
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue, this.isSetValue());
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
        if (draftCopy instanceof QualifiedExtentValueType) {
            final QualifiedExtentValueType copy = ((QualifiedExtentValueType) draftCopy);
            {
                Boolean qualifierShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetQualifier());
                if (qualifierShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceQualifier;
                    sourceQualifier = this.getQualifier();
                    String copyQualifier = ((String) strategy.copy(LocatorUtils.property(locator, "qualifier", sourceQualifier), sourceQualifier, this.isSetQualifier()));
                    copy.setQualifier(copyQualifier);
                } else {
                    if (qualifierShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.qualifier = null;
                    }
                }
            }
            {
                Boolean valueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValue());
                if (valueShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceValue;
                    sourceValue = this.getValue();
                    String copyValue = ((String) strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue, this.isSetValue()));
                    copy.setValue(copyValue);
                } else {
                    if (valueShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.value = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new QualifiedExtentValueType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        if (right instanceof QualifiedExtentValueType) {
            final QualifiedExtentValueType target = this;
            final QualifiedExtentValueType leftObject = ((QualifiedExtentValueType) left);
            final QualifiedExtentValueType rightObject = ((QualifiedExtentValueType) right);
            {
                Boolean qualifierShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetQualifier(), rightObject.isSetQualifier());
                if (qualifierShouldBeMergedAndSet == Boolean.TRUE) {
                    String lhsQualifier;
                    lhsQualifier = leftObject.getQualifier();
                    String rhsQualifier;
                    rhsQualifier = rightObject.getQualifier();
                    String mergedQualifier = ((String) strategy.merge(LocatorUtils.property(leftLocator, "qualifier", lhsQualifier), LocatorUtils.property(rightLocator, "qualifier", rhsQualifier), lhsQualifier, rhsQualifier, leftObject.isSetQualifier(), rightObject.isSetQualifier()));
                    target.setQualifier(mergedQualifier);
                } else {
                    if (qualifierShouldBeMergedAndSet == Boolean.FALSE) {
                        target.qualifier = null;
                    }
                }
            }
            {
                Boolean valueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValue(), rightObject.isSetValue());
                if (valueShouldBeMergedAndSet == Boolean.TRUE) {
                    String lhsValue;
                    lhsValue = leftObject.getValue();
                    String rhsValue;
                    rhsValue = rightObject.getValue();
                    String mergedValue = ((String) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue, leftObject.isSetValue(), rightObject.isSetValue()));
                    target.setValue(mergedValue);
                } else {
                    if (valueShouldBeMergedAndSet == Boolean.FALSE) {
                        target.value = null;
                    }
                }
            }
        }
    }

}
