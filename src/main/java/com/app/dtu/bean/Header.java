package com.app.dtu.bean;

import com.app.dtu.config.Const;

/**
 * 固定的消息头
 * 4个字节长度
 */
public class Header {

    public static final byte[] header = new byte[Const.HEAD_LEN];

    static {
        header[0] = 0x5A;
        header[1] = 0x13;
        header[2] = 0x56;
        header[3] = 0x78;
    }

    /**
     * 比较head头， 两个byte[] 是否相同
     * @param bytes
     * @return
     */
    public static boolean compare(byte[] bytes){
        for (int i =0; i < Const.HEAD_LEN; i++){
            if (header[i] != bytes[i]){
                return false;
            }
        }
        return true;
    }

}
