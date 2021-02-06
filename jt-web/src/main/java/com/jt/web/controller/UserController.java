package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangning
 * @create 2021-02-06 14:45
 */
@Controller
@RequestMapping("/user")
public class UserController {
	//实现用户页面通用跳转
	@RequestMapping("/{moduleName}")
	public String index(@PathVariable String moduleName) {
		System.out.println("moduleName = " + moduleName);
		return moduleName;
	}
}
