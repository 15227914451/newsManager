package com.plg.shiro.service;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.NewsComment;

public interface INewsCommentService {

	List<NewsComment> findAllList(Map<String, Object> param);

	Integer findSize(Map<String, Object> param);

	NewsComment findOne(Integer commentId);

	void update(NewsComment newsComment);

	void delete(NewsComment newsComment);

}
