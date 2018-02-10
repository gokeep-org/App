package com.app.hl7.filter;

import ca.uhn.hl7v2.model.Message;

public class ADT_A01MessageProcess implements Hl7MessageFilter{
    @Override
    public <T extends Message> T process(T message) {
        if (!message.getName().equalsIgnoreCase("ADT_A01")){
            return null;
        }
        return null;
    }
}
