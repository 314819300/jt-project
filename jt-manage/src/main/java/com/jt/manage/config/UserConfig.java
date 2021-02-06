package com.jt.manage.config;

import com.jt.manage.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangning
 * @create 2021-02-05 22:10
 */
@Configuration
public class UserConfig {

	@Bean
	public User getUser() {
		User user = new User();
		user.setId(10);
		user.setAge(101);
		user.setName("11");
		user.setSex("女");
		return user;
	}
}
