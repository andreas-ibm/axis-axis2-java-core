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

package org.apache.axis2.jaxws.provider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import javax.xml.namespace.QName;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.AsyncHandler;
import jakarta.xml.ws.Binding;
import jakarta.xml.ws.Response; 
import jakarta.xml.ws.Service;
import jakarta.xml.ws.soap.SOAPBinding;

import org.apache.axis2.jaxws.framework.ClientConfigurationContextBinder;
import org.apache.axis2.testutils.Axis2Server;
import org.junit.ClassRule;
import org.junit.Test;
import org.xmlunit.assertj3.XmlAssert;

import jakarta.xml.ws.handler.Handler;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import static org.apache.axis2.jaxws.framework.TestUtils.await;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * Tests Dispatch<SOAPMessage> client and a Provider<SOAPMessage> service
 * with mustUnderstand attribute header.
 */
public class SoapMessageMUProviderTests {
    @ClassRule
    public static final Axis2Server server = new Axis2Server("target/repo");

    @ClassRule
    public static final ClientConfigurationContextBinder binder = new ClientConfigurationContextBinder("target/client-repo");

    public static final QName serviceName =
            new QName("http://ws.apache.org/axis2", "SoapMessageMUProviderService");
    public static final QName portName =
            new QName("http://ws.apache.org/axis2", "SimpleProviderServiceSOAP11port0");

    public static final String bindingID = SOAPBinding.SOAP11HTTP_BINDING;
    public static final Service.Mode mode = Service.Mode.MESSAGE;

    private static String getEndpointUrl() throws Exception {
        return server.getEndpoint("SoapMessageMUProviderService.SimpleProviderServiceSOAP11port0");
    }

    /**
     * Test soap message with no MU headers
     */
    @Test
    public void testNoMustUnderstandHeaders() throws Exception {
        System.out.println("testNoMustUnderstandHeaders");
        // create a service
        Service svc = Service.create(serviceName);
        svc.addPort(portName, bindingID, getEndpointUrl());

        jakarta.xml.ws.Dispatch<SOAPMessage> dispatch = null;
        dispatch = svc.createDispatch(portName, SOAPMessage.class, mode);

        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_USE_PROPERTY, true);
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_URI_PROPERTY, "echoString");

        SOAPMessage message = AttachmentUtil.toSOAPMessage(AttachmentUtil.msgEnvPlain);

        SOAPMessage response = dispatch.invoke(message);

        XmlAssert.assertThat(response.getSOAPPart().getContent()).and(AttachmentUtil.msgEnvPlain)
                .areIdentical();
            
        // Try a second time
        response = dispatch.invoke(message);

        XmlAssert.assertThat(response.getSOAPPart().getContent()).and(AttachmentUtil.msgEnvPlain)
                .areIdentical();
    }

    /**
     * Test the mustUnderstand soap header attribute on the client's
     * outbound soap message for headers that are not understood.  Should cause an 
     * exception.
     */
    @Test
    public void testClientRequestNotUnderstoodHeaders() throws Exception {
        System.out.println("testClientRequestNotUnderstoodHeaders");
        // create a service
        Service svc = Service.create(serviceName);
        svc.addPort(portName, bindingID, getEndpointUrl());

        jakarta.xml.ws.Dispatch<SOAPMessage> dispatch = null;
        dispatch = svc.createDispatch(portName, SOAPMessage.class, mode);

        //force SOAPAction to match with wsdl action                        
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_USE_PROPERTY, true);
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_URI_PROPERTY, "echoString");

        SOAPMessage message = AttachmentUtil.toSOAPMessage(AttachmentUtil.msgEnvMU);

        try {
            dispatch.invoke(message);
            fail("Should have received fault for not understood headers on request");
        } catch (Exception e) {
            // Expected path
        }
        
        // Try a second time
        try {
            dispatch.invoke(message);
            fail("Should have received fault for not understood headers on request");
        } catch (Exception e) {
            // Expected path
        }
    }

    /**
     * Test the mustUnderstand soap header attribute on the server's
     * outbound soap message (i.e. the inbound response to the client) for headers that
     * are not understood.  Should cause an exception.
     */
    @Test
    public void testClientResponseNotUnderstoodHeaders() throws Exception {
        System.out.println("testClientResponseNotUnderstoodHeaders");
        // create a service
        Service svc = Service.create(serviceName);
        svc.addPort(portName, bindingID, getEndpointUrl());

        jakarta.xml.ws.Dispatch<SOAPMessage> dispatch = null;
        dispatch = svc.createDispatch(portName, SOAPMessage.class, mode);

        //force SOAPAction to match with wsdl action                        
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_USE_PROPERTY, true);
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_URI_PROPERTY, "echoString");

        SOAPMessage message = AttachmentUtil.toSOAPMessage(AttachmentUtil.msgEnv);

        try {
            dispatch.invoke(message);
            fail("Should have received fault for not understood headers on response");
        } catch (Exception e) {
            // Expected path
        }
        
        // Try a second time
        try {
            dispatch.invoke(message);
            fail("Should have received fault for not understood headers on response");
        } catch (Exception e) {
            // Expected path
        }
    }

    /**
     * Test the mustUnderstand soap header attribute on the client's
     * outbound soap message for headers that should be understood.  Should not cause an 
     * exception.
     */
    @Test
    public void testClientRequestUnderstoodHeaders() throws Exception {
        System.out.println("testClientRequestUnderstoodHeaders");
        // create a service
        Service svc = Service.create(serviceName);
        svc.addPort(portName, bindingID, getEndpointUrl());

        jakarta.xml.ws.Dispatch<SOAPMessage> dispatch = null;
        dispatch = svc.createDispatch(portName, SOAPMessage.class, mode);

        //force SOAPAction to match with wsdl action                        
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_USE_PROPERTY, true);
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_URI_PROPERTY, "echoString");

        SOAPMessage message = AttachmentUtil.toSOAPMessage(AttachmentUtil.msgEnvMU_understood);

        dispatch.invoke(message);

        // Try a second time
        dispatch.invoke(message);
    }

    /**
     * Test the mustUnderstand soap header attribute on the server's
     * outbound soap message (i.e. the inbound response to the client) for headers that
     * are understood.  Should not cause an exception.
     */
    @Test
    public void testClientResponseUnderstoodHeaders() throws Exception {
        System.out.println("testClientResponseUnderstoodHeaders");
        // create a service
        Service svc = Service.create(serviceName);
        svc.addPort(portName, bindingID, getEndpointUrl());

        jakarta.xml.ws.Dispatch<SOAPMessage> dispatch = null;
        dispatch = svc.createDispatch(portName, SOAPMessage.class, mode);

        //force SOAPAction to match with wsdl action                        
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_USE_PROPERTY, true);
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_URI_PROPERTY, "echoString");

        SOAPMessage message = AttachmentUtil.toSOAPMessage(AttachmentUtil.msgEnv_understood);

        dispatch.invoke(message);

        // Try a second time
        dispatch.invoke(message);
    }
    /**
     * Test the mustUnderstand soap header attribute on the server's
     * outbound soap message using multiple handlers.  The response contains a header that 
     * should be understood by the JAX-WS application handler, so there should be no exception.
     * Tests that multiple handlers do not cause a collision when registering headers they 
     * understand.
     */
    @Test
    public void testClientResponseHandlerUnderstoodHeaders2() throws Exception {
        System.out.println("testClientResponseTwoHandlerUnderstoodHeaders2");

        // create a service
        Service svc = Service.create(serviceName);
        svc.addPort(portName, bindingID, getEndpointUrl());

        jakarta.xml.ws.Dispatch<SOAPMessage> dispatch = null;
        dispatch = svc.createDispatch(portName, SOAPMessage.class, mode);

        //force SOAPAction to match with wsdl action                        
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_USE_PROPERTY, true);
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_URI_PROPERTY, "echoString");
        // set handler chain for binding provider to add the handler that will
        // understand the mU header
        Binding binding = ((BindingProvider) dispatch).getBinding();

        // create a new list or use the existing one
        List<Handler> handlers = binding.getHandlerChain();
    
        if (handlers == null) {
            handlers = new ArrayList<Handler>();
        }
        handlers.add(new MustUnderstandClientHandler2());
        handlers.add(new MustUnderstandClientHandler());
        binding.setHandlerChain(handlers);

        SOAPMessage message = AttachmentUtil.toSOAPMessage(AttachmentUtil.msgEnv2);
        
        try {
	    System.out.println("testClientResponseHandlerUnderstoodHeaders2 on SOAPMessage header: " + message.getSOAPPart().getEnvelope().getHeader());
            SOAPMessage response = dispatch.invoke(message);
            assertThat(response).isNotNull();
            String responseString = AttachmentUtil.toString(response);
            assertThat(responseString).isNotNull();
        } catch (Exception e) {
	    System.out.println("testClientResponseHandlerUnderstoodHeaders2 on error: ");
	    e.printStackTrace();
            fail("Should not have caught an exception: " + e.toString());
        }
        
    }
    
    /**
     * Test that not-understood mustUnderstand headers cause an exception in the async polling
     * case.
     */
    @Test
    public void testClientResponseNotUnderstoodHeadersAsyncPolling() throws Exception {
        System.out.println("testClientResponseNotUnderstoodHeadersAsyncPolling");

        // create a service
        Service svc = Service.create(serviceName);
        svc.addPort(portName, bindingID, getEndpointUrl());

        jakarta.xml.ws.Dispatch<SOAPMessage> dispatch = null;
        dispatch = svc.createDispatch(portName, SOAPMessage.class, mode);

        //force SOAPAction to match with wsdl action                        
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_USE_PROPERTY, true);
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_URI_PROPERTY, "echoString");

        SOAPMessage message = AttachmentUtil.toSOAPMessage(AttachmentUtil.msgEnv);
        
        Response<SOAPMessage> asyncResponse = null;
        try {
            asyncResponse = dispatch.invokeAsync(message);
            assertThat(asyncResponse).isNotNull();
        } catch (Exception e) {
            fail("Should not have caught an exception on the async invocation: " + e.toString());
        }
        
        try {
            await(asyncResponse);
            SOAPMessage response = asyncResponse.get();
            fail("Should have caught a mustUnderstand exception");
        } catch (Exception e) {
            // Expected path
            assertThat(e.getCause().toString()).contains(
                    "Must Understand check failed for header http://ws.apache.org/axis2 : muserver");
        }
        
    }
    
    /**
     * Test that JAX-WS handlers can register they processes certain mustUnderstand headers in the 
     * async polling case.
     */
    @Test
    public void testClientResponseHandlerUnderstoodHeadersAsyncPolling() throws Exception {
        System.out.println("testClientResponseHandlerUnderstoodHeadersAsyncPolling");

        // create a service
        Service svc = Service.create(serviceName);
        svc.addPort(portName, bindingID, getEndpointUrl());

        jakarta.xml.ws.Dispatch<SOAPMessage> dispatch = null;
        dispatch = svc.createDispatch(portName, SOAPMessage.class, mode);

        //force SOAPAction to match with wsdl action                        
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_USE_PROPERTY, true);
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_URI_PROPERTY, "echoString");
        // set handler chain for binding provider to add the handler that will
        // understand the mU header
        Binding binding = ((BindingProvider) dispatch).getBinding();

        // create a new list or use the existing one
        List<Handler> handlers = binding.getHandlerChain();
        if (handlers == null) {
            handlers = new ArrayList<Handler>();
        }
        handlers.add(new MustUnderstandClientHandler());
        binding.setHandlerChain(handlers);

        SOAPMessage message = AttachmentUtil.toSOAPMessage(AttachmentUtil.msgEnv);
        
        try {
            Response<SOAPMessage> asyncResponse = dispatch.invokeAsync(message);
            assertThat(asyncResponse).isNotNull();
            await(asyncResponse);
            SOAPMessage response = asyncResponse.get();
            assertThat(response).isNotNull();
            String responseString = AttachmentUtil.toString(response);
            assertThat(responseString).isNotNull();
        } catch (Exception e) {
            fail("Should not have caught an exception: " + e.toString());
        }
        
    }
    
    /**
     * Test that not-understood mustUnderstand headers cause an exception in the async callback
     * case.
     */
    @Test
    public void testClientResponseNotUnderstoodHeadersAsyncCallback() throws Exception {
        System.out.println("testClientResponseNotUnderstoodHeadersAsyncCallback");

        // create a service
        Service svc = Service.create(serviceName);
        svc.addPort(portName, bindingID, getEndpointUrl());

        jakarta.xml.ws.Dispatch<SOAPMessage> dispatch = null;
        dispatch = svc.createDispatch(portName, SOAPMessage.class, mode);

        //force SOAPAction to match with wsdl action                        
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_USE_PROPERTY, true);
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_URI_PROPERTY, "echoString");

        SOAPMessage message = AttachmentUtil.toSOAPMessage(AttachmentUtil.msgEnv);
        
        Future<?> asyncResponse = null;
        AsyncCallback<SOAPMessage> callback = new AsyncCallback<SOAPMessage>();
        try {
            asyncResponse = dispatch.invokeAsync(message, callback);
            assertThat(asyncResponse).isNotNull();
        } catch (Exception e) {
            fail("Should not have caught an exception on the async invocation: " + e.toString());
        }
        
        try {
            await(asyncResponse);
            assertThat(callback.hasError()).isTrue();
            assertThat(
                    callback.getError().toString()).contains(
                            "Must Understand check failed for header http://ws.apache.org/axis2 : muserver");
        } catch (Exception e) {
            fail("Received unexpected exception: " + e.toString()); 
        }
        
    }

    /**
     * Test that JAX-WS handlers can register they processes certain mustUnderstand headers in the 
     * async callback case.
     */
    @Test
    public void testClientResponseUnderstoodHeadersAsyncCallback() throws Exception {
        System.out.println("testClientResponseUnderstoodHeadersAsyncCallback");

        // create a service
        Service svc = Service.create(serviceName);
        svc.addPort(portName, bindingID, getEndpointUrl());

        jakarta.xml.ws.Dispatch<SOAPMessage> dispatch = null;
        dispatch = svc.createDispatch(portName, SOAPMessage.class, mode);

        //force SOAPAction to match with wsdl action                        
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_USE_PROPERTY, true);
        ((BindingProvider) dispatch).getRequestContext()
                                    .put(BindingProvider.SOAPACTION_URI_PROPERTY, "echoString");

        // set handler chain for binding provider to add the handler that will
        // understand the mU header
        Binding binding = ((BindingProvider) dispatch).getBinding();

        // create a new list or use the existing one
        List<Handler> handlers = binding.getHandlerChain();
        if (handlers == null) {
            handlers = new ArrayList<Handler>();
        }
        handlers.add(new MustUnderstandClientHandler());
        binding.setHandlerChain(handlers);
        
        SOAPMessage message = AttachmentUtil.toSOAPMessage(AttachmentUtil.msgEnv);
        
        Future<?> asyncResponse = null;
        AsyncCallback<SOAPMessage> callback = new AsyncCallback<SOAPMessage>();
        try {
            asyncResponse = dispatch.invokeAsync(message, callback);
            assertThat(asyncResponse).isNotNull();
        } catch (Exception e) {
            fail("Should not have caught an exception on the async invocation: " + e.toString());
        }
        
        try {
            await(asyncResponse);
            assertThat(callback.hasError()).isFalse();
            SOAPMessage response = callback.getValue();
            assertThat(response).isNotNull();
        } catch (Exception e) {
            fail("Received unexpected exception" + e.toString());
        }
        
    }
    // ============================================================================================
    // Test handlers and test classes
    // ============================================================================================

    class MustUnderstandClientHandler implements
    jakarta.xml.ws.handler.soap.SOAPHandler<SOAPMessageContext> {

        public Set<QName> getHeaders() {
            // Understand the header {http://ws.apache.org/axis2}muserver that will be sent back
            // by the server; the other headers are just to test multiple entries in the Set
            Set<QName> result = new HashSet<QName>();
            result.add(new QName("http://ws.apache.org/axis2", "muserverAnother"));
            result.add(new QName("http://ws.apache.org/axis2", "muserver"));
            result.add(new QName("http://ws.apache.org/axis2", "muserverYetAnother"));
            return result;
        }

        public void close(MessageContext context) {
        }

        public boolean handleFault(SOAPMessageContext context) {
            return true;
        }

        public boolean handleMessage(SOAPMessageContext context) {
            return true;
        }
        
    }
    
    class MustUnderstandClientHandler2 implements
    jakarta.xml.ws.handler.soap.SOAPHandler<SOAPMessageContext> {

        public Set<QName> getHeaders() {
            // Understand the header {http://ws.apache.org/axis2}muserver2 that will be sent back
            // by the server; the other headers are just to test multiple entries in the Set
            Set<QName> result = new HashSet<QName>();
            // The first header is a collision with one understood by the other handler
            result.add(new QName("http://ws.apache.org/axis2", "muserverAnother"));
            result.add(new QName("http://ws.apache.org/axis2", "muserver2"));
            result.add(new QName("http://ws.apache.org/axis2", "muserverYetAnother2"));
            return result;
        }

        public void close(MessageContext context) {
        }

        public boolean handleFault(SOAPMessageContext context) {
            return true;
        }

        public boolean handleMessage(SOAPMessageContext context) {
            return true;
        }
    }
    
    class AsyncCallback<T> implements AsyncHandler<T> {

        private T value;
        private Throwable exception;

        public void handleResponse(Response<T> response) {
            try {
                value = response.get();
            } catch (Throwable t) {
                exception = t;
            }
        }

        public boolean hasError() {
            return (exception != null);
        }

        public Throwable getError() {
            return exception;
        }

        public T getValue() {
            return value;
        }
    }
    
}
