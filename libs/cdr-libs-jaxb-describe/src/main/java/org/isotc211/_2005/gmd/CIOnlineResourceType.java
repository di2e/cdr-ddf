//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package org.isotc211._2005.gmd;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.isotc211._2005.gco.AbstractObjectType;
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
 * Information about online sources from which the dataset, specification, or community profile name and extended metadata elements can be obtained.
 * 
 * <p>Java class for CI_OnlineResource_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CI_OnlineResource_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="linkage" type="{http://www.isotc211.org/2005/gmd}URL_PropertyType"/&gt;
 *         &lt;element name="protocol" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="applicationProfile" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="description" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="function" type="{http://www.isotc211.org/2005/gmd}CI_OnLineFunctionCode_PropertyType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CI_OnlineResource_Type", propOrder = {
    "linkage",
    "protocol",
    "applicationProfile",
    "name",
    "description",
    "function"
})
public class CIOnlineResourceType
    extends AbstractObjectType
    implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    @NotNull(message = "CIOnlineResourceType.linkage {javax.validation.constraints.NotNull.message}")
    @Valid
    protected URLPropertyType linkage;
    @Valid
    protected CharacterStringPropertyType protocol;
    @Valid
    protected CharacterStringPropertyType applicationProfile;
    @Valid
    protected CharacterStringPropertyType name;
    @Valid
    protected CharacterStringPropertyType description;
    @Valid
    protected CIOnLineFunctionCodePropertyType function;

    /**
     * Gets the value of the linkage property.
     * 
     * @return
     *     possible object is
     *     {@link URLPropertyType }
     *     
     */
    public URLPropertyType getLinkage() {
        return linkage;
    }

    /**
     * Sets the value of the linkage property.
     * 
     * @param value
     *     allowed object is
     *     {@link URLPropertyType }
     *     
     */
    public void setLinkage(URLPropertyType value) {
        this.linkage = value;
    }

    public boolean isSetLinkage() {
        return (this.linkage!= null);
    }

    /**
     * Gets the value of the protocol property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getProtocol() {
        return protocol;
    }

    /**
     * Sets the value of the protocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setProtocol(CharacterStringPropertyType value) {
        this.protocol = value;
    }

    public boolean isSetProtocol() {
        return (this.protocol!= null);
    }

    /**
     * Gets the value of the applicationProfile property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getApplicationProfile() {
        return applicationProfile;
    }

    /**
     * Sets the value of the applicationProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setApplicationProfile(CharacterStringPropertyType value) {
        this.applicationProfile = value;
    }

    public boolean isSetApplicationProfile() {
        return (this.applicationProfile!= null);
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setName(CharacterStringPropertyType value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

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
     * Gets the value of the function property.
     * 
     * @return
     *     possible object is
     *     {@link CIOnLineFunctionCodePropertyType }
     *     
     */
    public CIOnLineFunctionCodePropertyType getFunction() {
        return function;
    }

    /**
     * Sets the value of the function property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIOnLineFunctionCodePropertyType }
     *     
     */
    public void setFunction(CIOnLineFunctionCodePropertyType value) {
        this.function = value;
    }

    public boolean isSetFunction() {
        return (this.function!= null);
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
        super.appendFields(locator, buffer, strategy);
        {
            URLPropertyType theLinkage;
            theLinkage = this.getLinkage();
            strategy.appendField(locator, this, "linkage", buffer, theLinkage, this.isSetLinkage());
        }
        {
            CharacterStringPropertyType theProtocol;
            theProtocol = this.getProtocol();
            strategy.appendField(locator, this, "protocol", buffer, theProtocol, this.isSetProtocol());
        }
        {
            CharacterStringPropertyType theApplicationProfile;
            theApplicationProfile = this.getApplicationProfile();
            strategy.appendField(locator, this, "applicationProfile", buffer, theApplicationProfile, this.isSetApplicationProfile());
        }
        {
            CharacterStringPropertyType theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            CharacterStringPropertyType theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription, this.isSetDescription());
        }
        {
            CIOnLineFunctionCodePropertyType theFunction;
            theFunction = this.getFunction();
            strategy.appendField(locator, this, "function", buffer, theFunction, this.isSetFunction());
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
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final CIOnlineResourceType that = ((CIOnlineResourceType) object);
        {
            URLPropertyType lhsLinkage;
            lhsLinkage = this.getLinkage();
            URLPropertyType rhsLinkage;
            rhsLinkage = that.getLinkage();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "linkage", lhsLinkage), LocatorUtils.property(thatLocator, "linkage", rhsLinkage), lhsLinkage, rhsLinkage, this.isSetLinkage(), that.isSetLinkage())) {
                return false;
            }
        }
        {
            CharacterStringPropertyType lhsProtocol;
            lhsProtocol = this.getProtocol();
            CharacterStringPropertyType rhsProtocol;
            rhsProtocol = that.getProtocol();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "protocol", lhsProtocol), LocatorUtils.property(thatLocator, "protocol", rhsProtocol), lhsProtocol, rhsProtocol, this.isSetProtocol(), that.isSetProtocol())) {
                return false;
            }
        }
        {
            CharacterStringPropertyType lhsApplicationProfile;
            lhsApplicationProfile = this.getApplicationProfile();
            CharacterStringPropertyType rhsApplicationProfile;
            rhsApplicationProfile = that.getApplicationProfile();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "applicationProfile", lhsApplicationProfile), LocatorUtils.property(thatLocator, "applicationProfile", rhsApplicationProfile), lhsApplicationProfile, rhsApplicationProfile, this.isSetApplicationProfile(), that.isSetApplicationProfile())) {
                return false;
            }
        }
        {
            CharacterStringPropertyType lhsName;
            lhsName = this.getName();
            CharacterStringPropertyType rhsName;
            rhsName = that.getName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName, this.isSetName(), that.isSetName())) {
                return false;
            }
        }
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
            CIOnLineFunctionCodePropertyType lhsFunction;
            lhsFunction = this.getFunction();
            CIOnLineFunctionCodePropertyType rhsFunction;
            rhsFunction = that.getFunction();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "function", lhsFunction), LocatorUtils.property(thatLocator, "function", rhsFunction), lhsFunction, rhsFunction, this.isSetFunction(), that.isSetFunction())) {
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
        int currentHashCode = super.hashCode(locator, strategy);
        {
            URLPropertyType theLinkage;
            theLinkage = this.getLinkage();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "linkage", theLinkage), currentHashCode, theLinkage, this.isSetLinkage());
        }
        {
            CharacterStringPropertyType theProtocol;
            theProtocol = this.getProtocol();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "protocol", theProtocol), currentHashCode, theProtocol, this.isSetProtocol());
        }
        {
            CharacterStringPropertyType theApplicationProfile;
            theApplicationProfile = this.getApplicationProfile();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "applicationProfile", theApplicationProfile), currentHashCode, theApplicationProfile, this.isSetApplicationProfile());
        }
        {
            CharacterStringPropertyType theName;
            theName = this.getName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "name", theName), currentHashCode, theName, this.isSetName());
        }
        {
            CharacterStringPropertyType theDescription;
            theDescription = this.getDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription, this.isSetDescription());
        }
        {
            CIOnLineFunctionCodePropertyType theFunction;
            theFunction = this.getFunction();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "function", theFunction), currentHashCode, theFunction, this.isSetFunction());
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
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof CIOnlineResourceType) {
            final CIOnlineResourceType copy = ((CIOnlineResourceType) draftCopy);
            {
                Boolean linkageShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLinkage());
                if (linkageShouldBeCopiedAndSet == Boolean.TRUE) {
                    URLPropertyType sourceLinkage;
                    sourceLinkage = this.getLinkage();
                    URLPropertyType copyLinkage = ((URLPropertyType) strategy.copy(LocatorUtils.property(locator, "linkage", sourceLinkage), sourceLinkage, this.isSetLinkage()));
                    copy.setLinkage(copyLinkage);
                } else {
                    if (linkageShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.linkage = null;
                    }
                }
            }
            {
                Boolean protocolShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetProtocol());
                if (protocolShouldBeCopiedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType sourceProtocol;
                    sourceProtocol = this.getProtocol();
                    CharacterStringPropertyType copyProtocol = ((CharacterStringPropertyType) strategy.copy(LocatorUtils.property(locator, "protocol", sourceProtocol), sourceProtocol, this.isSetProtocol()));
                    copy.setProtocol(copyProtocol);
                } else {
                    if (protocolShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.protocol = null;
                    }
                }
            }
            {
                Boolean applicationProfileShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetApplicationProfile());
                if (applicationProfileShouldBeCopiedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType sourceApplicationProfile;
                    sourceApplicationProfile = this.getApplicationProfile();
                    CharacterStringPropertyType copyApplicationProfile = ((CharacterStringPropertyType) strategy.copy(LocatorUtils.property(locator, "applicationProfile", sourceApplicationProfile), sourceApplicationProfile, this.isSetApplicationProfile()));
                    copy.setApplicationProfile(copyApplicationProfile);
                } else {
                    if (applicationProfileShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.applicationProfile = null;
                    }
                }
            }
            {
                Boolean nameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetName());
                if (nameShouldBeCopiedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType sourceName;
                    sourceName = this.getName();
                    CharacterStringPropertyType copyName = ((CharacterStringPropertyType) strategy.copy(LocatorUtils.property(locator, "name", sourceName), sourceName, this.isSetName()));
                    copy.setName(copyName);
                } else {
                    if (nameShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.name = null;
                    }
                }
            }
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
                Boolean functionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetFunction());
                if (functionShouldBeCopiedAndSet == Boolean.TRUE) {
                    CIOnLineFunctionCodePropertyType sourceFunction;
                    sourceFunction = this.getFunction();
                    CIOnLineFunctionCodePropertyType copyFunction = ((CIOnLineFunctionCodePropertyType) strategy.copy(LocatorUtils.property(locator, "function", sourceFunction), sourceFunction, this.isSetFunction()));
                    copy.setFunction(copyFunction);
                } else {
                    if (functionShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.function = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new CIOnlineResourceType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof CIOnlineResourceType) {
            final CIOnlineResourceType target = this;
            final CIOnlineResourceType leftObject = ((CIOnlineResourceType) left);
            final CIOnlineResourceType rightObject = ((CIOnlineResourceType) right);
            {
                Boolean linkageShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLinkage(), rightObject.isSetLinkage());
                if (linkageShouldBeMergedAndSet == Boolean.TRUE) {
                    URLPropertyType lhsLinkage;
                    lhsLinkage = leftObject.getLinkage();
                    URLPropertyType rhsLinkage;
                    rhsLinkage = rightObject.getLinkage();
                    URLPropertyType mergedLinkage = ((URLPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "linkage", lhsLinkage), LocatorUtils.property(rightLocator, "linkage", rhsLinkage), lhsLinkage, rhsLinkage, leftObject.isSetLinkage(), rightObject.isSetLinkage()));
                    target.setLinkage(mergedLinkage);
                } else {
                    if (linkageShouldBeMergedAndSet == Boolean.FALSE) {
                        target.linkage = null;
                    }
                }
            }
            {
                Boolean protocolShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetProtocol(), rightObject.isSetProtocol());
                if (protocolShouldBeMergedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType lhsProtocol;
                    lhsProtocol = leftObject.getProtocol();
                    CharacterStringPropertyType rhsProtocol;
                    rhsProtocol = rightObject.getProtocol();
                    CharacterStringPropertyType mergedProtocol = ((CharacterStringPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "protocol", lhsProtocol), LocatorUtils.property(rightLocator, "protocol", rhsProtocol), lhsProtocol, rhsProtocol, leftObject.isSetProtocol(), rightObject.isSetProtocol()));
                    target.setProtocol(mergedProtocol);
                } else {
                    if (protocolShouldBeMergedAndSet == Boolean.FALSE) {
                        target.protocol = null;
                    }
                }
            }
            {
                Boolean applicationProfileShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetApplicationProfile(), rightObject.isSetApplicationProfile());
                if (applicationProfileShouldBeMergedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType lhsApplicationProfile;
                    lhsApplicationProfile = leftObject.getApplicationProfile();
                    CharacterStringPropertyType rhsApplicationProfile;
                    rhsApplicationProfile = rightObject.getApplicationProfile();
                    CharacterStringPropertyType mergedApplicationProfile = ((CharacterStringPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "applicationProfile", lhsApplicationProfile), LocatorUtils.property(rightLocator, "applicationProfile", rhsApplicationProfile), lhsApplicationProfile, rhsApplicationProfile, leftObject.isSetApplicationProfile(), rightObject.isSetApplicationProfile()));
                    target.setApplicationProfile(mergedApplicationProfile);
                } else {
                    if (applicationProfileShouldBeMergedAndSet == Boolean.FALSE) {
                        target.applicationProfile = null;
                    }
                }
            }
            {
                Boolean nameShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetName(), rightObject.isSetName());
                if (nameShouldBeMergedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType lhsName;
                    lhsName = leftObject.getName();
                    CharacterStringPropertyType rhsName;
                    rhsName = rightObject.getName();
                    CharacterStringPropertyType mergedName = ((CharacterStringPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "name", lhsName), LocatorUtils.property(rightLocator, "name", rhsName), lhsName, rhsName, leftObject.isSetName(), rightObject.isSetName()));
                    target.setName(mergedName);
                } else {
                    if (nameShouldBeMergedAndSet == Boolean.FALSE) {
                        target.name = null;
                    }
                }
            }
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
                Boolean functionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetFunction(), rightObject.isSetFunction());
                if (functionShouldBeMergedAndSet == Boolean.TRUE) {
                    CIOnLineFunctionCodePropertyType lhsFunction;
                    lhsFunction = leftObject.getFunction();
                    CIOnLineFunctionCodePropertyType rhsFunction;
                    rhsFunction = rightObject.getFunction();
                    CIOnLineFunctionCodePropertyType mergedFunction = ((CIOnLineFunctionCodePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "function", lhsFunction), LocatorUtils.property(rightLocator, "function", rhsFunction), lhsFunction, rhsFunction, leftObject.isSetFunction(), rightObject.isSetFunction()));
                    target.setFunction(mergedFunction);
                } else {
                    if (functionShouldBeMergedAndSet == Boolean.FALSE) {
                        target.function = null;
                    }
                }
            }
        }
    }

}
