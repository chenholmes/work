package com.ruoyi.project.server.protocol.deserialize.impl;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.deserialize.Deserializer;
import com.ruoyi.project.server.protocol.packet.ReturnDataPacket;

public class ReturnDataDeserializer implements Deserializer {

    public final static ReturnDataDeserializer INSTANCE = new ReturnDataDeserializer();

    @Override
    public Packet deserialize(byte command, byte[] data) {
        int result = bytesToIntLittle(data);
        ReturnDataPacket packet = new ReturnDataPacket();
        packet.setResult(result);
        packet.setCommand(command);
        return packet;
    }

    private int bytesToIntLittle(byte[] src) {
        int value;
        value = (src[0] & 0xFF)
                | ((src[1] & 0xFF) << 8)
                | ((src[2] & 0xFF) << 16)
                | ((src[3] & 0xFF) << 24);
        return value;
    }
}
