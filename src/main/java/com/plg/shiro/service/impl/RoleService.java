package com.plg.shiro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmRoleMapper;
import com.plg.shiro.entity.OmRole;
import com.plg.shiro.service.IRoleService;

@Service
public class RoleService implements IRoleService {
	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

	@Resource
	private OmRoleMapper omRoleMapper;

	@Override
	public int deleteByPrimaryKey(Integer roleId) {
		logger.info("删除角色：{}", roleId);
		return omRoleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public int insert(OmRole record) {
		logger.info("新增角色：{}", record.getRoleId());
		return omRoleMapper.insert(record);
	}

	@Override
	public OmRole selectByPrimaryKey(Integer roleId) {
		logger.info("查询角色：{}", roleId);
		return omRoleMapper.selectByPrimaryKey(roleId);
	}
	
	@Override
	public List<OmRole> selectByUserId(Integer userId) {
		logger.info("根据用户ID，查询永不角色，用户ID：{}", userId);
		return omRoleMapper.selectByUserId(userId);
	}
	
	@Override
	public List<OmRole> selectAll() {
		logger.info("查询所有角色");
		return omRoleMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKeySelective(OmRole record) {
		logger.info("更新角色：{}", record.getRoleId());
		return omRoleMapper.updateByPrimaryKeySelective(record);
	}
}
