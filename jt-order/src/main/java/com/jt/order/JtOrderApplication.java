package com.jt.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jt.order.mapper")
public class JtOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(JtOrderApplication.class, args);
	}

}
