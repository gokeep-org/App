package com.app.config;


import com.app.filter.RequestFilter;
import com.app.filter.ResponseFilter;
import com.app.library.handle.RequestExceptionHandler;
import com.app.library.provide.GsonMessageBodyHandler;
import org.glassfish.jersey.message.DeflateEncoder;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.EncodingFilter;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;


@Configuration
@ApplicationPath(CommonConfig.BASE_PATH)
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        setJerseyComponentsLocation();
        registerJerseyFilter();
		registerExceptionProcessProvode();
        registerJsonProvider();
        registerCompressionEncoder();
        registerSwagger();
    }

    private void setJerseyComponentsLocation() {
        packages(CommonConfig.SCAN_REST_PACKAGE);
    }

    private void registerJerseyFilter() {
        register(RequestFilter.class);
        register(ResponseFilter.class);
    }

    private void registerJsonProvider() {
        register(GsonMessageBodyHandler.class);
        property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, false);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
    }

    public void registerExceptionProcessProvode() {
        register(RequestExceptionHandler.class);
    }

    /**
     * 注册Response body压缩时用到的encoder
     */
    private void registerCompressionEncoder() {
        registerClasses(EncodingFilter.class, GZipEncoder.class, DeflateEncoder.class);
    }

    /**
     * 注册Swagger,用于生成api文档
     */
    private void registerSwagger() {

    }
}