package com.app.service.impl;

import com.app.service.BaseService;
import com.app.service.ServiceBeanNames;
import com.app.service.TestService;
import org.springframework.stereotype.Service;

/****************************************
 * Copyright (c) xuning.
 * @Auther is xuning on 17-7-2
 ****************************************/
@Service(ServiceBeanNames.TEST_SERVICE)
public class TestServiceImpl extends BaseService implements TestService {
    @Override
    public String start() {
        return "This is a rest test interface";
    }
}
