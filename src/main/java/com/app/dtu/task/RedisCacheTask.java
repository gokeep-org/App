package com.app.dtu.task;

import com.app.dtu.bean.model.PreviousData;
import com.app.dtu.redis.RedisClient;
import com.app.dtu.repository.RedisCachePreviousDataReponisory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Configuration
public class RedisCacheTask {

    @Autowired
    RedisCachePreviousDataReponisory redisCachePreviousDataReponisory;

    RedisClient redisClient;
    /**
     * 缓存上一条数据到缓存
     */
//    @Scheduled(cron = DtuConfig.CACHE_PREVIOUS_DATA)
    public void cachePreviousData(){
        List<PreviousData> previousDataList = redisCachePreviousDataReponisory.findAll();
        if (CollectionUtils.isEmpty(previousDataList)) {
            return;
        }
        previousDataList.stream().filter(previousData -> {
//            List<String> data = redisClient.hmget(previousData.getDeviceId(), "warn", "id", "status");
            return true;
        }).forEach(previousData -> {
            redisClient.hmset(previousData.getDeviceId(), previousData.toMap());
        });
    }
}
