package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.IntelligentPowerMonitorDevice;
import com.app.dtu.repository.IntelligentPowerMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(ServiceBeanNames.INTELLIGENT_POWER_SERVICE)
public class IntelligentPowerMonitorServiceImpl extends BaseService implements DataService<IntelligentPowerMonitorDevice> {

    @Autowired
    private IntelligentPowerMonitorReponsitory intelligentPowerMonitorReponsitory;
    @Override
    public boolean save(IntelligentPowerMonitorDevice deviceData) {
        intelligentPowerMonitorReponsitory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        intelligentPowerMonitorReponsitory.updateOldDataStatus(messageId);
        return true;
    }
}
