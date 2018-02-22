package com.app.hl7.filter;

import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v21.message.ADT_A01;
import ca.uhn.hl7v2.protocol.impl.HL7Server;

public class ADT_A01MessageProcess implements Hl7MessageFilter{
    @Override
    public <T extends Message> T process(T message) {
        if (!message.getName().equalsIgnoreCase(MessageTypeEnum.ADT_A01.getName())){
            return null;
        }
        ADT_A01 adtA01 = (ADT_A01) message;
        return null;
    }
}
