/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.03.21 at 10:56:51 AM CDT 
//


package org.apache.axis2.jaxws.description.xml.handler;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * This type is a general type that can be used to declare parameter/value lists.
 * <p/>
 * <p/>
 * <p/>
 * <p>Java class for param-valueType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="param-valueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://java.sun.com/xml/ns/javaee}descriptionType"
 * maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="param-name" type="{http://java.sun.com/xml/ns/javaee}string"/>
 *         &lt;element name="param-value" type="{http://java.sun.com/xml/ns/javaee}xsdStringType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "param-valueType", propOrder = {
        "description",
        "paramName",
        "paramValue"
        })
public class ParamValueType {

    @XmlElement(namespace = "http://java.sun.com/xml/ns/javaee", required = true)
    protected List<DescriptionType> description;
    @XmlElement(name = "param-name", namespace = "http://java.sun.com/xml/ns/javaee",
                required = true)
    protected org.apache.axis2.jaxws.description.xml.handler.String paramName;
    @XmlElement(name = "param-value", namespace = "http://java.sun.com/xml/ns/javaee",
                required = true)
    protected XsdStringType paramValue;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected java.lang.String id;

    /**
     * Gets the value of the description property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the description property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list {@link DescriptionType }
     */
    public List<DescriptionType> getDescription() {
        if (description == null) {
            description = new ArrayList<DescriptionType>();
        }
        return this.description;
    }

    /**
     * Gets the value of the paramName property.
     *
     * @return possible object is {@link String }
     */
    public org.apache.axis2.jaxws.description.xml.handler.String getParamName() {
        return paramName;
    }

    /**
     * Sets the value of the paramName property.
     *
     * @param value allowed object is {@link String
     *              }
     */
    public void setParamName(org.apache.axis2.jaxws.description.xml.handler.String value) {
        this.paramName = value;
    }

    /**
     * Gets the value of the paramValue property.
     *
     * @return possible object is {@link XsdStringType }
     */
    public XsdStringType getParamValue() {
        return paramValue;
    }

    /**
     * Sets the value of the paramValue property.
     *
     * @param value allowed object is {@link XsdStringType }
     */
    public void setParamValue(XsdStringType value) {
        this.paramValue = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is {@link java.lang.String }
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is {@link java.lang.String }
     */
    public void setId(java.lang.String value) {
        this.id = value;
    }

}
