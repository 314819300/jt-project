package com.jt.order.service;

import com.jt.order.pojo.Order;

/**
 * @author wangning
 * @create 2021-02-09 11:07
 */
public interface OrderService {
	String saveOrder(Order order);

	Order findOrderById(String orderId);
}
