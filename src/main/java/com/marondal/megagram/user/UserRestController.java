package com.marondal.megagram.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marondal.megagram.user.bo.UserBO;
import com.marondal.megagram.user.model.User;

@RestController// 기본 컨트롤러 + responsebody 까지 추가된 형태의 controller 굳이 밑에 responsebody 안붙여도된다
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;//비오메소드 호출하기위해서 autowired로 관리
	
	@PostMapping("/signup")
		public Map<String, String>signup(// json 형태 보면 어떤 형태 데이터 만들지 그려짐 = result, success = 키(문자열) 밸류(문자열) 형태 = 맵 형태
				@RequestParam("loginId") String loginId//로그인 아이디, 패스워드, 이름, 이메일
				, @RequestParam("password") String password
				, @RequestParam("name") String name
				, @RequestParam("email") String email
				) {//여기서 암호화하는게 아님컨트롤러는 리퀘스트, 리스폰스 만 담당하는곳 그이상의 기능은 비오
			
			int count = userBO.addUser(loginId, password, name, email);//비오메소드 호출함으로써 실제 데이터 저장.
			//실행된 행 갯수
					//회원가입및 중복 확인기능까지 진행해보기
			Map<String, String>	resultMap = new HashMap<>();
			
			if (count == 1) {// 성공/실패 여부
				resultMap.put("result", "success");//항상 키 밸류 형태
			} else {
				resultMap.put("result", "fail");
			}
			return resultMap;//실제 맵객체는 잭슨 라이브러리를통해서 제이슨 문자열이 만들어지고 그 문자열이 리스폰스에 담긴다.			
			
		}//기존의 코드랑 비교해가며 뭐가 틀렸는지 하면 아무 도움이 안된다. 여러분들은 과정배우러 온거지
	
		@GetMapping("/duplicate_id") //api니 레스트 컨트롤러 겟매핑이면 충분
		@ResponseBody
		public Map<String, Boolean> duplicateCheck(//제이슨과 유사한 자바객체로 구성-> 키밸류형태니 맵
				@RequestParam("loginId") String loginId) {//아이디 전달
//			boolean isDuplicate = userBO.isDuplicate(loginId);
			
			Map<String, Boolean> resultMap = new HashMap<>();
			
//			if(isDuplicate) {//중복여부에 따라 트루 폴스 boolean변수는 등호통해서 트루폴스 비교하지않는다. 이값자체가 트루 폴스 이변수 자체가 트루
//				resultMap.put("is_duplicate", true);
//			} else {
//				resultMap.put("is_duplicate", false);
//			}

			resultMap.put("is_duplicate", userBO.isDuplicate(loginId));//요값의 트루 폴스값 자체가 요값에 들어갈 밸류와 결국 일치하기 때문
			// 이렇게도 표현 가능 요갑자체가 이 밸류랑 일치
			// 코드는 줄여볼려고 노력하는게 좋음 코드의 이해도 높일수 있다. 하지만 무조건 줄이면 안좋음
			
			return resultMap;// 이 api는 로그인아이디 중복여부를 확인해서 제이손 형태로 이스 듀플리케이트라는 키로 중복여부결과를 트루폴스형태값으로 전달한다 뭘로 제인슨 문자열로 뭘통해서? 리스폰스를 통해서 
			
		
		}
		
		@PostMapping("/signin")
		public Map<String, String> signin(
				 @RequestParam("loginId") String loginId //두값이 일치 하는지 확인
				,@RequestParam("password") String password
				, HttpServletRequest request//HttpServlet 세션 객체 구해오기(?)
				) {
			//일치하는 사용자 정보 가져오기
			User user = userBO.getUser(loginId, password);
			
			
			Map<String, String> resultMap = new HashMap<>();
			
			
			if(user != null) {//조회된 경우가 있다 없다로 나뉨
				resultMap.put("result", "success");//널이아니면 일치 성공했으니 success
				
				HttpSession session = request.getSession();
				
				session.setAttribute("userId", user.getId());//user테이블의 id 세션이라는 공간에 유저아이디라는 키로 해당하는 아이디값을 저장시킴
				session.setAttribute("userName", user.getName());// 이것도 키 밸류 형태  게터를 통해 저장
				
			} else {
				resultMap.put("result", "fail");
			}
					
			return resultMap;//restcontroller라서 자동포함이므로 굳이 리스폰스바디 안넣어도 됨
	
		}
		

}
