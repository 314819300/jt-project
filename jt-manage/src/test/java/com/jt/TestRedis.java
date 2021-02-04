package com.jt;

import org.junit.Test;
import redis.clients.jedis.*;

import java.util.*;

/**
 * @author wangning
 * @create 2021-01-30 22:05
 */
public class TestRedis {

	//1.操作String类型
	@Test
	public void testString() {
		Jedis jedis = new Jedis("192.168.68.132", 6379);
		jedis.set("1810", "1810班");
		String s = jedis.get("1810");
		System.out.println("s = " + s);

	}

	@Test
	public void testHash() {
		Jedis jedis = new Jedis("192.168.68.132", 6379);
		jedis.hset("user", "age", "18");
		Map<String, String> map = jedis.hgetAll("user");
		System.out.println("map = " + map);
	}

	//使用redis进行list数据的操作
	@Test
	public void testList() {
		Jedis jedis = new Jedis("192.168.68.132", 6379);
		jedis.lpush("list", "1", "2", "3");
		for (int i = 0; i < 3; i++) {
			//当做栈使用
			String value = jedis.lpop("list");
			System.out.println("value = " + value);
		}
	}

	//使用redis进行事务管理
	@Test
	public void testTx() {

		Jedis jedis = new Jedis("192.168.68.132", 6379);

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

	//实现redis的分片操作
	@Test
	public void testShards() {
		String host = "192.168.68.132";
		List<JedisShardInfo> shards = new ArrayList<>();
		shards.add(new JedisShardInfo(host, 6379));
		shards.add(new JedisShardInfo(host, 6380));
		shards.add(new JedisShardInfo(host, 6381));
		ShardedJedis shardedJedis = new ShardedJedis(shards);
		shardedJedis.set("shards", "完成分片操作");
		System.out.println("获取数据：" + shardedJedis.get("shards"));
	}

	/**
	 * 实现哨兵的操作
	 * masterName:获取主机变量名称
	 * IP：端口
	 */

	@Test
	public void testSentinel() {
		Set<String> sentinels = new HashSet<>();
		//以下两种方式本质上是一样的效果
//		sentinels.add(new HostAndPort("192.168.68.132",26379));
		sentinels.add("192.168.68.132:26379");
		JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);
		//获取redis连接
		Jedis jedis = pool.getResource();
		jedis.set("aa", "redis哨兵的配置");
		System.out.println(jedis.get("aa"));
		jedis.close();
	}

	/**
	 * 实现redis集群操作
	 */
	@Test
	public void testCluster() {
		String host = "192.168.68.132";
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.68.132", 7000));
		nodes.add(new HostAndPort("192.168.68.132", 7001));
		nodes.add(new HostAndPort("192.168.68.132", 7002));
		nodes.add(new HostAndPort("192.168.68.132", 7003));
		nodes.add(new HostAndPort("192.168.68.132", 7004));
		nodes.add(new HostAndPort("192.168.68.132", 7005));
		nodes.add(new HostAndPort("192.168.68.132", 7006));
		nodes.add(new HostAndPort("192.168.68.132", 7007));
		nodes.add(new HostAndPort("192.168.68.132", 7008));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("cluster", "集群的操作测试");
		System.out.println(jedisCluster.get("cluster"));
	}


}
