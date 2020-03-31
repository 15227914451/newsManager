package com.plg.shiro.service;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.GoodsInfo;

public interface IGoodsInfoService {

	List<GoodsInfo> findAllList(Map<String, Object> param);

	Integer findSize(Map<String, Object> param);

	int insert(GoodsInfo goodsInfo);

	void update(Map<String, Object> param);

	void delate(Map<String, Object> param);

	GoodsInfo findOne(Integer goodsId);

}
