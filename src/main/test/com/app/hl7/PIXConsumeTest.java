package com.app.hl7;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.FastParser;
import ca.uhn.hl7v2.parser.GenericParser;
import ca.uhn.hl7v2.parser.PipeParser;
import com.app.hl7.msg.TestMessage;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IHE 测试用例,用来测试
 */
public class PIXConsumeTest {
    private static final Logger logger = LoggerFactory.getLogger(PIXConsumeTest.class);
    private static final String host = "202.105.136.186";
    private static final Integer port = 7001;
    public static final String applicationName= "LWClient";
    public static final String facilitName = "LWRIS";

    @Before
    @Ignore("测试已通过")
    public void before(){
        logger.info("Test PIX Manager start");
    }


    @After
    public void after(){
        logger.info("Test PIX Manager end");
    }


    @Test
    @Ignore("测试已通过")
    public void testITI_8_A01() throws HL7Exception {
        Message response = SendAndReceiveAMessage.build().buildAddess(host, port).sendMessage(TestMessage.PIX_SOURCE_ITI8_A01);
        logger.info("Receiver message is {}", new PipeParser().encode(response));
    }

    @Test
    @Ignore("测试已通过")
    public void testITI_8_A04() throws HL7Exception {
        Message response = SendAndReceiveAMessage.build().buildAddess(host, port).sendMessage(TestMessage.PIX_SOURCES_ITI_8_A04);
        logger.info("Receiver message is {}", new PipeParser().encode(response));

    }

    @Test
    @Ignore("测试已通过")
    public void testITI_8_A05() throws HL7Exception {
        Message response = SendAndReceiveAMessage.build().buildAddess(host, port).sendMessage(TestMessage.PIX_SOURCES_ITI_8_A05);
        logger.info("Receiver message is {}", new PipeParser().encode(response));
    }


    @Test
    @Ignore("测试已通过")
    public void testITI_8_A08() throws HL7Exception {
        Message response = SendAndReceiveAMessage.build().buildAddess(host, port).sendMessage(TestMessage.PIX_SOURCES_ITI_8_A08);
        logger.info("Receiver message is {}", new PipeParser().encode(response));
    }

    @Test
    @Ignore("测试已通过")
    public void testITI_8_A40() throws HL7Exception {
        Message response = SendAndReceiveAMessage.build().buildAddess(host, port).sendMessage(TestMessage.PIX_SOURCES_ITI_8_A40);
        logger.info("Receiver message is {}", new PipeParser().encode(response));
    }
    /**
     * 测试ITI-9-Consumer-1, 已经通过
     * 描述： 测试Send a valid PIX query message (QBP^Q23^QBP_Q21) to the NIST PIX Manager with QPD.4 valued
     * url: http://pixpdqtests.nist.gov/pixpdqtool/#tests%2Fdriver%2Fversion.htm
     * 发送一个测试消息，返回一个消息响应
     */
    @Test
    @Ignore("测试已通过")
    public void testITI_9Consume() throws HL7Exception {
        Message response = SendAndReceiveAMessage.build().buildAddess(host, port).sendMessage(TestMessage.PIX_CONSUME_ITI_9);
        logger.info("Receiver message is {}", response);
    }



    @Test
    @Ignore("测试已通过")
    public void testADT_A04_Message() throws HL7Exception {
        Message response = SendAndReceiveAMessage.build().buildAddess(host, port).sendMessage(TestMessage.ADT_A04_MESSAGE);
        logger.info("Receiver message is {}", response);
    }
}
