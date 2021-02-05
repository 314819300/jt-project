package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangning
 * @create 2021-02-04 15:15
 */
@Controller
public class IndexController {

	/**
	 * 实现jt前台的首页的实现
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
