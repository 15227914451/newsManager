package com.plg.shiro.service;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.ProductInfo;

public interface IProductInfoService {

	int insert(ProductInfo productInfo);
	/**
	 * 查询商品列表
	 * @param param
	 * @return
	 */
	List<ProductInfo> findAllList(Map<String, Object> param);
	Integer findSize(Map<String, Object> param);
	void update(Map<String, Object> param);
	/**
	 * 删除
	 * @param param
	 */
	void delete(Map<String, Object> param);
	List<ProductInfo> findByProductIds(Map<String, Object> param);
	Integer findSizeByProductIds(Map<String, Object> param);


}
