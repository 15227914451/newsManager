package com.plg.shiro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.plg.shiro.entity.OmRole;

public interface OmRoleMapper {
	int deleteByPrimaryKey(Integer roleId);

	int insert(OmRole record);

	OmRole selectByPrimaryKey(Integer roleId);

	List<OmRole> selectByUserId(@Param("userId") Integer userId);

	List<OmRole> selectAll();

	int updateByPrimaryKeySelective(OmRole record);
}