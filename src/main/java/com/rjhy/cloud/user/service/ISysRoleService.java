package com.rjhy.cloud.user.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.rjhy.cloud.user.entity.SysRole;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-09
 */
public interface ISysRoleService extends IService<SysRole> {
	/**
     * 根据用户id获取角色集合
     * @param userId
     * @return
     */
	List<String> getRoles(String userId);
}
