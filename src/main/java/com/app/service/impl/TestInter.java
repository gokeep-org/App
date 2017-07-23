package com.app.service.impl;

/****************************************
 * Copyright (c) xuning.
 * @Auther is xuning on 17-7-24
 ****************************************/
public interface TestInter {
    public default String getInfo(){
        return getCollectorUrl();
    }
    
    public String getCollectorUrl();
}
