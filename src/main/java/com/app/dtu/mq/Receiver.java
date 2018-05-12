package com.app.dtu.mq;

public interface Receiver {
    public void process(String msg);
}
