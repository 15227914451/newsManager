package com.plg.shiro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.plg.shiro.entity.OmUser;

public interface OmUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(OmUser record);

    OmUser selectByPrimaryKey(Integer userId);
    
    OmUser selectByUserName(@Param("userName") String userName);
    
    List<OmUser> selectAll();

    int updateByPrimaryKeySelective(OmUser record);
}