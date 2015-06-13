/**
 * Copyright (C) 2014 Cohesive Integrations, LLC (info@cohesiveintegrations.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.di2e.ecdr.commons.xml.osd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for Url complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="Url">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="template" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="indexOffset" type="{http://www.w3.org/2001/XMLSchema}int" default="1" />
 *       &lt;attribute name="pageOffset" type="{http://www.w3.org/2001/XMLSchema}int" default="1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "Url" )
public class Url {

    @XmlAttribute( name = "template", required = true )
    private String template;
    @XmlAttribute( name = "type", required = true )
    private String type;
    @XmlAttribute( name = "rel" )
    private String rel;
    @XmlAttribute( name = "indexOffset" )
    private Integer indexOffset;
    @XmlAttribute( name = "pageOffset" )
    private Integer pageOffset;

    /**
     * Gets the value of the template property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTemplate() {
        return template;
    }

    /**
     * Sets the value of the template property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTemplate( String value ) {
        this.template = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setType( String value ) {
        this.type = value;
    }

    /**
     * Gets the value of the rel property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRel() {
        return rel;
    }

    /**
     * Sets the value of the rel property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRel( String value ) {
        this.rel = value;
    }

    /**
     * Gets the value of the indexOffset property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public int getIndexOffset() {
        if ( indexOffset == null ) {
            return 1;
        } else {
            return indexOffset;
        }
    }

    /**
     * Sets the value of the indexOffset property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setIndexOffset( Integer value ) {
        this.indexOffset = value;
    }

    /**
     * Gets the value of the pageOffset property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public int getPageOffset() {
        if ( pageOffset == null ) {
            return 1;
        } else {
            return pageOffset;
        }
    }

    /**
     * Sets the value of the pageOffset property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setPageOffset( Integer value ) {
        this.pageOffset = value;
    }

}
