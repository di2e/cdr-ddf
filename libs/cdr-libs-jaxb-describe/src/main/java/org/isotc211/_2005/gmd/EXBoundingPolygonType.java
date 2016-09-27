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
import org.isotc211._2005.gss.GMObjectPropertyType;
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
 * Boundary enclosing the dataset expressed as the closed set of (x,y) coordinates of the polygon (last point replicates first point)
 * 
 * <p>Java class for EX_BoundingPolygon_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EX_BoundingPolygon_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.isotc211.org/2005/gmd}AbstractEX_GeographicExtent_Type"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="polygon" type="{http://www.isotc211.org/2005/gss}GM_Object_PropertyType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EX_BoundingPolygon_Type", propOrder = {
    "polygon"
})
public class EXBoundingPolygonType
    extends AbstractEXGeographicExtentType
    implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    @NotNull(message = "EXBoundingPolygonType.polygon {javax.validation.constraints.NotNull.message}")
    @Size(min = 1)
    @Valid
    protected List<GMObjectPropertyType> polygon;

    /**
     * Gets the value of the polygon property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the polygon property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPolygon().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GMObjectPropertyType }
     * 
     * 
     */
    public List<GMObjectPropertyType> getPolygon() {
        if (polygon == null) {
            polygon = new ArrayList<GMObjectPropertyType>();
        }
        return this.polygon;
    }

    public boolean isSetPolygon() {
        return ((this.polygon!= null)&&(!this.polygon.isEmpty()));
    }

    public void unsetPolygon() {
        this.polygon = null;
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
            List<GMObjectPropertyType> thePolygon;
            thePolygon = (this.isSetPolygon()?this.getPolygon():null);
            strategy.appendField(locator, this, "polygon", buffer, thePolygon, this.isSetPolygon());
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
        final EXBoundingPolygonType that = ((EXBoundingPolygonType) object);
        {
            List<GMObjectPropertyType> lhsPolygon;
            lhsPolygon = (this.isSetPolygon()?this.getPolygon():null);
            List<GMObjectPropertyType> rhsPolygon;
            rhsPolygon = (that.isSetPolygon()?that.getPolygon():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "polygon", lhsPolygon), LocatorUtils.property(thatLocator, "polygon", rhsPolygon), lhsPolygon, rhsPolygon, this.isSetPolygon(), that.isSetPolygon())) {
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
            List<GMObjectPropertyType> thePolygon;
            thePolygon = (this.isSetPolygon()?this.getPolygon():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "polygon", thePolygon), currentHashCode, thePolygon, this.isSetPolygon());
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
        if (draftCopy instanceof EXBoundingPolygonType) {
            final EXBoundingPolygonType copy = ((EXBoundingPolygonType) draftCopy);
            {
                Boolean polygonShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPolygon());
                if (polygonShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<GMObjectPropertyType> sourcePolygon;
                    sourcePolygon = (this.isSetPolygon()?this.getPolygon():null);
                    @SuppressWarnings("unchecked")
                    List<GMObjectPropertyType> copyPolygon = ((List<GMObjectPropertyType> ) strategy.copy(LocatorUtils.property(locator, "polygon", sourcePolygon), sourcePolygon, this.isSetPolygon()));
                    copy.unsetPolygon();
                    if (copyPolygon!= null) {
                        List<GMObjectPropertyType> uniquePolygonl = copy.getPolygon();
                        uniquePolygonl.addAll(copyPolygon);
                    }
                } else {
                    if (polygonShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetPolygon();
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new EXBoundingPolygonType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof EXBoundingPolygonType) {
            final EXBoundingPolygonType target = this;
            final EXBoundingPolygonType leftObject = ((EXBoundingPolygonType) left);
            final EXBoundingPolygonType rightObject = ((EXBoundingPolygonType) right);
            {
                Boolean polygonShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPolygon(), rightObject.isSetPolygon());
                if (polygonShouldBeMergedAndSet == Boolean.TRUE) {
                    List<GMObjectPropertyType> lhsPolygon;
                    lhsPolygon = (leftObject.isSetPolygon()?leftObject.getPolygon():null);
                    List<GMObjectPropertyType> rhsPolygon;
                    rhsPolygon = (rightObject.isSetPolygon()?rightObject.getPolygon():null);
                    List<GMObjectPropertyType> mergedPolygon = ((List<GMObjectPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "polygon", lhsPolygon), LocatorUtils.property(rightLocator, "polygon", rhsPolygon), lhsPolygon, rhsPolygon, leftObject.isSetPolygon(), rightObject.isSetPolygon()));
                    target.unsetPolygon();
                    if (mergedPolygon!= null) {
                        List<GMObjectPropertyType> uniquePolygonl = target.getPolygon();
                        uniquePolygonl.addAll(mergedPolygon);
                    }
                } else {
                    if (polygonShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetPolygon();
                    }
                }
            }
        }
    }

    public void setPolygon(List<GMObjectPropertyType> value) {
        this.polygon = null;
        if (value!= null) {
            List<GMObjectPropertyType> draftl = this.getPolygon();
            draftl.addAll(value);
        }
    }

}
