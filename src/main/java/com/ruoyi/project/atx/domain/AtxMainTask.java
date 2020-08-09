package com.ruoyi.project.atx.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

public class AtxMainTask  extends BaseEntity{
    private Integer nodeId;

    private  String taskName;

    private  String policyName;

    private  String computerName;

    private Date taskStarttime;

    private  Date taskEndtime;

    private  Date  runTime;

    public String getPolicyName() {
        return policyName;
}

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    //当前执行的case数量
    private Integer currentCaseNum;


    //case总数量
    private Integer totalCaseNum;

    //case测试结果
    private Integer testResult;

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public Date getTaskStarttime() {
        return taskStarttime;
    }

    public void setTaskStarttime(Date taskStarttime) {
        this.taskStarttime = taskStarttime;
    }

    public Date getTaskEndtime() {
        return taskEndtime;
    }

    public void setTaskEndtime(Date taskEndtime) {
        this.taskEndtime = taskEndtime;
    }

    public Date getRuntTime() {
        return runTime;
    }

    public void setRuntTime(Date runtTime) {
        this.runTime = runtTime;
    }

    public Integer getCurrentCaseNum() {
        return currentCaseNum;
    }

    public void setCurrentCaseNum(Integer currentCaseNum) {
        this.currentCaseNum = currentCaseNum;
    }

    public Integer getTotalCaseNum() {
        return totalCaseNum;
    }

    public void setTotalCaseNum(Integer totalCaseNum) {
        this.totalCaseNum = totalCaseNum;
    }

    public Integer getTestResult() {
        return testResult;
    }

    public void setTestResult(Integer testResult) {
        this.testResult = testResult;
    }
}
