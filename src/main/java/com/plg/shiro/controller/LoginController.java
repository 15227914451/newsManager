package com.plg.shiro.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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

/**
 * 登录控制器
 * 
 * @author Thinkpad
 *
 */
@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 进入登录页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/", "/login"})
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("======login=========");
		return "login";
	}

	/**
	 * 登录验证，验证成功进入首页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("用户登录成功，进入首页");
		try {
			String username = request.getParameter("loginName");
			String pwd = request.getParameter("password");
			UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
			Subject currentUser = SecurityUtils.getSubject();
			
			//没有登录的用户进行登录
			if (!currentUser.isAuthenticated()) {
				currentUser.login(token);
			}

			Locale locale = Locale.SIMPLIFIED_CHINESE;
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			String formattedDate = dateFormat.format(date);

			model.addAttribute("currentUser", username);
			model.addAttribute("serverTime", formattedDate);
		} catch (UnknownAccountException uae) {
			// 用户名未知...
			logger.error("用户不存在", uae);
			model.addAttribute("errorMsg", "用户不存在");
			return "403";
		} catch (IncorrectCredentialsException ice) {
			// 凭据不正确，例如密码不正确 ...
			logger.error("密码不正确", ice);
			model.addAttribute("errorMsg", "密码不正确");
			return "403";
		} catch (LockedAccountException lae) {
			// 用户被锁定，例如管理员把某个用户禁用...
			logger.error("用户被禁用", lae);
			model.addAttribute("errorMsg", "用户被禁用");
			return "403";
		} catch (ExcessiveAttemptsException eae) {
			// 尝试认证次数多余系统指定次数 ...
			logger.error("请求次数过多，用户被锁定", eae);
			model.addAttribute("errorMsg", "请求次数过多，用户被锁定");
			return "403";
		} catch (Exception ex) {
			logger.error("未知错误，无法完成登录", ex);
			model.addAttribute("errorMsg", "未知错误，无法完成登录");
			return "403";
		}

		return "home";
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
