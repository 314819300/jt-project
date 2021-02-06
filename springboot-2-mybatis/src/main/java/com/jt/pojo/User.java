package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wangning
 * @create 2021-02-05 16:58
 */
@Data
@Accessors(chain = true)
@TableName(value = "user") //标识表的名称，如果表的名字和对象的名字相同，后面可以省略不写
public class User implements Serializable {
	private static final long serialVersionUID = 6316976317906178373L;
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String name;
	private Integer age;
	private String sex;
}
