package com.ruoyi.project.atx.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 atx_task
 *
 * @author ruoyi
 * @date 2020-08-05
 */
public class AtxTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增id */
    private Long id;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 应用的策略ID */
    @Excel(name = "应用的策略ID")
    private Long policyId;

    /** 任务开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskStarttime;

    /** 任务结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskEndtime;

    /** $column.columnComment */
    @Excel(name = "任务结束时间")
    private Integer progress;

    /** $column.columnComment */
    @Excel(name = "任务结束时间")
    private Integer nodeId;

    /** $column.columnComment */
    @Excel(name = "任务结束时间")
    private String devices;

    /** 当前是否正在执行 0:初始状态 1:执行 2:暂定 */
    @Excel(name = "当前是否正在执行 0:初始状态 1:执行 2:暂定")
    private Integer status;

    /** $column.columnComment */
    @Excel(name = "当前是否正在执行 0:初始状态 1:执行 2:暂定")
    private Integer autoFlg;

    /** $column.columnComment */
    @Excel(name = "当前是否正在执行 0:初始状态 1:执行 2:暂定")
    private Integer currentCaseNum;

    /** $column.columnComment */
    @Excel(name = "当前是否正在执行 0:初始状态 1:执行 2:暂定")
    private Integer totalCaseNum;

    /** 测试结果 0：ng 1：ok */
    @Excel(name = "测试结果 0：ng 1：ok")
    private Integer testResult;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    public String getTaskName()
    {
        return taskName;
    }
    public void setPolicyId(Long policyId)
    {
        this.policyId = policyId;
    }

    public Long getPolicyId()
    {
        return policyId;
    }
    public void setTaskStarttime(Date taskStarttime)
    {
        this.taskStarttime = taskStarttime;
    }

    public Date getTaskStarttime()
    {
        return taskStarttime;
    }
    public void setTaskEndtime(Date taskEndtime)
    {
        this.taskEndtime = taskEndtime;
    }

    public Date getTaskEndtime()
    {
        return taskEndtime;
    }
    public void setProgress(Integer progress)
    {
        this.progress = progress;
    }

    public Integer getProgress()
    {
        return progress;
    }
    public void setNodeId(Integer nodeId)
    {
        this.nodeId = nodeId;
    }

    public Integer getNodeId()
    {
        return nodeId;
    }
    public void setDevices(String devices)
    {
        this.devices = devices;
    }

    public String getDevices()
    {
        return devices;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setAutoFlg(Integer autoFlg)
    {
        this.autoFlg = autoFlg;
    }

    public Integer getAutoFlg()
    {
        return autoFlg;
    }
    public void setCurrentCaseNum(Integer currentCaseNum)
    {
        this.currentCaseNum = currentCaseNum;
    }

    public Integer getCurrentCaseNum()
    {
        return currentCaseNum;
    }
    public void setTotalCaseNum(Integer totalCaseNum)
    {
        this.totalCaseNum = totalCaseNum;
    }

    public Integer getTotalCaseNum()
    {
        return totalCaseNum;
    }
    public void setTestResult(Integer testResult)
    {
        this.testResult = testResult;
    }

    public Integer getTestResult()
    {
        return testResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("taskName", getTaskName())
                .append("policyId", getPolicyId())
                .append("taskStarttime", getTaskStarttime())
                .append("taskEndtime", getTaskEndtime())
                .append("progress", getProgress())
                .append("nodeId", getNodeId())
                .append("devices", getDevices())
                .append("status", getStatus())
                .append("autoFlg", getAutoFlg())
                .append("createTime", getCreateTime())
                .append("currentCaseNum", getCurrentCaseNum())
                .append("totalCaseNum", getTotalCaseNum())
                .append("testResult", getTestResult())
                .toString();
    }
}