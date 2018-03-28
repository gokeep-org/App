package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 消防设备电源监控-04
 */

@Entity
@Table(name = "fire_control_power_monitor_device")
public class FireControlPowerMonitorDevice  extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<FireControlPowerMonitorDevice> {
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public FireControlPowerMonitorDevice buildDevice() {
        return null;
    }

    @Override
    public Message buildMessage() {
        return null;
    }

    @Override
    public FireControlPowerMonitorDevice generateEntity(Message message) {
        return null;
    }
}
