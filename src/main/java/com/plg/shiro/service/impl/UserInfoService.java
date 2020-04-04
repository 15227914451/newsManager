package com.plg.shiro.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.plg.shiro.dao.UserInfoMapper;
import com.plg.shiro.entity.UserInfo;
import com.plg.shiro.service.IUserInfoService;
@Service
public class UserInfoService implements IUserInfoService{
    @Resource
    private UserInfoMapper userInfoMapper;
	@Override
	public List<UserInfo> findAllList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return userInfoMapper.findAllList(param);
	}

	@Override
	public Integer findSize(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return userInfoMapper.findSize(param);
	}

	@Override
	public UserInfo findOne(Integer id) {
		// TODO Auto-generated method stub
		return userInfoMapper.findOne(id);
	}

	@Override
	public void update(UserInfo userInfo) {
		userInfoMapper.update(userInfo);
		
	}

	@Override
	public void insert(UserInfo userInfo) {
		this.userInfoMapper.insert(userInfo);
		
	}

	@Override
	public UserInfo findOneByMobile(String mobile) {
		// TODO Auto-generated method stub
		return userInfoMapper. findOneByMobile(mobile);
	}

	@Override
	public List<UserInfo> findAll() {
		// TODO Auto-generated method stub
		return userInfoMapper.findAll();
	}

	

	

}
