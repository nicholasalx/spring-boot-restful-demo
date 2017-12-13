package com.rjhy.cloud.common.quartz.jobs;

import java.util.Date;

import com.rjhy.cloud.user.entity.SysScheduleJob;
 
/**
 * 任务类.
 * @author Angel --守护天使
 * @version v.0.1
 * @date 2017年4月21日
 */
public class TestJob2 extends BaseJob{
 
	@Override
	public void run(SysScheduleJob scheduleJob) throws Exception {
        // 执行响应的任务.
       System.out.println("HelloJob2.execute,"+new Date());
    }
   
}