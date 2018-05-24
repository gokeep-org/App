package com.app.dtu.bean.model;


import com.app.dtu.config.DtuConfig;

import java.util.UUID;

public class PID {
    private String messageId;
    private Long time;
    private String code;

    public PID(String messageId) {
        this.messageId = messageId;
    }

    public void build(){
        if (messageId == null || messageId.length() == 0){
            return;
        }
        if (this.messageId.equalsIgnoreCase(messageId)){
            if (System.currentTimeMillis() - time > DtuConfig.PID_UPDATE_TIME){
                code = UUID.randomUUID().toString();
                time = System.currentTimeMillis();
            }
        }
    }


    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
