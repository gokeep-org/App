package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.CombustibleGasMonitorDevice;
import com.app.dtu.repository.CombustibleGasMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service(ServiceBeanNames.COMBUSTIBLE_GAS_SERVICE)
public class CombustibleGasMonitorServiceImpl extends BaseService implements DataService<CombustibleGasMonitorDevice> {

    private static final Logger logger = LoggerFactory.getLogger(CombustibleGasMonitorServiceImpl.class);
    @Autowired
    private CombustibleGasMonitorReponsitory combustibleGasMonitorReponsitory;

    @Override
    public boolean save(CombustibleGasMonitorDevice deviceData) {
        if (Objects.isNull(deviceData)){
            logger.info("Save device data is null");
            return false;
        }
        combustibleGasMonitorReponsitory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        if (StringUtils.isEmpty(messageId)){
            logger.info("update old device status is null");
            return false;
        }
        combustibleGasMonitorReponsitory.updateOldDataStatus(messageId);
        return true;
    }

    @Override
    public boolean updateOffLineData() {

        return false;
    }
}
