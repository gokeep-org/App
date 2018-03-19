package com.app.netty;

import com.app.dtu.DtuConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class NettyTest {

    private static final Logger logger = LoggerFactory.getLogger(NettyTest.class);

    /**
     * 测试socket发送请求byte数据
     *
     */
    @Test
    public void testSendSocketMessage() {
        byte[] msg = "abcd".getBytes();
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(DtuConfig.DEFAULT_HOST, DtuConfig.SOCKET_SERVER_PORT));
            socket.setKeepAlive(true);
            OutputStream out = socket.getOutputStream();
            ByteBuffer header = ByteBuffer.allocate(4);
            header.putInt(msg.length);
            out.write(header.array());
            out.write(msg);
            out.flush();
            InputStream in = socket.getInputStream();
            byte[] buff = new byte[4096];
            int readed = in.read(buff);
            if (readed > 0) {
                String str = new String(buff, 4, readed);
                logger.info("client received msg from server:" + str);
            }
            out.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
