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
import org.isotc211._2005.gco.BooleanPropertyType;
import org.isotc211._2005.gco.CharacterStringPropertyType;
import org.isotc211._2005.gco.GenericNamePropertyType;
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
 * Information identifing the feature catalogue
 * 
 * <p>Java class for MD_FeatureCatalogueDescription_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MD_FeatureCatalogueDescription_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.isotc211.org/2005/gmd}AbstractMD_ContentInformation_Type"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="complianceCode" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType" minOccurs="0"/&gt;
 *         &lt;element name="language" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="includedWithDataset" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType"/&gt;
 *         &lt;element name="featureTypes" type="{http://www.isotc211.org/2005/gco}GenericName_PropertyType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="featureCatalogueCitation" type="{http://www.isotc211.org/2005/gmd}CI_Citation_PropertyType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_FeatureCatalogueDescription_Type", propOrder = {
    "complianceCode",
    "language",
    "includedWithDataset",
    "featureTypes",
    "featureCatalogueCitation"
})
public class MDFeatureCatalogueDescriptionType
    extends AbstractMDContentInformationType
    implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @Valid
    protected BooleanPropertyType complianceCode;
    @Valid
    protected List<CharacterStringPropertyType> language;
    @XmlElement(required = true)
    @NotNull(message = "MDFeatureCatalogueDescriptionType.includedWithDataset {javax.validation.constraints.NotNull.message}")
    @Valid
    protected BooleanPropertyType includedWithDataset;
    @Valid
    protected List<GenericNamePropertyType> featureTypes;
    @XmlElement(required = true)
    @NotNull(message = "MDFeatureCatalogueDescriptionType.featureCatalogueCitation {javax.validation.constraints.NotNull.message}")
    @Size(min = 1)
    @Valid
    protected List<CICitationPropertyType> featureCatalogueCitation;

    /**
     * Gets the value of the complianceCode property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getComplianceCode() {
        return complianceCode;
    }

    /**
     * Sets the value of the complianceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setComplianceCode(BooleanPropertyType value) {
        this.complianceCode = value;
    }

    public boolean isSetComplianceCode() {
        return (this.complianceCode!= null);
    }

    /**
     * Gets the value of the language property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the language property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLanguage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacterStringPropertyType }
     * 
     * 
     */
    public List<CharacterStringPropertyType> getLanguage() {
        if (language == null) {
            language = new ArrayList<CharacterStringPropertyType>();
        }
        return this.language;
    }

    public boolean isSetLanguage() {
        return ((this.language!= null)&&(!this.language.isEmpty()));
    }

    public void unsetLanguage() {
        this.language = null;
    }

    /**
     * Gets the value of the includedWithDataset property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getIncludedWithDataset() {
        return includedWithDataset;
    }

    /**
     * Sets the value of the includedWithDataset property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setIncludedWithDataset(BooleanPropertyType value) {
        this.includedWithDataset = value;
    }

    public boolean isSetIncludedWithDataset() {
        return (this.includedWithDataset!= null);
    }

    /**
     * Gets the value of the featureTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the featureTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeatureTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GenericNamePropertyType }
     * 
     * 
     */
    public List<GenericNamePropertyType> getFeatureTypes() {
        if (featureTypes == null) {
            featureTypes = new ArrayList<GenericNamePropertyType>();
        }
        return this.featureTypes;
    }

    public boolean isSetFeatureTypes() {
        return ((this.featureTypes!= null)&&(!this.featureTypes.isEmpty()));
    }

    public void unsetFeatureTypes() {
        this.featureTypes = null;
    }

    /**
     * Gets the value of the featureCatalogueCitation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the featureCatalogueCitation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeatureCatalogueCitation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CICitationPropertyType }
     * 
     * 
     */
    public List<CICitationPropertyType> getFeatureCatalogueCitation() {
        if (featureCatalogueCitation == null) {
            featureCatalogueCitation = new ArrayList<CICitationPropertyType>();
        }
        return this.featureCatalogueCitation;
    }

    public boolean isSetFeatureCatalogueCitation() {
        return ((this.featureCatalogueCitation!= null)&&(!this.featureCatalogueCitation.isEmpty()));
    }

    public void unsetFeatureCatalogueCitation() {
        this.featureCatalogueCitation = null;
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
            BooleanPropertyType theComplianceCode;
            theComplianceCode = this.getComplianceCode();
            strategy.appendField(locator, this, "complianceCode", buffer, theComplianceCode, this.isSetComplianceCode());
        }
        {
            List<CharacterStringPropertyType> theLanguage;
            theLanguage = (this.isSetLanguage()?this.getLanguage():null);
            strategy.appendField(locator, this, "language", buffer, theLanguage, this.isSetLanguage());
        }
        {
            BooleanPropertyType theIncludedWithDataset;
            theIncludedWithDataset = this.getIncludedWithDataset();
            strategy.appendField(locator, this, "includedWithDataset", buffer, theIncludedWithDataset, this.isSetIncludedWithDataset());
        }
        {
            List<GenericNamePropertyType> theFeatureTypes;
            theFeatureTypes = (this.isSetFeatureTypes()?this.getFeatureTypes():null);
            strategy.appendField(locator, this, "featureTypes", buffer, theFeatureTypes, this.isSetFeatureTypes());
        }
        {
            List<CICitationPropertyType> theFeatureCatalogueCitation;
            theFeatureCatalogueCitation = (this.isSetFeatureCatalogueCitation()?this.getFeatureCatalogueCitation():null);
            strategy.appendField(locator, this, "featureCatalogueCitation", buffer, theFeatureCatalogueCitation, this.isSetFeatureCatalogueCitation());
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
        final MDFeatureCatalogueDescriptionType that = ((MDFeatureCatalogueDescriptionType) object);
        {
            BooleanPropertyType lhsComplianceCode;
            lhsComplianceCode = this.getComplianceCode();
            BooleanPropertyType rhsComplianceCode;
            rhsComplianceCode = that.getComplianceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "complianceCode", lhsComplianceCode), LocatorUtils.property(thatLocator, "complianceCode", rhsComplianceCode), lhsComplianceCode, rhsComplianceCode, this.isSetComplianceCode(), that.isSetComplianceCode())) {
                return false;
            }
        }
        {
            List<CharacterStringPropertyType> lhsLanguage;
            lhsLanguage = (this.isSetLanguage()?this.getLanguage():null);
            List<CharacterStringPropertyType> rhsLanguage;
            rhsLanguage = (that.isSetLanguage()?that.getLanguage():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "language", lhsLanguage), LocatorUtils.property(thatLocator, "language", rhsLanguage), lhsLanguage, rhsLanguage, this.isSetLanguage(), that.isSetLanguage())) {
                return false;
            }
        }
        {
            BooleanPropertyType lhsIncludedWithDataset;
            lhsIncludedWithDataset = this.getIncludedWithDataset();
            BooleanPropertyType rhsIncludedWithDataset;
            rhsIncludedWithDataset = that.getIncludedWithDataset();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "includedWithDataset", lhsIncludedWithDataset), LocatorUtils.property(thatLocator, "includedWithDataset", rhsIncludedWithDataset), lhsIncludedWithDataset, rhsIncludedWithDataset, this.isSetIncludedWithDataset(), that.isSetIncludedWithDataset())) {
                return false;
            }
        }
        {
            List<GenericNamePropertyType> lhsFeatureTypes;
            lhsFeatureTypes = (this.isSetFeatureTypes()?this.getFeatureTypes():null);
            List<GenericNamePropertyType> rhsFeatureTypes;
            rhsFeatureTypes = (that.isSetFeatureTypes()?that.getFeatureTypes():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "featureTypes", lhsFeatureTypes), LocatorUtils.property(thatLocator, "featureTypes", rhsFeatureTypes), lhsFeatureTypes, rhsFeatureTypes, this.isSetFeatureTypes(), that.isSetFeatureTypes())) {
                return false;
            }
        }
        {
            List<CICitationPropertyType> lhsFeatureCatalogueCitation;
            lhsFeatureCatalogueCitation = (this.isSetFeatureCatalogueCitation()?this.getFeatureCatalogueCitation():null);
            List<CICitationPropertyType> rhsFeatureCatalogueCitation;
            rhsFeatureCatalogueCitation = (that.isSetFeatureCatalogueCitation()?that.getFeatureCatalogueCitation():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "featureCatalogueCitation", lhsFeatureCatalogueCitation), LocatorUtils.property(thatLocator, "featureCatalogueCitation", rhsFeatureCatalogueCitation), lhsFeatureCatalogueCitation, rhsFeatureCatalogueCitation, this.isSetFeatureCatalogueCitation(), that.isSetFeatureCatalogueCitation())) {
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
            BooleanPropertyType theComplianceCode;
            theComplianceCode = this.getComplianceCode();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "complianceCode", theComplianceCode), currentHashCode, theComplianceCode, this.isSetComplianceCode());
        }
        {
            List<CharacterStringPropertyType> theLanguage;
            theLanguage = (this.isSetLanguage()?this.getLanguage():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "language", theLanguage), currentHashCode, theLanguage, this.isSetLanguage());
        }
        {
            BooleanPropertyType theIncludedWithDataset;
            theIncludedWithDataset = this.getIncludedWithDataset();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "includedWithDataset", theIncludedWithDataset), currentHashCode, theIncludedWithDataset, this.isSetIncludedWithDataset());
        }
        {
            List<GenericNamePropertyType> theFeatureTypes;
            theFeatureTypes = (this.isSetFeatureTypes()?this.getFeatureTypes():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "featureTypes", theFeatureTypes), currentHashCode, theFeatureTypes, this.isSetFeatureTypes());
        }
        {
            List<CICitationPropertyType> theFeatureCatalogueCitation;
            theFeatureCatalogueCitation = (this.isSetFeatureCatalogueCitation()?this.getFeatureCatalogueCitation():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "featureCatalogueCitation", theFeatureCatalogueCitation), currentHashCode, theFeatureCatalogueCitation, this.isSetFeatureCatalogueCitation());
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
        if (draftCopy instanceof MDFeatureCatalogueDescriptionType) {
            final MDFeatureCatalogueDescriptionType copy = ((MDFeatureCatalogueDescriptionType) draftCopy);
            {
                Boolean complianceCodeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetComplianceCode());
                if (complianceCodeShouldBeCopiedAndSet == Boolean.TRUE) {
                    BooleanPropertyType sourceComplianceCode;
                    sourceComplianceCode = this.getComplianceCode();
                    BooleanPropertyType copyComplianceCode = ((BooleanPropertyType) strategy.copy(LocatorUtils.property(locator, "complianceCode", sourceComplianceCode), sourceComplianceCode, this.isSetComplianceCode()));
                    copy.setComplianceCode(copyComplianceCode);
                } else {
                    if (complianceCodeShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.complianceCode = null;
                    }
                }
            }
            {
                Boolean languageShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLanguage());
                if (languageShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<CharacterStringPropertyType> sourceLanguage;
                    sourceLanguage = (this.isSetLanguage()?this.getLanguage():null);
                    @SuppressWarnings("unchecked")
                    List<CharacterStringPropertyType> copyLanguage = ((List<CharacterStringPropertyType> ) strategy.copy(LocatorUtils.property(locator, "language", sourceLanguage), sourceLanguage, this.isSetLanguage()));
                    copy.unsetLanguage();
                    if (copyLanguage!= null) {
                        List<CharacterStringPropertyType> uniqueLanguagel = copy.getLanguage();
                        uniqueLanguagel.addAll(copyLanguage);
                    }
                } else {
                    if (languageShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetLanguage();
                    }
                }
            }
            {
                Boolean includedWithDatasetShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetIncludedWithDataset());
                if (includedWithDatasetShouldBeCopiedAndSet == Boolean.TRUE) {
                    BooleanPropertyType sourceIncludedWithDataset;
                    sourceIncludedWithDataset = this.getIncludedWithDataset();
                    BooleanPropertyType copyIncludedWithDataset = ((BooleanPropertyType) strategy.copy(LocatorUtils.property(locator, "includedWithDataset", sourceIncludedWithDataset), sourceIncludedWithDataset, this.isSetIncludedWithDataset()));
                    copy.setIncludedWithDataset(copyIncludedWithDataset);
                } else {
                    if (includedWithDatasetShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.includedWithDataset = null;
                    }
                }
            }
            {
                Boolean featureTypesShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetFeatureTypes());
                if (featureTypesShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<GenericNamePropertyType> sourceFeatureTypes;
                    sourceFeatureTypes = (this.isSetFeatureTypes()?this.getFeatureTypes():null);
                    @SuppressWarnings("unchecked")
                    List<GenericNamePropertyType> copyFeatureTypes = ((List<GenericNamePropertyType> ) strategy.copy(LocatorUtils.property(locator, "featureTypes", sourceFeatureTypes), sourceFeatureTypes, this.isSetFeatureTypes()));
                    copy.unsetFeatureTypes();
                    if (copyFeatureTypes!= null) {
                        List<GenericNamePropertyType> uniqueFeatureTypesl = copy.getFeatureTypes();
                        uniqueFeatureTypesl.addAll(copyFeatureTypes);
                    }
                } else {
                    if (featureTypesShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetFeatureTypes();
                    }
                }
            }
            {
                Boolean featureCatalogueCitationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetFeatureCatalogueCitation());
                if (featureCatalogueCitationShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<CICitationPropertyType> sourceFeatureCatalogueCitation;
                    sourceFeatureCatalogueCitation = (this.isSetFeatureCatalogueCitation()?this.getFeatureCatalogueCitation():null);
                    @SuppressWarnings("unchecked")
                    List<CICitationPropertyType> copyFeatureCatalogueCitation = ((List<CICitationPropertyType> ) strategy.copy(LocatorUtils.property(locator, "featureCatalogueCitation", sourceFeatureCatalogueCitation), sourceFeatureCatalogueCitation, this.isSetFeatureCatalogueCitation()));
                    copy.unsetFeatureCatalogueCitation();
                    if (copyFeatureCatalogueCitation!= null) {
                        List<CICitationPropertyType> uniqueFeatureCatalogueCitationl = copy.getFeatureCatalogueCitation();
                        uniqueFeatureCatalogueCitationl.addAll(copyFeatureCatalogueCitation);
                    }
                } else {
                    if (featureCatalogueCitationShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetFeatureCatalogueCitation();
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new MDFeatureCatalogueDescriptionType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof MDFeatureCatalogueDescriptionType) {
            final MDFeatureCatalogueDescriptionType target = this;
            final MDFeatureCatalogueDescriptionType leftObject = ((MDFeatureCatalogueDescriptionType) left);
            final MDFeatureCatalogueDescriptionType rightObject = ((MDFeatureCatalogueDescriptionType) right);
            {
                Boolean complianceCodeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetComplianceCode(), rightObject.isSetComplianceCode());
                if (complianceCodeShouldBeMergedAndSet == Boolean.TRUE) {
                    BooleanPropertyType lhsComplianceCode;
                    lhsComplianceCode = leftObject.getComplianceCode();
                    BooleanPropertyType rhsComplianceCode;
                    rhsComplianceCode = rightObject.getComplianceCode();
                    BooleanPropertyType mergedComplianceCode = ((BooleanPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "complianceCode", lhsComplianceCode), LocatorUtils.property(rightLocator, "complianceCode", rhsComplianceCode), lhsComplianceCode, rhsComplianceCode, leftObject.isSetComplianceCode(), rightObject.isSetComplianceCode()));
                    target.setComplianceCode(mergedComplianceCode);
                } else {
                    if (complianceCodeShouldBeMergedAndSet == Boolean.FALSE) {
                        target.complianceCode = null;
                    }
                }
            }
            {
                Boolean languageShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLanguage(), rightObject.isSetLanguage());
                if (languageShouldBeMergedAndSet == Boolean.TRUE) {
                    List<CharacterStringPropertyType> lhsLanguage;
                    lhsLanguage = (leftObject.isSetLanguage()?leftObject.getLanguage():null);
                    List<CharacterStringPropertyType> rhsLanguage;
                    rhsLanguage = (rightObject.isSetLanguage()?rightObject.getLanguage():null);
                    List<CharacterStringPropertyType> mergedLanguage = ((List<CharacterStringPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "language", lhsLanguage), LocatorUtils.property(rightLocator, "language", rhsLanguage), lhsLanguage, rhsLanguage, leftObject.isSetLanguage(), rightObject.isSetLanguage()));
                    target.unsetLanguage();
                    if (mergedLanguage!= null) {
                        List<CharacterStringPropertyType> uniqueLanguagel = target.getLanguage();
                        uniqueLanguagel.addAll(mergedLanguage);
                    }
                } else {
                    if (languageShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetLanguage();
                    }
                }
            }
            {
                Boolean includedWithDatasetShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetIncludedWithDataset(), rightObject.isSetIncludedWithDataset());
                if (includedWithDatasetShouldBeMergedAndSet == Boolean.TRUE) {
                    BooleanPropertyType lhsIncludedWithDataset;
                    lhsIncludedWithDataset = leftObject.getIncludedWithDataset();
                    BooleanPropertyType rhsIncludedWithDataset;
                    rhsIncludedWithDataset = rightObject.getIncludedWithDataset();
                    BooleanPropertyType mergedIncludedWithDataset = ((BooleanPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "includedWithDataset", lhsIncludedWithDataset), LocatorUtils.property(rightLocator, "includedWithDataset", rhsIncludedWithDataset), lhsIncludedWithDataset, rhsIncludedWithDataset, leftObject.isSetIncludedWithDataset(), rightObject.isSetIncludedWithDataset()));
                    target.setIncludedWithDataset(mergedIncludedWithDataset);
                } else {
                    if (includedWithDatasetShouldBeMergedAndSet == Boolean.FALSE) {
                        target.includedWithDataset = null;
                    }
                }
            }
            {
                Boolean featureTypesShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetFeatureTypes(), rightObject.isSetFeatureTypes());
                if (featureTypesShouldBeMergedAndSet == Boolean.TRUE) {
                    List<GenericNamePropertyType> lhsFeatureTypes;
                    lhsFeatureTypes = (leftObject.isSetFeatureTypes()?leftObject.getFeatureTypes():null);
                    List<GenericNamePropertyType> rhsFeatureTypes;
                    rhsFeatureTypes = (rightObject.isSetFeatureTypes()?rightObject.getFeatureTypes():null);
                    List<GenericNamePropertyType> mergedFeatureTypes = ((List<GenericNamePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "featureTypes", lhsFeatureTypes), LocatorUtils.property(rightLocator, "featureTypes", rhsFeatureTypes), lhsFeatureTypes, rhsFeatureTypes, leftObject.isSetFeatureTypes(), rightObject.isSetFeatureTypes()));
                    target.unsetFeatureTypes();
                    if (mergedFeatureTypes!= null) {
                        List<GenericNamePropertyType> uniqueFeatureTypesl = target.getFeatureTypes();
                        uniqueFeatureTypesl.addAll(mergedFeatureTypes);
                    }
                } else {
                    if (featureTypesShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetFeatureTypes();
                    }
                }
            }
            {
                Boolean featureCatalogueCitationShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetFeatureCatalogueCitation(), rightObject.isSetFeatureCatalogueCitation());
                if (featureCatalogueCitationShouldBeMergedAndSet == Boolean.TRUE) {
                    List<CICitationPropertyType> lhsFeatureCatalogueCitation;
                    lhsFeatureCatalogueCitation = (leftObject.isSetFeatureCatalogueCitation()?leftObject.getFeatureCatalogueCitation():null);
                    List<CICitationPropertyType> rhsFeatureCatalogueCitation;
                    rhsFeatureCatalogueCitation = (rightObject.isSetFeatureCatalogueCitation()?rightObject.getFeatureCatalogueCitation():null);
                    List<CICitationPropertyType> mergedFeatureCatalogueCitation = ((List<CICitationPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "featureCatalogueCitation", lhsFeatureCatalogueCitation), LocatorUtils.property(rightLocator, "featureCatalogueCitation", rhsFeatureCatalogueCitation), lhsFeatureCatalogueCitation, rhsFeatureCatalogueCitation, leftObject.isSetFeatureCatalogueCitation(), rightObject.isSetFeatureCatalogueCitation()));
                    target.unsetFeatureCatalogueCitation();
                    if (mergedFeatureCatalogueCitation!= null) {
                        List<CICitationPropertyType> uniqueFeatureCatalogueCitationl = target.getFeatureCatalogueCitation();
                        uniqueFeatureCatalogueCitationl.addAll(mergedFeatureCatalogueCitation);
                    }
                } else {
                    if (featureCatalogueCitationShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetFeatureCatalogueCitation();
                    }
                }
            }
        }
    }

    public void setLanguage(List<CharacterStringPropertyType> value) {
        this.language = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getLanguage();
            draftl.addAll(value);
        }
    }

    public void setFeatureTypes(List<GenericNamePropertyType> value) {
        this.featureTypes = null;
        if (value!= null) {
            List<GenericNamePropertyType> draftl = this.getFeatureTypes();
            draftl.addAll(value);
        }
    }

    public void setFeatureCatalogueCitation(List<CICitationPropertyType> value) {
        this.featureCatalogueCitation = null;
        if (value!= null) {
            List<CICitationPropertyType> draftl = this.getFeatureCatalogueCitation();
            draftl.addAll(value);
        }
    }

}
