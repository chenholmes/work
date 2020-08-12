package com.ruoyi.project.server.protocol.serialize.impl;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.serialize.Serializer;

/**
 * @author chenli.fnst
 * @date 2020/8/7 11:35
 */
public class ReturnDataSerializer implements Serializer {

    public static final ReturnDataSerializer INSTANCE = new ReturnDataSerializer();

    @Override
    public byte[] serialize(Packet packet) {
        byte[] result = intToBytesLittle(packet.getResult());
        return result;
    }

    /**
     * 小端模式将int转成byte[]
     * @param value int值
     * @return byte[]
     */
    public static byte[] intToBytesLittle(int value) {
        byte[] result = new byte[4];
        result[3] = (byte) ((value >> 24) & 0xFF);
        result[2] = (byte) ((value >> 16) & 0xFF);
        result[1] = (byte) ((value >> 8) & 0xFF);
        result[0] = (byte) (value & 0xFF);
        return result;
    }
}
