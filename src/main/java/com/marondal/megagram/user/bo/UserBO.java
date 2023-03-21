package com.marondal.megagram.user.bo;

import javax.crypto.EncryptedPrivateKeyInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.megagram.common.EncryptService;
import com.marondal.megagram.user.dao.UserDAO;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;//해당하는 다오메소드 사용하려면 다오객체 필요 -> 다오객체는 autowired를 통해서 스프링 프레임워크한테 객체관리를 맡기겠다.
	
	public int addUser(// 사용자 추가하기 위해 필요한 파라미터들 진행된 행의개수 return = int 
			String loginId // 전달 받고 뭘 리턴 할끼? 행의 갯수 리턴
			, String password// 해당하는 값들 그대로 인서트 해야하는데 비오에서 할수 있는일이 아님 다오 만들기.
			, String name
			, String email) {

		String encryptPassword= EncryptService.md5(password);// return할결과를 적당한 변수에다 저장
									//해당하는 메소드 그대로 사용
											//암호화된 패스워드를 전달하기
		return userDAO.insertUser(loginId, encryptPassword, name, email);//전달할 인자형태 고민 리턴해서 어떤결과가 전달될지.
		//지금은 비오만딱 수정해서 컨트롤러 다오엔 문제 없다								
		//문제가 있다면 얘만 딱 보기.
	}

	public boolean isDuplicate(String loginId) {//로그인아이디를 전달받아서 중복되는게 있는지 없는지 파악해주는 메소드
		//됐는지 안됐는지를 확인 boolean
		int count = userDAO.selectCountloginId(loginId);//조회된 갯수가 몇개인지???
		//해당하는 변수값에 따라서 0이면 중복 x 
		if(count == 0) {//중복되는게 0개 
			return false;
		} else {
			return true;
		}
		
		//return userDAO.selectCountloginId(loginId) != 0;
		
		

	}
}
