package com.plg.shiro.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.ProductInfoMapper;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.entity.ProductInfo;
import com.plg.shiro.service.IProductInfoService;
@Service
public class ProductInfoService implements IProductInfoService {
	@Resource
	private ProductInfoMapper productInfoMapper;
	private static final Logger logger = LoggerFactory.getLogger(ProductInfoService.class);
	@Override
	public int insert(ProductInfo productInfo) {
		logger.info("新增产品：{}", productInfo.getProductName());
		return productInfoMapper.insert(productInfo);
	}
	@Override
	public List<ProductInfo> findAllList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return productInfoMapper.findAllList(param);
	}
	@Override
	public Integer findSize(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return productInfoMapper.findSize(param);
	}
	@Override
	public void update(Map<String, Object> param) {
		// TODO Auto-generated method stub
		this.productInfoMapper.update(param);
		
	}
	@Override
	public void delete(Map<String, Object> param) {
		this.productInfoMapper.delate(param);
		
	}
	@Override
	public List<ProductInfo> findByProductIds(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.productInfoMapper.findByProductIds(param);
	}
	@Override
	public Integer findSizeByProductIds(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.productInfoMapper.findSizeByProductIds(param);
	}

}
