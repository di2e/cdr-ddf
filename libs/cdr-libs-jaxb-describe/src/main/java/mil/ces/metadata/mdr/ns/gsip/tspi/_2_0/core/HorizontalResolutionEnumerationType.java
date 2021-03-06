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
 * <p>Java class for HorizontalResolutionEnumerationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="HorizontalResolutionEnumerationType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="arcDegree"/&gt;
 *     &lt;enumeration value="arcDecidegree"/&gt;
 *     &lt;enumeration value="arcCentidegree"/&gt;
 *     &lt;enumeration value="arcMillidegree"/&gt;
 *     &lt;enumeration value="arcDeciMillidegree"/&gt;
 *     &lt;enumeration value="arcCentiMillidegree"/&gt;
 *     &lt;enumeration value="arcMicrodegree"/&gt;
 *     &lt;enumeration value="arcMinute"/&gt;
 *     &lt;enumeration value="arcDeciminute"/&gt;
 *     &lt;enumeration value="arcCentiminute"/&gt;
 *     &lt;enumeration value="arcMilliminute"/&gt;
 *     &lt;enumeration value="arcDeciMilliminute"/&gt;
 *     &lt;enumeration value="arcSecond"/&gt;
 *     &lt;enumeration value="arcDecisecond"/&gt;
 *     &lt;enumeration value="arcCentisecond"/&gt;
 *     &lt;enumeration value="arcMillisecond"/&gt;
 *     &lt;enumeration value="arcDegree20Bit"/&gt;
 *     &lt;enumeration value="arcDegree21Bit"/&gt;
 *     &lt;enumeration value="arcDegree23Bit"/&gt;
 *     &lt;enumeration value="arcDegree25Bit"/&gt;
 *     &lt;enumeration value="arcDegree31Bit"/&gt;
 *     &lt;enumeration value="hectokilometre"/&gt;
 *     &lt;enumeration value="decakilometre"/&gt;
 *     &lt;enumeration value="kilometre"/&gt;
 *     &lt;enumeration value="hectometre"/&gt;
 *     &lt;enumeration value="decametre"/&gt;
 *     &lt;enumeration value="metre"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "HorizontalResolutionEnumerationType")
@XmlEnum
public enum HorizontalResolutionEnumerationType {


    /**
     * Geodetic Arc Degree: The spatial position is horizontally resolved to the nearest arc degree. [desc] An arc degree is a maximum of 111,694 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcDegree")
    ARC_DEGREE("arcDegree"),

    /**
     * Geodetic Arc Decidegree: The spatial position is horizontally resolved to the nearest tenth of an arc degree. [desc] A tenth of an arc degree is a maximum of 11,169.4 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcDecidegree")
    ARC_DECIDEGREE("arcDecidegree"),

    /**
     * Geodetic Arc Centidegree: The spatial position is horizontally resolved to the nearest hundredth of an arc degree. [desc] A hundredth of an arc degree is a maximum of 1,116.94 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcCentidegree")
    ARC_CENTIDEGREE("arcCentidegree"),

    /**
     * Geodetic Arc Millidegree: The spatial position is horizontally resolved to the nearest thousandth of an arc degree. [desc] A thousandth of an arc degree is a maximum of 111.694 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcMillidegree")
    ARC_MILLIDEGREE("arcMillidegree"),

    /**
     * Geodetic Arc Decimillidegree: The spatial position is horizontally resolved to the nearest ten-thousandth of an arc degree. [desc] A ten-thousandth of an arc degree is a maximum of 11.1694 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcDeciMillidegree")
    ARC_DECI_MILLIDEGREE("arcDeciMillidegree"),

    /**
     * Geodetic Arc Centimillidegree: The spatial position is horizontally resolved to the nearest hundred-thousandth of an arc degree. [desc] A hundred-thousandth of an arc degree is a maximum of 1.11694 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcCentiMillidegree")
    ARC_CENTI_MILLIDEGREE("arcCentiMillidegree"),

    /**
     * Geodetic Arc Microdegree: The spatial position is horizontally resolved to the nearest millionth of an arc degree. [desc] A millionth of an arc degree is a maximum of 0.111694 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcMicrodegree")
    ARC_MICRODEGREE("arcMicrodegree"),

    /**
     * Geodetic Arc Minute: The spatial position is horizontally resolved to the nearest arc minute. [desc] An arc minute is a maximum of 1,855.3 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcMinute")
    ARC_MINUTE("arcMinute"),

    /**
     * Geodetic Arc Deciminute: The spatial position is horizontally resolved to the nearest tenth of an arc minute. [desc] A tenth of an arc minute is a maximum of 185.53 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcDeciminute")
    ARC_DECIMINUTE("arcDeciminute"),

    /**
     * Geodetic Arc Centiminute: The spatial position is horizontally resolved to the nearest hundredth of an arc minute. [desc] A hundredth of an arc minute is a maximum of 18.553 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcCentiminute")
    ARC_CENTIMINUTE("arcCentiminute"),

    /**
     * Geodetic Arc Milliminute: The spatial position is horizontally resolved to the nearest thousandth of an arc minute. [desc] A thousandth of an arc minute is a maximum of 1.8553 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcMilliminute")
    ARC_MILLIMINUTE("arcMilliminute"),

    /**
     * Geodetic Arc Decimilliminute: The spatial position is horizontally resolved to the nearest ten-thousandth of an arc minute. [desc] A ten-thousandth of an arc minute is a maximum of 0.18553 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcDeciMilliminute")
    ARC_DECI_MILLIMINUTE("arcDeciMilliminute"),

    /**
     * Geodetic Arc Second: The spatial position is horizontally resolved to the nearest arc second. [desc] An arc second is a maximum of 30.92 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcSecond")
    ARC_SECOND("arcSecond"),

    /**
     * Geodetic Arc Decisecond: The spatial position is horizontally resolved to the nearest tenth of an arc second. [desc] A tenth of an arc second is a maximum of 3.092 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcDecisecond")
    ARC_DECISECOND("arcDecisecond"),

    /**
     * Geodetic Arc Centisecond: The spatial position is horizontally resolved to the nearest hundredth of an arc second. [desc] A hundredth of an arc second is a maximum of 0.3092 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcCentisecond")
    ARC_CENTISECOND("arcCentisecond"),

    /**
     * Geodetic Arc Millisecond: The spatial position is horizontally resolved to the nearest thousandth of an arc second. [desc] A thousandth of an arc second is a maximum of 0.03092 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcMillisecond")
    ARC_MILLISECOND("arcMillisecond"),

    /**
     * Geodetic Arc Degree 20-bit: The spatial position is horizontally resolved to the nearest 90/524,287 arc degree (approximately 0.618 arc seconds). [desc] This corresponds to a maximum of 19.1 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcDegree20Bit")
    ARC_DEGREE_20_BIT("arcDegree20Bit"),

    /**
     * Geodetic Arc Degree 21-bit: The spatial position is horizontally resolved to the nearest 90/1,048,575 arc degree (approximately 0.309 arc seconds). [desc] This corresponds to a maximum of 9.55 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcDegree21Bit")
    ARC_DEGREE_21_BIT("arcDegree21Bit"),

    /**
     * Geodetic Arc Degree 23-bit: The spatial position is horizontally resolved to the nearest 90/4,194,303 arc degree (approximately 0.0772 arc seconds). [desc] This corresponds to a maximum of 2.39 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcDegree23Bit")
    ARC_DEGREE_23_BIT("arcDegree23Bit"),

    /**
     * Geodetic Arc Degree 25-bit: The spatial position is horizontally resolved to the nearest 90/16,777,215 arc degree (approximately 0.0193 arc seconds). [desc] This corresponds to a maximum of 0.597 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcDegree25Bit")
    ARC_DEGREE_25_BIT("arcDegree25Bit"),

    /**
     * Geodetic Arc Degree 31-bit: The spatial position is horizontally resolved to the nearest 90/1,073,741,823 arc degree (approximately 0.000302 arc seconds). [desc] This corresponds to a maximum of 0.00933 metres in length on the WGS84 ellipsoid.
     * 
     */
    @XmlEnumValue("arcDegree31Bit")
    ARC_DEGREE_31_BIT("arcDegree31Bit"),

    /**
     * Hectokilometre: The spatial position is horizontally resolved to the nearest hundred kilometres.
     * 
     */
    @XmlEnumValue("hectokilometre")
    HECTOKILOMETRE("hectokilometre"),

    /**
     * Decakilometre: The spatial position is horizontally resolved to the nearest ten kilometres.
     * 
     */
    @XmlEnumValue("decakilometre")
    DECAKILOMETRE("decakilometre"),

    /**
     * Kilometre: The spatial position is horizontally resolved to the nearest kilometre.
     * 
     */
    @XmlEnumValue("kilometre")
    KILOMETRE("kilometre"),

    /**
     * Hectometre: The spatial position is horizontally resolved to the nearest hundred metres.
     * 
     */
    @XmlEnumValue("hectometre")
    HECTOMETRE("hectometre"),

    /**
     * Decametre: The spatial position is horizontally resolved to the nearest ten metres.
     * 
     */
    @XmlEnumValue("decametre")
    DECAMETRE("decametre"),

    /**
     * Metre: The spatial position is horizontally resolved to the nearest metre.
     * 
     */
    @XmlEnumValue("metre")
    METRE("metre");
    private final String value;

    HorizontalResolutionEnumerationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HorizontalResolutionEnumerationType fromValue(String v) {
        for (HorizontalResolutionEnumerationType c: HorizontalResolutionEnumerationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
