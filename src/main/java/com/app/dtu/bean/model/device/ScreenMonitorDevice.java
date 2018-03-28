package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 视屏监控08
 */

@Entity
@Table(name = "scree_monitor_device")
public class ScreenMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<ScreenMonitorDevice> {

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public ScreenMonitorDevice buildDevice() {
        return null;
    }

    @Override
    public Message buildMessage() {
        return null;
    }

    @Override
    public ScreenMonitorDevice generateEntity(Message message) {
        return null;
    }
}
