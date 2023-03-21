package com.marondal.megagram.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptService {
	// 암호화 기능 
	
	public static String md5(String message) {//뭘전달받아서 뭘 돌려줄지 구성해주기 암호화할 문자열 전달
		//모든코드는 인풋과 아웃풋으로 정리 된다 이것만 머리속에 딱 기억하기.
		String resultString = "";//비어있는문자열 만들어서 이어붙이기
		
		//강사님도 이밑에는 다 복붙하심
		try {
			MessageDigest md = MessageDigest.getInstance("md5");// 암호화를 처리해주는 클래스
			//암호화할방식을 문자로 전달해서 객체를 생성 해주고. 
			//암호화 하기위해서 문자열을 바이트로			
			byte[] bytes = message.getBytes();//문자열을 bytes라는 타입으로 변경
			md.update(bytes);
			
			byte[] digest = md.digest();//byte배열형태로 리턴
			
			// 16 진수 형태의 문자열로 변환 
			for(int i = 0; i < digest.length; i++) {//문자열을 쭉 반복문을 통해 이어붙인다.
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
