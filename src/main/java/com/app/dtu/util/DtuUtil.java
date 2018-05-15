package com.app.dtu.util;

import com.app.dtu.bean.Message;
import com.app.dtu.config.DtuConfig;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DtuUtil {
    private DtuUtil(){}
    private static final Logger logger = LoggerFactory.getLogger(DtuUtil.class);
    private static final SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Gson gson = new Gson();
    /**
     * 获取设备的上传数据的值根据索引位置
     * @param values
     * @param index
     * @return
     */
    public static final Integer getValue(List<Integer> values, int index){
        try{
            if (CollectionUtils.isEmpty(values)){
                return null;
            }
            int count = values.size();
            if (index >= count){
                return null;
            }
            return values.get(index);
        }catch (Throwable e){
            return null;
        }
    }

    /**
     * 16
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src){
        if (null == src || src.length == 0){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        logger.info("bate string builer lenght is {}", stringBuilder.length());
        return stringBuilder.toString();
    }

    /**
     * 不再使用该方法
     * @param src
     * @return
     */
    public static String bytesToHexFormatString(byte[] src){
        if (null == src || src.length == 0){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        String format = new String();
        char[] result = stringBuilder.toString().toCharArray();
        for (int i = 0; i < result.length; i = i + 2){
            format += result[i]  + result[i+1] + "";
        }
        return format;
    }

    /**
     * 获取上个月的今天
     * @return
     */
    public static String getLastMonthDayTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        String time = simpleDateFormat.format(calendar.getTime());
        return time;
    }

    /**
     * 获取当前的时间
     * @return
     */
    public static String getCurrentTime(){
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static long getCurrentTimestrap(){
        return System.currentTimeMillis();
    }

    /**
     * 获取上个月的第一天
     * @return
     */
    public static String getLastMonthFristDayTime() {
        //获取前一个月第一天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 获取上个月的第一天
     * @return
     */
    public static String getLastMonthEndDayTime() {
        //获取前一个月第一天
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 获取之前的时间
     * @param time
     * @return
     */
    public static long getBrforeTime(long time){
        return System.currentTimeMillis() - time;
    }

    /**
     * 获取之后的时间
     * @param time
     * @return
     */
    public static long getAfterTime(long time){
        return System.currentTimeMillis() + time;
    }

    /**
     * 获取n小时之前的时间戳
     * @return
     */
    public static long getBeforeTimeForHors(int num){
       return getCurrentTimestrap() - num * 3600 * 1000;
    }

    /**
     * 获取48小时之前的时间戳
     * @return
     */
    public static long getUpdateOfflineTaskTime(){
        return getBrforeTime(DtuConfig.LOCAL_OFF_LINE_UPDATE_BEFORE_TIME_SCAN);
    }


    public static String getJsonByMessage(Message message){
        return gson.toJson(message, Message.class);
    }

}
