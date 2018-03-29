package com.app.dtu.bean.model;

import com.app.dtu.bean.Message;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 可以作为基础的数据存储被其他的设备类继承， 不是一张单独的表
 */
public class RedundancyDeviceData implements Serializable{

    // 设备的型号
    private String modelCode;

    // 添加日期
    private long createDate;

    // 唯一16位消息id
    private String messageId;

    // 告警列表
    private int warnList;

    // 控制指令
    private int controCmd;

    // 数据长度
    private int dataLen;

    /**
     * 设置消息元数据信息
     */
    public void buildRedunancyDeviceInfo(){
        setMessageId(message.getId());
        setCreateDate(new Date().getTime());
        setModelCode(message.parseModelCode());
        setControCmd(message.getControCmd());
        setDataLen(message.getDataLen());
        setWarnList(message.getWarnList());
    }
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

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

    public int getWarnList() {
        return warnList;
    }

    public void setWarnList(int warnList) {
        this.warnList = warnList;
    }

    public int getControCmd() {
        return controCmd;
    }

    public void setControCmd(int controCmd) {
        this.controCmd = controCmd;
    }

    public int getDataLen() {
        return dataLen;
    }

    public void setDataLen(int dataLen) {
        this.dataLen = dataLen;
    }
}
