package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;
import com.app.dtu.config.DtuConfig;

import javax.persistence.*;

/**
 * 故障电弧监控-03
 */
@Entity
@Table(name = DtuConfig.DTU_TABLE_PRIFIX +"fault_arc_monitor_device")
public class FaultArcMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<FaultArcMonitorDevice> {


    public FaultArcMonitorDevice(Message message) {
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
