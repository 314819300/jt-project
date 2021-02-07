package com.jt.web.service;

import com.jt.common.po.User;

/**
 * @author wangning
 * @create 2021-02-06 21:51
 */
public interface UserService {
	void saveUser(User user);

	String findUserByUP(User user);
}
