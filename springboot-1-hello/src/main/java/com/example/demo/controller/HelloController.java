package com.example.demo.controller;

import com.example.demo.pojo.Cat;
import com.example.demo.pojo.Dog;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangning
 * @create 2021-02-05 12:18
 */
@RestController
public class HelloController {

	@Autowired
	private User user;

	@Autowired
	private Dog dog;

	@RequestMapping("/hello")
	public String hello() {
		return "你好，SpringBoot";
	}

	@RequestMapping("/getUser")
	public User getUser() {
		System.out.println("你好");
		return user;
	}

	@RequestMapping("/getDog")
	public Dog getDog() {
		return dog;
	}
	@RequestMapping("/getCat")
	public Cat getCat() {
		Cat cat = new Cat();
		cat.setId(1).setAge(12).setName("mini");
		return cat;
	}
}
