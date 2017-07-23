package com.app.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/****************************************
 * Copyright (c) xuning.
 * @Auther is xuning on 17-7-24
 ****************************************/
@Service
public class TestInterImpl implements TestInter{
    @Value("${info.str}")
    private String str;
    @Override
    public String getCollectorUrl() {
        return str;
    }
}
