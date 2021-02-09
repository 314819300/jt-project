package com.jt.order.controller;

import com.jt.order.pojo.Order;
import com.jt.order.service.OrderService;
import com.jt.order.util.MapperUtil;
import com.jt.order.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangning
 * @create 2021-02-09 11:05
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/create")
	public SysResult saveOrder(String orderJSON) {
		try {
			Order order = MapperUtil.toObject1(orderJSON, Order.class);
			String orderId = orderService.saveOrder(order);
			if(!StringUtils.isEmpty(orderId)) {
				return SysResult.oK(orderId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "订单入库失败");
	}
	/**
	 * 根据orderId查询Order对象
	 */
	@RequestMapping("/query/{orderId}")
	public Order findOrderById(@PathVariable String orderId) {
		Order order = orderService.findOrderById(orderId);
		return order;

	}
}
