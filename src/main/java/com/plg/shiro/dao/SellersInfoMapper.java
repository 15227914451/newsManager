package com.plg.shiro.dao;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.SellersInfo;

public interface SellersInfoMapper {

	List<SellersInfo> findAllList(Map<String, Object> param);

	Integer findSize(Map<String, Object> param);

	void insert(SellersInfo sellersInfo);

	SellersInfo findOne(Integer sellersId);

	void delate(Map<String, Object> param);
}
