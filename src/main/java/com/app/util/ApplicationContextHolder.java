package com.app.util;

import com.app.dtu.NettyServer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        new NettyServerThread().start();
    }


    class NettyServerThread extends Thread{
        @Override
        public void run() {
            new NettyServer().start();
        }
    }

}
