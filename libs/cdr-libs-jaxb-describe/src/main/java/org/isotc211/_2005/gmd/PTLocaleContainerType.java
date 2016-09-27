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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.isotc211._2005.gco.CharacterStringPropertyType;
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
 * <p>Java class for PT_LocaleContainer_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PT_LocaleContainer_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="description" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/&gt;
 *         &lt;element name="locale" type="{http://www.isotc211.org/2005/gmd}PT_Locale_PropertyType"/&gt;
 *         &lt;element name="date" type="{http://www.isotc211.org/2005/gmd}CI_Date_PropertyType" maxOccurs="unbounded"/&gt;
 *         &lt;element name="responsibleParty" type="{http://www.isotc211.org/2005/gmd}CI_ResponsibleParty_PropertyType" maxOccurs="unbounded"/&gt;
 *         &lt;element name="localisedString" type="{http://www.isotc211.org/2005/gmd}LocalisedCharacterString_PropertyType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PT_LocaleContainer_Type", propOrder = {
    "description",
    "locale",
    "date",
    "responsibleParty",
    "localisedString"
})
public class PTLocaleContainerType implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    @NotNull(message = "PTLocaleContainerType.description {javax.validation.constraints.NotNull.message}")
    @Valid
    protected CharacterStringPropertyType description;
    @XmlElement(required = true)
    @NotNull(message = "PTLocaleContainerType.locale {javax.validation.constraints.NotNull.message}")
    @Valid
    protected PTLocalePropertyType locale;
    @XmlElement(required = true)
    @NotNull(message = "PTLocaleContainerType.date {javax.validation.constraints.NotNull.message}")
    @Size(min = 1)
    @Valid
    protected List<CIDatePropertyType> date;
    @XmlElement(required = true)
    @NotNull(message = "PTLocaleContainerType.responsibleParty {javax.validation.constraints.NotNull.message}")
    @Size(min = 1)
    @Valid
    protected List<CIResponsiblePartyPropertyType> responsibleParty;
    @XmlElement(required = true)
    @NotNull(message = "PTLocaleContainerType.localisedString {javax.validation.constraints.NotNull.message}")
    @Size(min = 1)
    @Valid
    protected List<LocalisedCharacterStringPropertyType> localisedString;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setDescription(CharacterStringPropertyType value) {
        this.description = value;
    }

    public boolean isSetDescription() {
        return (this.description!= null);
    }

    /**
     * Gets the value of the locale property.
     * 
     * @return
     *     possible object is
     *     {@link PTLocalePropertyType }
     *     
     */
    public PTLocalePropertyType getLocale() {
        return locale;
    }

    /**
     * Sets the value of the locale property.
     * 
     * @param value
     *     allowed object is
     *     {@link PTLocalePropertyType }
     *     
     */
    public void setLocale(PTLocalePropertyType value) {
        this.locale = value;
    }

    public boolean isSetLocale() {
        return (this.locale!= null);
    }

    /**
     * Gets the value of the date property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the date property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIDatePropertyType }
     * 
     * 
     */
    public List<CIDatePropertyType> getDate() {
        if (date == null) {
            date = new ArrayList<CIDatePropertyType>();
        }
        return this.date;
    }

    public boolean isSetDate() {
        return ((this.date!= null)&&(!this.date.isEmpty()));
    }

    public void unsetDate() {
        this.date = null;
    }

    /**
     * Gets the value of the responsibleParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the responsibleParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponsibleParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIResponsiblePartyPropertyType }
     * 
     * 
     */
    public List<CIResponsiblePartyPropertyType> getResponsibleParty() {
        if (responsibleParty == null) {
            responsibleParty = new ArrayList<CIResponsiblePartyPropertyType>();
        }
        return this.responsibleParty;
    }

    public boolean isSetResponsibleParty() {
        return ((this.responsibleParty!= null)&&(!this.responsibleParty.isEmpty()));
    }

    public void unsetResponsibleParty() {
        this.responsibleParty = null;
    }

    /**
     * Gets the value of the localisedString property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the localisedString property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocalisedString().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalisedCharacterStringPropertyType }
     * 
     * 
     */
    public List<LocalisedCharacterStringPropertyType> getLocalisedString() {
        if (localisedString == null) {
            localisedString = new ArrayList<LocalisedCharacterStringPropertyType>();
        }
        return this.localisedString;
    }

    public boolean isSetLocalisedString() {
        return ((this.localisedString!= null)&&(!this.localisedString.isEmpty()));
    }

    public void unsetLocalisedString() {
        this.localisedString = null;
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
            CharacterStringPropertyType theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription, this.isSetDescription());
        }
        {
            PTLocalePropertyType theLocale;
            theLocale = this.getLocale();
            strategy.appendField(locator, this, "locale", buffer, theLocale, this.isSetLocale());
        }
        {
            List<CIDatePropertyType> theDate;
            theDate = (this.isSetDate()?this.getDate():null);
            strategy.appendField(locator, this, "date", buffer, theDate, this.isSetDate());
        }
        {
            List<CIResponsiblePartyPropertyType> theResponsibleParty;
            theResponsibleParty = (this.isSetResponsibleParty()?this.getResponsibleParty():null);
            strategy.appendField(locator, this, "responsibleParty", buffer, theResponsibleParty, this.isSetResponsibleParty());
        }
        {
            List<LocalisedCharacterStringPropertyType> theLocalisedString;
            theLocalisedString = (this.isSetLocalisedString()?this.getLocalisedString():null);
            strategy.appendField(locator, this, "localisedString", buffer, theLocalisedString, this.isSetLocalisedString());
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
        final PTLocaleContainerType that = ((PTLocaleContainerType) object);
        {
            CharacterStringPropertyType lhsDescription;
            lhsDescription = this.getDescription();
            CharacterStringPropertyType rhsDescription;
            rhsDescription = that.getDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription, this.isSetDescription(), that.isSetDescription())) {
                return false;
            }
        }
        {
            PTLocalePropertyType lhsLocale;
            lhsLocale = this.getLocale();
            PTLocalePropertyType rhsLocale;
            rhsLocale = that.getLocale();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "locale", lhsLocale), LocatorUtils.property(thatLocator, "locale", rhsLocale), lhsLocale, rhsLocale, this.isSetLocale(), that.isSetLocale())) {
                return false;
            }
        }
        {
            List<CIDatePropertyType> lhsDate;
            lhsDate = (this.isSetDate()?this.getDate():null);
            List<CIDatePropertyType> rhsDate;
            rhsDate = (that.isSetDate()?that.getDate():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "date", lhsDate), LocatorUtils.property(thatLocator, "date", rhsDate), lhsDate, rhsDate, this.isSetDate(), that.isSetDate())) {
                return false;
            }
        }
        {
            List<CIResponsiblePartyPropertyType> lhsResponsibleParty;
            lhsResponsibleParty = (this.isSetResponsibleParty()?this.getResponsibleParty():null);
            List<CIResponsiblePartyPropertyType> rhsResponsibleParty;
            rhsResponsibleParty = (that.isSetResponsibleParty()?that.getResponsibleParty():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "responsibleParty", lhsResponsibleParty), LocatorUtils.property(thatLocator, "responsibleParty", rhsResponsibleParty), lhsResponsibleParty, rhsResponsibleParty, this.isSetResponsibleParty(), that.isSetResponsibleParty())) {
                return false;
            }
        }
        {
            List<LocalisedCharacterStringPropertyType> lhsLocalisedString;
            lhsLocalisedString = (this.isSetLocalisedString()?this.getLocalisedString():null);
            List<LocalisedCharacterStringPropertyType> rhsLocalisedString;
            rhsLocalisedString = (that.isSetLocalisedString()?that.getLocalisedString():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "localisedString", lhsLocalisedString), LocatorUtils.property(thatLocator, "localisedString", rhsLocalisedString), lhsLocalisedString, rhsLocalisedString, this.isSetLocalisedString(), that.isSetLocalisedString())) {
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
            CharacterStringPropertyType theDescription;
            theDescription = this.getDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription, this.isSetDescription());
        }
        {
            PTLocalePropertyType theLocale;
            theLocale = this.getLocale();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "locale", theLocale), currentHashCode, theLocale, this.isSetLocale());
        }
        {
            List<CIDatePropertyType> theDate;
            theDate = (this.isSetDate()?this.getDate():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "date", theDate), currentHashCode, theDate, this.isSetDate());
        }
        {
            List<CIResponsiblePartyPropertyType> theResponsibleParty;
            theResponsibleParty = (this.isSetResponsibleParty()?this.getResponsibleParty():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "responsibleParty", theResponsibleParty), currentHashCode, theResponsibleParty, this.isSetResponsibleParty());
        }
        {
            List<LocalisedCharacterStringPropertyType> theLocalisedString;
            theLocalisedString = (this.isSetLocalisedString()?this.getLocalisedString():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "localisedString", theLocalisedString), currentHashCode, theLocalisedString, this.isSetLocalisedString());
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
        if (draftCopy instanceof PTLocaleContainerType) {
            final PTLocaleContainerType copy = ((PTLocaleContainerType) draftCopy);
            {
                Boolean descriptionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDescription());
                if (descriptionShouldBeCopiedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType sourceDescription;
                    sourceDescription = this.getDescription();
                    CharacterStringPropertyType copyDescription = ((CharacterStringPropertyType) strategy.copy(LocatorUtils.property(locator, "description", sourceDescription), sourceDescription, this.isSetDescription()));
                    copy.setDescription(copyDescription);
                } else {
                    if (descriptionShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.description = null;
                    }
                }
            }
            {
                Boolean localeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLocale());
                if (localeShouldBeCopiedAndSet == Boolean.TRUE) {
                    PTLocalePropertyType sourceLocale;
                    sourceLocale = this.getLocale();
                    PTLocalePropertyType copyLocale = ((PTLocalePropertyType) strategy.copy(LocatorUtils.property(locator, "locale", sourceLocale), sourceLocale, this.isSetLocale()));
                    copy.setLocale(copyLocale);
                } else {
                    if (localeShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.locale = null;
                    }
                }
            }
            {
                Boolean dateShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDate());
                if (dateShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<CIDatePropertyType> sourceDate;
                    sourceDate = (this.isSetDate()?this.getDate():null);
                    @SuppressWarnings("unchecked")
                    List<CIDatePropertyType> copyDate = ((List<CIDatePropertyType> ) strategy.copy(LocatorUtils.property(locator, "date", sourceDate), sourceDate, this.isSetDate()));
                    copy.unsetDate();
                    if (copyDate!= null) {
                        List<CIDatePropertyType> uniqueDatel = copy.getDate();
                        uniqueDatel.addAll(copyDate);
                    }
                } else {
                    if (dateShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetDate();
                    }
                }
            }
            {
                Boolean responsiblePartyShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetResponsibleParty());
                if (responsiblePartyShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<CIResponsiblePartyPropertyType> sourceResponsibleParty;
                    sourceResponsibleParty = (this.isSetResponsibleParty()?this.getResponsibleParty():null);
                    @SuppressWarnings("unchecked")
                    List<CIResponsiblePartyPropertyType> copyResponsibleParty = ((List<CIResponsiblePartyPropertyType> ) strategy.copy(LocatorUtils.property(locator, "responsibleParty", sourceResponsibleParty), sourceResponsibleParty, this.isSetResponsibleParty()));
                    copy.unsetResponsibleParty();
                    if (copyResponsibleParty!= null) {
                        List<CIResponsiblePartyPropertyType> uniqueResponsiblePartyl = copy.getResponsibleParty();
                        uniqueResponsiblePartyl.addAll(copyResponsibleParty);
                    }
                } else {
                    if (responsiblePartyShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetResponsibleParty();
                    }
                }
            }
            {
                Boolean localisedStringShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLocalisedString());
                if (localisedStringShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<LocalisedCharacterStringPropertyType> sourceLocalisedString;
                    sourceLocalisedString = (this.isSetLocalisedString()?this.getLocalisedString():null);
                    @SuppressWarnings("unchecked")
                    List<LocalisedCharacterStringPropertyType> copyLocalisedString = ((List<LocalisedCharacterStringPropertyType> ) strategy.copy(LocatorUtils.property(locator, "localisedString", sourceLocalisedString), sourceLocalisedString, this.isSetLocalisedString()));
                    copy.unsetLocalisedString();
                    if (copyLocalisedString!= null) {
                        List<LocalisedCharacterStringPropertyType> uniqueLocalisedStringl = copy.getLocalisedString();
                        uniqueLocalisedStringl.addAll(copyLocalisedString);
                    }
                } else {
                    if (localisedStringShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetLocalisedString();
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new PTLocaleContainerType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        if (right instanceof PTLocaleContainerType) {
            final PTLocaleContainerType target = this;
            final PTLocaleContainerType leftObject = ((PTLocaleContainerType) left);
            final PTLocaleContainerType rightObject = ((PTLocaleContainerType) right);
            {
                Boolean descriptionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDescription(), rightObject.isSetDescription());
                if (descriptionShouldBeMergedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType lhsDescription;
                    lhsDescription = leftObject.getDescription();
                    CharacterStringPropertyType rhsDescription;
                    rhsDescription = rightObject.getDescription();
                    CharacterStringPropertyType mergedDescription = ((CharacterStringPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "description", lhsDescription), LocatorUtils.property(rightLocator, "description", rhsDescription), lhsDescription, rhsDescription, leftObject.isSetDescription(), rightObject.isSetDescription()));
                    target.setDescription(mergedDescription);
                } else {
                    if (descriptionShouldBeMergedAndSet == Boolean.FALSE) {
                        target.description = null;
                    }
                }
            }
            {
                Boolean localeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLocale(), rightObject.isSetLocale());
                if (localeShouldBeMergedAndSet == Boolean.TRUE) {
                    PTLocalePropertyType lhsLocale;
                    lhsLocale = leftObject.getLocale();
                    PTLocalePropertyType rhsLocale;
                    rhsLocale = rightObject.getLocale();
                    PTLocalePropertyType mergedLocale = ((PTLocalePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "locale", lhsLocale), LocatorUtils.property(rightLocator, "locale", rhsLocale), lhsLocale, rhsLocale, leftObject.isSetLocale(), rightObject.isSetLocale()));
                    target.setLocale(mergedLocale);
                } else {
                    if (localeShouldBeMergedAndSet == Boolean.FALSE) {
                        target.locale = null;
                    }
                }
            }
            {
                Boolean dateShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDate(), rightObject.isSetDate());
                if (dateShouldBeMergedAndSet == Boolean.TRUE) {
                    List<CIDatePropertyType> lhsDate;
                    lhsDate = (leftObject.isSetDate()?leftObject.getDate():null);
                    List<CIDatePropertyType> rhsDate;
                    rhsDate = (rightObject.isSetDate()?rightObject.getDate():null);
                    List<CIDatePropertyType> mergedDate = ((List<CIDatePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "date", lhsDate), LocatorUtils.property(rightLocator, "date", rhsDate), lhsDate, rhsDate, leftObject.isSetDate(), rightObject.isSetDate()));
                    target.unsetDate();
                    if (mergedDate!= null) {
                        List<CIDatePropertyType> uniqueDatel = target.getDate();
                        uniqueDatel.addAll(mergedDate);
                    }
                } else {
                    if (dateShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetDate();
                    }
                }
            }
            {
                Boolean responsiblePartyShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetResponsibleParty(), rightObject.isSetResponsibleParty());
                if (responsiblePartyShouldBeMergedAndSet == Boolean.TRUE) {
                    List<CIResponsiblePartyPropertyType> lhsResponsibleParty;
                    lhsResponsibleParty = (leftObject.isSetResponsibleParty()?leftObject.getResponsibleParty():null);
                    List<CIResponsiblePartyPropertyType> rhsResponsibleParty;
                    rhsResponsibleParty = (rightObject.isSetResponsibleParty()?rightObject.getResponsibleParty():null);
                    List<CIResponsiblePartyPropertyType> mergedResponsibleParty = ((List<CIResponsiblePartyPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "responsibleParty", lhsResponsibleParty), LocatorUtils.property(rightLocator, "responsibleParty", rhsResponsibleParty), lhsResponsibleParty, rhsResponsibleParty, leftObject.isSetResponsibleParty(), rightObject.isSetResponsibleParty()));
                    target.unsetResponsibleParty();
                    if (mergedResponsibleParty!= null) {
                        List<CIResponsiblePartyPropertyType> uniqueResponsiblePartyl = target.getResponsibleParty();
                        uniqueResponsiblePartyl.addAll(mergedResponsibleParty);
                    }
                } else {
                    if (responsiblePartyShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetResponsibleParty();
                    }
                }
            }
            {
                Boolean localisedStringShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLocalisedString(), rightObject.isSetLocalisedString());
                if (localisedStringShouldBeMergedAndSet == Boolean.TRUE) {
                    List<LocalisedCharacterStringPropertyType> lhsLocalisedString;
                    lhsLocalisedString = (leftObject.isSetLocalisedString()?leftObject.getLocalisedString():null);
                    List<LocalisedCharacterStringPropertyType> rhsLocalisedString;
                    rhsLocalisedString = (rightObject.isSetLocalisedString()?rightObject.getLocalisedString():null);
                    List<LocalisedCharacterStringPropertyType> mergedLocalisedString = ((List<LocalisedCharacterStringPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "localisedString", lhsLocalisedString), LocatorUtils.property(rightLocator, "localisedString", rhsLocalisedString), lhsLocalisedString, rhsLocalisedString, leftObject.isSetLocalisedString(), rightObject.isSetLocalisedString()));
                    target.unsetLocalisedString();
                    if (mergedLocalisedString!= null) {
                        List<LocalisedCharacterStringPropertyType> uniqueLocalisedStringl = target.getLocalisedString();
                        uniqueLocalisedStringl.addAll(mergedLocalisedString);
                    }
                } else {
                    if (localisedStringShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetLocalisedString();
                    }
                }
            }
        }
    }

    public void setDate(List<CIDatePropertyType> value) {
        this.date = null;
        if (value!= null) {
            List<CIDatePropertyType> draftl = this.getDate();
            draftl.addAll(value);
        }
    }

    public void setResponsibleParty(List<CIResponsiblePartyPropertyType> value) {
        this.responsibleParty = null;
        if (value!= null) {
            List<CIResponsiblePartyPropertyType> draftl = this.getResponsibleParty();
            draftl.addAll(value);
        }
    }

    public void setLocalisedString(List<LocalisedCharacterStringPropertyType> value) {
        this.localisedString = null;
        if (value!= null) {
            List<LocalisedCharacterStringPropertyType> draftl = this.getLocalisedString();
            draftl.addAll(value);
        }
    }

}
