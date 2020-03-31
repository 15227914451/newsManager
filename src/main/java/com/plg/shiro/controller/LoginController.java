package com.plg.shiro.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plg.shiro.entity.OmMenu;
import com.plg.shiro.service.IMenuService;
import com.plg.util.Message;

/**
 * 登录控制器
 * 
 * @author Thinkpad
 *
 */
@Controller
@RequestMapping(value="/home")
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Resource
	private IMenuService menuService;
	/**
	 * 进入登录页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("======login=========");
		return "/home/login";
	}

	/**
	 * 登录验证，验证成功进入首页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="/login",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String,Object> home(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("用户登录成功，进入首页");
		Map <String,Object> map = new HashMap<String, Object>();
		try {
			String username = request.getParameter("loginName");
			String pwd = request.getParameter("password");
			List<OmMenu> menuList = menuService.queryUseMenu(1);
			UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
			Subject currentUser = SecurityUtils.getSubject();
			
			//没有登录的用户进行登录
			//if (!currentUser.isAuthenticated()) {
				currentUser.login(token);
			//}

			Locale locale = Locale.SIMPLIFIED_CHINESE;
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			String formattedDate = dateFormat.format(date);

			model.addAttribute("currentUser", username);
			model.addAttribute("serverTime", formattedDate);
		} catch (UnknownAccountException uae) {
			map.put("code", "E000001");
			map.put("message", Message.getMessage("E000001"));
			//model.addAttribute("errorMsg", "用户不存在");
			return map;
		} catch (IncorrectCredentialsException ice) {
			// 凭据不正确，例如密码不正确 ...
			//logger.error("密码不正确", ice);
			map.put("code", "E000002");
			map.put("message", Message.getMessage("E000002"));
			return map;
		} 
		map.put("code", "S000001");
		map.put("message", Message.getMessage("S000001"));
		return map;
	}

	/**
	 * 登出
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/logout")
	public void onLogout(HttpServletRequest request, HttpServletResponse response) {
		logger.info("退出登录");
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
	}

	/**
	 * 错误页面
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/403")
	public String error(HttpServletRequest request, HttpServletResponse response) {
		logger.info("========403=======");
		return "403";
	}
}
