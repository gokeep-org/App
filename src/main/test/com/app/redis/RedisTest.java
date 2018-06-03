package com.app.redis;

import com.app.dtu.redis.DefaultRedisClient;
import com.app.dtu.redis.RedisClient;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(AppStart.class)
//@ActiveProfiles(value = "produce")
public class RedisTest {

    RedisClient client = new DefaultRedisClient();

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
}
