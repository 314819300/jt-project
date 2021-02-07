package com.jt.sso.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;
import com.jt.sso.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangning
 * @create 2021-02-06 14:45
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 实现用户信息的校验
	 */
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public JSONPObject findCheckUser(String callback, @PathVariable String param, @PathVariable Integer type) {
		//获取数据库返回值结果
		boolean flag = userService.findCheckUser(param, type);

		//封装返回值数据
		SysResult result = SysResult.oK(flag);
		return new JSONPObject(callback, result);

	}

	/**
	 *
	 */
	@RequestMapping("/register")
	@ResponseBody
	public SysResult saveUser(User user) {
		try {
			userService.saveUser(user);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "用户新增失败");
	}
	/**
	 * 1.当用户点击登陆按钮时,携带username和password实现登录操作.将请求发送给jt-web.
	 * 2.Jt-web接收用户请求.之后将参数进行封装.通过httpClient中的Post请求将数据发送给jt-sso.单点登录系统.
	 * 3.Jt-sso接收jt-web发送的用户登陆数据.通过数据库查询校验用户数据是否正确!
	 * 如果用户名和密码不正确,直接201报错返回
	 * 如果用户名和密码正确.
	 * 1.经过加密的算法生成秘钥TOKEN
	 * 2.将用户信息转化为JSON数据.
	 * 4.将秘钥token和JSON保存到redis中.
	 * 5.将后台生成的TOKEN返回给jt-web
	 * 6.jt-web接收token数据后,通过request对象操作Cookie.将用户token信息保存到Cookie中(默认7天).
	 * 7.如果之前的一系列操作执行正确.如果用户做敏感操作时,则通过token检查redis中是否有数据即可.该操作之后完善!!!
	 */

	/**
	 * 实现用户信息的登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public SysResult findUserByUP(User user) {
		try {
			String token = userService.findUserByUP(user);
			if (!StringUtils.isEmpty(token)) {
				return SysResult.oK(token);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "用户登陆失败");
	}

	/**
	 * 实现用户登录的回显
	 */
	@RequestMapping("/query/{token}")
	@ResponseBody
	public JSONPObject queryToken(String callback, @PathVariable String token) {
		String result = userService.queryToken(token);
		SysResult sysResult = SysResult.oK(result);
		return new JSONPObject(callback, sysResult);
	}

}
