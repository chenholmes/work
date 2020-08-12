package com.ruoyi.project.server.protocol;

import com.ruoyi.project.server.protocol.deserialize.impl.*;
import com.ruoyi.project.server.protocol.deserialize.Deserializer;
import com.ruoyi.project.server.protocol.serialize.Serializer;
import com.ruoyi.project.server.protocol.serialize.impl.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.ruoyi.project.server.protocol.command.Command.*;

/**
 * @author chenli.fnst
 * @date 2020/8/5 17:40
 */
public class PacketCodeC {
    private static final Logger LOGGER = LoggerFactory.getLogger(PacketCodeC.class);
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private static byte STX = 0x02;
    private static byte ETX = 0x03;
    private static final int RETURN_LEN = 13;
    /**
     *  返回的整个帧长度
     */
    private static final int RETURN_DATA_LEN = 19;
    /**
     * 返回数据需要计算checksum的数组长度
     */
    private static final int RETURN_CHECKSUM_LEN = 16;

    private final Map<Byte, Serializer> serializerMap;
    private final Map<Byte, Deserializer> deserializerMap;
    public static AtomicInteger count = new AtomicInteger(0);

    private PacketCodeC() {
        deserializerMap = new HashMap<>();
        deserializerMap.put(CONNECT_COMMAND, ConnnectDeserializer.INSTANCE);
        deserializerMap.put(NODE_STATUS_COMMAND, NodeStatusDeserializer.INSTANCE);
        deserializerMap.put(DEVICE_STATUS_COMMAND, DeviceStatusDeserializer.INSTANCE);
        deserializerMap.put(VSG_STATUS_COMMAND, VsgStatusDeserializer.INSTANCE);
        deserializerMap.put(DEVICE_STATUS_CHANGE_COMMAND, ReturnDataDeserializer.INSTANCE);
        deserializerMap.put(CASE_SEND_COMMAND,ReturnDataDeserializer.INSTANCE);
        deserializerMap.put(CASE_CLEAR_COMMAND,ReturnDataDeserializer.INSTANCE);
        deserializerMap.put(EXECUTE_COMMAND,ReturnDataDeserializer.INSTANCE);
        serializerMap = new HashMap<>();
        serializerMap.put(CONNECT_COMMAND, ReturnDataSerializer.INSTANCE);
        serializerMap.put(NODE_STATUS_COMMAND, ReturnDataSerializer.INSTANCE);
        serializerMap.put(DEVICE_STATUS_COMMAND,ReturnDataSerializer.INSTANCE);
        serializerMap.put(VSG_STATUS_COMMAND,ReturnDataSerializer.INSTANCE);
        serializerMap.put(DEVICE_STATUS_CHANGE_COMMAND, DeviceStatusChangeSerializer.INSTANCE);
        serializerMap.put(CASE_SEND_COMMAND, CaseSendSerializer.INSTANCE);
        serializerMap.put(CASE_CLEAR_COMMAND, CaseClearSerializer.INSTANCE);
        serializerMap.put(EXECUTE_COMMAND, ExecuteCommandSerializer.INSTANCE);
    }

    public ByteBuf encode(ByteBuf byteBuf,Packet packet) {
        byte[] returnData = new byte[RETURN_DATA_LEN];
        int cnt = count.getAndIncrement();
        int sum = 0;
        returnData[0] = STX;
        returnData[1] = (byte) RETURN_LEN;
        returnData[2] = (byte) (RETURN_LEN >> 8);
        returnData[3] = (byte) (0 - RETURN_LEN);
        returnData[4] = (byte) ((0 - RETURN_LEN) >> 8);
        returnData[5] = (byte) cnt;
        returnData[6] = (byte) (cnt >> 8);
        returnData[11] = packet.getCommand();
        returnData[12] = (byte) (packet.getCommand() >> 8 & 0xff);
        Serializer serializer = serializerMap.get(packet.getCommand());
        byte[] content = serializer.serialize(packet);
        System.arraycopy(content, 0, returnData, 13, content.length);
        for (int i = 1; i < returnData.length; i++) {
            sum += returnData[i];
        }
        returnData[content.length + 12] = (byte) sum;
        returnData[content.length + 13] = ETX;
        System.out.println(Arrays.toString(returnData));
        byteBuf.writeBytes(returnData);
        return byteBuf;
    }

    public Packet decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        //检查协议头
        byte start = byteBuf.readByte();
        if(STX != start) {
            //起始表示不对
            channelHandlerContext.channel().close();
            return null;
        }
        //数据长度,从序号开始,到校验和为止
        byte[] lenL = new byte[1];
        byteBuf.readBytes(lenL);
        byte[] lenH = new byte[1];
        byteBuf.readBytes(lenH);
        int length = lenL[0] & 0xff | lenH[0] << 8;
        //数据长度补数
        byte[] lenC = new byte[2];
        byteBuf.readBytes(lenC);
        //序号
        byte[] order = new byte[2];
        byteBuf.readBytes(order);
        //保留
        byte[] reserve = new byte[4];
        byteBuf.readBytes(reserve);
        //命令ID
        byte[] commandL = new byte[1];
        byteBuf.readBytes(commandL);
        byte[] commandH = new byte[1];
        byteBuf.readBytes(commandH);
        byte command = (byte) (commandL[0] & 0xff | commandH[0] << 8);
        //数据部长度
        int dataLen = length - 9;
        //数据部
        byte[] content = new byte[dataLen];
        byteBuf.readBytes(content);
        //校验和
        byte checksum = byteBuf.readByte();
        byte actChecksum = countChecksum(lenL,lenH,lenC,order,reserve,commandL,commandH,content);
        if(checksum != actChecksum) {
            LOGGER.error("checksum error!");
            //TODO throw exception
        }

        //解析数据部
        Deserializer deserializer = getDeserializer(command);
        if(deserializer != null) {
            return deserializer.deserialize(command,content);
        }
        return null;
    }

    public Deserializer getDeserializer(byte command) {
        return deserializerMap.get(command);
    }

    private static byte countChecksum(byte[]... values) {
        int length_byte = 0;
        byte sum = 0;
        for (int i = 0; i < values.length; i++) {
            length_byte += values[i].length;
        }
        byte[] allByte = new byte[length_byte];
        int countLength = 0;
        for (int i = 0; i < values.length; i++) {
            byte[] b = values[i];
            System.arraycopy(b, 0, allByte, countLength, b.length);
            countLength += b.length;
        }
        for(int i = 0; i < allByte.length; i++) {
            sum += allByte[i];
        }
        return sum;
    }


}
