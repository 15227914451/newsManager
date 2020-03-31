package com.plg.shiro.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plg.shiro.entity.DataGrid;
import com.plg.shiro.entity.GoodsInfo;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.entity.ProductInfo;
import com.plg.shiro.service.IGoodsInfoService;
import com.plg.shiro.service.IProductInfoService;
import com.plg.shiro.service.IUserService;
import com.plg.util.DateUtil;
import com.plg.util.Message;

@Controller
@RequestMapping("goods")
public class GoodsInfoController {
	private static Logger logger = LoggerFactory.getLogger(GoodsInfoController.class);
	@Resource
	private IProductInfoService productInfoService;
	@Resource
	private IGoodsInfoService goodsInfoService;
	@Resource
	private IUserService userService;
	@RequestMapping("list")
	@ResponseBody
	public DataGrid  list(HttpServletRequest request) {
		DataGrid dataGrid = new DataGrid();
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		
		String goodsName = request.getParameter("goodsName");
		Map<String,Object> param = new HashMap<>();
		param.put("begin", rows * (page - 1));
		param.put("rows", rows);
		if(StringUtils.isNotBlank(goodsName)) {
			param.put("goodsName", goodsName);
		}
		//区分查询全部和自己
		String state = request.getParameter("state");
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		if(state != null && !"".equals(state)) {
			if(Integer.valueOf(state) != 1) {
				param.put("userId", omUser.getUserId());
			}
		}else {
			param.put("userId", omUser.getUserId());
		}
		List<GoodsInfo> list =  this.goodsInfoService.findAllList(param);
		List<Map<String,Object>> listResult = new ArrayList<>();
		for (GoodsInfo goodsInfo : list) {
			Map<String,Object> mapResult = new HashMap<>();
			mapResult.put("goodsName", goodsInfo.getGoodsName());
			mapResult.put("id", goodsInfo.getId());
			mapResult.put("qualityPeriod", goodsInfo.getQualityPeriod());
			mapResult.put("goodsDescribe", goodsInfo.getGoodsDescribe());
			String manufactureTime = "";
			if(goodsInfo.getManufactureTime() != null) {
				manufactureTime = DateUtil.dateToFullStringNoSecond(goodsInfo.getManufactureTime());
			}
			mapResult.put("manufactureTime", manufactureTime);
			//OmUser omUser = this.userService.selectByPrimaryKey(goodsInfo.getUserId());
			mapResult.put("userName", omUser.getUserName());
			mapResult.put("officeName", goodsInfo.getOfficeName());
			if(goodsInfo.getProductIds() == null || "".equals(goodsInfo.getProductIds())) {
				mapResult.put("state", 1);
			}else {
				mapResult.put("state", 0);
			}
			listResult.add(mapResult);
		}
		Integer count = this.goodsInfoService.findSize(param);
		dataGrid.setRows(listResult);
		dataGrid.setTotal(count);
		return dataGrid;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Map<String,Object>  save(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param) {
		try {
			GoodsInfo goodsInfo = new GoodsInfo();
			if(StringUtils.isNotBlank((String)param.get("goodsDescribe"))) {
				goodsInfo.setGoodsDescribe((String)param.get("goodsDescribe"));
			}
			if(StringUtils.isNotBlank((String)param.get("goodsName"))) {
				goodsInfo.setGoodsName((String)param.get("goodsName"));
			}
			if(StringUtils.isNotBlank((String)param.get("officeName"))) {
				goodsInfo.setOfficeName((String)param.get("officeName"));
			}
			if(StringUtils.isNotBlank((String)param.get("manufactureTime"))) {
				Date date = DateUtil.stringToSimpleDateTwo((String)param.get("manufactureTime"));
				goodsInfo.setManufactureTime(date);
			}
			if(StringUtils.isNotBlank((String)param.get("qualityPeriod"))) {
				//Date date = DateUtil.stringToSimpleDateTwo((String)param.get("harvestTime"));
				goodsInfo.setQualityPeriod((String)param.get("qualityPeriod"));
			}
			goodsInfo.setCreateTime(new Date());
			goodsInfo.setUpdateTime(new Date());
			OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
			goodsInfo.setUserId(omUser.getUserId());
			int insert = this.goodsInfoService.insert(goodsInfo);
			
		
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
			if(StringUtils.isNotBlank((String)param.get("manufactureTime"))) {
				Date date = DateUtil.stringToSimpleDateTwo((String)param.get("manufactureTime"));
				param.put("manufactureTime",date);
			}
			param.put("updateTime", new Date());
			this.goodsInfoService.update(param);
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
			this.goodsInfoService.delate(param);
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
	@RequestMapping("addProductIds")
	@ResponseBody
	public Map<String,Object> addProductIds(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param){
		try {
			Map<String, Object> data = new HashMap<>();
			if(param.get("id")!=null && "".equals(param.get("id") + "")) {
				data.put("message", Message.getMessage("E000003"));
				data.put("code", "E000003");
				return data;
			}
			String id = param.get("id") + "";
			List<Integer> list = (List<Integer>)param.get("productIds");
			GoodsInfo goodsInfo = this.goodsInfoService.findOne(Integer.valueOf(id));
			String productIds = goodsInfo.getProductIds();
			Set<Integer> strSet = null;
			if(productIds != null && !"".equals(productIds)) {
				List<Integer> listIds =  Arrays.asList(productIds.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
				strSet = new HashSet<>(listIds);
				for (Integer productId : list) {
					strSet.add(productId);
				}
			}else {
				strSet = new HashSet<>(list);
			}
			String ids = StringUtils.join(strSet, ',');
			Map<String,Object> updatePara = new HashMap<>();
			updatePara.put("id", id);
			updatePara.put("productIds", ids);
			updatePara.put("updateTime", new Date());
			this.goodsInfoService.update(updatePara);
			data.put("message", Message.getMessage("S000001"));
			data.put("code", "S000001");
			return data;
		}catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> data = new HashMap<>();
			data.put("message", Message.getMessage("E000000"));
			data.put("code", "E000000");
			return data;
		}
	}
	@RequestMapping("deleteProductIds")
	@ResponseBody
	public Map<String,Object> deleteProductIds(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param){
		try {
			Map<String, Object> data = new HashMap<>();
			if(param.get("id")!=null && "".equals(param.get("id") + "")) {
				data.put("message", Message.getMessage("E000003"));
				data.put("code", "E000003");
				return data;
			}
			String id = param.get("id") + "";
			List<Integer> list = (List<Integer>)param.get("productIds");
			GoodsInfo goodsInfo = this.goodsInfoService.findOne(Integer.valueOf(id));
			String productIds = goodsInfo.getProductIds();
			Set<String> strSet = null;
			if(productIds != null) {
				List<String> listId = Arrays.asList(productIds.split(","));
				List<String> listIds = new ArrayList(listId);
		        Iterator<String> iterator = listIds.iterator();
		        while(iterator.hasNext()){
		            String productId = iterator.next();
		            Integer proId = Integer.valueOf(productId);
		            for (Integer deleteId : list) {
						if(proId.compareTo(deleteId) == 0) {
							iterator.remove();
							break;
						}
					}
		        }
		        Map<String,Object> updatePara = new HashMap<>();
				updatePara.put("id", id);
				if(listIds !=null && listIds.size() >0) {
					updatePara.put("productIds", StringUtils.join(listIds, ','));
				}else {
					updatePara.put("productIds", "");
					
				}
				updatePara.put("updateTime", new Date());
				this.goodsInfoService.update(updatePara);
			}
			
			data.put("message", Message.getMessage("S000001"));
			data.put("code", "S000001");
			return data;
		}catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> data = new HashMap<>();
			data.put("message", Message.getMessage("E000000"));
			data.put("code", "E000000");
			return data;
		}
	}
}
