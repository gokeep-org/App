package com.app.hl7;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.ConnectionHub;
import ca.uhn.hl7v2.app.HL7ServerTestHelper;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.MinLowerLayerProtocol;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.PipeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Objects;

public class SendAndReceiveAMessage{
    private static final Logger logegr = LoggerFactory.getLogger(SendAndReceiveAMessage.class);

    boolean useTls = false;
    private static SendAndReceiveAMessage client;
    private String host;
    private int port;
    private static ConnectionHub connectionHub = ConnectionHub.getInstance();
    private Connection connection;
    private SendAndReceiveAMessage() {
    }

    public Message sendMessage(String theMessage){
        try {
            Initiator initiator = connection.getInitiator();
            Message response = initiator.sendAndReceive(new PipeParser().parse(theMessage));
            return response;
        } catch (Throwable e) {
            logegr.error("Send message is fail: {}", e.getMessage());
        }finally {
            if (Objects.nonNull(connection) || connection.isOpen()){
                connection.close();
            }
        }
        return null;
    }
    public static SendAndReceiveAMessage build() {
        synchronized (new Object()) {
            client = Objects.isNull(client) ? new SendAndReceiveAMessage() : client;
        }
        if (Objects.isNull(connectionHub)){
            connectionHub = ConnectionHub.getInstance();
        }
        return client;
    }

    public SendAndReceiveAMessage buildAddess(String host, int port){
        this.host = host;
        this.port = port;
        try {
            connection = connectionHub.attach(host, port, new PipeParser(), MinLowerLayerProtocol.class);
        } catch (HL7Exception e) {
            logegr.error("get Connection is fail: {}", e.getMessage());
        }
        return client;
    }

    public static void main(String[] args) {
        String message = "MSH|^~\\&|CHAOSI|CLIENT_1|CHAO_SI_PIX_MANAGER|CHAO_SI_PIX_FACILLITY|20101004144709||ADT^A01^ADT_A01|NIST-101004144709009|P|2.5\r" +
                "EVN||20101004144709\r" +
                "PID|||PIX^^^IHE2010&1.3.6.1.4.1.21367.2010.1.1&ISO||ALPHA^ALAN^^^^^L|BARNES^^^^^^L|19781208|M|||820 JORIE BLVD^^NEW YORK CITY^NY^10503||||||||13-12-5430\r" +
                "PV1||I";
        SendAndReceiveAMessage.build().buildAddess("59.110.9.17", 3600).sendMessage(message);
    }
}
