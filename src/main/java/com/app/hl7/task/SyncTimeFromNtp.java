package com.app.hl7.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SyncTimeFromNtp {
    private static final Logger logger = LoggerFactory.getLogger(SyncTimeFromNtp.class);

    @Scheduled(cron = "0 0/2 * * * ?")
    public void execute() {
        try {
            Process process = null;
            String shpath = "/Users/xuning/workspace/idea/App/script/sync.sh";
            String command = "/bin/sh " + shpath;
            List<String> processList = new ArrayList<String>();
            process = Runtime.getRuntime().exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
        } catch (Throwable e) {
            logger.error("execute ntp server sync date find fail, now date is {}", new Date());
        }
        logger.info("execute ntp server sync date is success , now date is {}", new Date());
    }
}
