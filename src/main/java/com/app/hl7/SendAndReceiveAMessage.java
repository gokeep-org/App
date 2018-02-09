package com.app.hl7;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.ConnectionListener;
import ca.uhn.hl7v2.app.HL7Service;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.protocol.ReceivingApplication;
import ca.uhn.hl7v2.protocol.ReceivingApplicationExceptionHandler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;


public class SendAndReceiveAMessage {
    private SendAndReceiveAMessage() {
    }

    private static final Logger logegr = LoggerFactory.getLogger(SendAndReceiveAMessage.class);

    boolean useTls = false;
    HapiContext context;
    private static SendAndReceiveAMessage client;
    private String host;
    private int port;

    public static SendAndReceiveAMessage build() {
        synchronized (new Object()) {
            client = Objects.isNull(client) ? new SendAndReceiveAMessage() : client;
            client.context = new DefaultHapiContext();
        }
        return client;
    }

    public SendAndReceiveAMessage buildAddess(String host, int port){
        this.host = host;
        this.port = port;
        build();
        return client;
    }


    public String parseMessage(Message message){
        Parser parser = this.context.getPipeParser();
        try {
            return parser.encode(message);
        } catch (HL7Exception e) {
            logegr.error("parser message by code is fail");
        }
        return null;
    }

    public Message sendByCode(String code) {
        Message message = null;
        Parser parser = this.context.getPipeParser();
        try {
            message = parser.parse(code);
            if (Objects.isNull(message)) {
                return message;
            }
        } catch (HL7Exception e) {
            logegr.error("parser message by code is fail");
        }
        return send(message);
    }

    public Message send(Message message) {
        Connection connection = null;
        Message response = null;
        if (Objects.isNull(message)){
            return message;
        }
        try {
            if (StringUtils.isEmpty(client.host)){
                connection = this.context.newClient(Hl7Config.SERVER_HOST, Hl7Config.SERVER_PORT, useTls);
            }else {
                connection = this.context.newClient(this.host, this.port, useTls);
            }
            Initiator initiator = connection.getInitiator();
            response = initiator.sendAndReceive(message);
        } catch (Throwable e) {
            logegr.error("create new client is fail", e.getMessage());
        } finally {
            if (Objects.nonNull(connection) || connection.isOpen()) {
                connection.close();
            }
        }
        return response;
    }

    public static void main(String[] args) {
        Message message = null;
        try {
            message = build().sendByCode(Hl7Config.hinacomPix()).generateACK();
        } catch (HL7Exception e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logegr.info("");
    }
}
