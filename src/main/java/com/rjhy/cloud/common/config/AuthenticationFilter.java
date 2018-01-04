package com.rjhy.cloud.common.config;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class AuthenticationFilter extends FormAuthenticationFilter
{
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
	{				
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
                return executeLogin(request, response);
            } else {
                return true;
            }
		}else{
			WebUtils.toHttp(response).sendError(403);
		}
		return false;
	}
}
