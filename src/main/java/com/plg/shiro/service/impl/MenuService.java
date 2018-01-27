package com.plg.shiro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmMenuMapper;
import com.plg.shiro.entity.OmMenu;
import com.plg.shiro.service.IMenuService;

@Service
public class MenuService implements IMenuService {
	private static final Logger logger = LoggerFactory.getLogger(MenuService.class);

	@Resource
	private OmMenuMapper omMenuMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer menuId) {
		logger.info("删除菜单：{}", menuId);
		return omMenuMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public int insert(OmMenu record) {
		logger.info("新增菜单：{}", record.getMenuId());
		return omMenuMapper.insert(record);
	}

	@Override
	public OmMenu selectByPrimaryKey(Integer menuId) {
		logger.info("查询菜单：{}", menuId);
		return omMenuMapper.selectByPrimaryKey(menuId);
	}
	
	@Override
	public List<OmMenu> queryUseMenu(Integer userId) {
		logger.info("查询用户菜单，userId{}", userId);
		return omMenuMapper.queryUseMenu(userId);
	}

	@Override
	public List<OmMenu> selectAll() {
		logger.info("查询所有菜单");
		return omMenuMapper.selectAll();
	}
	
	@Override
	public int updateByPrimaryKeySelective(OmMenu record) {
		logger.info("更新菜单：{}", record.getMenuId());
		return omMenuMapper.updateByPrimaryKeySelective(record);
	}
}
