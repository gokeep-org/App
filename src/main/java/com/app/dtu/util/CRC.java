package com.app.dtu.util;

import com.app.dtu.bean.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hp on 2017/6/29.
 */
public class CRC {
    private static final Logger logger = LoggerFactory.getLogger(CRC.class);
    /**
     * CRC 8 lookup table
     */
    private static byte dscrc_table[];

    /*
     * Create the lookup table
     */
    static {
        //Translated from the assembly code in iButton Standards, page 129.
        dscrc_table = new byte[256];
        int acc;
        int crc;
        for (int i = 0; i < 256; i++) {
            acc = i;
            crc = 0;
            for (int j = 0; j < 8; j++) {
                if (((acc ^ crc) & 0x01) == 0x01) {
                    crc = ((crc ^ 0x18) >> 1) | 0x80;
                } else
                    crc = crc >> 1;
                acc = acc >> 1;
            }
            dscrc_table[i] = (byte) crc;
        }
    }
    /**
     * Private constructor to prevent instantiation.
     */
    /**
     * Perform the CRC8 on the data element based on the provided seed.
     * <p>
     * CRC8 is based on the polynomial = X^8 + X^5 + X^4 + 1.
     *
     * @param dataToCRC data element on which to perform the CRC8
     * @param seed      seed the CRC8 with this value
     * @return CRC8 value
     */
    public static int compute(int dataToCRC, int seed) {
        return (dscrc_table[(seed ^ dataToCRC) & 0x0FF] & 0x0FF);
    }

    /**
     * Perform the CRC8 on the data element based on a zero seed.
     * <p>
     * CRC8 is based on the polynomial = X^8 + X^5 + X^4 + 1.
     *
     * @param dataToCRC data element on which to perform the CRC8
     * @return CRC8 value
     */
    public static int compute(int dataToCRC) {
        return (dscrc_table[dataToCRC & 0x0FF] & 0x0FF);
    }

    /**
     * Perform the CRC8 on an array of data elements based on a
     * zero seed.
     * <p>
     * CRC8 is based on the polynomial = X^8 + X^5 + X^4 + 1.
     *
     * @param dataToCrc array of data elements on which to perform the CRC8
     * @return CRC8 value
     */
    public static int compute(byte dataToCrc[]) {
        return compute(dataToCrc, 0, dataToCrc.length);
    }

    /**
     * Perform the CRC8 on an array of data elements based on a
     * zero seed.
     * <p>
     * CRC8 is based on the polynomial = X^8 + X^5 + X^4 + 1.
     *
     * @param dataToCrc array of data elements on which to perform the CRC8
     * @param off       offset into array
     * @param len       length of data to crc
     * @return CRC8 value
     */
    public static int compute(byte dataToCrc[], int off, int len) {
        return compute(dataToCrc, off, len, 0);
    }

    /**
     * Perform the CRC8 on an array of data elements based on the
     * provided seed.
     * <p>
     * CRC8 is based on the polynomial = X^8 + X^5 + X^4 + 1.
     *
     * @param dataToCrc array of data elements on which to perform the CRC8
     * @param off       offset into array
     * @param len       length of data to crc
     * @param seed      seed to use for CRC8
     * @return CRC8 value
     */
    public static int compute(byte dataToCrc[], int off, int len, int seed) {
        // loop to do the crc on each data element
        int CRC8 = seed;
        for (int i = 0; i < len; i++)
            CRC8 = dscrc_table[(CRC8 ^ dataToCrc[i + off]) & 0x0FF];
        return (CRC8 & 0x0FF);
    }

    /**
     * Perform the CRC8 on an array of data elements based on the
     * provided seed.
     * <p>
     * CRC8 is based on the polynomial = X^8 + X^5 + X^4 + 1.
     *
     * @param dataToCrc array of data elements on which to perform the CRC8
     * @param seed      seed to use for CRC8
     * @return CRC8 value
     */
    public static int compute(byte dataToCrc[], int seed) {
        return compute(dataToCrc, 0, dataToCrc.length, seed);
    }

    /**
     * 检查数据是否完整
     *
     * @param bytes 原始数组
     * @param off   开始位置
     * @param len   协议数据的长度
     * @return
     */
    public static boolean check(byte[] bytes, int off, int len) throws Exception {
        // 校验包头
        for (int i = 0; i < Header.header.length; i++) {
            if (Header.header[i]!=bytes[off+i])
                throw new Exception("protocol header error-->" + HexString.bytesToHexString(bytes, off, 4));
        }
        short length= (short)(bytes[off + 14]<<8);
        length+=(short)(bytes[off + 15])+19;
        // 校验长度
        if (length != len) {
            throw new Exception("protocol length error-->" + ((short)(bytes[off + 14]<<8))+((short)bytes[off + 15]));
        }
        // 校验码
        int sourceCrc = ((int)(bytes[len - 4]<<8))+((int)bytes[len - 3]);
        int targetCrc = compute(bytes, off, len - 1);
//        int targetCrc = compute2(bytes, len-1);
        if (sourceCrc != targetCrc) {
            throw new Exception("protocol CRC error-->" + (bytes[off + len - 1] & 0xff));
        }
        return true;
    }

    /**
     * 检查数据是否完整
     *
     * @param bytes
     * @return
     */
    public static boolean check(byte[] bytes) throws Exception {
        return check(bytes, 0, bytes.length);
    }
}
