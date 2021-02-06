package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangning
 * @create 2021-02-05 20:11
 */
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/findAll")
	public List<User> findAll() {
		return userService.findAll();

	}
}
