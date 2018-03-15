package com.app.hl7;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.PipeParser;
import com.app.hl7.msg.TestMessage;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdqConsumeTest {
    private static final Logger logger = LoggerFactory.getLogger(PdqConsumeTest.class);
    private static final String host = "202.105.136.186";
    private static final Integer port = 7001;
    public static final String applicationName= "LWClient";
    public static final String facilitName = "LWRIS";

    @Test
    public void testPDQ_ITI_21_1_1() throws HL7Exception {
        Message response = SendAndReceiveAMessage.build().buildAddess(host, port).sendMessage(TestMessage.PDQ_ITI_21_1_1);
        logger.info("Receiver message is {}", new PipeParser().encode(response));
    }

    @Test
    public void testITI_8_A01() throws HL7Exception {
        Message response = SendAndReceiveAMessage.build().buildAddess(host, port).sendMessage(TestMessage.PDQ_ITI_21_1_1);
        logger.info("Receiver message is {}", new PipeParser().encode(response));
    }
}
