package com.jt.order.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.order.mapper.OrderItemMapper;
import com.jt.order.mapper.OrderMapper;
import com.jt.order.mapper.OrderShippingMapper;
import com.jt.order.pojo.Order;
import com.jt.order.pojo.OrderItem;
import com.jt.order.pojo.OrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

	/**
	 * 一次入库三张表数据
	 * @param order 订单对象
	 * @return 返回订单orderId
	 */
	@Override
	@Transactional
	public String saveOrder(Order order) {
		//防止拼接字符串时进行加法操作，所以把“”放在最前面
		String orderId = "" + order.getUserId() + System.currentTimeMillis();
		Date date = new Date();
		order.setOrderId(orderId);
		order.setStatus(1);	//设置未付款状态
		order.setCreated(date);
		order.setUpdated(date);
		//入库订单表
		orderMapper.insert(order);
		System.out.println("订单表入库成功");
		//订单物流入库
		OrderShipping orderShipping = order.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingMapper.insert(orderShipping);
		System.out.println("订单物流入库成功");
		//入库订单商品s
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrderId(orderId);
			orderItem.setCreated(date);
			orderItem.setUpdated(date);
			orderItemMapper.insert(orderItem);
		}
		System.out.println("订单商品入库成功");
		return orderId;
	}

	@Override
	public Order findOrderById(String orderId) {
		Order order = orderMapper.selectById(orderId);
		OrderShipping orderShipping = orderShippingMapper.selectById(orderId);
		QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("order_id", orderId);
		List<OrderItem> orderItems = orderItemMapper.selectList(queryWrapper);
		order.setOrderShipping(orderShipping);
		order.setOrderItems(orderItems);

		return order;
	}

	/**
	 * 定时关闭超时的任务
	 * 当前时间-创建时间>30分钟 1-未支付
	 * 当前时间 - 30分钟 > 创建时间
	 */
	@Override
	public void updateOrderStatus() {
		//设定30分钟超时
		Calendar calendar = Calendar.getInstance();//获取的是当前时间
		calendar.add(Calendar.MINUTE, -30);
		Date date = calendar.getTime();
		Order tempOrder = new Order();
		tempOrder.setStatus(6);
		tempOrder.setUpdated(new Date());
		UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
//		当前时间-创建时间>30分钟 1-未支付
		updateWrapper.eq("status", 1).lt("created", date);
		orderMapper.update(tempOrder, updateWrapper);

	}
}
