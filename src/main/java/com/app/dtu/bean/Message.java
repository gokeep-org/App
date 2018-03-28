package com.app.dtu.bean;

import com.app.dtu.bean.model.Device;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.device.MonitorManagerDevice;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息传输实体
 */
public class Message {
    private String id;
    private int warnList;
    private int controCmd;
    private int dataLen;
    private List<DataMsg> dataMsgs;

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
        return this.id.substring(8, 10);
    }


    /**
     * 解析设备型号
     * @return
     */
    public String parseModelCode(){
        return this.id.substring(6, 10);
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
     * 获取到具体的设备处理
     * @return
     */
    public DeviceDataDeal getDevice(){
        String terminaTypeCode = parseTypeCode();
        switch (terminaTypeCode){
            case "00" : return new MonitorManagerDevice(this);
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
}
