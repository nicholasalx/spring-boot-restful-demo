package com.rjhy.cloud.user.dao;

import com.rjhy.cloud.user.entity.SysRole;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 角色 Mapper 接口
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-08
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

	/**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    List<String> getRoles(String userId);
}