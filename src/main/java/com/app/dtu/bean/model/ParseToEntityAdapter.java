package com.app.dtu.bean.model;

import cn.networklab.requests.Requests;
import cn.networklab.requests.core.RequestImpl;
import com.app.dtu.bean.DataMsg;
import com.app.dtu.bean.Message;
import com.app.dtu.config.DtuConfig;
import com.app.dtu.redis.RedisClient;
import com.app.dtu.service.DataService;
import com.app.dtu.util.DtuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 用于支持设备数据解析完成之后解析给对象
 * 所有的实体类对象均实现这个接口
 */
public interface ParseToEntityAdapter<T extends DeviceDataDeal> {

    static Requests httpClient = new RequestImpl();

    public Logger logger = LoggerFactory.getLogger(ParseToEntityAdapter.class);

    // 获取实体对象
    T buildDevice();

    // 获取实体的消息对象
    Message buildMessage();

    T generateEntity(Message message);

    /**
     * 这里只需要关系和分离出来报警和故障两种情况
     */

    public Message getMessage();

    // 校验消息
    default boolean checkMessage(Message message) {
        return Objects.isNull(message);
    }


    default boolean isChange() {
        boolean isChange = false;
        List<Object> values = getRedisClient().opsForHash().multiGet(getMessage().getId(), Arrays.asList(new String[]{"warn", "id", "warn_time"}));
        Long warnTime = -1L;
        try {
            warnTime = processWarnTime(values);
        }catch (Throwable e) {
            logger.error("Prpcess warn start time from redis cache is fail, cause is {}", e.getMessage());
        }
        if (CollectionUtils.isEmpty(values) || values.size() < 2) {
            isChange = true;
        } else {
            if (values.get(0) == null || values.get(1) == null || !String.valueOf(values.get(0)).equalsIgnoreCase(String.valueOf(getMessage().getWarnList()))) {
                isChange = true;
            } else {
                isChange = false;
            }
        }
        if (isChange) {
            Map<String, String> hashValue = new HashMap<>();
            hashValue.put("warn", String.valueOf(getMessage().getWarnList()));
            hashValue.put("id", String.valueOf(getId()));
            hashValue.put("warn_time", String.valueOf(warnTime));
            getRedisClient().expire(getMessage().getId(), DtuConfig.CACHE_EXPRIE_TIME_FOR_DAY, TimeUnit.DAYS);
            getRedisClient().opsForHash().putAll(getMessage().getId(), hashValue);
            logger.info("Redis set cache is [device_id: {}], [value: {}]", hashValue.toString());
        } else {
            getService().updatePreviousDataStatus(String.valueOf(values.get(1)), 2);
        }
        return isChange;
    }


    default Long processWarnTime(List<Object> values) {
        // 数据为空，从来没有接收 ， 正常状态
        if (CollectionUtils.isEmpty(values) || values.size() < 3 || values.get(2) == null) {
            PreviousData previousData = new PreviousData();
            // 正常
            if (getMessage().getWarnList() == 0 && getMessage().getWarnList() != 28) {
                previousData.buildData(getMessage().getId(), getId(), String.valueOf(getMessage().getWarnList()), -1L);
            }
            // 不正常
            else {
                previousData = PreviousData.build().buildData(getMessage().getId(), getId(), String.valueOf(getMessage().getWarnList()), System.currentTimeMillis());
            }
            getRedisClient().expire(getMessage().getId(), DtuConfig.CACHE_EXPRIE_TIME_FOR_DAY, TimeUnit.DAYS);
            getRedisClient().opsForHash().putAll(previousData.getDeviceId(), previousData.toMap());
            return previousData.getWarnTime();
        }
        // 接收过数据
        if (!CollectionUtils.isEmpty(values) || values.size() == 3) {
            PreviousData previousData = new PreviousData();
            if (getMessage().getWarnList() == 0 && getMessage().getWarnList() != 28 && !String.valueOf(values.get(0)).equals("0")) {
                // 这次正常，历史不正常
                previousData.buildData(getMessage().getId(), getId(), String.valueOf(getMessage().getWarnList()), -1L);
            } else if (getMessage().getWarnList() == 0 && getMessage().getWarnList() != 28 && String.valueOf(values.get(0)).equals(0)) {
                // 这次正常，上一条也是正常
                previousData = PreviousData.build().buildData(getMessage().getId(), getId(), String.valueOf(getMessage().getWarnList()), -1L);
            } else if ((getMessage().getWarnList() != 0 || getMessage().getWarnList() == 28) && !String.valueOf(values.get(0)).equals(0)) {
                // 这次不正常， 历史也不正常
                previousData = PreviousData.build().buildData(getMessage().getId(), getId(), String.valueOf(getMessage().getWarnList()), values.get(2) == null ?  -2L: Long.parseLong(String.valueOf(values.get(2) == null ? "-2" : values.get(2))));
            } else {
                // 这次不正常，历史是正常
                previousData = PreviousData.build().buildData(getMessage().getId(), getId(), String.valueOf(getMessage().getWarnList()), System.currentTimeMillis());
            }
            getRedisClient().expire(getMessage().getId(), DtuConfig.CACHE_EXPRIE_TIME_FOR_DAY, TimeUnit.DAYS);
            getRedisClient().opsForHash().putAll(previousData.getDeviceId(), previousData.toMap());
            return previousData.getWarnTime();
        }
        return -1L;
    }

//    /**
//     * 同步更新redis缓存
//     */
//    default void updateCache() {
//        String deviceId = getMessage().getId();
//        PreviousData previousData = PreviousData.build().buildData(deviceId, getId(), String.valueOf(getMessage().getWarnList()), getMessage().getStatus());
//        redisClient().hmset(previousData.getDeviceId(), previousData.getData().toMap());
//    }

    // 校验设备
    default boolean checkDevice() {
        return Objects.isNull(buildDevice());
    }

    public StringRedisTemplate getRedisClient();

    // 发送报警信息到fms系统
    default void sendWarnInfoToFmsSystem(T entity) {
        String request = "";
        if (Objects.isNull(getMessage()) || getMessage().getId() != null) {
            request = String.format(DtuConfig.FMS_SYS_WARN_NOTICE_PATH, getMessage().getId(), getMessage().parseTypeCode());
            if (!StringUtils.isEmpty(request)) {
                try {
                    Message message = getMessage();
                    if (isChange()) {
                        logger.info("Send warn notice url [{}] is successful", request);
                        httpClient.post(request);
                    }
                } catch (Throwable e) {
                    logger.error("Send warn notice error, cause {}", e.getMessage());
                }
            }
        }
    }

    public DataService getService();

    // 获取实体类
    default T getStorageEntity() {
        if (checkMessage(buildMessage()) || checkDevice()) {
            logger.info("Receiver message is null");
            return null;
        }
        T entity = generateEntity(buildMessage());
        // 这里先发送报警信息
        sendWarnInfoToFmsSystem(entity);
        buildWarnTime();
        logger.info("Parse to entity is {}", Objects.isNull(entity) ? null : entity.toString());
        return entity;
    }

    void buildWarnTime();

    default Message getOfflineMessage(String messageId) {
        Message message = new Message();
        message.setId(messageId);
        message.setStatus(4);
        message.setControCmd(28);
        message.setDataLen(0);
        message.setWarnList(0);
        message.setCreateTime(DtuUtil.getCurrentTimestrap());
        message.addDataMsgs(new DataMsg());
        return message;
    }

    /**
     * 获取到每一条存储数据的uuid
     *
     * @return
     */
    public String getId();


    public RedisClient redisClient();

}
