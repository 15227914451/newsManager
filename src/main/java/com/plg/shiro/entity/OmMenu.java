package com.plg.shiro.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单（om_menu）
 * 
 * @author Thinkpad
 *
 */
public class OmMenu implements Serializable {
	private static final long serialVersionUID = -4748685888032457706L;

	/**
	 * 菜单ID
	 */
	private Integer menuId;

	/**
	 * 上级菜单ID
	 */
	private Integer parentId;

	/**
	 * 菜单文本
	 */
	private String menuText;

	/**
	 * 菜单url
	 */
	private String menuUrl;

	/**
	 * 权限ID
	 */
	private Integer permisId;

	/**
	 * 启用标示，1-启用；2-禁用
	 */
	private Integer enableFlag;

	/**
	 * 菜单顺序。通常一级菜单才需要设置
	 * 
	 */
	private Integer menuOrder;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getMenuText() {
		return menuText;
	}

	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getPermisId() {
		return permisId;
	}

	public void setPermisId(Integer permisId) {
		this.permisId = permisId;
	}

	public Integer getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(Integer enableFlag) {
		this.enableFlag = enableFlag;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
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