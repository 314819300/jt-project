package com.jt.web.service;

import com.jt.common.po.Order;
import com.jt.common.service.HttpClientService;
import com.jt.common.util.MapperUtil;
import com.jt.common.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangning
 * @create 2021-02-09 10:48
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private HttpClientService httpClient;
	@Override
	public String saveOrder(Order order) {
		String url = "http://order.jt.com/order/create";
		String orderJSON = MapperUtil.toJSON(order);
		Map<String, String> params = new HashMap<>();
		params.put("orderJSON", orderJSON);

		String result = httpClient.doPost(url, params);
		SysResult sysResult = MapperUtil.toObject1(result, SysResult.class);
		String orderId = null;
		if(sysResult.getStatus() == 200) {
			orderId = (String) sysResult.getData();
		}
		return orderId;
	}

	@Override
	public Order findOrderById(String id) {
		String url = "http://order.jt.com/order/query/" + id;
		String orderJSON = httpClient.doGet(url);
		Order order = MapperUtil.toObject1(orderJSON, Order.class);
		return order;
	}
}
