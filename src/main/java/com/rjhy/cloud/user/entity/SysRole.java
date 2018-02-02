package com.rjhy.cloud.user.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.rjhy.cloud.common.BaseEntity;


/**
 * <p>
 * 角色
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-08
 */
@TableName("sys_role")
public class SysRole extends BaseEntity<SysRole> {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 角色名称
     */
	@TableField("role_name")
	private String roleName;
    /**
     * 备注
     */
	private String remark;
	@TableField("create_user_id")
	private String createUserId;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_user_id")
	private String updateUserId;
	@TableField("update_time")
	private Date updateTime;
	
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@TableField(exist=false)
    private Boolean checked;

    public SysRole() {
    }

    public SysRole(String roleName) {
        this.roleName = roleName;
    }
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		return "SysRole{" +
			", id=" + id +
			", roleName=" + roleName +
			", remark=" + remark +
			", createUserId=" + createUserId +
			", createTime=" + createTime +
			", updateUserId=" + updateUserId +
			", updateTime=" + updateTime +
			"}";
	}
}
