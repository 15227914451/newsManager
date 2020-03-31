package com.plg.shiro.service;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.NewsCategory;

public interface INewsCategoryService {

	List<NewsCategory> findAllList(Map<String, Object> param);

	Integer findSize(Map<String, Object> param);

	int insert(NewsCategory newsCategory);

	void update(Map<String, Object> param);

	void delete(Map<String, Object> param);

	List<NewsCategory> getAll();

	NewsCategory findOne(Integer catogryId);

}
