package com.app.dtu.service;

import com.app.dtu.service.impl.*;
import com.app.util.ApplicationContextHolder;

/**
 * 这里取到所有service处理类
 */
public class ServiceItem {
    // 设备监控管理业务处理
    // 基础设备业务数据
    public static DataService deviceService = ApplicationContextHolder.getContext().getBean(ServiceBeanNames.DEVICE_SERVICE, DeviceServiceImpl.class);
    public static DataService monitorManagerService = ApplicationContextHolder.getContext().getBean(ServiceBeanNames.MONITOR_MNAAGER_DEVICE_SERVICE, MonitorManagerDeviceServiceImpl.class);
    public static DataService combustibleGasMonitorService = ApplicationContextHolder.getContext().getBean(ServiceBeanNames.COMBUSTIBLE_GAS_SERVICE, CombustibleGasMonitorServiceImpl.class);
    public static DataService electricalFireMonitorService = ApplicationContextHolder.getContext().getBean(ServiceBeanNames.ELECTRICAL_FIRE_SERVICE, ElectricalFireMonitorImpl.class);
    public static DataService faultArcMonitorService = ApplicationContextHolder.getContext().getBean(ServiceBeanNames.FAULT_ARC_SERVICE_SERVICE, FaultArcMonitorServiceImpl.class);
    public static DataService fireControlPowerService = ApplicationContextHolder.getContext().getBean(ServiceBeanNames.FIRE_CONTROL_POWER_SERVICE, FireControlPowerMonitorServiceImpl.class);
    public static DataService hydraulicPressureService = ApplicationContextHolder.getContext().getBean(ServiceBeanNames.HYDRAULIC_PRESSURE_SERVICE, HydraulicPressureMonitorServiceImpl.class);
    public static DataService intelligentPowerService = ApplicationContextHolder.getContext().getBean(ServiceBeanNames.INTELLIGENT_POWER_SERVICE, IntelligentPowerMonitorServiceImpl.class);
    public static DataService preventFireDoorService = ApplicationContextHolder.getContext().getBean(ServiceBeanNames.PRVENT_FIRE_DOOR_SERVICE, PreventFireDoorMonitorServiceImpl.class);
    public static DataService screenMonitorService = ApplicationContextHolder.getContext().getBean(ServiceBeanNames.SCREEN_SERVICE, ScreenFeeMonitorServiceImpl.class);
    public static DataService somkeFeeService = ApplicationContextHolder.getContext().getBean(ServiceBeanNames.SMOKE_FEEL_SERVICE, SmokeFeeMonitorServiceImpl.class);
}
