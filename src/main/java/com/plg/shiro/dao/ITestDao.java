package com.plg.shiro.dao;

import org.apache.ibatis.annotations.Param;

import com.plg.shiro.entity.Test;

public interface ITestDao {

	public Test selectById(@Param("name") String name);
}
