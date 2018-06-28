package com.app.dtu.bean.model;

import com.app.dtu.config.DtuConfig;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

/**
 * 缓存到Redis上一条数据的内容
 * key'为设备ID
 * value为Data对象hashMap
 */

@Entity
@Table(name =  DtuConfig.DTU_TABLE_PRIFIX +"redis_cache_previous_data")
public class PreviousData extends Data{
    @Id
    private String deviceId;

    public PreviousData(String warn, String id) {
        super(warn, id);
    }

    public PreviousData(){}

    public static PreviousData build() {
        return new PreviousData();
    }

    public PreviousData buildData(String deviceId, String strongId, String warnList, Long warnTime){
        this.deviceId = deviceId;
        setWarnTime(warnTime);
        setWarn(warnList);
        setId(strongId);
        return this;
    }

    public Map<String, String> getDataMap(){
        return getData().toMap();
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
        getData().setWarn(warnValue);
        return this;
    }

    public PreviousData id(String id){
        getData().setId(id);
        return this;
    }

    public Data getData() {
        return getData();
    }

    public void setData(Data data) {
        setId(data.getId());
        setWarn(data.getWarn());
        setWarnTime(data.getWarnTime());
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
