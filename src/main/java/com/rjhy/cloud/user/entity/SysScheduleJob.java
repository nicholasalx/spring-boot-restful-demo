package com.rjhy.cloud.user.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.rjhy.cloud.common.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-11
 */
@TableName("sys_schedule_job")
public class SysScheduleJob extends BaseEntity<SysScheduleJob> {

    private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	@TableField("class_name")
	private String className;
	private String times;
	@TableField("auto_start")
	private String autoStart;
	private String status;
	@TableField("last_runtime")
	private Date lastRuntime;
	private String comments;
	@TableField("create_user_id")
	private String createUserId;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_user_id")
	private String updateUserId;
	@TableField("update_time")
	private Date updateTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getAutoStart() {
		return autoStart;
	}

	public void setAutoStart(String autoStart) {
		this.autoStart = autoStart;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastRuntime() {
		return lastRuntime;
	}

	public void setLastRuntime(Date lastRuntime) {
		this.lastRuntime = lastRuntime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysScheduleJob{" +
			", id=" + id +
			", name=" + name +
			", className=" + className +
			", times=" + times +
			", autoStart=" + autoStart +
			", status=" + status +
			", lastRuntime=" + lastRuntime +
			", comments=" + comments +
			", createUserId=" + createUserId +
			", createTime=" + createTime +
			", updateUserId=" + updateUserId +
			", updateTime=" + updateTime +
			"}";
	}
}
