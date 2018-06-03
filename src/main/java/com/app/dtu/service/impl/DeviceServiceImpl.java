package com.app.dtu.service.impl;

import com.app.dtu.bean.model.Device;
import com.app.dtu.repository.DeviceRepository;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service(ServiceBeanNames.DEVICE_SERVICE)
public class DeviceServiceImpl  extends BaseService implements DataService<Device> {
    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public boolean save(Device deviceData) {
        if (Objects.isNull(deviceData)){
            logger.info("Save device data is null");
            return false;
        }
        deviceRepository.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        return false;
    }

    @Override
    public boolean updateOffLineData(String messageId) {
        return true;
    }

    @Override
    public boolean updatePreviousDataStatus(String id, Integer status) {
        return true;
    }
}
