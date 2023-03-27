package com.marondal.megagram.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.megagram.common.FileManagerService;
import com.marondal.megagram.post.dao.PostDAO;
import com.marondal.megagram.post.model.Comment;
import com.marondal.megagram.post.model.Post;
import com.marondal.megagram.post.model.PostDetail;
import com.marondal.megagram.user.bo.UserBO;
import com.marondal.megagram.user.model.User;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	//유저객체 필요 역시 이렇게 하는게 맞았음
	
	@Autowired
	private UserBO userBO;//비오서 비오 불러오기 같은이름의 다오는 불러오는게 자연스럽지만 다른이름의 다오는 불러오기 부자연스러움 그래서 비오를 통해서 불러온거라 함
	
	//타임라인 추가
	public int addPost(int userId, String content,  MultipartFile file) {//userId 도 추가(이미추가됨)
		
		String imagePath = FileManagerService.saveFile(userId, file);//세이브 파일 호출 userId얻을방법이없으면 얻어와야지.. userId
		return postDAO.insertPost(userId, content, imagePath);//실행되는 행의 갯수 

	
	}
	
	//타임라인 보기
	public List<PostDetail>getPostList() {//여러개이므로 리스트 게시글들 영러개 저장 <>안에 객체 여기도 파라미터
			//return type 변경
		//컨트롤러에서 원하는 (jsp에서 사용할) 데이터 형태를 만들어준다.	
		
		List<Post> postList = postDAO.selectPostList();
		
		//스스로 생성
		List<PostDetail> postDetailList = new ArrayList<>();//for문 밖에 생성
		
		
		for(Post post:postList) {
			
			//카드를 구성하기위한 포스트디테일객체
			
			User user = userBO.getUserById(post.getUserId());
			
			PostDetail postDetail = new PostDetail();//객체생성도 직접
			
			postDetail.setId(post.getId());
			postDetail.setContent(post.getContent());
			postDetail.setImagePath(post.getImagePath());
			postDetail.setUserId(post.getUserId());
			postDetail.setLoginId(user.getLoginId());//어떻게든 얻어내야하는값 테이블에서 조회해 와야함
			//nullpointException이 뜬다. 여 값이 널값이란뜻 왜 널일까
			postDetailList.add(postDetail);
		}
		
		return postDetailList;//하나의객체 안에 이 카드의 정보들을 채울수있는 데이터가 필요 id, 좋아요, 댓글 등
		//객체 생성부터 값을 넣는것까지 직접다해야함.
		
	}
	
	//좋아요
	public int addLike(int userId) {
		
		return postDAO.insertLike(userId);

	}
	
	public int deleteLike(int userId) {
		
		return userId;		
		
	}
	
	
	//좋아요 수(게시글마다의 좋아요 어떤 게시글의 삭제버튼과 유사)
	
	public int getLike(int userId) {
		
		return postDAO.selectLike(userId);

	}
	
	//댓글달기
	public int addComment(int userId, String content) {
		
		return postDAO.insertComment(userId, content);
	}
		

	//댓글보기	
	public List<Comment> getCommentList(){
		return postDAO.selectCommentList();
		
	}


		
}

	
	

