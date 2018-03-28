package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 故障电弧监控-03
 */
@Entity
@Table(name = "fault_arc_monitor_device")
public class FaultArcMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<FaultArcMonitorDevice> {
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public FaultArcMonitorDevice buildDevice() {
        return null;
    }

    @Override
    public Message buildMessage() {
        return null;
    }

    @Override
    public FaultArcMonitorDevice generateEntity(Message message) {
        return null;
    }
}
