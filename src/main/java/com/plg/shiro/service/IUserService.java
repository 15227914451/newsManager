package com.plg.shiro.service;

import java.util.List;

import com.plg.shiro.entity.OmUser;

/**
 * 用户服务接口
 * 
 * @author Thinkpad
 *
 */
public interface IUserService {

	int deleteByPrimaryKey(Integer userId);

	int insert(OmUser record);

	OmUser selectByPrimaryKey(Integer userId);
	
	OmUser selectByUserName(String userName);

	int updateByPrimaryKeySelective(OmUser record);
	
	List<OmUser> selectAll();
}
