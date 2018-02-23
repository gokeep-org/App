package com.app.hl7;


import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Application;
import ca.uhn.hl7v2.app.HL7Service;
import com.app.hl7.config.Hl7Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class ListenHL7Socket extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(ListenHL7Socket.class);
    private HL7Service hl7Service;

    @Override
    public void run() {
        HapiContext hapiContext = new DefaultHapiContext();
        try {
            hl7Service = Objects.isNull(hl7Service) || !hl7Service.isRunning() ? hapiContext.newServer(Hl7Config.SERVER_PORT, Hl7Config.IS_TLS) : hl7Service;
        } catch (Throwable e) {
            logger.error("Hl7 Server enable fail. {}", e.getMessage());
        }
        Application handler = new ExampleReceiverApplication();
        hl7Service.registerApplication("*", "*", handler);
        try {
            hl7Service.startAndWait();
            /**
             * bug fix:
             *  这里因为是一个单例，close会导致之后的所有context为null
                try {
                    hapiContext.close();
                } catch (IOException e) {
                    logger.error("Hl7 context close fail");
                }
             */
        } catch (InterruptedException e) {
            logger.error("Hl7 Server start is fail, ", e.getMessage());
        }
        logger.info("Hl7 Server thread is start and waiting, host is {}, port is {}", Hl7Config.SERVER_HOST, Hl7Config.SERVER_PORT);
    }

    public void closeSocketServer(){
        if (Objects.nonNull(hl7Service) || hl7Service.isRunning())
            hl7Service.stopAndWait();
    }
}
