
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

package org.apache.axis2.jaxws.sample.addnumbershandler;

import org.test.addnumbershandler.AddNumbersHandlerResponse;

import jakarta.jws.Oneway;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.AsyncHandler;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import java.util.concurrent.Future;



/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.0_01-b15-fcs
 * Generated source version: 2.0
 * 
 */
@WebService(name = "AddNumbersHandlerPortType", targetNamespace = "http://org/test/addnumbershandler")
//@HandlerChain(file="META-INF/AddNumbersClientHandlers.xml", name="")
public interface AddNumbersHandlerPortType {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     * @throws AddNumbersHandlerFault_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "http://org/test/addnumbershandler")
    @RequestWrapper(localName = "addNumbersHandler", targetNamespace = "http://org/test/addnumbershandler", className = "org.test.addnumbershandler.AddNumbersHandler")
    @ResponseWrapper(localName = "addNumbersHandlerResponse", targetNamespace = "http://org/test/addnumbershandler", className = "org.test.addnumbershandler.AddNumbersHandlerResponse")
    public int addNumbersHandler(
        @WebParam(name = "arg0", targetNamespace = "http://org/test/addnumbershandler")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "http://org/test/addnumbershandler")
        int arg1)
        throws AddNumbersHandlerFault_Exception
    ;

    /**
     * 
     * @param asyncHandler
     * @param arg0
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod
    @RequestWrapper(localName = "addNumbersHandler", targetNamespace = "http://org/test/addnumbershandler", className = "org.test.addnumbershandler.AddNumbersHandler")
    @ResponseWrapper(localName = "addNumbersHandlerResponse", targetNamespace = "http://org/test/addnumbershandler", className = "org.test.addnumbershandler.AddNumbersHandlerResponse")
    public Future<?> addNumbersHandlerAsync(
            @WebParam(name = "arg0", targetNamespace = "http://org/test/addnumbershandler")
            int arg0,
            @WebParam(name = "arg1", targetNamespace = "http://org/test/addnumbershandler")
            int arg1,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<AddNumbersHandlerResponse> asyncHandler);

    
    /**
     * 
     * @param arg0
     */
    @WebMethod
    @Oneway
    @RequestWrapper(localName = "oneWayInt", targetNamespace = "http://org/test/addnumbershandler", className = "org.test.addnumbershandler.OneWayInt")
    public void oneWayInt(
        @WebParam(name = "arg0", targetNamespace = "http://org/test/addnumbershandler")
        int arg0);

}
