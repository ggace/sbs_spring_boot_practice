package com.min.sbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.min.sbs.dto.Rq;
import com.min.sbs.util.Util;

import lombok.extern.log4j.Log4j2;




@Component
public class BeforeInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Rq rq = new Rq(request, response);
		
		request.setAttribute("rq", rq);
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
