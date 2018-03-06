package com.app.hl7;


public interface Hl7Log {
    public String take();

    public void put(String message);

    public void offer(String message);
}
