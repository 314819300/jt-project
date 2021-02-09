package com.jt.order.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.jt.order.service.OrderService;

//准备订单定时任务
@Component
public class OrderQuartz extends QuartzJobBean{
	
	@Autowired
	private OrderService orderService;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		orderService.updateOrderStatus();
		System.out.println("定时任务执行");
	}
}
