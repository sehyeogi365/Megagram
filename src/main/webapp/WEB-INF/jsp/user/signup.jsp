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
				<div class="d-flex mt-3">
					<input type="text" id="loginIdInput" placeholder="로그인 ID" class="form-control mt-4">
					<button type="button" class="btn btn-info btn-block" id="duplicateBtn">중복확인</button>
				</div>
				<div class="small text-succes d-none" id="availableText">사용가능한 아이디입니다.</div>
				<div class="small text-danger d-none" id="duplicateText">중복된 아이디입니다.</div>
				
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
		
		var isChecked = false;//전역변수 하나 만들어라. 중복검사 했는지 안했는지 여부 저장
							//check안된상태
							
		var isDuplicateId = true;//id중복여부	중복이되야 진행안되니 중복되는걸 기본값	
		
		
		//중복아이디 검사하고 아이디 교체하자말자 바로 중복됩니다 로 나오게 하려면
		$("#loginIdInput").on("input", function(){
			//중복과 관련된 상태를 모두 초기화시킨다.
			isChecked = false; 
			isDuplicateId = true;//변경된 상태를 다시 첨으로
			
			$("#duplicateText").addClass("d-none");//디논 추가
			$("#availableText").addClass("d-none");//아이디 인풋을 진행했을때 무조건 추가
			//다시 지우면 중복확인 상태가 초기화
			
			
			
			
		});
		
		
		$("#duplicateBtn").on("click", function(){
			let id = $("#loginIdInput").val();//그값에대한 validaiton
			
			if(id == "") {
				alert("아이디를 입력하세요.")
				return ;
			}
			
			
			$.ajax({
				type:"get"
				, url:"/user/duplicate_id"
				, data:{"loginId":id}
				, success:function(data){// 리스폰스를 저장할 변수
					
					isChecked = true;
					isDuplicateId = data.is_duplicate;//가능하면 이렇게하자. 변수값을 통해 값을 저장하는게 더 간결
				
				
					if(data.is_duplicate){//컨텐트타입이 제이슨 형태면. 자바스크립트 객체형태로  변환해서 이변수에저장
						alert("아이디가 중복됩니다.");
						$("#duplicateText").removeClass("d-none");//그반대의 상태의 문구도 숨기는.
						$("#availableText").addClass("d-none");//숨기는거니 되려 추가해야함.
						
						//isDuplicateId = true; //이렇게하지말고
						
					} else {//중복 안되는 상태.
						alert("사용가능 ID");
						$("#availableText").removeClass("d-none");
						$("#duplicateText").addClass("d-none");
						
						//isDuplicateId = false;
						
					}
					
				}
				, error:function(){
					alert("중복확인 에러");
				}
				
			});
			
			
		});
		
		
		$("#joinBtn").on("click", function(){  // 이벤트 이름, 실행 함수
			let id = $("#loginIdInput").val();
			let password = $("#passwordInput").val();
			let passwordConfirm = $("#passwordConfirmInput").val();
			let name = $("#nameInput").val();
			let email = $("#emailInput").val();
			
			//유효성 검사
			if(id == "") {
				alert("아이디를 입력하세요.");
				return ;
			}
			
			if(!isChecked) {//확인이 안된상태.
				alert("아이디 중복확인을 해주세요.");
				return ;
			}
			
			if(isDuplicateId) {
				alert("아이디 중복되었습니다");
				return ;// 서로 공유되어야 하는상태라면 전역변수를 만들고 한쪽에선 기록을 한쪽에선 기록된값을 활용.
			}
			
			if(password == "") {
				alert("비밀번호를 입력하세요.");
				return ;
			}
			
			if(password != passwordConfirm) {//비밀번호 일치
				alert("비밀번호가 일치하지 않습니다");
				return ;
			}
			if(name == "") {
				alert("이름을 입력하세요.");
				return ;
			}
			if(email == "") {
				alert("이메일을 입력하세요.");
				return ;
			}
			
			$.ajax({//아작스라는 메소드를 통해서 api 호출
				type:"post" // 해당하는 메소드
				, url:"/user/signup" // 주소
				, data:{"loginId" : id, "password" : password, "name" : name, "email" : email}//파라미터 이름
				, success:function(data){// 각각 같을이유가 없음 
					if(data.result == "success"){//리스폰스에 전달되는 데이터에 따라서 처리 리스폰스를 저장할변수를 해당하는 함수의 펑션에 파라미터로 지정
						location.href = "/user/signin/view";//로그인페이지로 이동하게 끔						
					} else {//지금은 페이지 이동이된건데 로그인페이지가 없는거뿐 그래서 404가뜬다.
						alert("회원가입 실패");
					}
				}
				, error:function(){
					alert("회원가입 에러");//이거 뜨면 검사눌러서 에러코드 확인 405는 파라미터 문제, 500은 서버에러 콘솔창 확인
				}
			});
			
			
		});
		
		
		
		
	});
		
	
	</script>

</body>
</html>