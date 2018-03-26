package com.app.dtu.service;


import com.app.dtu.bean.model.monitormanager.MonitorManagerDeviceV2;
import com.app.dtu.repository.MonitorManagerDeviceReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "dataServiceImpl")
public class DateServiceImpl implements DataService<MonitorManagerDeviceV2>{
//    @Autowired
//    MonitorManagerDeviceReponsitory monitorManagerDeviceReponsitory;
    @Autowired
    MonitorManagerDeviceReponsitory monitorManagerDeviceReponsitory;
    @Override
    public boolean save(MonitorManagerDeviceV2 deviceData) {
        monitorManagerDeviceReponsitory.save(deviceData);
        return true;
    }
}
