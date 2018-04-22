package com.app;

import com.app.config.CommonConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableJpaRepositories
@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AppStart.class)
                .web(CommonConfig.ENABLE_WEB_SERVER)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
