package com.jt;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Map;

/**
 * @author wangning
 * @create 2021-01-30 22:05
 */
public class TestRedis {

	//1.操作String类型
	@Test
	public void testString() {
		Jedis jedis = new Jedis("192.168.68.132",6379);
		jedis.set("1810","1810班");
		String s = jedis.get("1810");
		System.out.println("s = " + s);

	}

	@Test
	public void testHash() {
		Jedis jedis = new Jedis("192.168.68.132",6379);
		jedis.hset("user", "age", "18");
		Map<String, String> map = jedis.hgetAll("user");
		System.out.println("map = " + map);
	}

	//使用redis进行list数据的操作
	@Test
	public void testList() {
		Jedis jedis = new Jedis("192.168.68.132",6379);
		jedis.lpush("list","1","2","3");
		for (int i = 0;i<3 ;i++) {
			//当做栈使用
			String value = jedis.lpop("list");
			System.out.println("value = " + value);
		}
	}

	//使用redis进行事务管理
	@Test
	public void testTx() {

		Jedis jedis = new Jedis("192.168.68.132",6379);

		//1.先开启事务
		Transaction multi = jedis.multi();
		try {
			multi.set("cccc", "ssss");
			int a = 1 / 0;
			//2.事务提交
			multi.exec();

		} catch (Exception e) {
			e.printStackTrace();
			//3.事务回滚
			multi.discard();
			System.out.println("事务回滚");
		}
	}


}
