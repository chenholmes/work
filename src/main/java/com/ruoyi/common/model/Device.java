package com.ruoyi.common.model;

/**
 * @author wangsr.fnst
 */
public class Device {
    String DeviceName;
    String DeviceVsgPort;

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public String getDeviceVsgPort() {
        return DeviceVsgPort;
    }

    public void setDeviceVsgPort(String deviceVsgPort) {
        DeviceVsgPort = deviceVsgPort;
    }

    @Override
    public String toString() {
        return "Device{" +
                "DeviceName='" + DeviceName + '\'' +
                ", DeviceVsgPort='" + DeviceVsgPort + '\'' +
                '}';
    }
}
