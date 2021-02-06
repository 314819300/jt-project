package com.jt;

import com.jt.manage.config.UserConfig;
import com.jt.manage.pojo.User;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wangning
 * @create 2021-02-05 22:12
 */
public class TestConfig {

	/**
	 * 测试结果：bean默认的名字为方法名
	 */
	@Test
	public void testUserBeanDefaultName() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(UserConfig.class);
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		User user = (User)applicationContext.getBean("getUser", User.class);
		for (String s: beanDefinitionNames)
		System.out.println(s);

		System.out.println(user);
	}
}
