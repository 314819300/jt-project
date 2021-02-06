package com.jt.service;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangning
 * @create 2021-02-05 20:07
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * 使用@Autowired注解出现警告：Field injection is not recommended
	 * 说是spring团队建议使用构造器的方式注入，使用变量依赖注入是不推荐的
	 */
//	@Autowired
	private UserMapper userMapper;
	//构造方法注入
	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	//setter注入
	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}
}
