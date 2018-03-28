package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;

import javax.persistence.*;

/**
 * 电器火灾监监控设备-01
 */
@Entity
@Table(name = "dtu_combustible_gas_monitor_device")
public class ElectricalFireMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<ElectricalFireMonitorDevice> {

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
    public ElectricalFireMonitorDevice buildDevice() {
        return null;
    }

    @Override
    public Message buildMessage() {
        return null;
    }

    @Override
    public ElectricalFireMonitorDevice generateEntity(Message message) {
        return null;
    }
}
