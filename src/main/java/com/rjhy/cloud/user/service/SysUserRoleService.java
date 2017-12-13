package com.rjhy.cloud.user.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rjhy.cloud.user.dao.SysUserRoleMapper;
import com.rjhy.cloud.user.entity.SysUserRole;

/**
 * <p>
 * 用户与角色关系表 服务实现类
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-08
 */
@Service
public class SysUserRoleService extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
	
}
