package com.jt.sso.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangning
 * @create 2021-02-06 17:18
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

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
}
