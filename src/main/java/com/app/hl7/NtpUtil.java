package com.app.hl7;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NtpUtil {

    private static final Logger logger = LoggerFactory.getLogger(NtpUtil.class);
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String serverTime;
    private static String localTime = smartFormtTime(new Date().getTime());
    private static String osType;
    private static String ntpServerAddress = "time-a.nist.gov";
    private NtpUtil(){}
    public void before() {
        logger.info("Test time NTP, now time is {}", localTime);
        osType = getOsType();
        logger.info("Get os type is {}", osType);
    }

    public void start() {
        // 1: 从NTP服务器获取东八区时间
        serverTime = getDateTimeFromNtpServer();
        logger.info("get date time from NTP server is: {}", serverTime);
        localTimeSynchronization();
    }


    public void after() {
        logger.info("Synchronization local system time end!, time is: {}", serverTime);
    }


    public String getDateTimeFromNtpServer() {
        String ntpAddress = "cn.ntp.org.cn";
        return getDateTimeFromNtpServer(ntpAddress);
    }

    public static String getDateTimeFromNtpServer(String ntpAddress) {
        try {
            NTPUDPClient timeClient = new NTPUDPClient();
            InetAddress timeServerAddress = InetAddress.getByName(ntpAddress);
            TimeInfo timeInfo = timeClient.getTime(timeServerAddress);
            TimeStamp timeStamp = timeInfo.getMessage().getTransmitTimeStamp();
            return dateFormat.format(timeStamp.getDate());
        } catch (Throwable e) {
            logger.error("与ntp服务器同步时间错误！", e);
        }
        return null;
    }

    public void localTimeSynchronization() {
        resetTimeByOsType();
    }


    public static final String getOsType() {
        String osName = System.getProperty("os.name");
        if (osName.equalsIgnoreCase("Mac OS X")) {
            return "MAC";
        } else if (osName.equalsIgnoreCase("win")) {
            return "WIN";
        } else {
            return "LINUX";
        }
    }

    public void resetTimeByOsType() {
        String cmd = "";
        try {
            if (osType.equalsIgnoreCase("WIN")) {// Window 系统
                // 格式 HH:mm:ss
                cmd = "cmd /c time " + serverTime.substring(11, 8);
                Runtime.getRuntime().exec(cmd);
                // 格式：yyyy-MM-dd
                cmd = "cmd /c date " + serverTime.substring(0, 10);
                Runtime.getRuntime().exec(cmd);
            } else if (osType.equalsIgnoreCase("LINUX")) {// Linux 系统
                // 格式：yyyyMMdd
                cmd = "date -s " + serverTime + " && hwclock --systohc";
                Runtime.getRuntime().exec(cmd);
            } else {
                cmd = "sudo date " + serverTime;
                Runtime.getRuntime().exec(cmd);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String smartFormtTime(long time) {
        return dateFormat.format(time);
    }

}
