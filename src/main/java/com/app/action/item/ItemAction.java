package com.app.action.item;


import com.app.action.BaseAction;
import com.app.domain.output.BaseOutput;
import com.app.service.BaseService;
import com.app.service.ServiceBeanNames;
import com.app.service.TestService;
import com.app.service.impl.TestServiceImpl;

public abstract class ItemAction<T extends BaseOutput> extends BaseAction<T> {
    public static TestService testService = BaseService.getService(ServiceBeanNames.TEST_SERVICE, TestServiceImpl.class);
}