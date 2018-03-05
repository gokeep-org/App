package com.app.hl7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Objects;

@SpringBootApplication
@WebListener
public class Hl7ServerStart implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(Hl7ServerStart.class);
    public static final String KEYWORD_SOCKET = "SOCKET_HANDLE";
    public Hl7MessageListenThread hl7MessageListenThread;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("Start listen hl7 message server is enable");
        if (Objects.isNull(hl7MessageListenThread)){
            hl7MessageListenThread = new Hl7MessageListenThread();
            hl7MessageListenThread.start();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (Objects.nonNull(hl7MessageListenThread) && hl7MessageListenThread.isAlive()){
            hl7MessageListenThread.stop();
        }
        logger.info("HL7 server listen is enable and start listen web servlet");
    }
}
