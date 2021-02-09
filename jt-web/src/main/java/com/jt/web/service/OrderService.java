package com.jt.web.service;

import com.jt.common.po.Order;

/**
 * @author wangning
 * @create 2021-02-09 10:47
 */
public interface OrderService {

	String saveOrder(Order order);

	Order findOrderById(String id);
}
