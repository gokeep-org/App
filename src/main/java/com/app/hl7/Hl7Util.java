package com.app.hl7;

import ch.qos.logback.core.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Hl7Util {
    private static final Logger logger = LoggerFactory.getLogger(Hl7Util.class);

    private Hl7Util(){}

    public static final void writeLog(String log){
        writeLog("\n\n" + log, Hl7ComonConfig.IHE_LOG_PATH, true);
    }



    public static final void writeLogNoAppend(String log){
        writeLog(log, Hl7ComonConfig.IHE_LOG_PATH, false);
    }

    public static final void writeLog(String log, String path, boolean append){
        File file = new File(path);
        try {
            FileUtils.writeStringToFile(file, log, "UTF-8", append);
        } catch (IOException e) {
            logger.error("write log message is fail", e.getMessage());
        }
    }

    public static final void cleanFileConetnt(){
        writeLog("", Hl7ComonConfig.IHE_LOG_PATH, false);
    }


}
