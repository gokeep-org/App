package com.app.dtu.config;

import com.app.dtu.handlers.DataStorgeHandler;
import com.app.dtu.handlers.DtuMsgHeaderHandler;
import com.app.dtu.handlers.DtuResDestroyHandler;
import com.app.dtu.handlers.DtuServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/****************************************
 * Copyright (c) xuning.
 * 尊重版权，禁止抄袭!
 * 如有违反，必将追究其法律责任.
 * @Auther is xuning on 2017/5/14.
 ****************************************/
public class DtuServerInitializer extends ChannelInitializer<SocketChannel> {

    private static final Logger logger = LoggerFactory.getLogger(DtuServerInitializer.class);

    /**
     * 这里不使用解码，编码器， 只是实现消息的处理
     * @param socketChannel
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        /**
         * 注册socket处理组件
         * DtuConfig.ENABLE_KEEP_ALIVE_CONNECTION 决定是否使用长连接
         */
        ChannelPipeline pipeline = socketChannel.pipeline();
        /*
        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());// 解析
        pipeline.addLast("encoder", new StringEncoder()); //
        pipeline.addLast("encoder", new ByteArrayDecoder());
        pipeline.addLast("handler", new DtuServerHandler());
        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
        pipeline.addLast(new FixedLengthFrameDecoder(6));
        */
        pipeline.addLast("msg-filter-handler", new DtuMsgHeaderHandler());
        pipeline.addLast("dtu-handler", new DtuServerHandler());
        pipeline.addLast("storge-handler", new DataStorgeHandler());
        if (!DtuConfig.ENABLE_KEEP_ALIVE_CONNECTION){
            pipeline.addLast("destroy-res-handler", new DtuResDestroyHandler());
        }
        logger.info("Netty socket server listen client connection, [ip: {}]", socketChannel.remoteAddress());
    }
}
