package com.jt.web.service;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.User;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.management.relation.RoleUnresolved;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangning
 * @create 2021-02-06 21:51
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private HttpClientService httpClient;

	@Autowired
	private ObjectMapper objectMapper;
	/**
	 * 发起httpClient post请求
	 * @param user
	 */
	@Override
	public void saveUser(User user) {
		String url = "http://sso.jt.com/user/register";
		Map<String, String> params = new HashMap<>();
		params.put("username", user.getUsername());
		String md5DigestAsHex = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		params.put("password", md5DigestAsHex);
		params.put("phone", user.getPhone());
		params.put("email", user.getEmail());
		try {
			String result = httpClient.doPost(url, params);
			SysResult sysResult = objectMapper.readValue(result, SysResult.class);
			if(sysResult.getStatus() != 200) {
				System.out.println("表示后台有错");
				throw new RuntimeException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public String findUserByUP(User user) {
		String token = null;
		String url = "http://sso.jt.com/user/login";
		Map<String, String> params = new HashMap<>();
		params.put("username", user.getUsername());
		//将密码加密出来
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		params.put("password", md5Pass);
		try {
			String result = httpClient.doPost(url, params);
			SysResult sysResult = objectMapper.readValue(result, SysResult.class);
			if(sysResult.getStatus() == 200) {
				token = (String) sysResult.getData();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		return token;
	}
}
