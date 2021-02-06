package com.jt.manage.controller.web;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.manage.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangning
 * @create 2021-02-06 16:46
 */
@RestController
public class JSONPController {
	/**
	 * 参数定义
	 * 1.返回值写法
	 * 对象代替：JSONPObject
	 * 	函数名称（JSON）
	 */
	@RequestMapping("/web/testJSONP")
	public JSONPObject jsonp(String callback) {
		User user = new User();
		user.setId(101);
		user.setName("tomcat猫！！！");
		JSONPObject jsonpObject = new JSONPObject(callback, user);
		return  jsonpObject;

	}
}
