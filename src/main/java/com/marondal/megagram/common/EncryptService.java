package com.marondal.megagram.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptService {
	// 암호화 기능 
	
	public static String md5(String message) {//static 메소드 객체생성 없이 호출 고로 멤버변수 사용 불가함. 멤버변수를 사용하지 않는 메소드
		
		String resultString = "";//비어있는문자열 만들어서 이어붙이기
		
		//암호화 메소드
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			
			byte[] bytes = message.getBytes();//문자열을 bytes라는 타입으로 변경
			md.update(bytes);
			
			byte[] digest = md.digest();//byte배열형태로 리턴
			
			// 16 진수 형태의 문자열로 변환 
			for(int i = 0; i < digest.length; i++) {
				// 010111001
				// 111111111
				// 010111 비트연산에대해 공부 해보기 
				
				resultString += Integer.toHexString(digest[i] & 0xff);
			}
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultString;
		
	}

}
