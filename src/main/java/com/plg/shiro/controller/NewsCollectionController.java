package com.plg.shiro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plg.shiro.entity.DataGrid;
import com.plg.shiro.entity.News;
import com.plg.shiro.entity.NewsComment;
import com.plg.shiro.entity.UserInfo;
import com.plg.shiro.entity.NewsCollection;
import com.plg.shiro.service.INewsCollectionService;
import com.plg.shiro.service.INewsService;
import com.plg.shiro.service.IUserInfoService;
import com.plg.util.DateUtil;

@Controller
@RequestMapping("news")
public class NewsCollectionController {
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private INewsCollectionService newsCollectionService;
	@Resource
	private INewsService newsService;
	@RequestMapping("list")
	@ResponseBody
	public DataGrid  contentList(HttpServletRequest request) {
		DataGrid dataGrid = new DataGrid();
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		HttpSession session = request.getSession(true);
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
		//String newsName = request.getParameter("newsName");
		Map<String,Object> param = new HashMap<>();
		param.put("begin", rows * (page - 1));
		param.put("rows", rows);
		param.put("userId", userInfo.getId());
		
		List<NewsCollection> list =  this.newsCollectionService.findAllList(param);
		List<Map<String,Object>> listResult = new ArrayList<>();
		for (NewsCollection newsCollection : list) {
			Map<String,Object> mapResult = new HashMap<>();
			mapResult.put("id", newsCollection.getId());
			mapResult.put("createTime",  DateUtil.dateToFullStringNoSecond(newsCollection.getCreateTime()));
			
			mapResult.put("newsId", newsCollection.getNewsId());
			News news = newsService.findOne(newsCollection.getNewsId());
			
			mapResult.put("title", news.getNewsTitle());
			
			
			listResult.add(mapResult);
		}
		Integer count = this.newsCollectionService.findSize(param);
		dataGrid.setRows(listResult);
		dataGrid.setTotal(count);
		return dataGrid;
	}
}
