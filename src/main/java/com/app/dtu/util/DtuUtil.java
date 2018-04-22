package com.app.dtu.util;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class DtuUtil {
    private DtuUtil(){}

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

    public static String bytesToHexString(byte[] src){
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
}
