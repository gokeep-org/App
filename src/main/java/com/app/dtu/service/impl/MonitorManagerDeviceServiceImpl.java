package com.app.dtu.service.impl;


import com.app.dtu.bean.model.device.MonitorManagerDevice;
import com.app.dtu.repository.MonitorManagerDeviceReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.dtu.util.DtuUtil;
import com.app.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
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
    @Override
    public boolean updateOffLineData(String messageId) {
        try{
            List<MonitorManagerDevice> devices = monitorManagerDeviceReponsitory.findByMessageIdAndCreateDateGreaterThanEqual(messageId, DtuUtil.getUpdateOfflineTaskTime());
            if (CollectionUtils.isEmpty(devices)){
                updateOldDataStatus(messageId);
                // 加一条
                MonitorManagerDevice device = new MonitorManagerDevice();
                device.setMessage(device.getOfflineMessage(messageId));
                device.generateEntity(device.getOfflineMessage(messageId));
                device.setOld_flag(0);
                monitorManagerDeviceReponsitory.save(device);
            }
            return true;
        }catch (Throwable e){
            return false;
        }
    }

    @Override
    public boolean PreviousDataStatus(String id, Integer status) {
        if (id == null) {
            return false;
        }
        try {
            monitorManagerDeviceReponsitory.updatePreviousDataStatus(id, status);
            return true;
        }catch (Throwable e){
            return false;
        }
    }
}
