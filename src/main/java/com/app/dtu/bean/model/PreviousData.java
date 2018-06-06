package com.app.dtu.bean.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存到Redis上一条数据的内容
 * key'为设备ID
 * value为Data对象hashMap
 */
public class PreviousData {
    private String deviceId;
    private Data data;

    public PreviousData build() {
        return new PreviousData();
    }

    public PreviousData build(String devideId, String warnValue, String id){
        return build().deviceId(deviceId).warn(warnValue).id(id);
    }


    public PreviousData deviceId(String deviceId){
        this.deviceId = deviceId;
        return this;
    }


    public PreviousData warn(String warnValue) {
        this.data.setWarn(warnValue);
        return this;
    }

    public PreviousData id(String id){
        this.data.setId(id);
        return this;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


    @Override
    public String toString() {
        return "PreviousData{" +
                "deviceId='" + deviceId + '\'' +
                ", data=" + data +
                '}';
    }


    class Data {
        private String warn;
        private String id;

        public Data(String warn, String id) {
            this.warn = warn;
            this.id = id;
        }


        public Map<String, String> toMap(){
            Map<String, String> result = new HashMap<>();
            result.put("warn", this.warn);
            result.put("id", this.id);
            return result;
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
}
