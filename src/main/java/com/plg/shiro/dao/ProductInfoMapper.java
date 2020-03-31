package com.plg.shiro.dao;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.ProductInfo;

public interface ProductInfoMapper {
	int insert(ProductInfo productInfo);

	List<ProductInfo> findAllList(Map<String, Object> param);

	Integer findSize(Map<String, Object> param);

	void update(Map<String, Object> param);

	void delate(Map<String, Object> param);

	List<ProductInfo> findByProductIds(Map<String, Object> param);

	Integer findSizeByProductIds(Map<String, Object> param);
}
