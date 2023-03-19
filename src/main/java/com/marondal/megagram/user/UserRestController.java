package com.marondal.megagram.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marondal.megagram.user.bo.UserBO;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	@PostMapping("/signup")
		public Map<String, String>signup(
				@RequestParam("loginId") String loginId
				, @RequestParam("password") String password
				, @RequestParam("name") String name
				, @RequestParam("email") String email
				) {
			
			int count = userBO.addUser(loginId, password, name, email);
			
					//회원가입및 중복 확인기능까지 진행해보기
			Map<String, String>	resultMap = new HashMap<>();
			
			if (count == 1) {
				resultMap.put("result", "success");
			} else {
				resultMap.put("result", "fail");
			}
			return resultMap;
			
			
		}
	
		@PostMapping("/duplicate")
		@ResponseBody
		public Map<String, Boolean> duplicateCheck(
				@RequestParam("loginId") String loginId) {
			
			Map<String, Boolean> isDuplicate = new HashMap<>();
			
			if(userBO.isDuplicate(loginId)) {
				isDuplicate.put("is_duplicate", true);
			} else {
				isDuplicate.put("is_duplicate", false);
			}
			
			return isDuplicate;
			
			
		
		
	}
		
	

}
