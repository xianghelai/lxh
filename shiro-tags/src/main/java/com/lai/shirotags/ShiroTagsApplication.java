package com.lai.shirotags;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lai.shirotags.mapper")
public class ShiroTagsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiroTagsApplication.class, args);
	}

}
