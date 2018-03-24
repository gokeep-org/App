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
     * 解析消息实体到数据传输对象实体
     * @param message
     * @return
     */
    @Override
    public MonitorManagerDevice generateEntity(Message message) {
        this.data = message.getId();
        return this;
    }

}
