package com.app.dtu.config;

import com.app.dtu.bean.model.DeviceTypeName;

import java.util.ArrayList;
import java.util.List;

public class DtuConfig {

    // 是否启动socket监听服务
    public static final boolean IS_ENABLE_SOCKET_SERVER = false;
    // 以具体的服务器为准
    public static final String DEFAULT_HOST = "localhost";
    // Socket 服务的段端口
    public static final int SOCKET_SERVER_PORT = 34885;
    // Socket 连接最大缓存数
    public static final int SOCKET_SERVER_SO_BACKLOG = 65535;
    // 是否开启长连接
    public static final boolean ENABLE_KEEP_ALIVE_CONNECTION = false;
    // 是否开启队列模式，这里使用的RabbitMq, 无论启动会创建连接，用于动态修改模式
    public static final boolean ENABLE_MQ_MODE = true;
    // 数据消息上传的消息队列名称
    public static final String MQ_NAME = "DTU_MESSAGE";


    // 数据库表名前缀
    public static final String DTU_TABLE_PRIFIX = "dtu_";
    // 是否需要服务启动检查并初始化设备类型数据
    public static final boolean IS_CHECK_INIT_DEVICE_MODEL_DATA = true;
    // 使用本地开发模式，会优先使用本地初始化型号型号缓存，不支持代传模式
    public static final boolean LOCAL_DEV_ONALY_FORWARD = false;
    // 启动debug模式，只会一次初始化任务
    public static final boolean ENABLE_BUBUG_MODE = false;


    // 离线数据更新任务cron
    public static final boolean IS_ENABLE_SCHEDULE_TASK = false;
    // 本地缓存定时任务
    public static final String LOCAL_CACHE_CRON = "0 0 0/1 * * ?";
    // 离线数据更新任务cron
    public static final String LOCAL_OFF_LINE_UPDATE_CRON = "0 0 0/36 * * ?";
    // 定时任务执行缓存数据到数据库, 每1小时执行一次
    public static final String CACHE_PREVIOUS_DATA = "0 0 /1 * * ?";
    // 0 0 0/1 * * ? 1小时更新1次
    public static final int LOCAL_OFF_LINE_UPDATE_BEFORE_TIME_SCAN = 1000 * 60 * 15;
    // PID时间更新间隔，确保特殊情况下是哪一次发送的数据
    public static final long PID_UPDATE_TIME = 1000 * 60;
    // Dtu-Config的更新设备列表
    public static final List<DeviceTypeName> updateDeviceTypes = new ArrayList<>();
    public static final long CACHE_EXPRIE_TIME_FOR_DAY = 365 * 10;

    public static final String REDIS_URL = "114.116.23.218";
    // FMS系统路径
    public static final String FMS_SYS_API_PATH = "http://114.116.23.218:9999/FMS/V1-API";
    // FMS报警接口路径
    public static final String FMS_SYS_WARN_NOTICE_PATH = FMS_SYS_API_PATH + "/notice/warn?id=%s&dt=%s";

    // 定时任务数据库更新配置信息, 以下配置是为了方便快速开发调用sql的util，目前不再使用，仅做保留
    public static final String cacheHost = "118.89.240.60";
    public static final String cachePort = "3306";
    public static final String cacheDbName = "fms";
    public static final String cacheUsername = "root";
    public static final String cachePassword = "qwe@w1#gg$v_";

    static {
        updateDeviceTypes.add(DeviceTypeName.INTELLIGENT_POWER_MONITOR_0201);
    }
}
