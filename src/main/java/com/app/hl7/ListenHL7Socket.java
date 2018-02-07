package com.app.hl7;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Application;
import ca.uhn.hl7v2.app.HL7Service;
import ca.uhn.hl7v2.protocol.impl.HL7Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class ListenHL7Socket extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(ListenHL7Socket.class);
    private static final boolean IS_TLS = false;
    private static final String Hl7ServerHost = "localhost";
    private static final int Hl7ServerPort = 9080;
    private HL7Service hl7Service;

    @Override
    public void run() {
        HapiContext hapiContext = new DefaultHapiContext();
        try {
            hl7Service = Objects.isNull(hl7Service) || !hl7Service.isRunning() ? hapiContext.newServer(Hl7ServerPort, IS_TLS) : hl7Service;
        } catch (Throwable e) {
            logger.error("Hl7 Server enable fail. {}", e.getMessage());
        }
        Application handler = new ExampleReceiverApplication();
        hl7Service.registerApplication("*", "*", handler);
        try {
            hl7Service.startAndWait();
//            try {
////                hapiContext.close();
//            } catch (IOException e) {
//                logger.error("Hl7 context close fail");
//            }
        } catch (InterruptedException e) {
            logger.error("Hl7 Server start is fail, ", e.getMessage());
        }
        logger.info("Hl7 Server thread is start and waiting");
    }

    public void closeSocketServer(){
        if (Objects.nonNull(hl7Service) || hl7Service.isRunning())
            hl7Service.stopAndWait();
    }
}
