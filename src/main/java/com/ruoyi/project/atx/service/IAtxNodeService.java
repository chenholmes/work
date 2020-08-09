package com.ruoyi.project.atx.service;

import java.util.List;
import com.ruoyi.project.atx.domain.AtxDevice;
import com.ruoyi.project.atx.domain.AtxNode;

/**
 *  服务层
 * 
 * @author wangst.fnst
 */
public interface IAtxNodeService {
	/**
	 * 查询台架列表
	 * 
	 * @return 台架列表
	 */
	public List<AtxNode> selectNodeList();

	/**
	 * 根據台架ID查询關聯設備列表
	 * 
	 * @param nodeId 台架ID
	 * @return 設備列表
	 */
	public List<AtxDevice> selectDeviceList(Integer nodeId);

}
