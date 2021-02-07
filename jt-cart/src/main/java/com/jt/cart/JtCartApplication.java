package com.jt.cart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jt.cart.mapper")
public class JtCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(JtCartApplication.class, args);
	}

}
