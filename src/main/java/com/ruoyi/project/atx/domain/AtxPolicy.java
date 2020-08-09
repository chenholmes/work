package com.ruoyi.project.atx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 atx_policy
 *
 * @author ruoyi
 * @date 2020-08-04
 */
public class AtxPolicy extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** 策略ID */
	private Long id;

	/** 策略名称 */
	@Excel(name = "策略名称")
	private String policyName;

	/** $column.columnComment */
	@Excel(name = "策略名称")
	private String cases;

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}
	public void setPolicyName(String policyName)
	{
		this.policyName = policyName;
	}

	public String getPolicyName()
	{
		return policyName;
	}
	public void setCases(String cases)
	{
		this.cases = cases;
	}

	public String getCases()
	{
		return cases;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("policyName", getPolicyName())
				.append("cases", getCases())
				.append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime())
				.toString();
	}
}