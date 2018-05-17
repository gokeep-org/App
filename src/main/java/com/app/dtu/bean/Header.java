package com.app.dtu.bean;

import com.app.dtu.config.Const;
import io.netty.buffer.ByteBuf;

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
     *
     * @param bytes
     * @return
     */
    public static boolean compareWithHead(byte[] bytes) {
        for (int i = 0; i < Const.HEAD_LEN; i++) {
            if (header[i] != bytes[i]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 读取协议消息，直到读取到正确的消息为条件不再进行读取，执行后面的操作
     *
     * @return
     */
    public static boolean getValidMessageData(ByteBuf byteBuf) {
        byte[] allBytes = new byte[byteBuf.readableBytes()];
        while (byteBuf.readableBytes() <= 6) {
            byteBuf.readBytes(allBytes, 0, 1);
            if (allBytes[0] == header[0]) {
                byteBuf.readBytes(allBytes, 0, 1);
                if (allBytes[0] == header[1]) {
                    byteBuf.readBytes(allBytes, 0, 1);
                    if (allBytes[0] == header[2]) {
                        byteBuf.readBytes(allBytes, 0, 1);
                        if (allBytes[0] == header[3]) {
                            return true;
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        return false;
    }

}
