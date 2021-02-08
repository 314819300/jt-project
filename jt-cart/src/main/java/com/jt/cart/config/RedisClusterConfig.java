package com.jt.cart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangning
 * @create 2021-02-06 14:38
 */
@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class RedisClusterConfig {
	@Value("${redis.nodes}")
	private String redisNodes;

	@Bean
	public JedisCluster getJedisCluster() {
		Set<HostAndPort> nodes = new HashSet<>();
		String[] hostAndPorts = redisNodes.split(",");
		for (String node : hostAndPorts) {
			String[] args = node.split(":");
			String host = args[0];
			int port = Integer.parseInt(args[1]);
			nodes.add(new HostAndPort(host, port));
		}
		return new JedisCluster(nodes);
	}
}
