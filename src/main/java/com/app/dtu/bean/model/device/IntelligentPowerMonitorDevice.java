package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 智能电力设监控设别-02
 */
@Entity
@Table(name = "intelligent_power_monitor_device")
public class IntelligentPowerMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<IntelligentPowerMonitorDevice> {
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public IntelligentPowerMonitorDevice buildDevice() {
        return null;
    }

    @Override
    public Message buildMessage() {
        return null;
    }

    @Override
    public IntelligentPowerMonitorDevice generateEntity(Message message) {
        return null;
    }
}
