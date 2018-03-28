package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;

import javax.persistence.*;

/**
 * 水压监控-12
 */
@Entity
@Table(name = "hydraulic_pressure_monitor_device")
public class HydraulicPressureMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<HydraulicPressureMonitorDevice> {

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
    public HydraulicPressureMonitorDevice buildDevice() {
        return null;
    }

    @Override
    public Message buildMessage() {
        return null;
    }

    @Override
    public HydraulicPressureMonitorDevice generateEntity(Message message) {
        return null;
    }
}
