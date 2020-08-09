package com.ruoyi.project.server.protocol.command;

/**
 * @author chenli.fnst
 * @date 2020/8/5 11:10
 */
public interface Command {

    Byte CONNECT_COMMAND = 1;

    Byte NODE_STATUS_COMMAND = 2;

    Byte DEVICE_STATUS_COMMAND = 3;

    Byte VSG_STATUS_COMMAND = 4;

    Byte DEVICE_STATUS_CHANGE_COMMAND = 5;

    Byte CASE_SEND_COMMAND = 6;

    Byte CASE_CLEAR_COMMAND = 7;

    Byte EXECUTE_COMMAND = 8;
}
