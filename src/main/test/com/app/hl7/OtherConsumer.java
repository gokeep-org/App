package com.app.hl7;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
//import com.app.hl7.msg.TestMessage;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OtherConsumer {
    private static final Logger logger = LoggerFactory.getLogger(OtherConsumer.class);
    private static final String host = "localhost";
    private static final Integer port = 3600;
    public static final String applicationName= "EHR_MISYS";
    public static final String facilitName = "MISYS";

    @Before
    public void before(){
        logger.info("Test PIX Manager start");
    }


    @After
    public void after(){
        logger.info("Test PIX Manager end");
    }


@Test
    public void testhaniServer() throws HL7Exception {
        String PIX_SOURCE_ITI8_A01 = "MSH|^~\\&|CHAOSI|CLIENT_1|"+applicationName+"|"+facilitName+"|20101004144709||ADT^A01^ADT_A01|NIST-101004144709009|P|2.5\r" +
                "EVN||20101004144709\r" +
                "PID|||PIX^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO||ALPHA^ALAN^^^^^L|BARNES^^^^^^L|19781208|M|||820 JORIE BLVD^^NEW YORK CITY^NY^10503||||||||13-12-5432\n" +
                "PV1||I";
//        String a04 = "MSH|^~\\&|OTHER_IBM_BRIDGE_TLS|IBM|PAT_IDENTITY_X_REF_MGR_MISYS|ALLSCRIPTS|20090224104145-0600||ADT^A04^ADT_A01|0851077658473390286|P|2.5 EVN||20090224104145-0600 PID|||101^^^IBOT&1.3.6.1.4.1.21367.2009.1.2.370&ISO||FARNSWORTH^STEVE||19661109|M PV1||O";
//        Message response = SendAndReceiveAMessage.build().buildAddess(host, port).sendByCode(PIX_SOURCE_ITI8_A01);
//        logger.info("Receiver message is {}", response);
//        Assert.assertEquals(true, !response.isEmpty());
    System.out.println();
    }
}
