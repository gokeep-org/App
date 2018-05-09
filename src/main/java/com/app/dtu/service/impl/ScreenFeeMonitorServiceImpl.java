package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.ScreenMonitorDevice;
import com.app.dtu.repository.ScreenMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(ServiceBeanNames.SCREEN_SERVICE)
public class ScreenFeeMonitorServiceImpl extends BaseService implements DataService<ScreenMonitorDevice> {
    @Autowired
    private ScreenMonitorReponsitory screenMonitorReponsitory;
    @Override
    public boolean save(ScreenMonitorDevice deviceData) {
        screenMonitorReponsitory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        screenMonitorReponsitory.updateOldDataStatus(messageId);
        return true;
    }

    @Override
    public boolean updateOffLineData() {
        return false;
    }
}
