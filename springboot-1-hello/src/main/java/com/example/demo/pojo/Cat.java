package com.example.demo.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wangning
 * @create 2021-02-05 15:57
 */
@Data
@Accessors(chain = true)	//开启链式结构
public class Cat {
	private Integer id;
	private String name;
	private Integer age;

	/**
	 * 链式结构的实现方式，供学习理解使用
	 * @param id
	 * @return
	 */
	public Cat setId(Integer id) {
		this.id = id;
		return this;
	}
}
