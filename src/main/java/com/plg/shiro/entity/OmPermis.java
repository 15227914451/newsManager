package com.plg.shiro.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限（om_permis）
 * 
 * @author Thinkpad
 *
 */
public class OmPermis implements Serializable {
	private static final long serialVersionUID = -2762166067056298183L;

	/**
	 * 权限ID
	 */
	private Integer permisId;

	/**
	 * 权限名称
	 */
	private String permisName;

	/**
	 * 菜单类型：1-菜单权限；2-功能权限
	 */
	private Integer menuPermisFlag;

	/**
	 * 权限code
	 */
	private String permisCode;

	/**
	 * 权限顺序, 通常一级权限才需要设置
	 */
	private Integer permisOrder;

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

	public Integer getPermisId() {
		return permisId;
	}

	public void setPermisId(Integer permisId) {
		this.permisId = permisId;
	}

	public String getPermisName() {
		return permisName;
	}

	public void setPermisName(String permisName) {
		this.permisName = permisName;
	}

	public Integer getMenuPermisFlag() {
		return menuPermisFlag;
	}

	public void setMenuPermisFlag(Integer menuPermisFlag) {
		this.menuPermisFlag = menuPermisFlag;
	}

	public String getPermisCode() {
		return permisCode;
	}

	public void setPermisCode(String permisCode) {
		this.permisCode = permisCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPermisOrder() {
		return permisOrder;
	}

	public void setPermisOrder(Integer permisOrder) {
		this.permisOrder = permisOrder;
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