package com.rjhy.cloud.common.quartz;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.rjhy.cloud.user.service.SysScheduleJobService;

@Configuration // 必须？
@EnableScheduling // 必须？
@Component
public class ScheduleRefreshDatabase {

	@Autowired
	SysScheduleJobService sysScheduleJobService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);
	/**
	 * 自动初始化定时任务方法
	 */
	/*
	 * @Scheduled(fixedRate = 5000) // 每隔5s查库，并根据查询结果决定是否重新设置定时任务
	 * private void initScheduleJob() { LOGGER.info("定时任务初始化开始...");
	 * 
	 * sysScheduleJobService.initScheduleJob();
	 * 
	 * LOGGER.info("定时任务初始化结束..."); 
	 * 
	 * }
	 */

	/**
	 * 项目启动时初始化
	 */
	@PostConstruct  //随spring 容器启动，这个方法将被执行一次
	private void init() {
		LOGGER.info("定时任务初始化开始...");

		sysScheduleJobService.initScheduleJob();

		LOGGER.info("定时任务初始化结束...");
	}
}
