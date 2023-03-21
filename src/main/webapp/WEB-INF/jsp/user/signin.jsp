<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>megagram - 로그인</title>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
<div id="wrap" class="container">
		
		<c:import url="/WEB-INF/jsp/include/header.jsp"/>
		<section class="contents">
			<div class="login-box my-5">
				<h1 class="text-center">로그인</h1>
				
				<form id="loginForm">
				<div class="d-flex mt-3">
					<input type="text" id="loginIdInput" placeholder="로그인 ID" class="form-control mt-4">
					
				</div>
				
				
				<input type="password" id="passwordInput" placeholder="비밀번호" class="form-control mt-4">
				
				
				<button type="submit" class="btn btn-info btn-block mt-3" id="loginBtn">로그인</button>
				</form>
				
			</div>
		
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp"/>
	</div>
	
	
	<script>
	$(document).ready(function(){
		$("#loginForm").on("submit", function(e){//loginForm submit event에대한 정보가 저장되어있는데.
			
			//이벤트가 가진 고유기능과 속성을 취소한다.
			//특정이벤트 취소시키는 방법 //오늘과제 메모입력, 리스트만 화면구성까지만
			e.preventDefault();
			
			
			let loginId = $("#loginIdInput").val();
			let password = $("#passwordInput").val();
			
			
			if(loginId == "") {
				
				alert("아이디를 입력해주세요.");
				return ;
			} 
			
			if(password == ""){
				alert("비밀번호를 입력해주세요.");
				return ;
			}
			
			$.ajax({
				type:"post"
				, url:"/user/signin"
				, data:{"loginId" : loginId, "password" : password}//request정의
				, success:function(data){
					
					if(data.result == "success"){//key값으로 값을 검증
						location.href="/post/timeline/view";//api를 아작스로 사용하는것
						alert("로그인 성공");//잘만들어진 api가 있다는가정하에 api가 수정되는곳 아작스가 수정되는곳 완전 별개 임 500이면 서버에러
					} else {
						alert("아이디/비밀번호를 확인하세요.")
					}
				}
				, error:function(){
					alert("로그인 에러");//이게뜨면 무조건 검사 클릭 오른쪽 글자 누르지말기 ㅇㅇ 500이면 서버에러
				}
				
			});
			
			
			
			
		});
		
		
		
	});
	
	</script>
</body>
</html>