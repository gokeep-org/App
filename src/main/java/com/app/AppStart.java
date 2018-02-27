package com.app;

import cn.networklab.requests.Requests;
import cn.networklab.requests.core.RequestImpl;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AppStart.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Bean
    public Requests requests(){
        return new RequestImpl();
    }
}
