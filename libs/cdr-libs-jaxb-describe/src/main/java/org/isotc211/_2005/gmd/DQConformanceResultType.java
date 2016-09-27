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
import org.isotc211._2005.gco.BooleanPropertyType;
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
 * quantitative_result from Quality Procedures -  - renamed to remove implied use limitiation.
 * 
 * <p>Java class for DQ_ConformanceResult_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DQ_ConformanceResult_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.isotc211.org/2005/gmd}AbstractDQ_Result_Type"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="specification" type="{http://www.isotc211.org/2005/gmd}CI_Citation_PropertyType"/&gt;
 *         &lt;element name="explanation" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/&gt;
 *         &lt;element name="pass" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DQ_ConformanceResult_Type", propOrder = {
    "specification",
    "explanation",
    "pass"
})
public class DQConformanceResultType
    extends AbstractDQResultType
    implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    @NotNull(message = "DQConformanceResultType.specification {javax.validation.constraints.NotNull.message}")
    @Valid
    protected CICitationPropertyType specification;
    @XmlElement(required = true)
    @NotNull(message = "DQConformanceResultType.explanation {javax.validation.constraints.NotNull.message}")
    @Valid
    protected CharacterStringPropertyType explanation;
    @XmlElement(required = true)
    @NotNull(message = "DQConformanceResultType.pass {javax.validation.constraints.NotNull.message}")
    @Valid
    protected BooleanPropertyType pass;

    /**
     * Gets the value of the specification property.
     * 
     * @return
     *     possible object is
     *     {@link CICitationPropertyType }
     *     
     */
    public CICitationPropertyType getSpecification() {
        return specification;
    }

    /**
     * Sets the value of the specification property.
     * 
     * @param value
     *     allowed object is
     *     {@link CICitationPropertyType }
     *     
     */
    public void setSpecification(CICitationPropertyType value) {
        this.specification = value;
    }

    public boolean isSetSpecification() {
        return (this.specification!= null);
    }

    /**
     * Gets the value of the explanation property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getExplanation() {
        return explanation;
    }

    /**
     * Sets the value of the explanation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setExplanation(CharacterStringPropertyType value) {
        this.explanation = value;
    }

    public boolean isSetExplanation() {
        return (this.explanation!= null);
    }

    /**
     * Gets the value of the pass property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getPass() {
        return pass;
    }

    /**
     * Sets the value of the pass property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setPass(BooleanPropertyType value) {
        this.pass = value;
    }

    public boolean isSetPass() {
        return (this.pass!= null);
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
            CICitationPropertyType theSpecification;
            theSpecification = this.getSpecification();
            strategy.appendField(locator, this, "specification", buffer, theSpecification, this.isSetSpecification());
        }
        {
            CharacterStringPropertyType theExplanation;
            theExplanation = this.getExplanation();
            strategy.appendField(locator, this, "explanation", buffer, theExplanation, this.isSetExplanation());
        }
        {
            BooleanPropertyType thePass;
            thePass = this.getPass();
            strategy.appendField(locator, this, "pass", buffer, thePass, this.isSetPass());
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
        final DQConformanceResultType that = ((DQConformanceResultType) object);
        {
            CICitationPropertyType lhsSpecification;
            lhsSpecification = this.getSpecification();
            CICitationPropertyType rhsSpecification;
            rhsSpecification = that.getSpecification();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "specification", lhsSpecification), LocatorUtils.property(thatLocator, "specification", rhsSpecification), lhsSpecification, rhsSpecification, this.isSetSpecification(), that.isSetSpecification())) {
                return false;
            }
        }
        {
            CharacterStringPropertyType lhsExplanation;
            lhsExplanation = this.getExplanation();
            CharacterStringPropertyType rhsExplanation;
            rhsExplanation = that.getExplanation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "explanation", lhsExplanation), LocatorUtils.property(thatLocator, "explanation", rhsExplanation), lhsExplanation, rhsExplanation, this.isSetExplanation(), that.isSetExplanation())) {
                return false;
            }
        }
        {
            BooleanPropertyType lhsPass;
            lhsPass = this.getPass();
            BooleanPropertyType rhsPass;
            rhsPass = that.getPass();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pass", lhsPass), LocatorUtils.property(thatLocator, "pass", rhsPass), lhsPass, rhsPass, this.isSetPass(), that.isSetPass())) {
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
            CICitationPropertyType theSpecification;
            theSpecification = this.getSpecification();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "specification", theSpecification), currentHashCode, theSpecification, this.isSetSpecification());
        }
        {
            CharacterStringPropertyType theExplanation;
            theExplanation = this.getExplanation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "explanation", theExplanation), currentHashCode, theExplanation, this.isSetExplanation());
        }
        {
            BooleanPropertyType thePass;
            thePass = this.getPass();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pass", thePass), currentHashCode, thePass, this.isSetPass());
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
        if (draftCopy instanceof DQConformanceResultType) {
            final DQConformanceResultType copy = ((DQConformanceResultType) draftCopy);
            {
                Boolean specificationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSpecification());
                if (specificationShouldBeCopiedAndSet == Boolean.TRUE) {
                    CICitationPropertyType sourceSpecification;
                    sourceSpecification = this.getSpecification();
                    CICitationPropertyType copySpecification = ((CICitationPropertyType) strategy.copy(LocatorUtils.property(locator, "specification", sourceSpecification), sourceSpecification, this.isSetSpecification()));
                    copy.setSpecification(copySpecification);
                } else {
                    if (specificationShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.specification = null;
                    }
                }
            }
            {
                Boolean explanationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetExplanation());
                if (explanationShouldBeCopiedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType sourceExplanation;
                    sourceExplanation = this.getExplanation();
                    CharacterStringPropertyType copyExplanation = ((CharacterStringPropertyType) strategy.copy(LocatorUtils.property(locator, "explanation", sourceExplanation), sourceExplanation, this.isSetExplanation()));
                    copy.setExplanation(copyExplanation);
                } else {
                    if (explanationShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.explanation = null;
                    }
                }
            }
            {
                Boolean passShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPass());
                if (passShouldBeCopiedAndSet == Boolean.TRUE) {
                    BooleanPropertyType sourcePass;
                    sourcePass = this.getPass();
                    BooleanPropertyType copyPass = ((BooleanPropertyType) strategy.copy(LocatorUtils.property(locator, "pass", sourcePass), sourcePass, this.isSetPass()));
                    copy.setPass(copyPass);
                } else {
                    if (passShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.pass = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new DQConformanceResultType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof DQConformanceResultType) {
            final DQConformanceResultType target = this;
            final DQConformanceResultType leftObject = ((DQConformanceResultType) left);
            final DQConformanceResultType rightObject = ((DQConformanceResultType) right);
            {
                Boolean specificationShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSpecification(), rightObject.isSetSpecification());
                if (specificationShouldBeMergedAndSet == Boolean.TRUE) {
                    CICitationPropertyType lhsSpecification;
                    lhsSpecification = leftObject.getSpecification();
                    CICitationPropertyType rhsSpecification;
                    rhsSpecification = rightObject.getSpecification();
                    CICitationPropertyType mergedSpecification = ((CICitationPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "specification", lhsSpecification), LocatorUtils.property(rightLocator, "specification", rhsSpecification), lhsSpecification, rhsSpecification, leftObject.isSetSpecification(), rightObject.isSetSpecification()));
                    target.setSpecification(mergedSpecification);
                } else {
                    if (specificationShouldBeMergedAndSet == Boolean.FALSE) {
                        target.specification = null;
                    }
                }
            }
            {
                Boolean explanationShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetExplanation(), rightObject.isSetExplanation());
                if (explanationShouldBeMergedAndSet == Boolean.TRUE) {
                    CharacterStringPropertyType lhsExplanation;
                    lhsExplanation = leftObject.getExplanation();
                    CharacterStringPropertyType rhsExplanation;
                    rhsExplanation = rightObject.getExplanation();
                    CharacterStringPropertyType mergedExplanation = ((CharacterStringPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "explanation", lhsExplanation), LocatorUtils.property(rightLocator, "explanation", rhsExplanation), lhsExplanation, rhsExplanation, leftObject.isSetExplanation(), rightObject.isSetExplanation()));
                    target.setExplanation(mergedExplanation);
                } else {
                    if (explanationShouldBeMergedAndSet == Boolean.FALSE) {
                        target.explanation = null;
                    }
                }
            }
            {
                Boolean passShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPass(), rightObject.isSetPass());
                if (passShouldBeMergedAndSet == Boolean.TRUE) {
                    BooleanPropertyType lhsPass;
                    lhsPass = leftObject.getPass();
                    BooleanPropertyType rhsPass;
                    rhsPass = rightObject.getPass();
                    BooleanPropertyType mergedPass = ((BooleanPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "pass", lhsPass), LocatorUtils.property(rightLocator, "pass", rhsPass), lhsPass, rhsPass, leftObject.isSetPass(), rightObject.isSetPass()));
                    target.setPass(mergedPass);
                } else {
                    if (passShouldBeMergedAndSet == Boolean.FALSE) {
                        target.pass = null;
                    }
                }
            }
        }
    }

}
