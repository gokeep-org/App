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
}
