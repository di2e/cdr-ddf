//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package org.isotc211._2005.gmd;

import java.io.Serializable;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.isotc211._2005.gco.AbstractObjectType;
import org.isotc211._2005.gco.CharacterStringPropertyType;
import org.isotc211._2005.gco.DateTimePropertyType;
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
 * Common ways in which the dataset may be obtained or received, and related instructions and fee information
 * 
 * <p>Java class for MD_StandardOrderProcess_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MD_StandardOrderProcess_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fees" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="plannedAvailableDateTime" type="{http://www.isotc211.org/2005/gco}DateTime_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="orderingInstructions" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="turnaround" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_StandardOrderProcess_Type", propOrder = {
    "fees",
    "plannedAvailableDateTime",
    "orderingInstructions",
    "turnaround"
})
public class MDStandardOrderProcessType
    extends AbstractObjectType
    implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @Valid
    protected CharacterStringPropertyType fees;
    @Valid
    protected DateTimePropertyType plannedAvailableDateTime;
    @Valid
    protected CharacterStringPropertyType orderingInstructions;
    @Valid
    protected CharacterStringPropertyType turnaround;

    /**
     * Gets the value of the fees property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getFees() {
        return fees;
    }

    /**
     * Sets the value of the fees property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setFees(CharacterStringPropertyType value) {
        this.fees = value;
    }

    public boolean isSetFees() {
        return (this.fees!= null);
    }

    /**
     * Gets the value of the plannedAvailableDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link DateTimePropertyType }
     *     
     */
    public DateTimePropertyType getPlannedAvailableDateTime() {
        return plannedAvailableDateTime;
    }

    /**
     * Sets the value of the plannedAvailableDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimePropertyType }
     *     
     */
    public void setPlannedAvailableDateTime(DateTimePropertyType value) {
        this.plannedAvailableDateTime = value;
    }

    public boolean isSetPlannedAvailableDateTime() {
        return (this.plannedAvailableDateTime!= null);
    }

    /**
     * Gets the value of the orderingInstructions property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getOrderingInstructions() {
        return orderingInstructions;
    }

    /**
     * Sets the value of the orderingInstructions property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setOrderingInstructions(CharacterStringPropertyType value) {
        this.orderingInstructions = value;
    }

    public boolean isSetOrderingInstructions() {
        return (this.orderingInstructions!= null);
    }

    /**
     * Gets the value of the turnaround property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getTurnaround() {
        return turnaround;
    }

    /**
     * Sets the value of the turnaround property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setTurnaround(CharacterStringPropertyType value) {
        this.turnaround = value;
    }

    public boolean isSetTurnaround() {
        return (this.turnaround!= null);
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
            CharacterStringPropertyType theFees;
            theFees = this.getFees();
            strategy.appendField(locator, this, "fees", buffer, theFees, this.isSetFees());
        }
        {
            DateTimePropertyType thePlannedAvailableDateTime;
            thePlannedAvailableDateTime = this.getPlannedAvailableDateTime();
            strategy.appendField(locator, this, "plannedAvailableDateTime", buffer, thePlannedAvailableDateTime, this.isSetPlannedAvailableDateTime());
        }
        {
            CharacterStringPropertyType theOrderingInstructions;
            theOrderingInstructions = this.getOrderingInstructions();
            strategy.appendField(locator, this, "orderingInstructions", buffer, theOrderingInstructions, this.isSetOrderingInstructions());
        }
        {
            CharacterStringPropertyType theTurnaround;
            theTurnaround = this.getTurnaround();
            strategy.appendField(locator, this, "turnaround", buffer, theTurnaround, this.isSetTurnaround());
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
        final MDStandardOrderProcessType that = ((MDStandardOrderProcessType) object);
        {
            CharacterStringPropertyType lhsFees;
            lhsFees = this.getFees();
            CharacterStringPropertyType rhsFees;
            rhsFees = that.getFees();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fees", lhsFees), LocatorUtils.property(thatLocator, "fees", rhsFees), lhsFees, rhsFees, this.isSetFees(), that.isSetFees())) {
                return false;
            }
        }
        {
            DateTimePropertyType lhsPlannedAvailableDateTime;
            lhsPlannedAvailableDateTime = this.getPlannedAvailableDateTime();
            DateTimePropertyType rhsPlannedAvailableDateTime;
            rhsPlannedAvailableDateTime = that.getPlannedAvailableDateTime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "plannedAvailableDateTime", lhsPlannedAvailableDateTime), LocatorUtils.property(thatLocator, "plannedAvailableDateTime", rhsPlannedAvailableDateTime), lhsPlannedAvailableDateTime, rhsPlannedAvailableDateTime, this.isSetPlannedAvailableDateTime(), that.isSetPlannedAvailableDateTime())) {
                return false;
            }
        }
        {
            CharacterStringPropertyType lhsOrderingInstructions;
            lhsOrderingInstructions = this.getOrderingInstructions();
            CharacterStringPropertyType rhsOrderingInstructions;
            rhsOrderingInstructions = that.getOrderingInstructions();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "orderingInstructions", lhsOrderingInstructions), LocatorUtils.property(thatLocator, "orderingInstructions", rhsOrderingInstructions), lhsOrderingInstructions, rhsOrderingInstructions, this.isSetOrderingInstructions(), that.isSetOrderingInstructions())) {
                return false;
            }
        }
        {
            CharacterStringPropertyType lhsTurnaround;
            lhsTurnaround = this.getTurnaround();
            CharacterStringPropertyType rhsTurnaround;
            rhsTurnaround = that.getTurnaround();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "turnaround", lhsTurnaround), LocatorUtils.property(thatLocator, "turnaround", rhsTurnaround), lhsTurnaround, rhsTurnaround, this.isSetTurnaround(), that.isSetTurnaround())) {
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
            CharacterStringPropertyType theFees;
            theFees = this.getFees();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "fees", theFees), currentHashCode, theFees, this.isSetFees());
        }
        {
            DateTimePropertyType thePlannedAvailableDateTime;
            thePlannedAvailableDateTime = this.getPlannedAvailableDateTime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "plannedAvailableDateTime", thePlannedAvailableDateTime), currentHashCode, thePlannedAvailableDateTime, this.isSetPlannedAvailableDateTime());
        }
        {
            CharacterStringPropertyType theOrderingInstructions;
            theOrderingInstructions = this.getOrderingInstructions();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "orderingInstructions", theOrderingInstructions), currentHashCode, theOrderingInstructions, this.isSetOrderingInstructions());
        }
        {
            CharacterStringPropertyType theTurnaround;
            theTurnaround = this.getTurnaround();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "turnaround", theTurnaround), currentHashCode, theTurnaround, this.isSetTurnaround());
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
        if (draftCopy instanceof MDStandardOrderProcessType) {
            final MDStandardOrderProcessType copy = ((MDStandardOrderProcessType) draftCopy);
            {
                Boolean feesShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetFees());
                if (feesShouldBeCopiedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType sourceFees;
                    sourceFees = this.getFees();
                    CharacterStringPropertyType copyFees = ((CharacterStringPropertyType) strategy.copy(LocatorUtils.property(locator, "fees", sourceFees), sourceFees, this.isSetFees()));
                    copy.setFees(copyFees);
                } else {
                    if (feesShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.fees = null;
                    }
                }
            }
            {
                Boolean plannedAvailableDateTimeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPlannedAvailableDateTime());
                if (plannedAvailableDateTimeShouldBeCopiedAndSet == Boolean.TRUE) {
                    DateTimePropertyType sourcePlannedAvailableDateTime;
                    sourcePlannedAvailableDateTime = this.getPlannedAvailableDateTime();
                    DateTimePropertyType copyPlannedAvailableDateTime = ((DateTimePropertyType) strategy.copy(LocatorUtils.property(locator, "plannedAvailableDateTime", sourcePlannedAvailableDateTime), sourcePlannedAvailableDateTime, this.isSetPlannedAvailableDateTime()));
                    copy.setPlannedAvailableDateTime(copyPlannedAvailableDateTime);
                } else {
                    if (plannedAvailableDateTimeShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.plannedAvailableDateTime = null;
                    }
                }
            }
            {
                Boolean orderingInstructionsShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetOrderingInstructions());
                if (orderingInstructionsShouldBeCopiedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType sourceOrderingInstructions;
                    sourceOrderingInstructions = this.getOrderingInstructions();
                    CharacterStringPropertyType copyOrderingInstructions = ((CharacterStringPropertyType) strategy.copy(LocatorUtils.property(locator, "orderingInstructions", sourceOrderingInstructions), sourceOrderingInstructions, this.isSetOrderingInstructions()));
                    copy.setOrderingInstructions(copyOrderingInstructions);
                } else {
                    if (orderingInstructionsShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.orderingInstructions = null;
                    }
                }
            }
            {
                Boolean turnaroundShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetTurnaround());
                if (turnaroundShouldBeCopiedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType sourceTurnaround;
                    sourceTurnaround = this.getTurnaround();
                    CharacterStringPropertyType copyTurnaround = ((CharacterStringPropertyType) strategy.copy(LocatorUtils.property(locator, "turnaround", sourceTurnaround), sourceTurnaround, this.isSetTurnaround()));
                    copy.setTurnaround(copyTurnaround);
                } else {
                    if (turnaroundShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.turnaround = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new MDStandardOrderProcessType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof MDStandardOrderProcessType) {
            final MDStandardOrderProcessType target = this;
            final MDStandardOrderProcessType leftObject = ((MDStandardOrderProcessType) left);
            final MDStandardOrderProcessType rightObject = ((MDStandardOrderProcessType) right);
            {
                Boolean feesShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetFees(), rightObject.isSetFees());
                if (feesShouldBeMergedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType lhsFees;
                    lhsFees = leftObject.getFees();
                    CharacterStringPropertyType rhsFees;
                    rhsFees = rightObject.getFees();
                    CharacterStringPropertyType mergedFees = ((CharacterStringPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "fees", lhsFees), LocatorUtils.property(rightLocator, "fees", rhsFees), lhsFees, rhsFees, leftObject.isSetFees(), rightObject.isSetFees()));
                    target.setFees(mergedFees);
                } else {
                    if (feesShouldBeMergedAndSet == Boolean.FALSE) {
                        target.fees = null;
                    }
                }
            }
            {
                Boolean plannedAvailableDateTimeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPlannedAvailableDateTime(), rightObject.isSetPlannedAvailableDateTime());
                if (plannedAvailableDateTimeShouldBeMergedAndSet == Boolean.TRUE) {
                    DateTimePropertyType lhsPlannedAvailableDateTime;
                    lhsPlannedAvailableDateTime = leftObject.getPlannedAvailableDateTime();
                    DateTimePropertyType rhsPlannedAvailableDateTime;
                    rhsPlannedAvailableDateTime = rightObject.getPlannedAvailableDateTime();
                    DateTimePropertyType mergedPlannedAvailableDateTime = ((DateTimePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "plannedAvailableDateTime", lhsPlannedAvailableDateTime), LocatorUtils.property(rightLocator, "plannedAvailableDateTime", rhsPlannedAvailableDateTime), lhsPlannedAvailableDateTime, rhsPlannedAvailableDateTime, leftObject.isSetPlannedAvailableDateTime(), rightObject.isSetPlannedAvailableDateTime()));
                    target.setPlannedAvailableDateTime(mergedPlannedAvailableDateTime);
                } else {
                    if (plannedAvailableDateTimeShouldBeMergedAndSet == Boolean.FALSE) {
                        target.plannedAvailableDateTime = null;
                    }
                }
            }
            {
                Boolean orderingInstructionsShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetOrderingInstructions(), rightObject.isSetOrderingInstructions());
                if (orderingInstructionsShouldBeMergedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType lhsOrderingInstructions;
                    lhsOrderingInstructions = leftObject.getOrderingInstructions();
                    CharacterStringPropertyType rhsOrderingInstructions;
                    rhsOrderingInstructions = rightObject.getOrderingInstructions();
                    CharacterStringPropertyType mergedOrderingInstructions = ((CharacterStringPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "orderingInstructions", lhsOrderingInstructions), LocatorUtils.property(rightLocator, "orderingInstructions", rhsOrderingInstructions), lhsOrderingInstructions, rhsOrderingInstructions, leftObject.isSetOrderingInstructions(), rightObject.isSetOrderingInstructions()));
                    target.setOrderingInstructions(mergedOrderingInstructions);
                } else {
                    if (orderingInstructionsShouldBeMergedAndSet == Boolean.FALSE) {
                        target.orderingInstructions = null;
                    }
                }
            }
            {
                Boolean turnaroundShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetTurnaround(), rightObject.isSetTurnaround());
                if (turnaroundShouldBeMergedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType lhsTurnaround;
                    lhsTurnaround = leftObject.getTurnaround();
                    CharacterStringPropertyType rhsTurnaround;
                    rhsTurnaround = rightObject.getTurnaround();
                    CharacterStringPropertyType mergedTurnaround = ((CharacterStringPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "turnaround", lhsTurnaround), LocatorUtils.property(rightLocator, "turnaround", rhsTurnaround), lhsTurnaround, rhsTurnaround, leftObject.isSetTurnaround(), rightObject.isSetTurnaround()));
                    target.setTurnaround(mergedTurnaround);
                } else {
                    if (turnaroundShouldBeMergedAndSet == Boolean.FALSE) {
                        target.turnaround = null;
                    }
                }
            }
        }
    }

}
