package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.CombustibleGasMonitorDevice;
import com.app.dtu.repository.CombustibleGasMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.dtu.util.DtuUtil;
import com.app.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
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
    public boolean updateOffLineData(String messageId) {
        try{
            List<CombustibleGasMonitorDevice> devices = combustibleGasMonitorReponsitory.findByCreateDateGreaterThanEqual(DtuUtil.getBeforeTimeFor48Hors());
            if (CollectionUtils.isEmpty(devices)){
                CombustibleGasMonitorDevice device = new CombustibleGasMonitorDevice();
                device.setMessage(device.getOfflineMessage(messageId));
                device.generateEntity(device.getOfflineMessage(messageId));
                updateOldDataStatus(messageId);
                combustibleGasMonitorReponsitory.save(device);
            }
            return true;
        }catch (Throwable e){
            return false;
        }
    }
}
