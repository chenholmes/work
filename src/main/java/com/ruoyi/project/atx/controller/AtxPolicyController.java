package com.ruoyi.project.atx.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.atx.domain.AtxPolicy;
import com.ruoyi.project.atx.service.IAtxPolicyService;

/**
 * 設備信息
 * 
 * @author wangst.fnst
 */
@RestController
@RequestMapping("/atx/policy")
public class AtxPolicyController extends BaseController {

	@Autowired
	private IAtxPolicyService atxPolicyService;

	/**
	 * 查询【请填写功能名称】列表
	 */
	@PreAuthorize("@ss.hasPermi('system:policy:list')")
	@GetMapping("/list")
	public TableDataInfo list()
	{
		startPage();
		List<AtxPolicy> list = atxPolicyService.selectAtxPolicyList();
		return getDataTable(list);
	}

	/**
	 * 导出【请填写功能名称】列表
	 */
	@PreAuthorize("@ss.hasPermi('system:policy:export')")
	@Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(AtxPolicy atxPolicy)
	{
		List<AtxPolicy> list = atxPolicyService.selectAtxPolicyList();
		ExcelUtil<AtxPolicy> util = new ExcelUtil<AtxPolicy>(AtxPolicy.class);
		return util.exportExcel(list, "policy");
	}

	/**
	 * 获取【请填写功能名称】详细信息
	 */
	@PreAuthorize("@ss.hasPermi('system:policy:query')")
	@GetMapping(value = "/{id}")
	public AjaxResult getInfo(@PathVariable("id") Long id)
	{
		return AjaxResult.success(atxPolicyService.selectAtxPolicyById(id));
	}

	/**
	 * 新增【请填写功能名称】
	 */
	@PreAuthorize("@ss.hasPermi('system:policy:add')")
	@Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody AtxPolicy atxPolicy)
	{
		return toAjax(atxPolicyService.insertAtxPolicy(atxPolicy));
	}

	/**
	 * 修改【请填写功能名称】
	 */
	@PreAuthorize("@ss.hasPermi('system:policy:edit')")
	@Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody AtxPolicy atxPolicy)
	{
		return toAjax(atxPolicyService.updateAtxPolicy(atxPolicy));
	}

	/**
	 * 删除【请填写功能名称】
	 */
	@PreAuthorize("@ss.hasPermi('system:policy:remove')")
	@Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids)
	{
		return toAjax(atxPolicyService.deleteAtxPolicyByIds(ids));
	}

	/**
	 * 遍历case
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@PreAuthorize("@ss.hasRole('atxuser')")
	@GetMapping(value = "/cases")
	public AjaxResult getCases() throws FileNotFoundException, IOException {
		return AjaxResult.success(atxPolicyService.getCaseList("E:/share/atx"));
	}

}
