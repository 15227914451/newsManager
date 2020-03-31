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
import com.plg.shiro.entity.NewsCategory;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.entity.ProductInfo;
import com.plg.shiro.entity.UserInfo;
import com.plg.shiro.service.INewsCategoryService;
import com.plg.util.DateUtil;
import com.plg.util.Message;

@Controller
@RequestMapping("category")
public class NewsCategoryController {
	@Resource
	private INewsCategoryService newsCategoryService;
	
	@RequestMapping("list")
	@ResponseBody
	public DataGrid  list(HttpServletRequest request) {
		DataGrid dataGrid = new DataGrid();
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		String categoryName = request.getParameter("categoryName");
		Map<String,Object> param = new HashMap<>();
		param.put("begin", rows * (page - 1));
		param.put("rows", rows);
		if(StringUtils.isNotBlank(categoryName)) {
			param.put("categoryName", categoryName);
		}
		
		List<NewsCategory> list =  this.newsCategoryService.findAllList(param);
		List<Map<String,Object>> listResult = new ArrayList<>();
		for (NewsCategory newsCategory : list) {
			Map<String,Object> mapResult = new HashMap<>();
			mapResult.put("categoryName", newsCategory.getCategoryName());
			mapResult.put("createTime",  DateUtil.dateToFullStringNoSecond(newsCategory.getCreateTime()));
			
			mapResult.put("id", newsCategory.getId());
			
			listResult.add(mapResult);
		}
		Integer count = this.newsCategoryService.findSize(param);
		dataGrid.setRows(listResult);
		dataGrid.setTotal(count);
		return dataGrid;
	}
	@RequestMapping("save")
	@ResponseBody
	public Map<String,Object>  save(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param) {
		try {
			NewsCategory newsCategory = new NewsCategory();
			if(StringUtils.isNotBlank((String)param.get("categoryName"))) {
				newsCategory.setCategoryName((String)param.get("categoryName"));
			}
			
			newsCategory.setCreateTime(new Date());
			newsCategory.setUpdateTime(new Date());
			
			int insert = this.newsCategoryService.insert(newsCategory);
			
		
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
	
	@RequestMapping("update")
	@ResponseBody
	public Map<String,Object>  update(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param) {
		try {
			
			param.put("updateTime", new Date());
			this.newsCategoryService.update(param);
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
	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Object> delete(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param){
		try {
			Map<String, Object> data = new HashMap<>();
			if(param.get("id")!=null && "".equals(param.get("id") + "")) {
				data.put("message", Message.getMessage("E000003"));
				data.put("code", "E000003");
				return data;
			}
			
			//删除
			this.newsCategoryService.delete(param);
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
	
	@RequestMapping("allList")
	@ResponseBody
	public List<Map<String,Object>> getAllList(HttpServletRequest request) {
		List<NewsCategory> list =  this.newsCategoryService.getAll();
		List<Map<String,Object>> listResult = new ArrayList<>();
		for (NewsCategory newsCategory : list) {
			Map<String,Object> mapResult = new HashMap<>();
			mapResult.put("text", newsCategory.getCategoryName());
			mapResult.put("createTime",  DateUtil.dateToFullStringNoSecond(newsCategory.getCreateTime()));
			
			mapResult.put("id", newsCategory.getId());
			
			listResult.add(mapResult);
		}
		/*Map<String, Object> data = new HashMap<>();
		data.put("data", listResult);
		data.put("message", Message.getMessage("S000001"));
		data.put("code", "S000001");*/
		return listResult;
	}
}
