package com.ruoyi.project.atx.service;

import com.ruoyi.project.atx.domain.AtxMainTask;
import com.ruoyi.project.atx.domain.AtxTask;

import java.util.List;

/**
 * 任务 业务层
 *
 * @author wangst.fnst
 */
public interface IAtxTaskService {
    /**
     * 获取首页数据
     * @return
     */

    public List<AtxMainTask> selectMainTaskList();


    /**
     *
     * @return
     */
    public List<AtxTask> selectAtxTaskList();

    /**
     * 根据任务ID查询信息
     *
     * @param taskId 任务ID
     * @return 任务信息
     */
    public AtxTask selectTaskById(Long taskId);

    /**
     * 新增保存任务信息
     *
     * @param task 任务信息
     * @return 结果
     */
    public int insertTask(AtxTask task);

    /**
     * 修改保存任务信息
     *
     * @param task 任务信息
     * @return 结果
     */
    public int updateTask(AtxTask task);

    /**
     * 删除任务管理信息
     *
     * @param taskId 任务ID
     * @return 结果
     */
    public int deleteTaskById(Long taskId);
}
