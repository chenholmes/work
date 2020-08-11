package com.ruoyi.project.server.protocol.deserialize.impl;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.deserialize.Deserializer;
import com.ruoyi.project.server.protocol.packet.VsgStatusPacket;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Date: 2020-08-09 21:49.</p>
 *
 * @author chenli
 */
public class VsgStatusDeserializer implements Deserializer {

    public final static VsgStatusDeserializer INSTANCE = new VsgStatusDeserializer();
    @Override
    public Packet deserialize(byte command,byte[] data) {
        VsgStatusPacket packet = new VsgStatusPacket();
        int num = data[0];
        packet.setNum(num);
        byte[] content = new byte[data.length - 1];
        System.arraycopy(data,1,content,data.length,data.length-1);
        String str = new String(content);
        List<String> vsgList = Arrays.asList(str.split("\0"));
        packet.setVsgList(vsgList);
        return packet;
    }
}
