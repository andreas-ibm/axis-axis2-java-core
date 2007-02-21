/**
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
package org.apache.axis2.jaxws.sample.dlwmin;

import org.apache.axis2.jaxws.sample.dlwmin.sei.Greeter;
import org.apache.axis2.jaxws.sample.dlwmin.sei.TestException;
import org.apache.axis2.jaxws.sample.dlwmin.types.TestBean;

import javax.jws.WebService;
import javax.xml.ws.WebServiceException;

@WebService(endpointInterface = "org.apache.axis2.jaxws.sample.dlwmin.sei.Greeter",
        targetNamespace = "http://apache.org/axis2/jaxws/sample/dlwmin")
public class GreeterImpl implements Greeter {

    public String greetMe(String me) {
        return "Hello " + me;
    }

    public String testUnqualified(String in) {
        return in;
    }

    public TestBean process(int inAction, TestBean in) throws TestException {
        if (inAction == 0) {
            // echo
            return in;
        } else if (inAction == 1) {
            // throw checked exception
            throw new TestException(123, "TestException thrown");
        } else if (inAction == 2) {
            throw new WebServiceException("WebServiceException thrown");
        } else if (inAction == 3) {
            throw new NullPointerException("NPE thrown");
        }
        return null;
    }

}
