package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.FaultArcMonitorDevice;
import com.app.dtu.repository.FaultArcMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.dtu.util.DtuUtil;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service(ServiceBeanNames.FAULT_ARC_SERVICE_SERVICE)
public class FaultArcMonitorServiceImpl extends BaseService implements DataService<FaultArcMonitorDevice> {
    @Autowired
    private FaultArcMonitorReponsitory faultArcMonitorReponsitory;

    @Override
    public boolean save(FaultArcMonitorDevice deviceData) {
        faultArcMonitorReponsitory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        faultArcMonitorReponsitory.updateOldDataStatus(messageId);
        return true;
    }

    @Override
    public boolean updateOffLineData(String messageId) {
        try{
            List<FaultArcMonitorDevice> devices = faultArcMonitorReponsitory.findByCreateDateGreaterThanEqual(DtuUtil.getUpdateOfflineTaskTime());
            if (CollectionUtils.isEmpty(devices)){
                // 加一条
                FaultArcMonitorDevice device = new FaultArcMonitorDevice();
                device.setMessage(device.getOfflineMessage(messageId));
                device.generateEntity(device.getOfflineMessage(messageId));
                updateOldDataStatus(messageId);
                faultArcMonitorReponsitory.save(device);
            }
            return true;
        }catch (Throwable e){
            return false;
        }
    }
}
