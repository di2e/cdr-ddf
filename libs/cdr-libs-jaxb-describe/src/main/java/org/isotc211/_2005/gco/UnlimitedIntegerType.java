//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package org.isotc211._2005.gco;

import java.io.Serializable;
import java.math.BigInteger;
import javax.validation.constraints.DecimalMin;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
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
 * <p>Java class for UnlimitedInteger_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnlimitedInteger_Type"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;nonNegativeInteger"&gt;
 *       &lt;attribute name="isInfinite" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnlimitedInteger_Type", propOrder = {
    "value"
})
public class UnlimitedIntegerType implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlValue
    @XmlSchemaType(name = "nonNegativeInteger")
    @DecimalMin("0")
    protected BigInteger value;
    @XmlAttribute(name = "isInfinite")
    protected Boolean isInfinite;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setValue(BigInteger value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return (this.value!= null);
    }

    /**
     * Gets the value of the isInfinite property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean getIsInfinite() {
        return isInfinite;
    }

    /**
     * Sets the value of the isInfinite property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsInfinite(boolean value) {
        this.isInfinite = value;
    }

    public boolean isSetIsInfinite() {
        return (this.isInfinite!= null);
    }

    public void unsetIsInfinite() {
        this.isInfinite = null;
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
            BigInteger theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue, this.isSetValue());
        }
        {
            boolean theIsInfinite;
            theIsInfinite = (this.isSetIsInfinite()?this.getIsInfinite():false);
            strategy.appendField(locator, this, "isInfinite", buffer, theIsInfinite, this.isSetIsInfinite());
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
        final UnlimitedIntegerType that = ((UnlimitedIntegerType) object);
        {
            BigInteger lhsValue;
            lhsValue = this.getValue();
            BigInteger rhsValue;
            rhsValue = that.getValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue, this.isSetValue(), that.isSetValue())) {
                return false;
            }
        }
        {
            boolean lhsIsInfinite;
            lhsIsInfinite = (this.isSetIsInfinite()?this.getIsInfinite():false);
            boolean rhsIsInfinite;
            rhsIsInfinite = (that.isSetIsInfinite()?that.getIsInfinite():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "isInfinite", lhsIsInfinite), LocatorUtils.property(thatLocator, "isInfinite", rhsIsInfinite), lhsIsInfinite, rhsIsInfinite, this.isSetIsInfinite(), that.isSetIsInfinite())) {
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
            BigInteger theValue;
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue, this.isSetValue());
        }
        {
            boolean theIsInfinite;
            theIsInfinite = (this.isSetIsInfinite()?this.getIsInfinite():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "isInfinite", theIsInfinite), currentHashCode, theIsInfinite, this.isSetIsInfinite());
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
        if (draftCopy instanceof UnlimitedIntegerType) {
            final UnlimitedIntegerType copy = ((UnlimitedIntegerType) draftCopy);
            {
                Boolean valueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValue());
                if (valueShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigInteger sourceValue;
                    sourceValue = this.getValue();
                    BigInteger copyValue = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue, this.isSetValue()));
                    copy.setValue(copyValue);
                } else {
                    if (valueShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.value = null;
                    }
                }
            }
            {
                Boolean isInfiniteShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetIsInfinite());
                if (isInfiniteShouldBeCopiedAndSet == Boolean.TRUE) {
                    boolean sourceIsInfinite;
                    sourceIsInfinite = (this.isSetIsInfinite()?this.getIsInfinite():false);
                    boolean copyIsInfinite = strategy.copy(LocatorUtils.property(locator, "isInfinite", sourceIsInfinite), sourceIsInfinite, this.isSetIsInfinite());
                    copy.setIsInfinite(copyIsInfinite);
                } else {
                    if (isInfiniteShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetIsInfinite();
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new UnlimitedIntegerType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        if (right instanceof UnlimitedIntegerType) {
            final UnlimitedIntegerType target = this;
            final UnlimitedIntegerType leftObject = ((UnlimitedIntegerType) left);
            final UnlimitedIntegerType rightObject = ((UnlimitedIntegerType) right);
            {
                Boolean valueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValue(), rightObject.isSetValue());
                if (valueShouldBeMergedAndSet == Boolean.TRUE) {
                    BigInteger lhsValue;
                    lhsValue = leftObject.getValue();
                    BigInteger rhsValue;
                    rhsValue = rightObject.getValue();
                    BigInteger mergedValue = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue, leftObject.isSetValue(), rightObject.isSetValue()));
                    target.setValue(mergedValue);
                } else {
                    if (valueShouldBeMergedAndSet == Boolean.FALSE) {
                        target.value = null;
                    }
                }
            }
            {
                Boolean isInfiniteShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetIsInfinite(), rightObject.isSetIsInfinite());
                if (isInfiniteShouldBeMergedAndSet == Boolean.TRUE) {
                    boolean lhsIsInfinite;
                    lhsIsInfinite = (leftObject.isSetIsInfinite()?leftObject.getIsInfinite():false);
                    boolean rhsIsInfinite;
                    rhsIsInfinite = (rightObject.isSetIsInfinite()?rightObject.getIsInfinite():false);
                    boolean mergedIsInfinite = ((boolean) strategy.merge(LocatorUtils.property(leftLocator, "isInfinite", lhsIsInfinite), LocatorUtils.property(rightLocator, "isInfinite", rhsIsInfinite), lhsIsInfinite, rhsIsInfinite, leftObject.isSetIsInfinite(), rightObject.isSetIsInfinite()));
                    target.setIsInfinite(mergedIsInfinite);
                } else {
                    if (isInfiniteShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetIsInfinite();
                    }
                }
            }
        }
    }

}
