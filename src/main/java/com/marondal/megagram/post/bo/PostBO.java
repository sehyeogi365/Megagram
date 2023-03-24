package com.marondal.megagram.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.megagram.common.FileManagerService;
import com.marondal.megagram.post.dao.PostDAO;
import com.marondal.megagram.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	//타임라인 추가
	public int addPost(int userId, String content,  MultipartFile file) {
		
		String imagePath = FileManagerService.saveFile(userId, file);
		return postDAO.insertPost(userId, content, imagePath);

	
	}
	
	//타임라인 보기
	public List<Post>getPostList(int userId) {//여러개이므로 리스트 게시글들 영러개 저장 <>안에 객체 여기도 파라미터
			
			
		return postDAO.selectPostList(userId);
		
		
	}
	
	//댓글달기
	public int addComment(int userId, int postId, String content) {
		
		return postDAO.insertComment(userId, postId, content);
	}
		

	//댓글보기	
	public List<Post> getCommentList(int userId, int postId, String content){
		return null;
		
	}
		
}

	
	

