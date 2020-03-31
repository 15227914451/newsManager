package com.plg.shiro.service;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.News;
import com.plg.shiro.entity.NewsComment;
import com.plg.shiro.entity.NewsCollection;

public interface INewsCollectionService {

	List<NewsCollection> findAllList(Map<String, Object> param);

	NewsCollection findOne(Integer newsId);

	Integer findSize(Map<String, Object> param);

	void delete(NewsCollection findOne);

}
