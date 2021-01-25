package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangning
 * @create 2021-01-23 11:36
 */
@Controller
public class IndexController {

	//跳转页面 返回值必须是string类型
	//如果是json 返回值类型一般为对象@ResponseBody
	@RequestMapping("/index")
	public String index() {
		return "index";//返回页面逻辑名称
	}

	/**
	 * 如果写成下面的样子感觉都是重复的，一点技术含量都没有
	 * 如果用户跳转页面时,如果跳转的路径类似,则可以使用通用的页面跳转技术.如果页面跳转使用传统方式开发,
	 * 每有一个url,在Controller中必须写一个与之对应的@RequestMapping注解标识的方法!!!
	 *
	 * 思考：能够动态的从url中获取路径，则获取跳转页面的名称，然后直接传给要返回的页面
	 * 实现：REST结构REST-FULL
	 * 要求：
	 * 1.参数必需拼接在url中，必须以/分割
	 * 2.url中的参数必须使用{}包裹
	 * 3.为了转化参数，在方法中添加一个名称一致的参数，同时添加转化的注解@PathVariable
	 */
	@RequestMapping("/page/{moduleName}")
	public String item(@PathVariable String moduleName) {
//		System.out.println("moduleName = " + moduleName);
		return moduleName;
	}

//	@RequestMapping("/page/{aaa}")
//	public String item1(@PathVariable(value="aaa") String moduleName) {
//		System.out.println("moduleName11 = " + moduleName);
//		return moduleName;
//	}
//	@RequestMapping("/page/item-add")
//	public String add() {
//		return "item-add";
//	}
//	@RequestMapping("/page/item-list")
//	public String list() {
//		return "item-list";
//	}
//	@RequestMapping("/page/item-param-list")
//	public String paramList() {
//		return "item-param-list";
//	}

}
