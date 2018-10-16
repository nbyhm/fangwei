package com.dowell.config;

import com.dowell.interceptor.AuthorizationInterceptor;
import com.dowell.resolver.LoginUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author nanbo
 * @description
 * @create 2018-09-28
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private AuthorizationInterceptor authorizationInterceptor;

	@Autowired
	private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//用于添加拦截规则
		registry.addInterceptor(authorizationInterceptor).addPathPatterns("/api/**");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
	}

}
