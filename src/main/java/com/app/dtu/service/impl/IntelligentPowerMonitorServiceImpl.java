package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.IntelligentPowerMonitorDevice;
import com.app.dtu.repository.IntelligentPowerMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.dtu.util.DtuUtil;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service(ServiceBeanNames.INTELLIGENT_POWER_SERVICE)
public class IntelligentPowerMonitorServiceImpl extends BaseService implements DataService<IntelligentPowerMonitorDevice> {

    @Autowired
    private IntelligentPowerMonitorReponsitory intelligentPowerMonitorReponsitory;
    @Override
    public boolean save(IntelligentPowerMonitorDevice deviceData) {
        intelligentPowerMonitorReponsitory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        intelligentPowerMonitorReponsitory.updateOldDataStatus(messageId);
        return true;
    }

    @Override
    public boolean updateOffLineData(String messageId) {
        try{
            List<IntelligentPowerMonitorDevice> devices = intelligentPowerMonitorReponsitory.findByMessageIdAndCreateDateGreaterThanEqual(messageId, DtuUtil.getUpdateOfflineTaskTime());
            if (CollectionUtils.isEmpty(devices)){
                updateOldDataStatus(messageId);
                // 加一条
                IntelligentPowerMonitorDevice device = new IntelligentPowerMonitorDevice();
                device.setMessage(device.getOfflineMessage(messageId));
                device.generateEntity(device.getOfflineMessage(messageId));
                device.setOld_flag(0);
                intelligentPowerMonitorReponsitory.save(device);
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
            intelligentPowerMonitorReponsitory.updatePreviousDataStatus(id, status);
            return true;
        }catch (Throwable e){
            return false;
        }
    }
}
