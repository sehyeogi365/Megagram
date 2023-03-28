package com.marondal.megagram.post;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marondal.megagram.post.bo.PostBO;
import com.marondal.megagram.post.comment.bo.CommentBO;
import com.marondal.megagram.post.model.Comment;
import com.marondal.megagram.post.model.Post;
import com.marondal.megagram.post.model.PostDetail;
import com.marondal.megagram.user.bo.UserBO;

@Controller
@RequestMapping("/post")
public class PostController {
	

	@Autowired 
	private PostBO postBO;
	
	@Autowired 
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@GetMapping("/timeline/view")
	public String timeline(//모든것들다 조회해오기 비올를 통해서
			Model model
			, HttpSession session // 컨트롤러서 어디서든 세션정보 활용할수 있으니
			) {
		
		int userId = (Integer)session.getAttribute("userId");
	
		//그럼 유저 리스트도 불러와야하나요?
		
		//List<User> userList = userBO.getUserList(LoginId);
		//model.addAttribute("userList", userList);
		//컨트롤러(jsp에게데이터 전달)도 마찬가지 그대로 쓰면 되고 비오도 마찬가지. 
		List<PostDetail> postList = postBO.getPostList(userId);//이 조회된 데이터를 쓰려고	userId 인자로 넣어주기	
		model.addAttribute("postList", postList);//컨트롤러 어디서든
		

//		List<Comment> commentList = commentBO.getCommentList();
//		model.addAttribute("commentList", commentList);
		//좋아요도 조회기능 없듯이 이것도 안해도 된다하는데.
		
		
		
		return "post/timeline";
		
	}
	
	
	

}
