package com.minivision.sjzx.trade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.minivision.sjzx.trade.dao")
public class SjzxTradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SjzxTradeApplication.class, args);
	}

}
