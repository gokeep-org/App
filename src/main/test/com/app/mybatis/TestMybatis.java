package com.app.mybatis;


import com.app.AppStart;
import com.app.dtu.bean.model.DeviceTypeName;
import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.Device;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.device.MonitorManagerDevice;
import com.app.dtu.repository.DeviceRepository;
import com.app.dtu.repository.MonitorManagerDeviceReponsitory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.app.dtu.repository.DeviceRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AppStart.class)
@ActiveProfiles(value = "dev")
public class TestMybatis {
    private static final Logger logger = LoggerFactory.getLogger(TestMybatis.class);

    @Autowired
    MonitorManagerDeviceReponsitory monitorManagerDeviceReponsitory;

    @Autowired
    DeviceRepository deviceRepository;

    @Test
    public void initDeviceModelTable(){
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


    @Test
    public void testJpa(){
        Message message = new Message();
        message.setId("9170000101000511");
        ParseToEntityAdapter parseToEntityAdapter = new MonitorManagerDevice(message);
        MonitorManagerDevice device = (MonitorManagerDevice) parseToEntityAdapter.parseEntity();
        monitorManagerDeviceReponsitory.save(device);
        logger.info("ok");
    }
}
