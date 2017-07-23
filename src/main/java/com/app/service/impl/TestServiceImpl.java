package com.app.service.impl;

import com.app.service.BaseService;
import com.app.service.ServiceBeanNames;
import com.app.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(ServiceBeanNames.TEST_SERVICE)
public class TestServiceImpl extends BaseService implements TestService{
    @Value("${info.str}")
    private String connectionTimeout;
    @Autowired
    TestInter testInter;
    @Autowired
    private TestConfig testConfig;
    @Override
    public String start() {
        return "This is a rest test interface: "+connectionTimeout+": "+testConfig.getStr();
    }
}
