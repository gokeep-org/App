package com.app.netty;

import com.app.dtu.parser.ByteUtils;
import org.junit.Test;

public class ParserTest {
    @Test
    public void testConst(){
//        byte[] v=
        System.out.println(0x0401);
        try {
//            0x5A,0x13,0x56,0x78
                byte[] b = new byte[256];
                b[0] = (byte) 0x5A;
                b[1] = (byte) 0x13;
                b[2] = (byte) 0x56;
                b[3] = (byte) 0x78;
                b[4] = (byte) 0x00;
                b[5] = (byte) 0x00;
                b[6] = (byte) 0x00;
                b[7] = (byte) 0x00;
                b[8] = (byte) 0x00;
                b[9] = (byte) 0x00;
                b[10] = (byte) 0x00;
                b[11] = (byte) 0x00;
                b[12] = (byte) 0x00;
                b[13] = (byte) 0x00;
                b[14] = (byte) 0x00;
                b[15] = (byte) 0x00;
                b[16] = (byte) 0x00;
            long s = ByteUtils.bytesToLong(b[0], b[1], b[1], b[1]);
            System.out.println(s);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOut(){
        System.out.println(0x5A135678);
    }
}
