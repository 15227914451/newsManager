package com.plg.shiro.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.plg.shiro.dao.NewsCollectionMapper;
import com.plg.shiro.entity.News;
import com.plg.shiro.entity.NewsCollection;
import com.plg.shiro.service.INewsCollectionService;
import com.plg.shiro.service.INewsCommentService;
@Service
public class NewsCollectionService implements INewsCollectionService {
	@Resource
	private NewsCollectionMapper newsCollectionMapper;
	@Override
	public List<NewsCollection> findAllList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return newsCollectionMapper.findAllList(param);
	}

	

	@Override
	public Integer findSize(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return newsCollectionMapper.findSize(param);
	}

	@Override
	public NewsCollection findOne(Integer id) {
		// TODO Auto-generated method stub
		return newsCollectionMapper.findOne(id);
	}

	@Override
	public void delete(NewsCollection findOne) {
		// TODO Auto-generated method stub
		newsCollectionMapper.delete(findOne);
		
	}

}
