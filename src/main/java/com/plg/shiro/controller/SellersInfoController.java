package com.plg.shiro.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plg.shiro.entity.DataGrid;
import com.plg.shiro.entity.GoodsInfo;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.entity.ProductInfo;
import com.plg.shiro.entity.SellersInfo;
import com.plg.shiro.service.IGoodsInfoService;
import com.plg.shiro.service.IProductInfoService;
import com.plg.shiro.service.ISellersInfoService;
import com.plg.shiro.service.IUserService;
import com.plg.shiro.service.impl.ProductInfoService;
import com.plg.util.DateUtil;
import com.plg.util.Message;

@Controller
@RequestMapping("sellers")
public class SellersInfoController {
	@Resource
	private ISellersInfoService sellersInfoService;
	@Resource
	private IGoodsInfoService goodsInfoService;
	@Resource
	private IProductInfoService productInfoService;
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
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		param.put("userId", omUser.getUserId());
		List<SellersInfo> list =  this.sellersInfoService.findAllList(param);
		List<Map<String,Object>> listResult = new ArrayList<>();
		for (SellersInfo sellersInfo : list) {
			Map<String,Object> mapResult = new HashMap<>();
			mapResult.put("goodsName", sellersInfo.getSellersName());
			mapResult.put("id", sellersInfo.getId());
			
			mapResult.put("userName", omUser.getUserName());
			listResult.add(mapResult);
		}
		Integer count = this.sellersInfoService.findSize(param);
		dataGrid.setRows(listResult);
		dataGrid.setTotal(count);
		return dataGrid;
	}
	@RequestMapping("addsellers")
	@ResponseBody
	public Map<String,Object>  addsellers(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param) {
		try {
			List<Integer> list = (List<Integer>)param.get("ids");
			if(list != null && list.size() > 0) {
				for (Integer goodsId : list) {
					SellersInfo sellersInfo = new SellersInfo();
					GoodsInfo goodsInfo = goodsInfoService.findOne(goodsId);
					sellersInfo.setCreateTime(new Date());
					sellersInfo.setGoodsId(goodsId);
					sellersInfo.setUpdateTime(new Date());
					sellersInfo.setSellersName(goodsInfo.getGoodsName());
					OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
					sellersInfo.setUserId(omUser.getUserId());
					this.sellersInfoService.insert(sellersInfo);
				}
			}
			
			OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
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
	@RequestMapping("getGoods")
	@ResponseBody
	public Map<String,Object>  getGoods(HttpServletRequest request,@RequestBody(required=true) Map<String,Object> param) {
		try {
			Integer goodsId = Integer.valueOf(param.get("goodsId") + "");
			SellersInfo sellersInfo = this.sellersInfoService.findOne(goodsId);
			GoodsInfo goodsInfo = this.goodsInfoService.findOne(sellersInfo.getGoodsId());
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
			OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
			mapResult.put("userName", omUser.getUserName());
			mapResult.put("officeName", goodsInfo.getOfficeName());
			
			
			
			Map<String, Object> data = new HashMap<>();
			data.put("message", Message.getMessage("S000001"));
			data.put("code", "S000001");
			data.put("goodsInfo", mapResult);
			
			return data;
		}catch (Exception e) {
			Map<String, Object> data = new HashMap<>();
			data.put("message", Message.getMessage("E000000"));
			data.put("code", "E000000");
			
			return data;
		}
	}
	//listProduct
	
	@RequestMapping("listProduct")
	@ResponseBody
	public DataGrid  listProduct(HttpServletRequest request,HttpServletResponse response,Model model) {
		//OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		DataGrid dataGrid = new DataGrid();
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		String sellersId = request.getParameter("sellersId");
		Map<String,Object> param = new HashMap<>();
		param.put("begin", rows * (page - 1));
		param.put("rows", rows);
		SellersInfo sellerInfo =this.sellersInfoService.findOne(Integer.valueOf(sellersId));
		//List<ProductInfo> list = this.productInfoService.findAllList(param);
		Integer count = 0;
		List<Map<String,Object>> listResult = new ArrayList<>();
		GoodsInfo goodsInfo = this.goodsInfoService.findOne(sellerInfo.getGoodsId());
		if(goodsInfo.getProductIds() != null && !"".equals(goodsInfo.getProductIds())) {
			param.put("productIds", goodsInfo.getProductIds());
			List<ProductInfo> list = this.productInfoService.findByProductIds(param);
			if(list != null && list.size() > 0) {
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
				
			}
			count = this.productInfoService.findSize(param);
		}
		//model.addAttribute("userName", omUser.getUserName());
		//this.productInfoService.
		
		//list.add(map);
		dataGrid.setTotal(count);
		dataGrid.setRows(listResult);
		return dataGrid;
	}
	//delete
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
			this.sellersInfoService.delate(param);
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
