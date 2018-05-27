package com.app.dtu.bean.model;

import java.util.Arrays;
import java.util.List;

/**
 * 数据类型定义枚举
 */
public enum DataType {
    DATA_TYPE_01(0x01, "漏电实时数据"),
    DATA_TYPE_02(0x02, "温度实时数据"),
    DATA_TYPE_03(0x03, "电压实时数据"),
    DATA_TYPE_04(0x04, "电流实时数据"),
    DATA_TYPE_05(0x05, "电弧实时数据"),
    DATA_TYPE_06(0x06, "烟感实时数据"),
    DATA_TYPE_07(0x07, "温感实时数据"),
    DATA_TYPE_08(0x08, "天然气实时数据"),
    DATA_TYPE_09(0x09, "液化气实时数据"),
    DATA_TYPE_0A(0x0A, "煤制气实时数据"),
    DATA_TYPE_0B(0x0B, "闭门器实时数据"),
    DATA_TYPE_0C(0x0C, "门磁实时数据"),
    DATA_TYPE_0D(0x0D, "频率,总功率|因数,电能"),
    DATA_TYPE_0E(0x0E, "UI 相位|平衡,相P,Q,PF"),
    DATA_TYPE_0F(0x0F, "谐波含有率"),
    DATA_TYPE_10(0x10, "水压实时数"),
    DATA_TYPE_80(0x80, "其它配置数据"),
    DATA_TYPE_81(0x81, "漏电报警阈值"),
    DATA_TYPE_82(0x82, "温度报警阈值"),
    DATA_TYPE_83(0x83, "电压报警阈值"),
    DATA_TYPE_84(0x84, "电流报警阈值"),
    DATA_TYPE_85(0x85, "电弧报警阈值"),
    DATA_TYPE_86(0x86, "烟感报警阈值"),
    DATA_TYPE_87(0x87, "温感报警阈值"),
    DATA_TYPE_88(0x88, "天然气报警阈值"),
    DATA_TYPE_89(0x89, "液化气报警阈值"),
    DATA_TYPE_8A(0x8A, "煤制气报警阈值"),
    DATA_TYPE_8B(0x8B, "电流变比值"),
    DATA_TYPE_90(0x90, "水压报警阈值"),
    DATA_TYPE_11(0x11, "Ua谐波含有率"),
    DATA_TYPE_12(0x12, "Ub谐波含有率"),
    DATA_TYPE_13(0x13, "Uc谐波含有率"),
    DATA_TYPE_14(0x14, "Ia谐波含有率"),
    DATA_TYPE_15(0x15, "Ib谐波含有率"),
    DATA_TYPE_16(0x16, "Ic谐波含有率");
    public int code;
    public String desc;

    DataType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public final static List<DataType> dataTypes = Arrays.asList(DataType.values());

    /**
     * 根据Key获取属性值
     * @param key
     * @return
     */
    public static DataType getValue(Integer key){
        if (key == null){
            return null;
        }
        for (DataType dataType : dataTypes){
            if (dataType.code == key){
                return dataType;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static List<DataType> getDataTypes() {
        return dataTypes;
    }
}
