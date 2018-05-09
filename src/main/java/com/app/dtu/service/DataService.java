package com.app.dtu.service;

import com.app.dtu.bean.model.DeviceDataDeal;

/**
 * 抽象化数据插入操作
 * @param <T>
 */
public interface DataService <T extends DeviceDataDeal>{
    public boolean save(T deviceData);
    public boolean updateOldDataStatus(String messageId);
    public boolean updateOffLineData(String messageId);
}