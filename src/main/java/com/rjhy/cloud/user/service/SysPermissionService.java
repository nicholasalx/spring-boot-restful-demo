package com.rjhy.cloud.user.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rjhy.cloud.common.utils.MenuTreeUtil;
import com.rjhy.cloud.user.dao.SysPermissionMapper;
import com.rjhy.cloud.user.entity.SysPermission;
import com.rjhy.cloud.vm.MenuVM;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-09
 */
@Service
public class SysPermissionService extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {
	
	@Override
    public Set<String> getPermissions(String userId) {

        List<String> menuList = baseMapper.getPermissions(userId);
        //用户权限列表
        Set<String> permsSet = new HashSet<String>();
        for(String perm : menuList){
            if(StringUtils.isBlank(perm)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perm.trim().split(",")));
        }
        return permsSet;
    }
	@Override
    public List<MenuVM> getMenu(String userId) {
        List<SysPermission> menuList = baseMapper.selectMenuByUserId(userId);
        return MenuTreeUtil.getMenu(menuList);
    }
}
