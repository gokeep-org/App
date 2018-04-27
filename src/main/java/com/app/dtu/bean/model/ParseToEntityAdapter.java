package com.app.dtu.bean.model;

import com.app.dtu.bean.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * 用于支持设备数据解析完成之后解析给对象
 * 所有的实体类对象均实现这个接口
 */
public interface ParseToEntityAdapter<T extends DeviceDataDeal> {
    public Logger logger = LoggerFactory.getLogger(ParseToEntityAdapter.class);
    /**
     * 默认的解析实现，如果某一个实体有特殊的需求，那么重写execute方法即可
     *
     */
    // 获取实体对象
    T buildDevice();

    // 获取实体的消息对象
    Message buildMessage();

    T generateEntity(Message message);

    /**
     * 这里只需要关系和分离出来报警和故障两种情况
     */
    void parseDeviceStatus();

    // 校验消息
    default boolean checkMessage(Message message){
        return Objects.isNull(message);
    }

    // 校验设备
    default boolean checkDevice(){
        return Objects.isNull(buildDevice());
    }

    // 获取实体类
    default T getStorageEntity(){
        if (checkMessage(buildMessage()) || checkDevice()){
            logger.info("Receiver message is null");
            return null;
        }
        T entity =  generateEntity(buildMessage());
        parseDeviceStatus();
        logger.info("Parse to entity is {}", Objects.isNull(entity) ? null : entity.toString());
        return entity;
    }
}
