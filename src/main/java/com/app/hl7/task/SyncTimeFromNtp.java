package com.app.hl7.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class SyncTimeFromNtp {
    private static final Logger logger = LoggerFactory.getLogger(SyncTimeFromNtp.class);

//    @Scheduled(cron="0 0/2 * * * ?")
    public void execute() {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("cd /Users/xuning/workspace/idea/App/script && expect sync_date.sh gazelle.ihe-c.org");
        } catch (IOException e) {
            logger.error("execute ntp server sync date find fail, now date is {}", new Date());
        }
        logger.info("execute ntp server sync date is success , now date is {}", new Date());
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//        Runtime runtime = Runtime.getRuntime();
//        Process process = runtime.exec("cd /Users/xuning/workspace/idea/App/script && expect sync_date.sh gazelle.ihe-c.org");
//    }
}
