package com.rjhy.cloud.user.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rjhy.cloud.user.dao.SysRoleMapper;
import com.rjhy.cloud.user.entity.SysRole;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-08
 */
@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService{
	
}
