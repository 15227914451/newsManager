package com.plg.shiro.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plg.shiro.entity.DataGrid;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.entity.SellersInfo;
import com.plg.shiro.entity.UserInfo;
import com.plg.shiro.service.IUserInfoService;
import com.plg.util.DateUtil;
import com.plg.util.Message;

@Controller
@RequestMapping("userInfo")
public class UserController {
	
	@Resource
	private IUserInfoService userInfoService;
	
	@RequestMapping("list")
	@ResponseBody
	public DataGrid  list(HttpServletRequest request) {
		DataGrid dataGrid = new DataGrid();
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		String userName = request.getParameter("goodsName");
		String mobile = request.getParameter("mobile");
		String id = request.getParameter("id");
		Map<String,Object> param = new HashMap<>();
		param.put("begin", rows * (page - 1));
		param.put("rows", rows);
		if(StringUtils.isNotBlank(userName)) {
			param.put("userName", userName);
		}
		if(StringUtils.isNotBlank(mobile)) {
			param.put("mobile", mobile);
		}
		if(StringUtils.isNotBlank(id)) {
			param.put("id", id);
		}
		List<UserInfo> list =  this.userInfoService.findAllList(param);
		List<Map<String,Object>> listResult = new ArrayList<>();
		for (UserInfo userInfo : list) {
			Map<String,Object> mapResult = new HashMap<>();
			mapResult.put("userName", userInfo.getUserName());
			mapResult.put("createTime",  DateUtil.dateToFullStringNoSecond(userInfo.getCreateTime()));
			mapResult.put("updateTime", DateUtil.dateToFullStringNoSecond(userInfo.getUpdateTime()));
			mapResult.put("score", userInfo.getScore());
			mapResult.put("grade", userInfo.getGrade());
			mapResult.put("mobile", userInfo.getMobile());
			mapResult.put("status", userInfo.getStatus());
			mapResult.put("id", userInfo.getId());
			
			listResult.add(mapResult);
		}
		Integer count = this.userInfoService.findSize(param);
		dataGrid.setRows(listResult);
		dataGrid.setTotal(count);
		return dataGrid;
	}
	
	@RequestMapping("audience")
	@ResponseBody
	public Map<String,Object>  audience(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param) {
		
		Integer audience = Integer.valueOf(param.get("audience") + "");//1-通过  2 拒绝
		Integer id = Integer.valueOf(param.get("userInfoId") + "");
		
		
		UserInfo userInfo = this.userInfoService.findOne(Integer.valueOf(id));
		if(Integer.valueOf(audience) == 1) {
			userInfo.setGrade(1);
		}
		userInfo.setAudience(Integer.valueOf(audience));
		userInfo.setUpdateTime(new Date());
		this.userInfoService.update(userInfo);
			
		Map<String, Object> data = new HashMap<>();
		data.put("message", Message.getMessage("S000001"));
		data.put("code", "S000001");
		return data;
	}
	
	/**
	 * 修改
	 * @param request
	 * @param param
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Map<String,Object>  update(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param) {
		try {
			UserInfo userInfo = userInfoService.findOne(Integer.valueOf(param.get("id") + ""));
			userInfo.setScore(Integer.valueOf(param.get("score") + ""));
			this.userInfoService.update(userInfo);
			Map<String, Object> data = new HashMap<>();
			data.put("message", Message.getMessage("S000001"));
			data.put("code", "S000001");
			return data;
		}catch (Exception e) {
			Map<String, Object> data = new HashMap<>();
			data.put("message", Message.getMessage("E000000"));
			data.put("code", "E000000");
			return data;
		}
	}

	
}
