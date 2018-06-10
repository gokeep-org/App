package com.app.redis;

import com.app.AppStart;
import com.app.dtu.redis.DefaultRedisClient;
import com.app.dtu.redis.RedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AppStart.class)
@ActiveProfiles(value = "produce")
public class RedisTest {

    RedisClient client = new DefaultRedisClient();

    @Autowired
    StringRedisTemplate  stringRedisTemplate;
    @Autowired
    RedisTemplate<String, Map<String, String>> redisTemplate;
    @Test
    public void redis(){
        client.set("name", "value");
        String name = client.get("name");
        System.out.println(name);
    }

    @Test
    public void testHmset(){
        Map<String, String> value = new HashMap<>();
        value.put("v1", "11111");
        value.put("v2", "33333");
        client.hmset("name", value);
        System.out.println(client.hmget("name1", "v2"));
        client.close();
    }

    @Test
    public void setStringRedisTemplate(){
        Set<Object> keys = stringRedisTemplate.opsForHash().keys("*");
        System.out.println();
    }
}
