package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 防火门监控设备
 */
@Entity
@Table(name = "prven_fir_door_monitor_device")
public class PrventFireDoorMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<PrventFireDoorMonitorDevice> {
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public PrventFireDoorMonitorDevice buildDevice() {
        return null;
    }

    @Override
    public Message buildMessage() {
        return null;
    }

    @Override
    public PrventFireDoorMonitorDevice generateEntity(Message message) {
        return null;
    }
}
