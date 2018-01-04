package com.rjhy.cloud.common.config;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
public class ShiroFilter extends AdviceFilter {
	/*
	 * (non-Javadoc)
	 * 使用VUe.js 的axios跨域请求，会在 POST、DELETE、PUT请求前先发送OPTIONS请求，
	 * OPTIONS请求无cookie，导致
	 * shiro返回未授权错误，此处单独处理OPTIONS请求，强行返回200
	 * @see org.apache.shiro.web.servlet.AdviceFilter#preHandle(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
			response.setStatus(HttpStatus.OK.value());
			return false;
		}
		return super.preHandle(request, response);
	}
}
