package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.FireControlPowerMonitorDevice;
import com.app.dtu.repository.FireControlPowerMonitorReponstory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(ServiceBeanNames.FIRE_CONTROL_POWER_SERVICE)
public class FireControlPowerMonitorServiceImpl extends BaseService implements DataService<FireControlPowerMonitorDevice> {
    @Autowired
    private FireControlPowerMonitorReponstory fireControlPowerMonitorReponstory;

    @Override
    public boolean save(FireControlPowerMonitorDevice deviceData) {
        fireControlPowerMonitorReponstory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        fireControlPowerMonitorReponstory.updateOldDataStatus(messageId);
        return true;
    }
}
