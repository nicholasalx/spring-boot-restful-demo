package com.rjhy.cloud.user.service;

import com.rjhy.cloud.user.entity.SysPermission;
import com.rjhy.cloud.vm.MenuVM;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-09
 */
public interface ISysPermissionService extends IService<SysPermission> {
    /**
     * 根据用户id获取权限集合
     * @param userId
     * @return
     */
    Set<String> getPermissions(String userId);

    /**
     * 根据用户id获取菜单
     * @param userId
     * @return
     */
    List<MenuVM> getMenu(String userId);
}
