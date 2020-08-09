package com.ruoyi.project.server.protocol.deserialize.impl;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.request.ConnectRequestPacket;
import com.ruoyi.project.server.protocol.deserialize.Deserializer;

/**
 * @author chenli.fnst
 * @date 2020/8/5 17:37
 */
public class ConnnectDeserializer implements Deserializer {

    public static final ConnnectDeserializer INSTANCE = new ConnnectDeserializer();


    @Override
    public Packet deserialize(byte[] data) {
        String str = new String(data, 0, data.length);
        String[] strArray = str.split("\0");
        ConnectRequestPacket packet = new ConnectRequestPacket(strArray[0],strArray[1],strArray[2]);

        return packet;
    }
}
