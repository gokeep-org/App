package com.app.action.factory;

import com.app.action.test.TestInfoAction;

/****************************************
 * Copyright (c) xuning.
 * @Auther is xuning on 17-7-2
 ****************************************/
public class TestActionFactory extends BaseActionFactory {
    public static TestInfoAction getTestInfoAction(boolean flag){
        return new TestInfoAction(flag);
    }
}
