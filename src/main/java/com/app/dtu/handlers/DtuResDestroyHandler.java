package com.app.dtu.handlers;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 最后的处理，这里是需要关闭资源
 */
public class DtuResDestroyHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(DtuResDestroyHandler.class);

    /**
     * 这里是最终的handler处理， 即关闭连接
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.close();
        logger.info("Close netty socket server connection is success");
    }


    /**
     * 监听client连接当断开连接的时候回调处理
     *
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        logger.info("Listen client {} is not connection", incoming.remoteAddress());
    }

    /**
     * 如果处理出现异常关闭连接，防止资源的无效暂用
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel incoming = ctx.channel();
        logger.error("Listen socket connection {} exception, {}", incoming.remoteAddress(), cause.getMessage());
        ctx.close();
    }
}
