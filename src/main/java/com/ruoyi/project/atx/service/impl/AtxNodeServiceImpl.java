package com.ruoyi.project.atx.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.atx.domain.AtxDevice;
import com.ruoyi.project.atx.domain.AtxNode;
import com.ruoyi.project.atx.mapper.AtxNodeMapper;
import com.ruoyi.project.atx.service.IAtxNodeService;

/**
 * 設備信息 服务层实现
 * 
 * @author wangst.fnst
 */
@Service
public class AtxNodeServiceImpl implements IAtxNodeService {
	@Autowired
	private AtxNodeMapper nodeMapper;

	/**
	 * 查询台架列表
	 * 
	 * @param configId 参数配置ID
	 * @return 参数配置信息
	 */
	@Override
	public List<AtxNode> selectNodeList() {
		return nodeMapper.selectNodeList();
	}

	/**
	 * 根據台架ID查询關聯設備列表
	 * 
	 * @param nodeId 台架ID
	 * @return 設備列表
	 */
	@Override
	public List<AtxDevice> selectDeviceList(Integer nodeId) {
		return nodeMapper.selectDeviceList(nodeId);
	}
}
