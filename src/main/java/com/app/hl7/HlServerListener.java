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
public class HlServerListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(HlServerListener.class);
    public static final String KEYWORD_SOCKET = "SOCKET_HANDLE";
    public ListenHL7Socket socketThread;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        if (Objects.nonNull(socketThread) && !socketThread.isInterrupted()){
            socketThread.closeSocketServer();
        }
        logger.info("HL7 server listen is enable and stop listen web servlet");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        if (Objects.isNull(socketThread)){
            socketThread = new ListenHL7Socket();
            socketThread.start();
            arg0.getServletContext().setAttribute(KEYWORD_SOCKET, socketThread);
        }
        logger.info("HL7 server listen is enable and start listen web servlet");

    }
}
