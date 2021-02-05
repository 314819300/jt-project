package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangning
 * @create 2021-02-05 12:18
 */
@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		return "你好，SpringBoot";
	}
}
