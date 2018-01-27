package com.plg.shiro.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.plg.shiro.entity.OmUser;

public class IUserServiceTest extends TestBase {
	
	@Resource
	private IUserService userService;
	
	@Test
	public void testSelectByPrimaryKey(){
		try{
			OmUser omUser = userService.selectByPrimaryKey(1);
			System.out.println(omUser.getUserId());
			System.out.println(omUser.getUserName());
			System.out.println(omUser.getPassword());
			System.out.println(omUser.getMobile());
			System.out.println(omUser.getRemark());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
