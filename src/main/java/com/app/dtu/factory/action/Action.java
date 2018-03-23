package com.app.dtu.factory.action;

import com.app.dtu.bean.DtuBean;

/**
 * 在当前的业务场景中，只是存在数据的添加操作，特殊情款下
 * 需要满足具备查询操作
 * @param <T>
 */
public interface Action <T extends DtuBean>{
    /**
     * 保存数据对象事件
     * @param object
     */
    public void save(T object);
}
