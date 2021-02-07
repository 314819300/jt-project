package com.jt.sso.service;

import com.jt.sso.pojo.User;

/**
 * @author wangning
 * @create 2021-02-06 17:18
 */
public interface UserService {
	boolean findCheckUser(String param, Integer type);

	void saveUser(User user);

	String findUserByUP(User user);

	String queryToken(String token);
}
