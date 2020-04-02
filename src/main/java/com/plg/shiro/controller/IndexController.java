package com.plg.shiro.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.plg.shiro.entity.DataGrid;
import com.plg.shiro.entity.DataPage;
import com.plg.shiro.entity.Invitation;
import com.plg.shiro.entity.News;
import com.plg.shiro.entity.NewsCategory;
import com.plg.shiro.entity.NewsCollection;
import com.plg.shiro.entity.NewsComment;
import com.plg.shiro.entity.ScoreRules;
import com.plg.shiro.entity.UserInfo;
import com.plg.shiro.service.IInvitationService;
import com.plg.shiro.service.INewsCategoryService;
import com.plg.shiro.service.INewsCollectionService;
import com.plg.shiro.service.INewsCommentService;
import com.plg.shiro.service.INewsService;
import com.plg.shiro.service.IScoreRulesService;
import com.plg.shiro.service.IUserInfoService;
import com.plg.shiro.service.impl.UserInfoService;
import com.plg.shiro.util.Md5;
import com.plg.util.DateUtil;
import com.plg.util.Message;



@Controller

public class IndexController {
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private IScoreRulesService scoreRulesService;
	@Resource
	private INewsCommentService newsCommentService;
	@Resource
	private INewsCategoryService newsCategoryService;
	@Resource
	private INewsService newsService;
	@Resource
	private INewsCollectionService newsCollectionService;
	@Resource
	private IInvitationService invitationService;
	@RequestMapping("index")
	public String contentData(HttpServletRequest request, HttpServletResponse response) {
		
		return "/home/home";
	}
	@RequestMapping(value={"","news"})  
	public String index(HttpServletRequest request, HttpServletResponse response,Model model) {
		HttpSession session = request.getSession(true);
		UserInfo user =(UserInfo) session.getAttribute("userInfo");
		model.addAttribute("userInfo",user);
		//获取新闻分类
		Map<String,Object> param = new HashMap<>();
		param.put("begin", 0);
		param.put("rows", 30);
		List<NewsCategory> list = newsCategoryService.findAllList(param);
		model.addAttribute("categoryList",list);
		//获取热点帖子
		List<Invitation> listInvitation = invitationService.findByRate();
		if(listInvitation != null && listInvitation.size() > 0) {
			for (Invitation invitation : listInvitation) {
				if(invitation.getInvitationContent().length() > 10) {
					invitation.setInvitationContent(invitation.getInvitationContent().substring(0, 10) + "......");
				}
			}
			
		}
		
		Map<String,Object> newsParam = new HashMap<>();
		newsParam.put("begin", 0);
		newsParam.put("rows", 10);
		DataPage dataPage = this.findData(newsParam);
	    model.addAttribute("dataPage",dataPage);
	    model.addAttribute("listInvitation", listInvitation);
		return "news/news";
	}
	@RequestMapping(value= {"news/getNewsList"})  
	public ModelAndView  getNewsList(HttpServletRequest request, HttpServletResponse response,@RequestBody(required=true) Map<String,Object> map) {
		
		
		
		Map<String,Object> newsParam = new HashMap<>();
		Object beginPage = map.get("begin");
		Integer begin = 1;
		if(beginPage != null && !"".equals(beginPage + "")) {
			begin =Integer.valueOf(beginPage + "");
		}
		newsParam.put("begin", (begin -1) * 10 );
		newsParam.put("rows", 10);
		if(StringUtils.isNotBlank((String)map.get("param"))) {
			String parameter = (String)map.get("param");
			Integer catogryId = 0;
			if(parameter.contains("category")) {
				String replace = parameter.replace("category", "");
				
				catogryId = Integer.valueOf(replace);
			}
			if(catogryId != 0) {
				newsParam.put("catogryId", catogryId);
			}
		}
	
		DataPage dataPage = this.findData(newsParam);
		Map<String,Object> mapData = new HashMap<>();
		mapData.put("dataPage", dataPage);
		return new ModelAndView("news/newsTemp",mapData);
	}
	private DataPage findData(Map<String, Object> newsParam) {
		DataPage dataPage = new DataPage();
		Integer size = newsService.findSize(newsParam);
		List<News> newsList = newsService.findAllList(newsParam);
		Integer pageNo = Integer.valueOf(newsParam.get("begin") + "") +1;
		Integer totalPage = size%10 > 0 ? size/10+1 : size/10;
		Integer nextPage =  Integer.valueOf(newsParam.get("begin") + "") +2;
		Integer upPage = Integer.valueOf(newsParam.get("begin") + "");
		dataPage.setData(newsList);
		dataPage.setNextPage(nextPage);
		dataPage.setPageNo(pageNo);
		dataPage.setTotalCount(size);
		dataPage.setUpPage(upPage);
		dataPage.setTotalPage(totalPage);
		
		return dataPage;
	}
	@RequestMapping(value={"news/signup"})  
	public String signup(HttpServletRequest request, HttpServletResponse response) {
		
		return "news/signup";
	}
	@RequestMapping(value={"news/login"})  
	public String login(HttpServletRequest request, HttpServletResponse response,Model model) {
		
		return "news/login";
	}
	@RequestMapping(value={"news/manager"})  
	public String manager(HttpServletRequest request, HttpServletResponse response,Model model) {
		HttpSession session = request.getSession(true);
		UserInfo user =(UserInfo) session.getAttribute("userInfo");
		model.addAttribute("userInfo",user);
		return "news/manager";
	}
	@RequestMapping(value={"news/newsDetail"})  
	public String newsDetail(HttpServletRequest request, HttpServletResponse response,Model model) {
		String parameter = request.getParameter("id");
		News news = this.newsService.findOne(Integer.valueOf(parameter));
		//news.setClickRate(news.getClickRate() +1);
		Map<String,Object> map = new HashMap<>();
		map.put("id", news.getId());
		Integer clickRate = 1;
		if(news.getClickRate() != null) {
			clickRate = news.getClickRate() + 1;
		}
		List<Invitation> listInvitation = invitationService.findByRate();
		if(listInvitation != null && listInvitation.size() > 0) {
			for (Invitation invitation : listInvitation) {
				if(invitation.getInvitationContent().length() > 10) {
					invitation.setInvitationContent(invitation.getInvitationContent().substring(0, 10) + "......");
				}
			}
			
		}
		
		map.put("clickRate", clickRate);
		this.newsService.update(map);
		//获取评论
		Map<String,Object> mapComment = new HashMap<>();
		mapComment.put("newsId", news.getId());
		mapComment.put("report", 0);
		List<Map<String,Object>> list = new ArrayList<>();
		List<NewsComment> listComment = this.newsCommentService.selectAll(mapComment);
		for (NewsComment newsComment : listComment) {
			Map<String,Object> comment = new HashMap<>();
			comment.put("content", newsComment.getComment());
			UserInfo user = this.userInfoService.findOne(newsComment.getUserId());
			comment.put("id", newsComment.getId());
			comment.put("userName", user.getMobile());
			comment.put("createTime", newsComment.getCreateTime());
			list.add(comment);
		}
		
		HttpSession session = request.getSession(true);
		UserInfo user =(UserInfo) session.getAttribute("userInfo");
		model.addAttribute("userInfo",user);
		model.addAttribute("news",news);
		model.addAttribute("commentList",list);
		model.addAttribute("listInvitation",listInvitation);
		return "news/detail";
	}
	@RequestMapping(value={"news/invitationDetail"})  
	public String invitationDetail(HttpServletRequest request, HttpServletResponse response,Model model) {
		String parameter = request.getParameter("id");
		Invitation invitation = this.invitationService.findOne(Integer.valueOf(parameter));
		
		Integer clickRate = 1;
		if(invitation.getRate() != null) {
			clickRate = invitation.getRate() + 1;
		}
		invitation.setRate(clickRate);
		this.invitationService.update(invitation);
		HttpSession session = request.getSession(true);
		UserInfo user =(UserInfo) session.getAttribute("userInfo");
		model.addAttribute("userInfo",user);
		model.addAttribute("invitation",invitation);
		return "news/invitationDetail";
	}
	@RequestMapping(value={"news/invitationLists"})  
	public String invitationList(HttpServletRequest request, HttpServletResponse response,Model model) {
		
		//Invitation invitation = this.invitationService.findOne(Integer.valueOf(parameter));
		List<Invitation> list = this.invitationService.findAllSize();
		if(list != null && list.size() > 0) {
			for (Invitation invitation : list) {
				if(invitation.getInvitationContent().length() > 11) {
					invitation.setInvitationContent(invitation.getInvitationContent().substring(0, 10) + "......");
				}
			}
		}
		HttpSession session = request.getSession(true);
		UserInfo user =(UserInfo) session.getAttribute("userInfo");
		model.addAttribute("userInfo",user);
		model.addAttribute("invitationList",list);
		return "news/invitationList";
	}
	
	@RequestMapping(value={"news/register"})
	@ResponseBody
	public String register(HttpServletRequest request, HttpServletResponse response,@RequestBody(required=true) Map<String,Object> param) {
		
		try {
			Map<String, Object> data = new HashMap<>();
			if(StringUtils.isBlank((String)param.get("mobile"))) {
				data.put("message", Message.getMessage("E000003"));
				data.put("code", "E000003");
				JSONObject json = new JSONObject(data);
				return json.toString();
				
			}
			if(StringUtils.isBlank((String)param.get("password"))) {
				data.put("message", Message.getMessage("E000003"));
				data.put("code", "E000003");
				JSONObject json = new JSONObject(data);
				return json.toString();
			}
			UserInfo findOneByMobile = this.userInfoService.findOneByMobile((String)param.get("mobile"));
			if(findOneByMobile != null) {
				data.put("message", Message.getMessage("E000005"));
				data.put("code", "E000005");
				JSONObject json = new JSONObject(data);
				return json.toString();
			}
			UserInfo userInfo = new UserInfo();
			userInfo.setMobile((String)param.get("mobile"));
			String md5ofStrByLowerCase = Md5.getMD5ofStrByLowerCase((String)param.get("password"));
			userInfo.setPassword(md5ofStrByLowerCase);
			ScoreRules scoreRules = scoreRulesService.findOne(1);
			String ruleValue = scoreRules.getRuleValue();
			Map<String,Object> maps = (Map)JSON.parse(scoreRules.getRuleValue()); 
			Integer score = Integer.valueOf(maps.get("orignalScore") + "");
			userInfo.setScore(score);
			userInfo.setUpdateTime(new Date());
			userInfo.setCreateTime(new Date());
			userInfo.setStatus(0);
			userInfo.setGrade(0);
			this.userInfoService.insert(userInfo);
			data.put("message", Message.getMessage("S000001"));
			data.put("code", "S000001");
			JSONObject json = new JSONObject(data);
			return json.toString();
		}catch (Exception e) {
			Map<String, Object> data = new HashMap<>();
			data.put("message", Message.getMessage("E000000"));
			data.put("code", "E000000");
			JSONObject json = new JSONObject(data);
			return json.toString();
		}
	}
	@RequestMapping(value={"news/loginByMobile"})
	@ResponseBody
	public Map<String,Object> loginByMobile(HttpServletRequest request, HttpServletResponse response,@RequestBody(required=true) Map<String,Object> param,Model modelMap) {
		
		try {
			Map<String, Object> data = new HashMap<>();
			if(StringUtils.isBlank((String)param.get("mobile"))) {
				data.put("message", Message.getMessage("E000003"));
				data.put("code", "E000003");
				return data;
			}
			if(StringUtils.isBlank((String)param.get("password"))) {
				data.put("message", Message.getMessage("E000003"));
				data.put("code", "E000003");
				return data;
			}
			UserInfo userInfo = this.userInfoService.findOneByMobile((String)param.get("mobile"));
			if(userInfo.getPassword().equals(Md5.getMD5ofStrByLowerCase((String)param.get("password")))) {
				//ThreadContextHolder.getSessionContext().setAttribute(UserConext.CURRENT_MEMBER_KEY, bxxUser);
				HttpSession session = request.getSession(true);
				session.setAttribute("userInfo", userInfo);
				data.put("message", Message.getMessage("S000001"));
				data.put("code", "S000001");
				return data;
				
			}else {
				data.put("message", Message.getMessage("E000002"));
				data.put("code", "E000002");
				return data;
			}
			//删除
			
			
		}catch (Exception e) {
			Map<String, Object> data = new HashMap<>();
			data.put("message", Message.getMessage("E000000"));
			data.put("code", "E000000");
			return data;
		}
	}
	
	@RequestMapping(value={"news/getUserMessage"})
	@ResponseBody
	public Map<String,Object> getUserMessage(HttpServletRequest request, HttpServletResponse response,@RequestBody(required=true) Map<String,Object> param,Model modelMap) {
		try {
			Map<String, Object> data = new HashMap<>();
			String message = (String)param.get("param");
			
			if("message".equals(message)) {
				Map<String,Object> map =  this.getUserInfoMessage(request,modelMap);
				data.put("message", Message.getMessage("S000001"));
				data.put("result",map);
				data.put("code", "S000001");
				return data;
			}
			
			
		}catch (Exception e) {
			Map<String, Object> data = new HashMap<>();
			data.put("message", Message.getMessage("E000000"));
			data.put("code", "E000000");
			return data;
		}
		return null;
	}
	@RequestMapping(value={"news/update"})
	@ResponseBody
	public Map<String,Object> update(HttpServletRequest request, HttpServletResponse response,@RequestBody(required=true) Map<String,Object> param,Model modelMap) {
		try {
			Map<String, Object> data = new HashMap<>();
			String userName = (String)param.get("userName");
			HttpSession session = request.getSession(true);
			UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
			userInfo = this.userInfoService.findOne(userInfo.getId());
			userInfo.setUserName(userName);
			this.userInfoService.update(userInfo);
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
	@RequestMapping(value={"news/saveInvitation"})
	@ResponseBody
	public Map<String,Object> saveInvitation(HttpServletRequest request, HttpServletResponse response,@RequestBody(required=true) Map<String,Object> param,Model modelMap) {
		try {
			Map<String, Object> data = new HashMap<>();
			Object id = param.get("id");
			String invitationContent = (String)param.get("content");
			if(id !=null && !"".equals(id)) {
				Invitation invitation = this.invitationService.findOne(Integer.valueOf(id + ""));
				invitation.setInvitationContent(invitationContent);
				invitation.setUpdateTime(new Date());
				this.invitationService.update(invitation);
			}else {
				Invitation invitation = new Invitation();
				invitation.setCreateTime(new Date());
				invitation.setInvitationContent(invitationContent);
				invitation.setUpdateTime(new Date());
				HttpSession session = request.getSession(true);
				UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
				invitation.setUserId(userInfo.getId());
				this.invitationService.save(invitation);
			}
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
	@RequestMapping(value={"news/saveNewsComment"})
	@ResponseBody
	public Map<String,Object> saveNewsComment(HttpServletRequest request, HttpServletResponse response,@RequestBody(required=true) Map<String,Object> param,Model modelMap) {
		try {
			
			Map<String, Object> data = new HashMap<>();
			HttpSession session = request.getSession(true);
			UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
			if(userInfo == null) {
				data.put("message", Message.getMessage("E000006"));
				data.put("code", "E000006");
				return data;
			}
			String content = (String)param.get("content");
			Integer newsId = Integer.valueOf(param.get("newsId") + "");
			NewsComment newsComment = new NewsComment();
			newsComment.setComment(content);
			newsComment.setNewsId(newsId);
			newsComment.setCreateTime(new Date());
			newsComment.setUserId(userInfo.getId());
			newsComment.setReport(0);
			this.newsCommentService.save(newsComment);
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
	@RequestMapping(value={"news/deleteConment"})
	@ResponseBody
	public Map<String,Object> deleteConment(HttpServletRequest request, HttpServletResponse response,@RequestBody(required=true) Map<String,Object> param,Model modelMap) {
		try {
			Map<String, Object> data = new HashMap<>();
			Integer id = Integer.valueOf(param.get("id") + "");
			
			NewsComment findOne = this.newsCommentService.findOne(id);
			this.newsCommentService.delete(findOne);
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
	@RequestMapping(value={"news/deleteInvitation"})
	@ResponseBody
	public Map<String,Object> deleteInvitation(HttpServletRequest request, HttpServletResponse response,@RequestBody(required=true) Map<String,Object> param,Model modelMap) {
		try {
			Map<String, Object> data = new HashMap<>();
			Integer id = Integer.valueOf(param.get("id") + "");
			
			Invitation findOne = this.invitationService.findOne(id);
			this.invitationService.delete(findOne);
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
	
	@RequestMapping(value={"news/deleteCollection"})
	@ResponseBody
	public Map<String,Object> deleteCollection(HttpServletRequest request, HttpServletResponse response,@RequestBody(required=true) Map<String,Object> param,Model modelMap) {
		try {
			Map<String, Object> data = new HashMap<>();
			Integer id = Integer.valueOf(param.get("id") + "");
			
			NewsCollection findOne = this.newsCollectionService.findOne(id);
			this.newsCollectionService.delete(findOne);
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
	@RequestMapping(value={"news/reportContent"})
	@ResponseBody
	public Map<String,Object> reportContent(HttpServletRequest request, HttpServletResponse response,@RequestBody(required=true) Map<String,Object> param,Model modelMap) {
		try {
			Map<String, Object> data = new HashMap<>();
			Integer id = Integer.valueOf(param.get("id") + "");
			NewsComment newsComment = this.newsCommentService.findOne(id);
			newsComment.setReport(1);
			this.newsCommentService.update(newsComment);
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
	
	
	
	@RequestMapping("news/contentList")
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
		
		List<NewsComment> list =  this.newsCommentService.findAllList(param);
		List<Map<String,Object>> listResult = new ArrayList<>();
		for (NewsComment newsComment : list) {
			Map<String,Object> mapResult = new HashMap<>();
			mapResult.put("id", newsComment.getId());
			mapResult.put("createTime",  DateUtil.dateToFullStringNoSecond(newsComment.getCreateTime()));
			
			mapResult.put("newsId", newsComment.getNewsId());
			mapResult.put("comment", newsComment.getComment());
			mapResult.put("report", newsComment.getReport());
			News findOne = this.newsService.findOne(newsComment.getNewsId());
			mapResult.put("title", findOne.getNewsTitle());
			
			
			listResult.add(mapResult);
		}
		Integer count = this.newsCommentService.findSize(param);
		dataGrid.setRows(listResult);
		dataGrid.setTotal(count);
		return dataGrid;
	}
	
	@RequestMapping("news/collectionList")
	@ResponseBody
	public DataGrid  collectionList(HttpServletRequest request) {
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
	@RequestMapping("news/invitationList")
	@ResponseBody
	public DataGrid  invitationList(HttpServletRequest request) {
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
		
		List<Invitation> list =  this.invitationService.findAllList(param);
		List<Map<String,Object>> listResult = new ArrayList<>();
		for (Invitation invitation : list) {
			Map<String,Object> mapResult = new HashMap<>();
			mapResult.put("id", invitation.getId());
			mapResult.put("createTime",  DateUtil.dateToFullStringNoSecond(invitation.getCreateTime()));
			
			mapResult.put("content", invitation.getInvitationContent());
			
			
			listResult.add(mapResult);
		}
		Integer count = this.invitationService.findSize(param);
		dataGrid.setRows(listResult);
		dataGrid.setTotal(count);
		return dataGrid;
	}
	private Map<String, Object> getUserInfoMessage(HttpServletRequest request, Model modelMap) {
		HttpSession session = request.getSession(true);
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
		userInfo = this.userInfoService.findOne(userInfo.getId());
		Map<String, Object> map = new HashMap<>();
		map.put("id", userInfo.getId());
		map.put("userName", userInfo.getUserName());
		map.put("mobile", userInfo.getMobile());
		String grade = "";
		if(userInfo.getGrade() == 0) {
			grade = "普通用户";
		}else {
			grade = "发布者";
		}
		map.put("grade", grade);
		String state = "";
		if(userInfo.getStatus() == 0) {
			state = "未禁言";
		}else {
			state = "禁言";
		}
		map.put("state", state);
		map.put("score", userInfo.getScore());
		map.put("createTime", DateUtil.dateToFullStringNoSecond(userInfo.getCreateTime()));
		map.put("audienceReason", userInfo.getAudienceReason());
		return map;
		
		
	}
	public static void main(String[] args) {
		
	}

}
