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
 * The message-destination-ref element contains a declaration of Deployment Component's reference to
 * a message destination associated with a resource in Deployment Component's environment. It
 * consists of:
 * <p/>
 * - an optional description - the message destination reference name - an optional message
 * destination type - an optional specification as to whether the destination is used for consuming
 * or producing messages, or both. if not specified, "both" is assumed. - an optional link to the
 * message destination - optional injection targets
 * <p/>
 * The message destination type must be supplied unless an injection target is specified, in which
 * case the type of the target is used.  If both are specified, the type must be assignment
 * compatible with the type of the injection target.
 * <p/>
 * Examples:
 * <p/>
 * <message-destination-ref> <message-destination-ref-name>jms/StockQueue
 * </message-destination-ref-name> <message-destination-type>javax.jms.Queue
 * </message-destination-type> <message-destination-usage>Consumes </message-destination-usage>
 * <message-destination-link>CorporateStocks </message-destination-link> </message-destination-ref>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p>Java class for message-destination-refType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="message-destination-refType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://java.sun.com/xml/ns/javaee}descriptionType"
 * maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="message-destination-ref-name" type="{http://java.sun.com/xml/ns/javaee}jndi-nameType"/>
 *         &lt;element name="message-destination-type" type="{http://java.sun.com/xml/ns/javaee}message-destination-typeType"
 * minOccurs="0"/>
 *         &lt;element name="message-destination-usage" type="{http://java.sun.com/xml/ns/javaee}message-destination-usageType"
 * minOccurs="0"/>
 *         &lt;element name="message-destination-link" type="{http://java.sun.com/xml/ns/javaee}message-destination-linkType"
 * minOccurs="0"/>
 *         &lt;group ref="{http://java.sun.com/xml/ns/javaee}resourceGroup"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "message-destination-refType", propOrder = {
        "description",
        "messageDestinationRefName",
        "messageDestinationType",
        "messageDestinationUsage",
        "messageDestinationLink",
        "mappedName",
        "injectionTarget"
        })
public class MessageDestinationRefType {

    @XmlElement(namespace = "http://java.sun.com/xml/ns/javaee", required = true)
    protected List<DescriptionType> description;
    @XmlElement(name = "message-destination-ref-name",
                namespace = "http://java.sun.com/xml/ns/javaee", required = true)
    protected JndiNameType messageDestinationRefName;
    @XmlElement(name = "message-destination-type", namespace = "http://java.sun.com/xml/ns/javaee")
    protected MessageDestinationTypeType messageDestinationType;
    @XmlElement(name = "message-destination-usage", namespace = "http://java.sun.com/xml/ns/javaee")
    protected MessageDestinationUsageType messageDestinationUsage;
    @XmlElement(name = "message-destination-link", namespace = "http://java.sun.com/xml/ns/javaee")
    protected MessageDestinationLinkType messageDestinationLink;
    @XmlElement(name = "mapped-name", namespace = "http://java.sun.com/xml/ns/javaee")
    protected XsdStringType mappedName;
    @XmlElement(name = "injection-target", namespace = "http://java.sun.com/xml/ns/javaee",
                required = true)
    protected List<InjectionTargetType> injectionTarget;
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
     * Gets the value of the messageDestinationRefName property.
     *
     * @return possible object is {@link JndiNameType }
     */
    public JndiNameType getMessageDestinationRefName() {
        return messageDestinationRefName;
    }

    /**
     * Sets the value of the messageDestinationRefName property.
     *
     * @param value allowed object is {@link JndiNameType }
     */
    public void setMessageDestinationRefName(JndiNameType value) {
        this.messageDestinationRefName = value;
    }

    /**
     * Gets the value of the messageDestinationType property.
     *
     * @return possible object is {@link MessageDestinationTypeType }
     */
    public MessageDestinationTypeType getMessageDestinationType() {
        return messageDestinationType;
    }

    /**
     * Sets the value of the messageDestinationType property.
     *
     * @param value allowed object is {@link MessageDestinationTypeType }
     */
    public void setMessageDestinationType(MessageDestinationTypeType value) {
        this.messageDestinationType = value;
    }

    /**
     * Gets the value of the messageDestinationUsage property.
     *
     * @return possible object is {@link MessageDestinationUsageType }
     */
    public MessageDestinationUsageType getMessageDestinationUsage() {
        return messageDestinationUsage;
    }

    /**
     * Sets the value of the messageDestinationUsage property.
     *
     * @param value allowed object is {@link MessageDestinationUsageType }
     */
    public void setMessageDestinationUsage(MessageDestinationUsageType value) {
        this.messageDestinationUsage = value;
    }

    /**
     * Gets the value of the messageDestinationLink property.
     *
     * @return possible object is {@link MessageDestinationLinkType }
     */
    public MessageDestinationLinkType getMessageDestinationLink() {
        return messageDestinationLink;
    }

    /**
     * Sets the value of the messageDestinationLink property.
     *
     * @param value allowed object is {@link MessageDestinationLinkType }
     */
    public void setMessageDestinationLink(MessageDestinationLinkType value) {
        this.messageDestinationLink = value;
    }

    /**
     * Gets the value of the mappedName property.
     *
     * @return possible object is {@link XsdStringType }
     */
    public XsdStringType getMappedName() {
        return mappedName;
    }

    /**
     * Sets the value of the mappedName property.
     *
     * @param value allowed object is {@link XsdStringType }
     */
    public void setMappedName(XsdStringType value) {
        this.mappedName = value;
    }

    /**
     * Gets the value of the injectionTarget property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the injectionTarget property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInjectionTarget().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list {@link InjectionTargetType }
     */
    public List<InjectionTargetType> getInjectionTarget() {
        if (injectionTarget == null) {
            injectionTarget = new ArrayList<InjectionTargetType>();
        }
        return this.injectionTarget;
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
