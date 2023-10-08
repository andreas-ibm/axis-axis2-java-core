//
// Generated By:JAX-WS RI IBM 2.1.1 in JDK 6 (JAXB RI IBM JAXB 2.1.3 in JDK 1.6)
//


package org.apache.axis2.jaxws.sample.mtomfeature;

import jakarta.activation.DataHandler;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

import org.apache.axis2.jaxws.sample.mtomfeature.ObjectFactory;

@WebService(name = "ProcessDocumentDelegate", targetNamespace = "http://mtomfeature.sample.jaxws.axis2.apache.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ProcessDocumentDelegate {


    /**
     * 
     * @param arg0
     * @return
     *     returns jakarta.activation.DataHandler
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sendPDFFile", targetNamespace = "http://mtomfeature.sample.jaxws.axis2.apache.org/", className = "org.apache.axis2.jaxws.sample.mtomfeature.SendPDFFile")
    @ResponseWrapper(localName = "sendPDFFileResponse", targetNamespace = "http://mtomfeature.sample.jaxws.axis2.apache.org/", className = "org.apache.axis2.jaxws.sample.mtomfeature.SendPDFFileResponse")
    public DataHandler sendPDFFile(
        @WebParam(name = "arg0", targetNamespace = "")
        DataHandler arg0);

}
