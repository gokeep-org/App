package com.app.dtu.bean;

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

    String typeNo;
    String typeName;
    String modelNo;
    String modelName;
    String deviceModelCode;

    DeviceTypeName(String typeNo, String typeName, String modelNo, String modelName, String deviceModelCode) {
        this.typeNo = typeNo;
        this.typeName = typeName;
        this.modelNo = modelNo;
        this.modelName = modelName;
        this.deviceModelCode = deviceModelCode;
    }
}
