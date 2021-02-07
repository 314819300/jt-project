package com.jt.web.controller;

import com.jt.common.po.User;
import com.jt.common.vo.SysResult;
import com.jt.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangning
 * @create 2021-02-06 14:45
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private JedisCluster jedisCluster;

	//实现用户页面通用跳转
	@RequestMapping("/{moduleName}")
	public String index(@PathVariable String moduleName) {
		System.out.println("moduleName = " + moduleName);
		return moduleName;
	}

	/**
	 * 实现用户退出的操作
	 * 由于有优先级的问题，不会与上面的方法冲突
	 * 1.删除cookie
	 * 2.删除redis
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();
		String token = null;
		for(Cookie cookie : cookies) {
			if("JT_TICKET".equals(cookie.getName())) {
				token = cookie.getValue();
				break;
			}
		}
		//删除redis
		jedisCluster.del(token);
		//删除cookie
		Cookie cookie = new Cookie("JT_TICKET", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);

		//执行完成后跳转到系统首页
		return "redirect:/index.html";

	}

	/**
	 *
	 */
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult saveUser(User user) {
		//底层是使用这种接收参数的，HttpServletRequest request
//		String password = request.getParameter("password");
//		int age = Integer.parseInt(request.getParameter("age"));
		try {
			userService.saveUser(user);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "用户注册失败");
	}

	/**
	 * 实现用户的登录
	 */
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult doLogin(User user, HttpServletResponse response) {
		try {
			String token = userService.findUserByUP(user);
			//判断返回值token不为空时，返回token
			if(!StringUtils.isEmpty(token)) {
				Cookie cookie = new Cookie("JT_TICKET", token);
				cookie.setMaxAge(7 * 24 * 3600);//超时时间
				//cookie的使用权限配置，一般写/
				cookie.setPath("/");
				response.addCookie(cookie);
				return SysResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"用户登陆失败");


	}
}
