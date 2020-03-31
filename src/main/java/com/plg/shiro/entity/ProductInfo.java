package com.plg.shiro.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * 产品表
 * @author Administrator
 *
 */
public class ProductInfo implements Serializable{
	private static final long serialVersionUID = 4247982114023182182L;
	
	

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 产品名
	 */
	@NotEmpty(message = "产品名名不能为空")
	private String productName;
	@NotEmpty(message = "生产者id不能为空")
	private Integer userId;
	/**
	 * 产地
	 */
	private String originPlace;
	

	 private  Date createTime;
	 private  Date	plantingTime;
	 private  Date	harvestTime;
	 private Date updateTime;
	 private String productDescribe;
	// private String	describe;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getOriginPlace() {
		return originPlace;
	}
	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getPlantingTime() {
		return plantingTime;
	}
	public void setPlantingTime(Date plantingTime) {
		this.plantingTime = plantingTime;
	}
	public Date getHarvestTime() {
		return harvestTime;
	}
	public void setHarvestTime(Date harvestTime) {
		this.harvestTime = harvestTime;
	}
	/*public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}*/
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getProductDescribe() {
		return productDescribe;
	}
	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}

}
