package com.plg.shiro.entity;

import java.util.List;

public class DataPage {
	private Integer pageNo;//当前页
	private Integer upPage;//上一页
	private Integer totalPage ;//总页数
	private Integer nextPage;//下一页
	private Integer totalCount;//总数据
	private List data;
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getUpPage() {
		return upPage;
	}
	public void setUpPage(Integer upPage) {
		this.upPage = upPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
	
}
