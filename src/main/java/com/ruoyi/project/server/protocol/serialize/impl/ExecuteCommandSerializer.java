package com.ruoyi.project.server.protocol.serialize.impl;

import com.ruoyi.project.server.protocol.packet.ExecuteCommandPacket;
import com.ruoyi.project.server.protocol.serialize.Serializer;

public class ExecuteCommandSerializer implements Serializer<ExecuteCommandPacket> {

    public static final ExecuteCommandSerializer INSTANCE = new ExecuteCommandSerializer();
    @Override
    public byte[] serialize(ExecuteCommandPacket packet) {
        byte[] result = intToBytesLittle(packet.getExeCommand());
        return result;
    }

    /**
     * 小端模式将int转成byte[]
     * @param value int值
     * @return byte[]
     */
    public static byte[] intToBytesLittle(int value) {
        byte[] result = new byte[2];
        result[1] = (byte) ((value >> 8) & 0xFF);
        result[0] = (byte) (value & 0xFF);
        return result;
    }
}
