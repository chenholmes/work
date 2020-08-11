package com.ruoyi.project.server.handler;

import com.ruoyi.project.server.attribute.Attributes;
import com.ruoyi.project.server.protocol.packet.VsgStatusPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class VsgStatusHandler extends SimpleChannelInboundHandler<VsgStatusPacket> {

    public static final VsgStatusHandler INSTANCE = new VsgStatusHandler();

    private VsgStatusHandler(){}
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, VsgStatusPacket vsgStatusPacket) throws Exception {
        String computerName = channelHandlerContext.channel().attr(Attributes.client).get();
        //TODO DB
        channelHandlerContext.channel().writeAndFlush(vsgStatusPacket);
    }
}
