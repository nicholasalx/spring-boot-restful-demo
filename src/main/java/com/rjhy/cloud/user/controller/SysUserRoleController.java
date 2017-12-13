package com.rjhy.cloud.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rjhy.cloud.common.BaseController;
import com.rjhy.cloud.user.entity.SysUserRole;
import com.rjhy.cloud.user.service.SysUserRoleService;

/**
 * <p>
 * 用户与角色关系表 前端控制器
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-08
 */
@RestController
@RequestMapping("/sysUserRole")

public class SysUserRoleController extends BaseController<SysUserRoleService,SysUserRole>{
}
