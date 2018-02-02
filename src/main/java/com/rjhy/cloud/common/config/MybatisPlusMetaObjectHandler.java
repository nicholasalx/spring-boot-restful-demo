package com.rjhy.cloud.common.config;

import java.sql.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.rjhy.cloud.common.utils.SnowFlake;
import com.rjhy.cloud.user.entity.SysUser;

public class MybatisPlusMetaObjectHandler extends MetaObjectHandler  {
	SnowFlake snowFlake;
	
    public MybatisPlusMetaObjectHandler() {
		super();
		this.snowFlake = new SnowFlake(2, 3);
		// TODO Auto-generated constructor stub
	}

	//新增填充
    @Override
    public void insertFill(MetaObject metaObject) {        
       /* Object createID = getFieldValByName("id", metaObject);
        if (createID == null) {  
            setFieldValByName("id", String.valueOf(snowFlake.nextId()), metaObject); 
        } */
        
        //使用当前登陆用户id自动填充 update_user_id 字段
        Object lastUpdateUserId = getFieldValByName("update_user_id", metaObject);
        SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
        if (null == lastUpdateUserId) {
        	setFieldValByName("update_user_id", user.getId(), metaObject);
        }
        //使用当前时间自动填充update_time
        Object lastUpdateTime = getFieldValByName("update_time", metaObject);
        if (null == lastUpdateTime) {
        	setFieldValByName("update_time", new Date(0), metaObject);
        }
    }

    //更新填充
    @Override
    public void updateFill(MetaObject metaObject) {
        insertFill(metaObject);
    }
    
}
