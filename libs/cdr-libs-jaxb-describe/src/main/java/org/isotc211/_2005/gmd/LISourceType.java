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
 * <p>Java class for LI_Source_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LI_Source_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="description" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="scaleDenominator" type="{http://www.isotc211.org/2005/gmd}MD_RepresentativeFraction_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="sourceReferenceSystem" type="{http://www.isotc211.org/2005/gmd}MD_ReferenceSystem_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="sourceCitation" type="{http://www.isotc211.org/2005/gmd}CI_Citation_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="sourceExtent" type="{http://www.isotc211.org/2005/gmd}EX_Extent_PropertyType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="sourceStep" type="{http://www.isotc211.org/2005/gmd}LI_ProcessStep_PropertyType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LI_Source_Type", propOrder = {
    "description",
    "scaleDenominator",
    "sourceReferenceSystem",
    "sourceCitation",
    "sourceExtent",
    "sourceStep"
})
public class LISourceType
    extends AbstractObjectType
    implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @Valid
    protected CharacterStringPropertyType description;
    @Valid
    protected MDRepresentativeFractionPropertyType scaleDenominator;
    @Valid
    protected MDReferenceSystemPropertyType sourceReferenceSystem;
    @Valid
    protected CICitationPropertyType sourceCitation;
    @Valid
    protected List<EXExtentPropertyType> sourceExtent;
    @Valid
    protected List<LIProcessStepPropertyType> sourceStep;

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
     * Gets the value of the scaleDenominator property.
     * 
     * @return
     *     possible object is
     *     {@link MDRepresentativeFractionPropertyType }
     *     
     */
    public MDRepresentativeFractionPropertyType getScaleDenominator() {
        return scaleDenominator;
    }

    /**
     * Sets the value of the scaleDenominator property.
     * 
     * @param value
     *     allowed object is
     *     {@link MDRepresentativeFractionPropertyType }
     *     
     */
    public void setScaleDenominator(MDRepresentativeFractionPropertyType value) {
        this.scaleDenominator = value;
    }

    public boolean isSetScaleDenominator() {
        return (this.scaleDenominator!= null);
    }

    /**
     * Gets the value of the sourceReferenceSystem property.
     * 
     * @return
     *     possible object is
     *     {@link MDReferenceSystemPropertyType }
     *     
     */
    public MDReferenceSystemPropertyType getSourceReferenceSystem() {
        return sourceReferenceSystem;
    }

    /**
     * Sets the value of the sourceReferenceSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link MDReferenceSystemPropertyType }
     *     
     */
    public void setSourceReferenceSystem(MDReferenceSystemPropertyType value) {
        this.sourceReferenceSystem = value;
    }

    public boolean isSetSourceReferenceSystem() {
        return (this.sourceReferenceSystem!= null);
    }

    /**
     * Gets the value of the sourceCitation property.
     * 
     * @return
     *     possible object is
     *     {@link CICitationPropertyType }
     *     
     */
    public CICitationPropertyType getSourceCitation() {
        return sourceCitation;
    }

    /**
     * Sets the value of the sourceCitation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CICitationPropertyType }
     *     
     */
    public void setSourceCitation(CICitationPropertyType value) {
        this.sourceCitation = value;
    }

    public boolean isSetSourceCitation() {
        return (this.sourceCitation!= null);
    }

    /**
     * Gets the value of the sourceExtent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sourceExtent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSourceExtent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EXExtentPropertyType }
     * 
     * 
     */
    public List<EXExtentPropertyType> getSourceExtent() {
        if (sourceExtent == null) {
            sourceExtent = new ArrayList<EXExtentPropertyType>();
        }
        return this.sourceExtent;
    }

    public boolean isSetSourceExtent() {
        return ((this.sourceExtent!= null)&&(!this.sourceExtent.isEmpty()));
    }

    public void unsetSourceExtent() {
        this.sourceExtent = null;
    }

    /**
     * Gets the value of the sourceStep property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sourceStep property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSourceStep().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LIProcessStepPropertyType }
     * 
     * 
     */
    public List<LIProcessStepPropertyType> getSourceStep() {
        if (sourceStep == null) {
            sourceStep = new ArrayList<LIProcessStepPropertyType>();
        }
        return this.sourceStep;
    }

    public boolean isSetSourceStep() {
        return ((this.sourceStep!= null)&&(!this.sourceStep.isEmpty()));
    }

    public void unsetSourceStep() {
        this.sourceStep = null;
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
            MDRepresentativeFractionPropertyType theScaleDenominator;
            theScaleDenominator = this.getScaleDenominator();
            strategy.appendField(locator, this, "scaleDenominator", buffer, theScaleDenominator, this.isSetScaleDenominator());
        }
        {
            MDReferenceSystemPropertyType theSourceReferenceSystem;
            theSourceReferenceSystem = this.getSourceReferenceSystem();
            strategy.appendField(locator, this, "sourceReferenceSystem", buffer, theSourceReferenceSystem, this.isSetSourceReferenceSystem());
        }
        {
            CICitationPropertyType theSourceCitation;
            theSourceCitation = this.getSourceCitation();
            strategy.appendField(locator, this, "sourceCitation", buffer, theSourceCitation, this.isSetSourceCitation());
        }
        {
            List<EXExtentPropertyType> theSourceExtent;
            theSourceExtent = (this.isSetSourceExtent()?this.getSourceExtent():null);
            strategy.appendField(locator, this, "sourceExtent", buffer, theSourceExtent, this.isSetSourceExtent());
        }
        {
            List<LIProcessStepPropertyType> theSourceStep;
            theSourceStep = (this.isSetSourceStep()?this.getSourceStep():null);
            strategy.appendField(locator, this, "sourceStep", buffer, theSourceStep, this.isSetSourceStep());
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
        final LISourceType that = ((LISourceType) object);
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
            MDRepresentativeFractionPropertyType lhsScaleDenominator;
            lhsScaleDenominator = this.getScaleDenominator();
            MDRepresentativeFractionPropertyType rhsScaleDenominator;
            rhsScaleDenominator = that.getScaleDenominator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "scaleDenominator", lhsScaleDenominator), LocatorUtils.property(thatLocator, "scaleDenominator", rhsScaleDenominator), lhsScaleDenominator, rhsScaleDenominator, this.isSetScaleDenominator(), that.isSetScaleDenominator())) {
                return false;
            }
        }
        {
            MDReferenceSystemPropertyType lhsSourceReferenceSystem;
            lhsSourceReferenceSystem = this.getSourceReferenceSystem();
            MDReferenceSystemPropertyType rhsSourceReferenceSystem;
            rhsSourceReferenceSystem = that.getSourceReferenceSystem();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sourceReferenceSystem", lhsSourceReferenceSystem), LocatorUtils.property(thatLocator, "sourceReferenceSystem", rhsSourceReferenceSystem), lhsSourceReferenceSystem, rhsSourceReferenceSystem, this.isSetSourceReferenceSystem(), that.isSetSourceReferenceSystem())) {
                return false;
            }
        }
        {
            CICitationPropertyType lhsSourceCitation;
            lhsSourceCitation = this.getSourceCitation();
            CICitationPropertyType rhsSourceCitation;
            rhsSourceCitation = that.getSourceCitation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sourceCitation", lhsSourceCitation), LocatorUtils.property(thatLocator, "sourceCitation", rhsSourceCitation), lhsSourceCitation, rhsSourceCitation, this.isSetSourceCitation(), that.isSetSourceCitation())) {
                return false;
            }
        }
        {
            List<EXExtentPropertyType> lhsSourceExtent;
            lhsSourceExtent = (this.isSetSourceExtent()?this.getSourceExtent():null);
            List<EXExtentPropertyType> rhsSourceExtent;
            rhsSourceExtent = (that.isSetSourceExtent()?that.getSourceExtent():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sourceExtent", lhsSourceExtent), LocatorUtils.property(thatLocator, "sourceExtent", rhsSourceExtent), lhsSourceExtent, rhsSourceExtent, this.isSetSourceExtent(), that.isSetSourceExtent())) {
                return false;
            }
        }
        {
            List<LIProcessStepPropertyType> lhsSourceStep;
            lhsSourceStep = (this.isSetSourceStep()?this.getSourceStep():null);
            List<LIProcessStepPropertyType> rhsSourceStep;
            rhsSourceStep = (that.isSetSourceStep()?that.getSourceStep():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sourceStep", lhsSourceStep), LocatorUtils.property(thatLocator, "sourceStep", rhsSourceStep), lhsSourceStep, rhsSourceStep, this.isSetSourceStep(), that.isSetSourceStep())) {
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
            MDRepresentativeFractionPropertyType theScaleDenominator;
            theScaleDenominator = this.getScaleDenominator();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "scaleDenominator", theScaleDenominator), currentHashCode, theScaleDenominator, this.isSetScaleDenominator());
        }
        {
            MDReferenceSystemPropertyType theSourceReferenceSystem;
            theSourceReferenceSystem = this.getSourceReferenceSystem();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sourceReferenceSystem", theSourceReferenceSystem), currentHashCode, theSourceReferenceSystem, this.isSetSourceReferenceSystem());
        }
        {
            CICitationPropertyType theSourceCitation;
            theSourceCitation = this.getSourceCitation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sourceCitation", theSourceCitation), currentHashCode, theSourceCitation, this.isSetSourceCitation());
        }
        {
            List<EXExtentPropertyType> theSourceExtent;
            theSourceExtent = (this.isSetSourceExtent()?this.getSourceExtent():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sourceExtent", theSourceExtent), currentHashCode, theSourceExtent, this.isSetSourceExtent());
        }
        {
            List<LIProcessStepPropertyType> theSourceStep;
            theSourceStep = (this.isSetSourceStep()?this.getSourceStep():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sourceStep", theSourceStep), currentHashCode, theSourceStep, this.isSetSourceStep());
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
        if (draftCopy instanceof LISourceType) {
            final LISourceType copy = ((LISourceType) draftCopy);
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
                Boolean scaleDenominatorShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetScaleDenominator());
                if (scaleDenominatorShouldBeCopiedAndSet == Boolean.TRUE) {
                    MDRepresentativeFractionPropertyType sourceScaleDenominator;
                    sourceScaleDenominator = this.getScaleDenominator();
                    MDRepresentativeFractionPropertyType copyScaleDenominator = ((MDRepresentativeFractionPropertyType) strategy.copy(LocatorUtils.property(locator, "scaleDenominator", sourceScaleDenominator), sourceScaleDenominator, this.isSetScaleDenominator()));
                    copy.setScaleDenominator(copyScaleDenominator);
                } else {
                    if (scaleDenominatorShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.scaleDenominator = null;
                    }
                }
            }
            {
                Boolean sourceReferenceSystemShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSourceReferenceSystem());
                if (sourceReferenceSystemShouldBeCopiedAndSet == Boolean.TRUE) {
                    MDReferenceSystemPropertyType sourceSourceReferenceSystem;
                    sourceSourceReferenceSystem = this.getSourceReferenceSystem();
                    MDReferenceSystemPropertyType copySourceReferenceSystem = ((MDReferenceSystemPropertyType) strategy.copy(LocatorUtils.property(locator, "sourceReferenceSystem", sourceSourceReferenceSystem), sourceSourceReferenceSystem, this.isSetSourceReferenceSystem()));
                    copy.setSourceReferenceSystem(copySourceReferenceSystem);
                } else {
                    if (sourceReferenceSystemShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.sourceReferenceSystem = null;
                    }
                }
            }
            {
                Boolean sourceCitationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSourceCitation());
                if (sourceCitationShouldBeCopiedAndSet == Boolean.TRUE) {
                    CICitationPropertyType sourceSourceCitation;
                    sourceSourceCitation = this.getSourceCitation();
                    CICitationPropertyType copySourceCitation = ((CICitationPropertyType) strategy.copy(LocatorUtils.property(locator, "sourceCitation", sourceSourceCitation), sourceSourceCitation, this.isSetSourceCitation()));
                    copy.setSourceCitation(copySourceCitation);
                } else {
                    if (sourceCitationShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.sourceCitation = null;
                    }
                }
            }
            {
                Boolean sourceExtentShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSourceExtent());
                if (sourceExtentShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<EXExtentPropertyType> sourceSourceExtent;
                    sourceSourceExtent = (this.isSetSourceExtent()?this.getSourceExtent():null);
                    @SuppressWarnings("unchecked")
                    List<EXExtentPropertyType> copySourceExtent = ((List<EXExtentPropertyType> ) strategy.copy(LocatorUtils.property(locator, "sourceExtent", sourceSourceExtent), sourceSourceExtent, this.isSetSourceExtent()));
                    copy.unsetSourceExtent();
                    if (copySourceExtent!= null) {
                        List<EXExtentPropertyType> uniqueSourceExtentl = copy.getSourceExtent();
                        uniqueSourceExtentl.addAll(copySourceExtent);
                    }
                } else {
                    if (sourceExtentShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetSourceExtent();
                    }
                }
            }
            {
                Boolean sourceStepShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSourceStep());
                if (sourceStepShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<LIProcessStepPropertyType> sourceSourceStep;
                    sourceSourceStep = (this.isSetSourceStep()?this.getSourceStep():null);
                    @SuppressWarnings("unchecked")
                    List<LIProcessStepPropertyType> copySourceStep = ((List<LIProcessStepPropertyType> ) strategy.copy(LocatorUtils.property(locator, "sourceStep", sourceSourceStep), sourceSourceStep, this.isSetSourceStep()));
                    copy.unsetSourceStep();
                    if (copySourceStep!= null) {
                        List<LIProcessStepPropertyType> uniqueSourceStepl = copy.getSourceStep();
                        uniqueSourceStepl.addAll(copySourceStep);
                    }
                } else {
                    if (sourceStepShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetSourceStep();
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new LISourceType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof LISourceType) {
            final LISourceType target = this;
            final LISourceType leftObject = ((LISourceType) left);
            final LISourceType rightObject = ((LISourceType) right);
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
                Boolean scaleDenominatorShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetScaleDenominator(), rightObject.isSetScaleDenominator());
                if (scaleDenominatorShouldBeMergedAndSet == Boolean.TRUE) {
                    MDRepresentativeFractionPropertyType lhsScaleDenominator;
                    lhsScaleDenominator = leftObject.getScaleDenominator();
                    MDRepresentativeFractionPropertyType rhsScaleDenominator;
                    rhsScaleDenominator = rightObject.getScaleDenominator();
                    MDRepresentativeFractionPropertyType mergedScaleDenominator = ((MDRepresentativeFractionPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "scaleDenominator", lhsScaleDenominator), LocatorUtils.property(rightLocator, "scaleDenominator", rhsScaleDenominator), lhsScaleDenominator, rhsScaleDenominator, leftObject.isSetScaleDenominator(), rightObject.isSetScaleDenominator()));
                    target.setScaleDenominator(mergedScaleDenominator);
                } else {
                    if (scaleDenominatorShouldBeMergedAndSet == Boolean.FALSE) {
                        target.scaleDenominator = null;
                    }
                }
            }
            {
                Boolean sourceReferenceSystemShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSourceReferenceSystem(), rightObject.isSetSourceReferenceSystem());
                if (sourceReferenceSystemShouldBeMergedAndSet == Boolean.TRUE) {
                    MDReferenceSystemPropertyType lhsSourceReferenceSystem;
                    lhsSourceReferenceSystem = leftObject.getSourceReferenceSystem();
                    MDReferenceSystemPropertyType rhsSourceReferenceSystem;
                    rhsSourceReferenceSystem = rightObject.getSourceReferenceSystem();
                    MDReferenceSystemPropertyType mergedSourceReferenceSystem = ((MDReferenceSystemPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "sourceReferenceSystem", lhsSourceReferenceSystem), LocatorUtils.property(rightLocator, "sourceReferenceSystem", rhsSourceReferenceSystem), lhsSourceReferenceSystem, rhsSourceReferenceSystem, leftObject.isSetSourceReferenceSystem(), rightObject.isSetSourceReferenceSystem()));
                    target.setSourceReferenceSystem(mergedSourceReferenceSystem);
                } else {
                    if (sourceReferenceSystemShouldBeMergedAndSet == Boolean.FALSE) {
                        target.sourceReferenceSystem = null;
                    }
                }
            }
            {
                Boolean sourceCitationShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSourceCitation(), rightObject.isSetSourceCitation());
                if (sourceCitationShouldBeMergedAndSet == Boolean.TRUE) {
                    CICitationPropertyType lhsSourceCitation;
                    lhsSourceCitation = leftObject.getSourceCitation();
                    CICitationPropertyType rhsSourceCitation;
                    rhsSourceCitation = rightObject.getSourceCitation();
                    CICitationPropertyType mergedSourceCitation = ((CICitationPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "sourceCitation", lhsSourceCitation), LocatorUtils.property(rightLocator, "sourceCitation", rhsSourceCitation), lhsSourceCitation, rhsSourceCitation, leftObject.isSetSourceCitation(), rightObject.isSetSourceCitation()));
                    target.setSourceCitation(mergedSourceCitation);
                } else {
                    if (sourceCitationShouldBeMergedAndSet == Boolean.FALSE) {
                        target.sourceCitation = null;
                    }
                }
            }
            {
                Boolean sourceExtentShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSourceExtent(), rightObject.isSetSourceExtent());
                if (sourceExtentShouldBeMergedAndSet == Boolean.TRUE) {
                    List<EXExtentPropertyType> lhsSourceExtent;
                    lhsSourceExtent = (leftObject.isSetSourceExtent()?leftObject.getSourceExtent():null);
                    List<EXExtentPropertyType> rhsSourceExtent;
                    rhsSourceExtent = (rightObject.isSetSourceExtent()?rightObject.getSourceExtent():null);
                    List<EXExtentPropertyType> mergedSourceExtent = ((List<EXExtentPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "sourceExtent", lhsSourceExtent), LocatorUtils.property(rightLocator, "sourceExtent", rhsSourceExtent), lhsSourceExtent, rhsSourceExtent, leftObject.isSetSourceExtent(), rightObject.isSetSourceExtent()));
                    target.unsetSourceExtent();
                    if (mergedSourceExtent!= null) {
                        List<EXExtentPropertyType> uniqueSourceExtentl = target.getSourceExtent();
                        uniqueSourceExtentl.addAll(mergedSourceExtent);
                    }
                } else {
                    if (sourceExtentShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetSourceExtent();
                    }
                }
            }
            {
                Boolean sourceStepShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSourceStep(), rightObject.isSetSourceStep());
                if (sourceStepShouldBeMergedAndSet == Boolean.TRUE) {
                    List<LIProcessStepPropertyType> lhsSourceStep;
                    lhsSourceStep = (leftObject.isSetSourceStep()?leftObject.getSourceStep():null);
                    List<LIProcessStepPropertyType> rhsSourceStep;
                    rhsSourceStep = (rightObject.isSetSourceStep()?rightObject.getSourceStep():null);
                    List<LIProcessStepPropertyType> mergedSourceStep = ((List<LIProcessStepPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "sourceStep", lhsSourceStep), LocatorUtils.property(rightLocator, "sourceStep", rhsSourceStep), lhsSourceStep, rhsSourceStep, leftObject.isSetSourceStep(), rightObject.isSetSourceStep()));
                    target.unsetSourceStep();
                    if (mergedSourceStep!= null) {
                        List<LIProcessStepPropertyType> uniqueSourceStepl = target.getSourceStep();
                        uniqueSourceStepl.addAll(mergedSourceStep);
                    }
                } else {
                    if (sourceStepShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetSourceStep();
                    }
                }
            }
        }
    }

    public void setSourceExtent(List<EXExtentPropertyType> value) {
        this.sourceExtent = null;
        if (value!= null) {
            List<EXExtentPropertyType> draftl = this.getSourceExtent();
            draftl.addAll(value);
        }
    }

    public void setSourceStep(List<LIProcessStepPropertyType> value) {
        this.sourceStep = null;
        if (value!= null) {
            List<LIProcessStepPropertyType> draftl = this.getSourceStep();
            draftl.addAll(value);
        }
    }

}
