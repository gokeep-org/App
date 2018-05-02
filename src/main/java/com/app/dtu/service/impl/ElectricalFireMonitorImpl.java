package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.ElectricalFireMonitorDevice;
import com.app.dtu.repository.ElectricalFireMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(ServiceBeanNames.ELECTRICAL_FIRE_SERVICE)
public class ElectricalFireMonitorImpl extends BaseService implements DataService<ElectricalFireMonitorDevice> {
    @Autowired
    private ElectricalFireMonitorReponsitory electricalFireMonitorReponsitory;

    @Override
    public boolean save(ElectricalFireMonitorDevice deviceData) {
        electricalFireMonitorReponsitory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        electricalFireMonitorReponsitory.updateOldDataStatus(messageId);
        return true;
    }
}
