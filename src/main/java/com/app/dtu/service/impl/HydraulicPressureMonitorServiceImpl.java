package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.HydraulicPressureMonitorDevice;
import com.app.dtu.repository.HydraulicPressureMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(ServiceBeanNames.HYDRAULIC_PRESSURE_SERVICE)
public class HydraulicPressureMonitorServiceImpl extends BaseService implements DataService<HydraulicPressureMonitorDevice> {
    @Autowired
    private HydraulicPressureMonitorReponsitory hydraulicPressureMonitorReponsitory;

    @Override
    public boolean save(HydraulicPressureMonitorDevice deviceData) {
        hydraulicPressureMonitorReponsitory.save(deviceData);
        return true;
    }
}
