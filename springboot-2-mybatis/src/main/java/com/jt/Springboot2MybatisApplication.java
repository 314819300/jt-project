package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
@MapperScan(value = "com.jt.mapper")	//为指定的包创建代理对象
public class Springboot2MybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot2MybatisApplication.class, args);
	}

}
