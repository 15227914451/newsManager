package com.plg.shiro.entity;

import java.io.Serializable;
import java.util.Date;

public class SellersInfo implements Serializable {
	private static final long serialVersionUID = 4247982114023182182L;
	
	private Integer id;
	private String sellersName;
	private Date createTime;
	private Date updateTime;
	private Integer goodsId;
	private Integer userId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSellersName() {
		return sellersName;
	}
	public void setSellersName(String sellersName) {
		this.sellersName = sellersName;
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
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	

	 

}
