package com.ruoyi.project.atx.mapper;

import java.util.List;
import com.ruoyi.project.atx.domain.AtxPolicy;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2020-08-04
 */
public interface AtxPolicyMapper
{
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
	 * 删除【请填写功能名称】
	 *
	 * @param id 【请填写功能名称】ID
	 * @return 结果
	 */
	public int deleteAtxPolicyById(Long id);

	/**
	 * 批量删除【请填写功能名称】
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteAtxPolicyByIds(Long[] ids);
}