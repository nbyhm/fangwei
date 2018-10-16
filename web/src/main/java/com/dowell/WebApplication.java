package com.dowell;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * extends SpringBootServletInitializer
 */
@SpringBootApplication
@MapperScan(basePackages = "com.dowell.*.*.mapper")
public class WebApplication {

	private static Logger log = LoggerFactory.getLogger(WebApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
		log.info("Dowell started up successfully at {} {}", LocalDate.now(), LocalTime.now());
	}

	//@Override
	//protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	//	return application.sources(WebApplication.class);
	//}
}
