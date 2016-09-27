//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package net.opengis.gml._3_3.ce;

import java.io.Serializable;
import java.math.BigInteger;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import mil.ces.metadata.mdr.ns.gsip.tspi._2_0.core.CircleType;
import net.opengis.gml.v_3_2_1.AbstractCurveType;
import net.opengis.gml.v_3_2_1.AngleType;
import net.opengis.gml.v_3_2_1.CurveInterpolationType;
import net.opengis.gml.v_3_2_1.DirectPositionListType;
import net.opengis.gml.v_3_2_1.DirectPositionType;
import net.opengis.gml.v_3_2_1.LengthType;
import net.opengis.gml.v_3_2_1.PointPropertyType;
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
 * <p>Java class for SimpleArcByCenterPointType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SimpleArcByCenterPointType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/gml/3.2}AbstractCurveType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;choice&gt;
 *             &lt;element ref="{http://www.opengis.net/gml/3.2}pos"/&gt;
 *             &lt;element ref="{http://www.opengis.net/gml/3.2}pointProperty"/&gt;
 *           &lt;/choice&gt;
 *           &lt;element ref="{http://www.opengis.net/gml/3.2}posList"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="radius" type="{http://www.opengis.net/gml/3.2}LengthType"/&gt;
 *         &lt;element name="startAngle" type="{http://www.opengis.net/gml/3.2}AngleType"/&gt;
 *         &lt;element name="endAngle" type="{http://www.opengis.net/gml/3.2}AngleType"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="interpolation" type="{http://www.opengis.net/gml/3.2}CurveInterpolationType" fixed="circularArcCenterPointWithRadius" /&gt;
 *       &lt;attribute name="numArc" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" fixed="1" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimpleArcByCenterPointType", propOrder = {
    "pos",
    "pointProperty",
    "posList",
    "radius",
    "startAngle",
    "endAngle"
})
@XmlSeeAlso({
    CircleType.class
})
public class SimpleArcByCenterPointType
    extends AbstractCurveType
    implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(namespace = "http://www.opengis.net/gml/3.2")
    @Valid
    protected DirectPositionType pos;
    @XmlElement(namespace = "http://www.opengis.net/gml/3.2")
    @Valid
    protected PointPropertyType pointProperty;
    @XmlElement(namespace = "http://www.opengis.net/gml/3.2")
    @Valid
    protected DirectPositionListType posList;
    @XmlElement(required = true)
    @NotNull(message = "SimpleArcByCenterPointType.radius {javax.validation.constraints.NotNull.message}")
    @Valid
    protected LengthType radius;
    @XmlElement(required = true)
    @NotNull(message = "SimpleArcByCenterPointType.startAngle {javax.validation.constraints.NotNull.message}")
    @Valid
    protected AngleType startAngle;
    @XmlElement(required = true)
    @NotNull(message = "SimpleArcByCenterPointType.endAngle {javax.validation.constraints.NotNull.message}")
    @Valid
    protected AngleType endAngle;
    @XmlAttribute(name = "interpolation")
    protected CurveInterpolationType interpolation;
    @XmlAttribute(name = "numArc", required = true)
    @NotNull(message = "SimpleArcByCenterPointType.numArc {javax.validation.constraints.NotNull.message}")
    protected BigInteger numArc;

    /**
     * Gets the value of the pos property.
     * 
     * @return
     *     possible object is
     *     {@link DirectPositionType }
     *     
     */
    public DirectPositionType getPos() {
        return pos;
    }

    /**
     * Sets the value of the pos property.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectPositionType }
     *     
     */
    public void setPos(DirectPositionType value) {
        this.pos = value;
    }

    public boolean isSetPos() {
        return (this.pos!= null);
    }

    /**
     * Gets the value of the pointProperty property.
     * 
     * @return
     *     possible object is
     *     {@link PointPropertyType }
     *     
     */
    public PointPropertyType getPointProperty() {
        return pointProperty;
    }

    /**
     * Sets the value of the pointProperty property.
     * 
     * @param value
     *     allowed object is
     *     {@link PointPropertyType }
     *     
     */
    public void setPointProperty(PointPropertyType value) {
        this.pointProperty = value;
    }

    public boolean isSetPointProperty() {
        return (this.pointProperty!= null);
    }

    /**
     * Gets the value of the posList property.
     * 
     * @return
     *     possible object is
     *     {@link DirectPositionListType }
     *     
     */
    public DirectPositionListType getPosList() {
        return posList;
    }

    /**
     * Sets the value of the posList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectPositionListType }
     *     
     */
    public void setPosList(DirectPositionListType value) {
        this.posList = value;
    }

    public boolean isSetPosList() {
        return (this.posList!= null);
    }

    /**
     * Gets the value of the radius property.
     * 
     * @return
     *     possible object is
     *     {@link LengthType }
     *     
     */
    public LengthType getRadius() {
        return radius;
    }

    /**
     * Sets the value of the radius property.
     * 
     * @param value
     *     allowed object is
     *     {@link LengthType }
     *     
     */
    public void setRadius(LengthType value) {
        this.radius = value;
    }

    public boolean isSetRadius() {
        return (this.radius!= null);
    }

    /**
     * Gets the value of the startAngle property.
     * 
     * @return
     *     possible object is
     *     {@link AngleType }
     *     
     */
    public AngleType getStartAngle() {
        return startAngle;
    }

    /**
     * Sets the value of the startAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link AngleType }
     *     
     */
    public void setStartAngle(AngleType value) {
        this.startAngle = value;
    }

    public boolean isSetStartAngle() {
        return (this.startAngle!= null);
    }

    /**
     * Gets the value of the endAngle property.
     * 
     * @return
     *     possible object is
     *     {@link AngleType }
     *     
     */
    public AngleType getEndAngle() {
        return endAngle;
    }

    /**
     * Sets the value of the endAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link AngleType }
     *     
     */
    public void setEndAngle(AngleType value) {
        this.endAngle = value;
    }

    public boolean isSetEndAngle() {
        return (this.endAngle!= null);
    }

    /**
     * Gets the value of the interpolation property.
     * 
     * @return
     *     possible object is
     *     {@link CurveInterpolationType }
     *     
     */
    public CurveInterpolationType getInterpolation() {
        return interpolation;
    }

    /**
     * Sets the value of the interpolation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurveInterpolationType }
     *     
     */
    public void setInterpolation(CurveInterpolationType value) {
        this.interpolation = value;
    }

    public boolean isSetInterpolation() {
        return (this.interpolation!= null);
    }

    /**
     * Gets the value of the numArc property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumArc() {
        if (numArc == null) {
            return new BigInteger("1");
        } else {
            return numArc;
        }
    }

    /**
     * Sets the value of the numArc property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumArc(BigInteger value) {
        this.numArc = value;
    }

    public boolean isSetNumArc() {
        return (this.numArc!= null);
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
            DirectPositionType thePos;
            thePos = this.getPos();
            strategy.appendField(locator, this, "pos", buffer, thePos, this.isSetPos());
        }
        {
            PointPropertyType thePointProperty;
            thePointProperty = this.getPointProperty();
            strategy.appendField(locator, this, "pointProperty", buffer, thePointProperty, this.isSetPointProperty());
        }
        {
            DirectPositionListType thePosList;
            thePosList = this.getPosList();
            strategy.appendField(locator, this, "posList", buffer, thePosList, this.isSetPosList());
        }
        {
            LengthType theRadius;
            theRadius = this.getRadius();
            strategy.appendField(locator, this, "radius", buffer, theRadius, this.isSetRadius());
        }
        {
            AngleType theStartAngle;
            theStartAngle = this.getStartAngle();
            strategy.appendField(locator, this, "startAngle", buffer, theStartAngle, this.isSetStartAngle());
        }
        {
            AngleType theEndAngle;
            theEndAngle = this.getEndAngle();
            strategy.appendField(locator, this, "endAngle", buffer, theEndAngle, this.isSetEndAngle());
        }
        {
            CurveInterpolationType theInterpolation;
            theInterpolation = this.getInterpolation();
            strategy.appendField(locator, this, "interpolation", buffer, theInterpolation, this.isSetInterpolation());
        }
        {
            BigInteger theNumArc;
            theNumArc = this.getNumArc();
            strategy.appendField(locator, this, "numArc", buffer, theNumArc, this.isSetNumArc());
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
        final SimpleArcByCenterPointType that = ((SimpleArcByCenterPointType) object);
        {
            DirectPositionType lhsPos;
            lhsPos = this.getPos();
            DirectPositionType rhsPos;
            rhsPos = that.getPos();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pos", lhsPos), LocatorUtils.property(thatLocator, "pos", rhsPos), lhsPos, rhsPos, this.isSetPos(), that.isSetPos())) {
                return false;
            }
        }
        {
            PointPropertyType lhsPointProperty;
            lhsPointProperty = this.getPointProperty();
            PointPropertyType rhsPointProperty;
            rhsPointProperty = that.getPointProperty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pointProperty", lhsPointProperty), LocatorUtils.property(thatLocator, "pointProperty", rhsPointProperty), lhsPointProperty, rhsPointProperty, this.isSetPointProperty(), that.isSetPointProperty())) {
                return false;
            }
        }
        {
            DirectPositionListType lhsPosList;
            lhsPosList = this.getPosList();
            DirectPositionListType rhsPosList;
            rhsPosList = that.getPosList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "posList", lhsPosList), LocatorUtils.property(thatLocator, "posList", rhsPosList), lhsPosList, rhsPosList, this.isSetPosList(), that.isSetPosList())) {
                return false;
            }
        }
        {
            LengthType lhsRadius;
            lhsRadius = this.getRadius();
            LengthType rhsRadius;
            rhsRadius = that.getRadius();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "radius", lhsRadius), LocatorUtils.property(thatLocator, "radius", rhsRadius), lhsRadius, rhsRadius, this.isSetRadius(), that.isSetRadius())) {
                return false;
            }
        }
        {
            AngleType lhsStartAngle;
            lhsStartAngle = this.getStartAngle();
            AngleType rhsStartAngle;
            rhsStartAngle = that.getStartAngle();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "startAngle", lhsStartAngle), LocatorUtils.property(thatLocator, "startAngle", rhsStartAngle), lhsStartAngle, rhsStartAngle, this.isSetStartAngle(), that.isSetStartAngle())) {
                return false;
            }
        }
        {
            AngleType lhsEndAngle;
            lhsEndAngle = this.getEndAngle();
            AngleType rhsEndAngle;
            rhsEndAngle = that.getEndAngle();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "endAngle", lhsEndAngle), LocatorUtils.property(thatLocator, "endAngle", rhsEndAngle), lhsEndAngle, rhsEndAngle, this.isSetEndAngle(), that.isSetEndAngle())) {
                return false;
            }
        }
        {
            CurveInterpolationType lhsInterpolation;
            lhsInterpolation = this.getInterpolation();
            CurveInterpolationType rhsInterpolation;
            rhsInterpolation = that.getInterpolation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "interpolation", lhsInterpolation), LocatorUtils.property(thatLocator, "interpolation", rhsInterpolation), lhsInterpolation, rhsInterpolation, this.isSetInterpolation(), that.isSetInterpolation())) {
                return false;
            }
        }
        {
            BigInteger lhsNumArc;
            lhsNumArc = this.getNumArc();
            BigInteger rhsNumArc;
            rhsNumArc = that.getNumArc();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "numArc", lhsNumArc), LocatorUtils.property(thatLocator, "numArc", rhsNumArc), lhsNumArc, rhsNumArc, this.isSetNumArc(), that.isSetNumArc())) {
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
            DirectPositionType thePos;
            thePos = this.getPos();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pos", thePos), currentHashCode, thePos, this.isSetPos());
        }
        {
            PointPropertyType thePointProperty;
            thePointProperty = this.getPointProperty();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pointProperty", thePointProperty), currentHashCode, thePointProperty, this.isSetPointProperty());
        }
        {
            DirectPositionListType thePosList;
            thePosList = this.getPosList();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "posList", thePosList), currentHashCode, thePosList, this.isSetPosList());
        }
        {
            LengthType theRadius;
            theRadius = this.getRadius();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "radius", theRadius), currentHashCode, theRadius, this.isSetRadius());
        }
        {
            AngleType theStartAngle;
            theStartAngle = this.getStartAngle();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "startAngle", theStartAngle), currentHashCode, theStartAngle, this.isSetStartAngle());
        }
        {
            AngleType theEndAngle;
            theEndAngle = this.getEndAngle();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "endAngle", theEndAngle), currentHashCode, theEndAngle, this.isSetEndAngle());
        }
        {
            CurveInterpolationType theInterpolation;
            theInterpolation = this.getInterpolation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interpolation", theInterpolation), currentHashCode, theInterpolation, this.isSetInterpolation());
        }
        {
            BigInteger theNumArc;
            theNumArc = this.getNumArc();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "numArc", theNumArc), currentHashCode, theNumArc, this.isSetNumArc());
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
        if (draftCopy instanceof SimpleArcByCenterPointType) {
            final SimpleArcByCenterPointType copy = ((SimpleArcByCenterPointType) draftCopy);
            {
                Boolean posShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPos());
                if (posShouldBeCopiedAndSet == Boolean.TRUE) {
                    DirectPositionType sourcePos;
                    sourcePos = this.getPos();
                    DirectPositionType copyPos = ((DirectPositionType) strategy.copy(LocatorUtils.property(locator, "pos", sourcePos), sourcePos, this.isSetPos()));
                    copy.setPos(copyPos);
                } else {
                    if (posShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.pos = null;
                    }
                }
            }
            {
                Boolean pointPropertyShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPointProperty());
                if (pointPropertyShouldBeCopiedAndSet == Boolean.TRUE) {
                    PointPropertyType sourcePointProperty;
                    sourcePointProperty = this.getPointProperty();
                    PointPropertyType copyPointProperty = ((PointPropertyType) strategy.copy(LocatorUtils.property(locator, "pointProperty", sourcePointProperty), sourcePointProperty, this.isSetPointProperty()));
                    copy.setPointProperty(copyPointProperty);
                } else {
                    if (pointPropertyShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.pointProperty = null;
                    }
                }
            }
            {
                Boolean posListShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPosList());
                if (posListShouldBeCopiedAndSet == Boolean.TRUE) {
                    DirectPositionListType sourcePosList;
                    sourcePosList = this.getPosList();
                    DirectPositionListType copyPosList = ((DirectPositionListType) strategy.copy(LocatorUtils.property(locator, "posList", sourcePosList), sourcePosList, this.isSetPosList()));
                    copy.setPosList(copyPosList);
                } else {
                    if (posListShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.posList = null;
                    }
                }
            }
            {
                Boolean radiusShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRadius());
                if (radiusShouldBeCopiedAndSet == Boolean.TRUE) {
                    LengthType sourceRadius;
                    sourceRadius = this.getRadius();
                    LengthType copyRadius = ((LengthType) strategy.copy(LocatorUtils.property(locator, "radius", sourceRadius), sourceRadius, this.isSetRadius()));
                    copy.setRadius(copyRadius);
                } else {
                    if (radiusShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.radius = null;
                    }
                }
            }
            {
                Boolean startAngleShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetStartAngle());
                if (startAngleShouldBeCopiedAndSet == Boolean.TRUE) {
                    AngleType sourceStartAngle;
                    sourceStartAngle = this.getStartAngle();
                    AngleType copyStartAngle = ((AngleType) strategy.copy(LocatorUtils.property(locator, "startAngle", sourceStartAngle), sourceStartAngle, this.isSetStartAngle()));
                    copy.setStartAngle(copyStartAngle);
                } else {
                    if (startAngleShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.startAngle = null;
                    }
                }
            }
            {
                Boolean endAngleShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetEndAngle());
                if (endAngleShouldBeCopiedAndSet == Boolean.TRUE) {
                    AngleType sourceEndAngle;
                    sourceEndAngle = this.getEndAngle();
                    AngleType copyEndAngle = ((AngleType) strategy.copy(LocatorUtils.property(locator, "endAngle", sourceEndAngle), sourceEndAngle, this.isSetEndAngle()));
                    copy.setEndAngle(copyEndAngle);
                } else {
                    if (endAngleShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.endAngle = null;
                    }
                }
            }
            {
                Boolean interpolationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetInterpolation());
                if (interpolationShouldBeCopiedAndSet == Boolean.TRUE) {
                    CurveInterpolationType sourceInterpolation;
                    sourceInterpolation = this.getInterpolation();
                    CurveInterpolationType copyInterpolation = ((CurveInterpolationType) strategy.copy(LocatorUtils.property(locator, "interpolation", sourceInterpolation), sourceInterpolation, this.isSetInterpolation()));
                    copy.setInterpolation(copyInterpolation);
                } else {
                    if (interpolationShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.interpolation = null;
                    }
                }
            }
            {
                Boolean numArcShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetNumArc());
                if (numArcShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigInteger sourceNumArc;
                    sourceNumArc = this.getNumArc();
                    BigInteger copyNumArc = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "numArc", sourceNumArc), sourceNumArc, this.isSetNumArc()));
                    copy.setNumArc(copyNumArc);
                } else {
                    if (numArcShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.numArc = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new SimpleArcByCenterPointType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof SimpleArcByCenterPointType) {
            final SimpleArcByCenterPointType target = this;
            final SimpleArcByCenterPointType leftObject = ((SimpleArcByCenterPointType) left);
            final SimpleArcByCenterPointType rightObject = ((SimpleArcByCenterPointType) right);
            {
                Boolean posShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPos(), rightObject.isSetPos());
                if (posShouldBeMergedAndSet == Boolean.TRUE) {
                    DirectPositionType lhsPos;
                    lhsPos = leftObject.getPos();
                    DirectPositionType rhsPos;
                    rhsPos = rightObject.getPos();
                    DirectPositionType mergedPos = ((DirectPositionType) strategy.merge(LocatorUtils.property(leftLocator, "pos", lhsPos), LocatorUtils.property(rightLocator, "pos", rhsPos), lhsPos, rhsPos, leftObject.isSetPos(), rightObject.isSetPos()));
                    target.setPos(mergedPos);
                } else {
                    if (posShouldBeMergedAndSet == Boolean.FALSE) {
                        target.pos = null;
                    }
                }
            }
            {
                Boolean pointPropertyShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPointProperty(), rightObject.isSetPointProperty());
                if (pointPropertyShouldBeMergedAndSet == Boolean.TRUE) {
                    PointPropertyType lhsPointProperty;
                    lhsPointProperty = leftObject.getPointProperty();
                    PointPropertyType rhsPointProperty;
                    rhsPointProperty = rightObject.getPointProperty();
                    PointPropertyType mergedPointProperty = ((PointPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "pointProperty", lhsPointProperty), LocatorUtils.property(rightLocator, "pointProperty", rhsPointProperty), lhsPointProperty, rhsPointProperty, leftObject.isSetPointProperty(), rightObject.isSetPointProperty()));
                    target.setPointProperty(mergedPointProperty);
                } else {
                    if (pointPropertyShouldBeMergedAndSet == Boolean.FALSE) {
                        target.pointProperty = null;
                    }
                }
            }
            {
                Boolean posListShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPosList(), rightObject.isSetPosList());
                if (posListShouldBeMergedAndSet == Boolean.TRUE) {
                    DirectPositionListType lhsPosList;
                    lhsPosList = leftObject.getPosList();
                    DirectPositionListType rhsPosList;
                    rhsPosList = rightObject.getPosList();
                    DirectPositionListType mergedPosList = ((DirectPositionListType) strategy.merge(LocatorUtils.property(leftLocator, "posList", lhsPosList), LocatorUtils.property(rightLocator, "posList", rhsPosList), lhsPosList, rhsPosList, leftObject.isSetPosList(), rightObject.isSetPosList()));
                    target.setPosList(mergedPosList);
                } else {
                    if (posListShouldBeMergedAndSet == Boolean.FALSE) {
                        target.posList = null;
                    }
                }
            }
            {
                Boolean radiusShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRadius(), rightObject.isSetRadius());
                if (radiusShouldBeMergedAndSet == Boolean.TRUE) {
                    LengthType lhsRadius;
                    lhsRadius = leftObject.getRadius();
                    LengthType rhsRadius;
                    rhsRadius = rightObject.getRadius();
                    LengthType mergedRadius = ((LengthType) strategy.merge(LocatorUtils.property(leftLocator, "radius", lhsRadius), LocatorUtils.property(rightLocator, "radius", rhsRadius), lhsRadius, rhsRadius, leftObject.isSetRadius(), rightObject.isSetRadius()));
                    target.setRadius(mergedRadius);
                } else {
                    if (radiusShouldBeMergedAndSet == Boolean.FALSE) {
                        target.radius = null;
                    }
                }
            }
            {
                Boolean startAngleShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetStartAngle(), rightObject.isSetStartAngle());
                if (startAngleShouldBeMergedAndSet == Boolean.TRUE) {
                    AngleType lhsStartAngle;
                    lhsStartAngle = leftObject.getStartAngle();
                    AngleType rhsStartAngle;
                    rhsStartAngle = rightObject.getStartAngle();
                    AngleType mergedStartAngle = ((AngleType) strategy.merge(LocatorUtils.property(leftLocator, "startAngle", lhsStartAngle), LocatorUtils.property(rightLocator, "startAngle", rhsStartAngle), lhsStartAngle, rhsStartAngle, leftObject.isSetStartAngle(), rightObject.isSetStartAngle()));
                    target.setStartAngle(mergedStartAngle);
                } else {
                    if (startAngleShouldBeMergedAndSet == Boolean.FALSE) {
                        target.startAngle = null;
                    }
                }
            }
            {
                Boolean endAngleShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetEndAngle(), rightObject.isSetEndAngle());
                if (endAngleShouldBeMergedAndSet == Boolean.TRUE) {
                    AngleType lhsEndAngle;
                    lhsEndAngle = leftObject.getEndAngle();
                    AngleType rhsEndAngle;
                    rhsEndAngle = rightObject.getEndAngle();
                    AngleType mergedEndAngle = ((AngleType) strategy.merge(LocatorUtils.property(leftLocator, "endAngle", lhsEndAngle), LocatorUtils.property(rightLocator, "endAngle", rhsEndAngle), lhsEndAngle, rhsEndAngle, leftObject.isSetEndAngle(), rightObject.isSetEndAngle()));
                    target.setEndAngle(mergedEndAngle);
                } else {
                    if (endAngleShouldBeMergedAndSet == Boolean.FALSE) {
                        target.endAngle = null;
                    }
                }
            }
            {
                Boolean interpolationShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetInterpolation(), rightObject.isSetInterpolation());
                if (interpolationShouldBeMergedAndSet == Boolean.TRUE) {
                    CurveInterpolationType lhsInterpolation;
                    lhsInterpolation = leftObject.getInterpolation();
                    CurveInterpolationType rhsInterpolation;
                    rhsInterpolation = rightObject.getInterpolation();
                    CurveInterpolationType mergedInterpolation = ((CurveInterpolationType) strategy.merge(LocatorUtils.property(leftLocator, "interpolation", lhsInterpolation), LocatorUtils.property(rightLocator, "interpolation", rhsInterpolation), lhsInterpolation, rhsInterpolation, leftObject.isSetInterpolation(), rightObject.isSetInterpolation()));
                    target.setInterpolation(mergedInterpolation);
                } else {
                    if (interpolationShouldBeMergedAndSet == Boolean.FALSE) {
                        target.interpolation = null;
                    }
                }
            }
            {
                Boolean numArcShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetNumArc(), rightObject.isSetNumArc());
                if (numArcShouldBeMergedAndSet == Boolean.TRUE) {
                    BigInteger lhsNumArc;
                    lhsNumArc = leftObject.getNumArc();
                    BigInteger rhsNumArc;
                    rhsNumArc = rightObject.getNumArc();
                    BigInteger mergedNumArc = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "numArc", lhsNumArc), LocatorUtils.property(rightLocator, "numArc", rhsNumArc), lhsNumArc, rhsNumArc, leftObject.isSetNumArc(), rightObject.isSetNumArc()));
                    target.setNumArc(mergedNumArc);
                } else {
                    if (numArcShouldBeMergedAndSet == Boolean.FALSE) {
                        target.numArc = null;
                    }
                }
            }
        }
    }

}
