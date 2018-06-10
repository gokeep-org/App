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

    public static PreviousData build() {
        return new PreviousData();
    }

    public PreviousData buildData(String deviceId, String strongId, String warnList, Integer status){
        PreviousData.Data data = new PreviousData.Data(warnList, strongId, status);
        this.deviceId = deviceId;
        return this;
    }

    public Map<String, String> getDataMap(){
        return this.data.toMap();
    }

    public PreviousData get(){
        return this;
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

    public Data getData() {
        return data;
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


    /**
     *  这里添加上一次的消息缓存策略
     *  这里的id为存储的主键id
     */
    class Data {
        private String warn;
        private String id;
        private Integer status;

        public Data(String warn, String id) {
            this.warn = warn;
            this.id = id;
        }

        public Data(String warn, String id, Integer status) {
            this.warn = warn;
            this.id = id;
            this.status = status;
        }

        public Map<String, String> toMap(){
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
}
