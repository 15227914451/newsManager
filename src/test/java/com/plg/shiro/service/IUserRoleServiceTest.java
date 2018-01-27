package com.plg.shiro.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.plg.shiro.entity.UserRole;

public class IUserRoleServiceTest extends TestBase {

	@Resource
	private IUserRoleService userRoleService;

	@Test
	public void testSelectByPrimaryKey() {
		try {
			UserRole userRole = userRoleService.selectByPrimaryKey(1);
			if (null != userRole) {
				System.out.println(userRole.getId());
				System.out.println(userRole.getUserId());
				System.out.println(userRole.getRoleId());
				System.out.println(userRole.getCreateTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
