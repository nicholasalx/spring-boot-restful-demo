package com.rjhy.cloud.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-08
 */
@TableName("sys_user")
public class SysUser extends BaseEntity<SysUser> {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 用户名
     */
	private String username;
    /**
     * 密码
     */
	private String password;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 手机号
     */
	private String mobile;
	private String status;
	@TableField("create_user_id")
	private String createUserId;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_user_id")
	private String updateUserId;
	@TableField("update_time")
	private Date updateTime;

	@TableField(exist=false)
	private List<String> permissions;
	
	@TableField(exist=false)
	private List<String> rolelist;
	
	

	public List<String> getRolelist() {
		return rolelist;
	}


	public void setRolelist(List<String> rolelist) {
		this.rolelist = rolelist;
	}


	public List<String> getPermissions() {
		return permissions;
	}


	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	 
    public SysUser(String username) {
        this.username = username;
    }

    public SysUser() {
    }
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "SysUser{" +
			", id=" + id +
			", username=" + username +
			", password=" + password +
			", email=" + email +
			", mobile=" + mobile +
			", status=" + status +
			", createUserId=" + createUserId +
			", createTime=" + createTime +
			", updateUserId=" + updateUserId +
			", updateTime=" + updateTime +
			"}";
	}
}
