package com.app.dtu.bean.model;

import javax.persistence.MappedSuperclass;
import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
public class Data {

    private String warn;
    private String id;
    private Long warnTime;

    public Data() {
    }

    public Data(String warn, String id) {
        this.warn = warn;
        this.id = id;
    }

    public Data(String warn, String id, Long status) {
        this.warn = warn;
        this.id = id;
        this.warnTime = status;
    }

    public Map<String, String> toMap() {
        Map<String, String> result = new HashMap<>();
        result.put("warn", this.warn);
        result.put("id", this.id);
        result.put("warn_time", String.valueOf(this.warnTime));
        return result;
    }


    public Long getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(Long warnTime) {
        this.warnTime = warnTime;
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
}
