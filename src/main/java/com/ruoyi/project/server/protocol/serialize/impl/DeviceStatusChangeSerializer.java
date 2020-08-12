package com.ruoyi.project.server.protocol.serialize.impl;

import com.ruoyi.project.server.protocol.packet.DeviceStausChangePacket;
import com.ruoyi.project.server.protocol.serialize.Serializer;

public class DeviceStatusChangeSerializer implements Serializer<DeviceStausChangePacket> {
    public static final DeviceStatusChangeSerializer INSTANCE = new DeviceStatusChangeSerializer();

    @Override
    public byte[] serialize(DeviceStausChangePacket packet) {
        byte status = (byte) packet.getStatus();
        byte[] deviceName = (packet.getDeviceName() + '\0').getBytes();
        byte[] vsgName = (packet.getVsgName() + '\0').getBytes();
        byte[] content = new byte[deviceName.length + vsgName.length + 3];
        content[0] = status;
        System.arraycopy(deviceName,0,content,1,deviceName.length);
        System.arraycopy(vsgName,0,content,deviceName.length + 1,vsgName.length);
        return content;
    }


}
