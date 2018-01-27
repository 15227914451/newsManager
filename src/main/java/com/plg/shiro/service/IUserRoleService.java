package com.plg.shiro.service;

import java.util.List;

import com.plg.shiro.bo.UserRoleBo;
import com.plg.shiro.entity.UserRole;

/**
 * 用户-角色关系服务
 * 
 * @author Thinkpad
 *
 */
public interface IUserRoleService {
	int deleteByPrimaryKey(Integer id);

	int insert(UserRole record);

	UserRole selectByPrimaryKey(Integer id);
	
	List<UserRoleBo> selectAll();

	int updateByPrimaryKeySelective(UserRole record);
}
