package com.dowell.web;

import com.dowell.dal.entity.UserEntity;
import com.dowell.interceptor.AuthorizationInterceptor;
import com.dowell.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author nanbo
 * @description Controller公共组件
 * @create 2018-09-28
 **/
public abstract class BaseController {

	@Autowired
	UserService userService;

	public UserEntity getCurrentUser(){
		String userKey = AuthorizationInterceptor.USER_KEY;
		UserEntity currentUser = userService.selectByKey(userKey);
		return currentUser;
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	protected Session getSession() {
		return getSubject().getSession();
	}

}
