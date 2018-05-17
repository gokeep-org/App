package com.app.dtu.config;

import java.util.Arrays;
import java.util.List;

public class Const {

    public static final List<Integer> protocolHeader = Arrays.asList(0x5A,0x13,0x56,0x78);
    // 过鬼门关成人化
    public static final List<Integer> protocolTail = Arrays.asList(0x26,0x3A);

    // 帧头的固定长度
    public static final int HEAD_LEN = protocolHeader.size();
    // 帧尾的固定长度
    public static final int FOOL_LEN = protocolTail.size();
    // 校验码长度
    public static final int CHECK_CODE_LEN = 2;
    // 固定字段长最小度
    public static final int FIXED_LEN = 19;

    public static final List<String> terminalCode = Arrays.asList(
            "91700001"
    );
}
