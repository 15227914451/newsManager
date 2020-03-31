package com.plg.shiro.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("product")
public class ProductInfoController {
	private static Logger logger = LoggerFactory.getLogger(ProductInfoController.class);

	@Resource
	private IProductInfoService productInfoService;
	@Resource
	private IGoodsInfoService goodsInfoService;
	@Resource
	private IUserService userService;
	@RequestMapping("save")
	@ResponseBody
	public Map<String,Object>  save(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param) {
		try {
			ProductInfo productInfo = new ProductInfo();
			if(StringUtils.isNotBlank((String)param.get("originPlace"))) {
				productInfo.setOriginPlace((String)param.get("originPlace"));
			}
			if(StringUtils.isNotBlank((String)param.get("productName"))) {
				productInfo.setProductName((String)param.get("productName"));
			}
			if(StringUtils.isNotBlank((String)param.get("productDescribe"))) {
				productInfo.setProductDescribe((String)param.get("productDescribe"));
			}
			if(StringUtils.isNotBlank((String)param.get("plantingTime"))) {
				Date date = DateUtil.stringToSimpleDateTwo((String)param.get("plantingTime"));
				productInfo.setPlantingTime(date);
			}
			if(StringUtils.isNotBlank((String)param.get("harvestTime"))) {
				Date date = DateUtil.stringToSimpleDateTwo((String)param.get("harvestTime"));
				productInfo.setHarvestTime(date);
			}
			productInfo.setCreateTime(new Date());
			productInfo.setUpdateTime(new Date());
			OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
			productInfo.setUserId(omUser.getUserId());
			int insert = this.productInfoService.insert(productInfo);
			
		
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
	@RequestMapping("list")
	@ResponseBody
	public DataGrid  productInfoList(HttpServletRequest request,HttpServletResponse response,Model model) {
		//OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		DataGrid dataGrid = new DataGrid();
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		String productName = request.getParameter("productName");
		Map<String,Object> param = new HashMap<>();
		param.put("begin", rows * (page - 1));
		param.put("rows", rows);
		if(StringUtils.isNotBlank(productName)) {
			param.put("productName", productName);
		}
		List<ProductInfo> list = this.productInfoService.findAllList(param);
		List<Map<String,Object>> listResult = new ArrayList<>();
		for (ProductInfo productInfo : list) {
			Map<String,Object> mapResult = new HashMap<>();
			mapResult.put("productName", productInfo.getProductName());
			mapResult.put("originPlace", productInfo.getOriginPlace());
			String plantingTime = "";
			if(productInfo.getPlantingTime() != null) {
				plantingTime = DateUtil.dateToFullStringNoSecond(productInfo.getPlantingTime());
			}
			mapResult.put("plantingTime", plantingTime);
			String harvestTime = "";
			if(productInfo.getHarvestTime() != null) {
				harvestTime = DateUtil.dateToFullStringNoSecond(productInfo.getHarvestTime());
			}
			mapResult.put("harvestTime", harvestTime);

			OmUser omUser = this.userService.selectByPrimaryKey(productInfo.getUserId());
			mapResult.put("userName", omUser.getUserName());
			mapResult.put("id", productInfo.getId());
			mapResult.put("productDescribe", productInfo.getProductDescribe());
			listResult.add(mapResult);
		}
		Integer count = this.productInfoService.findSize(param);
		//model.addAttribute("userName", omUser.getUserName());
		//this.productInfoService.
		
		//list.add(map);
		dataGrid.setTotal(count);
		dataGrid.setRows(listResult);
		return dataGrid;
	}
	/**
	 * 关联产品查询
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("listProduct")
	@ResponseBody
	public DataGrid  listProduct(HttpServletRequest request,HttpServletResponse response,Model model) {
		//OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		DataGrid dataGrid = new DataGrid();
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		String productName = request.getParameter("productName");
		String goodsId = request.getParameter("goodsId");
		Map<String,Object> param = new HashMap<>();
		param.put("begin", rows * (page - 1));
		param.put("rows", rows);
		if(StringUtils.isNotBlank(productName)) {
			param.put("productName", productName);
		}
		
		
		List<ProductInfo> list = this.productInfoService.findAllList(param);
		List<Map<String,Object>> listResult = new ArrayList<>();
		GoodsInfo goodsInfo = goodsInfoService.findOne(Integer.valueOf(goodsId));
		for (ProductInfo productInfo : list) {
			Map<String,Object> mapResult = new HashMap<>();
			mapResult.put("productName", productInfo.getProductName());
			mapResult.put("originPlace", productInfo.getOriginPlace());
			String plantingTime = "";
			if(productInfo.getPlantingTime() != null) {
				plantingTime = DateUtil.dateToFullStringNoSecond(productInfo.getPlantingTime());
			}
			
			mapResult.put("plantingTime", plantingTime);
			String harvestTime = "";
			if(productInfo.getHarvestTime() != null) {
				harvestTime = DateUtil.dateToFullStringNoSecond(productInfo.getHarvestTime());
			}
			mapResult.put("harvestTime", harvestTime);
			
			OmUser omUser = this.userService.selectByPrimaryKey(productInfo.getUserId());
			mapResult.put("userName", omUser.getUserName());
			mapResult.put("id", productInfo.getId());
			mapResult.put("productDescribe", productInfo.getProductDescribe());
			String productIds = goodsInfo.getProductIds();
			boolean check = false;
			if(StringUtils.isNotBlank(productIds)) {
				List<String> listIds = Arrays.asList(productIds.split(","));
				for (String string : listIds) {
					if(Integer.valueOf(string) == productInfo.getId()) {
						check = true;
						break;
					}
				}
			}
			mapResult.put("checked", check);
			listResult.add(mapResult);
		}
		Integer count = this.productInfoService.findSize(param);
		//model.addAttribute("userName", omUser.getUserName());
		//this.productInfoService.
		
		//list.add(map);
		dataGrid.setTotal(count);
		dataGrid.setRows(listResult);
		return dataGrid;
	}
	/**
	 * 已关联产品查询
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("listProductAlread")
	@ResponseBody
	public DataGrid  listProductAlread(HttpServletRequest request,HttpServletResponse response,Model model) {
		//OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		DataGrid dataGrid = new DataGrid();
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		String productName = request.getParameter("productName");
		String goodsId = request.getParameter("goodsId");
		Map<String,Object> param = new HashMap<>();
		param.put("begin", rows * (page - 1));
		param.put("rows", rows);
		if(StringUtils.isNotBlank(productName)) {
			param.put("productName", productName);
		}
		
		
		GoodsInfo goodsInfo = this.goodsInfoService.findOne(Integer.valueOf(goodsId));
		String productIds = goodsInfo.getProductIds();
		Integer count = 0;
		List<Map<String,Object>> listResult = new ArrayList<>();
		if(StringUtils.isNotBlank(productIds)) {
			param.put("productIds", productIds);
			List<ProductInfo> list = this.productInfoService.findByProductIds(param);
			
			for (ProductInfo productInfo : list) {
				Map<String,Object> mapResult = new HashMap<>();
				mapResult.put("productName", productInfo.getProductName());
				mapResult.put("originPlace", productInfo.getOriginPlace());
				String plantingTime = "";
				if(productInfo.getPlantingTime() != null) {
					plantingTime = DateUtil.dateToFullStringNoSecond(productInfo.getPlantingTime());
				}
				
				mapResult.put("plantingTime", plantingTime);
				String harvestTime = "";
				if(productInfo.getHarvestTime() != null) {
					harvestTime = DateUtil.dateToFullStringNoSecond(productInfo.getHarvestTime());
				}
				mapResult.put("harvestTime", harvestTime);
				
				OmUser omUser = this.userService.selectByPrimaryKey(productInfo.getUserId());
				mapResult.put("userName", omUser.getUserName());
				mapResult.put("id", productInfo.getId());
				mapResult.put("productDescribe", productInfo.getProductDescribe());
			
				listResult.add(mapResult);
			}
			count = this.productInfoService.findSizeByProductIds(param);
		}
		//model.addAttribute("userName", omUser.getUserName());
		//this.productInfoService.
		
		//list.add(map);
		dataGrid.setTotal(count);
		dataGrid.setRows(listResult);
		return dataGrid;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Map<String,Object>  update(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param) {
		try {
			if(StringUtils.isNotBlank((String)param.get("plantingTime"))) {
				Date date = DateUtil.stringToSimpleDateTwo((String)param.get("plantingTime"));
				param.put("plantingTime",date);
			}
			if(StringUtils.isNotBlank((String)param.get("harvestTime"))) {
				Date date = DateUtil.stringToSimpleDateTwo((String)param.get("harvestTime"));
				param.put("harvestTime",date);
			}
			param.put("updateTime", new Date());
			this.productInfoService.update(param);
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
			this.productInfoService.delete(param);
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
