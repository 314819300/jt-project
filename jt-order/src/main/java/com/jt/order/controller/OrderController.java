package com.jt.order.controller;

import com.jt.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangning
 * @create 2021-02-09 11:05
 */
@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/test")
	public String test() {
		return "订单数据测试成功";
	}
}
