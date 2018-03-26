package com.app.dtu.service;


import com.app.dtu.bean.model.device.MonitorManagerDevice;
import com.app.dtu.repository.MonitorManagerDeviceReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "dataServiceImpl")
public class DateServiceImpl implements DataService<MonitorManagerDevice> {

    @Autowired
    MonitorManagerDeviceReponsitory monitorManagerDeviceReponsitory;


    @Override
    public boolean save(MonitorManagerDevice deviceData) {
        monitorManagerDeviceReponsitory.save(deviceData);
        return true;
    }
}
