<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>megagram - timeline</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<!-- Option 1: Include in HTML -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
	
	<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	<div id="wrap">
	
		<c:import url="/WEB-INF/jsp/include/header.jsp"/>
		<section class="contents d-flex justify-content-center">
			
			<!-- 타임 라인 -->
			<div class="timeline-box my-5">
				
				
				
				
				<!-- 입력 박스 -->
				<div class="inputbox border rounded"><!-- 둥글게 하고 포함시키기 -->
									<!-- 몇줄 정도 -->												<!-- 기본적으로 폼컨트롤 --><!-- 선없애기 -->
					<textarea cols="20" rows="4" id = "contentInput" placeholder="내용을 입력해주세요." class="mt-4 form-control border-0"></textarea><br>
				
				
					<div class="d-flex justify-content-between mx-2 mb-2">
						<!--버튼 왼쪽 오른쪽 배치 justify-content-between -->
						
						<input type="file" name="file" id="fileInput"><br>
						<a href="" class="btn btn-primary small" id="uploadBtn">업로드</a>
				
					</div>
				</div>
				
				<!--  /입력박스 -->
				
				
				<!-- 게시글 카드 리스트 -->
				<!-- c태그 반복문 활용 -->
				<c:forEach var="post" items="${postList }"> 
				<div class="card-list mt-4">
					<!-- 게시글 카드 -->
					<div class="card">
						<!-- https://icons.getbootstrap.com/ -->
							<div class="d-flex justify-content-between p-2"><!-- 여백까지 -->
								<div>${post.userId}</div>
								<div><i class="bi bi-three-dots"></i></div><!-- i태그만 넣으면 안됨 -->
							
							
							</div>
							
							<div><!-- 이미지태그 -->
								<img width=100% src="https://cdn.pixabay.com/photo/2022/10/20/05/07/lichen-7534074_960_720.jpg">
							</div>
							<!-- 하트아이콘 -->
							<div class="p-2"> 
								<i class="bi bi-heart"></i> 좋아요 11개
							</div>
							<div class="p-2"><!-- 설명및 아이디 -->
								<b>dulumary</b> 이끼
							</div>
							
							<!-- 댓글 박스 -->
							<div class="p-2">
								<div>댓글</div>
								<c:forEach var="comment" items="${commentList }">
									<div><b>hagulu</b>진짜 예쁘네요</div>
									<div><b>bada</b>저도 가보고 싶어요</div>
								</c:forEach>
								<div class="d-flex">
									<input type="text" class="form-control">
									<button type="button" id="commentBtn"class="btn btn-info btn-sm">게시</button>
								</div>
								
							</div>
							
					</div>
					
					</c:forEach>
					<!-- /게시글 카드 -->
					
					<!-- 게시글 카드 -->
					<div class="card mt-3">
						<!-- https://icons.getbootstrap.com/ -->
							<div class="d-flex justify-content-between p-2"><!-- 여백까지 -->
								<div>dulumary</div>
								<div><i class="bi bi-three-dots"></i></div><!-- i태그만 넣으면 안됨 -->
							
							
							</div>
							
							<div><!-- 이미지태그 -->
								<img width=100% src="https://cdn.pixabay.com/photo/2022/10/20/05/07/lichen-7534074_960_720.jpg">
							</div>
							<!-- 하트아이콘 -->
							<div class="p-2"> 
								<i class="bi bi-heart"></i> 좋아요 11개
							</div>
							<div class="p-2"><!-- 설명및 아이디 -->
								<b>dulumary</b> 이끼
							</div>
							
							<!-- 댓글 박스 -->
							<div class="small">
								<div class="p-2">댓글</div>
								
								<div class="px-2"><b>hagulu</b>진짜 예쁘네요</div>
								<div class="px-2"><b>bada</b>저도 가보고 싶어요</div>
								
								<div class="d-flex">
									<input type="text" class="form-control" id = "commentInput">
									<button type="button" id="commentBtn" class="btn btn-info btn-sm" >게시</button>
								</div>
								
							</div>
							
					</div>
					<!-- /게시글 카드 -->
					
				</div>
				
				
				<!-- /게시글 카드 리스트 -->
				
				
				
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp"/>
	</div>
	
	<script>
	$(document).ready(function(){
		
		
		$("#uploadBtn").on("click", function(){
			
			let content = $("#contentInput").val(); 
			let file = $("#fileInput")[0];
			
			
			if(content.trim() == "") {
				alert("내용을 입력하세요");
				return;
			}
			
			var formData = new FormData();
			formData.append("content", content);
			formData.append("file", file.files[0]);
			
			$.ajax({
				type:"post"
				, url : "/post/create"
				, data : formData
				, enctype :"multipart/form-data"
				, processData:false
				, contentType:false
				, success:function(data){
					if(data.result == "success") {
						location.href="/post/timeline/view";
					} else{
						alert("글쓰기 실패");
					}
					
					
				}
				, error :function(){
					alert("글쓰기 실패");
				}
				
				
				
			});
			
			
			
		});
		
		
		
		$("#commentBtn").on("click", function(){
			
			let comment =$("#commentInput").val();
			
			
			if(comment == "") {
				alert("댓글을 입력하세요");
				return;
			}
			
			$.ajax({
				type:"post"
					, url:"/post/comment"
					, data:formData //여기다가 그대로 넣어주기
					, data:{"userId" : userId, "content" : content}
					, success:function(data){
						
						if(data.result =="success"){
							location.href="/post/timeline/view";
							alert("댓글 성공");
						} else {
							alert("댓글 오류");
						}
					}
					, error:function(){
						alert("댓글 에러");
					}
				
			});
			
			
			
			
			
		});
		
		
	});
	</script>

</body>
</html>