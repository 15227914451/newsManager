package com.plg.shiro.dao;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.UserInfo;

public interface UserInfoMapper {

	List<UserInfo> findAllList(Map<String, Object> param);

	Integer findSize(Map<String, Object> param);

	UserInfo findOne(Integer id);

	void update(UserInfo userInfo);

	void insert(UserInfo userInfo);

	UserInfo findOneByMobile(String mobile);

}
