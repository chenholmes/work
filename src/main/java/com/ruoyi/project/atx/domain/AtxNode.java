package com.ruoyi.project.atx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 atx_node
 *
 * @author ruoyi
 * @date 2020-08-04
 */
public class AtxNode extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** 设置主键自增,台架ID */
	private Long id;

	/** IP地址 */
	@Excel(name = "IP地址")
	private String ip;

	/** MAC地址 */
	@Excel(name = "MAC地址")
	private String mac;

	/** 台架所在计算机名称 */
	@Excel(name = "台架所在计算机名称")
	private String computerName;

	/** $column.columnComment */
	@Excel(name = "台架所在计算机名称")
	private Long policyId;

	/** 状态 1: 连接  0：不连接 */
	@Excel(name = "状态 1: 连接  0：不连接")
	private Integer status;

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}
	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String getIp()
	{
		return ip;
	}
	public void setMac(String mac)
	{
		this.mac = mac;
	}

	public String getMac()
	{
		return mac;
	}
	public void setComputerName(String computerName)
	{
		this.computerName = computerName;
	}

	public String getComputerName()
	{
		return computerName;
	}
	public void setPolicyId(Long policyId)
	{
		this.policyId = policyId;
	}

	public Long getPolicyId()
	{
		return policyId;
	}
	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("ip", getIp())
				.append("mac", getMac())
				.append("computerName", getComputerName())
				.append("policyId", getPolicyId())
				.append("status", getStatus())
				.append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime())
				.toString();
	}
}