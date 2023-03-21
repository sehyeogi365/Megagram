package com.marondal.megagram.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

	
	public int insertUser( // 인서트 하는 과정에서 필요한 데이터들이 있따면 파라미터로 전달받는다. 로그인 아이디, 비번, 이름, 이메일 
		@Param("loginId") String loginId//행의 갯수가 리턴되기에 int 가 리턴형
		, @Param("password") String password
		, @Param("name") String name
		, @Param("email") String email
		);//다오메소드 수행하면 지정한 쿼리 수행됨

	public int selectCountloginId(//일치하는게 몇개가 조회되는지 
			@Param("loginId")String loginId);
		
	
}
