package com.app.dtu.bean.model;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 本地缓存实现
 */
public final class LocalCache {
    private LocalCache(){}


    // 设备关系缓存，每隔1小时自动更新一次
    private static List<DeviceRelation> deviceRelationCache = new ArrayList<>();

    public static List<DeviceRelation> getDeviceRelationCache() {
        return deviceRelationCache;
    }


    /**
     * 设备关系缓存实现, 每想个一定的时间去更新这个缓存
     * @param updateResult
     */
    public static void updateDeviceRelation(List<DeviceRelation> updateResult){
        deviceRelationCache.clear();
        deviceRelationCache.addAll(updateResult);
    }

    /**
     * 根据设备的Id吗去获取设备的型号
     * @param deviceId
     * @return
     */
    public static String getDeviceModelCodeByDevideId(String deviceId){
        List<DeviceRelation> searchResults = deviceRelationCache.stream().filter(deviceRelation -> {
            if (deviceRelation.getDevice_id().equalsIgnoreCase(deviceId)){
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        return CollectionUtils.isEmpty(searchResults) ? null : searchResults.get(0).getDevice_model_code();
    }

    /**
     * 更新设备的型号去获取设备的本地枚举常量，但是不建议使用
     * @param deviceId
     * @return
     */
    public static DeviceTypeName getDeviceModelEnumByDeviceId(String deviceId){
        return DeviceTypeName.getDeviceTypeInfoByModelCode(getDeviceModelCodeByDevideId(deviceId));
    }
}
