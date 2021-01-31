package com.jt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangning
 * @create 2021-01-31 14:42
 */
public class TestJSON {

	@Test
	public void testObjectToJSON () throws IOException {
		User user = new User();
		user.setId(1);
		user.setName("redis");
		user.setAge(22);
		ObjectMapper objectMapper = new ObjectMapper();
		//对象转json串
		String json = objectMapper.writeValueAsString(user);
		System.out.println("string = " + json);

		//将要转化为对象src
		//valueType 表示转化的数据类型
		User user1 = objectMapper.readValue(json, User.class);
		System.out.println("user1 = " + user1);


	}

	@Test
	public void listToJSON() throws Exception {
		User user = new User();
		user.setId(1);
		user.setName("redis");
		user.setAge(22);
		User user1 = new User();
		user1.setId(2);
		user1.setName("redis测试");
		user1.setAge(55);
		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(user1);
		ObjectMapper objectMapper = new ObjectMapper();
		String listJSON = objectMapper.writeValueAsString(userList);
		System.out.println("listJSON = " + listJSON);

		//将listJSON转化为List<User>
		//这种写法会出现警告，如果公司要求比较严格，那样就可以使用第二种
		//为什么警告呢，因为他只能帮助你解析list集合的类型，但是不能帮你解决其中的泛型的类型
		//List<>尖括号中写什么都是正确的,不会进行检查，此处的警告只是为了告诉你这个
		//第一种
		List<User> list = objectMapper.readValue(listJSON, userList.getClass());
		System.out.println("list = " + list);

		//第二种
		User[] users = objectMapper.readValue(listJSON, User[].class);
		System.out.println(Arrays.asList(users));
	}
}
