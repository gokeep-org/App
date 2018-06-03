package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.ElectricalFireMonitorDevice;
import com.app.dtu.repository.ElectricalFireMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.dtu.util.DtuUtil;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service(ServiceBeanNames.ELECTRICAL_FIRE_SERVICE)
public class ElectricalFireMonitorImpl extends BaseService implements DataService<ElectricalFireMonitorDevice> {
    @Autowired
    private ElectricalFireMonitorReponsitory electricalFireMonitorReponsitory;

    @Override
    public boolean save(ElectricalFireMonitorDevice deviceData) {
        electricalFireMonitorReponsitory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        electricalFireMonitorReponsitory.updateOldDataStatus(messageId);
        return true;
    }

    @Override
    public boolean updateOffLineData(String messageId) {
        try{
            List<ElectricalFireMonitorDevice> devices = electricalFireMonitorReponsitory.findByMessageIdAndCreateDateGreaterThanEqual(messageId, DtuUtil.getUpdateOfflineTaskTime());
            if (CollectionUtils.isEmpty(devices)){
                updateOldDataStatus(messageId);
                // 加一条
                ElectricalFireMonitorDevice device = new ElectricalFireMonitorDevice();
                device.setMessage(device.getOfflineMessage(messageId));
                device.generateEntity(device.getOfflineMessage(messageId));
                device.setOld_flag(0);
                electricalFireMonitorReponsitory.save(device);
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
            electricalFireMonitorReponsitory.updatePreviousDataStatus(id, status);
            return true;
        }catch (Throwable e){
            return false;
        }
    }
}
