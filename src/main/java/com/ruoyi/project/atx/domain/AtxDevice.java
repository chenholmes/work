package com.ruoyi.project.atx.domain;



import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 atx_device
 *
 * @author ruoyi
 * @date 2020-08-04
 */
public class AtxDevice extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** 设置主键自增 */
	private Long id;

	/** 台架ID */
	@Excel(name = "台架ID")
	private Long nodeId;

	/** 当前是否是空闲状态 0:空闲 1:不空闲 */
	@Excel(name = "当前是否是空闲状态 0:空闲 1:不空闲")
	private Integer status;

	/** 设备名称 */
	@Excel(name = "设备名称")
	private String name;

	/** 连接的VSG key */
	@Excel(name = "连接的VSG key")
	private String vsgCom;

	/** 信息 */
	@Excel(name = "信息")
	private String comment;

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}
	public void setNodeId(Long nodeId)
	{
		this.nodeId = nodeId;
	}

	public Long getNodeId()
	{
		return nodeId;
	}
	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
	public void setVsgCom(String vsgCom)
	{
		this.vsgCom = vsgCom;
	}

	public String getVsgCom()
	{
		return vsgCom;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getComment()
	{
		return comment;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("nodeId", getNodeId())
				.append("status", getStatus())
				.append("name", getName())
				.append("vsgCom", getVsgCom())
				.append("comment", getComment())
				.append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime())
				.toString();
	}
}