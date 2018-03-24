package com.app.dtu.bean.model.monitormanager;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.Device;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监控管理设备实体
 */
@Mapper
public class MonitorManagerDevice extends Device {

    public MonitorManagerDevice(Message message) {
        super(message);
    }
    private String data;


    /**
     * 消息解析到实体
     * @param message
     * @return
     */
    @Override
    public MonitorManagerDevice generateEntity(Message message) {
        this.data = message.getId();
        return this;
    }

    public static void main(String[] args) {
        Message message = new Message();
        message.setId("aaaa");
        Device device = new MonitorManagerDevice(message);
        MonitorManagerDevice monitorManagerDevice = (MonitorManagerDevice) device.parseEntity();
        System.out.println(device);
    }

}
