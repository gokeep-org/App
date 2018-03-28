package com.app.dtu.bean.model;

import com.app.dtu.bean.DeviceTypeName;
import com.app.dtu.repository.DeviceRepository;
import com.app.dtu.service.ServiceBeanNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service(ServiceBeanNames.INIT_MODEL_TYPE_TABLE_DATA_SERVICE)
public class InitDataTask extends Thread{

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public void run() {
        if (isNeedInitDeviceTypeData()){
            initDeviceModelTable();
        }
    }

    public boolean isNeedInitDeviceTypeData(){
        long count = deviceRepository.count();
        if (count != 32){
            return true;
        }
        return true;
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
}
