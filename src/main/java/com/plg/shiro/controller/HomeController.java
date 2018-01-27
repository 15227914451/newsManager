package com.plg.shiro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plg.shiro.service.IMenuService;
import com.plg.shiro.service.IPermissionService;
import com.plg.shiro.service.IRolePermisService;
import com.plg.shiro.service.IRoleService;
import com.plg.shiro.service.IUserRoleService;
import com.plg.shiro.service.IUserService;

/**
 * 首页资源加载
 * 
 * @author Thinkpad
 *
 */
@Controller
public class HomeController {
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Resource
	private IUserService userService;
	
	@Resource
	private IRoleService roleService;
	
	@Resource
	private IUserRoleService userRoleService;
	
	@Resource
	private IPermissionService permissionService;
	
	@Resource
	private IRolePermisService rolePermisService;
	
	@Resource
	private IMenuService menuService;
	
	
	/**
	 * 加载content页面
	 * 
	 * @return
	 */
	@RequestMapping("content/data")
	@ResponseBody
	public Map<String, Object> contentData(HttpServletRequest request) {
		logger.info("======content=data=========");
		Map<String, Object> data = new HashMap<>();
		data.put("userList", userService.selectAll());
		data.put("roleList", roleService.selectAll());
		data.put("userRoleList", userRoleService.selectAll());
		data.put("permissionList", permissionService.selectAll());
		data.put("rolePermisList", rolePermisService.selectAll());
		data.put("menuList", menuService.selectAll());
		return data;
	}
}
