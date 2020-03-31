package com.plg.shiro.dao;

import java.util.Map;

import com.plg.shiro.entity.ScoreRules;

public interface ScoreRulesMapper {

	ScoreRules findOne(Integer id);

	void update(Map<String, Object> map);

}
