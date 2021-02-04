package com.jt;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;

/**
 * @author wangning
 * @create 2021-02-02 21:26
 */
public class TestFactory {

	/**
	 * 测试静态工厂，从容器中获取calendar1
	 */
	@Test
	public void textStaticFactory() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("/spring/factory.xml");
		//也可以使用
//		new AnnotationConfigApplicationContext("");
		Calendar calendar1 = (Calendar)classPathXmlApplicationContext.getBean("calendar1");
		System.out.println("当前时间1："+calendar1.getTime());
	}

	/**
	 * 测试实例工厂，从容器中获取calendar2
	 */
	@Test
	public void textInstanceFactory() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("/spring/factory.xml");
		//也可以使用
//		new AnnotationConfigApplicationContext("");
		Calendar calendar1 = (Calendar)classPathXmlApplicationContext.getBean("calendar2");
		System.out.println("当前时间2："+calendar1.getTime());
	}

	/**
	 * 测试Spring工厂，从容器中获取calendar2
	 */
	@Test
	public void textSpringFactory() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("/spring/factory.xml");
		//也可以使用
//		new AnnotationConfigApplicationContext("");
		Calendar calendar1 = (Calendar)classPathXmlApplicationContext.getBean("calendar3");
		System.out.println("当前时间3："+calendar1.getTime());
	}
}
