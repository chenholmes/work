package com.ruoyi.common.model;

import java.util.List;

/**
 * @author wangsr.fnst
 */
public class DeviceStatusOrder {
    Byte deviceNum;
    List<Device> deviceList;

    public Byte getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(Byte deviceNum) {
        this.deviceNum = deviceNum;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }
}
