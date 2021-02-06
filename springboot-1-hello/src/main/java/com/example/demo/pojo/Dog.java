package com.example.demo.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author wangning
 * @create 2021-02-05 14:38
 */
@Component
@PropertySource("classpath:/properties/dog.properties")//用来导入指定的properties配置文件
@ConfigurationProperties(prefix = "dog")
public class Dog {

	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
