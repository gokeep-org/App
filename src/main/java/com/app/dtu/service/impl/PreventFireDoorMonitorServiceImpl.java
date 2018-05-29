package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.PrventFireDoorMonitorDevice;
import com.app.dtu.repository.PreventFireDoorMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.dtu.util.DtuUtil;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service(ServiceBeanNames.PRVENT_FIRE_DOOR_SERVICE)
public class PreventFireDoorMonitorServiceImpl extends BaseService implements DataService<PrventFireDoorMonitorDevice> {
    @Autowired
    PreventFireDoorMonitorReponsitory preventFireDoorMonitorReponsitory;

    @Override
    public boolean save(PrventFireDoorMonitorDevice deviceData) {
        preventFireDoorMonitorReponsitory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        preventFireDoorMonitorReponsitory.updateOldDataStatus(messageId);
        return true;
    }
    @Override
    public boolean updateOffLineData(String messageId) {
        try{
            List<PrventFireDoorMonitorDevice> devices = preventFireDoorMonitorReponsitory.findByMessageIdAndCreateDateGreaterThanEqual(messageId, DtuUtil.getUpdateOfflineTaskTime());
            if (CollectionUtils.isEmpty(devices)){
                updateOldDataStatus(messageId);
                // 加一条
                PrventFireDoorMonitorDevice device = new PrventFireDoorMonitorDevice();
                device.setMessage(device.getOfflineMessage(messageId));
                device.generateEntity(device.getOfflineMessage(messageId));
                device.setOld_flag(0);
                preventFireDoorMonitorReponsitory.save(device);
            }
            return true;
        }catch (Throwable e){
            return false;
        }
    }
}
