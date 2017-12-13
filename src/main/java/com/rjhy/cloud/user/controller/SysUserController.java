package com.rjhy.cloud.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rjhy.cloud.common.BaseController;
import com.rjhy.cloud.user.entity.SysUser;
import com.rjhy.cloud.user.service.SysUserService;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-08
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController<SysUserService,SysUser>{
    
}
