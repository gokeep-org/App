package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;
import com.app.dtu.config.DtuConfig;

import javax.persistence.*;

/**
 * 消防设备电源监控-04
 */

@Entity
@Table(name = DtuConfig.DTU_TABLE_PRIFIX +"fire_control_power_monitor_device")
public class FireControlPowerMonitorDevice  extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<FireControlPowerMonitorDevice> {

    public FireControlPowerMonitorDevice(Message message) {
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
    public FireControlPowerMonitorDevice buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }

    @Override
    public FireControlPowerMonitorDevice generateEntity(Message message) {
        return null;
    }
}
