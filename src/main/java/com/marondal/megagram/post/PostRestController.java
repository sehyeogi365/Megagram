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

@RestController//컨트롤러+리스폰스바디
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	private PostBO postBO;
	//강사님은 라이크 비오 까지 어토와이어드 함
	
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
	//좋아요api 강사님은 라이크레스트컨트롤러를 따로 만들어버림
	@GetMapping("/like")
	public Map<String, String> likeCreate(
			@RequestParam("postId") int postId
			//userId 세션으로 받기 세션객체로
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");// 세션은 항상로그인되는 과정에서 저장. 그런담에 필요한거를 꺼내 쓰는것.
		
		int count = postBO.addLike(userId, postId);
			
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");//역시나 json 형태로 
		} else {
			resultMap.put("result", "fail");//결과는 역시나 json형태로 만들어진 문자열이 reponse에 담아진다.
		}
		
		
		return resultMap;
		
	}
	
	
	//좋아요 조회 기능 빈하트냐 아니냐 (api)
	@GetMapping("/like_select")
	public Map<String, String> likeSelect(
			@RequestParam("postId") int postId
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		int count = postBO.isLike(userId, postId);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
			
		}
		return resultMap;
		
	}
	
	
	
	//좋아요 취소기능
	
	@GetMapping("/like_delete")
	
	public Map<String, String> deleteLike(
			@RequestParam("userId") int userId){
		
		Map<String, String> resultMap = new HashMap<>(); 
		
		int count = postBO.deleteLike(userId);
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
		
	}
	
	
	
	//댓글 달기
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
