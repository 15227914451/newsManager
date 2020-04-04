package com.plg.shiro.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.plg.shiro.entity.ScoreRules;
import com.plg.shiro.entity.UserInfo;
import com.plg.shiro.service.IScoreRulesService;
import com.plg.shiro.service.IUserInfoService;
import com.plg.shiro.service.impl.ScoreRulesService;
import com.plg.shiro.service.impl.UserInfoService;
import com.plg.util.DateUtil;

@Controller
@Component
public class TimeSchemerController {
	@Autowired
	private IScoreRulesService scoreRulesService;
	@Autowired
	private IUserInfoService userInfoService;
	@Scheduled(cron = "0 0 10 * * ?")
	public void modifyIntegral() {
		try {
		ScoreRules scoreRules = scoreRulesService.findOne(1);
		Map<String,Object> maps = (Map)JSON.parse(scoreRules.getRuleValue());
		Integer score =Integer.valueOf( maps.get("scoles") + "");
		Integer deductionScore = Integer.valueOf(maps.get("deductionScore") + "");
		Integer days = Integer.valueOf(maps.get("days") + "");
		
		//获取用户
		List<UserInfo> list = userInfoService.findAll();
		for (UserInfo userInfo : list) {
			Integer status = userInfo.getStatus();
			//未禁言
			if(status == 0) {
				if(userInfo.getSendScoreTime() == null) {
					userInfo.setScore(userInfo.getScore() + score);
					userInfo.setSendScoreTime(new Date());
					this.userInfoService.update(userInfo);
				}else {
					int daysBetween = DateUtil.daysBetween( userInfo.getSendScoreTime(),new Date());
					System.out.println(daysBetween);
					if(daysBetween > days) {
						userInfo.setScore(userInfo.getScore() + score);
						userInfo.setSendScoreTime(new Date());
						this.userInfoService.update(userInfo);
						
					}
				}
			}
		}
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
