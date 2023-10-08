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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * The lifecycle-callback type specifies a method on a class to be called when a lifecycle event
 * occurs. Note that each class may have only one lifecycle callback method for any given event and
 * that the method may not be overloaded.
 * <p/>
 * If the lifefycle-callback-class element is missing then the class defining the callback is
 * assumed to be the component class in scope at the place in the descriptor in which the callback
 * definition appears.
 * <p/>
 * <p/>
 * <p/>
 * <p>Java class for lifecycle-callbackType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="lifecycle-callbackType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lifecycle-callback-class" type="{http://java.sun.com/xml/ns/javaee}fully-qualified-classType"
 * minOccurs="0"/>
 *         &lt;element name="lifecycle-callback-method" type="{http://java.sun.com/xml/ns/javaee}java-identifierType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lifecycle-callbackType", propOrder = {
        "lifecycleCallbackClass",
        "lifecycleCallbackMethod"
        })
public class LifecycleCallbackType {

    @XmlElement(name = "lifecycle-callback-class", namespace = "http://java.sun.com/xml/ns/javaee")
    protected FullyQualifiedClassType lifecycleCallbackClass;
    @XmlElement(name = "lifecycle-callback-method", namespace = "http://java.sun.com/xml/ns/javaee",
                required = true)
    protected JavaIdentifierType lifecycleCallbackMethod;

    /**
     * Gets the value of the lifecycleCallbackClass property.
     *
     * @return possible object is {@link FullyQualifiedClassType }
     */
    public FullyQualifiedClassType getLifecycleCallbackClass() {
        return lifecycleCallbackClass;
    }

    /**
     * Sets the value of the lifecycleCallbackClass property.
     *
     * @param value allowed object is {@link FullyQualifiedClassType }
     */
    public void setLifecycleCallbackClass(FullyQualifiedClassType value) {
        this.lifecycleCallbackClass = value;
    }

    /**
     * Gets the value of the lifecycleCallbackMethod property.
     *
     * @return possible object is {@link JavaIdentifierType }
     */
    public JavaIdentifierType getLifecycleCallbackMethod() {
        return lifecycleCallbackMethod;
    }

    /**
     * Sets the value of the lifecycleCallbackMethod property.
     *
     * @param value allowed object is {@link JavaIdentifierType }
     */
    public void setLifecycleCallbackMethod(JavaIdentifierType value) {
        this.lifecycleCallbackMethod = value;
    }

}
