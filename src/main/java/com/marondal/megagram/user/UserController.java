package com.marondal.megagram.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/signup/view")
	public String signupInput() {
		return "user/signup";
	}
	@GetMapping("/signin/view")
	public String signinInput() {
		return "user/signin";
	}
	
	
	
	
	@GetMapping("/signout")//리퀘스트 객체를 통해 얻어올수있지만 더 간단하게
	public String signout(HttpSession session) {
		
		session.removeAttribute("userId");
		session.removeAttribute("userName");//특정페이지로 이동시킬거다.
		
		return "redirect:/user/signin/view";//이동할페이지 url
	}
	
	
	
}
