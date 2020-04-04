package com.plg.shiro.service;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.News;

public interface INewsService {

	List<News> findAllList(Map<String, Object> param);

	Integer findSize(Map<String, Object> param);

	void save(News news);

	News findOne(Integer valueOf);

	void update(Map<String, Object> param);

	void delete(Map<String, Object> param);

	List<News> findByRate();

}
