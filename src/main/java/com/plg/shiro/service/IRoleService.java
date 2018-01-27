package com.plg.shiro.service;

import java.util.List;

import com.plg.shiro.entity.OmRole;

public interface IRoleService {
    int deleteByPrimaryKey(Integer roleId);

    int insert(OmRole record);

    OmRole selectByPrimaryKey(Integer roleId);
    
    /**
     * 根据用户ID查询用户角色列表
     * @param userId
     * @return
     */
    List<OmRole> selectByUserId(Integer userId);
    
    List<OmRole> selectAll();

    int updateByPrimaryKeySelective(OmRole record);
}
