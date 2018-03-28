package com.app.dtu.config;

import java.util.Arrays;
import java.util.List;

public class Const {
    // 帧头的固定长度
    public static final int HEAD_LEN = 4;
    // 帧尾的固定长度
    public static final int FOOL_LEN = 1;
    // 校验码长度
    public static final int CHECK_CODE_LEN = 2;
    // 固定字段长度
    public static final int FIXED_LEN = 19;

    public static final List<String> terminalCode = Arrays.asList(
            "91700001"
    );
}
