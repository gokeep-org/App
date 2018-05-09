package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.HydraulicPressureMonitorDevice;
import com.app.dtu.repository.HydraulicPressureMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.dtu.util.DtuUtil;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service(ServiceBeanNames.HYDRAULIC_PRESSURE_SERVICE)
public class HydraulicPressureMonitorServiceImpl extends BaseService implements DataService<HydraulicPressureMonitorDevice> {
    @Autowired
    private HydraulicPressureMonitorReponsitory hydraulicPressureMonitorReponsitory;

    @Override
    public boolean save(HydraulicPressureMonitorDevice deviceData) {
        hydraulicPressureMonitorReponsitory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        hydraulicPressureMonitorReponsitory.updateOldDataStatus(messageId);
        return true;
    }

    @Override
    public boolean updateOffLineData(String messageId) {
        try{
            List<HydraulicPressureMonitorDevice> devices = hydraulicPressureMonitorReponsitory.findByCreateDateGreaterThanEqual(DtuUtil.getBeforeTimeFor48Hors());
            if (CollectionUtils.isEmpty(devices)){
                // 加一条
                HydraulicPressureMonitorDevice device = new HydraulicPressureMonitorDevice();
                device.setMessage(device.getOfflineMessage(messageId));
                device.generateEntity(device.getOfflineMessage(messageId));
                updateOldDataStatus(messageId);
                hydraulicPressureMonitorReponsitory.save(device);
            }
            return true;
        }catch (Throwable e){
            return false;
        }
    }
}