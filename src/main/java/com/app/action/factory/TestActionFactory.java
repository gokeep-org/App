package com.app.action.factory;

import com.app.action.test.TestInfoAction;

public class TestActionFactory extends BaseActionFactory {
    public static TestInfoAction getTestInfoAction(boolean flag){
        return new TestInfoAction(flag);
    }
}
