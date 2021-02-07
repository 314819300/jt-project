package com.jt.sso.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("tb_user")
public class User extends BasePojo{

	private static final long serialVersionUID = 7638040116539393035L;
	@TableId(type=IdType.AUTO)	//主键自增
	private Long id;
	private String username;
	private String password;
	private String phone;
	private String email;
	
}
