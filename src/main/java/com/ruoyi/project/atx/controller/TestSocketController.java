package com.ruoyi.project.atx.controller;

import com.ruoyi.project.server.NettySocketHandler;
import com.ruoyi.project.server.protocol.packet.CaseClearPacket;
import com.ruoyi.project.server.protocol.packet.CaseSendPacket;
import com.ruoyi.project.server.protocol.packet.ExecuteCommandPacket;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
//http://localhost:4399/test/casesend/G08FNSTD190044
//http://localhost:4399/test/caseclear/G08FNSTD190044
//http://localhost:4399/test/execute/G08FNSTD190044
@RestController
@RequestMapping("/test")
public class TestSocketController {

    @GetMapping("/casesend/{computerName}")
    public String caseSend(@PathVariable String computerName) {
        NioSocketChannel channel =  NettySocketHandler.get(computerName);
        CaseSendPacket packet = new CaseSendPacket();
        packet.setNum(3);
        List<String> list = new ArrayList<>();
        list.add("D:\\workspace\\IdeaProjects\\atx-backend");
        list.add("D:\\aa\\bb");
        list.add("D:\\cc\\dd");
        packet.setCasePath(list);
        channel.writeAndFlush(packet);
        return computerName;
    }
    @GetMapping("/caseclear/{computerName}")
    public String caseClear(@PathVariable String computerName) {
        NioSocketChannel channel =  NettySocketHandler.get(computerName);
        CaseClearPacket packet = new CaseClearPacket();
        packet.setNum(2);
        List<String> list = new ArrayList<>();
        list.add("D:\\aa\\bb");
        list.add("D:\\cc\\dd");
        packet.setCasePath(list);
        channel.writeAndFlush(packet);
        return computerName;
    }

    @GetMapping("/execute/{computerName}")
    public String execute(@PathVariable String computerName) {
        NioSocketChannel channel =  NettySocketHandler.get(computerName);
        ExecuteCommandPacket packet = new ExecuteCommandPacket();
        packet.setExeCommand(1);
        channel.writeAndFlush(packet);
        return computerName;
    }

}
