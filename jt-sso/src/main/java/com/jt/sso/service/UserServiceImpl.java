package com.jt.sso.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.JedisCluster;

import java.util.Date;

/**
 * @author wangning
 * @create 2021-02-06 17:18
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private JedisCluster jedisCluster;

	/**
	 * 思路：根据用户传递的数据表和类型查询数据
	 * select count（*） from 表名 where
	 *
	 * @param param 参数
	 * @param type
	 * @return
	 */
	@Override
	public boolean findCheckUser(String param, Integer type) {
		String column = null;
		switch (type) {
			case 1:
				column = "username";
				break;
			case 2:
				column = "phone";
				break;
			case 3:
				column = "email";
				break;
		}
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq(column, param);
		int count = userMapper.selectCount(queryWrapper);
		return count == 0 ? false : true;
	}

	@Override
	@Transactional	//添加事物的控制
	public void saveUser(User user) {
		//为了不让后台报错，暂时使用电话代替email
		user.setEmail(user.getPhone());
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		userMapper.insert(user);
		System.out.println(" 用户入库成功 ");
	}

	/**
	 * 1.检验用户名可密码是否正确
	 * @param user
	 * @return
	 */
	@Override
	public String findUserByUP(User user) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username", user.getUsername())
				.eq("password",user.getPassword());
		User userDB = userMapper.selectOne(queryWrapper);
		String token = null;
		try {
			if(userDB != null) {
				//1.生成加密的token
				String key = "JT_TICKET_" + System.currentTimeMillis() + user.getUsername();
				token = DigestUtils.md5DigestAsHex(key.getBytes());
				String userJson = objectMapper.writeValueAsString(userDB);
				//将数据保存到redis中，并添加超时时间
				jedisCluster.set(token, userJson);
				jedisCluster.expire(token, 7 * 24 * 3600);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return token;
	}

	/**
	 * 查询用户是否登录
	 * @param token
	 * @return
	 */
	@Override
	public String queryToken(String token) {
		String userJSON = jedisCluster.get(token);

		return userJSON;
	}
}
