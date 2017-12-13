package com.rjhy.cloud.common.utils;


import java.util.ArrayList;
import java.util.List;

import com.rjhy.cloud.user.entity.SysPermission;
import com.rjhy.cloud.vm.MenuVM;

/**
 * 组装菜单树工具类
 * Created by oukingtim
 */
public class MenuTreeUtil {

	public static MenuVM getTree(List<SysPermission> list)  {

		if(list==null||list.size()<1)	return null;
		return buildTree(getRoot(list),list);
	}

	private static MenuVM buildTree(MenuVM pnode, List<SysPermission> nodes) {
		List<MenuVM> childs = new ArrayList<MenuVM>();
		for( SysPermission tmp : nodes ){
			if( pnode.getId().equals(tmp.getParentId()) ){
				childs.add(new MenuVM(tmp));
			}
		}

		if( childs.size()>0 ){
			pnode.setSubMenu(childs);
			for( MenuVM ptmp : childs ){
				buildTree(ptmp, nodes);
			}
		}
		return pnode;
	}

	private static MenuVM getRoot(List<SysPermission> list) {
		for (SysPermission po : list){
			if("#".equals(po.getParentId())){
				return new MenuVM(po);
			}
		}
		return null;
	}
    public static List<MenuVM> getMenu(List<SysPermission> list) {

        if(list==null||list.size()<1)	return null;

        return getTree(list)==null?null:getTree(list).getSubMenu();
    }

}
