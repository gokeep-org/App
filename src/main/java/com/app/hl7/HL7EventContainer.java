package com.app.hl7;

import ca.uhn.hl7v2.model.Message;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class HL7EventContainer implements Hl7Log{

    private HL7EventContainer(){}
    private static BlockingQueue<Message> iheMessageQueue;
    private static final Logger logger = LoggerFactory.getLogger(HL7EventContainer.class);
    private static HL7EventContainer instance;
    private static HazelcastInstance hazelcastClient;
    static {
        Config config = new Config();
        if (Objects.isNull(hazelcastClient)){
            hazelcastClient = Hazelcast.newHazelcastInstance(config);
        }
    }

    public static HL7EventContainer build() {
        iheMessageQueue = hazelcastClient.getQueue("ihe-message-queue");
        if (Objects.isNull(instance)){
            instance = new HL7EventContainer();
        }
        return instance;
    }


    public void put(Message message) {
        try {
            iheMessageQueue.put(message);
        } catch (InterruptedException e) {
            return;
        }
    }

    public void offer(Message message) {
        try {
            iheMessageQueue.offer(message);
        } catch (Throwable e) {
            return;
        }
    }


    public Message take() {
        try {
            return iheMessageQueue.take();
        } catch (InterruptedException e) {
            return null;
        }
    }
}