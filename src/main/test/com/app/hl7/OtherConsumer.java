package com.app.hl7;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import com.app.hl7.msg.TestMessage;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OtherConsumer {
    private static final Logger logger = LoggerFactory.getLogger(OtherConsumer.class);
    private static final String host = "localhost";
    private static final Integer port = 3610;
    public static final String applicationName= "hinacom-pix";
    public static final String facilitName = "hinacom";

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
                "PID|||PIX^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO||ALPHA^ALAN^^^^^L|BARNES^^^^^^L|19781208|M|||820 JORIE BLVD^^NEW YORK CITY^NY^10503||||||||153-12-5432\n" +
                "PV1||I";
        Message response = SendAndReceiveAMessage.build().buildAddess(host, port).sendByCode(PIX_SOURCE_ITI8_A01);
        logger.info("Receiver message is {}", response);
        Assert.assertEquals(true, !response.isEmpty());
    }
}
