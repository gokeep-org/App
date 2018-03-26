package com.app.dtu.bean.model;

import com.app.dtu.bean.Message;

import java.util.Objects;

/**
 * 用于支持设备数据解析完成之后解析给对象
 * 所有的实体类对象均实现这个接口
 */
public interface ParseToEntityAdapter<T extends DeviceDataDeal> {
    /**
     * 默认的解析实现，如果某一个实体有特殊的需求，那么重写execute方法即可
     *
     */
    // 获取实体对象
    T buildDevice();

    // 获取实体的消息对象
    Message buildMessage();

    T generateEntity(Message message);


    // 校验消息
    default boolean checkMessage(Message message){
        return Objects.isNull(message);
    }

    // 校验设备
    default boolean checkDevice(){
        return Objects.isNull(buildDevice());
    }

    // 获取实体类
    default T parseEntity(){
        if (checkMessage(buildMessage()) || checkDevice()){
            return null;
        }
        return generateEntity(buildMessage());
    }


}
