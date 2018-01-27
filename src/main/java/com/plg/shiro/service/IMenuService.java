package com.plg.shiro.service;

import java.util.List;

import com.plg.shiro.entity.OmMenu;

public interface IMenuService {
    int deleteByPrimaryKey(Integer menuId);

    int insert(OmMenu record);

    OmMenu selectByPrimaryKey(Integer menuId);
    
    List<OmMenu> queryUseMenu(Integer userId);
    
    List<OmMenu> selectAll();

    int updateByPrimaryKeySelective(OmMenu record);
}
