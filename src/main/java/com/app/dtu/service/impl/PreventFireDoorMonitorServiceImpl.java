package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.PrventFireDoorMonitorDevice;
import com.app.dtu.repository.PreventFireDoorMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(ServiceBeanNames.PRVENT_FIRE_DOOR_SERVICE)
public class PreventFireDoorMonitorServiceImpl extends BaseService implements DataService<PrventFireDoorMonitorDevice> {
    @Autowired
    PreventFireDoorMonitorReponsitory preventFireDoorMonitorReponsitory;

    @Override
    public boolean save(PrventFireDoorMonitorDevice deviceData) {
        preventFireDoorMonitorReponsitory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        preventFireDoorMonitorReponsitory.updateOldDataStatus(messageId);
        return true;
    }

    @Override
    public boolean updateOffLineData() {
        return false;
    }
}
