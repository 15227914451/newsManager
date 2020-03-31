package com.plg.shiro.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.plg.shiro.entity.ScoreRules;
import com.plg.shiro.service.IScoreRulesService;
import com.plg.shiro.service.impl.ScoreRulesService;
import com.plg.util.DateUtil;
import com.plg.util.Message;

@Controller
@RequestMapping("scoreRules")
public class ScoreRulesController {
	@Resource
	private IScoreRulesService scoreRulesService;
	
	@RequestMapping("getRules")
	@ResponseBody
	public Map<String,Object> getRules(HttpServletRequest request){
		try {
			
			ScoreRules  scoreRules = this.scoreRulesService.findOne(1);
			Map<String,Object> data = new HashMap<>();
			Map<String,Object> maps = new HashMap<>();
			if(scoreRules != null) {
				if(StringUtils.isNoneBlank(scoreRules.getRuleValue())) {
					
					maps = (Map)JSON.parse(scoreRules.getRuleValue()); 
				}
			}
			

			data.put("message", Message.getMessage("S000001"));
			data.put("code", "S000001");
			data.put("data", maps);
			return data;
		}catch (Exception e) {
			Map<String, Object> data = new HashMap<>();
			data.put("message", Message.getMessage("E000000"));
			data.put("code", "E000000");
			return data;
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Map<String,Object>  update(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param) {
		try {
			
			Map<String, Object> map = new HashMap<>();
			JSONObject json = new JSONObject(param);
			map.put("ruleValue",json.toString());
			map.put("id", 1);
			map.put("updateTime", new Date());
			this.scoreRulesService.update(map);
			Map<String, Object> data = new HashMap<>();
			data.put("message", Message.getMessage("S000001"));
			data.put("code", "S000001");
			return data;
		}catch (Exception e) {
			Map<String, Object> data = new HashMap<>();
			data.put("message", Message.getMessage("E000000"));
			data.put("code", "E000000");
			return data;
		}
	}

}
