package com.app.dtu.service;

import com.app.dtu.service.impl.DeviceServiceImpl;
import com.app.dtu.service.impl.MonitorManagerDeviceServiceImpl;
import com.app.util.ApplicationContextHolder;


public class ServiceItem {
    // 设备监控管理业务处理
    public static DataService monitorManagerService = ApplicationContextHolder.getContext().getBean(ServiceBeanNames.MONITOR_MNAAGER_DEVICE_SERVICE, MonitorManagerDeviceServiceImpl.class);
    // 基础设备业务数据
    public static DataService deviceService = ApplicationContextHolder.getContext().getBean(ServiceBeanNames.DEVICE_SERVICE, DeviceServiceImpl.class);
}
