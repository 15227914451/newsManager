package com.plg.shiro.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.GoodsInfoMapper;
import com.plg.shiro.dao.ProductInfoMapper;
import com.plg.shiro.entity.GoodsInfo;
import com.plg.shiro.service.IGoodsInfoService;

@Service
public class GoodsInfoService implements IGoodsInfoService {
	@Resource
	private GoodsInfoMapper goodsInfoMapper;
	private static final Logger logger = LoggerFactory.getLogger(GoodsInfoService.class);
	@Override
	public List<GoodsInfo> findAllList(Map<String, Object> param) {
		
		return goodsInfoMapper.findAllList(param);
	}

	@Override
	public Integer findSize(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.findSize(param);
	}

	@Override
	public int insert(GoodsInfo goodsInfo) {
		// TODO Auto-generated method stub
		
		return goodsInfoMapper.insert(goodsInfo);
	}

	@Override
	public void update(Map<String, Object> param) {
		// TODO Auto-generated method stub
		this.goodsInfoMapper.update(param);
		
	}

	@Override
	public void delate(Map<String, Object> param) {
		// TODO Auto-generated method stub
		this.goodsInfoMapper.delate(param);
	}

	@Override
	public GoodsInfo findOne(Integer goodsId) {
		// TODO Auto-generated method stub
		return this.goodsInfoMapper.findOne(goodsId);
	}

}
