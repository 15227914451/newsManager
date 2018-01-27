package com.plg.shiro.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.ITestDao;
import com.plg.shiro.entity.Test;
import com.plg.shiro.service.ITestService;

@Service
public class TestService implements ITestService {
	private static final Logger logger = LoggerFactory.getLogger(TestService.class);
	
	@Resource
	private ITestDao testDao;
	
	@Override
	public Test selectById(String name) {
		logger.info("根据id查询");
		return testDao.selectById(name);
	}

}
