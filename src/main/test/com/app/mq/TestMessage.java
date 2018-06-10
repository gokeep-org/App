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
        String msg = "{\"id\":\"9170001504000005\",\"warnList\":7,\"controCmd\":17,\"dataLen\":30,\"dataMsgs\":[{\"type\":3,\"len\":6,\"datas\":[0,0,0]},{\"type\":131,\"len\":4,\"datas\":[248,187]},{\"type\":4,\"len\":6,\"datas\":[0,0,0]},{\"type\":132,\"len\":4,\"datas\":[5000,35588]},{\"type\":0,\"len\":5}],\"status\":1,\"createTime\":1528459725156}";
        RabbitMqMessageListenImpl rabbitMqMessageListen = new RabbitMqMessageListenImpl();
        rabbitMqMessageListen.process(msg);
        System.out.println();
    }
}
