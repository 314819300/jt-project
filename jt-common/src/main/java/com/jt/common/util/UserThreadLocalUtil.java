package com.jt.common.util;

import com.jt.common.po.User;

import java.util.Map;

/**
 * @author wangning
 * @create 2021-02-08 21:40
 */
public class UserThreadLocalUtil {
	//如果想要多个对象存取，考虑Map<String, Object>使用map集合，key不能重复
	private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

	public static void set(User user) {
		threadLocal.set(user);
	}

	public static User get() {
		return threadLocal.get();
	}

	//为了防止内存泄漏，添加remove方法，为什么要写remove方法呢
	//因为GC不会回收这个值
	public static void remove() {
		threadLocal.remove();
	}
}
