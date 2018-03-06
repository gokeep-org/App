package com.app.hl7;

import ca.uhn.hl7v2.model.Message;

public interface Hl7Log {
    public Message take();

    public void put(Message message);

    public void offer(Message message);
}
