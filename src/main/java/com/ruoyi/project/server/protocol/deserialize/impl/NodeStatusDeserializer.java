package com.ruoyi.project.server.protocol.deserialize.impl;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.deserialize.Deserializer;
import com.ruoyi.project.server.protocol.packet.NodeStatusPacket;

/**
 * @author chenli.fnst
 * @date 2020/8/7 15:34
 */
public class NodeStatusDeserializer implements Deserializer {

    public static final NodeStatusDeserializer INSTANCE = new NodeStatusDeserializer();

    @Override
    public Packet deserialize(byte command, byte[] data) {
        NodeStatusPacket packet = new NodeStatusPacket();
        packet.setStatus(data[0]);
        packet.setStatus(data[1]);
        packet.setCurrentCaseNum(data[2] & 0xff | data[3] << 8);
        packet.setTotalCaseNum(data[4] & 0xff | data[5] << 8);
        packet.setResult(data[6]);
        return packet;
    }
}
