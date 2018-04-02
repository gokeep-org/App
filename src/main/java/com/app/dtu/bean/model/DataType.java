package com.app.dtu.bean.model;

/**
 * 数据类型定义
 */
public enum DataType {
    DATA_TYPE_01(0x01, "漏电实时数"),
    DATA_TYPE_02(0x02, "漏电实时数"),
    DATA_TYPE_03(0x03, "漏电实时数"),
    DATA_TYPE_04(0x04, "漏电实时数"),
    DATA_TYPE_05(0x05, "漏电实时数"),
    DATA_TYPE_06(0x06, "漏电实时数");
    public int code;
    public String desc;

    DataType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
