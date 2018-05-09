package com.app.task;

import com.app.AppStart;
import com.app.dtu.task.ScheduleOffLineDeviceUpdate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AppStart.class)
@ActiveProfiles(value = "dev")
public class TaskTest {
    @Autowired
    ScheduleOffLineDeviceUpdate scheduleOffLineDeviceUpdate;

    @Test
    public void test(){
        scheduleOffLineDeviceUpdate.updateOffLineData();
    }
}
