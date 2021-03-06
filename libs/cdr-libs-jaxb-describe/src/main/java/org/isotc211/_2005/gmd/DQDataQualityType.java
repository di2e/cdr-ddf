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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.isotc211._2005.gco.AbstractObjectType;
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
 * <p>Java class for DQ_DataQuality_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DQ_DataQuality_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="scope" type="{http://www.isotc211.org/2005/gmd}DQ_Scope_PropertyType"/&gt;
 *         &lt;element name="report" type="{http://www.isotc211.org/2005/gmd}DQ_Element_PropertyType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="lineage" type="{http://www.isotc211.org/2005/gmd}LI_Lineage_PropertyType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DQ_DataQuality_Type", propOrder = {
    "scope",
    "report",
    "lineage"
})
public class DQDataQualityType
    extends AbstractObjectType
    implements Serializable, Cloneable, CopyTo2, Equals2, HashCode2, MergeFrom2, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    @NotNull(message = "DQDataQualityType.scope {javax.validation.constraints.NotNull.message}")
    @Valid
    protected DQScopePropertyType scope;
    @Valid
    protected List<DQElementPropertyType> report;
    @Valid
    protected LILineagePropertyType lineage;

    /**
     * Gets the value of the scope property.
     * 
     * @return
     *     possible object is
     *     {@link DQScopePropertyType }
     *     
     */
    public DQScopePropertyType getScope() {
        return scope;
    }

    /**
     * Sets the value of the scope property.
     * 
     * @param value
     *     allowed object is
     *     {@link DQScopePropertyType }
     *     
     */
    public void setScope(DQScopePropertyType value) {
        this.scope = value;
    }

    public boolean isSetScope() {
        return (this.scope!= null);
    }

    /**
     * Gets the value of the report property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the report property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReport().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DQElementPropertyType }
     * 
     * 
     */
    public List<DQElementPropertyType> getReport() {
        if (report == null) {
            report = new ArrayList<DQElementPropertyType>();
        }
        return this.report;
    }

    public boolean isSetReport() {
        return ((this.report!= null)&&(!this.report.isEmpty()));
    }

    public void unsetReport() {
        this.report = null;
    }

    /**
     * Gets the value of the lineage property.
     * 
     * @return
     *     possible object is
     *     {@link LILineagePropertyType }
     *     
     */
    public LILineagePropertyType getLineage() {
        return lineage;
    }

    /**
     * Sets the value of the lineage property.
     * 
     * @param value
     *     allowed object is
     *     {@link LILineagePropertyType }
     *     
     */
    public void setLineage(LILineagePropertyType value) {
        this.lineage = value;
    }

    public boolean isSetLineage() {
        return (this.lineage!= null);
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
            DQScopePropertyType theScope;
            theScope = this.getScope();
            strategy.appendField(locator, this, "scope", buffer, theScope, this.isSetScope());
        }
        {
            List<DQElementPropertyType> theReport;
            theReport = (this.isSetReport()?this.getReport():null);
            strategy.appendField(locator, this, "report", buffer, theReport, this.isSetReport());
        }
        {
            LILineagePropertyType theLineage;
            theLineage = this.getLineage();
            strategy.appendField(locator, this, "lineage", buffer, theLineage, this.isSetLineage());
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
        final DQDataQualityType that = ((DQDataQualityType) object);
        {
            DQScopePropertyType lhsScope;
            lhsScope = this.getScope();
            DQScopePropertyType rhsScope;
            rhsScope = that.getScope();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "scope", lhsScope), LocatorUtils.property(thatLocator, "scope", rhsScope), lhsScope, rhsScope, this.isSetScope(), that.isSetScope())) {
                return false;
            }
        }
        {
            List<DQElementPropertyType> lhsReport;
            lhsReport = (this.isSetReport()?this.getReport():null);
            List<DQElementPropertyType> rhsReport;
            rhsReport = (that.isSetReport()?that.getReport():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "report", lhsReport), LocatorUtils.property(thatLocator, "report", rhsReport), lhsReport, rhsReport, this.isSetReport(), that.isSetReport())) {
                return false;
            }
        }
        {
            LILineagePropertyType lhsLineage;
            lhsLineage = this.getLineage();
            LILineagePropertyType rhsLineage;
            rhsLineage = that.getLineage();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lineage", lhsLineage), LocatorUtils.property(thatLocator, "lineage", rhsLineage), lhsLineage, rhsLineage, this.isSetLineage(), that.isSetLineage())) {
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
            DQScopePropertyType theScope;
            theScope = this.getScope();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "scope", theScope), currentHashCode, theScope, this.isSetScope());
        }
        {
            List<DQElementPropertyType> theReport;
            theReport = (this.isSetReport()?this.getReport():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "report", theReport), currentHashCode, theReport, this.isSetReport());
        }
        {
            LILineagePropertyType theLineage;
            theLineage = this.getLineage();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lineage", theLineage), currentHashCode, theLineage, this.isSetLineage());
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
        if (draftCopy instanceof DQDataQualityType) {
            final DQDataQualityType copy = ((DQDataQualityType) draftCopy);
            {
                Boolean scopeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetScope());
                if (scopeShouldBeCopiedAndSet == Boolean.TRUE) {
                    DQScopePropertyType sourceScope;
                    sourceScope = this.getScope();
                    DQScopePropertyType copyScope = ((DQScopePropertyType) strategy.copy(LocatorUtils.property(locator, "scope", sourceScope), sourceScope, this.isSetScope()));
                    copy.setScope(copyScope);
                } else {
                    if (scopeShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.scope = null;
                    }
                }
            }
            {
                Boolean reportShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetReport());
                if (reportShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<DQElementPropertyType> sourceReport;
                    sourceReport = (this.isSetReport()?this.getReport():null);
                    @SuppressWarnings("unchecked")
                    List<DQElementPropertyType> copyReport = ((List<DQElementPropertyType> ) strategy.copy(LocatorUtils.property(locator, "report", sourceReport), sourceReport, this.isSetReport()));
                    copy.unsetReport();
                    if (copyReport!= null) {
                        List<DQElementPropertyType> uniqueReportl = copy.getReport();
                        uniqueReportl.addAll(copyReport);
                    }
                } else {
                    if (reportShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.unsetReport();
                    }
                }
            }
            {
                Boolean lineageShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLineage());
                if (lineageShouldBeCopiedAndSet == Boolean.TRUE) {
                    LILineagePropertyType sourceLineage;
                    sourceLineage = this.getLineage();
                    LILineagePropertyType copyLineage = ((LILineagePropertyType) strategy.copy(LocatorUtils.property(locator, "lineage", sourceLineage), sourceLineage, this.isSetLineage()));
                    copy.setLineage(copyLineage);
                } else {
                    if (lineageShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.lineage = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new DQDataQualityType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy2 strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy2 strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof DQDataQualityType) {
            final DQDataQualityType target = this;
            final DQDataQualityType leftObject = ((DQDataQualityType) left);
            final DQDataQualityType rightObject = ((DQDataQualityType) right);
            {
                Boolean scopeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetScope(), rightObject.isSetScope());
                if (scopeShouldBeMergedAndSet == Boolean.TRUE) {
                    DQScopePropertyType lhsScope;
                    lhsScope = leftObject.getScope();
                    DQScopePropertyType rhsScope;
                    rhsScope = rightObject.getScope();
                    DQScopePropertyType mergedScope = ((DQScopePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "scope", lhsScope), LocatorUtils.property(rightLocator, "scope", rhsScope), lhsScope, rhsScope, leftObject.isSetScope(), rightObject.isSetScope()));
                    target.setScope(mergedScope);
                } else {
                    if (scopeShouldBeMergedAndSet == Boolean.FALSE) {
                        target.scope = null;
                    }
                }
            }
            {
                Boolean reportShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetReport(), rightObject.isSetReport());
                if (reportShouldBeMergedAndSet == Boolean.TRUE) {
                    List<DQElementPropertyType> lhsReport;
                    lhsReport = (leftObject.isSetReport()?leftObject.getReport():null);
                    List<DQElementPropertyType> rhsReport;
                    rhsReport = (rightObject.isSetReport()?rightObject.getReport():null);
                    List<DQElementPropertyType> mergedReport = ((List<DQElementPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "report", lhsReport), LocatorUtils.property(rightLocator, "report", rhsReport), lhsReport, rhsReport, leftObject.isSetReport(), rightObject.isSetReport()));
                    target.unsetReport();
                    if (mergedReport!= null) {
                        List<DQElementPropertyType> uniqueReportl = target.getReport();
                        uniqueReportl.addAll(mergedReport);
                    }
                } else {
                    if (reportShouldBeMergedAndSet == Boolean.FALSE) {
                        target.unsetReport();
                    }
                }
            }
            {
                Boolean lineageShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLineage(), rightObject.isSetLineage());
                if (lineageShouldBeMergedAndSet == Boolean.TRUE) {
                    LILineagePropertyType lhsLineage;
                    lhsLineage = leftObject.getLineage();
                    LILineagePropertyType rhsLineage;
                    rhsLineage = rightObject.getLineage();
                    LILineagePropertyType mergedLineage = ((LILineagePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "lineage", lhsLineage), LocatorUtils.property(rightLocator, "lineage", rhsLineage), lhsLineage, rhsLineage, leftObject.isSetLineage(), rightObject.isSetLineage()));
                    target.setLineage(mergedLineage);
                } else {
                    if (lineageShouldBeMergedAndSet == Boolean.FALSE) {
                        target.lineage = null;
                    }
                }
            }
        }
    }

    public void setReport(List<DQElementPropertyType> value) {
        this.report = null;
        if (value!= null) {
            List<DQElementPropertyType> draftl = this.getReport();
            draftl.addAll(value);
        }
    }

}
