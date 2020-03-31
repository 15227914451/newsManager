package com.plg.shiro.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.plg.shiro.dao.NewsCategoryMapper;
import com.plg.shiro.entity.NewsCategory;
import com.plg.shiro.service.INewsCategoryService;
@Service
public class NewsCategoryService implements INewsCategoryService {
	@Resource
	private NewsCategoryMapper newsCategoryMapper;
	@Override
	public List<NewsCategory> findAllList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return newsCategoryMapper.findAllList(param);
	}

	@Override
	public Integer findSize(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return newsCategoryMapper.findSize(param);
	}

	@Override
	public int insert(NewsCategory newsCategory) {
		// TODO Auto-generated method stub
		return newsCategoryMapper.insert(newsCategory);
	}

	@Override
	public void update(Map<String, Object> param) {
		// TODO Auto-generated method stub
		newsCategoryMapper.update(param);
		
	}

	@Override
	public void delete(Map<String, Object> param) {
		// TODO Auto-generated method stub
		newsCategoryMapper.delate(param);
		
	}

	@Override
	public List<NewsCategory> getAll() {
		// TODO Auto-generated method stub
		return newsCategoryMapper.getAll();
	}

	@Override
	public NewsCategory findOne(Integer catogryId) {
		// TODO Auto-generated method stub
		return newsCategoryMapper.findOne(catogryId);
	}

}
