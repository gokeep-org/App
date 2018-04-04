package com.app.dtu.bean.model;


/**
 * 设备关系表，不关联业务
 * 通过定时任务的方式
 * 一定时间内同步数据到本地缓存
 * 用户通过设备id号，查到设备的型号，这里主要针对是管理机上传的数据解析到设备型号
 *
 */
public class DeviceRelation {
    // 设备的ID
    private String device_id;
    // 设备的sn
    private String device_sn;
    // 设备的型号
    private String device_model_code;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_sn() {
        return device_sn;
    }

    public void setDevice_sn(String device_sn) {
        this.device_sn = device_sn;
    }

    public String getDevice_model_code() {
        return device_model_code;
    }

    public void setDevice_model_code(String device_model_code) {
        this.device_model_code = device_model_code;
    }
}

