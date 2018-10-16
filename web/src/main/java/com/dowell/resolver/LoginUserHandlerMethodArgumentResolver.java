package com.dowell.resolver;

import com.dowell.annotation.LoginUser;
import com.dowell.dal.entity.UserEntity;
import com.dowell.interceptor.AuthorizationInterceptor;
import com.dowell.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author nanbo
 * @description 有@LoginUser注释的方法参数，注入当前登录用户
 * @create 2018-10-02
 **/
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private UserService userService;

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterType().isAssignableFrom(UserEntity.class) && methodParameter.hasParameterAnnotation(LoginUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container, NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
		Object object = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
		if (object == null){
			return null;
		}

		//获取用户信息
		UserEntity user = userService.selectByKey(object);
		return user;
	}
}
