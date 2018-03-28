package com.app.dtu.bean;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO: 前期时间紧，先以硬编码方式实现
 * 后期需要优化，即一次缓存读取到数据，然后以定时任务的方式刷新缓存
 */
public enum DeviceTypeName {
    /**
     * 设备型号列表
     */
    // 消防管理设备
    MONITOR_MANAGER_0001("00", "监控管理设备", "0001", "电气火灾监控设备壁挂式", "DH-A-XT/BG"),
    MONITOR_MANAGER_0002("00", "监控管理设备", "0002", "电气火灾监控设备立柜式", "DH-A-XT/LG"),
    MONITOR_MANAGER_0003("00", "监控管理设备", "0003", "消防设备电源状态监控器壁挂式", "DH-A-GM/BG"),
    MONITOR_MANAGER_0004("00", "监控管理设备", "0004", "消防设备电源状态监控器立柜式", "DH-A-GM/LG"),
    MONITOR_MANAGER_0005("00", "监控管理设备", "0005", "防火门监控器壁挂式", "DH-A-FM/BG"),
    MONITOR_MANAGER_0006("00", "监控管理设备", "0006", "防火门监控器立柜式", "DH-A-FM/LG"),
    MONITOR_MANAGER_0007("00", "监控管理设备", "0007", "智能电力监控管理机", "DH-A-DL"),

    // 电气火灾监控设备

    ELECTRICAL_FIRE_MONITOR_0101("01", "电气火灾监控", "0101", "组合式电气火灾监控探测器", "JTL-DQ"),
    ELECTRICAL_FIRE_MONITOR_0102("02", "电气火灾监控", "0102", "剩余电流式电气火灾监控探测器", "DH-A-FT/N"),
    ELECTRICAL_FIRE_MONITOR_0103("01", "电气火灾监控", "0103", "测温式电气火灾监控探测器", "DH-A-FT/P"),
    ELECTRICAL_FIRE_MONITOR_0104("01", "电气火灾监控", "0104", "剩余电流式电气火灾监控探测器", "DH-A-FT/N8"),
    ELECTRICAL_FIRE_MONITOR_0105("01", "电气火灾监控", "0105", "剩余电流式电气火灾监控探测器", "DH-A-FD/N"),
    ELECTRICAL_FIRE_MONITOR_0106("01", "电气火灾监控", "0106", "测温式电气火灾监控探测器", "DH-A-FD/P"),
    ELECTRICAL_FIRE_MONITOR_0107("01", "电气火灾监控", "0107", "剩余电流式电气火灾监控探测器", "DH-A-FD/N8"),
    ELECTRICAL_FIRE_MONITOR_0108("01", "电气火灾监控", "0108", "剩余电流式电气火灾监控探测器", "DH-A-MT"),

    // 智能电力监控
    INTELLIGENT_POWER_MONITOR_0201("02", "智能电力监控", "0201",  "多功能电力仪", "JTL-M/C100"),

    // 故障电弧监控
    FAULT_ARC_MONITOR_0301("03", "故障电弧监控", "0301", "故障电弧探测器", "DH-A-AF"),

    // 消防设备电源监控
    FIRE_CONTROL_POWER_MONITOR_0401("04", "消防设备电源监控", "0401", "电压电流信号传感器", "DH-A-53M/S"),
    FIRE_CONTROL_POWER_MONITOR_0402("04", "消防设备电源监控", "0402", "电压电流信号传感器", "DH-A-53M/SA"),
    FIRE_CONTROL_POWER_MONITOR_0403("04", "消防设备电源监控", "0403", "电压电流信号传感器", "DH-A-53M/A6"),
    FIRE_CONTROL_POWER_MONITOR_0404("04", "消防设备电源监控", "0404", "电压电流信号传感器", "DH-A-63M"),
    FIRE_CONTROL_POWER_MONITOR_0405("04", "消防设备电源监控", "0405", "电压电流信号传感器", "DH-A-93M"),

    // 烟感监控
    SMOKE_FEE_MONITOR_0501("05", "烟感监控", "0501", "烟感监控探测", "JTY-GD-JTL501"),

    // 可燃气体监控

    COMBUSTIBLE_GAS_MONITOR_0601("06", "可燃气体监控", "0601", "可燃气体监控探测器（天然气）", "JTL-KR/601"),
    COMBUSTIBLE_GAS_MONITOR_0602("06", "可燃气体监控", "0602", "可燃气体监控探测器（液化气）", "JTL-KR/602"),
    COMBUSTIBLE_GAS_MONITOR_0603("06", "可燃气体监控", "0603", "可燃气体监控探测器（煤制气）", "JTL-KR/603"),


    // 防火门监控

    PREVENT_FIRE_DOOR_MONITOR_0701("07", "防火门监控", "0701", "防火门一体式电动闭门器", "DH-A-FM/B"),
    PREVENT_FIRE_DOOR_MONITOR_0702("07", "防火门监控", "0702", "防火门一体式门磁开关", "DH-A-FM/C2"),
    PREVENT_FIRE_DOOR_MONITOR_0703("07", "防火门监控", "0703", "防火门一体式门磁开关", "DH-A-FM/C1"),

    // 视频监控
    SCREEN_MONITOR_0801("08", "视频监控", "0801", "720P监控摄像头", "JTL-SP/720P"),
    SCREEN_MONITOR_0802("08", "视频监控", "0802", "1080P监控摄像头", "JTL-SP/1080P"),

    // 水压监控
    HYDRAULIC_PRESSURE_MONITOR_1201("12", "水压监控", "1201", "水压监控器", "JTL-SY/1201");


    String typeCode;
    String typeName;
    String modelCode;
    String modelName;
    String deviceModelCode;

    DeviceTypeName(String typeCode, String typeName, String modelCode, String modelName, String deviceModelCode) {
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.modelCode = modelCode;
        this.modelName = modelName;
        this.deviceModelCode = deviceModelCode;
    }


    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getDeviceModelCode() {
        return deviceModelCode;
    }

    public void setDeviceModelCode(String deviceModelCode) {
        this.deviceModelCode = deviceModelCode;
    }

    /**
     * 获取所有枚举值
     * @return
     */
    public static List<DeviceTypeName> findAll(){
        return Arrays.asList(DeviceTypeName.values());
    }

    /**
     * 根据型号编码获取设备的信息
     * @param deviceModelCode
     * @return
     */
    public static DeviceTypeName getDeviceTypeInfoByModelCode(String deviceModelCode){
       List<DeviceTypeName> deviceTypeNames = findAll().stream().filter(deviceTypeName -> {
            if (deviceTypeName.modelCode.equalsIgnoreCase(deviceModelCode)){
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        return CollectionUtils.isEmpty(deviceTypeNames) && deviceTypeNames.size() > 0 ? null : deviceTypeNames.get(0);
    }
}
