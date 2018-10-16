package com.dowell.interceptor;

import com.dowell.annotation.Login;
import com.dowell.common.exception.BizException;
import com.dowell.dal.entity.TokenEntity;
import com.dowell.service.token.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author nanbo
 * @description 权限（Token验证）
 * @create 2018-10-02
 **/
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	public static final String USER_KEY = "userId";

	@Autowired
	private TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Login annotation;

		if (handler instanceof HandlerMethod){
			annotation = ((HandlerMethod)handler).getMethodAnnotation(Login.class);
		}else {
			return true;
		}

		if (annotation == null){
			return true;
		}

		//从header中获取token
		String token = request.getHeader("token");
		//如果header中不存在token,则从参数中获取token
		if (StringUtils.isBlank(token)){
			token = request.getParameter("token");
		}

		//token为空
		if (StringUtils.isBlank(token)){
			throw new BizException("token不能为空");
		}

		//查询token信息
		TokenEntity userToken = tokenService.getUserToken(token);
		if (userToken == null || userToken.getExpireTime().getTime() < System.currentTimeMillis()){
			throw new BizException("token失效，请重新登录");
		}

		//设置userId到request里，后续根据userId，获取用户信息
		request.setAttribute(USER_KEY, userToken.getUserId());
		return true;
	}
}
