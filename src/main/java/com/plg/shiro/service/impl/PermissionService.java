package com.plg.shiro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmPermisMapper;
import com.plg.shiro.entity.OmPermis;
import com.plg.shiro.service.IPermissionService;

@Service
public class PermissionService implements IPermissionService {
	private static final Logger logger = LoggerFactory.getLogger(PermissionService.class);
	
	@Resource
	private OmPermisMapper omPermisMapper;

	
	@Override
	public int deleteByPrimaryKey(Integer permisId) {
		logger.info("删除权限:{}", permisId);
		return omPermisMapper.deleteByPrimaryKey(permisId);
	}

	@Override
	public int insert(OmPermis record) {
		logger.info("新增权限:{}", record.getPermisId());
		return omPermisMapper.insert(record);
	}

	@Override
	public OmPermis selectByPrimaryKey(Integer permisId) {
		logger.info("查询权限:{}", permisId);
		return omPermisMapper.selectByPrimaryKey(permisId);
	}

	@Override
	public List<OmPermis> selectByUserId(Integer userId) {
		logger.info("按用户ID查询用户权限，用户ID：{}", userId);
		return omPermisMapper.selectByUserId(userId);
	}
	
	@Override
	public List<OmPermis> selectAll() {
		logger.info("查询所有权限");
		return omPermisMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKeySelective(OmPermis record) {
		logger.info("更新权限:{}", record.getPermisId());
		return omPermisMapper.updateByPrimaryKeySelective(record);
	}

}
