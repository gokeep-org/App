package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 监控管理设备实体-00
 */
@Entity
@Table(name = "monitor_manager_device")
public class MonitorManagerDevice implements DeviceDataDeal, ParseToEntityAdapter<MonitorManagerDevice>, Serializable {


    @Transient
    private Message message;

    public MonitorManagerDevice(Message message) {
        this.message = message;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 数据创建时间
    private long createDate;

    // 设备型号ID, 即device_id
    private long terminalNo = -1L;

    private  int warnList;

    private String warnDesc;

    public int getWarnList() {
        return warnList;
    }
    public void setWarnList(int warnList) {
        this.warnList = warnList;
    }

    public String getWarnDesc() {
        return warnDesc;
    }

    public void setWarnDesc(String warnDesc) {
        this.warnDesc = warnDesc;
    }

    @Override
    public MonitorManagerDevice buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return this.message;
    }

    /**
     * 解析消息实体到数据传输对象实体
     * @param message
     * @return
     */
    @Override
    public MonitorManagerDevice generateEntity(Message message) {
//        setTerminalAddress(message.parseTerminaAddress());
        this.warnList = message.getWarnList();
        this.warnList = message.getWarnList();
        return this;
    }

}
