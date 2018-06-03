package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.FireControlPowerMonitorDevice;
import com.app.dtu.repository.FireControlPowerMonitorReponstory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.dtu.util.DtuUtil;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service(ServiceBeanNames.FIRE_CONTROL_POWER_SERVICE)
public class FireControlPowerMonitorServiceImpl extends BaseService implements DataService<FireControlPowerMonitorDevice> {
    @Autowired
    private FireControlPowerMonitorReponstory fireControlPowerMonitorReponstory;

    @Override
    public boolean save(FireControlPowerMonitorDevice deviceData) {
        fireControlPowerMonitorReponstory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        fireControlPowerMonitorReponstory.updateOldDataStatus(messageId);
        return true;
    }

    @Override
    public boolean updateOffLineData(String messageId) {
        try{
            List<FireControlPowerMonitorDevice> devices = fireControlPowerMonitorReponstory.findByMessageIdAndCreateDateGreaterThanEqual(messageId, DtuUtil.getUpdateOfflineTaskTime());
            if (CollectionUtils.isEmpty(devices)){
                updateOldDataStatus(messageId);
                // 加一条
                FireControlPowerMonitorDevice device = new FireControlPowerMonitorDevice();
                device.setMessage(device.getOfflineMessage(messageId));
                device.generateEntity(device.getOfflineMessage(messageId));
                device.setOld_flag(0);
                fireControlPowerMonitorReponstory.save(device);
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
            fireControlPowerMonitorReponstory.updatePreviousDataStatus(id, status);
            return true;
        }catch (Throwable e){
            return false;
        }
    }
}
