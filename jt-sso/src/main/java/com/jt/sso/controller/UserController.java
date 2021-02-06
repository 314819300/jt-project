package com.jt.sso.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.sso.service.UserService;
import com.jt.sso.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

}
