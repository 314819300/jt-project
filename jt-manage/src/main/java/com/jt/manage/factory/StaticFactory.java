package com.jt.manage.factory;

import java.util.Calendar;

/**
 * @author wangning
 * @create 2021-02-02 21:22
 */
public class StaticFactory {
	//必须有静态方法
	public static Calendar getInstance() {
		return Calendar.getInstance();
	}
}
