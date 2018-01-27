package com.plg.shiro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmUserMapper;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.service.IUserService;

@Service
public class UserService implements IUserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Resource
	private OmUserMapper omUserMapper;

	@Override
	public int deleteByPrimaryKey(Integer userId) {
		logger.info("删除用户:{}", userId);
		return omUserMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int insert(OmUser record) {
		logger.info("新增用户：{}", record.getUserId());
		return omUserMapper.insert(record);
	}

	@Override
	public OmUser selectByPrimaryKey(Integer userId) {
		logger.info("查询用户：{}", userId);
		return omUserMapper.selectByPrimaryKey(userId);
	}
	
	@Override
	public OmUser selectByUserName(String userName) {
		logger.info("通过用户名查询用户，用户名userName：{}", userName);
		return omUserMapper.selectByUserName(userName);
	}

	@Override
	public List<OmUser> selectAll() {
		logger.info("查询所有用户");
		return omUserMapper.selectAll();
	}
	
	@Override
	public int updateByPrimaryKeySelective(OmUser record) {
		logger.info("更新用户：{}", record.getUserId());
		return omUserMapper.updateByPrimaryKeySelective(record);
	}
}
