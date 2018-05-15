package com.app.mybatis;


import com.app.AppStart;
import com.app.dtu.bean.model.DeviceTypeName;
import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.Device;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.device.CombustibleGasMonitorDevice;
import com.app.dtu.bean.model.device.ElectricalFireMonitorDevice;
import com.app.dtu.bean.model.device.MonitorManagerDevice;
import com.app.dtu.repository.CombustibleGasMonitorReponsitory;
import com.app.dtu.repository.DeviceRepository;
import com.app.dtu.repository.ElectricalFireMonitorReponsitory;
import com.app.dtu.repository.MonitorManagerDeviceReponsitory;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.dtu.util.DtuUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    ElectricalFireMonitorReponsitory electricalFireMonitorReponsitory;

    @Autowired
    CombustibleGasMonitorReponsitory combustibleGasMonitorReponsitory;

    @Autowired
    @Qualifier(ServiceBeanNames.COMBUSTIBLE_GAS_SERVICE)
    DataService dataService;

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
        MonitorManagerDevice device = (MonitorManagerDevice) parseToEntityAdapter.getStorageEntity();
        monitorManagerDeviceReponsitory.save(device);
        logger.info("ok");
    }

    @Test
    public void testCombustible(){
        CombustibleGasMonitorDevice device = new CombustibleGasMonitorDevice();
        device.setMessageId("1802080602000153");
        device.setOld_flag(1);
        combustibleGasMonitorReponsitory.updateOldDataStatus("1802080602000153");
        logger.info("");
    }

    @Test
    public void testThandNowTime(){
        List<CombustibleGasMonitorDevice> combustibleGasMonitorDevices = combustibleGasMonitorReponsitory.findByCreateDateGreaterThanEqual(DtuUtil.getUpdateOfflineTaskTime());
        logger.info(combustibleGasMonitorDevices.toString());
    }

    @Test
    public void testDataSevieOffline(){
        dataService.updateOffLineData("1802080601000152");
        logger.info("");
    }

    @Test
    public void testMessageAngGrantEqual(){
        List<ElectricalFireMonitorDevice> devices = electricalFireMonitorReponsitory.findByMessageIdAndCreateDateGreaterThanEqual("180408010200000", 0);
        logger.info(devices.toString());
    }
}
