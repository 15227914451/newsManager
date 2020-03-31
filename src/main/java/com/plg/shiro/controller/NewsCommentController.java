package com.plg.shiro.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plg.shiro.entity.DataGrid;
import com.plg.shiro.entity.NewsCategory;
import com.plg.shiro.entity.NewsComment;
import com.plg.shiro.entity.UserInfo;
import com.plg.shiro.service.INewsCommentService;
import com.plg.shiro.service.IUserInfoService;
import com.plg.util.DateUtil;
import com.plg.util.Message;

@Controller
@RequestMapping("newsComment")
public class NewsCommentController {
	@Resource
	private INewsCommentService newsCommentService; 
	@Resource
	private IUserInfoService userInfoService; 
	@RequestMapping("list")
	@ResponseBody
	public DataGrid  list(HttpServletRequest request) {
		DataGrid dataGrid = new DataGrid();
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		
		Map<String,Object> param = new HashMap<>();
		param.put("begin", rows * (page - 1));
		param.put("rows", rows);
		
		List<NewsComment> list =  this.newsCommentService.findAllList(param);
		List<Map<String,Object>> listResult = new ArrayList<>();
		for (NewsComment newsComment : list) {
			Map<String,Object> mapResult = new HashMap<>();
			mapResult.put("comment", newsComment.getComment());
			mapResult.put("createTime",  DateUtil.dateToFullStringNoSecond(newsComment.getCreateTime()));
			UserInfo userInfo = userInfoService.findOne(newsComment.getUserId());
			if(userInfo != null) {
				
				mapResult.put("mobile", userInfo.getMobile());
				mapResult.put("userId", userInfo.getId());
			}
			mapResult.put("report", newsComment.getReport());
			mapResult.put("news", newsComment.getNewsId());
			mapResult.put("id", newsComment.getId());
			
			listResult.add(mapResult);
		}
		Integer count = this.newsCommentService.findSize(param);
		dataGrid.setRows(listResult);
		dataGrid.setTotal(count);
		return dataGrid;
	}
	@RequestMapping("audience")
	@ResponseBody
	public Map<String,Object>  audience(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param) {
		
		Integer audience = Integer.valueOf(param.get("audience") + "");//1-通过  2 拒绝
		Integer commentId = Integer.valueOf(param.get("commentId") + "");
		String dealRason = param.get("dealRason") + "";
		NewsComment newsComment= newsCommentService.findOne(commentId);
		if(audience == 1) {
			newsComment.setReport(0);
			this.newsCommentService.update(newsComment);
		}else {
			UserInfo userInfo = this.userInfoService.findOne(newsComment.getUserId());
			userInfo.setStatus(0);
			this.userInfoService.update(userInfo);
			this.newsCommentService.delete(newsComment);
			
		}
		
			
		Map<String, Object> data = new HashMap<>();
		data.put("message", Message.getMessage("S000001"));
		data.put("code", "S000001");
		return data;
	}
	
}
