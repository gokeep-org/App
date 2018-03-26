package com.app.dtu.bean.model;

import com.app.dtu.bean.Message;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 设备的基础类
 * 用于存储设备的基础信息
 * 其他的走具体的设备处理， 数据库中会表示为一个存储具体设备信息的码表
 * 其他的表根据device_id与之做关联，其他的表示具体上报的数据信息存储
 */

@Entity
@Table(name = "device")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Device implements DeviceDataDeal, ParseToEntityAdapter<Device>, Serializable{

    public Device(Message message) {
        this.message = message;
    }
    // 表唯一性ID,表示设备在数据库中唯一存储的ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String sn;
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
    // 创建时间
    private long createTime;
    // 该字段不应该被数据库所存储
    @Transient
    private Message message;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * 此方法需要子类去重写,否则无法生成实体
     * @param message
     * @return
     */
    @Override
    public Device generateEntity(Message message) {
        String terminaIdString = message.getId();
        if (StringUtils.isEmpty(message) || terminaIdString.length() != 16){
            return null;
        }
        terminaIdString.substring(0, 8);
        return this;
    }
}
