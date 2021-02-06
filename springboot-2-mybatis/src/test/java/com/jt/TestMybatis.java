package com.jt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * @author wangning
 * @create 2021-02-05 17:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMybatis {
	@Autowired
	private User user;

	@Autowired
	private JedisCluster jedisCluster;
	/**
	 * 该测试类相当于直接获取了spring容器对象
	 */
	@Autowired
	private UserMapper userMapper;

	@Test
	public void testFind() {
		List<User> userList = userMapper.findAll();
		System.out.println("userList = " + userList);
	}

	/**
	 * eq 等于，gt 大于，lt小于
	 */
	@Test
	public void testFinds() {
		//第一种方法，这种方法还是有点繁琐，想要查询就需要先创建对象，然后在传给QueryWrapper
//		User user = new User().setAge(3000);
//		QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
		//第二种方法，
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//		queryWrapper.eq("age", 3000);//查询年龄等于3000
		queryWrapper.gt("age", 0).lt("age", 150);
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println("userList = " + userList);
	}

	@Test
	public void testInsert() {
		User user = new User().setName("流浪地球").setAge(2).setSex("男");
		int rows = userMapper.insert(user);
		System.out.println("rows = " + rows);
	}

	@Test
	public void testDelete() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name", "马化腾");
		userMapper.delete(queryWrapper);
	}

	/**
	 * 使用mybatis-plus修改操作
	 * 参数介绍：userMapper.update(entity,updateWrapper)
	 * sql：update 表名 set 字段名=值 where id = 100
	 * 	1.entity 修改后的值使用entity封装
	 * 	2.updateWrapper 定义where条件的值
	 * 	案例：将id=53的数据修改为汤圆，年龄20，性别女
	 */
	@Test
	public void testUpdate() {
		User user = new User().setName("汤圆").setAge(20).setSex("女");
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", 53);
		int update = userMapper.update(user, updateWrapper);
		System.out.println("修改成功！！！！！！！！");
	}

	@Test
	public void getUser() {
		System.out.println(user);
	}

	@Test
	public void testRedis() {
		jedisCluster.set("1810", "springboot整合redis成功");
		System.out.println(jedisCluster.get("1810"));
	}

}
