package com.ruoyi.project.atx.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.atx.domain.AtxDevice;
import com.ruoyi.project.atx.domain.AtxNode;
import com.ruoyi.project.atx.service.IAtxNodeService;
import com.ruoyi.project.system.domain.SysRole;
import com.ruoyi.project.system.domain.SysUser;

/**
 * 設備信息
 * 
 * @author wangst.fnst
 */
@RestController
@RequestMapping("/atx/node")
public class AtxNodeController extends BaseController {

	@Autowired
	private IAtxNodeService nodeService;

	/**
	 * 获取台架列表
	 */
	@PreAuthorize("@ss.hasRole('atxuser')")
	@GetMapping("/list")
	public AjaxResult list(AtxNode node) {

		List<AtxNode> nodes = nodeService.selectNodeList();
		return AjaxResult.success(nodes);
	}

	/**
	 * 根据台架ID获取關聯設備列表
	 */
	@PreAuthorize("@ss.hasRole('atxuser')")
	@GetMapping(value = "/{nodeId}")
	public AjaxResult getInfo(@PathVariable Integer nodeId) {
		return AjaxResult.success(nodeService.selectDeviceList(nodeId));
	}

}
