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
 * Location of the responsible individual or organisation
 * 
 * <p>Java class for CI_Address_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CI_Address_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="deliveryPoint" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="city" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="administrativeArea" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="postalCode" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="country" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="electronicMailAddress" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CI_Address_Type", propOrder = {
    "deliveryPoint",
    "city",
    "administrativeArea",
    "postalCode",
    "country",
    "electronicMailAddress"
})
public class CIAddressType
    extends AbstractObjectType
    implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @Valid
    protected List<CharacterStringPropertyType> deliveryPoint;
    @Valid
    protected CharacterStringPropertyType city;
    @Valid
    protected CharacterStringPropertyType administrativeArea;
    @Valid
    protected CharacterStringPropertyType postalCode;
    @Valid
    protected CharacterStringPropertyType country;
    @Valid
    protected List<CharacterStringPropertyType> electronicMailAddress;

    /**
     * Gets the value of the deliveryPoint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryPoint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryPoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacterStringPropertyType }
     * 
     * 
     */
    public List<CharacterStringPropertyType> getDeliveryPoint() {
        if (deliveryPoint == null) {
            deliveryPoint = new ArrayList<CharacterStringPropertyType>();
        }
        return this.deliveryPoint;
    }

    public boolean isSetDeliveryPoint() {
        return ((this.deliveryPoint!= null)&&(!this.deliveryPoint.isEmpty()));
    }

    public void unsetDeliveryPoint() {
        this.deliveryPoint = null;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setCity(CharacterStringPropertyType value) {
        this.city = value;
    }

    public boolean isSetCity() {
        return (this.city!= null);
    }

    /**
     * Gets the value of the administrativeArea property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getAdministrativeArea() {
        return administrativeArea;
    }

    /**
     * Sets the value of the administrativeArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setAdministrativeArea(CharacterStringPropertyType value) {
        this.administrativeArea = value;
    }

    public boolean isSetAdministrativeArea() {
        return (this.administrativeArea!= null);
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setPostalCode(CharacterStringPropertyType value) {
        this.postalCode = value;
    }

    public boolean isSetPostalCode() {
        return (this.postalCode!= null);
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setCountry(CharacterStringPropertyType value) {
        this.country = value;
    }

    public boolean isSetCountry() {
        return (this.country!= null);
    }

    /**
     * Gets the value of the electronicMailAddress property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the electronicMailAddress property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElectronicMailAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacterStringPropertyType }
     * 
     * 
     */
    public List<CharacterStringPropertyType> getElectronicMailAddress() {
        if (electronicMailAddress == null) {
            electronicMailAddress = new ArrayList<CharacterStringPropertyType>();
        }
        return this.electronicMailAddress;
    }

    public boolean isSetElectronicMailAddress() {
        return ((this.electronicMailAddress!= null)&&(!this.electronicMailAddress.isEmpty()));
    }

    public void unsetElectronicMailAddress() {
        this.electronicMailAddress = null;
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
            List<CharacterStringPropertyType> theDeliveryPoint;
            theDeliveryPoint = (this.isSetDeliveryPoint()?this.getDeliveryPoint():null);
            strategy.appendField(locator, this, "deliveryPoint", buffer, theDeliveryPoint, this.isSetDeliveryPoint());
        }
        {
            CharacterStringPropertyType theCity;
            theCity = this.getCity();
            strategy.appendField(locator, this, "city", buffer, theCity, this.isSetCity());
        }
        {
            CharacterStringPropertyType theAdministrativeArea;
            theAdministrativeArea = this.getAdministrativeArea();
            strategy.appendField(locator, this, "administrativeArea", buffer, theAdministrativeArea, this.isSetAdministrativeArea());
        }
        {
            CharacterStringPropertyType thePostalCode;
            thePostalCode = this.getPostalCode();
            strategy.appendField(locator, this, "postalCode", buffer, thePostalCode, this.isSetPostalCode());
        }
        {
            CharacterStringPropertyType theCountry;
            theCountry = this.getCountry();
            strategy.appendField(locator, this, "country", buffer, theCountry, this.isSetCountry());
        }
        {
            List<CharacterStringPropertyType> theElectronicMailAddress;
            theElectronicMailAddress = (this.isSetElectronicMailAddress()?this.getElectronicMailAddress():null);
            strategy.appendField(locator, this, "electronicMailAddress", buffer, theElectronicMailAddress, this.isSetElectronicMailAddress());
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
        final CIAddressType that = ((CIAddressType) object);
        {
            List<CharacterStringPropertyType> lhsDeliveryPoint;
            lhsDeliveryPoint = (this.isSetDeliveryPoint()?this.getDeliveryPoint():null);
            List<CharacterStringPropertyType> rhsDeliveryPoint;
            rhsDeliveryPoint = (that.isSetDeliveryPoint()?that.getDeliveryPoint():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "deliveryPoint", lhsDeliveryPoint), LocatorUtils.property(thatLocator, "deliveryPoint", rhsDeliveryPoint), lhsDeliveryPoint, rhsDeliveryPoint, this.isSetDeliveryPoint(), that.isSetDeliveryPoint())) {
                return false;
            }
        }
        {
            CharacterStringPropertyType lhsCity;
            lhsCity = this.getCity();
            CharacterStringPropertyType rhsCity;
            rhsCity = that.getCity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "city", lhsCity), LocatorUtils.property(thatLocator, "city", rhsCity), lhsCity, rhsCity, this.isSetCity(), that.isSetCity())) {
                return false;
            }
        }
        {
            CharacterStringPropertyType lhsAdministrativeArea;
            lhsAdministrativeArea = this.getAdministrativeArea();
            CharacterStringPropertyType rhsAdministrativeArea;
            rhsAdministrativeArea = that.getAdministrativeArea();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "administrativeArea", lhsAdministrativeArea), LocatorUtils.property(thatLocator, "administrativeArea", rhsAdministrativeArea), lhsAdministrativeArea, rhsAdministrativeArea, this.isSetAdministrativeArea(), that.isSetAdministrativeArea())) {
                return false;
            }
        }
        {
            CharacterStringPropertyType lhsPostalCode;
            lhsPostalCode = this.getPostalCode();
            CharacterStringPropertyType rhsPostalCode;
            rhsPostalCode = that.getPostalCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "postalCode", lhsPostalCode), LocatorUtils.property(thatLocator, "postalCode", rhsPostalCode), lhsPostalCode, rhsPostalCode, this.isSetPostalCode(), that.isSetPostalCode())) {
                return false;
            }
        }
        {
            CharacterStringPropertyType lhsCountry;
            lhsCountry = this.getCountry();
            CharacterStringPropertyType rhsCountry;
            rhsCountry = that.getCountry();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "country", lhsCountry), LocatorUtils.property(thatLocator, "country", rhsCountry), lhsCountry, rhsCountry, this.isSetCountry(), that.isSetCountry())) {
                return false;
            }
        }
        {
            List<CharacterStringPropertyType> lhsElectronicMailAddress;
            lhsElectronicMailAddress = (this.isSetElectronicMailAddress()?this.getElectronicMailAddress():null);
            List<CharacterStringPropertyType> rhsElectronicMailAddress;
            rhsElectronicMailAddress = (that.isSetElectronicMailAddress()?that.getElectronicMailAddress():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "electronicMailAddress", lhsElectronicMailAddress), LocatorUtils.property(thatLocator, "electronicMailAddress", rhsElectronicMailAddress), lhsElectronicMailAddress, rhsElectronicMailAddress, this.isSetElectronicMailAddress(), that.isSetElectronicMailAddress())) {
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
            List<CharacterStringPropertyType> theDeliveryPoint;
            theDeliveryPoint = (this.isSetDeliveryPoint()?this.getDeliveryPoint():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "deliveryPoint", theDeliveryPoint), currentHashCode, theDeliveryPoint, this.isSetDeliveryPoint());
        }
        {
            CharacterStringPropertyType theCity;
            theCity = this.getCity();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "city", theCity), currentHashCode, theCity, this.isSetCity());
        }
        {
            CharacterStringPropertyType theAdministrativeArea;
            theAdministrativeArea = this.getAdministrativeArea();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "administrativeArea", theAdministrativeArea), currentHashCode, theAdministrativeArea, this.isSetAdministrativeArea());
        }
        {
            CharacterStringPropertyType thePostalCode;
            thePostalCode = this.getPostalCode();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "postalCode", thePostalCode), currentHashCode, thePostalCode, this.isSetPostalCode());
        }
        {
            CharacterStringPropertyType theCountry;
            theCountry = this.getCountry();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "country", theCountry), currentHashCode, theCountry, this.isSetCountry());
        }
        {
            List<CharacterStringPropertyType> theElectronicMailAddress;
            theElectronicMailAddress = (this.isSetElectronicMailAddress()?this.getElectronicMailAddress():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "electronicMailAddress", theElectronicMailAddress), currentHashCode, theElectronicMailAddress, this.isSetElectronicMailAddress());
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
        if (draftCopy instanceof CIAddressType) {
            final CIAddressType copy = ((CIAddressType) draftCopy);
            {
                Boolean deliveryPointShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDeliveryPoint());
                if (deliveryPointShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<CharacterStringPropertyType> sourceDeliveryPoint;
                    sourceDeliveryPoint = (this.isSetDeliveryPoint()?this.getDeliveryPoint():null);
                    @SuppressWarnings("unchecked")
                    List<CharacterStringPropertyType> copyDeliveryPoint = ((List<CharacterStringPropertyType> ) strategy.copy(LocatorUtils.property(locator, "deliveryPoint", sourceDeliveryPoint), sourceDeliveryPoint, this.isSetDeliveryPoint()));
                    copy.unsetDeliveryPoint();
                    if (copyDeliveryPoint!= null) {
                        List<CharacterStringPropertyType> uniqueDeliveryPointl = copy.getDeliveryPoint();
                        uniqueDeliveryPointl.addAll(copyDeliveryPoint);
                    }
                } else {
                    if (deliveryPointShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetDeliveryPoint();
                    }
                }
            }
            {
                Boolean cityShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCity());
                if (cityShouldBeCopiedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType sourceCity;
                    sourceCity = this.getCity();
                    CharacterStringPropertyType copyCity = ((CharacterStringPropertyType) strategy.copy(LocatorUtils.property(locator, "city", sourceCity), sourceCity, this.isSetCity()));
                    copy.setCity(copyCity);
                } else {
                    if (cityShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.city = null;
                    }
                }
            }
            {
                Boolean administrativeAreaShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAdministrativeArea());
                if (administrativeAreaShouldBeCopiedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType sourceAdministrativeArea;
                    sourceAdministrativeArea = this.getAdministrativeArea();
                    CharacterStringPropertyType copyAdministrativeArea = ((CharacterStringPropertyType) strategy.copy(LocatorUtils.property(locator, "administrativeArea", sourceAdministrativeArea), sourceAdministrativeArea, this.isSetAdministrativeArea()));
                    copy.setAdministrativeArea(copyAdministrativeArea);
                } else {
                    if (administrativeAreaShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.administrativeArea = null;
                    }
                }
            }
            {
                Boolean postalCodeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPostalCode());
                if (postalCodeShouldBeCopiedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType sourcePostalCode;
                    sourcePostalCode = this.getPostalCode();
                    CharacterStringPropertyType copyPostalCode = ((CharacterStringPropertyType) strategy.copy(LocatorUtils.property(locator, "postalCode", sourcePostalCode), sourcePostalCode, this.isSetPostalCode()));
                    copy.setPostalCode(copyPostalCode);
                } else {
                    if (postalCodeShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.postalCode = null;
                    }
                }
            }
            {
                Boolean countryShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCountry());
                if (countryShouldBeCopiedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType sourceCountry;
                    sourceCountry = this.getCountry();
                    CharacterStringPropertyType copyCountry = ((CharacterStringPropertyType) strategy.copy(LocatorUtils.property(locator, "country", sourceCountry), sourceCountry, this.isSetCountry()));
                    copy.setCountry(copyCountry);
                } else {
                    if (countryShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.country = null;
                    }
                }
            }
            {
                Boolean electronicMailAddressShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetElectronicMailAddress());
                if (electronicMailAddressShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<CharacterStringPropertyType> sourceElectronicMailAddress;
                    sourceElectronicMailAddress = (this.isSetElectronicMailAddress()?this.getElectronicMailAddress():null);
                    @SuppressWarnings("unchecked")
                    List<CharacterStringPropertyType> copyElectronicMailAddress = ((List<CharacterStringPropertyType> ) strategy.copy(LocatorUtils.property(locator, "electronicMailAddress", sourceElectronicMailAddress), sourceElectronicMailAddress, this.isSetElectronicMailAddress()));
                    copy.unsetElectronicMailAddress();
                    if (copyElectronicMailAddress!= null) {
                        List<CharacterStringPropertyType> uniqueElectronicMailAddressl = copy.getElectronicMailAddress();
                        uniqueElectronicMailAddressl.addAll(copyElectronicMailAddress);
                    }
                } else {
                    if (electronicMailAddressShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetElectronicMailAddress();
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new CIAddressType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof CIAddressType) {
            final CIAddressType target = this;
            final CIAddressType leftObject = ((CIAddressType) left);
            final CIAddressType rightObject = ((CIAddressType) right);
            {
                Boolean deliveryPointShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDeliveryPoint(), rightObject.isSetDeliveryPoint());
                if (deliveryPointShouldBeMergedAndSet == Boolean.TRUE) {
                    List<CharacterStringPropertyType> lhsDeliveryPoint;
                    lhsDeliveryPoint = (leftObject.isSetDeliveryPoint()?leftObject.getDeliveryPoint():null);
                    List<CharacterStringPropertyType> rhsDeliveryPoint;
                    rhsDeliveryPoint = (rightObject.isSetDeliveryPoint()?rightObject.getDeliveryPoint():null);
                    List<CharacterStringPropertyType> mergedDeliveryPoint = ((List<CharacterStringPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "deliveryPoint", lhsDeliveryPoint), LocatorUtils.property(rightLocator, "deliveryPoint", rhsDeliveryPoint), lhsDeliveryPoint, rhsDeliveryPoint, leftObject.isSetDeliveryPoint(), rightObject.isSetDeliveryPoint()));
                    target.unsetDeliveryPoint();
                    if (mergedDeliveryPoint!= null) {
                        List<CharacterStringPropertyType> uniqueDeliveryPointl = target.getDeliveryPoint();
                        uniqueDeliveryPointl.addAll(mergedDeliveryPoint);
                    }
                } else {
                    if (deliveryPointShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetDeliveryPoint();
                    }
                }
            }
            {
                Boolean cityShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCity(), rightObject.isSetCity());
                if (cityShouldBeMergedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType lhsCity;
                    lhsCity = leftObject.getCity();
                    CharacterStringPropertyType rhsCity;
                    rhsCity = rightObject.getCity();
                    CharacterStringPropertyType mergedCity = ((CharacterStringPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "city", lhsCity), LocatorUtils.property(rightLocator, "city", rhsCity), lhsCity, rhsCity, leftObject.isSetCity(), rightObject.isSetCity()));
                    target.setCity(mergedCity);
                } else {
                    if (cityShouldBeMergedAndSet == Boolean.FALSE) {
                        target.city = null;
                    }
                }
            }
            {
                Boolean administrativeAreaShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAdministrativeArea(), rightObject.isSetAdministrativeArea());
                if (administrativeAreaShouldBeMergedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType lhsAdministrativeArea;
                    lhsAdministrativeArea = leftObject.getAdministrativeArea();
                    CharacterStringPropertyType rhsAdministrativeArea;
                    rhsAdministrativeArea = rightObject.getAdministrativeArea();
                    CharacterStringPropertyType mergedAdministrativeArea = ((CharacterStringPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "administrativeArea", lhsAdministrativeArea), LocatorUtils.property(rightLocator, "administrativeArea", rhsAdministrativeArea), lhsAdministrativeArea, rhsAdministrativeArea, leftObject.isSetAdministrativeArea(), rightObject.isSetAdministrativeArea()));
                    target.setAdministrativeArea(mergedAdministrativeArea);
                } else {
                    if (administrativeAreaShouldBeMergedAndSet == Boolean.FALSE) {
                        target.administrativeArea = null;
                    }
                }
            }
            {
                Boolean postalCodeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPostalCode(), rightObject.isSetPostalCode());
                if (postalCodeShouldBeMergedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType lhsPostalCode;
                    lhsPostalCode = leftObject.getPostalCode();
                    CharacterStringPropertyType rhsPostalCode;
                    rhsPostalCode = rightObject.getPostalCode();
                    CharacterStringPropertyType mergedPostalCode = ((CharacterStringPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "postalCode", lhsPostalCode), LocatorUtils.property(rightLocator, "postalCode", rhsPostalCode), lhsPostalCode, rhsPostalCode, leftObject.isSetPostalCode(), rightObject.isSetPostalCode()));
                    target.setPostalCode(mergedPostalCode);
                } else {
                    if (postalCodeShouldBeMergedAndSet == Boolean.FALSE) {
                        target.postalCode = null;
                    }
                }
            }
            {
                Boolean countryShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCountry(), rightObject.isSetCountry());
                if (countryShouldBeMergedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType lhsCountry;
                    lhsCountry = leftObject.getCountry();
                    CharacterStringPropertyType rhsCountry;
                    rhsCountry = rightObject.getCountry();
                    CharacterStringPropertyType mergedCountry = ((CharacterStringPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "country", lhsCountry), LocatorUtils.property(rightLocator, "country", rhsCountry), lhsCountry, rhsCountry, leftObject.isSetCountry(), rightObject.isSetCountry()));
                    target.setCountry(mergedCountry);
                } else {
                    if (countryShouldBeMergedAndSet == Boolean.FALSE) {
                        target.country = null;
                    }
                }
            }
            {
                Boolean electronicMailAddressShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetElectronicMailAddress(), rightObject.isSetElectronicMailAddress());
                if (electronicMailAddressShouldBeMergedAndSet == Boolean.TRUE) {
                    List<CharacterStringPropertyType> lhsElectronicMailAddress;
                    lhsElectronicMailAddress = (leftObject.isSetElectronicMailAddress()?leftObject.getElectronicMailAddress():null);
                    List<CharacterStringPropertyType> rhsElectronicMailAddress;
                    rhsElectronicMailAddress = (rightObject.isSetElectronicMailAddress()?rightObject.getElectronicMailAddress():null);
                    List<CharacterStringPropertyType> mergedElectronicMailAddress = ((List<CharacterStringPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "electronicMailAddress", lhsElectronicMailAddress), LocatorUtils.property(rightLocator, "electronicMailAddress", rhsElectronicMailAddress), lhsElectronicMailAddress, rhsElectronicMailAddress, leftObject.isSetElectronicMailAddress(), rightObject.isSetElectronicMailAddress()));
                    target.unsetElectronicMailAddress();
                    if (mergedElectronicMailAddress!= null) {
                        List<CharacterStringPropertyType> uniqueElectronicMailAddressl = target.getElectronicMailAddress();
                        uniqueElectronicMailAddressl.addAll(mergedElectronicMailAddress);
                    }
                } else {
                    if (electronicMailAddressShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetElectronicMailAddress();
                    }
                }
            }
        }
    }

    public void setDeliveryPoint(List<CharacterStringPropertyType> value) {
        this.deliveryPoint = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getDeliveryPoint();
            draftl.addAll(value);
        }
    }

    public void setElectronicMailAddress(List<CharacterStringPropertyType> value) {
        this.electronicMailAddress = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getElectronicMailAddress();
            draftl.addAll(value);
        }
    }

}
