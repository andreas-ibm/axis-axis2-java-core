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

package org.apache.axis2.jaxws.addressing.factory;

import jakarta.xml.bind.JAXBException;
import javax.xml.transform.Source;
import jakarta.xml.ws.EndpointReference;

/**
 * This class represents factories that can be used to generate instances of the
 * {@link EndpointReference}.
 */
public interface JAXWSEndpointReferenceFactory {
    /**
     * Create an instance of a supported subclass of <code>EndpointReference</code>.
     * 
     * @param eprInfoset the endpoint reference
     * @return an instance of <code>EndpointReference</code>.
     * @throws JAXBException
     */
    public EndpointReference createEndpointReference(Source eprInfoset) throws JAXBException;
    
    /**
     * Map the specified class to a supported WS-Addressing namespace.
     * 
     * @param <T> a subclass of <code>EndpointReference</code>.
     * @param clazz the class.
     * @return the WS-Addressing namespace that is associated with the class.
     */
    public <T extends EndpointReference> String getAddressingNamespace(Class<T> clazz);
}
