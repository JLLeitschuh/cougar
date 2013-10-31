/*
 * Copyright 2013, The Sporting Exchange Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Originally from UpdatedComponentTests/StandardTesting/SOAP/SOAP_RequestTypes_DateTimeSet_Milliseconds.xls;
package com.betfair.cougar.tests.updatedcomponenttests.standardtesting.soap;

import com.betfair.testing.utils.cougar.misc.TimingHelpers;
import com.betfair.testing.utils.cougar.misc.XMLHelpers;
import com.betfair.testing.utils.cougar.assertions.AssertionUtils;
import com.betfair.testing.utils.cougar.beans.HttpCallBean;
import com.betfair.testing.utils.cougar.beans.HttpResponseBean;
import com.betfair.testing.utils.cougar.manager.CougarManager;
import com.betfair.testing.utils.cougar.manager.RequestLogRequirement;

import org.testng.annotations.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Ensure that when a SOAP request is received, Cougar can handle the dateTimeSet datatype with milliseconds accuracy
 */
public class SOAPRequestTypesDateTimeSetMillisecondsTest {
    @Test
    public void doTest() throws Exception {
        // Create the SOAP request as an XML Document (with a dateTimeSet parameter containing a date with milliseconds accuracy)
        XMLHelpers xMLHelpers1 = new XMLHelpers();
        Document createAsDocument1 = xMLHelpers1.getXMLObjectFromString("<DateTimeSetOperationRequest><message><dateTimeSet><Date>2009-01-01T13:50:00.4Z</Date><Date>2009-01-02T13:50:00.4Z</Date></dateTimeSet></message></DateTimeSetOperationRequest>");
        // Set up the Http Call Bean to make the request
        CougarManager cougarManager2 = CougarManager.getInstance();
        HttpCallBean hbean = cougarManager2.getNewHttpCallBean("87.248.113.14");
        CougarManager hinstance = cougarManager2;
        
        hbean.setServiceName("Baseline");
        
        hbean.setVersion("v2");
        // Create the date objects expected to be returned in the response

        String convertUTCDateTimeToLocalTimezoneXMLSchema26 = TimingHelpers.convertUTCDateTimeToLocalTimezoneXMLSchema2((int) 2009, (int) 1, (int) 1, (int) 13, (int) 50, (int) 0, (int) 400);
        // Create the date objects expected to be returned in the response

        String convertUTCDateTimeToLocalTimezoneXMLSchema27 = TimingHelpers.convertUTCDateTimeToLocalTimezoneXMLSchema2((int) 2009, (int) 1, (int) 2, (int) 13, (int) 50, (int) 0, (int) 400);
        // Set the created SOAP request as the PostObject
        hbean.setPostObjectForRequestType(createAsDocument1, "SOAP");
        // Get current time for getting log entries later

        Timestamp getTimeAsTimeStamp9 = new Timestamp(System.currentTimeMillis());
        // Make the SOAP call to the operation
        hinstance.makeSoapCougarHTTPCalls(hbean);
        // Create the expected response object as an XML document (using the date objects created earlier)
        XMLHelpers xMLHelpers6 = new XMLHelpers();
        Document createAsDocument11 = xMLHelpers6.createAsDocument(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(("<response><responseSet><Date>"+convertUTCDateTimeToLocalTimezoneXMLSchema26+"</Date><Date>"+convertUTCDateTimeToLocalTimezoneXMLSchema27+"</Date></responseSet></response>").getBytes())));
        // Convert the expected response to SOAP for comparison with actual response
        Map<String, Object> convertResponseToSOAP12 = hinstance.convertResponseToSOAP(createAsDocument11, hbean);
        // Check the response is as expected
        HttpResponseBean response7 = hbean.getResponseObjectsByEnum(com.betfair.testing.utils.cougar.enums.CougarMessageProtocolResponseTypeEnum.SOAP);
        AssertionUtils.multiAssertEquals(convertResponseToSOAP12.get("SOAP"), response7.getResponseObject());
        
        // generalHelpers.pauseTest(4000L);
        // Check the log entries are as expected
        
        hinstance.verifyRequestLogEntriesAfterDate(getTimeAsTimeStamp9, new RequestLogRequirement("2.8", "dateTimeSetOperation") );
    }

}