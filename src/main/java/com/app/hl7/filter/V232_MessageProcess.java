package com.app.hl7.filter;

import ca.uhn.hl7v2.model.Message;

public class V232_MessageProcess implements Hl7MessageFilter{
    @Override
    public <T extends Message> T process(T message) {
        if (!message.getName().equalsIgnoreCase("V232")){
            return null;
        }
        // 处理逻辑， service->db->message
        return null;
    }
}
