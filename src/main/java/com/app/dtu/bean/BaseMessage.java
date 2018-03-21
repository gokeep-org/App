package com.app.dtu.bean;

public class BaseMessage {
    private static byte[] header = new byte[4];

    private static byte[] foot = new byte[4];



    static {
        header[0] = 0x5A;
        header[0] = 0x13;
        header[0] = 0x56;
        header[0] = 0x78;
        foot[0] = 0x26;
        foot[0] = 0x3A;
    }


}
