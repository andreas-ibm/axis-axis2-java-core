/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.axis2.soap.impl.dom;

import org.apache.axis2.om.OMConstants;
import org.apache.axis2.om.OMElement;
import org.apache.axis2.om.OMException;
import org.apache.axis2.om.OMNode;
import org.apache.axis2.om.OMXMLParserWrapper;
import org.apache.axis2.om.impl.dom.ElementImpl;
import org.apache.axis2.om.impl.dom.NamespaceImpl;
import org.apache.axis2.om.impl.dom.ParentNode;
import org.apache.axis2.om.impl.llom.OMSerializerUtil;
import org.apache.axis2.om.impl.llom.serialize.StreamWriterToContentHandlerConverter;
import org.apache.axis2.om.impl.OMNodeEx;
import org.apache.axis2.soap.SOAP12Constants;
import org.apache.axis2.soap.SOAPBody;
import org.apache.axis2.soap.SOAPConstants;
import org.apache.axis2.soap.SOAPFault;
import org.apache.axis2.soap.SOAPFaultCode;
import org.apache.axis2.soap.SOAPFaultDetail;
import org.apache.axis2.soap.SOAPFaultNode;
import org.apache.axis2.soap.SOAPFaultReason;
import org.apache.axis2.soap.SOAPFaultRole;
import org.apache.axis2.soap.SOAPProcessingException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

public abstract class SOAPFaultImpl extends SOAPElement implements SOAPFault,
		OMConstants {

	protected Exception e;
	/**
	 * Constructor SOAPFaultImpl
	 * 
	 * @param parent
	 * @param e
	 */
	public SOAPFaultImpl(SOAPBody parent, Exception e)
			throws SOAPProcessingException {
		super(parent, SOAPConstants.SOAPFAULT_LOCAL_NAME, true);
		setException(e);
	}

	public void setException(Exception e) {
		this.e = e;
		putExceptionToSOAPFault(e);
	}

	public SOAPFaultImpl(SOAPBody parent) throws SOAPProcessingException {
		super(parent, SOAPConstants.SOAPFAULT_LOCAL_NAME, true);
	}

	/**
	 * Constructor SOAPFaultImpl
	 * 
	 * @param parent
	 * @param builder
	 */
	public SOAPFaultImpl(SOAPBody parent, OMXMLParserWrapper builder) {
		super(parent, SOAPConstants.SOAPFAULT_LOCAL_NAME, builder);
	}

	protected abstract SOAPFaultDetail getNewSOAPFaultDetail(SOAPFault fault)
			throws SOAPProcessingException;

	// --------------- Getters and Settors --------------------------- //

	public void setCode(SOAPFaultCode soapFaultCode)
			throws SOAPProcessingException {
		setNewElement(getCode(), soapFaultCode);
	}

	public SOAPFaultCode getCode() {
		return (SOAPFaultCode) this
				.getChildWithName(SOAP12Constants.SOAP_FAULT_CODE_LOCAL_NAME);
	}

	public void setReason(SOAPFaultReason reason)
			throws SOAPProcessingException {
		setNewElement(getReason(), reason);
	}

	public SOAPFaultReason getReason() {
		return (SOAPFaultReason) this
				.getChildWithName(SOAP12Constants.SOAP_FAULT_REASON_LOCAL_NAME);
	}

	public void setNode(SOAPFaultNode node) throws SOAPProcessingException {
		setNewElement(getNode(), node);
	}

	public SOAPFaultNode getNode() {
		return (SOAPFaultNode) this
				.getChildWithName(SOAP12Constants.SOAP_FAULT_NODE_LOCAL_NAME);
	}

	public void setRole(SOAPFaultRole role) throws SOAPProcessingException {
		setNewElement(getRole(), role);
	}

	public SOAPFaultRole getRole() {
		return (SOAPFaultRoleImpl) this
				.getChildWithName(SOAP12Constants.SOAP_FAULT_ROLE_LOCAL_NAME);
	}

	public void setDetail(SOAPFaultDetail detail)
			throws SOAPProcessingException {
		setNewElement(getDetail(), detail);
	}

	public SOAPFaultDetail getDetail() {
		return (SOAPFaultDetail) this
				.getChildWithName(SOAP12Constants.SOAP_FAULT_DETAIL_LOCAL_NAME);
	}

	/**
	 * If exception detailElement is not there we will return null
	 */
	public Exception getException() throws OMException {
		SOAPFaultDetail detail = getDetail();
		if (detail == null) {
			return null;
		}

		OMElement exceptionElement = getDetail().getFirstChildWithName(
				new QName(SOAPConstants.SOAP_FAULT_DETAIL_EXCEPTION_ENTRY));
		if (exceptionElement != null && exceptionElement.getText() != null) {
			return new Exception(exceptionElement.getText());
		}
		return null;
	}

	protected void putExceptionToSOAPFault(Exception e)
			throws SOAPProcessingException {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		sw.flush();
		getDetail();
		if (getDetail() == null) {
			setDetail(getNewSOAPFaultDetail(this));

		}
		OMElement faultDetailEnty = new ElementImpl((ParentNode) this,
				SOAPConstants.SOAP_FAULT_DETAIL_EXCEPTION_ENTRY,
				(NamespaceImpl) this.namespace);
		faultDetailEnty.setText(sw.getBuffer().toString());
	}

	protected void setNewElement(OMElement myElement, OMElement newElement) {
		if (myElement != null) {
			myElement.discard();
		}
		if (newElement != null && newElement.getParent() != null) {
			newElement.discard();
		}
		this.addChild(newElement);
		myElement = newElement;
	}

	protected OMElement getChildWithName(String childName) {
		Iterator childrenIter = getChildren();
		while (childrenIter.hasNext()) {
			OMNode node = (OMNode) childrenIter.next();
			if (node.getType() == OMNode.ELEMENT_NODE
					&& childName.equals(((OMElement) node).getLocalName())) {
				return (OMElement) node;
			}
		}
		return null;
	}

	protected void serialize(org.apache.axis2.om.impl.OMOutputImpl omOutput,
			boolean cache) throws XMLStreamException {
		// select the builder
		short builderType = PULL_TYPE_BUILDER; // default is pull type
		if (builder != null) {
			builderType = this.builder.getBuilderType();
		}
		if ((builderType == PUSH_TYPE_BUILDER)
				&& (builder.getRegisteredContentHandler() == null)) {
			builder
					.registerExternalContentHandler(new StreamWriterToContentHandlerConverter(
							omOutput));
		}

		// this is a special case. This fault element may contain its children
		// in any order. But spec mandates a specific order
		// the overriding of the method will facilitate that. Not sure this is
		// the best method to do this :(
		build();

		OMSerializerUtil.serializeStartpart(this, omOutput);
		SOAPFaultCode faultCode = getCode();
		if (faultCode != null) {
			((OMNodeEx)faultCode).serialize(omOutput);
		}
		SOAPFaultReason faultReason = getReason();
		if (faultReason != null) {
			((OMNodeEx)faultReason).serialize(omOutput);
		}

		serializeFaultNode(omOutput);

		SOAPFaultRole faultRole = getRole();
		if (faultRole != null) {
			((OMNodeEx)faultRole).serialize(omOutput);
		}

		SOAPFaultDetail faultDetail = getDetail();
		if (faultDetail != null) {
			((OMNodeEx)faultDetail).serialize(omOutput);
		}

		OMSerializerUtil.serializeEndpart(omOutput);
	}

	protected abstract void serializeFaultNode(
			org.apache.axis2.om.impl.OMOutputImpl omOutput)
			throws XMLStreamException;

}
