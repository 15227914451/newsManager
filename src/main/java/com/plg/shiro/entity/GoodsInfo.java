package com.plg.shiro.entity;

import java.io.Serializable;
import java.util.Date;

public class GoodsInfo implements Serializable {
	private static final long serialVersionUID = 4247982114023182182L;
	private Integer id;
	private String goodsName; //名称
	private String officeName; //公司名称
	private String goodsDescribe;//商品描述
	private Date createTime; //创建时间
	private Date manufactureTime; //生产日期
	private Date updateTime; //修改时间
	private String qualityPeriod; //保质期
	private Integer userId;
	private String productIds; //关联的产品id 以“，”隔开
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getGoodsDescribe() {
		return goodsDescribe;
	}
	public void setGoodsDescribe(String goodsDescribe) {
		this.goodsDescribe = goodsDescribe;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getManufactureTime() {
		return manufactureTime;
	}
	public void setManufactureTime(Date manufactureTime) {
		this.manufactureTime = manufactureTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getQualityPeriod() {
		return qualityPeriod;
	}
	public void setQualityPeriod(String qualityPeriod) {
		this.qualityPeriod = qualityPeriod;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getProductIds() {
		return productIds;
	}
	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}
	
	 

}
