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
 * 用户与角色关系表
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-08
 */
@TableName("sys_user_role")
public class SysUserRole extends BaseEntity<SysUserRole> {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private String userId;
    /**
     * 角色ID
     */
	@TableField("role_id")
	private String roleId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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
		return "SysUserRole{" +
			", id=" + id +
			", userId=" + userId +
			", roleId=" + roleId +
			", createUserId=" + createUserId +
			", createTime=" + createTime +
			", updateUserId=" + updateUserId +
			", updateTime=" + updateTime +
			"}";
	}
}
