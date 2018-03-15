package com.app.hl7.bean;

import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class BasicMessage {

    private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private String host;

    private int port;

    // 发送应用名称
    @SerializedName("send_application_name")
    private String sendApplicationName;

    @SerializedName("send_facilit_name")
    private String sendFacilitName;

    // 接收应用名称
    @SerializedName("receiver_application_name")
    private String receiverApplicationName;

    @SerializedName("receiver_facilit_name")
    private String receiverFacilitName;

    // 消息domain
    private String domain;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    // 消息体内容字段包装
    private Map<String, String> content;

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }


    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public static DateFormat getDateFormat() {
        return dateFormat;
    }

    public static void setDateFormat(DateFormat dateFormat) {
        BasicMessage.dateFormat = dateFormat;
    }


    public String getSendApplicationName() {
        return sendApplicationName;
    }

    public void setSendApplicationName(String sendApplicationName) {
        this.sendApplicationName = sendApplicationName;
    }

    public String getSendFacilitName() {
        return sendFacilitName;
    }

    public void setSendFacilitName(String sendFacilitName) {
        this.sendFacilitName = sendFacilitName;
    }

    public String getReceiverApplicationName() {
        return receiverApplicationName;
    }

    public void setReceiverApplicationName(String receiverApplicationName) {
        this.receiverApplicationName = receiverApplicationName;
    }

    public String getReceiverFacilitName() {
        return receiverFacilitName;
    }

    public void setReceiverFacilitName(String receiverFacilitName) {
        this.receiverFacilitName = receiverFacilitName;
    }
}
