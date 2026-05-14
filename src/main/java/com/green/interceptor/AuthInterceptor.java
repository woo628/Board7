package com.green.interceptor;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.green.user.controller.UserController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
	
	
	// Interceptor : 페이지가 이동될 때 controller 앞에서 가로채기하는 class 
	// 전처리 (로그인 체크)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {		
		// 요청 주소
		String requestURI = request.getRequestURI();
		String qryStr =request.getQueryString(); // ? 뒤에있는 것들
		String loc = requestURI + "?" + qryStr;
		//System.out.println(requestURI);
		
		// loginform , login 제외
		if (requestURI.contains("/Users/LoginForm")) {
			return true; }
		
		if (requestURI.contains("/Users/Login")) {
			return true; }
		
		HttpSession session = request.getSession();
		// 사용자 로그인 정보를 세션 메모리에 user 로 저장
		Object login = session.getAttribute("login");
		session.setAttribute("loc", loc);
		//System.out.println(login);
		if (login == null) {
			// 로그인 되어 있지 않으니 로그인 페이지로 이동해라
			response.sendRedirect("/Users/LoginForm");
			return false;
		}
		
		// 컨트롤러 요청 url로 가도되나 안되나 결정 true: 컨트롤러 url로 가라
		return true;
	}
	
	// 후처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	 // System.out.println("2.post");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
}
