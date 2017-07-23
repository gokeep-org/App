package com.app.service.impl;

/****************************************
 * Copyright (c) xuning.
 * @Auther is xuning on 17-7-24
 ****************************************/
public class TestConfigImpl implements TestConfig {
    public TestConfigImpl(String str){
        this.str = str;
    }
    public String str;
    @Override
    public String getStr() {
        return str;
    }
}
