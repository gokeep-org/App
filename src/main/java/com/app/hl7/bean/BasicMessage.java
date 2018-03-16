package com.app.hl7.bean;

import com.app.hl7.Hl7ComonConfig;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class BasicMessage {

    private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private String host;

    private int port;

    // 发送应用名称
    private String send_application_name = Hl7ComonConfig.sendApplication;

    private String send_facility_name = Hl7ComonConfig.sendFacilit;

    // 接收应用名称
    private String receiver_application_name;

    // 接收设备名称
    private String receiver_facility_name;

    // 封装的元数据内容
    private Map<String, String> content;

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


    public static DateFormat getDateFormat() {
        return dateFormat;
    }

    public static void setDateFormat(DateFormat dateFormat) {
        BasicMessage.dateFormat = dateFormat;
    }

    public String getSend_application_name() {
        return send_application_name;
    }

    public void setSend_application_name(String send_application_name) {
        this.send_application_name = send_application_name;
    }

    public String getSend_facility_name() {
        return send_facility_name;
    }

    public void setSend_facility_name(String send_facility_name) {
        this.send_facility_name = send_facility_name;
    }

    public String getReceiver_application_name() {
        return receiver_application_name;
    }

    public void setReceiver_application_name(String receiver_application_name) {
        this.receiver_application_name = receiver_application_name;
    }

    public String getReceiver_facility_name() {
        return receiver_facility_name;
    }

    public void setReceiver_facility_name(String receiver_facility_name) {
        this.receiver_facility_name = receiver_facility_name;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }
}
