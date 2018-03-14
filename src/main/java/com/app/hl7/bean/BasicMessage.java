package com.app.hl7.bean;

import com.app.hl7.Hl7ComonConfig;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicMessage {
    @SerializedName("receiver_application_name")
    private String applicationName;

    @SerializedName("receiver_facilit_name")
    private String facilitName;

    private String domain;

    private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public String MSH = "MSH|^~\\&|"+ Hl7ComonConfig.sendApplication+"|"+ Hl7ComonConfig.sendFacilit+"|"+applicationName+"|"+facilitName+"|"+dateFormat.format(new Date())+"||ADT^A01^ADT_A01|"+domain+"|P|2.3.1\r";

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getFacilitName() {
        return facilitName;
    }

    public void setFacilitName(String facilitName) {
        this.facilitName = facilitName;
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

    public String getMSH() {
        return MSH;
    }

    public void setMSH(String MSH) {
        this.MSH = MSH;
    }
}
