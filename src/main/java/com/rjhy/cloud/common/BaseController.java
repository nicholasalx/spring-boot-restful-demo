package com.rjhy.cloud.common;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjhy.cloud.common.utils.ShiroUtils;
import com.rjhy.cloud.user.entity.BaseEntity;
import com.rjhy.cloud.vm.ResultVM;

/**
 * 通用Controller（增删改查）
 * Created by oukingtim
 */
public abstract class BaseController<S extends IService<T>, T extends BaseEntity<T>> {

    @Autowired
    protected S service;

    /**
     * 新增
     * @param t
     * @return
     */
    @PostMapping
    public ResultVM create(@RequestBody T t) {

        t.setCreateUserId(ShiroUtils.getUserId());
        t.setCreateTime(new Date());
        t.setUpdateTime(new Date());
        t.setUpdateUserId(ShiroUtils.getUserId());
        if(service.insert(t)){
            return ResultVM.ok();
        }else{
            return ResultVM.error();
        }
    }

    /**
     * 更新
     * @param t
     * @return
     */
    @PutMapping
    public ResultVM update(@RequestBody T t) {

        t.setUpdateTime(new Date());
        t.setUpdateUserId(ShiroUtils.getUserId());
        if(service.updateById(t)){
            return ResultVM.ok();
        }else{
            return ResultVM.error();
        }
    }
    
    /**
     * 根据id获取实体对象
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public T getInfo(@PathVariable String id) {
        return service.selectById(id);
    }

	/*
	 * 分页查询
	 */
	@GetMapping("/page")
	public PageInfo<T> getSysUsers(@RequestParam(value = "pn", defaultValue = "1") int pn,
			@RequestParam(value = "ps", defaultValue = "100") int ps, String keyword, String orderBy)
	{
		EntityWrapper<T> wrapper = new EntityWrapper<T>();
		wrapper.like("name", keyword);
		wrapper.orderBy(orderBy);		
		PageHelper.startPage(pn, ps);
		List<T> sysUserList = service.selectList(wrapper);		
		PageInfo<T> page = new PageInfo<T>(sysUserList);		
		return page;
	}
	
    /**
     * 删除，可批量
     * @param id
     * @return
     */
    @DeleteMapping("/{ids}")
    public ResultVM delete(@PathVariable String ids) {
    	
        if(service.deleteBatchIds(Arrays.asList(ids.split("_")))){
            return ResultVM.ok();
        }else{
            return ResultVM.error();
        }
    }

}
