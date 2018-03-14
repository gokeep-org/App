package com.app.hl7.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

public class HttpHl7Message implements Serializable{

    private String host;

    private int port;

    @SerializedName("receiver_application_name")
    private String applicationName;

    @SerializedName("receiver_facilit_name")
    private String facilitName;

    @SerializedName("domain")
    private String domain;

    private Map<String, Object> content;


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

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }
}
