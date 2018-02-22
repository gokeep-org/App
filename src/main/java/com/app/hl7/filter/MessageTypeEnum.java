package com.app.hl7.filter;


public enum  MessageTypeEnum {

    ADT_A01(0,"ADT_A01");

    private Integer key;
    private String name;

    MessageTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
