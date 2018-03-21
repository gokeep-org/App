package com.app.dtu.parser;

import java.util.Arrays;

/**
 * 字节转化帮助类
 */
public class ByteUtils {
    //默认大端模式
    public static boolean BIG_ENDIAN = true;

    /**
     * 设置字节序为大端模式
     */
    public static void setBigEndian() {
        BIG_ENDIAN = true;
    }

    /**
     * 设置字节序为小端模式
     */
    public static void setLittleEndian() {
        BIG_ENDIAN = false;
    }

    /**
     * u8(无符号1个字节)转化为short
     */
    public static short byteToShort(byte b) {
        return (short) (b & 0xff);
    }

    /**
     * u16(无符号2个字节)转化为int
     */
    public static int bytesToInt(byte byte1, byte byte2) {
        if (BIG_ENDIAN)
            return ((0xff & byte1) << 8) | (0xff & byte2);
        else
            return (0xff & byte1) | ((0xff & byte2) << 8);
    }

    /**
     * u16(无符号2个字节)转化为int
     */
    public static int bytesToInt(byte[] bytes) {
        return bytesToInt(bytes, 0);
    }

    /**
     * u16(无符号2个字节)转化为int
     */
    public static int bytesToInt(byte[] bytes, int index) {
        if (BIG_ENDIAN)
            return (0xff & bytes[index + 1]) | ((0xff & bytes[index]) << 8);
        else
            return (0xff & bytes[index]) | ((0xff & bytes[index + 1]) << 8);
    }

    /**
     * u32(无符号4个字节)转化为long
     */
    public static long bytesToLong(byte byte1, byte byte2, byte byte3, byte byte4) {
        if (BIG_ENDIAN)
            return (long) (((byte1 & 0xFF) << 24)
                    | ((byte2 & 0xFF) << 16)
                    | ((byte3 & 0xFF) << 8)
                    | ((byte4 & 0xFF))) & 0xFFFFFFFFL;
        else
            return (long) (((byte4 & 0xFF) << 24)
                    | ((byte3 & 0xFF) << 16)
                    | ((byte2 & 0xFF) << 8)
                    | ((byte1 & 0xFF))) & 0xFFFFFFFFL;
    }

    /**
     * u32(无符号4个字节)转化为long
     */
    public static long bytesToLong(byte[] bytes) {
        return bytesToLong(bytes, 0);
    }

    /**
     * u32(无符号4个字节)转化为long
     */
    public static long bytesToLong(byte[] bytes, int index) {
        if (BIG_ENDIAN)
            return (long) (((bytes[index] & 0xFF) << 24)
                    | ((bytes[index + 1] & 0xFF) << 16)
                    | ((bytes[index + 2] & 0xFF) << 8)
                    | ((bytes[index + 3] & 0xFF))) & 0xFFFFFFFFL;
        else
            return (long) (((bytes[index + 3] & 0xFF) << 24)
                    | ((bytes[index + 2] & 0xFF) << 16)
                    | ((bytes[index + 1] & 0xFF) << 8)
                    | ((bytes[index] & 0xFF))) & 0xFFFFFFFFL;
    }

    /**
     * short转化为u8(无符号1个字节)
     */
    public static byte shortToByte(short word) {
        return (byte) word;
    }

    /**
     * int转化为u16(无符号2个字节)
     */
    public static byte[] intToBytes(int value) {
        byte[] src = new byte[2];
        intToBytes(value, src, 0);
        return src;
    }

    /**
     * int转化为u16(无符号2个字节)
     */
    public static void intToBytes(int value, byte[] src) {
        intToBytes(value, src, 0);
    }

    /**
     * int转化为u16(无符号2个字节)
     */
    public static void intToBytes(int value, byte[] src, int index) {
        if (BIG_ENDIAN) {
            src[index] = (byte) ((value >> 8) & 0xff);
            src[index + 1] = (byte) (value & 0xff);
        } else {
            src[index] = (byte) (value & 0xff);
            src[index + 1] = (byte) ((value >> 8) & 0xff);
        }
    }

    /**
     * long转化为u32(无符号4个字节)
     */
    public static byte[] longToBytes(long value) {
        byte[] src = new byte[4];
        longToBytes(value, src);
        return src;
    }

    /**
     * long转化为u32(无符号4个字节)
     */
    public static void longToBytes(long value, byte[] src) {
        longToBytes(value, src, 0);
    }

    /**
     * long转化为u32(无符号4个字节)
     */
    public static void longToBytes(long value, byte[] src, int index) {
        if (BIG_ENDIAN) {
            src[index] = (byte) ((value >> 24) & 0xff);
            src[index + 1] = (byte) ((value >> 16) & 0xff);
            src[index + 2] = (byte) ((value >> 8) & 0xff);
            src[index + 3] = (byte) (value & 0xff);
        } else {
            src[index] = (byte) (value & 0xff);
            src[index + 1] = (byte) ((value >> 8) & 0xff);
            src[index + 2] = (byte) ((value >> 16) & 0xff);
            src[index + 3] = (byte) ((value >> 24) & 0xff);
        }
    }

    /**
     * 多个数组拼接
     */
    public static byte[] byteMerger(byte[] first, byte[]... rest) {
        int totalLength = first.length;
        for (byte[] array : rest) {
            totalLength += array.length;
        }
        byte[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (byte[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    /**
     * 字符串转换成十六进制字符串
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }



    /**
     * 把16进制字符串转换成字节数组
     * @return byte[]
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }


    /**
     * 数组转换成十六进制字符串
     * @return HexString
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static final String bytesToHexString(byte[] bArray, int len) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < len; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }


    /**
     * 数组转成十六进制字符串
     * @return HexString
     */
    public static String toHexString1(byte[] b){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i){
            buffer.append(toHexString1(b[i]));
        }
        return buffer.toString();
    }
    public static String toHexString1(byte b){
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1){
            return "0" + s;
        }else{
            return s;
        }
    }

    /**
     * 十六进制字符串转换成字符串
     * @return String
     */
    public static String hexStr2Str(String hexStr) {

        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }



    /**
     * 十六进制字符串转换字符串
     * @param HexString
     * @return String
     */
    public static String toStringHex(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }
}
