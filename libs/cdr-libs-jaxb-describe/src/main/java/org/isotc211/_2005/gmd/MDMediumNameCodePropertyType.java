//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package org.isotc211._2005.gmd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.isotc211._2005.gco.CodeListValueType;
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
 * <p>Java class for MD_MediumNameCode_PropertyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MD_MediumNameCode_PropertyType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence minOccurs="0"&gt;
 *         &lt;element ref="{http://www.isotc211.org/2005/gmd}MD_MediumNameCode"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute ref="{http://www.isotc211.org/2005/gco}nilReason"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_MediumNameCode_PropertyType", propOrder = {
    "mdMediumNameCode"
})
public class MDMediumNameCodePropertyType implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "MD_MediumNameCode")
    @Valid
    protected CodeListValueType mdMediumNameCode;
    @XmlAttribute(name = "nilReason", namespace = "http://www.isotc211.org/2005/gco")
    protected List<String> nilReason;

    /**
     * Gets the value of the mdMediumNameCode property.
     * 
     * @return
     *     possible object is
     *     {@link CodeListValueType }
     *     
     */
    public CodeListValueType getMDMediumNameCode() {
        return mdMediumNameCode;
    }

    /**
     * Sets the value of the mdMediumNameCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeListValueType }
     *     
     */
    public void setMDMediumNameCode(CodeListValueType value) {
        this.mdMediumNameCode = value;
    }

    public boolean isSetMDMediumNameCode() {
        return (this.mdMediumNameCode!= null);
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
            CodeListValueType theMDMediumNameCode;
            theMDMediumNameCode = this.getMDMediumNameCode();
            strategy.appendField(locator, this, "mdMediumNameCode", buffer, theMDMediumNameCode, this.isSetMDMediumNameCode());
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
        final MDMediumNameCodePropertyType that = ((MDMediumNameCodePropertyType) object);
        {
            CodeListValueType lhsMDMediumNameCode;
            lhsMDMediumNameCode = this.getMDMediumNameCode();
            CodeListValueType rhsMDMediumNameCode;
            rhsMDMediumNameCode = that.getMDMediumNameCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mdMediumNameCode", lhsMDMediumNameCode), LocatorUtils.property(thatLocator, "mdMediumNameCode", rhsMDMediumNameCode), lhsMDMediumNameCode, rhsMDMediumNameCode, this.isSetMDMediumNameCode(), that.isSetMDMediumNameCode())) {
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
            CodeListValueType theMDMediumNameCode;
            theMDMediumNameCode = this.getMDMediumNameCode();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "mdMediumNameCode", theMDMediumNameCode), currentHashCode, theMDMediumNameCode, this.isSetMDMediumNameCode());
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
        if (draftCopy instanceof MDMediumNameCodePropertyType) {
            final MDMediumNameCodePropertyType copy = ((MDMediumNameCodePropertyType) draftCopy);
            {
                Boolean mdMediumNameCodeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetMDMediumNameCode());
                if (mdMediumNameCodeShouldBeCopiedAndSet == Boolean.TRUE) {
                    CodeListValueType sourceMDMediumNameCode;
                    sourceMDMediumNameCode = this.getMDMediumNameCode();
                    CodeListValueType copyMDMediumNameCode = ((CodeListValueType) strategy.copy(LocatorUtils.property(locator, "mdMediumNameCode", sourceMDMediumNameCode), sourceMDMediumNameCode, this.isSetMDMediumNameCode()));
                    copy.setMDMediumNameCode(copyMDMediumNameCode);
                } else {
                    if (mdMediumNameCodeShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.mdMediumNameCode = null;
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
        return new MDMediumNameCodePropertyType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        if (right instanceof MDMediumNameCodePropertyType) {
            final MDMediumNameCodePropertyType target = this;
            final MDMediumNameCodePropertyType leftObject = ((MDMediumNameCodePropertyType) left);
            final MDMediumNameCodePropertyType rightObject = ((MDMediumNameCodePropertyType) right);
            {
                Boolean mdMediumNameCodeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetMDMediumNameCode(), rightObject.isSetMDMediumNameCode());
                if (mdMediumNameCodeShouldBeMergedAndSet == Boolean.TRUE) {
                    CodeListValueType lhsMDMediumNameCode;
                    lhsMDMediumNameCode = leftObject.getMDMediumNameCode();
                    CodeListValueType rhsMDMediumNameCode;
                    rhsMDMediumNameCode = rightObject.getMDMediumNameCode();
                    CodeListValueType mergedMDMediumNameCode = ((CodeListValueType) strategy.merge(LocatorUtils.property(leftLocator, "mdMediumNameCode", lhsMDMediumNameCode), LocatorUtils.property(rightLocator, "mdMediumNameCode", rhsMDMediumNameCode), lhsMDMediumNameCode, rhsMDMediumNameCode, leftObject.isSetMDMediumNameCode(), rightObject.isSetMDMediumNameCode()));
                    target.setMDMediumNameCode(mergedMDMediumNameCode);
                } else {
                    if (mdMediumNameCodeShouldBeMergedAndSet == Boolean.FALSE) {
                        target.mdMediumNameCode = null;
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
