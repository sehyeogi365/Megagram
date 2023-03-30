package com.marondal.megagram.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.megagram.common.FileManagerService;
import com.marondal.megagram.post.comment.bo.CommentBO;
import com.marondal.megagram.post.dao.PostDAO;
import com.marondal.megagram.post.like.bo.LikeBO;
import com.marondal.megagram.post.model.Comment;
import com.marondal.megagram.post.model.CommentDetail;
import com.marondal.megagram.post.model.Post;
import com.marondal.megagram.post.model.PostDetail;
import com.marondal.megagram.user.bo.UserBO;
import com.marondal.megagram.user.model.User;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	//유저객체 필요 역시 이렇게 하는게 맞았음
	//강사님은 라이크 다오 까지 어토와이어드 함
	@Autowired
	private UserBO userBO;//비오서 비오 불러오기 같은이름의 다오는 불러오는게 자연스럽지만 다른이름의 다오는 불러오기 부자연스러움 그래서 비오를 통해서 불러온거라 함
	
	//강사님은 라이크비오추가
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	//타임라인 추가
	public int addPost(int userId, String content,  MultipartFile file) {//userId 도 추가(이미추가됨)
		
		String imagePath = FileManagerService.saveFile(userId, file);//세이브 파일 호출 userId얻을방법이없으면 얻어와야지.. userId
		return postDAO.insertPost(userId, content, imagePath);//실행되는 행의 갯수 

	
	}
	
	//타임라인 보기
	public List<PostDetail>getPostList(int userId) {//여러개이므로 리스트 게시글들 영러개 저장 <>안에 객체 여기도 파라미터
			//return type 변경		//parameter로 전달받는다. 이렇게. 로그인한 유저아이디 정보.
		//컨트롤러에서 원하는 (jsp에서 사용할) 데이터 형태를 만들어준다.	
		
		List<Post> postList = postDAO.selectPostList();
		
		//스스로 생성
		List<PostDetail> postDetailList = new ArrayList<>();//for문 밖에 생성
		
		
		for(Post post:postList) {
			
			//카드를 구성하기위한 포스트디테일객체
			
			User user = userBO.getUserById(post.getUserId());	
			int likeCount = likeBO.getLikeCount(post.getId());//???? 굳이 라이크 비오 안만들거면 postDAO 값의 것을 불러 오라 하심post객체안에있는것은 맞다 근데 반복문 내에 있는 값자동완성 최소화 하라고 당부하심 ㅇㅇ	  
						//likeBO.getLikeCount(post.getId());
			
			
			boolean isLike = likeBO.isLike(userId, post.getId());//parameter로 불러옴
			//boolean	
			
			//게시물 별로 댓글'들' 가져오기
			//커멘트 비오 호출
			List<CommentDetail> commentList = commentBO.getCommentList(post.getId());//, post.getId() 게시글 별로
	
			PostDetail postDetail = new PostDetail();//객체생성도 직접
			
			postDetail.setId(post.getId());
			postDetail.setContent(post.getContent());
			postDetail.setImagePath(post.getImagePath());//여기서도 likeCount 추가
			postDetail.setUserId(post.getUserId());
			postDetail.setLoginId(user.getLoginId());//어떻게든 얻어내야하는값 테이블에서 조회해 와야함
			postDetail.setLikeCount(likeCount);
			postDetail.setLike(isLike);
			postDetail.setCommentList(commentList);//불로온 객체를 그대로 전달
			
			//여기서도 댓글 쓰는거 postDetail 모델에서도 추가 하기 댓글목록이니 리스트 여야 한다는 점. 
			
			//컨트롤러는 전달해줄 뿐 
			//nullpointException이 뜬다. 여 값이 널값이란뜻 왜 널일까
			postDetailList.add(postDetail);
		}
		
		return postDetailList;//하나의객체 안에 이 카드의 정보들을 채울수있는 데이터가 필요 id, 좋아요, 댓글 등
		//객체 생성부터 값을 넣는것까지 직접다해야함.
		
	}
	
	public int deletePost(int userId, int postId) {//유저아이디 전달받기 일단
		
		Post post = postDAO.selectPostByUserId(userId, postId);
		
		//조회된 데이터가 있을때만 
		if(post != null) {
			FileManagerService.removeFile(post.getImagePath());//항상이미지가 포함되어있으니 이미지 삭제기능 이미지 경로가 필요함.
			//이미지경로 얻어온다.
			
			//댓글
		
			commentBO.deleteCommentByPostId(postId);//댓글삭제
			
			//좋아요
			likeBO.deleteLikeByPostId(postId);//좋아요 삭제		
			return postDAO.deletePost(postId);//1이 정상적으로 삭제된걸 알려주는 기능
				//리턴값은 이걸로
		} else {
			return 0;
		}
		
		
		
		
		
		//id가 일치하는 사람의 것만 삭제 해야한다. 그럴려면 어떢해야 하는가?
		//if를 해야하나??
		
		
		
			
		
			
		
	
		
		
		
		
	
		
		
		
		
	
	
	
	

	}

		
}

	
	

