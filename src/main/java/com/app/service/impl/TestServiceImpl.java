package com.app.service.impl;

import com.app.config.Constat;
import com.app.lock.LockUtil;
import com.app.service.BaseService;
import com.app.service.ServiceBeanNames;
import com.app.service.TestService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Service(ServiceBeanNames.TEST_SERVICE)
public class TestServiceImpl extends BaseService implements TestService {
    @Resource
    RedissonClient redissonClient;
    @Override
    public String start() throws InterruptedException {
        RLock lock = redissonClient.getLock("aaa");
        try {
//            lock.lock(1, TimeUnit.SECONDS);
            lock.lock();
            System.out.println("lock ");
            Constat.var ++;
            Thread.sleep(10000);
            System.out.println("var is: " + Constat.var);
        }finally {
            if (lock != null && lock.isLocked()) {
                lock.unlock();
            }
        }
        return "Ok";
    }
}
