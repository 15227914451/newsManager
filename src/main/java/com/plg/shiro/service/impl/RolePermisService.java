package com.plg.shiro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.bo.RolePermisBo;
import com.plg.shiro.dao.RolePermisMapper;
import com.plg.shiro.entity.RolePermis;
import com.plg.shiro.service.IRolePermisService;

@Service
public class RolePermisService implements IRolePermisService {
	private static final Logger logger = LoggerFactory.getLogger(RolePermisService.class);

	@Resource
	private RolePermisMapper rolePermisMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		logger.info("删除角色-权限关系：{}", id);
		return rolePermisMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RolePermis record) {
		logger.info("新增角色-权限关系：{}", record.getId());
		return rolePermisMapper.insert(record);
	}

	@Override
	public RolePermis selectByPrimaryKey(Integer id) {
		logger.info("查询角色-权限关系：{}", id);
		return rolePermisMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<RolePermisBo> selectAll() {
		logger.info("查询所有角色-权限");
		return rolePermisMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKeySelective(RolePermis record) {
		logger.info("更新角色-权限关系：{}", record.getId());
		return rolePermisMapper.updateByPrimaryKeySelective(record);
	}

}
