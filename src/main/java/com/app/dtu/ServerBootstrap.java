package com.app.dtu;

/**
 * 抽象服务启动关闭接口
 * 用于抽象socket各种服务的开启和关闭
 */
public interface ServerBootstrap {
    public void start();

    public void stop();
}
