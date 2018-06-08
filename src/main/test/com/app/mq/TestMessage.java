package com.app.mq;

import com.app.AppStart;
import com.app.dtu.mq.RabbitMqMessageListenImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AppStart.class)
@ActiveProfiles(value = "produce")
public class TestMessage {

    @Test
    public void test() {
        String msg = "{\"id\":\"9170001504000002\",\"warnList\":0,\"controCmd\":28,\"dataLen\":4,\"dataMsgs\":[{\"type\":128,\"len\":2,\"datas\":[36751]}],\"status\":4,\"createTime\":1528456096282}";
        RabbitMqMessageListenImpl rabbitMqMessageListen = new RabbitMqMessageListenImpl();
        rabbitMqMessageListen.process(msg);
        System.out.println();
    }
}
