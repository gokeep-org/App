package com.app.dtu.service.impl;


import com.app.dtu.bean.model.device.MonitorManagerDevice;
import com.app.dtu.repository.MonitorManagerDeviceReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service(value = ServiceBeanNames.MONITOR_MNAAGER_DEVICE_SERVICE)
public class MonitorManagerDeviceServiceImpl extends BaseService implements DataService<MonitorManagerDevice> {
    private static final Logger logger = LoggerFactory.getLogger(MonitorManagerDevice.class);

    @Autowired
    MonitorManagerDeviceReponsitory monitorManagerDeviceReponsitory;


    @Override
    public boolean save(MonitorManagerDevice deviceData) {
        if (Objects.isNull(deviceData)){
            logger.info("Save device data is null");
            return false;
        }
        monitorManagerDeviceReponsitory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        monitorManagerDeviceReponsitory.updateOldDataStatus(messageId);
        return true;
    }
}
