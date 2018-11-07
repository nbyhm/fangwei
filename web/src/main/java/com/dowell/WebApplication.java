package com.dowell;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 继承org.springframework.boot.web.servlet.support.SpringBootServletInitializer，实现configure方法：
 * 注：如果您正在构建WAR文件并部署它，则需要WebApplicationInitialize
 * extends SpringBootServletInitializer
 */
@SpringBootApplication
@MapperScan(basePackages = "com.dowell.dal.mapper.*")
public class WebApplication extends SpringBootServletInitializer {

	private static Logger log = LoggerFactory.getLogger(WebApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
		log.info("Dowell started up successfully at {} {}", LocalDate.now(), LocalTime.now());
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebApplication.class);
	}
}
