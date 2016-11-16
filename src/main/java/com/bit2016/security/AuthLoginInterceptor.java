package com.bit2016.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bit2016.jblog.service.JuserService;
import com.bit2016.jblog.vo.Jusers;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		// Web application Context 받아오기
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());

		// container 안에 있는 userservice bean을 받아오기
		JuserService juserService = ac.getBean(JuserService.class);

		// db에서 해당 uservo 받아오기
		Jusers jusersvo = juserService.login(id, password);

		// 이메일 패스워드가 틀린 경우
		if (jusersvo == null) {
			response.sendRedirect(request.getContextPath() + "/user/loginform?result=fail");
			return false;
		}
		// 인증
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", jusersvo);
		response.sendRedirect(request.getContextPath());

		return false;
	}
}
