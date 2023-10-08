
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

package org.apache.axis2.jaxws.unitTest.echo;

import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.0-b26-ea3
 * Generated source version: 2.0
 * 
 */
@WebServiceClient(name = "EchoService", targetNamespace = "http://ws.apache.org/axis2/tests", wsdlLocation = "\\work\\apps\\eclipse\\workspace\\axis2-live\\modules\\jaxws\\test-resources\\wsdl\\WSDLTests.wsdl")
public class EchoService
    extends Service
{

    private final static URL WSDL_LOCATION;
    private final static QName ECHOSERVICE = new QName("http://ws.apache.org/axis2/tests", "EchoService");
    private final static QName ECHOPORT = new QName("http://ws.apache.org/axis2/tests", "EchoPort");

    static {
        URL url = null;
        try {
            url = new URL("file:/C:/work/apps/eclipse/workspace/axis2-live/modules/jaxws/test-resources/wsdl/WSDLTests.wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public EchoService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EchoService() {
        super(WSDL_LOCATION, ECHOSERVICE);
    }

    /**
     * 
     * @return
     *     returns EchoPort
     */
    @WebEndpoint(name = "EchoPort")
    public EchoPort getEchoPort() {
        return (EchoPort)super.getPort(ECHOPORT, EchoPort.class);
    }

}
