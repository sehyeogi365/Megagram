package com.marondal.megagram.post.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.megagram.post.comment.dao.CommentDAO;
import com.marondal.megagram.post.model.Comment;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	
		//댓글달기
		public int addComment(int userId, int postId, String content) {
			
			return commentDAO.insertComment(userId, postId, content);
		}
			

		//댓글보기	
		public List<Comment> getCommentList(int userId){
			return commentDAO.selectCommentList();
			
		}





	
}
