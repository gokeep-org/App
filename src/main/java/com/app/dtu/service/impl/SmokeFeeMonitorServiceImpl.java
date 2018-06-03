package com.app.dtu.service.impl;

import com.app.dtu.bean.model.device.SmokeFeelMonitorDevice;
import com.app.dtu.repository.SmokeFeeMonitorReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.dtu.util.DtuUtil;
import com.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service(ServiceBeanNames.SMOKE_FEEL_SERVICE)
public class SmokeFeeMonitorServiceImpl extends BaseService implements DataService<SmokeFeelMonitorDevice> {

    @Autowired
    private SmokeFeeMonitorReponsitory smokeFeeMonitorReponsitory;
    @Override
    public boolean save(SmokeFeelMonitorDevice deviceData) {
        smokeFeeMonitorReponsitory.save(deviceData);
        return true;
    }

    @Override
    public boolean updateOldDataStatus(String messageId) {
        smokeFeeMonitorReponsitory.updateOldDataStatus(messageId);
        return true;
    }


    @Override
    public boolean updateOffLineData(String messageId) {
        try{
            List<SmokeFeelMonitorDevice> devices = smokeFeeMonitorReponsitory.findByMessageIdAndCreateDateGreaterThanEqual(messageId, DtuUtil.getUpdateOfflineTaskTime());
            if (CollectionUtils.isEmpty(devices)){
                updateOldDataStatus(messageId);
                // 加一条
                SmokeFeelMonitorDevice device = new SmokeFeelMonitorDevice();
                device.setMessage(device.getOfflineMessage(messageId));
                device.generateEntity(device.getOfflineMessage(messageId));
                device.setOld_flag(0);
                smokeFeeMonitorReponsitory.save(device);
            }
            return true;
        }catch (Throwable e){
            return false;
        }
    }

    @Override
    public boolean updatePreviousDataStatus(String id, Integer status) {
        if (id == null) {
            return false;
        }
        try {
            smokeFeeMonitorReponsitory.updatePreviousDataStatus(id, status);
            return true;
        }catch (Throwable e){
            return false;
        }
    }
}
