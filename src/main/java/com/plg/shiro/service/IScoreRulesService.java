package com.plg.shiro.service;

import java.util.Map;

import com.plg.shiro.entity.ScoreRules;

public interface IScoreRulesService {

	ScoreRules findOne(Integer group);

	void update(Map<String, Object> map);

}
