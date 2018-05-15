package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.ScreenMonitorDevice;
import com.app.dtu.repository.ScreenMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.dtu.util.DtuUtil;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service(ServiceBeanNames.SCREEN_SERVICE)
public class ScreenFeeMonitorServiceImpl extends BaseService implements DataService<ScreenMonitorDevice> {
    @Autowired
    private ScreenMonitorReponsitory screenMonitorReponsitory;
    @Override
    public boolean save(ScreenMonitorDevice deviceData) {
        screenMonitorReponsitory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        screenMonitorReponsitory.updateOldDataStatus(messageId);
        return true;
    }

    @Override
    public boolean updateOffLineData(String messageId) {
        try{
            List<ScreenMonitorDevice> devices = screenMonitorReponsitory.findByMessageIdAndCreateDateGreaterThanEqual(messageId, DtuUtil.getUpdateOfflineTaskTime());
            if (CollectionUtils.isEmpty(devices)){
                // 加一条
                ScreenMonitorDevice device = new ScreenMonitorDevice();
                device.setMessage(device.getOfflineMessage(messageId));
                device.generateEntity(device.getOfflineMessage(messageId));
                updateOldDataStatus(messageId);
                screenMonitorReponsitory.save(device);
            }
            return true;
        }catch (Throwable e){
            return false;
        }
    }
}
