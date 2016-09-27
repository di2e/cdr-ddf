//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package mil.ces.metadata.mdr.ns.gsip.tspi._2_0.core;

import java.io.Serializable;
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
 * Vertical Resolution Type: A flexible coded domain value denoting a vertical resolution category.
 * 
 * <p>Java class for VerticalResolutionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VerticalResolutionType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://metadata.ces.mil/mdr/ns/GSIP/tspi/2.0/core&gt;VerticalResolutionEnumerationType"&gt;
 *       &lt;attribute name="codeList" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="codeListValue" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerticalResolutionType", propOrder = {
    "value"
})
public class VerticalResolutionType implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlValue
    protected VerticalResolutionEnumerationType value;
    @XmlAttribute(name = "codeList")
    @XmlSchemaType(name = "anyURI")
    protected String codeList;
    @XmlAttribute(name = "codeListValue")
    @XmlSchemaType(name = "anyURI")
    protected String codeListValue;

    /**
     * Vertical Resolution Enumeration Type: A coded domain value denoting a vertical resolution category. [desc] The vertical resolution of a spatial position in its Coordinate Reference System (CRS), as a category.
     * 
     * @return
     *     possible object is
     *     {@link VerticalResolutionEnumerationType }
     *     
     */
    public VerticalResolutionEnumerationType getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerticalResolutionEnumerationType }
     *     
     */
    public void setValue(VerticalResolutionEnumerationType value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return (this.value!= null);
    }

    /**
     * Gets the value of the codeList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeList() {
        return codeList;
    }

    /**
     * Sets the value of the codeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeList(String value) {
        this.codeList = value;
    }

    public boolean isSetCodeList() {
        return (this.codeList!= null);
    }

    /**
     * Gets the value of the codeListValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeListValue() {
        return codeListValue;
    }

    /**
     * Sets the value of the codeListValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeListValue(String value) {
        this.codeListValue = value;
    }

    public boolean isSetCodeListValue() {
        return (this.codeListValue!= null);
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
            VerticalResolutionEnumerationType theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue, this.isSetValue());
        }
        {
            String theCodeList;
            theCodeList = this.getCodeList();
            strategy.appendField(locator, this, "codeList", buffer, theCodeList, this.isSetCodeList());
        }
        {
            String theCodeListValue;
            theCodeListValue = this.getCodeListValue();
            strategy.appendField(locator, this, "codeListValue", buffer, theCodeListValue, this.isSetCodeListValue());
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
        final VerticalResolutionType that = ((VerticalResolutionType) object);
        {
            VerticalResolutionEnumerationType lhsValue;
            lhsValue = this.getValue();
            VerticalResolutionEnumerationType rhsValue;
            rhsValue = that.getValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue, this.isSetValue(), that.isSetValue())) {
                return false;
            }
        }
        {
            String lhsCodeList;
            lhsCodeList = this.getCodeList();
            String rhsCodeList;
            rhsCodeList = that.getCodeList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "codeList", lhsCodeList), LocatorUtils.property(thatLocator, "codeList", rhsCodeList), lhsCodeList, rhsCodeList, this.isSetCodeList(), that.isSetCodeList())) {
                return false;
            }
        }
        {
            String lhsCodeListValue;
            lhsCodeListValue = this.getCodeListValue();
            String rhsCodeListValue;
            rhsCodeListValue = that.getCodeListValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "codeListValue", lhsCodeListValue), LocatorUtils.property(thatLocator, "codeListValue", rhsCodeListValue), lhsCodeListValue, rhsCodeListValue, this.isSetCodeListValue(), that.isSetCodeListValue())) {
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
            VerticalResolutionEnumerationType theValue;
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue, this.isSetValue());
        }
        {
            String theCodeList;
            theCodeList = this.getCodeList();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "codeList", theCodeList), currentHashCode, theCodeList, this.isSetCodeList());
        }
        {
            String theCodeListValue;
            theCodeListValue = this.getCodeListValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "codeListValue", theCodeListValue), currentHashCode, theCodeListValue, this.isSetCodeListValue());
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
        if (draftCopy instanceof VerticalResolutionType) {
            final VerticalResolutionType copy = ((VerticalResolutionType) draftCopy);
            {
                Boolean valueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValue());
                if (valueShouldBeCopiedAndSet == Boolean.TRUE) {
                    VerticalResolutionEnumerationType sourceValue;
                    sourceValue = this.getValue();
                    VerticalResolutionEnumerationType copyValue = ((VerticalResolutionEnumerationType) strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue, this.isSetValue()));
                    copy.setValue(copyValue);
                } else {
                    if (valueShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.value = null;
                    }
                }
            }
            {
                Boolean codeListShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCodeList());
                if (codeListShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceCodeList;
                    sourceCodeList = this.getCodeList();
                    String copyCodeList = ((String) strategy.copy(LocatorUtils.property(locator, "codeList", sourceCodeList), sourceCodeList, this.isSetCodeList()));
                    copy.setCodeList(copyCodeList);
                } else {
                    if (codeListShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.codeList = null;
                    }
                }
            }
            {
                Boolean codeListValueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCodeListValue());
                if (codeListValueShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceCodeListValue;
                    sourceCodeListValue = this.getCodeListValue();
                    String copyCodeListValue = ((String) strategy.copy(LocatorUtils.property(locator, "codeListValue", sourceCodeListValue), sourceCodeListValue, this.isSetCodeListValue()));
                    copy.setCodeListValue(copyCodeListValue);
                } else {
                    if (codeListValueShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.codeListValue = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new VerticalResolutionType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        if (right instanceof VerticalResolutionType) {
            final VerticalResolutionType target = this;
            final VerticalResolutionType leftObject = ((VerticalResolutionType) left);
            final VerticalResolutionType rightObject = ((VerticalResolutionType) right);
            {
                Boolean valueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValue(), rightObject.isSetValue());
                if (valueShouldBeMergedAndSet == Boolean.TRUE) {
                    VerticalResolutionEnumerationType lhsValue;
                    lhsValue = leftObject.getValue();
                    VerticalResolutionEnumerationType rhsValue;
                    rhsValue = rightObject.getValue();
                    VerticalResolutionEnumerationType mergedValue = ((VerticalResolutionEnumerationType) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue, leftObject.isSetValue(), rightObject.isSetValue()));
                    target.setValue(mergedValue);
                } else {
                    if (valueShouldBeMergedAndSet == Boolean.FALSE) {
                        target.value = null;
                    }
                }
            }
            {
                Boolean codeListShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCodeList(), rightObject.isSetCodeList());
                if (codeListShouldBeMergedAndSet == Boolean.TRUE) {
                    String lhsCodeList;
                    lhsCodeList = leftObject.getCodeList();
                    String rhsCodeList;
                    rhsCodeList = rightObject.getCodeList();
                    String mergedCodeList = ((String) strategy.merge(LocatorUtils.property(leftLocator, "codeList", lhsCodeList), LocatorUtils.property(rightLocator, "codeList", rhsCodeList), lhsCodeList, rhsCodeList, leftObject.isSetCodeList(), rightObject.isSetCodeList()));
                    target.setCodeList(mergedCodeList);
                } else {
                    if (codeListShouldBeMergedAndSet == Boolean.FALSE) {
                        target.codeList = null;
                    }
                }
            }
            {
                Boolean codeListValueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCodeListValue(), rightObject.isSetCodeListValue());
                if (codeListValueShouldBeMergedAndSet == Boolean.TRUE) {
                    String lhsCodeListValue;
                    lhsCodeListValue = leftObject.getCodeListValue();
                    String rhsCodeListValue;
                    rhsCodeListValue = rightObject.getCodeListValue();
                    String mergedCodeListValue = ((String) strategy.merge(LocatorUtils.property(leftLocator, "codeListValue", lhsCodeListValue), LocatorUtils.property(rightLocator, "codeListValue", rhsCodeListValue), lhsCodeListValue, rhsCodeListValue, leftObject.isSetCodeListValue(), rightObject.isSetCodeListValue()));
                    target.setCodeListValue(mergedCodeListValue);
                } else {
                    if (codeListValueShouldBeMergedAndSet == Boolean.FALSE) {
                        target.codeListValue = null;
                    }
                }
            }
        }
    }

}
