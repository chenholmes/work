package com.ruoyi.project.atx.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import com.ruoyi.project.atx.domain.AtxCase;
import com.ruoyi.project.atx.domain.AtxPolicy;

/**
 * 服务层
 * 
 * @author wangst.fnst
 */
public interface IAtxPolicyService {
	/**
	 * 查询【请填写功能名称】
	 *
	 * @param id 【请填写功能名称】ID
	 * @return 【请填写功能名称】
	 */
	public AtxPolicy selectAtxPolicyById(Long id);

	/**
	 * 查询【请填写功能名称】列表
	 *
	 *
	 * @return 【请填写功能名称】集合
	 */
	public List<AtxPolicy> selectAtxPolicyList();

	/**
	 * 新增【请填写功能名称】
	 *
	 * @param atxPolicy 【请填写功能名称】
	 * @return 结果
	 */
	public int insertAtxPolicy(AtxPolicy atxPolicy);

	/**
	 * 修改【请填写功能名称】
	 *
	 * @param atxPolicy 【请填写功能名称】
	 * @return 结果
	 */
	public int updateAtxPolicy(AtxPolicy atxPolicy);

	/**
	 * 批量删除【请填写功能名称】
	 *
	 * @param ids 需要删除的【请填写功能名称】ID
	 * @return 结果
	 */
	public int deleteAtxPolicyByIds(Long[] ids);

	/**
	 * 删除【请填写功能名称】信息
	 *
	 * @param id 【请填写功能名称】ID
	 * @return 结果
	 */
	public int deleteAtxPolicyById(Long id);

	/**
	 * 遍历case
	 * 
	 * @return case集合
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public List<AtxCase> getCaseList(String filepath) throws FileNotFoundException, IOException;

}
