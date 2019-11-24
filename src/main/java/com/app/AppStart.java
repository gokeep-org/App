package com.app;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AppStart.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
