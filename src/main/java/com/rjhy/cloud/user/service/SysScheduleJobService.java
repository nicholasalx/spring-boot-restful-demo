package com.rjhy.cloud.user.service;

import java.util.List;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rjhy.cloud.common.quartz.ScheduleUtils;
import com.rjhy.cloud.user.dao.SysScheduleJobMapper;
import com.rjhy.cloud.user.entity.SysScheduleJob;

/**
 * <p>
 * 瀹氭椂浠诲姟 鏈嶅姟瀹炵幇绫�
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-11
 */
@Service
public class SysScheduleJobService extends ServiceImpl<SysScheduleJobMapper, SysScheduleJob> implements ISysScheduleJobService {
	@Autowired
	Scheduler scheduler;
	
	/**
	 * 鑷姩鍒濆鍖栧畾鏃朵换鍔℃柟娉�
	 */
	public void initScheduleJob() {

			EntityWrapper<SysScheduleJob> wrapper=new EntityWrapper<SysScheduleJob>();
			wrapper.eq("auto_start", "1");
			List<SysScheduleJob> list = selectList(wrapper);;
	        if(list != null && list.size() > 0){
	        	SysScheduleJob entity = new SysScheduleJob();
	        	entity.setStatus("0");
	        	update(entity, wrapper);
	        	for (SysScheduleJob scheduleJob : list) {
					try {
						ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
						scheduleJob.setStatus("1");
						updateById(scheduleJob);
					} catch (Exception e) {
						scheduleJob.setStatus("0");
						updateById(scheduleJob);
						ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getId());
					}
				}
	        }
		}
}
