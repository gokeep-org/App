package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;

import javax.persistence.*;

/**
 * 烟感设备
 */

@Entity
@Table(name = "scree_monitor_device")
public class SmokeFeelMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<SmokeFeelMonitorDevice> {

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
