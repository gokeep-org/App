package com.app;

import com.app.config.CommonConfig;
import com.app.dtu.NettyServer;
import org.springframework.beans.BeansException;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AppStart.class)
                .web(CommonConfig.ENABLE_WEB_SERVER)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
