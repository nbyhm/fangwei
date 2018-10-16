package com.dowell.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author nanbo
 * @description
 * @create 2018-10-05
 **/
public class HttpContextUtils {

	public static HttpServletRequest getHttpServletRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
}
