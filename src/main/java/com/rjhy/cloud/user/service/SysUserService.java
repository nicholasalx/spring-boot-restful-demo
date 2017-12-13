package com.rjhy.cloud.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rjhy.cloud.user.dao.SysUserMapper;
import com.rjhy.cloud.user.entity.SysUser;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author Rainbow.Pang
 * @since 2017-12-08
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
	@Override
    public SysUser findByUserName(String username) {
        List<SysUser> list = selectList(new EntityWrapper<>(new SysUser(username)));
        if (list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
