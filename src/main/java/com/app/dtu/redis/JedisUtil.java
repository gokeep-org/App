package com.app.dtu.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Redis连接池工具类
 */
public class JedisUtil {
    private static final Logger log = LoggerFactory.getLogger(JedisUtil.class);

    private JedisUtil() {
    }

    private static Map<String, JedisPool> maps = new HashMap<String, JedisPool>();


    /**
     * 获取连接池.
     * @return JedisPool
     */
    private static JedisPool getPool(String ip, int port) {
        String key = ip + ":" + port;
        JedisPool pool = null;
        if (!maps.containsKey(key)) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(RedisConfig.getMaxTotal());
            config.setMaxIdle(RedisConfig.getMaxidle());
            config.setMaxWaitMillis(RedisConfig.getMaxWaitMillis());
            config.setTestOnBorrow(RedisConfig.getTestOnBorrow());
            config.setTestOnReturn(RedisConfig.getTestOnReturn());
            try {
                pool = new JedisPool(config, ip, port, RedisConfig.getTimeout());
                maps.put(key, pool);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            pool = maps.get(key);
        }
        return pool;
    }

    private static class RedisUtilHolder {
        private static JedisUtil instance = new JedisUtil();
    }

    /**
     * 获取当前实例
     * @return
     */
    public static JedisUtil getInstance() {
        return RedisUtilHolder.instance;
    }

    /**
     * 获取redis
     * @return
     */
    public Jedis getJedis() {
        Jedis jedis = null;
        int count = 0;
        do {
            try {
                jedis = getPool(RedisConfig.getRedisHost(), RedisConfig.getRedisPort()).getResource();
                log.info("get redis master is successful");
            } catch (Exception e) {
                log.error("get redis master failed!", e);
                closeJedis(jedis);
            }
            count++;
        } while (jedis == null && count < RedisConfig.getRetryNum());
        return jedis;
    }

    /**
     * 获取Redis实例.
     * @return Redis工具类实例
     */
    public Jedis getJedis(String ip, int port) {
        Jedis jedis = null;
        int count = 0;
        do {
            try {
                jedis = getPool(ip, port).getResource();
                log.info("get redis master is successful");
            } catch (Exception e) {
                log.error("get redis master is failed!", e);
                closeJedis(jedis);
            }
            count++;
        } while (jedis == null && count < RedisConfig.getRetryNum());
        return jedis;
    }

    /**
     * 关闭redis线程池
     * @param jedis
     */
    public void closeJedis(Jedis jedis) {
        if (null != jedis) {
            if (!getPool(RedisConfig.getRedisHost(), RedisConfig.getRedisPort()).isClosed()) {
                getPool(RedisConfig.getRedisHost(), RedisConfig.getRedisPort()).close();
            }
        }
    }

    /**
     * 关闭Redis线程池
     * @param jedis
     * @param ip
     * @param port
     */
    public void closeJedis(Jedis jedis, String ip, int port) {
        if (null != jedis) {
            if (!getPool(ip, port).isClosed()) {
                getPool(ip, port).close();
            }
        }
    }
}