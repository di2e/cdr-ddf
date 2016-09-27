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
 * Information about spatial, vertical, and temporal extent
 * 
 * <p>Java class for EX_Extent_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EX_Extent_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="description" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="geographicElement" type="{http://www.isotc211.org/2005/gmd}EX_GeographicExtent_PropertyType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="temporalElement" type="{http://www.isotc211.org/2005/gmd}EX_TemporalExtent_PropertyType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="verticalElement" type="{http://www.isotc211.org/2005/gmd}EX_VerticalExtent_PropertyType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EX_Extent_Type", propOrder = {
    "description",
    "geographicElement",
    "temporalElement",
    "verticalElement"
})
public class EXExtentType
    extends AbstractObjectType
    implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @Valid
    protected CharacterStringPropertyType description;
    @Valid
    protected List<EXGeographicExtentPropertyType> geographicElement;
    @Valid
    protected List<EXTemporalExtentPropertyType> temporalElement;
    @Valid
    protected List<EXVerticalExtentPropertyType> verticalElement;

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
     * Gets the value of the geographicElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the geographicElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGeographicElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EXGeographicExtentPropertyType }
     * 
     * 
     */
    public List<EXGeographicExtentPropertyType> getGeographicElement() {
        if (geographicElement == null) {
            geographicElement = new ArrayList<EXGeographicExtentPropertyType>();
        }
        return this.geographicElement;
    }

    public boolean isSetGeographicElement() {
        return ((this.geographicElement!= null)&&(!this.geographicElement.isEmpty()));
    }

    public void unsetGeographicElement() {
        this.geographicElement = null;
    }

    /**
     * Gets the value of the temporalElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the temporalElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTemporalElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EXTemporalExtentPropertyType }
     * 
     * 
     */
    public List<EXTemporalExtentPropertyType> getTemporalElement() {
        if (temporalElement == null) {
            temporalElement = new ArrayList<EXTemporalExtentPropertyType>();
        }
        return this.temporalElement;
    }

    public boolean isSetTemporalElement() {
        return ((this.temporalElement!= null)&&(!this.temporalElement.isEmpty()));
    }

    public void unsetTemporalElement() {
        this.temporalElement = null;
    }

    /**
     * Gets the value of the verticalElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the verticalElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVerticalElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EXVerticalExtentPropertyType }
     * 
     * 
     */
    public List<EXVerticalExtentPropertyType> getVerticalElement() {
        if (verticalElement == null) {
            verticalElement = new ArrayList<EXVerticalExtentPropertyType>();
        }
        return this.verticalElement;
    }

    public boolean isSetVerticalElement() {
        return ((this.verticalElement!= null)&&(!this.verticalElement.isEmpty()));
    }

    public void unsetVerticalElement() {
        this.verticalElement = null;
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
            CharacterStringPropertyType theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription, this.isSetDescription());
        }
        {
            List<EXGeographicExtentPropertyType> theGeographicElement;
            theGeographicElement = (this.isSetGeographicElement()?this.getGeographicElement():null);
            strategy.appendField(locator, this, "geographicElement", buffer, theGeographicElement, this.isSetGeographicElement());
        }
        {
            List<EXTemporalExtentPropertyType> theTemporalElement;
            theTemporalElement = (this.isSetTemporalElement()?this.getTemporalElement():null);
            strategy.appendField(locator, this, "temporalElement", buffer, theTemporalElement, this.isSetTemporalElement());
        }
        {
            List<EXVerticalExtentPropertyType> theVerticalElement;
            theVerticalElement = (this.isSetVerticalElement()?this.getVerticalElement():null);
            strategy.appendField(locator, this, "verticalElement", buffer, theVerticalElement, this.isSetVerticalElement());
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
        final EXExtentType that = ((EXExtentType) object);
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
            List<EXGeographicExtentPropertyType> lhsGeographicElement;
            lhsGeographicElement = (this.isSetGeographicElement()?this.getGeographicElement():null);
            List<EXGeographicExtentPropertyType> rhsGeographicElement;
            rhsGeographicElement = (that.isSetGeographicElement()?that.getGeographicElement():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "geographicElement", lhsGeographicElement), LocatorUtils.property(thatLocator, "geographicElement", rhsGeographicElement), lhsGeographicElement, rhsGeographicElement, this.isSetGeographicElement(), that.isSetGeographicElement())) {
                return false;
            }
        }
        {
            List<EXTemporalExtentPropertyType> lhsTemporalElement;
            lhsTemporalElement = (this.isSetTemporalElement()?this.getTemporalElement():null);
            List<EXTemporalExtentPropertyType> rhsTemporalElement;
            rhsTemporalElement = (that.isSetTemporalElement()?that.getTemporalElement():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "temporalElement", lhsTemporalElement), LocatorUtils.property(thatLocator, "temporalElement", rhsTemporalElement), lhsTemporalElement, rhsTemporalElement, this.isSetTemporalElement(), that.isSetTemporalElement())) {
                return false;
            }
        }
        {
            List<EXVerticalExtentPropertyType> lhsVerticalElement;
            lhsVerticalElement = (this.isSetVerticalElement()?this.getVerticalElement():null);
            List<EXVerticalExtentPropertyType> rhsVerticalElement;
            rhsVerticalElement = (that.isSetVerticalElement()?that.getVerticalElement():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "verticalElement", lhsVerticalElement), LocatorUtils.property(thatLocator, "verticalElement", rhsVerticalElement), lhsVerticalElement, rhsVerticalElement, this.isSetVerticalElement(), that.isSetVerticalElement())) {
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
            CharacterStringPropertyType theDescription;
            theDescription = this.getDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription, this.isSetDescription());
        }
        {
            List<EXGeographicExtentPropertyType> theGeographicElement;
            theGeographicElement = (this.isSetGeographicElement()?this.getGeographicElement():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "geographicElement", theGeographicElement), currentHashCode, theGeographicElement, this.isSetGeographicElement());
        }
        {
            List<EXTemporalExtentPropertyType> theTemporalElement;
            theTemporalElement = (this.isSetTemporalElement()?this.getTemporalElement():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "temporalElement", theTemporalElement), currentHashCode, theTemporalElement, this.isSetTemporalElement());
        }
        {
            List<EXVerticalExtentPropertyType> theVerticalElement;
            theVerticalElement = (this.isSetVerticalElement()?this.getVerticalElement():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "verticalElement", theVerticalElement), currentHashCode, theVerticalElement, this.isSetVerticalElement());
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
        if (draftCopy instanceof EXExtentType) {
            final EXExtentType copy = ((EXExtentType) draftCopy);
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
                Boolean geographicElementShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetGeographicElement());
                if (geographicElementShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<EXGeographicExtentPropertyType> sourceGeographicElement;
                    sourceGeographicElement = (this.isSetGeographicElement()?this.getGeographicElement():null);
                    @SuppressWarnings("unchecked")
                    List<EXGeographicExtentPropertyType> copyGeographicElement = ((List<EXGeographicExtentPropertyType> ) strategy.copy(LocatorUtils.property(locator, "geographicElement", sourceGeographicElement), sourceGeographicElement, this.isSetGeographicElement()));
                    copy.unsetGeographicElement();
                    if (copyGeographicElement!= null) {
                        List<EXGeographicExtentPropertyType> uniqueGeographicElementl = copy.getGeographicElement();
                        uniqueGeographicElementl.addAll(copyGeographicElement);
                    }
                } else {
                    if (geographicElementShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetGeographicElement();
                    }
                }
            }
            {
                Boolean temporalElementShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetTemporalElement());
                if (temporalElementShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<EXTemporalExtentPropertyType> sourceTemporalElement;
                    sourceTemporalElement = (this.isSetTemporalElement()?this.getTemporalElement():null);
                    @SuppressWarnings("unchecked")
                    List<EXTemporalExtentPropertyType> copyTemporalElement = ((List<EXTemporalExtentPropertyType> ) strategy.copy(LocatorUtils.property(locator, "temporalElement", sourceTemporalElement), sourceTemporalElement, this.isSetTemporalElement()));
                    copy.unsetTemporalElement();
                    if (copyTemporalElement!= null) {
                        List<EXTemporalExtentPropertyType> uniqueTemporalElementl = copy.getTemporalElement();
                        uniqueTemporalElementl.addAll(copyTemporalElement);
                    }
                } else {
                    if (temporalElementShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetTemporalElement();
                    }
                }
            }
            {
                Boolean verticalElementShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetVerticalElement());
                if (verticalElementShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<EXVerticalExtentPropertyType> sourceVerticalElement;
                    sourceVerticalElement = (this.isSetVerticalElement()?this.getVerticalElement():null);
                    @SuppressWarnings("unchecked")
                    List<EXVerticalExtentPropertyType> copyVerticalElement = ((List<EXVerticalExtentPropertyType> ) strategy.copy(LocatorUtils.property(locator, "verticalElement", sourceVerticalElement), sourceVerticalElement, this.isSetVerticalElement()));
                    copy.unsetVerticalElement();
                    if (copyVerticalElement!= null) {
                        List<EXVerticalExtentPropertyType> uniqueVerticalElementl = copy.getVerticalElement();
                        uniqueVerticalElementl.addAll(copyVerticalElement);
                    }
                } else {
                    if (verticalElementShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetVerticalElement();
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new EXExtentType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof EXExtentType) {
            final EXExtentType target = this;
            final EXExtentType leftObject = ((EXExtentType) left);
            final EXExtentType rightObject = ((EXExtentType) right);
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
                Boolean geographicElementShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetGeographicElement(), rightObject.isSetGeographicElement());
                if (geographicElementShouldBeMergedAndSet == Boolean.TRUE) {
                    List<EXGeographicExtentPropertyType> lhsGeographicElement;
                    lhsGeographicElement = (leftObject.isSetGeographicElement()?leftObject.getGeographicElement():null);
                    List<EXGeographicExtentPropertyType> rhsGeographicElement;
                    rhsGeographicElement = (rightObject.isSetGeographicElement()?rightObject.getGeographicElement():null);
                    List<EXGeographicExtentPropertyType> mergedGeographicElement = ((List<EXGeographicExtentPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "geographicElement", lhsGeographicElement), LocatorUtils.property(rightLocator, "geographicElement", rhsGeographicElement), lhsGeographicElement, rhsGeographicElement, leftObject.isSetGeographicElement(), rightObject.isSetGeographicElement()));
                    target.unsetGeographicElement();
                    if (mergedGeographicElement!= null) {
                        List<EXGeographicExtentPropertyType> uniqueGeographicElementl = target.getGeographicElement();
                        uniqueGeographicElementl.addAll(mergedGeographicElement);
                    }
                } else {
                    if (geographicElementShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetGeographicElement();
                    }
                }
            }
            {
                Boolean temporalElementShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetTemporalElement(), rightObject.isSetTemporalElement());
                if (temporalElementShouldBeMergedAndSet == Boolean.TRUE) {
                    List<EXTemporalExtentPropertyType> lhsTemporalElement;
                    lhsTemporalElement = (leftObject.isSetTemporalElement()?leftObject.getTemporalElement():null);
                    List<EXTemporalExtentPropertyType> rhsTemporalElement;
                    rhsTemporalElement = (rightObject.isSetTemporalElement()?rightObject.getTemporalElement():null);
                    List<EXTemporalExtentPropertyType> mergedTemporalElement = ((List<EXTemporalExtentPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "temporalElement", lhsTemporalElement), LocatorUtils.property(rightLocator, "temporalElement", rhsTemporalElement), lhsTemporalElement, rhsTemporalElement, leftObject.isSetTemporalElement(), rightObject.isSetTemporalElement()));
                    target.unsetTemporalElement();
                    if (mergedTemporalElement!= null) {
                        List<EXTemporalExtentPropertyType> uniqueTemporalElementl = target.getTemporalElement();
                        uniqueTemporalElementl.addAll(mergedTemporalElement);
                    }
                } else {
                    if (temporalElementShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetTemporalElement();
                    }
                }
            }
            {
                Boolean verticalElementShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetVerticalElement(), rightObject.isSetVerticalElement());
                if (verticalElementShouldBeMergedAndSet == Boolean.TRUE) {
                    List<EXVerticalExtentPropertyType> lhsVerticalElement;
                    lhsVerticalElement = (leftObject.isSetVerticalElement()?leftObject.getVerticalElement():null);
                    List<EXVerticalExtentPropertyType> rhsVerticalElement;
                    rhsVerticalElement = (rightObject.isSetVerticalElement()?rightObject.getVerticalElement():null);
                    List<EXVerticalExtentPropertyType> mergedVerticalElement = ((List<EXVerticalExtentPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "verticalElement", lhsVerticalElement), LocatorUtils.property(rightLocator, "verticalElement", rhsVerticalElement), lhsVerticalElement, rhsVerticalElement, leftObject.isSetVerticalElement(), rightObject.isSetVerticalElement()));
                    target.unsetVerticalElement();
                    if (mergedVerticalElement!= null) {
                        List<EXVerticalExtentPropertyType> uniqueVerticalElementl = target.getVerticalElement();
                        uniqueVerticalElementl.addAll(mergedVerticalElement);
                    }
                } else {
                    if (verticalElementShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetVerticalElement();
                    }
                }
            }
        }
    }

    public void setGeographicElement(List<EXGeographicExtentPropertyType> value) {
        this.geographicElement = null;
        if (value!= null) {
            List<EXGeographicExtentPropertyType> draftl = this.getGeographicElement();
            draftl.addAll(value);
        }
    }

    public void setTemporalElement(List<EXTemporalExtentPropertyType> value) {
        this.temporalElement = null;
        if (value!= null) {
            List<EXTemporalExtentPropertyType> draftl = this.getTemporalElement();
            draftl.addAll(value);
        }
    }

    public void setVerticalElement(List<EXVerticalExtentPropertyType> value) {
        this.verticalElement = null;
        if (value!= null) {
            List<EXVerticalExtentPropertyType> draftl = this.getVerticalElement();
            draftl.addAll(value);
        }
    }

}
