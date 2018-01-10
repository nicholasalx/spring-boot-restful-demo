package com.rjhy.cloud.user.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
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
	@Override
    public List<String> getRoles(String userId) {

        List<String> roleList = baseMapper.getRoles(userId);
        return roleList;
    }
}
