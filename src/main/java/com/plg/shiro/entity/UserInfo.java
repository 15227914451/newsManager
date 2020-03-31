package com.plg.shiro.entity;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
	private Integer id;
	private String userName;
	private String password;
	private String mobile;
	private Integer status; //0-禁言  1 未禁言
	private Integer score;
	private Integer grade; //0-普通用户 1-发布者
	private Date createTime;
	private Date updateTime;
	private Integer audience;//0-未审批  1 -审批通过 2-审批拒绝
	private String audienceReason;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getAudience() {
		return audience;
	}
	public void setAudience(Integer audience) {
		this.audience = audience;
	}
	public String getAudienceReason() {
		return audienceReason;
	}
	public void setAudienceReason(String audienceReason) {
		this.audienceReason = audienceReason;
	}
	 
}
