package com.app.util;

import com.app.dtu.NettyServer;
import com.app.dtu.bean.model.InitDataTask;
import com.app.dtu.bean.model.ScheduleUpdateLocalCache;
import com.app.dtu.service.ServiceBeanNames;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

@Configuration
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }
    @Autowired
    ScheduleUpdateLocalCache scheduleUpdateLocalCache;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        new NettyServerThread().start();
        new InitDeviceSiIdWhenStartServer().start();
        ((InitDataTask)applicationContext.getBean(ServiceBeanNames.INIT_MODEL_TYPE_TABLE_DATA_SERVICE)).start();
    }

    /**
     * 因为spring对阻塞类框架不友好，这采用上下文获取的方式， 只能够起一个
     * 线程当容器加载完毕的时候。
     */
    class NettyServerThread extends Thread{
        @Override
        public void run() {
            new NettyServer().start();
        }
    }

    /**
     * 当项目启动的时候需要需要初始化数据到本地缓存
     */


    @Async
    class InitDeviceSiIdWhenStartServer extends Thread{
        @Override
        public void run() {
            scheduleUpdateLocalCache.updateDeviceModelCode();
        }
    }
}
