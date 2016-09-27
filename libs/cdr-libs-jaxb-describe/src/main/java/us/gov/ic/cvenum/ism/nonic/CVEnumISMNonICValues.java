//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package us.gov.ic.cvenum.ism.nonic;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CVEnumISMNonICValues.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CVEnumISMNonICValues"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="DS"/&gt;
 *     &lt;enumeration value="XD"/&gt;
 *     &lt;enumeration value="ND"/&gt;
 *     &lt;enumeration value="SBU"/&gt;
 *     &lt;enumeration value="SBU-NF"/&gt;
 *     &lt;enumeration value="LES"/&gt;
 *     &lt;enumeration value="LES-NF"/&gt;
 *     &lt;enumeration value="SSI"/&gt;
 *     &lt;enumeration value="ACCM-FOCAL_POINT"/&gt;
 *     &lt;enumeration value="NNPI"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CVEnumISMNonICValues", namespace = "urn:us:gov:ic:cvenum:ism:nonic")
@XmlEnum
public enum CVEnumISMNonICValues {


    /**
     * (U) LIMITED DISTRIBUTION
     * 
     */
    DS("DS"),

    /**
     * (U) EXCLUSIVE DISTRIBUTION
     * 
     */
    XD("XD"),

    /**
     * (U) NO DISTRIBUTION
     * 
     */
    ND("ND"),

    /**
     * (U) SENSITIVE BUT UNCLASSIFIED
     * 
     */
    SBU("SBU"),

    /**
     * (U) SENSITIVE BUT UNCLASSIFIED NOFORN
     * 
     */
    @XmlEnumValue("SBU-NF")
    SBU_NF("SBU-NF"),

    /**
     * (U) LAW ENFORCEMENT SENSITIVE
     * 
     */
    LES("LES"),

    /**
     * (U) LAW ENFORCEMENT SENSITIVE NOFORN
     * 
     */
    @XmlEnumValue("LES-NF")
    LES_NF("LES-NF"),

    /**
     * (U) SENSITIVE SECURITY INFORMATION
     * 
     */
    SSI("SSI"),

    /**
     * (U) FOCAL POINT (This is an Unclassified value that is not for public release. It belongs in the FOUO release only.)
     * 
     */
    @XmlEnumValue("ACCM-FOCAL_POINT")
    ACCM_FOCAL_POINT("ACCM-FOCAL_POINT"),

    /**
     * (U) NAVAL NUCLEAR PROPULSION INFORMATION
     * 
     */
    NNPI("NNPI");
    private final String value;

    CVEnumISMNonICValues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CVEnumISMNonICValues fromValue(String v) {
        for (CVEnumISMNonICValues c: CVEnumISMNonICValues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}