package com.app.hl7;

import org.slf4j.Logger;

public class Hl7ComonConfig {
    //----------------PIX PDQ 服务端管理
    public static final Boolean IS_CHECK_RECEIVER_APPLICATION_AND_FACILITY = false;
    public static final Boolean ENABLE_PIX_PDQ_SERVER = false;

    public static final Boolean ENABLE_CT_SYNC = false;

    //-----------------时间同步配置
    public static final String DATE_SYNC_SH_FILE_PATH = "/Users/xuning/workspace/idea/App/script/sync.sh ";
//    public static final String DATE_SYNC_ADDRESS = "gazelle.ihe-c.org";// pool.ntp.org
    public static final String DATE_SYNC_ADDRESS = "172.16.2.49";// pool.ntp.org
    public static final String DATE_SYNC_CRON = "0 0/1 * * * ?";
    public static final String DATE_SYNC_VIEW_RESULT_URL = "https://gazelle.ihe-c.org/XDStarClient/ct/consistentTime.seam";// （默认一分钟)
    public static final long DATE_SYNC_INTERVAL = 1000 * 60;// （默认一分钟)
    //------------------日志输入输出日志
    public static final String IHE_LOG_PATH = "/Users/xuning/workspace/idea/App/log/ihe.log";


    public static final String sendApplication = "CHAOSI";

    public static final String sendFacilit = "CHAOSI_CLIENT";
}
