package com.app.mybatis;


import com.app.AppStart;
import com.app.dtu.bean.dao.impl.UserMapper;
import com.app.dtu.bean.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AppStart.class)
@ActiveProfiles(value = "dev")
public class TestMybatis {
    private static final Logger logger = LoggerFactory.getLogger(TestMybatis.class);

    @Autowired
    UserMapper userMapper;

    @Test
    public void testSearchAllUser(){
        List<User> users = userMapper.selectAll();
        logger.info("user");
    }
}
