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

// Originally from ClientTests/Transport/StandardTesting/Client_Rescript_Post_RequestTypes_List_NullEntry.xls;
package com.betfair.cougar.tests.clienttests.standardtesting;

import com.betfair.baseline.v2.BaselineSyncClient;
import com.betfair.baseline.v2.to.ComplexObject;
import com.betfair.baseline.v2.to.LargeRequest;
import com.betfair.baseline.v2.to.SimpleResponse;
import com.betfair.cougar.api.ExecutionContext;
import com.betfair.cougar.tests.clienttests.ClientTestsHelper;
import com.betfair.cougar.tests.clienttests.CougarClientWrapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Ensure that when a List with a null entry is passed in parameters to cougar via a cougar client, the request is sent and the response is handled correctly
 */
public class ClientPostRequestTypesListNullEntryTest {
    @Test(dataProvider = "TransportType")
    public void doTest(CougarClientWrapper.TransportType tt) throws Exception {
        // Create a complex object
        ComplexObject complexObject1 = new ComplexObject();
        complexObject1.setName("n1");
        ComplexObject complex1 = complexObject1;
        
        complex1.setValue1((int) 11);
        
        complex1.setValue2((int) 21);
        // Create another complex object
        ComplexObject complexObject2 = new ComplexObject();
        complexObject2.setName("n3");
        ComplexObject complex3 = complexObject2;
        
        complex3.setValue1((int) 13);
        
        complex3.setValue2((int) 23);
        // Create a large request object
        LargeRequest largeRequest3 = new LargeRequest();
        largeRequest3.setSize((int) 3);
        LargeRequest largeRequest = largeRequest3;
        
        largeRequest.setOddOrEven(com.betfair.baseline.v2.enumerations.LargeRequestOddOrEvenEnum.ODD);
        
        largeRequest.setReturnList(true);
        
        largeRequest.setObjects(Arrays.asList(complex1, null, complex3));
        // Set up the client to use rescript transport
        CougarClientWrapper cougarClientWrapper4 = CougarClientWrapper.getInstance(tt);
        CougarClientWrapper wrapper = cougarClientWrapper4;
        BaselineSyncClient client = cougarClientWrapper4.getClient();
        ExecutionContext context = cougarClientWrapper4.getCtx();
        // Make call to the method via client and validate response is as expected
        SimpleResponse response5 = client.testLargePostQA(context, largeRequest);
        assertEquals("Names: n1nulln3 - Value1s: 11null13 - Value2s: 21null23", response5.getMessage());
    }

    @DataProvider(name="TransportType")
    public Object[][] clients() {
        return ClientTestsHelper.clientsToTest();
    }

}
