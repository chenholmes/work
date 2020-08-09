package com.ruoyi;

import com.ruoyi.common.model.*;

import com.ruoyi.project.atx.domain.AtxNode;
import com.ruoyi.project.atx.mapper.AtxNodeMapper;
import com.ruoyi.project.atx.utils.BeanContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangsr.fnst
 */
public class ReceiveThread extends Thread {


   private AtxNodeMapper atxNodeMapper;

    static int cnt = 0;
    private InputStream a = null;
    private OutputStream b = null;
    private Socket s = null;

    public ReceiveThread(Socket s, InputStream a, OutputStream b) {
        this.a = a;
        this.b = b;
        this.s = s;
    }

    @Override
    public void run() {
        try {
            byte[] bytes = new byte[65535];
            // 服务器向客户端写入数据
            byte[] sendBytes = new byte[65535];

            while (true) {
                int lenR = a.read(bytes);
                System.out.println(bytes[1]);
                System.out.println(bytes[2]);
                // 对方已断开连接，这时终止该线程
                if (lenR == -1) {
                    break;
                }
                // TODO 先简单处理，后面再加上组帧的概念
                if (lenR > 15) {
                    FrameATX frameATX = new FrameATX();
                    frameATX.setStxByte(bytes[0]);
                    //8位转化为16位
                    frameATX.setDataLenShort((short) (((bytes[2] << 8) | bytes[1] & 0xff)));
                    frameATX.setDataLenCShort((short) (((bytes[4] << 8) | bytes[3] & 0xff)));
                    frameATX.setNumberShort((short) (((bytes[6] << 8) | bytes[5] & 0xff)));
                    frameATX.setReserveByte(arraySub(bytes, 7, 11));
                    frameATX.setOrderShort((short) (((bytes[12] << 8) | bytes[11] & 0xff)));
                    frameATX.setDataByte(arraySub(bytes, 13, 13 + frameATX.getDataLenShort()));
                    frameATX.setCheckSum(bytes[lenR - 2]);
                    frameATX.setEtxByte(bytes[lenR - 1]);
                    // 数据部展示
                    System.out.println("接收到的长度为：" + lenR);
                    System.out.println("数据:" + new String(bytes, 13, frameATX.getDataLenShort()));
                    System.out.println("接受到客户端的消息：" + frameATX.toString());
                    sendBytes = setHandShakeOrder(frameATX.getDataByte());
                    b.write(sendBytes);
                    //什么时候需要传给前台，什么时候需要存到数据库里
                    switch (frameATX.getOrderShort()) {
                        case 0x01:
                            // 握手命令,socket连接建立时client一次性发送给server
                            sendBytes = setHandShakeOrder(frameATX.getDataByte());
                            b.write(sendBytes);

                            break;
                        case 0x02:
                            // 台架状态命令,socket连接建立后，每3秒发送一次
                            setNodeStatusOrder(frameATX.getDataByte());
                            byte[] retrunData = new byte[2];
                            b.write(retrunData);
                            break;
                        case 0x03:
                            // device状态
                            sendBytes = setDeviceStatusOrder(frameATX.getDataByte());
                            b.write(sendBytes);
                            break;
                        case 0x04:
                            // vsg状态
                            sendBytes = setVsgStatusOrder(frameATX.getDataByte());
                            b.write(sendBytes);
                            break;
                        case 0x05:
                            // device状态修改
                            sendBytes = setDeviceStatusChangeOrder(frameATX.getDataByte());
                            b.write(sendBytes);
                            break;
//                        case 0x06:
//                            // case下发命令
//                            sendBytes = setCaseOrder(frameATX.getDataByte());
//                            b.write(sendBytes);
//                            break;
//                        case 0x07:
//                            // case清除命令
//                            sendBytes = setCaseCleanOrder(frameATX.getDataByte());
//                            b.write(sendBytes);
//                            break;
//                        case 0x08:
//                            // 执行操作命令
//                            sendBytes = setOperationOrder(frameATX.getDataByte());
//                            b.write(sendBytes);
//                            break;
                        default:
                            System.out.println("没找到命令：" + frameATX.getOrderShort());
                    }

                    // 随便返回一下
                    byte[] retrunData = new byte[4];
                    retrunData = int2Bytes(0);
                    sendBytes = setAtxPacData(1, 4, retrunData);
                    b.write(sendBytes);
                }
            }
        } catch (IOException e) {
            System.out.println("----连接异常---");
            e.printStackTrace();
        }
    }

    /**
     * 截取byte数组
     *
     * @param data
     * @param start
     * @param end
     * @return
     */
    public byte[] arraySub(byte[] data, int start, int end) {
        byte[] C = new byte[end - start];//新建数组C长度为start-end
        int j = 0;
        for (int i = start; i < end; i++) {
            C[j] = data[i];
            j++;
        }
        return C;
    }

    /**
     * int装换为byte类型
     *
     * @param num
     * @return
     */
    public byte[] int2Bytes(int num) {
        byte[] bytes = new byte[4];
        //通过移位运算，截取低8位的方式，将int保存到byte数组
        bytes[0] = (byte) (num >>> 24);
        bytes[1] = (byte) (num >>> 16);
        bytes[2] = (byte) (num >>> 8);
        bytes[3] = (byte) num;
        return bytes;
    }

    /**
     * 给发送数据打个包
     *
     * @param cmd
     * @param size
     * @param data
     * @return
     */
    public byte[] setAtxPacData(int cmd, int size, byte[] data) {
        byte sum = 0;
        byte[] sendbuf = new byte[15 + size];
        sendbuf[0] = 2;
        sendbuf[1] = (byte) size;
        sendbuf[2] = (byte) (size >> 8);
        sendbuf[3] = (byte) (0 - size);
        sendbuf[4] = (byte) ((0 - size) >> 8);
        sendbuf[5] = (byte) cnt;
        sendbuf[6] = (byte) (cnt >> 8);
        sendbuf[11] = (byte) cmd;
        sendbuf[12] = (byte) (cmd >> 8);
        System.arraycopy(data, 0, sendbuf, 13, size);
        cnt++;
        size += 13;
        for (int i = 1; i < size; i++) {
            sum += sendbuf[i];
        }
        sendbuf[size++] = sum;
        sendbuf[size++] = 3;
        return sendbuf;
    }

    /**
     * 命令0x01 数据封装
     *
     * @param data 帧数据
     * @return
     */
    public byte[] setHandShakeOrder(byte[] data) throws IOException {
        byte[] sendBytes = new byte[65535];
        byte[] returnData = new byte[4];
        String str = new String(data, 0, data.length);
        String[] starr = str.split("\0");
        if (starr.length != 3) {
            // 2：命令参数错误返回
            returnData = int2Bytes(2);
            sendBytes = setAtxPacData(1, 4, returnData);
        } else {
            HandShakeOrder handShakeOrder = new HandShakeOrder(starr[0], starr[1], starr[2]);
            System.out.println("0x01命令的数据信息" + handShakeOrder.toString());
            // TODO: 存数据库或者传到前台
            AtxNode atxNode = new AtxNode();
//            atxNode.setIp(handShakeOrder.getIpString());
//            atxNode.setMac(handShakeOrder.getMacString());
            atxNode.setIp(s.getInetAddress().getHostAddress());
            String macAddress = getLocalMacByIp(s.getInetAddress().getHostAddress());
            atxNode.setMac(macAddress);
            atxNode.setComputerName(handShakeOrder.getComNameString());
            try {
                // TODO 这个自动生成的不太行
                System.out.println("wozaizheli");
                atxNodeMapper = BeanContext.getApplicationContext().getBean(AtxNodeMapper.class);
                  atxNodeMapper.insert(atxNode);

            } catch (Exception e) {
                // TODO: 1：命令处理错误
                // 0：命令正确处理
                returnData = int2Bytes(1);
                sendBytes = setAtxPacData(1, 4, returnData);
                e.printStackTrace();
            }
            // 0：命令正确处理
            returnData = int2Bytes(0);
            sendBytes = setAtxPacData(1, 4, returnData);
        }
        return sendBytes;
    }

    /**
     * 命令0x02
     *
     * @param data
     */
    public void setNodeStatusOrder(byte[] data) {
        if (data.length == 4) {
            NodeStatusOrder nodeStatusOrder = new NodeStatusOrder();
            nodeStatusOrder.setStatus(data[0]);
            nodeStatusOrder.setProgress(data[1]);
            nodeStatusOrder.setReserve(arraySub(data, 2, 4));
            // TODO: 存数据库或者传到前台
            System.out.println("0x02命令的数据信息" + nodeStatusOrder.toString());
        }
    }

    /**
     * 命令0x03 数据封装
     *
     * @param data 帧数据
     * @return
     */
    public byte[] setDeviceStatusOrder(byte[] data) {
        byte[] sendBytes = new byte[65535];
        byte[] returnData = new byte[4];
        DeviceStatusOrder deviceStatusOrder = new DeviceStatusOrder();
        // device数量
        deviceStatusOrder.setDeviceNum(data[0]);
        // device数量大于0
        if (deviceStatusOrder.getDeviceNum() > 0) {
            String str = new String(data, 1, data.length);
            String[] starr = str.split("\0");
            if (starr.length != deviceStatusOrder.getDeviceNum() * 2) {
                // 2：命令参数错误返回
                returnData = int2Bytes(2);
                sendBytes = setAtxPacData(3, 4, returnData);
            } else {
                List<Device> deviceList = new ArrayList<>();
                for (int i = 0; i < deviceStatusOrder.getDeviceNum(); i++) {
                    Device device = new Device();
                    device.setDeviceName(starr[i * 2]);
                    device.setDeviceVsgPort(starr[i * 2 + 1]);
                    deviceList.add(device);
                }
                deviceStatusOrder.setDeviceList(deviceList);
                System.out.println("0x03命令的数据信息" + deviceStatusOrder.toString());
                // TODO: 存数据库或者传到前台
                // TODO: 1：命令处理错误
                // 0：命令正确处理
                returnData = int2Bytes(0);
                sendBytes = setAtxPacData(3, 4, returnData);
            }
        } else {
            // 没有设备的时候
            // 0：命令正确处理
            returnData = int2Bytes(0);
            sendBytes = setAtxPacData(3, 4, returnData);
        }
        return sendBytes;
    }

    /**
     * 命令0x04 数据封装
     *
     * @param data 帧数据
     * @return
     */
    public byte[] setVsgStatusOrder(byte[] data) {
        byte[] sendBytes = new byte[65535];
        byte[] returnData = new byte[4];
        VsgStatusOrder vsgStatusOrder = new VsgStatusOrder();
        // vsg数量
        vsgStatusOrder.setVsgNum(data[0]);
        // device数量大于0
        if (vsgStatusOrder.getVsgNum() > 0) {
            String str = new String(data, 1, data.length);
            String[] starr = str.split("\0");
            if (starr.length != vsgStatusOrder.getVsgNum()) {
                // 2：命令参数错误返回
                returnData = int2Bytes(2);
                sendBytes = setAtxPacData(4, 4, returnData);
            } else {
                List<String> vsgInfoList = new ArrayList<>();
                for (String item : starr) {
                    vsgInfoList.add(item);
                }
                vsgStatusOrder.setVsgInfoList(vsgInfoList);
                System.out.println("0x04命令的数据信息" + vsgStatusOrder.toString());
                // TODO: 存数据库或者传到前台
                // TODO: 1：命令处理错误
                // 0：命令正确处理
                returnData = int2Bytes(0);
                sendBytes = setAtxPacData(4, 4, returnData);
            }
        } else {
            // 没有设备的时候
            // 0：命令正确处理
            returnData = int2Bytes(0);
            sendBytes = setAtxPacData(4, 4, returnData);
        }
        return sendBytes;
    }

    /**
     * 命令0x05 数据封装
     *
     * @param data 帧数据
     * @return
     */
    public byte[] setDeviceStatusChangeOrder(byte[] data) {
        byte[] sendBytes = new byte[65535];
        byte[] returnData = new byte[4];
        String str = new String(data, 0, data.length);
        String[] starr = str.split("\0");
        if (starr.length != 2) {
            // 没有设备的时候
            // 2：命令参数错误
            returnData = int2Bytes(2);
            sendBytes = setAtxPacData(5, 4, returnData);
        } else {
            DeviceStatusChangeOrder deviceStatusChangeOrder = new DeviceStatusChangeOrder();
            deviceStatusChangeOrder.setDeviceName(starr[0]);
            deviceStatusChangeOrder.setVsgName(starr[1]);
            System.out.println("0x05命令的数据信息" + deviceStatusChangeOrder.toString());
            // TODO: 存数据库或者传到前台
            // TODO: 1：命令处理错误
            // 0：命令正确处理
            returnData = int2Bytes(0);
            sendBytes = setAtxPacData(4, 4, returnData);
        }
        return sendBytes;
    }

    /**
     * 命令0x06 数据封装
     *
     * @param data 帧数据
     * @return
     */
    public byte[] setCaseOrder(byte[] data) {
        byte[] sendBytes = new byte[65535];
        byte[] returnData = new byte[4];
        CaseOrder caseOrder = new CaseOrder();

        if (data.length < 2) {
            // 2：命令参数错误返回
            returnData = int2Bytes(2);
            sendBytes = setAtxPacData(3, 4, returnData);
            return sendBytes;
        }

        caseOrder.setCaseNumber((short) ((data[1] << 8) | (data[0] & 0xff)));
        // 按照\0分割
        String str = new String(data, 2, data.length);
        String[] starr = str.split("\0");

        if (starr.length != caseOrder.getCaseNumber()) {
            // 没有设备的时候
            // 2：命令参数错误
            returnData = int2Bytes(2);
            sendBytes = setAtxPacData(5, 4, returnData);
        } else {
            List<String> casePath = new ArrayList<>();
            for (String item : starr) {
                casePath.add(item);
            }
            caseOrder.setCasePath(casePath);
            System.out.println("0x06命令的数据信息" + caseOrder.toString());
            // TODO: 存数据库或者传到前台
            // TODO: 1：命令处理错误
            // 0：命令正确处理
            returnData = int2Bytes(0);
            sendBytes = setAtxPacData(4, 4, returnData);
        }
        return sendBytes;
    }

    /**
     * 命令0x07 数据封装
     *
     * @param data 帧数据
     * @return
     */
    public byte[] setCaseCleanOrder(byte[] data) {
        byte[] sendBytes = new byte[65535];
        byte[] returnData = new byte[4];
        CaseCleanOrder caseCleanOrder = new CaseCleanOrder();
        if (data.length < 2) {
            // 2：命令参数错误返回
            returnData = int2Bytes(2);
            sendBytes = setAtxPacData(3, 4, returnData);
            return sendBytes;
        }

        caseCleanOrder.setCaseNumber((short) ((data[1] << 8) | (data[0] & 0xff)));
        // 按照\0分割
        String str = new String(data, 2, data.length);
        String[] starr = str.split("\0");

        if (starr.length != caseCleanOrder.getCaseNumber()) {
            // 没有设备的时候
            // 2：命令参数错误
            returnData = int2Bytes(2);
            sendBytes = setAtxPacData(5, 4, returnData);
        } else {
            List<String> casePath = new ArrayList<>();
            for (String item : starr) {
                casePath.add(item);
            }
            caseCleanOrder.setCasePath(casePath);
            System.out.println("0x07命令的数据信息" + caseCleanOrder.toString());
            // TODO: 存数据库或者传到前台
            // TODO: 1：命令处理错误
            // 0：命令正确处理
            returnData = int2Bytes(0);
            sendBytes = setAtxPacData(4, 4, returnData);
        }
        return sendBytes;
    }

    /**
     * 命令0x08 数据封装
     *
     * @param data 帧数据
     * @return
     */
    public byte[] setOperationOrder(byte[] data) {
        byte[] sendBytes = new byte[65535];
        byte[] returnData = new byte[4];

        if (data.length < 2) {
            // 2：命令参数错误返回
            returnData = int2Bytes(2);
            sendBytes = setAtxPacData(3, 4, returnData);
            return sendBytes;
        }

        // 0:停止 1:开始执行 2:暂停
        short operation = (short) ((data[1] << 8) | (data[0] & 0xff));
        System.out.println("0x08命令的数据信息:(0:停止 1:开始执行 2:暂停)" + operation);

        // TODO: 存数据库或者传到前台
        // TODO: 1：命令处理错误
        // 0：命令正确处理
        returnData = int2Bytes(0);
        sendBytes = setAtxPacData(4, 4, returnData);

        return sendBytes;
    }

    public static String getLocalMacByIp(String ip) throws SocketException, IOException {
        NetworkInterface ne = NetworkInterface.getByInetAddress(InetAddress.getByName(ip));
        byte[] mac = ne.getHardwareAddress();
        String mac_s = hexByte(mac[0]) + "-" +
                hexByte(mac[1]) + "-" +
                hexByte(mac[2]) + "-" +
                hexByte(mac[3]) + "-" +
                hexByte(mac[4]) + "-" +
                hexByte(mac[5]);
        return mac_s;
    }

    private static String hexByte(byte b) {
        String s = "000000" + Integer.toHexString(b);
        return s.substring(s.length() - 2);
    }

}
