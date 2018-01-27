package com.plg.shiro.service;

import javax.annotation.Resource;

import org.junit.Test;

public class ITestServiceTest extends TestBase {

	@Resource
	private ITestService testService;
	
	@Test
	public void testSelectById(){
		try{
			com.plg.shiro.entity.Test test = testService.selectById("plg3");
			if(null != test){
				System.out.println(test.getName());
				System.out.println(test.getOwner());
				System.out.println(test.getSex());
				System.out.println(test.getSpecies());
				System.out.println(test.getBirth());
				System.out.println(test.getCreateTime());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
