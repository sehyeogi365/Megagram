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
import com.marondal.megagram.user.bo.UserBO;

@Controller
@RequestMapping("/post")
public class PostController {
	

	@Autowired 
	private PostBO postBO;
	
	@Autowired 
	private UserBO userBO;
	
	@GetMapping("/timeline/view")
	public String timeline(
			Model model
			, HttpSession session
			) {
		
		
		int userId = (Integer)session.getAttribute("userId");
		
		//그럼 유저 리스트도 불러와야하나요?
		
		//List<User> userList = userBO.getUserList(LoginId);
		//model.addAttribute("userList", userList);
		
		List<Post> postList = postBO.getPostList(userId);
		model.addAttribute("postList", postList);
		
		List<Post> commentList = postBO.getCommentList(userId);
		model.addAttribute("commentList", commentList);
	
		
		
		return "post/timeline";
		
	}
	
	
	

}
