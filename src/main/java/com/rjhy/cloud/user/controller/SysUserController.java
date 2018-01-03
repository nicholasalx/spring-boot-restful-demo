package com.rjhy.cloud.user.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjhy.cloud.common.BaseController;
import com.rjhy.cloud.common.utils.ShiroUtils;
import com.rjhy.cloud.user.entity.SysRole;
import com.rjhy.cloud.user.entity.SysUser;
import com.rjhy.cloud.user.service.SysRoleService;
import com.rjhy.cloud.user.service.SysUserService;
import com.rjhy.cloud.vm.ResultVM;

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
public class SysUserController extends BaseController<SysUserService, SysUser> {

	@Autowired
	private SysRoleService sysRoleService;
	@PostMapping
	public ResultVM create(@RequestBody SysUser user) {

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
	@GetMapping("/page")
	public PageInfo<SysUser> getPages(@RequestParam(value = "pn", defaultValue = "1") int pn,
			@RequestParam(value = "ps", defaultValue = "50") int ps, String keyword, String orderBy) {
		EntityWrapper<SysUser> wrapper = new EntityWrapper<SysUser>();
		wrapper.like("username", keyword);
		wrapper.orderBy(orderBy);
		PageHelper.startPage(pn, ps);
		List<SysUser> sysUserList = service.selectList(wrapper);
		PageInfo<SysUser> page = new PageInfo<SysUser>(sysUserList);
		return page;
	}
}
