package com.jt.manage.factory;

import org.springframework.beans.factory.FactoryBean;

import java.util.Calendar;

/**
 * @author wangning
 * @create 2021-02-02 21:41
 */
public class SpringFactory implements FactoryBean<Calendar> {
	@Override
	public Calendar getObject() throws Exception {
		//如果是接口。我们就需要使用代理创建对象
		return Calendar.getInstance();
	}

	@Override
	public Class<?> getObjectType() {
		return Calendar.class;//改配置不能为空
	}

	@Override
	public boolean isSingleton() {
		return true;//true表示单例对象
	}
}
