package com.w33kt.aicodehelper;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author tankaiwen
 */
@SpringBootApplication
@MapperScan("com.w33kt.aicodehelper.dao.mapper")
public class AiCodeHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiCodeHelperApplication.class, args);
	}

}
