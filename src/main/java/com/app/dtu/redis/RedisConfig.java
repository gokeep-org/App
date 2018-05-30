package com.app.dtu.redis;

import com.app.dtu.config.DtuConfig;

import java.util.Objects;

/****************************************
 * Copyright (c) xuning.
 * 尊重版权，禁止抄袭!
 * 如有违反，必将追究其法律责任.
 * @Auther is xuning on 2017/5/10.
 ****************************************/
public class RedisConfig {
    private static final String redisHost = DtuConfig.REDIS_URL;
    private static final int redisPort = 6379;

    private static final int timeout = 60000;
    private static final int retryNum = 2;
    private static final int maxTotal = 20;
    private static final int maxidle = 15;
    private static final int maxWaitMillis = 60000;
    private static final Boolean testOnBorrow = true;
    private static final Boolean testOnReturn = true;
    private static RedisConfig jedisConfig  = null;

    private RedisConfig() {
        if (Objects.equals(null, jedisConfig)) {
            jedisConfig = new RedisConfig();
        }
    }

    public static int getTimeout() {
        return timeout;
    }

    public static int getRetryNum() {
        return retryNum;
    }

    public static int getMaxTotal() {
        return maxTotal;
    }

    public static int getMaxidle() {
        return maxidle;
    }

    public static int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public static Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public static Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public static String getRedisHost() {
        return redisHost;
    }

    public static int getRedisPort() {
        return redisPort;
    }
}
