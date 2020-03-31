package com.plg.shiro.entity;

import java.util.Date;

public class Invitation {
	private Integer id;
	private String invitationContent;
	private Date updateTime;
	private Date createTime;
	private Integer userId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInvitationContent() {
		return invitationContent;
	}
	public void setInvitationContent(String invitationContent) {
		this.invitationContent = invitationContent;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	  
	

}
