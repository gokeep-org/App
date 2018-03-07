package com.app.hl7;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.HL7ServerTestHelper;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class SendAndReceiveAMessage{
    private static final Logger logegr = LoggerFactory.getLogger(SendAndReceiveAMessage.class);

    boolean useTls = false;
    private static SendAndReceiveAMessage client;
    private String host;
    private int port;
    private SendAndReceiveAMessage() {
    }

    public void sendMessage(String theMessage){
        HL7ServerTestHelper serverTest = new HL7ServerTestHelper(this.host, this.port);
        InputStream msgInputStream = new ByteArrayInputStream(theMessage.getBytes());
        try {
            serverTest.process(msgInputStream);
        } catch (Throwable e) {
            logegr.error("Send message is fail: {}", e.getMessage());
        }
    }

    public static SendAndReceiveAMessage build() {
        synchronized (new Object()) {
            client = Objects.isNull(client) ? new SendAndReceiveAMessage() : client;
        }
        return client;
    }

    public SendAndReceiveAMessage buildAddess(String host, int port){
        this.host = host;
        this.port = port;
        return client;
    }

    public static void main(String[] args) {
        String message = "MSH|^~\\&|CHAOSI|CLIENT_1|CHAO_SI_PIX_MANAGER|CHAO_SI_PIX_FACILLITY|20101004144709||ADT^A01^ADT_A01|NIST-101004144709009|P|2.5\n" +
                "EVN||20101004144709\n" +
                "PID|||PIX^^^IHE2010&1.3.6.1.4.1.21367.2010.1.1&ISO||ALPHA^ALAN^^^^^L|BARNES^^^^^^L|19781208|M|||820 JORIE BLVD^^NEW YORK CITY^NY^10503||||||||13-12-5430\n" +
                "PV1||I";
        SendAndReceiveAMessage.build().buildAddess("localhost", 3600).sendMessage(message);
    }
}
