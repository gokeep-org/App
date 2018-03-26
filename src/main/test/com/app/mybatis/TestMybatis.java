package com.app.mybatis;


import com.app.AppStart;
import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.monitormanager.MonitorManagerDeviceV2;
import com.app.dtu.repository.MonitorManagerDeviceReponsitory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import com.app.dtu.repository.DeviceRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AppStart.class)
@ActiveProfiles(value = "dev")
public class TestMybatis {
    private static final Logger logger = LoggerFactory.getLogger(TestMybatis.class);

    @Autowired
    MonitorManagerDeviceReponsitory monitorManagerDeviceReponsitory;

    @Test
    public void testJpa(){
        Message message = new Message();
        message.setId("9170000101000511");
        ParseToEntityAdapter parseToEntityAdapter = new MonitorManagerDeviceV2(message);
        MonitorManagerDeviceV2 device = (MonitorManagerDeviceV2) parseToEntityAdapter.parseEntity();
        monitorManagerDeviceReponsitory.save(device);
        logger.info("ok");

    }
}
