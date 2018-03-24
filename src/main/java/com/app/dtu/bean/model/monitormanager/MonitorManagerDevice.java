package com.app.dtu.bean.model.monitormanager;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.Device;

import javax.persistence.*;

/**
 * 监控管理设备实体
 */
@Entity
@Table(name = "monitor_manager_device")
public class MonitorManagerDevice extends Device{
    public MonitorManagerDevice(Message message) {
        super(message);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  int warnList;

    private String warnDesc;


    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

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

    /**
     * 解析消息实体到数据传输对象实体
     * @param message
     * @return
     */
    @Override
    public MonitorManagerDevice generateEntity(Message message) {
        setCreateTime(System.currentTimeMillis());
        setTerminalNo(message.parseTerminaNo());
//        setTerminalAddress(message.parseTerminaAddress());
        setTerminalId(message.getId());
        this.warnList = message.getWarnList();
        this.warnList = message.getWarnList();
        message.getDataMsgs().stream().forEach(dataMsg -> {

        });
        return this;
    }

}
