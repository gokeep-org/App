package com.app.dtu.mq;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.config.DtuConfig;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 消息队列方法接收处理
 * 注意，这里是使用的默认是no-ack策略，性能会提高，但是会有一定的消息丢失
 * 可以复写该方法，使用回调的方式来实现消息的确认
 */
@Component
@RabbitListener(queues = DtuConfig.MQ_NAME)
public class RabbitMqMessageListenImpl implements Receiver {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqMessageListenImpl.class);
    private static final Gson serialize = new Gson();

    @Override
    @RabbitHandler
    public void process(String message) {
        if (StringUtils.isEmpty(message)) {
            logger.error("Receiver message is null or length is 0");
        }
        logger.info("Receiver message is [{}]", message);
        Message deviceMessage = null;
        try{
            deviceMessage = serialize.fromJson(message, Message.class);
            if (Objects.isNull(deviceMessage)){
                return;
            }
        }catch (Throwable e){
            logger.error("Receiver message generate entity i serror , cause: {}", e.getMessage());
        }
        try{
            DeviceDataDeal deviceDataDeal = deviceMessage.getDevice();
            boolean executeResult = Objects.isNull(deviceDataDeal) ? false : deviceDataDeal.execute();
            if (executeResult)
                logger.info("Add device data result to db is {}", executeResult);
            else
                logger.error("Add device data result to db is {}", executeResult);
        }catch (Throwable e){
            logger.error("Parse device message is error", e.getMessage());
        }

    }
}
