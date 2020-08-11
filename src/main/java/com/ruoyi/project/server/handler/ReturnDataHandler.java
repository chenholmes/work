package com.ruoyi.project.server.handler;

import com.ruoyi.project.server.protocol.packet.ReturnDataPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class ReturnDataHandler extends SimpleChannelInboundHandler<ReturnDataPacket> {
    public static final ReturnDataHandler INSTANCE = new ReturnDataHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ReturnDataPacket returnDataPacket) throws Exception {
        //TODO 收到客户端命令响应处理
    }
}
