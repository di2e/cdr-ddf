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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.isotc211._2005.gco.AbstractObjectType;
import org.isotc211._2005.gts.TMPrimitivePropertyType;
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
 * Time period covered by the content of the dataset
 * 
 * <p>Java class for EX_TemporalExtent_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EX_TemporalExtent_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="extent" type="{http://www.isotc211.org/2005/gts}TM_Primitive_PropertyType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EX_TemporalExtent_Type", propOrder = {
    "extent"
})
@XmlSeeAlso({
    EXSpatialTemporalExtentType.class
})
public class EXTemporalExtentType
    extends AbstractObjectType
    implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    @NotNull(message = "EXTemporalExtentType.extent {javax.validation.constraints.NotNull.message}")
    @Valid
    protected TMPrimitivePropertyType extent;

    /**
     * Gets the value of the extent property.
     * 
     * @return
     *     possible object is
     *     {@link TMPrimitivePropertyType }
     *     
     */
    public TMPrimitivePropertyType getExtent() {
        return extent;
    }

    /**
     * Sets the value of the extent property.
     * 
     * @param value
     *     allowed object is
     *     {@link TMPrimitivePropertyType }
     *     
     */
    public void setExtent(TMPrimitivePropertyType value) {
        this.extent = value;
    }

    public boolean isSetExtent() {
        return (this.extent!= null);
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
            TMPrimitivePropertyType theExtent;
            theExtent = this.getExtent();
            strategy.appendField(locator, this, "extent", buffer, theExtent, this.isSetExtent());
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
        final EXTemporalExtentType that = ((EXTemporalExtentType) object);
        {
            TMPrimitivePropertyType lhsExtent;
            lhsExtent = this.getExtent();
            TMPrimitivePropertyType rhsExtent;
            rhsExtent = that.getExtent();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "extent", lhsExtent), LocatorUtils.property(thatLocator, "extent", rhsExtent), lhsExtent, rhsExtent, this.isSetExtent(), that.isSetExtent())) {
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
            TMPrimitivePropertyType theExtent;
            theExtent = this.getExtent();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "extent", theExtent), currentHashCode, theExtent, this.isSetExtent());
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
        if (draftCopy instanceof EXTemporalExtentType) {
            final EXTemporalExtentType copy = ((EXTemporalExtentType) draftCopy);
            {
                Boolean extentShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetExtent());
                if (extentShouldBeCopiedAndSet == Boolean.TRUE) {
                    TMPrimitivePropertyType sourceExtent;
                    sourceExtent = this.getExtent();
                    TMPrimitivePropertyType copyExtent = ((TMPrimitivePropertyType) strategy.copy(LocatorUtils.property(locator, "extent", sourceExtent), sourceExtent, this.isSetExtent()));
                    copy.setExtent(copyExtent);
                } else {
                    if (extentShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.extent = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new EXTemporalExtentType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof EXTemporalExtentType) {
            final EXTemporalExtentType target = this;
            final EXTemporalExtentType leftObject = ((EXTemporalExtentType) left);
            final EXTemporalExtentType rightObject = ((EXTemporalExtentType) right);
            {
                Boolean extentShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetExtent(), rightObject.isSetExtent());
                if (extentShouldBeMergedAndSet == Boolean.TRUE) {
                    TMPrimitivePropertyType lhsExtent;
                    lhsExtent = leftObject.getExtent();
                    TMPrimitivePropertyType rhsExtent;
                    rhsExtent = rightObject.getExtent();
                    TMPrimitivePropertyType mergedExtent = ((TMPrimitivePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "extent", lhsExtent), LocatorUtils.property(rightLocator, "extent", rhsExtent), lhsExtent, rhsExtent, leftObject.isSetExtent(), rightObject.isSetExtent()));
                    target.setExtent(mergedExtent);
                } else {
                    if (extentShouldBeMergedAndSet == Boolean.FALSE) {
                        target.extent = null;
                    }
                }
            }
        }
    }

}
