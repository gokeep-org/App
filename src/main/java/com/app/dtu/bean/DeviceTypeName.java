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
    // 消防管理设备
    MONITOR_MANAGER_0001("00", "监控管理设备", "0001", "电气火灾监控设备壁挂式", "DH-A-XT/BG"),
    MONITOR_MANAGER_0002("00", "监控管理设备", "0002", "电气火灾监控设备立柜式", "DH-A-XT/LG"),
    MONITOR_MANAGER_0003("00", "监控管理设备", "0003", "消防设备电源状态监控器壁挂式", "DH-A-GM/BG"),
    MONITOR_MANAGER_0004("00", "监控管理设备", "0004", "消防设备电源状态监控器立柜式", "DH-A-GM/LG"),
    MONITOR_MANAGER_0005("00", "监控管理设备", "0005", "防火门监控器壁挂式", "DH-A-FM/BG"),
    MONITOR_MANAGER_0006("00", "监控管理设备", "0006", "防火门监控器立柜式", "DH-A-FM/LG"),
    MONITOR_MANAGER_0007("00", "监控管理设备", "0007", "智能电力监控管理机", "DH-A-DL"),

    // TODO ： 暂时先写这么多后期需要添加，共计是10个大类型
    // 电气火灾监控设备
    MONITOR_MANAGER_0008("01", "电气火灾监控设备", "0101", "电气火灾监控设备壁挂式", "DH-A-XT/BG");

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
    static List<DeviceTypeName> findAll(){
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
