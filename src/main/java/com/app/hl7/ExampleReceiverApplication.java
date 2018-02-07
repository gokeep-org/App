package com.app.hl7;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.Application;
import ca.uhn.hl7v2.app.ApplicationException;
import ca.uhn.hl7v2.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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
            Message result = message.generateACK();
            return result;
        } catch (IOException e) {
            throw new HL7Exception(e);
        }
    }

    @Override
    public boolean canProcess(Message message) {
        return true;
    }
}
