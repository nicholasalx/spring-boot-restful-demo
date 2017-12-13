package com.rjhy.cloud.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.rjhy.cloud.user.entity.SysUser;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-09
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    SysUser findByUserName(String username);
}
