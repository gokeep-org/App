package com.app.dtu.config;

public class DtuConfig {

    public static final boolean IS_ENABLE_SOCKET_SERVER = true;

    public static final String DEFAULT_HOST = "localhost";
    // Socket 服务的段端口
    public static final int SOCKET_SERVER_PORT = 34885;
    // Socket 连接最大缓存数
    public static final int SOCKET_SERVER_SO_BACKLOG = 1024;
    // 是否开启长连接
    public static final boolean ENABLE_KEEP_ALIVE_CONNECTION = false;

}
