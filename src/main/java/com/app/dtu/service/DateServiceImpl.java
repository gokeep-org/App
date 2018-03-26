package com.app.dtu.service;

import com.app.dtu.bean.dao.impl.MonitorManagerDeviceMapper;
import com.app.dtu.bean.model.MonitorManagerDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "dataServiceImpl")
public class DateServiceImpl implements DataService<MonitorManagerDevice>{
//    @Autowired
//    MonitorManagerDeviceReponsitory monitorManagerDeviceReponsitory;
    @Autowired
    MonitorManagerDeviceMapper managerDeviceMapper;
    @Override
    public boolean save(com.app.dtu.bean.model.MonitorManagerDevice deviceData) {
        managerDeviceMapper.insert(deviceData);
        return true;
    }
}
