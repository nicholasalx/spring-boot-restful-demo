package com.rjhy.cloud.common.config;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rjhy.cloud.user.entity.SysUser;
import com.rjhy.cloud.user.service.SysPermissionService;
import com.rjhy.cloud.user.service.SysRoleService;
import com.rjhy.cloud.user.service.SysUserService;
/**
 * Created by oukingtim
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService; 
    @Autowired
    private SysPermissionService sysPermissionService;
    /**
     * @Author : Rainbow.pang
     * @Description : 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    	SysUser user = (SysUser)principalCollection.getPrimaryPrincipal();
        String userId = user.getId();

        //用户权限列表
        Set<String> permsSet = sysPermissionService.getPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * @Author : oukingtim
     * @Description : 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        //查询用户信息
        SysUser user = sysUserService.findByUserName(username);

        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("用户名不正确");
        }

        //密码错误
        if(!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("密码不正确");
        }

        //账号禁用
        if("0".equals(user.getStatus())){
            throw new LockedAccountException("用户已被禁用,请联系管理员");
        }

        //获取用户角色列表
        List<String>  rolelist=sysRoleService.getRoles(user.getId());
        user.setRolelist(rolelist);
        
        //获取用户权限列表
        Set<String> permsSet = sysPermissionService.getPermissions(user.getId());
        List<String> permsList=new ArrayList<String> ();
        permsList.addAll(permsSet);
        user.setPermissions(permsList);
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
    
    @Override
    public void onLogout(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
        SysUser shiroUser = (SysUser) principals.getPrimaryPrincipal();
        removeUserCache(shiroUser);
    }

    /**
     * 清除用户缓存
     * @param shiroUser
     */
    public void removeUserCache(SysUser user){
        removeUserCache(user.getUsername());
    }

    /**
     * 清除用户缓存
     * @param loginName
     */
    public void removeUserCache(String loginName){
        SimplePrincipalCollection principals = new SimplePrincipalCollection();
        principals.add(loginName, super.getName());
        super.clearCachedAuthenticationInfo(principals);
    }
}
