package com.marondal.megagram.post;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marondal.megagram.post.bo.PostBO;
import com.marondal.megagram.post.model.Post;

@Controller
@RequestMapping("post")
public class PostController {
	

	@Autowired 
	private PostBO postBO;
	
	@GetMapping("/timeline/view")
	public String timeline(
			Model model
			, HttpSession session
			) {
		
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<Post> postList = postBO.getPostList(userId);
		model.addAttribute("postList", postList);
	
		
		
				
				
		
		return "post/timeline";
		
	}
	
	
	

}
