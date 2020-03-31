package com.plg.shiro.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.plg.shiro.dao.SellersInfoMapper;
import com.plg.shiro.entity.SellersInfo;
import com.plg.shiro.service.ISellersInfoService;

@Service
public class SellersInfoService implements ISellersInfoService {
	@Resource
	private SellersInfoMapper sellersInfoMapper;
	@Override
	public List<SellersInfo> findAllList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sellersInfoMapper.findAllList(param);
	}
	@Override
	public Integer findSize(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sellersInfoMapper.findSize(param);
	}
	@Override
	public void insert(SellersInfo sellersInfo) {
		// TODO Auto-generated method stub
		sellersInfoMapper.insert(sellersInfo);
	}
	@Override
	public SellersInfo findOne(Integer sellersId) {
		// TODO Auto-generated method stub
		return this.sellersInfoMapper.findOne(sellersId);
	}
	@Override
	public void delate(Map<String, Object> param) {
		// TODO Auto-generated method stub
		this.sellersInfoMapper.delate(param);
	}

}
