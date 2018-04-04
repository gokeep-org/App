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

    // 定时任务数据库更新配置信息
    public static final String cacheHost = "118.89.240.60";
    public static final String cachePort = "3306";
    public static final String cacheDbName = "fms";
    public static final String cacheUsername = "root";
    public static final String cachePassword = "qwe@w1#gg$v_";


}
