package com.ruoyi.project.server.handler;


import com.alibaba.fastjson.JSON;
import com.ruoyi.project.server.NettySocketHandler;
import com.ruoyi.project.server.attribute.Attributes;
import com.ruoyi.project.server.protocol.request.ConnectRequestPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * 握手命令处理
 * @author chenli.fnst
 * @date 2020/8/5 11:01
 */
@ChannelHandler.Sharable
public class ConnectHandler extends SimpleChannelInboundHandler<ConnectRequestPacket>{

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectHandler.class);

    public static final ConnectHandler INSTANCE = new ConnectHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ConnectRequestPacket connectRequestPacket) throws Exception {
        String computerName = connectRequestPacket.getComputerName();
        String ip = ((InetSocketAddress)channelHandlerContext.channel().remoteAddress()).getAddress().getHostAddress();
        connectRequestPacket.setIp(ip);
        channelHandlerContext.channel().attr(Attributes.client).set(computerName);
        NettySocketHandler.put(computerName, (NioSocketChannel) channelHandlerContext.channel());
        System.out.println(JSON.toJSONString(connectRequestPacket));

        //TODO 操作DB

        connectRequestPacket.setResult(1);
        channelHandlerContext.channel().writeAndFlush(connectRequestPacket);
    }
}

