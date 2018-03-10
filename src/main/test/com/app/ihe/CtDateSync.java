package com.app.ihe;

import com.app.hl7.Hl7ComonConfig;
import com.app.hl7.task.SyncTimeFromNtp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Date;

/**
 * 测试url： https://gazelle.ihe-c.org/XDStarClient/ct/consistentTime.seam
 */
public class CtDateSync {
    private static final Logger logger = LoggerFactory.getLogger(CtDateSync.class);

    private SyncTimeFromNtp syncTimeFromNtp;
    private long dateInterval = Hl7ComonConfig.DATE_SYNC_INTERVAL;

    @Before
    public void before(){
        syncTimeFromNtp = new SyncTimeFromNtp();
        logger.info("Init date sync service");
        java.awt.Desktop dp = java.awt.Desktop.getDesktop();
        try {
            dp.browse(URI.create(Hl7ComonConfig.DATE_SYNC_VIEW_RESULT_URL));
        } catch (IOException e) {
            logger.error("Open test date sync brow is error, is {}", e.getMessage());
        }
    }

    @Test
    public void startTest(){
        while (true){
            try {
                syncTimeFromNtp.execute();
                logger.info("Sync date from ntp success， now date is {}", new Date());
                Thread.sleep(dateInterval);
            } catch (InterruptedException e) {
                logger.error("Sync date from ntp find error, ", e.getMessage());
            }
        }
    }

    @After
    public void end(){
        logger.info("Close date sync service");
    }
}
