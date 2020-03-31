package com.plg.shiro.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.plg.shiro.dao.NewsCommentMapper;
import com.plg.shiro.entity.NewsComment;
import com.plg.shiro.service.INewsCommentService;
@Service
public class NewsCommentService implements INewsCommentService{
	@Resource
	private NewsCommentMapper newsCommentMapper;
	@Override
	public List<NewsComment> findAllList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return newsCommentMapper.findAllList(param);
	}

	@Override
	public Integer findSize(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return newsCommentMapper.findSize(param);
	}

	@Override
	public NewsComment findOne(Integer commentId) {
		// TODO Auto-generated method stub
		return newsCommentMapper.findOne(commentId);
	}

	@Override
	public void update(NewsComment newsComment) {
		// TODO Auto-generated method stub
		newsCommentMapper.update(newsComment);
	}

	@Override
	public void delete(NewsComment newsComment) {
		// TODO Auto-generated method stub
		newsCommentMapper.delete(newsComment);
	}
	
}
