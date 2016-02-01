package com.utPro;


/**
 * Scheduler管理
 * @author hailiang.jiang
 * @date 2015�?7�?28�? 下午5:15:19
 */
public interface SchedulerQuartzManager {
	
	/**
	 * 增加task
	 * @author hailiang.jiang
	 * @date 2015�?7�?28�? 下午8:36:49
	 * @param cfg
	 */
	public void addTask(TaskConfig cfg);
	
	/**
	 * 增加task
	 * @author hailiang.jiang
	 * @date 2015�?7�?28�? 下午5:15:29
	 * @param groupName
	 * @param taskInstance
	 * @param taskMethod
	 * @param args
	 * @param cronExpression
	 * @param isConcurrent
	 * @param jobName
	 * @param triggerCode
	 * @throws BusinessException
	 */
	public void addTask(String groupName, Object taskInstance, String taskMethod, Object[] args, String cronExpression, boolean isConcurrent, String jobName, String triggerCode) throws BusinessException;
	
	/**
	 * 移除task
	 * @author hailiang.jiang
	 * @date 2015�?7�?28�? 下午5:15:45
	 * @param jobName
	 * @param groupName
	 * @throws BusinessException
	 */
	public void removeTask(String jobName, String groupName) throws BusinessException;
	
	/**
	 * 启动任务
	 * @author hailiang.jiang
	 * @date 2015�?7�?28�? 下午6:38:36
	 */
	public void startTask();
	
}
