package com.plg.shiro.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.plg.shiro.dao.NewsMapper;
import com.plg.shiro.entity.News;
import com.plg.shiro.service.INewsService;

@Service
public class NewsService implements INewsService {
	@Resource
	private NewsMapper newsMapper;

	@Override
	public List<News> findAllList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return newsMapper.findAllList(param);
	}

	@Override
	public Integer findSize(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return newsMapper.findSize(param);
	}

	@Override
	public void save(News news) {
		// TODO Auto-generated method stub
		newsMapper.insert(news);
	}

	@Override
	public News findOne(Integer id) {
		// TODO Auto-generated method stub
		return newsMapper.findOne(id);
	}

	@Override
	public void update(Map<String, Object> param) {
		// TODO Auto-generated method stub
		newsMapper.update(param);
	}

	@Override
	public void delete(Map<String, Object> param) {
		this.newsMapper.delete(param);
		
	}


}
