package com.jt.sso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

/**
 * @author wangning
 * @create 2021-02-06 14:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisCluster {
	@Autowired
	private JedisCluster jedisCluster;
	@Test
	public void testRedis() {
		jedisCluster.set("1810", "springboot整合redis成功");
		System.out.println(jedisCluster.get("1810"));
	}

	@Test
	public void testToken() {
		System.out.println(jedisCluster.get("1dcd7434fe34e3db2f76592c092a8563"));
	}
}
