package com.app.mybatis;


import com.app.AppStart;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import com.app.dtu.repository.DeviceRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AppStart.class)
@ActiveProfiles(value = "dev")
public class TestMybatis {
    private static final Logger logger = LoggerFactory.getLogger(TestMybatis.class);

//    @Autowired
////    UserMapper userMapper;
////
////    @Test
////    public void testSearchAllUser(){
////        List<User> users = userMapper.selectAll();
////        logger.info("user");
////    }
//    @Autowired
//    DeviceRepository repository;
//    @Test
//    public void testJpa(){
//        Device device = new Device(new Message());
//        device.setTerminalNo("aaaaa");
//        repository.save(device);
//        logger.info("ok");
//
//    }
}
