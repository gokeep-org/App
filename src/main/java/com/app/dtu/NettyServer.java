package com.app.dtu;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NettyServer  implements ApplicationContextAware, com.app.dtu.ServerBootstrap {
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        start();
    }

    /**
     * 当程序启动容器加载完毕启动该socket服务
     */
    @PostConstruct
    @Override
    public void start() {
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new DtuServerInitializer())
//                    .option(ChannelOption.SO_BACKLOG, DtuConfig.SOCKET_SERVER_SO_BACKLOG)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(DtuConfig.SOCKET_SERVER_PORT).sync();
            f.channel().closeFuture().sync();
            logger.info("Start netty socket server is success");
        } catch (InterruptedException e) {
            logger.error("Start netty socket server is fail, {}", e.getMessage());
        } finally {
            stop();
        }
    }

    @Override
    public void stop() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        logger.info("Netty socket close server is success");
    }
}