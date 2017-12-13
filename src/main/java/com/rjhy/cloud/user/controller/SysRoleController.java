package com.rjhy.cloud.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rjhy.cloud.common.BaseController;
import com.rjhy.cloud.user.entity.SysRole;
import com.rjhy.cloud.user.service.SysRoleService;


/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-08
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController<SysRoleService, SysRole>{
}
