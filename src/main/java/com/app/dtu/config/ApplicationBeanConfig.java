package com.app.dtu.config;

import cn.networklab.requests.Requests;
import cn.networklab.requests.core.RequestImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfig {
    private static final String REQUESTS_BEAN_NAME = "Request_beab_impl";

    @Bean(name = REQUESTS_BEAN_NAME)
    public Requests getRequests() {
        return new RequestImpl();
    }

//    @Bean
//    public RedisClient redisClient(){
//        return new DefaultRedisClient();
//    }
}
