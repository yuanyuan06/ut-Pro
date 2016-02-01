package com.utPro;

import java.util.List;

public interface TaskConfigManager {

    /**
     * 移除定时任务
     * 
     * @param cfg
     */
    void revokeTask(TaskConfig cfg);
    /**
     * 添加定时任务
     * 
     * @param cfg
     */
    void addTask(TaskConfig cfg);
    
    /**
     * 查询�?有可用任�?
     * @return
     */
    List<TaskConfig> loadAllAvailableTaskConfig();
    
    /**
     * 根据主键查找任务
     * @author hailiang.jiang
     * @date 2015�?7�?28�? 下午8:43:44
     * @param pk
     * @return
     */
    TaskConfig getByPrimaryKey(Long pk);
}
