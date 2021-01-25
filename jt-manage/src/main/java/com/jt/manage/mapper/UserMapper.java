package com.jt.manage.mapper;

import com.jt.manage.pojo.User;

import java.util.List;

/**
 * @author wangning
 * @create 2021-01-21 20:44
 */
public interface UserMapper {

	List<User> findAll();
}
