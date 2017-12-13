package com.rjhy.cloud.user.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rjhy.cloud.user.dao.SysRolePermissionMapper;
import com.rjhy.cloud.user.entity.SysRolePermission;

/**
 * <p>
 * 角色与菜单关系表 服务实现类
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-08
 */
@Service
public class SysRolePermissionService extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {
	
}
