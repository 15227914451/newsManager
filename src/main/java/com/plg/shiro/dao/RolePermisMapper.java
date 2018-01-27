package com.plg.shiro.dao;

import java.util.List;

import com.plg.shiro.bo.RolePermisBo;
import com.plg.shiro.entity.RolePermis;

public interface RolePermisMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermis record);

    RolePermis selectByPrimaryKey(Integer id);

    List<RolePermisBo> selectAll();
    
    int updateByPrimaryKeySelective(RolePermis record);
}