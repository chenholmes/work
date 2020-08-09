package com.ruoyi.project.server.protocol.deserialize.impl;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.deserialize.Deserializer;
import com.ruoyi.project.server.protocol.request.Device;
import com.ruoyi.project.server.protocol.request.DeviceStatusRequestPacket;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class DeviceStatusDeserializer implements Deserializer {

    @Override
    public Packet deserialize(byte[] data) {
        DeviceStatusRequestPacket packet = new DeviceStatusRequestPacket();
        packet.setNum(data[0]);
        packet.setDeviceList(parse(data));
        return packet;
    }


    private List<Device> parse(byte[] data) {
        List<Device> deviceList = new ArrayList<>();
        int n = 0;
        int start = 1;
        byte len = data[0];
        for(int i = 1; i < data.length; i++) {
            if(n == 0) i++;
            if(data[i] == 0) n++;
            if(n == 2) {
                byte[] dest = ArrayUtils.subarray(data,start,i + 1);
                Device device = new Device();
                device.setStatus(dest[0]);
                String destStr = new String(dest);
                String[] array = destStr.split("/0");
                device.setName(array[0]);
                device.setVsg(array[1]);
                n = 0;
                start = i + 1;
            }
            continue;

        }
        return deviceList;
    }
}
