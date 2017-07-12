package com.app.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/****************************************
 * Copyright (c) xuning.
 * @Auther is xuning on 17-7-8
 ****************************************/
public class MyElasticJob implements SimpleJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyElasticJob.class);
    @Override
    public void execute(ShardingContext shardingContext) {
        LOGGER.info(String.format("Item: %s | Time: %s | Thread: %s | %s",
                shardingContext.getShardingItem(),
                new SimpleDateFormat("HH:mm:ss").format(new Date()),
                Thread.currentThread().getId(), "SIMPLE"));
        
        switch (shardingContext.getShardingItem()) {
            case 0:
                LOGGER.info("被分片0处理");
//                throw new RuntimeException("异常测试");
                break;
            case 1:
                // do something by sharding item 1
                LOGGER.info("被分片1处理");
                break;
            case 2:
                // do something by sharding item 2
                LOGGER.info("被分片2处理");
                break;
            default:
                LOGGER.info("被未知分片处理");
        }
    }
}
