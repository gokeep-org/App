package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;
import com.app.dtu.config.DtuConfig;

import javax.persistence.*;

/**
 * 防火门监控设备
 */
@Entity
@Table(name =  DtuConfig.DTU_TABLE_PRIFIX +"prven_fir_door_monitor_device")
public class PrventFireDoorMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<PrventFireDoorMonitorDevice> {

    public PrventFireDoorMonitorDevice(Message message) {
        setMessage(message);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public PrventFireDoorMonitorDevice buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }

    @Override
    public PrventFireDoorMonitorDevice generateEntity(Message message) {
        return null;
    }
}
