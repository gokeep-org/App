package com.app.redis;

import com.app.AppStart;
import com.app.dtu.redis.DefaultRedisClient;
import com.app.dtu.redis.RedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AppStart.class)
@ActiveProfiles(value = "produce")
public class RedisTest {

    @Autowired
    RedisClient client;

    @Test
    public void redis(){
        client.set("name", "value");
        String name = client.get("name");
        System.out.println(name);




    }
}
