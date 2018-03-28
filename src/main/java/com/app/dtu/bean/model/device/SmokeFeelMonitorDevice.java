package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 烟感设备
 */

@Entity
@Table(name = "scree_monitor_device")
public class SmokeFeelMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<SmokeFeelMonitorDevice> {
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public SmokeFeelMonitorDevice buildDevice() {
        return null;
    }

    @Override
    public Message buildMessage() {
        return null;
    }

    @Override
    public SmokeFeelMonitorDevice generateEntity(Message message) {
        return null;
    }
}
