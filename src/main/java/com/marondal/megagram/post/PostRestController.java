package com.marondal.megagram.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.megagram.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	private PostBO postBO;
	
	
	@PostMapping("/create")
	public Map<String, String> postCreate(	
			 @RequestParam("content") String content
			, @RequestParam(value="file", required=false) MultipartFile file
			, HttpSession session
			){//필요하다면 세션에서 불러와서 값을쓰는것.
		
		int userId = (Integer)session.getAttribute("userId");
		
		int count = postBO.addPost(userId, content, file);
		
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	
	}
	@PostMapping("/comment")
	public Map<String, String> commentCreate(
			 @RequestParam("userId") int userId
			 , @RequestParam("content") String content
			, HttpSession session
			) {
		
		
		int count = postBO.addComment(userId, content);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
		
	}
	
	
	
}
