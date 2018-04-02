package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.FaultArcMonitorDevice;
import com.app.dtu.repository.FaultArcMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(ServiceBeanNames.FAULT_ARC_SERVICE_SERVICE)
public class FaultArcMonitorServiceImpl extends BaseService implements DataService<FaultArcMonitorDevice> {
    @Autowired
    private FaultArcMonitorReponsitory faultArcMonitorReponsitory;

    @Override
    public boolean save(FaultArcMonitorDevice deviceData) {
        faultArcMonitorReponsitory.save(deviceData);
        return true;
    }
}
