package com.app.hl7;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.Application;
import ca.uhn.hl7v2.app.ApplicationException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v22.segment.PID;
import com.app.hl7.filter.Hl7MessageFilterChain;
import com.app.hl7.util.Hl7Util;
import com.app.hl7.util.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExampleReceiverApplication implements Application {
    private static final Logger logger = LoggerFactory.getLogger(ExampleReceiverApplication.class);

    @Override
    public Message processMessage(Message message) throws ApplicationException, HL7Exception {
        String name = message.getName();
        String[] names = message.getNames();
        logger.info("names is : {}", names);
        HL7EventContainer.process(message, "names is : {}" + name);
        String encodeMessage = new DefaultHapiContext().getPipeParser().encode(message);
        logger.info("parse encod message is {}", encodeMessage);
        HL7EventContainer.process(message, "parse encod message is: " + encodeMessage);
        try {
            // TODO: 调用消息责任链处理消息，
            //Hl7MessageFilterChain.build().doFilter(message);
            // 这里临时委托给openmrs处理
            Hl7Util.insertDbMessageInQueue(encodeMessage);
            return message.generateACK();
        } catch (IOException e) {
            throw new HL7Exception(e);
        }
    }

    @Override
    public boolean canProcess(Message message) {
        return true;
    }
}
