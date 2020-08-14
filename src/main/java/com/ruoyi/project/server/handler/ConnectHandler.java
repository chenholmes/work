package com.ruoyi.project.server.handler;


import com.alibaba.fastjson.JSON;
import com.ruoyi.project.server.NettySocketHandler;
import com.ruoyi.project.server.attribute.Attributes;
import com.ruoyi.project.server.protocol.packet.ConnectPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * 握手命令处理
 * @author chenli.fnst
 * @date 2020/8/5 11:01
 */
@ChannelHandler.Sharable
public class ConnectHandler extends SimpleChannelInboundHandler<ConnectPacket>{

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectHandler.class);

    public static final ConnectHandler INSTANCE = new ConnectHandler();

    private ConnectHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ConnectPacket connectPacket) throws Exception {
        String computerName = connectPacket.getComputerName();
        String ip = ((InetSocketAddress)channelHandlerContext.channel().remoteAddress()).getAddress().getHostAddress();
        connectPacket.setIp(ip);
        channelHandlerContext.channel().attr(Attributes.client).set(computerName);
        NettySocketHandler.put(computerName, (NioSocketChannel) channelHandlerContext.channel());
        LOGGER.info("computerName: {}, 建立连接, 连接信息: {}", computerName,JSON.toJSONString(connectPacket));

        //TODO 操作DB

        connectPacket.setResult(1);
        channelHandlerContext.channel().writeAndFlush(connectPacket);
    }
}

