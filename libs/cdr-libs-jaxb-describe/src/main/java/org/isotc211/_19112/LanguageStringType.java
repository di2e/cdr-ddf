//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package org.isotc211._19112;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 * <p>Java class for LanguageStringType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LanguageStringType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/&gt;
 *       &lt;attribute name="transliterationDomain" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LanguageStringType", propOrder = {
    "value"
})
public class LanguageStringType implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlValue
    protected String value;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    protected String lang;
    @XmlAttribute(name = "transliterationDomain")
    protected String transliterationDomain;

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

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

    public boolean isSetLang() {
        return (this.lang!= null);
    }

    /**
     * Gets the value of the transliterationDomain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransliterationDomain() {
        return transliterationDomain;
    }

    /**
     * Sets the value of the transliterationDomain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransliterationDomain(String value) {
        this.transliterationDomain = value;
    }

    public boolean isSetTransliterationDomain() {
        return (this.transliterationDomain!= null);
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
            String theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue, this.isSetValue());
        }
        {
            String theLang;
            theLang = this.getLang();
            strategy.appendField(locator, this, "lang", buffer, theLang, this.isSetLang());
        }
        {
            String theTransliterationDomain;
            theTransliterationDomain = this.getTransliterationDomain();
            strategy.appendField(locator, this, "transliterationDomain", buffer, theTransliterationDomain, this.isSetTransliterationDomain());
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
        final LanguageStringType that = ((LanguageStringType) object);
        {
            String lhsValue;
            lhsValue = this.getValue();
            String rhsValue;
            rhsValue = that.getValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue, this.isSetValue(), that.isSetValue())) {
                return false;
            }
        }
        {
            String lhsLang;
            lhsLang = this.getLang();
            String rhsLang;
            rhsLang = that.getLang();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lang", lhsLang), LocatorUtils.property(thatLocator, "lang", rhsLang), lhsLang, rhsLang, this.isSetLang(), that.isSetLang())) {
                return false;
            }
        }
        {
            String lhsTransliterationDomain;
            lhsTransliterationDomain = this.getTransliterationDomain();
            String rhsTransliterationDomain;
            rhsTransliterationDomain = that.getTransliterationDomain();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "transliterationDomain", lhsTransliterationDomain), LocatorUtils.property(thatLocator, "transliterationDomain", rhsTransliterationDomain), lhsTransliterationDomain, rhsTransliterationDomain, this.isSetTransliterationDomain(), that.isSetTransliterationDomain())) {
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
            String theValue;
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue, this.isSetValue());
        }
        {
            String theLang;
            theLang = this.getLang();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lang", theLang), currentHashCode, theLang, this.isSetLang());
        }
        {
            String theTransliterationDomain;
            theTransliterationDomain = this.getTransliterationDomain();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "transliterationDomain", theTransliterationDomain), currentHashCode, theTransliterationDomain, this.isSetTransliterationDomain());
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
        if (draftCopy instanceof LanguageStringType) {
            final LanguageStringType copy = ((LanguageStringType) draftCopy);
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
            {
                Boolean langShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLang());
                if (langShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceLang;
                    sourceLang = this.getLang();
                    String copyLang = ((String) strategy.copy(LocatorUtils.property(locator, "lang", sourceLang), sourceLang, this.isSetLang()));
                    copy.setLang(copyLang);
                } else {
                    if (langShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.lang = null;
                    }
                }
            }
            {
                Boolean transliterationDomainShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetTransliterationDomain());
                if (transliterationDomainShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceTransliterationDomain;
                    sourceTransliterationDomain = this.getTransliterationDomain();
                    String copyTransliterationDomain = ((String) strategy.copy(LocatorUtils.property(locator, "transliterationDomain", sourceTransliterationDomain), sourceTransliterationDomain, this.isSetTransliterationDomain()));
                    copy.setTransliterationDomain(copyTransliterationDomain);
                } else {
                    if (transliterationDomainShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.transliterationDomain = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new LanguageStringType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        if (right instanceof LanguageStringType) {
            final LanguageStringType target = this;
            final LanguageStringType leftObject = ((LanguageStringType) left);
            final LanguageStringType rightObject = ((LanguageStringType) right);
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
            {
                Boolean langShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLang(), rightObject.isSetLang());
                if (langShouldBeMergedAndSet == Boolean.TRUE) {
                    String lhsLang;
                    lhsLang = leftObject.getLang();
                    String rhsLang;
                    rhsLang = rightObject.getLang();
                    String mergedLang = ((String) strategy.merge(LocatorUtils.property(leftLocator, "lang", lhsLang), LocatorUtils.property(rightLocator, "lang", rhsLang), lhsLang, rhsLang, leftObject.isSetLang(), rightObject.isSetLang()));
                    target.setLang(mergedLang);
                } else {
                    if (langShouldBeMergedAndSet == Boolean.FALSE) {
                        target.lang = null;
                    }
                }
            }
            {
                Boolean transliterationDomainShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetTransliterationDomain(), rightObject.isSetTransliterationDomain());
                if (transliterationDomainShouldBeMergedAndSet == Boolean.TRUE) {
                    String lhsTransliterationDomain;
                    lhsTransliterationDomain = leftObject.getTransliterationDomain();
                    String rhsTransliterationDomain;
                    rhsTransliterationDomain = rightObject.getTransliterationDomain();
                    String mergedTransliterationDomain = ((String) strategy.merge(LocatorUtils.property(leftLocator, "transliterationDomain", lhsTransliterationDomain), LocatorUtils.property(rightLocator, "transliterationDomain", rhsTransliterationDomain), lhsTransliterationDomain, rhsTransliterationDomain, leftObject.isSetTransliterationDomain(), rightObject.isSetTransliterationDomain()));
                    target.setTransliterationDomain(mergedTransliterationDomain);
                } else {
                    if (transliterationDomainShouldBeMergedAndSet == Boolean.FALSE) {
                        target.transliterationDomain = null;
                    }
                }
            }
        }
    }

}
