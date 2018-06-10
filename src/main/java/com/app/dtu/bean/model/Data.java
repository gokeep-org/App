package com.app.dtu.bean.model;

import javax.persistence.MappedSuperclass;
import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
public class Data {

    private String warn;
    private String id;
    private Integer status;

    public Data() {
    }

    public Data(String warn, String id) {
        this.warn = warn;
        this.id = id;
    }

    public Data(String warn, String id, Integer status) {
        this.warn = warn;
        this.id = id;
        this.status = status;
    }

    public Map<String, String> toMap() {
        Map<String, String> result = new HashMap<>();
        result.put("warn", this.warn);
        result.put("id", this.id);
        result.put("status", String.valueOf(this.status));
        return result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Data{" +
                "warn='" + warn + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
