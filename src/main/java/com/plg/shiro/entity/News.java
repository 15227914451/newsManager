package com.plg.shiro.entity;

import java.util.Date;

public class News {
	
	private Integer id;
	private String newsContent;
	private String newsTitle;
	private Date updateTime;
	private Date createTime;
	private Integer newsSort;
	private Integer catogryId;
	private Integer clickRate;
	private String newsAuthor;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
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
	public Integer getNewsSort() {
		return newsSort;
	}
	public void setNewsSort(Integer newsSort) {
		this.newsSort = newsSort;
	}
	public Integer getCatogryId() {
		return catogryId;
	}
	public void setCatogryId(Integer catogryId) {
		this.catogryId = catogryId;
	}
	public Integer getClickRate() {
		return clickRate;
	}
	public void setClickRate(Integer clickRate) {
		this.clickRate = clickRate;
	}
	public String getNewsAuthor() {
		return newsAuthor;
	}
	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}
	
	

}
