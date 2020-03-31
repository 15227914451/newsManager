package com.plg.shiro.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.plg.shiro.dao.ScoreRulesMapper;
import com.plg.shiro.entity.ScoreRules;
import com.plg.shiro.service.IScoreRulesService;
@Service
public class ScoreRulesService implements IScoreRulesService{
	@Resource
	private ScoreRulesMapper scoreRulesMapper;
	@Override
	public ScoreRules findOne(Integer id) {
		// TODO Auto-generated method stub
		return scoreRulesMapper.findOne(id);
	}
	@Override
	public void update(Map<String, Object> map) {
		
		scoreRulesMapper.update(map);
	}

}
