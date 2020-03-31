package com.plg.shiro.entity;

import java.io.Serializable;
import java.util.Date;

public class ScoreRules implements Serializable{
	private Integer id;
	private String ruleValue;
	private Integer groupValue;
	private Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getRuleValue() {
		return ruleValue;
	}
	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
	public Integer getGroupValue() {
		return groupValue;
	}
	public void setGroupValue(Integer groupValue) {
		this.groupValue = groupValue;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}