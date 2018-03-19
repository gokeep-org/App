package com.app.dtu;

/**
 * 抽象服务启动关闭接口
 * 用于抽象socket各种服务的开启和关闭
 */
public interface ServerBootstrap {
    /**
     * Socket netty服务启动
     */
    public void start();

    /**
     * Socket netty服务关闭
     */
    public void stop();
}
