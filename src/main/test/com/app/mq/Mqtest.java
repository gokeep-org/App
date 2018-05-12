package com.app.mq;

import com.app.AppStart;
import com.app.dtu.mq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AppStart.class)
@ActiveProfiles(value = "produce")
public class Mqtest {
    @Autowired
    Sender mqClient;

    @Test
    public void simple(){
        mqClient.send("This is a test");
    }
}
