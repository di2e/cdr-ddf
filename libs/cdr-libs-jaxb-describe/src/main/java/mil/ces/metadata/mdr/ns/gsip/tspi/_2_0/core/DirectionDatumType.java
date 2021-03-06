//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 04:06:37 PM EDT 
//


package mil.ces.metadata.mdr.ns.gsip.tspi._2_0.core;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DirectionDatumType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DirectionDatumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="geodetic"/&gt;
 *     &lt;enumeration value="magnetic"/&gt;
 *     &lt;enumeration value="militaryGrid"/&gt;
 *     &lt;enumeration value="relative"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DirectionDatumType")
@XmlEnum
public enum DirectionDatumType {


    /**
     * Geodetic: Referenced to the direction of the geographic North Pole.
     * 
     */
    @XmlEnumValue("geodetic")
    GEODETIC("geodetic"),

    /**
     * Magnetic: Referenced to the direction of the magnetic North Pole.
     * 
     */
    @XmlEnumValue("magnetic")
    MAGNETIC("magnetic"),

    /**
     * Military Grid: Referenced to the direction of "north" in a military grid established by a suitable map-projection, for example Universal Transverse Mercator (UTM).
     * 
     */
    @XmlEnumValue("militaryGrid")
    MILITARY_GRID("militaryGrid"),

    /**
     * Relative; Referenced to a direction established by the context of use (for example: the heading of a vessel).
     * 
     */
    @XmlEnumValue("relative")
    RELATIVE("relative");
    private final String value;

    DirectionDatumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DirectionDatumType fromValue(String v) {
        for (DirectionDatumType c: DirectionDatumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
