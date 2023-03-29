package com.marondal.megagram.post.comment.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.megagram.post.comment.dao.CommentDAO;
import com.marondal.megagram.post.model.Comment;
import com.marondal.megagram.post.model.CommentDetail;
import com.marondal.megagram.user.bo.UserBO;
import com.marondal.megagram.user.model.User;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserBO userBO;//userBO 불러온다.
	
	
		//댓글달기
		public int addComment(int userId, int postId, String content) {
			
			return commentDAO.insertComment(userId, postId, content);
		}
			

		//댓글보기	아이디값을 기반으로 그에대응 하는 댓글들 조회하는 기능 여러개라서 리스트
		public List<CommentDetail> getCommentList(int postId){//포스트아이디로
			
			List<Comment> commentList = commentDAO.selectCommentList(postId);
			
			List<CommentDetail> commentDetailList = new ArrayList<>();//이것도 직접 만들어준다.
			
			for(Comment comment:commentList){//향상된 for문통해서
			
			User user =  userBO.getUserById(comment.getUserId());//userbo를 통해서 일치하는 사용자정보얻어오기
				
				CommentDetail commentDetail  = new CommentDetail();
			
				commentDetail.setId(comment.getId());
				commentDetail.setUserId(comment.getUserId());
				commentDetail.setUserLoginId(user.getLoginId());//유저객체서 로그인아이디 불러오기 처음에 getContent였음.
				commentDetail.setContent(comment.getContent());
			
				commentDetailList.add(commentDetail);
			}
			return commentDetailList;

		}



	
}
