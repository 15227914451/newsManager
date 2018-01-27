package com.plg.shiro.service;

import java.util.List;

import com.plg.shiro.entity.OmPermis;

/**
 * 权限服务接口
 * 
 * @author Thinkpad
 *
 */
public interface IPermissionService {
	int deleteByPrimaryKey(Integer permisId);

	int insert(OmPermis record);

	OmPermis selectByPrimaryKey(Integer permisId);

	/**
	 * 按用户ID查询用户权限
	 * 
	 * @param userId
	 * @return
	 */
	List<OmPermis> selectByUserId(Integer userId);

	List<OmPermis> selectAll();

	int updateByPrimaryKeySelective(OmPermis record);
}
