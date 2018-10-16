package com.dowell.common.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author nanbo
 * @description Xss攻击拦截器
 * @create 2018-10-04
 **/
public class XssFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
				(HttpServletRequest) servletRequest);
		filterChain.doFilter(xssRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
