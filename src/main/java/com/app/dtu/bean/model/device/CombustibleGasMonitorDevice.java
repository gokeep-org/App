package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 可燃气体监控-06
 */
@Entity
@Table(name = "combustible_gas_monitor_device")
public class CombustibleGasMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<CombustibleGasMonitorDevice>{

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public CombustibleGasMonitorDevice buildDevice() {
        return null;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }

    @Override
    public CombustibleGasMonitorDevice generateEntity(Message message) {
        return this;
    }
}
