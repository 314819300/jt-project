package com.jt.manage.controller;

import com.jt.manage.pojo.User;
import com.jt.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * @author wangning
 * @create 2021-01-21 20:47
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	//实现测试数据查询
	@RequestMapping("/findAll")
	public String findAll(Model model) {
		List<User> userList = userService.findAll();
		//request域
		model.addAttribute("userList",userList);
		return "userList";//返回页面逻辑名称
	}
//	public ModelAndView findAll() {
//		List<User> userList = userService.findAll();
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("userList");
//		modelAndView.addObject("userList", userList);
//		return modelAndView;
//	}
}
