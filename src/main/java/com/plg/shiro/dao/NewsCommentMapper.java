package com.plg.shiro.dao;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.NewsComment;

public interface NewsCommentMapper {

	List<NewsComment> findAllList(Map<String, Object> param);

	Integer findSize(Map<String, Object> param);

	NewsComment findOne(Integer commentId);

	void update(NewsComment newsComment);

	void delete(NewsComment newsComment);

	

	

}
