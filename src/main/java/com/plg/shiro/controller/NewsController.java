package com.plg.shiro.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.plg.shiro.entity.DataGrid;
import com.plg.shiro.entity.GoodsInfo;
import com.plg.shiro.entity.News;
import com.plg.shiro.entity.NewsCategory;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.entity.ProductInfo;
import com.plg.shiro.service.INewsCategoryService;
import com.plg.shiro.service.INewsService;
import com.plg.util.DateUtil;
import com.plg.util.Message;
import com.plg.util.RandomCodeUtil;

@Controller
@RequestMapping("newsManager")
public class NewsController {
	@Resource
	private INewsService newsService;
	@Resource
	private INewsCategoryService newsCategoryService;
	
	@RequestMapping("list")
	
	@ResponseBody
	public DataGrid  list(HttpServletRequest request,HttpServletResponse response,Model model) {
		//OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		DataGrid dataGrid = new DataGrid();
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		String newsName = request.getParameter("newsName");
		
		Map<String,Object> param = new HashMap<>();
		param.put("begin", rows * (page - 1));
		param.put("rows", rows);
		if(StringUtils.isNotBlank(newsName)) {
			param.put("newsTitle", newsName);
		}
		
		
		List<News> list = this.newsService.findAllList(param);
		List<Map<String,Object>> listResult = new ArrayList<>();
		
		for (News news : list) {
			Map<String,Object> mapResult = new HashMap<>();
			mapResult.put("newsContent", news.getNewsContent());
			NewsCategory newsCategory = this.newsCategoryService.findOne(news.getCatogryId());
			if(newsCategory != null) {
				
				mapResult.put("category", newsCategory.getCategoryName());
			}
			mapResult.put("newsTitle", news.getNewsTitle());
			mapResult.put("clickRate", news.getClickRate());
			mapResult.put("newsAuthor", news.getNewsAuthor());
			mapResult.put("content", news.getNewsContent());
			String createTime = "";
			if(news.getCreateTime() != null) {
				createTime = DateUtil.dateToFullStringNoSecond(news.getCreateTime());
			}
			
			mapResult.put("createTime", createTime);
			String updateTime = "";
			if(news.getUpdateTime() != null) {
				updateTime = DateUtil.dateToFullStringNoSecond(news.getUpdateTime());
			}
			mapResult.put("updateTime", updateTime);
			
			
			mapResult.put("id", news.getId());
			
			
			listResult.add(mapResult);
		}
		Integer count = this.newsService.findSize(param);
		//model.addAttribute("userName", omUser.getUserName());
		//this.productInfoService.
		
		//list.add(map);
		dataGrid.setTotal(count);
		dataGrid.setRows(listResult);
		return dataGrid;
	}
	@RequestMapping(value="upload_img")
	@ResponseBody
	public void uploadImg(@RequestParam("imgFile") MultipartFile imgFile,HttpServletResponse response) throws IOException{
		JSONObject jb=new JSONObject();
		PrintWriter out = response.getWriter();
        jb.put("error", 0);
        //文件保存目录路径

        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");

        
        try {

            if (imgFile!=null) {
                    
                	String originalFilename = imgFile.getOriginalFilename();
                	String ex =originalFilename.substring(originalFilename.lastIndexOf(".") + 1,originalFilename.length());
                	if(!extMap.get("image").contains(ex)) {
                		jb.put("error", 1);
                        jb.put("message", "文件格式错误！");
                        //return jb.toJSONString();
                        out.println(jb.toJSONString());
                	}
                	String fileName =RandomCodeUtil.createSerial(6) + "." + ex;
                	String folder = "E:\\workspece\\managerAi\\springmvc-mybatis-shiro\\src\\main\\webapp\\static\\images/" + "newImage"+DateUtil.getCurrentDate("yyyyMMdd");
                	String folder1 = "/static/images/newImage"+DateUtil.getCurrentDate("yyyyMMdd");
                	File file = new File(folder +"/" + fileName );
                	if(!file.exists()){
                		file.mkdirs();
                    }
                	imgFile.transferTo(file);
                	
                    jb.put("error", "0");
                    jb.put("message", "上传成功！");
                    jb.put("url",folder1 +"/" + fileName);
                   //return jb.toJSONString();
                    out.println(jb.toJSONString());
            }else {
            	 jb.put("error", 1);
                 jb.put("message", "文件为空！");
                 //return jb.toJSONString();
                 out.println(jb.toJSONString());
            }
        } catch (Exception e1) {
            jb.put("error", 1);
            jb.put("message", e1.getMessage());
            //return jb.toJSONString();
            out.println(jb.toJSONString());
        }
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Map<String,Object>  save(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param) {
		try {
			Object object = param.get("id");
			News news;
			if(object != null && !"".equals(object)) {
				news = this.newsService.findOne(Integer.valueOf(object + ""));
				this.update(news,param);
			}else {
				news = new News();
			
				String newsAuthor =(String) param.get("newsAuthor");
				if(StringUtils.isNoneBlank(newsAuthor)) {
					news.setNewsAuthor(newsAuthor);
				}
				Integer newsCategory =Integer.valueOf(param.get("newsCategory") + "");
				if(newsCategory != null) {
					news.setCatogryId(newsCategory);;
				}
				String newsTitle =(String) param.get("newsTitle");
				if(StringUtils.isNoneBlank(newsTitle)) {
					news.setNewsTitle(newsTitle);
				}
				String content =(String) param.get("content");
				if(StringUtils.isNoneBlank(content)) {
					news.setNewsContent(content);
				}
				
				news.setCreateTime(new Date());
				news.setUpdateTime(new Date());
				
					
			   this.newsService.save(news);
			}
			/*productInfo.setUserId(omUser.getUserId());
			int insert = this.productInfoService.insert(productInfo);
			*/
		
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
			this.newsService.delete(param);
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
	private void update(News news, Map<String, Object> param) {
		if(param.get("newsCategory") != null && !"".equals(param.get("newsCategory") + "")) {
			Integer newsCategory =Integer.valueOf(param.get("newsCategory") + "");
			if(newsCategory != null) {
				param.put("catogryId",newsCategory);
			}
		}
		param.put("updateTime", new Date());
		this.newsService.update(param);
	}
}
