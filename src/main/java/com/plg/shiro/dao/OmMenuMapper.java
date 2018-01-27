package com.plg.shiro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.plg.shiro.entity.OmMenu;

public interface OmMenuMapper {
	int deleteByPrimaryKey(Integer menuId);

	int insert(OmMenu record);

	OmMenu selectByPrimaryKey(Integer menuId);

	List<OmMenu> queryUseMenu(@Param("userId") Integer userId);

	List<OmMenu> selectAll();

	int updateByPrimaryKeySelective(OmMenu record);
}