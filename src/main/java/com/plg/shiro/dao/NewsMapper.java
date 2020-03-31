package com.plg.shiro.dao;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.News;

public interface NewsMapper {

	List<News> findAllList(Map<String, Object> param);

	Integer findSize(Map<String, Object> param);

	void insert(News news);

	News findOne(Integer id);


	void update(Map<String, Object> param);

	void delete(Map<String, Object> param);

}
