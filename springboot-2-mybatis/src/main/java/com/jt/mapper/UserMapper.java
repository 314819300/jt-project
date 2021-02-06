package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangning
 * @create 2021-02-05 16:59
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
	List<User> findAll();
}
