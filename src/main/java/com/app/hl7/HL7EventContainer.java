package com.app.hl7;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;

@Configuration
public class HL7EventContainer implements Hl7Log{
    private static BlockingQueue<String> iheMessageQueue;
    private static final Logger logger = LoggerFactory.getLogger(HL7EventContainer.class);

    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        HazelcastInstance hazelcastClient = Hazelcast.newHazelcastInstance(config);
        iheMessageQueue = hazelcastClient.getQueue("ihe-message-queue");
        return hazelcastClient;
    }

    public static BlockingQueue<String> build() {
        return iheMessageQueue;
    }


    public void put(String message) {
        try {
            iheMessageQueue.put(message);
        } catch (InterruptedException e) {
            return;
        }
    }

    public void offer(String message) {
        try {
            iheMessageQueue.offer(message);
        } catch (Throwable e) {
            return;
        }
    }


    public String take() {
        try {
            return iheMessageQueue.take();
        } catch (InterruptedException e) {
            return null;
        }
    }
}