package com.app.dtu.bean.model;

import com.app.dtu.config.DtuConfig;
import com.app.dtu.repository.DeviceRepository;
import com.app.dtu.service.ServiceBeanNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service(ServiceBeanNames.INIT_MODEL_TYPE_TABLE_DATA_SERVICE)
public class InitDataTask extends Thread{
    private static final Logger logger = LoggerFactory.getLogger(InitDataTask.class);

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public void run() {
        if (!DtuConfig.IS_CHECK_INIT_DEVICE_MODEL_DATA){
            return;
        }
        try {
//            initFetchDeviceModelCodeTask();
            if (isNeedInitDeviceTypeData()){
                initDeviceModelTable();
            }
        }catch (Throwable e){
            logger.error("Init device model data is fail, ", e.getMessage());
        }

    }

    public boolean isNeedInitDeviceTypeData(){
        long count = deviceRepository.count();
        if (count != 32){
            return true;
        }
        return false;
    }

    public void initDeviceModelTable(){
        deviceRepository.deleteAll();
        List<Device> devices = new ArrayList<>();
        DeviceTypeName.findAll().stream().forEach(deviceTypeName -> {
            Device device = new Device();
            device.setCreateTime(new Date().getTime());
            device.setModel(deviceTypeName.getDeviceModelCode());
            device.setModelCode(deviceTypeName.getModelCode());
            device.setModelName(deviceTypeName.getModelName());
            device.setTypeCode(deviceTypeName.getTypeCode());
            device.setTypeName(deviceTypeName.getTypeName());
            devices.add(device);
        });
        if (!CollectionUtils.isEmpty(devices)){
            deviceRepository.save(devices);
        }
    }

    public void initFetchDeviceModelCodeTask(){
//        ScheduleUpdateLocalCache.updateDeviceModelCode();
    }
}
