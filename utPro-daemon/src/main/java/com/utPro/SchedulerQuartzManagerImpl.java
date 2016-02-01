package com.utPro;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.jfree.util.Log;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.StringUtils;

public class SchedulerQuartzManagerImpl implements SchedulerQuartzManager {
	
	protected static final Logger log = LoggerFactory.getLogger(SchedulerQuartzManagerImpl.class);
	
	@Resource
    private ApplicationContext applicationContext;
	
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@PostConstruct
    private void init() {
        try {
            schedulerFactoryBean = applicationContext.getBean(SchedulerFactoryBean.class);
        } catch (Exception e) {
            Log.error("error : schedulerFactoryBean not found");
        }
    }
	
	@Override
	public void addTask(TaskConfig cfg) {
		try {
        	String[] args = StringUtils.hasText(cfg.getArguments()) ? cfg.getArguments().split(TaskConfig.ARGUMENTS_SPLIT_CODE) : null;
        	Object instanceObj = applicationContext.getBean(Class.forName(cfg.getClassName()));
        	Boolean isConcurrent = cfg.getIsConcurrent() == null ? false : cfg.getIsConcurrent();
        	
        	addTask(cfg.getGroup(), instanceObj, cfg.getMethod(), args, cfg.getCronExpression(), isConcurrent, cfg.getJobCode(), cfg.getTriggerCode());
        } catch (BusinessException e) {
            throw e;
        } catch (BeansException e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.SYSTEM_ERROR);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.SYSTEM_ERROR);
		}
	};
	
	@Override
	public void addTask(String groupName, Object taskInstance, String taskMethod, Object[] args, String cronExpression, boolean isConcurrent, String jobName, String triggerCode) throws BusinessException {
		try {
			log.info("add task for jobName={}, groupName={}", jobName, groupName);
			MethodInvokingJobDetailFactoryBean mifb = constructionMethodInvokingJobDetailFactoryBean(groupName, taskInstance, taskMethod, args, isConcurrent, jobName);
			CronTriggerBean ctb = constructionCronTriggerBean(mifb.getObject(), triggerCode, groupName, jobName, cronExpression);
			schedulerFactoryBean.getScheduler().scheduleJob(mifb.getObject(), ctb);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.SYSTEM_ERROR);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.SYSTEM_ERROR);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.SYSTEM_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.SYSTEM_ERROR);
		}
	}
	
	
	@Override
	public void removeTask(String jobName, String groupName) throws BusinessException {
		try {
			log.info("remove task for jobName={}, groupName={}", jobName, groupName);
			schedulerFactoryBean.getScheduler().deleteJob(jobName, groupName);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.SYSTEM_ERROR);
		}
	}
	
	
	@Override
	public void startTask() {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		try {
			scheduler.unscheduleJob("initJobTrigger", Scheduler.DEFAULT_GROUP);
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.SYSTEM_ERROR);
		}
	}
	
	
	
	/**
	 * 构建MethodInvokingJobDetailFactoryBean
	 * @param groupName
	 * @param taskInstance
	 * @param taskMethod
	 * @param args
	 * @param isConcurrent
	 * @param jobName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 */
	private MethodInvokingJobDetailFactoryBean constructionMethodInvokingJobDetailFactoryBean(String groupName, Object taskInstance, String taskMethod, Object[] args, boolean isConcurrent, String jobName) throws ClassNotFoundException, NoSuchMethodException {
		//MethodInvokingJobDetailFactoryBean mifb = new MethodInvokingJobDetailFactoryBean();
		//AppsMethodInvokingJobDetailFactoryBean：fanht
		AppsMethodInvokingJobDetailFactoryBean mifb = new AppsMethodInvokingJobDetailFactoryBean();
		mifb.setTargetObject(taskInstance);
		mifb.setTargetMethod(taskMethod);
		mifb.setConcurrent(false);
		mifb.setName(jobName);
		mifb.setGroup(groupName);
		mifb.setArguments(args);
		mifb.afterPropertiesSet();
		return mifb;
	}
	
	
	
	/**
	 * 构建CronTriggerBean
	 * @param jobDetail
	 * @param triggerCode
	 * @param groupName
	 * @param jobName
	 * @param cronExpression
	 * @return
	 * @throws Exception
	 */
	private CronTriggerBean constructionCronTriggerBean(JobDetail jobDetail, String triggerCode, String groupName, String jobName, String cronExpression) throws Exception {
		CronTriggerBean ctb = new CronTriggerBean();
		ctb.setJobDetail(jobDetail);
		ctb.setName(triggerCode+"trigger");
		ctb.setGroup(groupName);
        ctb.setJobName(jobName);
        ctb.setCronExpression(cronExpression);
        ctb.afterPropertiesSet();
        return ctb;
	}


}
