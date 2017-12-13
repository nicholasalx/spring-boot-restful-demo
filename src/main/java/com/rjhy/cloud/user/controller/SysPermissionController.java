package com.rjhy.cloud.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rjhy.cloud.common.BaseController;
import com.rjhy.cloud.user.entity.SysPermission;
import com.rjhy.cloud.user.service.SysPermissionService;
/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-09
 */
@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController extends BaseController<SysPermissionService,SysPermission>{

}
