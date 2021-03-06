package com.ruoyi.project.atx.mapper;

import java.util.List;

import com.ruoyi.project.atx.domain.AtxDevice;
import com.ruoyi.project.atx.domain.AtxNode;


/**
 * 台架和設備表 数据层
 * 
 * @author wangst.fnst
 */

public interface AtxNodeMapper {
	/**
	 * 查詢台架列表
	 * 
	 * @return 台架列表
	 */
	public List<AtxNode> selectNodeList();



	/**
	 * 通过台架ID查詢關聯設備列表
	 * 
	 * @param nodeId 台架ID
	 * @return 設備列表
	 */
	public List<AtxDevice> selectDeviceList(Integer nodeId);

	public void  insert(AtxNode atxNode);

}
