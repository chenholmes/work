package com.ruoyi.common.model;

import java.util.Arrays;

/**
 * 自定义数据帧
 * 
 * @author wangsr.fnst
 *
 */
public class FrameATX {
	/**
	 * STX： STX起始标识，固定为0x02。
	 */
	Byte stxByte;
	/**
	 * 数据长度：从序号开始，到校验和为止，数据的长度。
	 */
	short dataLenShort;
	/**
	 * 数据长度补数: 用来检查数据长度是否正常的（数据长度 + 数据长度补数 = 0）
	 */
	short dataLenCShort;
	/**
	 * 序号: 通信帧的序号。依次累加，防止重复帧。
	 */
	short numberShort;
	/**
	 * 保留：[7][8][9][10]为保留字
	 */
	byte[] reserveByte = new byte[4];
	/**
	 * 命令ID: 区分通信帧的所要做的操作
	 */
	short orderShort;
	/**
	 * 数据部
	 */
	byte[] dataByte;
	/**
	 * 校验和：从数据长度开始，到数据部，逐个字节累加之后的值。
	 */
	Byte checkSum;
	/**
	 * ETX： ETX结束标志，固定位0x03
	 */
	Byte etxByte;
	/**
	 * @return the stxByte
	 */
	public Byte getStxByte() {
		return stxByte;
	}
	/**
	 * @param stxByte the stxByte to set
	 */
	public void setStxByte(Byte stxByte) {
		this.stxByte = stxByte;
	}
	/**
	 * @return the dataLenShort
	 */
	public short getDataLenShort() {
		return dataLenShort;
	}
	/**
	 * @param dataLenShort the dataLenShort to set
	 */
	public void setDataLenShort(short dataLenShort) {
		this.dataLenShort = dataLenShort;
	}
	/**
	 * @return the dataLenCShort
	 */
	public short getDataLenCShort() {
		return dataLenCShort;
	}
	/**
	 * @param dataLenCShort the dataLenCShort to set
	 */
	public void setDataLenCShort(short dataLenCShort) {
		this.dataLenCShort = dataLenCShort;
	}
	/**
	 * @return the numberShort
	 */
	public short getNumberShort() {
		return numberShort;
	}
	/**
	 * @param numberShort the numberShort to set
	 */
	public void setNumberShort(short numberShort) {
		this.numberShort = numberShort;
	}
	/**
	 * @return the reserveByte
	 */
	public byte[] getReserveByte() {
		return reserveByte;
	}
	/**
	 * @param reserveByte the reserveByte to set
	 */
	public void setReserveByte(byte[] reserveByte) {
		this.reserveByte = reserveByte;
	}
	/**
	 * @return the orderShort
	 */
	public short getOrderShort() {
		return orderShort;
	}
	/**
	 * @param orderShort the orderShort to set
	 */
	public void setOrderShort(short orderShort) {
		this.orderShort = orderShort;
	}
	/**
	 * @return the dataByte
	 */
	public byte[] getDataByte() {
		return dataByte;
	}
	/**
	 * @param dataByte the dataByte to set
	 */
	public void setDataByte(byte[] dataByte) {
		this.dataByte = dataByte;
	}
	/**
	 * @return the checkSum
	 */
	public Byte getCheckSum() {
		return checkSum;
	}
	/**
	 * @param checkSum the checkSum to set
	 */
	public void setCheckSum(Byte checkSum) {
		this.checkSum = checkSum;
	}
	/**
	 * @return the etxByte
	 */
	public Byte getEtxByte() {
		return etxByte;
	}
	/**
	 * @param etxByte the etxByte to set
	 */
	public void setEtxByte(Byte etxByte) {
		this.etxByte = etxByte;
	}
	
	@Override
	public String toString() {
		return "FrameATX [stxByte=" + stxByte + ", dataLenShort=" + dataLenShort + ", dataLenCShort=" + dataLenCShort
				+ ", numberShort=" + numberShort + ", reserveByte=" + Arrays.toString(reserveByte) + ", orderShort="
				+ orderShort + ", dataByte=" + Arrays.toString(dataByte) + ", checkSum=" + checkSum + ", etxByte="
				+ etxByte + "]";
	}
	
	
}
