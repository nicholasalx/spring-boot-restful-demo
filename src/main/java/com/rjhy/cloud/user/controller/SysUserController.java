package com.rjhy.cloud.user.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjhy.cloud.common.BaseController;
import com.rjhy.cloud.common.utils.ShiroUtils;
import com.rjhy.cloud.user.entity.SysUser;
import com.rjhy.cloud.user.service.SysRoleService;
import com.rjhy.cloud.user.service.SysUserService;
import com.rjhy.cloud.vm.ResultVM;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-08
 */
@Api(value="用户controller",tags={"用户操作接口"})
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController<SysUserService, SysUser> {

	@Autowired
	private SysRoleService sysRoleService;
	
	@ApiOperation(value="创建新用户",notes="这里是详细说明文档")
	@RequiresPermissions({ "KT Admin" })
	@PostMapping
	public ResultVM create(@ApiParam(name="user",value="用户信息json对象",required=true) @RequestBody SysUser user) {

		// sha256加密 保存密码
		user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		// 添加默认用户user1权限
		/*List<SysRole> rlist = sysRoleService.selectList(new EntityWrapper<>(new SysRole("user1")));
		for (SysRole role : rlist) {
			role.setChecked(true);
		}
		user.setRolelist(rlist);*/
		user.setCreateUserId(ShiroUtils.getUserId());
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setUpdateUserId(ShiroUtils.getUserId());
		if (service.insert(user)) {
			return ResultVM.ok();
		} else {
			return ResultVM.error();
		}
	}

	// 重写 getPages方法
	@ApiOperation(value="分页查询用户列表",notes="这里是详细说明文档，分页会返回详细的json数据，请仔细看")
	@GetMapping("/page")
	public PageInfo<SysUser> getPages(
			@ApiParam(name="pn",value="页号",required=false) @RequestParam(value = "pn", defaultValue = "1") int pn,
			@ApiParam(name="ps",value="每页大小",required=false) int ps,
			@ApiParam(name="keyword",value="关键字查询",required=false) String keyword, 
			@ApiParam(name="orderBy",value="排序字段名",required=false) String orderBy) {
		EntityWrapper<SysUser> wrapper = new EntityWrapper<SysUser>();
		wrapper.like("username", keyword);
		wrapper.orderBy(orderBy);
		PageHelper.startPage(pn, ps);
		List<SysUser> sysUserList = service.selectList(wrapper);
		PageInfo<SysUser> page = new PageInfo<SysUser>(sysUserList);
		return page;
	}
}
