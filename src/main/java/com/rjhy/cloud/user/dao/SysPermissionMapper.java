package com.rjhy.cloud.user.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rjhy.cloud.user.entity.SysPermission;

/**
 * <p>
  * 菜单管理 Mapper 接口
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-09
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户id获取菜单
     * @param userId
     * @return
     */
    List<SysPermission> selectMenuByUserId(String userId);

    /**
     * 根据用户id查询权限
     * @param userId
     * @return
     */
    List<String> getPermissions(String userId);
}