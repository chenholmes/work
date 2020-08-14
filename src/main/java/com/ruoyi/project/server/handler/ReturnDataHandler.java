package com.ruoyi.project.server.handler;

import com.ruoyi.project.server.attribute.Attributes;
import com.ruoyi.project.server.constants.ReturnCodeEnum;
import com.ruoyi.project.server.protocol.packet.ReturnDataPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class ReturnDataHandler extends SimpleChannelInboundHandler<ReturnDataPacket> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReturnDataHandler.class);

    public static final ReturnDataHandler INSTANCE = new ReturnDataHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ReturnDataPacket returnDataPacket) throws Exception {
        String client = channelHandlerContext.channel().attr(Attributes.client).get();
        int result = returnDataPacket.getResult();
        LOGGER.info("computerName: {}, 下发指令: {}, 返回结果: {}",client,returnDataPacket.getCommand(), ReturnCodeEnum.getEnum(result).getMsg());

    }
}
