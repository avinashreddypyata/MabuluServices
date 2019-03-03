package com.mabulu.project.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginInterceptor extends HandlerInterceptorAdapter {
	private  Logger log = LogManager.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug(request.getHeader("token"));
		if(!StringUtils.isEmpty(request.getHeader("token"))) {
			return true;
		}else {
			response.sendError(401);
			return false;
			
		}
        
	
	}

}
