package com.ruoyi.project.atx.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.atx.domain.AtxMainTask;
import com.ruoyi.project.atx.domain.AtxTask;
import com.ruoyi.project.atx.service.IAtxTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 任务信息
 *
 * @author wangst.fnst
 */
@RestController
@RequestMapping("/atx/task")
public class AtxTaskController extends BaseController {
    @Autowired
    private IAtxTaskService taskService;

    /**
     * 获取任务列表
     */
    @PreAuthorize("@ss.hasRole('atxuser')")
    @GetMapping("/list")
    public AjaxResult list() {
        Date date=new Date();
        List<AtxMainTask> tasks = taskService.selectMainTaskList();
//        List<AtxMainTask> atxMainTasks =new ArrayList<>();
//        for(AtxMainTask atxMainTask:tasks){
//            atxMainTask.setRuntTime()
//        }
        return AjaxResult.success(tasks);
    }

    /**
     * 根据task id获取详细信息
     */
    @PreAuthorize("@ss.hasRole('atxuser')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable Long taskId) {
        return AjaxResult.success(taskService.selectTaskById(taskId));
    }

    /**
     * 新增任务
     */
    @PreAuthorize("@ss.hasRole('atxuser')")
    @Log(title = "任务管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AtxTask task) {

        return toAjax(taskService.insertTask(task));
    }

    /**
     * 修改任务
     */
    @PreAuthorize("@ss.hasRole('atxuser')")
    @Log(title = "任务管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody AtxTask task) {
        return toAjax(taskService.updateTask(task));
    }

    /**
     * 删除任务
     */
    @PreAuthorize("@ss.hasPermi('system:menu:remove')")
    @Log(title = "任务管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuId}")
    public AjaxResult remove(@PathVariable("taskId") Long taskId) {

        return toAjax(taskService.deleteTaskById(taskId));
    }
}