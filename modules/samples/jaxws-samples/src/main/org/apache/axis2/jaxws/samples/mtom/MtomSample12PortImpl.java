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

package org.apache.axis2.jaxws.samples.mtom;

@jakarta.jws.WebService(endpointInterface = "org.apache.axis2.jaxws.samples.mtom.MtomSample12",
        targetNamespace = "http://org/apache/axis2/jaxws/samples/mtom/",
        serviceName = "MtomSampleService12",
        portName = "MtomSamplePort",
        wsdlLocation = "WEB-INF/wsdl/ImageDepot12.wsdl")
@jakarta.xml.ws.BindingType(value = jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_MTOM_BINDING)
public class MtomSample12PortImpl {

    public ImageDepot sendImage(ImageDepot input) {
        System.out.println(">>MTOM SOAP 1.2 request received, type = " + input.getImageData().getContentType());
        return input;
    }
}