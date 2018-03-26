package com.app.dtu.service;

import com.app.dtu.bean.model.DeviceDataDeal;

public interface DataService <T extends DeviceDataDeal>{
    public boolean save(T deviceData);
}
