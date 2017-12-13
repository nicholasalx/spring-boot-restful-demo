package com.rjhy.cloud.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rjhy.cloud.common.BaseController;
import com.rjhy.cloud.user.entity.SysRolePermission;
import com.rjhy.cloud.user.service.SysRolePermissionService;

/**
 * <p>
 * 角色与菜单关系表 前端控制器
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-08
 */
@RestController
@RequestMapping("/sysRolePermission")

public class SysRolePermissionController extends BaseController<SysRolePermissionService,SysRolePermission>{
}
