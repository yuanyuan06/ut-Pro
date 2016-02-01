package com.utPro;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SchedulerInitTask {

	protected static final Logger log = LoggerFactory.getLogger(SchedulerInitTask.class);
	
	@Resource
    private ApplicationContext applicationContext;
	@Autowired
	private TaskConfigManager taskConfigManager;
	
	private SchedulerQuartzManager schedulerQuartzManager;
	
	@PostConstruct
    private void init() {
        try {
        	schedulerQuartzManager = applicationContext.getBean(SchedulerQuartzManager.class);
        } catch (Exception e) {
            Log.error("error : schedulerFactoryBean not found");
        }
    }
	
	public void initJobTrigger() {
        log.info("===================initJobTrigger start===================================");
        //调用service 获取db job config
        List<TaskConfig> tasks = taskConfigManager.loadAllAvailableTaskConfig();
        if(tasks!=null && tasks.size()>0){
            log.info("=============tasks.size============="+tasks.size());
            List<String> successLogList = new ArrayList<String>();
            List<String> failedLogList = new ArrayList<String>();
            for (TaskConfig cfg : tasks) {
                try {
                	schedulerQuartzManager.addTask(cfg);
                	successLogList.add(constructPrintResult(cfg));
                } catch (Exception e) {
                    e.printStackTrace();
                    failedLogList.add(constructPrintResult(cfg));
                }
            }
            log.info("=============job added to Scheduler success count: [{}]=============", Arrays.asList(successLogList.size()));
            printStrList(successLogList);
            log.error("=============job added to Scheduler failed count: [{}]=============", Arrays.asList(failedLogList.size()));
            printStrList(failedLogList);
        } else {
        	log.info("=============tasks.size:0=============");
        }
        
        schedulerQuartzManager.startTask();
        log.info("===================initJobTrigger end===================================");
    }
	
	
	
	private String constructPrintResult(TaskConfig cfg) {
		StringBuffer strBuffer = new StringBuffer("");
		String triggerCode = cfg.getTriggerCode();
		String describe = cfg.getDescribe();
		String className = cfg.getClassName();
		String cronExpression = cfg.getCronExpression();
		String arguments = cfg.getArguments();
		strBuffer.append("-----")
		       .append("triggerCode:")
		       .append(triggerCode==null ? "null" : triggerCode)
		       .append("--")
		       .append("describe:")
		       .append(describe==null ? "null" : describe)
		       .append("--")
		       .append("className:")
		       .append(className==null ? "null" : className)
		       .append("--")
		       .append("cronExpression:")
		       .append(cronExpression==null ? "null" : cronExpression)
		       .append("--")
		       .append("arguments:")
		       .append(arguments==null ? "null" : arguments)
		       .append("-----");
		return strBuffer.toString();
	}
	
	private void printStrList(List<String> pringList) {
		for(String str : pringList) {
			log.info(str);
		}
	}
	
}
