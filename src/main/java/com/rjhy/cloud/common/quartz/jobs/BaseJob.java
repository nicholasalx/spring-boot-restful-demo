package com.rjhy.cloud.common.quartz.jobs;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.rjhy.cloud.common.utils.DateUtils;
import com.rjhy.cloud.user.entity.SysScheduleJob;
import com.rjhy.cloud.user.service.SysScheduleJobService;

/**
 * 同步任务工厂类 
 *
 */
@Component
public abstract class BaseJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(BaseJob.class);
    
    @Autowired
    SysScheduleJobService scheduleJobService;

    public void execute(JobExecutionContext context) throws JobExecutionException {
    	//解决bean无法注入问题
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        
        SysScheduleJob scheduleJob = (SysScheduleJob) context.getMergedJobDataMap().get("data");
        
        log.info("Job["+scheduleJob.getId()+"]"+scheduleJob.getName()+" start "+DateUtils.getDateTime());
        
        scheduleJob.setLastRuntime(new Date());
        try {
        	scheduleJobService.updateById(scheduleJob);
			this.run(scheduleJob);
			log.info("Job["+scheduleJob.getId()+"]"+scheduleJob.getName()+" end "+DateUtils.getDateTime());
		} catch (Exception e) {
			log.error("Job["+scheduleJob.getId()+"]"+scheduleJob.getName()+" error "+DateUtils.getDateTime());
		}
    }
    
    public abstract void run(SysScheduleJob scheduleJob) throws Exception;
    
    
}
