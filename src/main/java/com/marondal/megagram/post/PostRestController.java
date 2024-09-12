package com.marondal.megagram.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.megagram.post.bo.PostBO;
import com.marondal.megagram.post.comment.bo.CommentBO;
import com.marondal.megagram.post.like.bo.LikeBO;

@RestController//컨트롤러+리스폰스바디
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	private PostBO postBO;
	//라이크 비오 까지 어토와이어드 함
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/create")
	public Map<String, String> postCreate(	//글쓰기
			 @RequestParam("content") String content //파일 null이여도 오류 안뜨게 하기
			, @RequestParam(value="file", required=false) MultipartFile file
			, HttpSession session
			){//필요하다면 세션에서 불러와서 값을쓰는것.
		
		int userId = (Integer)session.getAttribute("userId");//어디서든 가져올수있는게 세션 userId값을 가져온다.
						//다운캐스팅 필요
		int count = postBO.addPost(userId, content, file);
		
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;//해당 제이슨 객체는 문자열로 전환되서 response에 담아둔다(?)
		
	
	}
	//좋아요api 라이크레스트컨트롤러를 따로 만들어버림
	@GetMapping("/like")
	public Map<String, String> likeCreate(
			@RequestParam("postId") int postId
			//userId 세션으로 받기 세션객체로
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");// 세션은 항상로그인되는 과정에서 저장. 그런담에 필요한거를 꺼내 쓰는것.
		
		int count = likeBO.addLike(userId, postId);
			
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");//역시나 json 형태로 
		} else {
			resultMap.put("result", "fail");//결과는 역시나 json형태로 만들어진 문자열이 reponse에 담아진다.
		}
		
		
		return resultMap;
		
	}
	
	

	
	
	
	//좋아요 취소기능
	
	@GetMapping("/unlike")	
	public Map<String, String> unLike(//좋아요 취소를 확인
			@RequestParam("postId") int postId
			,HttpSession session){//f\로그인 할때 세션에 저장해놨으니 어떤페이지든 세션의 값을 얻어쓸수 있따.
		
		int userId = (Integer)session.getAttribute("userId");		
		
		int count = likeBO.unLike(userId, postId);
		
		Map<String, String> resultMap = new HashMap<>(); 		
		
		
		if(count == 0) {//삭제된게 없을때 fail
			resultMap.put("result", "fail");
		} else {
			resultMap.put("result", "success");
		}
		
		return resultMap;
		
		
	}
	
	//댓글 달기
	@PostMapping("/comment/create")
	public Map<String, String> createComment(
			 @RequestParam("postId") int postId
			 , @RequestParam("content") String content
			, HttpSession session//마찬가지로 세션에있는 로그인아이디
			) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		int count = commentBO.addComment(userId, postId, content);
		
		
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;	
	}
	
	@GetMapping("/delete")
	public Map<String, String> deletePost(//
			@RequestParam("postId") int postId
			, HttpSession session//유저아이디 불러오기
			) {
		int userId = (Integer)session.getAttribute("userId");
		
		int count = postBO.deletePost(userId, postId);//수행된 행의갯수리턴
		
		
		Map<String, String> resultMap = new HashMap<>();
		
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		
		return resultMap;
		
	}
	
	
	
	
	
	
	
	
}
