package com.plg.shiro.service;

import java.util.List;

import com.plg.shiro.bo.RolePermisBo;
import com.plg.shiro.entity.RolePermis;

/**
 * 角色-权限关系服务接口
 * 
 * @author Thinkpad
 *
 */
public interface IRolePermisService {
	int deleteByPrimaryKey(Integer id);

	int insert(RolePermis record);

	RolePermis selectByPrimaryKey(Integer id);
	
	List<RolePermisBo> selectAll();

	int updateByPrimaryKeySelective(RolePermis record);
}
