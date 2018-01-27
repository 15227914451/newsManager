package com.plg.shiro.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色-权限关联（role_permis�?
 * 
 * @author Thinkpad
 *
 */
public class RolePermis implements Serializable {
	private static final long serialVersionUID = -4441069826456569112L;

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 角色ID
	 */
	private Integer roleId;

	/**
	 * 权限ID
	 */
	private Integer permisId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermisId() {
		return permisId;
	}

	public void setPermisId(Integer permisId) {
		this.permisId = permisId;
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
