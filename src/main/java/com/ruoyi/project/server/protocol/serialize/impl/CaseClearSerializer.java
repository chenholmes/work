package com.ruoyi.project.server.protocol.serialize.impl;

import com.ruoyi.project.server.protocol.packet.CaseClearPacket;
import com.ruoyi.project.server.protocol.packet.CaseSendPacket;
import com.ruoyi.project.server.protocol.serialize.Serializer;

import java.util.List;

public class CaseClearSerializer implements Serializer<CaseClearPacket> {

    public static final CaseClearSerializer INSTANCE = new CaseClearSerializer();

    @Override
    public byte[] serialize(CaseClearPacket packet) {
        int num = packet.getNum();
        List<String> casePath = packet.getCasePath();
        int len = 0;
        for(String path : casePath) {
            len += path.length();
        }
        len = len + casePath.size() + 2;
        byte[] content = new byte[len];
        int destPos = 2;
        byte[] numB = intToBytesLittle(num);
        System.arraycopy(numB,0,casePath,destPos,numB.length);
        for(String path: casePath) {
            path = path + '\0';
            System.arraycopy(path.getBytes(), 0, content, destPos, path.getBytes().length);
            destPos += path.getBytes().length;
        }
        return content;
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
