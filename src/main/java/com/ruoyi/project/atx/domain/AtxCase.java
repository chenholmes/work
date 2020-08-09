package com.ruoyi.project.atx.domain;

import java.util.Arrays;
import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.ColumnType;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * case Model
 * 
 * @author wsting.fnst
 */
public class AtxCase extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** case ID */
	@Excel(name = "case ID")
	private String caseId;

	/** parent ID */
	@Excel(name = "parent ID")
	private String parentId;

	/** case名称 */
	@Excel(name = "case名称")
	private String caseName;

	/** parent集合 */
	@Excel(name = "parent集合")
	private String ancestors;

	/** 子目录或文件 */
	@Excel(name = "子目录或文件")
	private String[] children;

	/** 动图url */
	@Excel(name = "动图url")
	private String gifUrl;

	/** case执行时间 */
	@Excel(name = "case执行时间")
	private String caseTime;

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getAncestors() {
		return ancestors;
	}

	public void setAncestors(String ancestors) {
		this.ancestors = ancestors;
	}

	public String[] getChildren() {
		return children;
	}

	public void setChildren(String[] arr) {
		this.children = arr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getGifUrl() {
		return gifUrl;
	}

	public void setGifUrl(String gifUrl) {
		this.gifUrl = gifUrl;
	}

	public String getCaseTime() {
		return caseTime;
	}

	public void setCaseTime(String caseTime) {
		this.caseTime = caseTime;
	}

	@Override
	public String toString() {
		return "AtxCase [caseId=" + caseId + ", parentId=" + parentId + ", caseName=" + caseName + ", ancestors="
				+ ancestors + ", children=" + Arrays.toString(children) + ", gifUrl=" + gifUrl + ", caseTime="
				+ caseTime + ", getCaseId()=" + getCaseId() + ", getParentId()=" + getParentId() + ", getCaseName()="
				+ getCaseName() + ", getAncestors()=" + getAncestors() + ", getChildren()="
				+ Arrays.toString(getChildren()) + ", getGifUrl()=" + getGifUrl() + ", getCaseTime()=" + getCaseTime()
				+ ", getSearchValue()=" + getSearchValue() + ", getCreateBy()=" + getCreateBy() + ", getCreateTime()="
				+ getCreateTime() + ", getUpdateBy()=" + getUpdateBy() + ", getUpdateTime()=" + getUpdateTime()
				+ ", getRemark()=" + getRemark() + ", getBeginTime()=" + getBeginTime() + ", getEndTime()="
				+ getEndTime() + ", getParams()=" + getParams() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
