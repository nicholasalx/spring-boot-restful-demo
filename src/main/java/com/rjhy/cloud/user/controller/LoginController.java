package com.rjhy.cloud.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.util.StringUtil;
import com.rjhy.cloud.common.utils.ShiroUtils;
import com.rjhy.cloud.user.entity.SysRole;
import com.rjhy.cloud.user.entity.SysUser;
import com.rjhy.cloud.user.service.SysPermissionService;
import com.rjhy.cloud.user.service.SysRoleService;
import com.rjhy.cloud.user.service.SysUserService;
import com.rjhy.cloud.vm.ResultVM;

/**
 * Created by oukingtim
 */
@RestController
@RequestMapping("/oauth")
public class LoginController {

	@Autowired
	private SysPermissionService sysPermissionService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;

	@PostMapping("/login")
	public ResultVM login(@RequestBody Map<String, String> map) {
		UsernamePasswordToken token = null;
		SysUser user = null;
		try {
			String password = map.get("password");
			String username = map.get("username");
			if (StringUtil.isEmpty(username)) {
				throw new IllegalArgumentException("username is required.");
			}
			if (StringUtil.isEmpty(password)) {
				throw new IllegalArgumentException("password is required.");
			}
			Subject subject = ShiroUtils.getSubject();
			// sha256加密
			password = new Sha256Hash(password).toHex();
			token = new UsernamePasswordToken(username, password);
			subject.login(token);
			// 登陆成功后，查询、返回用户信息
			user =ShiroUtils.getUser();
			//user = sysUserService.findByUserName(username);

		} catch (UnknownAccountException e) {
			return ResultVM.error(e.getMessage()); // 未知账户
		} catch (IncorrectCredentialsException e) {
			return ResultVM.error(e.getMessage()); // 密码不正确
		} catch (LockedAccountException e) {
			return ResultVM.error(e.getMessage()); // 账户已锁定
		} catch (ExcessiveAttemptsException e) {
			return ResultVM.error(e.getMessage()); // 错误次数大于5次,账户已锁定
		} catch (DisabledAccountException e) {
			return ResultVM.error(e.getMessage()); // 帐号已经禁止登录
		} catch (AuthenticationException e) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			// 进行登录验证..验证未通过,堆栈轨迹如下
			e.printStackTrace();
			return ResultVM.error(e.getMessage()); // 用户名或密码不正确
		}
		return ResultVM.ok(user);
	}

	@GetMapping("/signout")
	public ResultVM logout() {
		ShiroUtils.logout();
		return ResultVM.ok();
	}

	@GetMapping("/isLogin")
	public ResultVM isLogin() {
		if (ShiroUtils.isLogin()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", ShiroUtils.getUser());
			map.put("menu", sysPermissionService.getMenu(ShiroUtils.getUserId()));
			map.put("perms", sysPermissionService.getPermissions(ShiroUtils.getUserId()));
			return ResultVM.ok(map);
		}
		return ResultVM.error();
	}

	@PutMapping("/password")
	public ResultVM reSetPassword(@RequestBody Map<String, String> map) {
		UsernamePasswordToken token = null;
		String password = map.get("password");
		String newpassword = map.get("newpassword");
		if (StringUtils.isBlank(password)) {
			return ResultVM.error("原密码不能为空");
		}
		if (StringUtils.isBlank(newpassword)) {
			return ResultVM.error("新密码不能为空");
		}
		SysUser user = ShiroUtils.getUser();
		// sha256加密
		password = new Sha256Hash(password).toHex();
		if (!user.getPassword().equals(password)) {
			return ResultVM.error("密码错误");
		}
		newpassword = new Sha256Hash(newpassword).toHex();
		user.setPassword(newpassword);
		sysUserService.updateAllColumnById(user);
		return ResultVM.ok();
	}

	@PostMapping("/sign")
	public ResultVM sign(@RequestBody Map<String, String> map) {
		UsernamePasswordToken token = null;
		try {
			String password = map.get("password");
			String username = map.get("username");
			String email = map.get("email");
			SysUser user = new SysUser(username);
			
			// sha256加密 保存密码
			user.setPassword(new Sha256Hash(password).toHex());
			user.setEmail(email);
			// 添加默认用户user1权限
			List<SysRole> rlist = sysRoleService.selectList(new EntityWrapper<>(new SysRole("user1")));
			for (SysRole role : rlist) {
				role.setChecked(true);
			}
			user.setRolelist(rlist);
			sysUserService.insert(user);

			Subject subject = ShiroUtils.getSubject();
			token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			subject.login(token);
		} catch (UnknownAccountException e) {
			return ResultVM.error(e.getMessage());
		} catch (IncorrectCredentialsException e) {
			return ResultVM.error(e.getMessage());
		} catch (LockedAccountException e) {
			return ResultVM.error(e.getMessage());
		}

		return ResultVM.ok();
	}
}
