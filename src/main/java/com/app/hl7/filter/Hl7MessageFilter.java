package com.app.hl7.filter;

import ca.uhn.hl7v2.model.Message;

public interface Hl7MessageFilter {
    public <T extends Message> T process(T message);
}
