package com.plg.shiro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.plg.shiro.entity.OmPermis;

public interface OmPermisMapper {
    int deleteByPrimaryKey(Integer permisId);

    int insert(OmPermis record);

    OmPermis selectByPrimaryKey(Integer permisId);
    
    List<OmPermis> selectByUserId(@Param("userId") Integer userId);
    
    List<OmPermis> selectAll();

    int updateByPrimaryKeySelective(OmPermis record);
}