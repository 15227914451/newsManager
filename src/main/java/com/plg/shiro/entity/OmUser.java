package com.plg.shiro.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户（om_user）
 * 
 * @author Thinkpad
 *
 */
public class OmUser implements Serializable {
	private static final long serialVersionUID = 4247982114023182182L;

	/**
	 * 用户id
	 */
	private Integer userId;

	/**
	 * 用户姓名
	 */
	@NotEmpty(message = "用户名不能为空")
	private String userName;

	/**
	 * 登录密码
	 */
	@NotEmpty(message = "密码不能为空")
	private String password;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 用户状态：1、启用，2、禁用
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
}