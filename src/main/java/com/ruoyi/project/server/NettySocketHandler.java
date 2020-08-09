package com.ruoyi.project.server;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenli.fnst
 * @date 2020/8/5 10:23
 */
public class NettySocketHandler {

    private static final Map<String, NioSocketChannel> MAP = new ConcurrentHashMap<>();

    public static void put(String computerName, NioSocketChannel socketChannel) {
        MAP.put(computerName,socketChannel);
    }

    public static NioSocketChannel get(String computerName) {
        return MAP.get(computerName);
    }

    public static void remove(String computerName) {
        MAP.remove(computerName);
    }

}
