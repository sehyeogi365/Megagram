package com.marondal.megagram.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class PermissionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler
			) throws IOException{
		
		HttpSession session = request.getSession();
		
		Integer userId = (Integer)session.getAttribute("userId");//이게저장되있으면 로그인 된상태 아니면 로그인 안된상태
		//null이저장가능하게 끔 
		
		// /user/signin/view
		String uri = request.getRequestURI();
		
		if(userId != null) {
			// 로그인이 되었을때
			// 회원 가입, 로그인 페이지 접근하려고 하면
			// user 로 시작하는 페이지에 접근하려고 하면 
			// 리스트 페이지로 이동시켜라

			if(uri.startsWith("/user")) {//user 라는 시작하는걸로 접근할려면?
				// 리다이렉트 
				response.sendRedirect("/post/list/view");//이동하고픈 주소
				//다른페이지로 이동하는거를 대신 try catch 대신 throw
				
				return false;//원래 가려 했던 그 목적페이지에 못가게 하려면 폴스
			}
			
			} else {
			// 로그인이 안되었을떄
			// 리스트, 글쓰기, 상세화면 페이지로 접근하려고 하면
			// post 로 시작하는 페이지에 접근하려고 하면 	
			// 로그인 페이지로 이동해라
				if(uri.startsWith("/post")) {
					response.sendRedirect("/user/signin/view");
					return false;
				}
				
				
			}
		
		
		return true;//문제없이 여기까지 왔으면 정상수행
		
		
		//pre handle override
	}
	
}
