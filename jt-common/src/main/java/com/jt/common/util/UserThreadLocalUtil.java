package com.jt.common.util;

import com.jt.common.po.User;

/**
 * @author wangning
 * @create 2021-02-08 21:40
 */
public class UserThreadLocalUtil {
	private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

	public static void set(User user) {
		threadLocal.set(user);
	}

	public static User get() {
		return threadLocal.get();
	}

	//为了防止内存泄漏，添加remove方法
	public static void remove() {
		threadLocal.remove();
	}
}
