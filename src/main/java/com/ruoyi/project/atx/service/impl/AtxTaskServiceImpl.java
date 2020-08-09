package com.ruoyi.project.atx.service.impl;

import com.ruoyi.project.atx.domain.AtxMainTask;
import com.ruoyi.project.atx.domain.AtxTask;
import com.ruoyi.project.atx.mapper.AtxTaskMapper;
import com.ruoyi.project.atx.service.IAtxTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 任务 业务层处理
 *
 * @author wangst.fnst
 */
@Service
public class AtxTaskServiceImpl implements IAtxTaskService {

    @Autowired
    private AtxTaskMapper taskMapper;


    @Override
    public List<AtxMainTask> selectMainTaskList() {
        return taskMapper.selectMainTaskList();
    }

    @Override
    public List<AtxTask> selectAtxTaskList() {
        return taskMapper.selectAtxTaskList();
    }

    /**
     * 根据任务ID查询信息
     *
     * @param taskId 任务ID
     * @return 任务信息
     */
    @Override
    public AtxTask selectTaskById(Long taskId) {
        return taskMapper.selectTaskById(taskId);
    }

    /**
     * 新增保存任务信息
     *
     * @param task 任务信息
     * @return 结果
     */
    @Override
    public int insertTask(AtxTask task) {
        return taskMapper.insertTask(task);
    }

    /**
     * 修改保存任务信息
     *
     * @param task 任务信息
     * @return 结果
     */
    @Override
    public int updateTask(AtxTask task) {
        return taskMapper.updateTask(task);
    }

    /**
     * 删除任务管理信息
     *
     * @param taskId 任务ID
     * @return 结果
     */
    @Override
    public int deleteTaskById(Long taskId) {
        return taskMapper.deleteTaskById(taskId);
    }
}
