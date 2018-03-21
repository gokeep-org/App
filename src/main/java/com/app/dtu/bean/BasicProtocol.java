package com.app.dtu.bean;

import java.util.Arrays;

public class BasicProtocol {
    /**
     * 消息的开头的信息标志
     */
    private int head_data ;
    /**
     * 消息的长度
     */
    private int contentLength;
    /**
     * 消息的内容
     */
    private byte[] content;


    /**
     * 用于初始化，SmartCarProtocol
     *
     * @param contentLength
     *            协议里面，消息数据的长度
     * @param content
     *            协议里面，消息的数据
     */
    public BasicProtocol(int contentLength, byte[] content) {
        this.contentLength = contentLength;
        this.content = content;
    }

    public int getHead_data() {
        return head_data;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SmartCarProtocol [head_data=" + head_data + ", contentLength="
                + contentLength + ", content=" + Arrays.toString(content) + "]";
    }
}
