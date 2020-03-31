package com.plg.shiro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plg.shiro.entity.DataGrid;
import com.plg.shiro.entity.OmUser;

@Controller
@RequestMapping("business")
public class gatewayController {
	@RequestMapping("index")
	public String contentData(HttpServletRequest request, HttpServletResponse response,Model model) {
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		model.addAttribute("userName", omUser.getUserName());
		return "/business/index";
	}
	@RequestMapping("product1")
	public String productInfo(HttpServletRequest request, HttpServletResponse response,Model model) {
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		model.addAttribute("userName", omUser.getUserName());
		return "/business/product";
	}
	@RequestMapping("goods")
	public String goodsInfo(HttpServletRequest request, HttpServletResponse response,Model model) {
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		model.addAttribute("userName", omUser.getUserName());
		return "/business/goods";
	}
	@RequestMapping("sellers")
	public String sellersInfo(HttpServletRequest request, HttpServletResponse response,Model model) {
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		model.addAttribute("userName", omUser.getUserName());
		return "/business/sellers";
	}
	
	@RequestMapping("userInfo")
	public String userInfo(HttpServletRequest request, HttpServletResponse response,Model model) {
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		model.addAttribute("userName", omUser.getUserName());
		return "/business/userInfo";
	}
	@RequestMapping("newsCategory")
	public String newsCategory(HttpServletRequest request, HttpServletResponse response,Model model) {
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		model.addAttribute("userName", omUser.getUserName());
		return "/business/newsCategory";
	}
	@RequestMapping("newsComment")
	public String newsComment(HttpServletRequest request, HttpServletResponse response,Model model) {
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		model.addAttribute("userName", omUser.getUserName());
		return "/business/comment";
	}
	@RequestMapping("scoreRules")
	public String scoreRules(HttpServletRequest request, HttpServletResponse response,Model model) {
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		model.addAttribute("userName", omUser.getUserName());
		return "/business/scoreRules";
	}
	@RequestMapping("newsManage")
	public String newsManage(HttpServletRequest request, HttpServletResponse response,Model model) {
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		model.addAttribute("userName", omUser.getUserName());
		return "/business/news";
	}
	
}
