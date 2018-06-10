package com.app.netty;

import com.app.dtu.config.DtuConfig;
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
        byte[] msg = new byte[6];
        msg[0] = 0x5A;
        msg[1] = 0x13;
        msg[2] = 0x56;
        msg[3] = 0x78;
        msg[4] = 0x78;
        msg[5] = 0x78;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(DtuConfig.DEFAULT_HOST, DtuConfig.SOCKET_SERVER_PORT));
            socket.setKeepAlive(true);
            OutputStream out = socket.getOutputStream();
            ByteBuffer header = ByteBuffer.allocate(6);
            header.putInt(msg.length);
            out.write(msg);
            out.write(msg);
            out.flush();
            InputStream in = socket.getInputStream();
            byte[] buff = new byte[4096];
            int readed = in.read(buff);
            if (readed > 0) {
                String str = new String(buff, 4, readed);
                logger.info("redisClient received msg from server:" + str);
            }
            out.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Test
    public void sendSocketMessage(){
        byte[] msg = new byte[256];
        msg[0] = 0x5A;
        msg[1] = 0x13;
        msg[2] = 0x56;
        msg[3] = 0x78;
        msg[0] = 0x5A;
        msg[0] = 0x5A;
        int a = 0x0;

        System.out.println(0x03 * 256 + 0xe8);


    }

}
