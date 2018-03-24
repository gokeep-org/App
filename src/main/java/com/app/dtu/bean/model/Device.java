package com.app.dtu.bean.model;

import com.app.dtu.bean.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备的基础表
 * 用于存储设备的基础信息
 */

@Mapper
public class Device implements ParseToEntityAdapter<Device>{

    public Device(Message message) {
        this.message = message;
    }

    // 设备终端ID
    private String terminalId;
    // 设备类别编号
    private String typeNo;
    // 设备类别名称
    private String typeName;
    // 终端型号
    private String terminalNo;
    // 终端型号名称
    private String terminalName;
    // 终端地址
    private String terminalAddress;
    // 监控数据项
    private String monitorDateItem;

    // 管理机状态
    private String managerDeviceStatus;


    private Message message;

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public String getTerminalAddress() {
        return terminalAddress;
    }

    public void setTerminalAddress(String terminalAddress) {
        this.terminalAddress = terminalAddress;
    }

    public String getMonitorDateItem() {
        return monitorDateItem;
    }

    public void setMonitorDateItem(String monitorDateItem) {
        this.monitorDateItem = monitorDateItem;
    }

    public String getManagerDeviceStatus() {
        return managerDeviceStatus;
    }

    public void setManagerDeviceStatus(String managerDeviceStatus) {
        this.managerDeviceStatus = managerDeviceStatus;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public Device buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return message;
    }

    /**
     * 此方法需要子类去重写,否则无法生成实体
     * @param message
     * @return
     */
    @Override
    public Device generateEntity(Message message) {
        return this;
    }
}
