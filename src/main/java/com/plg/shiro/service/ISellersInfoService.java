package com.plg.shiro.service;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.SellersInfo;



public interface ISellersInfoService {

	List<SellersInfo> findAllList(Map<String, Object> param);

	Integer findSize(Map<String, Object> param);

	void insert(SellersInfo sellersInfo);

	SellersInfo findOne(Integer valueOf);

	void delate(Map<String, Object> param);

}
