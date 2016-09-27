//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package us.gov.ic.cvenum.ism.scicontrols;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CVEnumISMSCIControlsValues.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CVEnumISMSCIControlsValues"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="EL"/&gt;
 *     &lt;enumeration value="EL-EU"/&gt;
 *     &lt;enumeration value="EL-NK"/&gt;
 *     &lt;enumeration value="HCS"/&gt;
 *     &lt;enumeration value="HCS-O"/&gt;
 *     &lt;enumeration value="HCS-P"/&gt;
 *     &lt;enumeration value="KDK"/&gt;
 *     &lt;enumeration value="KDK-BLFH"/&gt;
 *     &lt;enumeration value="KDK-IDIT"/&gt;
 *     &lt;enumeration value="KDK-KAND"/&gt;
 *     &lt;enumeration value="RSV"/&gt;
 *     &lt;enumeration value="SI"/&gt;
 *     &lt;enumeration value="SI-G"/&gt;
 *     &lt;enumeration value="TK"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CVEnumISMSCIControlsValues", namespace = "urn:us:gov:ic:cvenum:ism:scicontrols")
@XmlEnum
public enum CVEnumISMSCIControlsValues {


    /**
     * (U) ENDSEAL
     * 
     */
    EL("EL"),

    /**
     * (U) ECRU
     * 
     */
    @XmlEnumValue("EL-EU")
    EL_EU("EL-EU"),

    /**
     * (U) NONBOOK
     * 
     */
    @XmlEnumValue("EL-NK")
    EL_NK("EL-NK"),

    /**
     * (U) HCS
     * 
     */
    HCS("HCS"),

    /**
     * (U) HCS-O
     * 
     */
    @XmlEnumValue("HCS-O")
    HCS_O("HCS-O"),

    /**
     * (U) HCS-P
     * 
     */
    @XmlEnumValue("HCS-P")
    HCS_P("HCS-P"),

    /**
     * (U) KLONDIKE
     * 
     */
    KDK("KDK"),

    /**
     * (U) KDK BLUEFISH
     * 
     */
    @XmlEnumValue("KDK-BLFH")
    KDK_BLFH("KDK-BLFH"),

    /**
     * (U) KDK IDITAROD
     * 
     */
    @XmlEnumValue("KDK-IDIT")
    KDK_IDIT("KDK-IDIT"),

    /**
     * (U) KDK KANDIK
     * 
     */
    @XmlEnumValue("KDK-KAND")
    KDK_KAND("KDK-KAND"),

    /**
     * (U) RESERVE
     * 
     */
    RSV("RSV"),

    /**
     * (U) SPECIAL INTELLIGENCE
     * 
     */
    SI("SI"),

    /**
     * (U) SI-GAMMA
     * 
     */
    @XmlEnumValue("SI-G")
    SI_G("SI-G"),

    /**
     * (U) TALENT KEYHOLE
     * 
     */
    TK("TK");
    private final String value;

    CVEnumISMSCIControlsValues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CVEnumISMSCIControlsValues fromValue(String v) {
        for (CVEnumISMSCIControlsValues c: CVEnumISMSCIControlsValues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}