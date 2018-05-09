package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.SmokeFeelMonitorDevice;
import com.app.dtu.repository.SmokeFeeMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(ServiceBeanNames.SMOKE_FEEL_SERVICE)
public class SmokeFeeMonitorServiceImpl extends BaseService implements DataService<SmokeFeelMonitorDevice> {

    @Autowired
    private SmokeFeeMonitorReponsitory smokeFeeMonitorReponsitory;
    @Override
    public boolean save(SmokeFeelMonitorDevice deviceData) {
        smokeFeeMonitorReponsitory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        smokeFeeMonitorReponsitory.updateOldDataStatus(messageId);
        return true;
    }

    @Override
    public boolean updateOffLineData() {
        return false;
    }
}
