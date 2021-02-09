package com.jt.order.service;

import com.jt.order.mapper.OrderItemMapper;
import com.jt.order.mapper.OrderMapper;
import com.jt.order.mapper.OrderShippingMapper;
import com.jt.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangning
 * @create 2021-02-09 11:07
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderShippingMapper orderShippingMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;
}
