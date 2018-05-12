package com.app.dtu.mq;

import com.app.dtu.config.DtuConfig;
import com.app.dtu.service.ServiceBeanNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

/****************************************
 * Copyright (c) xuning.
 * 尊重版权，禁止抄袭!
 * 如有违反，必将追究其法律责任.
 * @Auther is xuning on 2017/4/1.
 ****************************************/
@Component(value = ServiceBeanNames.RABBITMQ_LOG_SENDER)
public class RabbitMqMessageSenderImper implements Sender{

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqMessageSenderImper.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(String msg) {
        if (StringUtils.isEmpty(msg)) {
            LOGGER.info("RabbitMQ: push to elasticsearch message is null");
        } else {
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            LOGGER.info("RabbitMQ: Send log message correlation id is: " + correlationData.getId());
            this.rabbitTemplate.convertAndSend(DtuConfig.MQ_NAME, msg);
        }
    }
}