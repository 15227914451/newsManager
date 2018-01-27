package com.plg.shiro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.bo.UserRoleBo;
import com.plg.shiro.dao.UserRoleMapper;
import com.plg.shiro.entity.UserRole;
import com.plg.shiro.service.IUserRoleService;

@Service
public class UserRoleService implements IUserRoleService {
	private static final Logger logger = LoggerFactory.getLogger(UserRoleService.class);

	@Resource
	private UserRoleMapper userRoleMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		logger.info("删除用户-角色关系：{}", id);
		return userRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserRole record) {
		logger.info("新增用户-角色关系：{}", record.getId());
		return userRoleMapper.insert(record);
	}

	@Override
	public UserRole selectByPrimaryKey(Integer id) {
		logger.info("查询用户-角色关系：{}", id);
		return userRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserRoleBo> selectAll() {
		logger.info("查询所有用户-角色关系");
		return userRoleMapper.selectAll();
	}
	
	@Override
	public int updateByPrimaryKeySelective(UserRole record) {
		logger.info("更新用户-角色关系：{}", record.getId());
		return userRoleMapper.updateByPrimaryKeySelective(record);
	}

}
