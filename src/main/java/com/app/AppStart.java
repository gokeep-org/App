package com.app;

import com.app.config.CommonConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AppStart {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AppStart.class)
                .web(CommonConfig.ENABLE_WEB_SERVER)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
