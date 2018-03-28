package com.app.dtu.bean.model;

import com.app.dtu.bean.Message;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 可以作为基础的数据存储被其他的设备类继承， 不是一张单独的表
 */
public class RedundancyDeviceData implements Serializable{
    // 设备的型号
    private String modelCode;
    // 添加日期
    private long createDate;

    @Transient
    private Message message;

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
