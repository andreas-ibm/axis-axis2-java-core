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

package org.apache.axis2.jaxws.spi;

import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

/*
 * You can't actually specify whether a handler is for client or server,
 * you just have to check in the handleMessage and/or handleFault to make
 * sure what direction we're going.
 */

public class ClientMetadataHandlerChainHandler implements jakarta.xml.ws.handler.soap.SOAPHandler<SOAPMessageContext> {

    public void close(MessageContext messagecontext) {
    }

    public boolean handleFault(SOAPMessageContext messagecontext) {
        return true;
    }

    public Set getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext messagecontext) {
        return true;
    }

}
