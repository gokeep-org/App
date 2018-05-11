package com.app.dtu.config;

public class DtuConfig {

    // 是否启动socket监听服务
    public static final boolean IS_ENABLE_SOCKET_SERVER = true;
    // 以具体的服务器为准
    public static final String DEFAULT_HOST = "localhost";
    // Socket 服务的段端口
    public static final int SOCKET_SERVER_PORT = 34885;
    // Socket 连接最大缓存数
    public static final int SOCKET_SERVER_SO_BACKLOG = 1024;
    // 是否开启长连接
    public static final boolean ENABLE_KEEP_ALIVE_CONNECTION = false;

    // 数据库表名前缀
    public static final String DTU_TABLE_PRIFIX = "dtu_";
    // 是否需要服务启动检查并初始化设备类型数据
    public static final boolean IS_CHECK_INIT_DEVICE_MODEL_DATA = true;
    // 使用本地开发模式，会优先使用本地初始化型号型号缓存，不支持代传模式
    public static final boolean LOCAL_DEV_ONALY_FORWARD = false;
    // 启动debug模式，只会一次初始化任务
    public static final boolean ENABLE_BUBUG_MODE = false;

    // 本地缓存定时任务
    public static final String LOCAL_CACHE_CRON = "0 0 0/12 * * ?";
    // 离线数据更新任务cron
    public static final String LOCAL_OFF_LINE_UPDATE_CRON = "0 0/14 * * * ?";
    // 默认是0.15小时更新一次
    public static final int LOCAL_OFF_LINE_UPDATE_BEFORE_TIME_SCAN = 250 * 3600;


    // FMS系统路径
    public static final String FMS_SYS_API_PATH = "http://118.89.240.60:9999/FMS/V1-API";
    public static final String FMS_SYS_WARN_NOTICE_PATH = FMS_SYS_API_PATH + "/notice/warn?id=%s&dt=%s";

    // 定时任务数据库更新配置信息, 以下配置是为了方便快速开发调用sql的util，目前不再使用，仅做保留
    public static final String cacheHost = "118.89.240.60";
    public static final String cachePort = "3306";
    public static final String cacheDbName = "fms";
    public static final String cacheUsername = "root";
    public static final String cachePassword = "qwe@w1#gg$v_";


}
