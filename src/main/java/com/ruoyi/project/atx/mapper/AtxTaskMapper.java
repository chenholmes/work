package com.ruoyi.project.atx.mapper;

import com.ruoyi.project.atx.domain.AtxMainTask;
import com.ruoyi.project.atx.domain.AtxTask;

import java.util.List;

/**
 * 任务表 数据层
 *
 * @author wangst.fnst
 */
public interface AtxTaskMapper {

    /**
     * 首页获取数据
     * @return
     */
    public List<AtxMainTask> selectMainTaskList();


    /**
     * 查获取任务列表
     *
     * @return 任务列表
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
     * 新增任务信息
     *
     * @param task 任务信息
     * @return 结果
     */
    public int insertTask(AtxTask task);

    /**
     * 修改任务信息
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
