<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>megagram - 회원가입</title>
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<link rel="stylesheet" href="/static/css/style.css" type="text/css">

</head>
<body>
	<div id="wrap" class="bg-gray">
		<header class="bg-danger">
		</header>
		<section class="bg-primary contents">
			<div class="">
				<h1 class="text-center">회원가입</h1>
				<input type="text" id="loginIdInput" placeholder="로그인 ID" class="form-control mt-4">
				<button type="button" class="btn btn-info btn-block" id="duplicateBtn">중복확인</button>
				<input type="password" id="passwordInput" placeholder="비밀번호" class="form-control mt-4">
				<input type="password" id="passwordConfirmInput" placeholder="비밀번호 확인" class="form-control mt-4">
				<input type="text" id="nameInput" placeholder="이름" class="form-control mt-4">
				<input type="text" id="emailInput" placeholder="이메일" class="form-control mt-4">
				<button type="button" class="btn btn-info btn-block" id="joinBtn">가입</button>
			</div>
		
		</section>
		<footer class="bg-info">
			<div class="">Copyright © megagram 2023</div>
		
		</footer>
	</div>
	
	<script>
	$(document).ready(function(){
		$("#joinBtn").on("click", function(){ 
			let loginId = $("#loginIdInput").val();
			let password = $("#passwordInput").val();
			let passwordConfirm = $("#passwordConfirmInput").val();
			let name = $("#nameInput").val();
			let email = $("#emailInput").val();
			
			$.ajax({
				type:"post"
				, url:"/user/signup"
				, data:{"loginId" : loginId, "password" : password, "name" : name, "email" : email}
				, success:function(data){
					if(data.result == "success"){
						location.href = "/user/signin/view";						
					} else {
						alert("회원가입 실패");
					}
				}
				, error:function(){
					alert("회원가입 에러");
				}
			});
			
			
		});
		
		$("#duplicateBtn").on("click", function(){
			let loginId = $("#loginIdInput").val();
			
			if(loginId == "") {
				alert("아이디를 입력하세요.")
				return ;
			}
			
			
			$.ajax({
				type:"post"
				, url:"/user/duplicate"
				, data:{"loginId":loginId}
				, success:function(data){
					
					
					if(data.is_duplicate){
						alert("아이디가 중복됩니다.");
						
						isDuplicate = true;
					} else {
						alert("사용할수 있는 아이디입니다.");
						isDuplicate = false;
					}
					
				}
				, error:function(){
					alert("추가 에러");
				}
				
			});
			
			
		});
		
		
	});
		
	
	</script>

</body>
</html>