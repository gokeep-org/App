package com.app.hl7;

public class Hl7ComonConfig {
    public static final Boolean IS_CHECK_RECEIVER_APPLICATION_AND_FACILITY = true;
    public static final Boolean ENABLE_PIX_PDQ_SERVER = false;

    public static final Boolean ENABLE_CT_SYNC = true;


    //-----------------时间同步配置
    public static final String DATE_SYNC_SH_FILE_PATH = "/Users/xuning/workspace/idea/App/script/sync.sh ";
    public static final String DATE_SYNC_ADDRESS = "gazelle.ihe-c.org";// pool.ntp.org
    public static final String DATE_SYNC_CRON = "0 0/1 * * * ?";
    public static final long DATE_SYNC_INTERVAL = 1000 * 60;// （默认一分钟)
    public static final String DATE_SYNC_VIEW_RESULT_URL = "https://gazelle.ihe-c.org/XDStarClient/ct/consistentTime.seam";// （默认一分钟)


    public static final String IHE_LOG_PATH = "/Users/xuning/workspace/idea/App/log/ihe.log";
}
