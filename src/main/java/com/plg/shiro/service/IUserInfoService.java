package com.plg.shiro.service;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.UserInfo;

public interface IUserInfoService {

	List<UserInfo> findAllList(Map<String, Object> param);

	Integer findSize(Map<String, Object> param);

	UserInfo findOne(Integer valueOf);
	void update(UserInfo userInfo);

	void insert(UserInfo userInfo);

	UserInfo findOneByMobile(String mobile);

	List<UserInfo> findAll();

}
