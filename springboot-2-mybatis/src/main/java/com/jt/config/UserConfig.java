package com.jt.config;

import com.jt.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangning
 * @create 2021-02-05 21:50
 */
//标识配置类
@Configuration
public class UserConfig {

	/**
	 * 通过spring容器管理user对象
	 * bean的id默认为方法的名字
	 * 但是当类的名字是以两个或以上的大写字母开头的话，bean的名字会与类名保持一致
	 */
	@Bean
	public User getUser() {
		return new User().setId(100)
				.setName("配置类")
				.setAge(101)
				.setSex("男");
	}
}
