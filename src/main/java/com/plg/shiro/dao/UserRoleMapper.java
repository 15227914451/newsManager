package com.plg.shiro.dao;

import java.util.List;

import com.plg.shiro.bo.UserRoleBo;
import com.plg.shiro.entity.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    UserRole selectByPrimaryKey(Integer id);
    
    List<UserRoleBo> selectAll();

    int updateByPrimaryKeySelective(UserRole record);
}