//package com.app.dtu.redis;
//
//import java.util.HashMap;
//import java.util.Map;
//
///****************************************
// * Copyright (c) xuning.
// * 尊重版权，禁止抄袭!
// * 如有违反，必将追究其法律责任.
// * @Auther is xuning on 2017/5/10.
// ****************************************/
//public class Simple
//{
//    public static void main(String[] args) {
////        Jedis jedis = new Jedis("192.168.0.113");
////        String value = jedis.set("name", "宁宁");
////        String[] strs = {"111","222", "333"};
////        jedis.lpush("aaa", strs);
////        System.out.println(value);
////        System.out.println(jedis.get("name"));
////        System.out.println(jedis.lrange("aaa", 0, -1));
////        System.out.println(jedis.exists("aaa"));
////        String res= DefaultRedisClient.get().get("aaa");
//        RedisClient redisClient = new DefaultRedisClient();
////        String res = redisClient.set("name", "aaa");
//        Map<String, String> map = new HashMap<>();
//        map.put("111", "aaa333444");
//        map.put("22", "bbb");
////        redisClient.close();
////        JedisUtil.getInstance().closeJedis(redisClient);
////        System.out.println(res);
////        RedisClient redisClient = new DefaultRedisClient();
//        redisClient.hmset("name1", map);
////        System.out.println(redisClient.get("name"));
////        System.out.println(redisClient.lrange("aaa", 0 , -1));
//        System.out.println(redisClient.hmget("name1", "111"));
//
//    }
//}
