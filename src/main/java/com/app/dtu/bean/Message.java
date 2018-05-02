package com.app.dtu.bean;

import com.app.dtu.bean.model.*;
import com.app.dtu.bean.model.device.*;
import com.app.dtu.config.DtuConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 消息传输实体
 */
public class Message {
    @Transient
    private static final Logger logger = LoggerFactory.getLogger(Message.class);
    private String id;
    private int warnList;
    private int controCmd;
    private int dataLen;
    private List<DataMsg> dataMsgs;
    private int status;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWarnList() {
        return warnList;
    }

    public void setWarnList(int warnList) {
        this.warnList = warnList;
    }

    public int getControCmd() {
        return controCmd;
    }

    public void setControCmd(int controCmd) {
        this.controCmd = controCmd;
    }

    public int getDataLen() {
        return dataLen;
    }

    public void setDataLen(int dataLen) {
        this.dataLen = dataLen;
    }

    public List<DataMsg> getDataMsgs() {
        return dataMsgs;
    }

    public void addDataMsgs(DataMsg dataMsg) {
        if (dataMsgs == null){
            dataMsgs = new ArrayList<>();
        }
        dataMsgs.add(dataMsg);
    }

    /**
     * 解析终端类型， 如01， 02
     * @return
     */
    public String parseTypeCode(){
        return this.id.substring(6, 8);
    }


    /**
     * 解析设备型号
     * TODO： 这里需要解决终端机上传和设备上传的两种形式
     * 目前是仅仅支持直接设备上传的
     * @return
     */
    public String parseModelCode(){
        if (DtuConfig.LOCAL_DEV_ONALY_FORWARD){
            return this.id.substring(6, 10);
        }
        // 这里是终端代传的首选方式，也可以支持直传的方式
        if (CollectionUtils.isEmpty(LocalCache.getDeviceRelationCache())){
            logger.error("parse device code by db is error, local cache is null or nor has model");
            return null;
        }
        List<DeviceRelation> deviceRelations = LocalCache.getDeviceRelationCache().stream().filter(deviceRelation -> {
            return deviceRelation.getDevice_id().equalsIgnoreCase(id);
        }).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(deviceRelations)){
            logger.error("parse device code by db is error, local cache is null or nor has model");
            return null;
        }
        return deviceRelations.get(0).getDevice_model_code();
    }

    /**
     * 解析设备型号枚举
     * @return
     */
    public DeviceTypeName parseDeviceModelEnum(){
        if (StringUtils.isEmpty(this.id) || this.id.length() != 16){
            return null;
        }
        return DeviceTypeName.getDeviceTypeInfoByModelCode(parseModelCode());
    }


    /**
     * 获取到具体的设备实体对象
     * @return
     */
    public DeviceDataDeal getDevice(){
        String terminaTypeCode = parseTypeCode();
        switch (terminaTypeCode){
            case "00" : return new MonitorManagerDevice(this);
            case "01" : return new ElectricalFireMonitorDevice(this);
            case "02" : return new IntelligentPowerMonitorDevice(this);
            case "03" : return new FaultArcMonitorDevice(this);
            case "04" : return new FireControlPowerMonitorDevice(this);
            case "05" : return new SmokeFeelMonitorDevice(this);
            case "06" : return new CombustibleGasMonitorDevice(this);
            case "07" : return new PrventFireDoorMonitorDevice(this);
            case "08" : return new ScreenMonitorDevice(this);
            case "12" : return new HydraulicPressureMonitorDevice(this);
            default: return null;
        }
    }

    /**
     * 获取基础设备信息的处理, 这里主要用于数据的初始化
     * @return
     */
    public DeviceDataDeal getBasicDeviceInfoDeal(){
        return new Device(this);
    }


    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", warnList=" + warnList +
                ", controCmd=" + controCmd +
                ", dataLen=" + dataLen +
                ", dataMsgs=" + dataMsgs +
                '}';
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
