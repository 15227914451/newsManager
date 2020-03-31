package com.plg.shiro.dao;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.NewsCollection;

public interface NewsCollectionMapper {

	List<NewsCollection> findAllList(Map<String, Object> param);

	Integer findSize(Map<String, Object> param);

	NewsCollection findOne(Integer id);

	void delete(NewsCollection findOne);

}
