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
						<i id="imageIcon" class="bi bi-card-image image-icon-size"></i><!-- 그림클릭해서 업로드 글씨처럼 크기 키우기 css로-->
						<input type="file" id="fileInput" class="d-none"><br><!-- 글씨안보이게 숨기기 -->
						<a href="" class="btn btn-primary btn-sm" id="uploadBtn">업로드</a>
					
					</div>
				</div>
				
				<!--  /입력박스 -->
				
				
				<!-- 게시글 카드 리스트 -->
				<!-- c태그 반복문 활용 카드 범위도 기니 조심 반복범위도 제대로 잡고  -->
				<c:forEach var="post" items="${postList }"> 
				<div class="card-list mt-4">
					<!-- 게시글 카드 -->
					<div class="card mt-3">
						<!-- https://icons.getbootstrap.com/ -->
							<div class="d-flex justify-content-between p-2"><!-- 여백까지 -->
								<div>${post.loginId}</div><!-- 근데 게시글 올린사용자 정보 불러오려면?? post객체안의 데이터만 불러올수 있는데 어떻게 불러올것인가?? -->
								<div><i class="bi bi-three-dots"></i></div><!-- i태그만 넣으면 안됨 -->
							
							
							</div>
							
							<div><!-- 이미지태그 -->
								<img width=100% src="${post.imagePath }"><!-- 갑자기 이거까지하니 잘된다?? -->
							</div>
							<!-- 하트아이콘 -->
							<div class="p-2"> 
								<i id="likeIcon" class="like-btn bi bi-heart" data-post-id="${post.id }" >좋아요 11개</i> 
							</div>
							<!-- 한번 누르면 검정하트 리무브 두번 누르면 하얀하트  -->
							<div class="p-2"> 
								<i id="fulllikeIcon" class="d-none like-btn bi bi-heart-fill" data-post-id="${post.id }" >좋아요 11개</i> 
							</div>
							
							
							<div class="p-2">
								<b>${post.loginId }</b> ${post.content } 
							</div>
							
							<!-- 댓글 목록 -->
							<c:forEach var="comment" items="${commentList }">
									<div class="p-2">
									
									
										<b>${comment.loginId }</b>${comment.content }<!-- 댓글내용 -->
									</div>
								
							</c:forEach>
							
							<!-- 댓글 박스 -->
							<div class="p-2">
								<div>댓글</div><!-- 설명및 아이디 -->
								
								<div class="d-flex mt-2">
									<input type="text" class="form-control">
									<button type="button" id="commentBtn"class="btn btn-info btn-sm comment-btn" data-comment-id="${comment.id }">게시</button>
								</div>
								
							</div>
							
					</div>
					
					</c:forEach>
					<!-- /게시글 카드 -->
					
					<!-- 게시글 카드 -->
					
					<!-- /게시글 카드 -->
					
				</div>
				
				
				<!-- /게시글 카드 리스트 -->
				
				
				
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp"/>
	</div>
	
	<script>
	$(document).ready(function(){
		
		var ths = $(ths);
		
		ths.parents("");
		
		var id = "id";
		
		//댓글
		$(".comment-btn").on("click", function(){//id기반이아닌 클래스기반으로 할것. 왜냐면 버튼이여러개라서 사실상 댓글이 제일 어렵다고 함 좋아요부터 생각해보기.
			
			let id = $(this).data("comment-id")
			
			let comment = $("#commentInput").val();
			
			
			if(comment == "") {
				alert("댓글을 입력하세요");
				return;
			}
			
			$.ajax({
				type:"post"
					, url:"/post/comment"
					, data:{"userId" : id, "content" : comment}
					, success:function(data){
						
						if(data.result == "success"){
							location.reload();
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
		
		//좋아요
		$(".like-btn").on("click", function(){
			
			let id = $(this).data("post-id");
			
		
			
			$("#likeInput").click();
			alert("좋아요 누름");
			
			
			if(id == likeIcon){//빈하트를 눌렀을때
				alert("좋아요 누름");//좋아요 누름
				$(".bi-heart-fill").removeClass("d-none");//검정하트 add
				$(".bi-heart").addClass("d-none");//하얀하트 remove
				
				$.ajax({//api호출 api문서보면서하기
					type:"get"
					, url : "/post/like"
					, data:{"userId" : userId} //사실상 글올리기랑 유사하다 함
					, success:function(data){
						if(data.result == "success") {
							location.reload();//새로고침하기 가능하면 이렇게하면 깔끔함
						} else{
							alert("좋아요 실패");
						}			
					}
					, error :function(){
						alert("좋아요 에러");
					}
					
					
					
				});
			
			
			} else if(id == fulllikeIcon){ // 꽉찬 하트를 눌렀을때
				alert("좋아요 취소");// 좋아요 취소
				
				$(".bi-heart").removeClass("d-none"); // 하얀하트add
				$(".bi-heart-fill").addClass("d-none"); // 검정하트remove
			
				$.ajax({//api호출 api문서보면서하기
					type:"get"
					, url : "/post/like_delete"
					, data:{"userId" : userId} 
					, success:function(data){
						if(data.result == "success") {
							location.reload();
						} else{
							alert("좋아요취소 실패");
						}			
					}
					, error :function(){
						alert("좋아요 에러");
					}
			
				});	
			
				}
		
		});
		
		
		$("#imageIcon").on("click", function(){
			// file input을 클릭한 동작을 수행한다.
			//객체화시키기
			$("#fileInput").click();
			
			
		});
		
		
		$("#uploadBtn").on("click", function(){//타임라인 업로드 버튼
			
			let content = $("#contentInput").val(); 
			let file = $("#fileInput")[0];// 변수에저장 파일목록을 관리하는 객체를 얻어오는과정
			
			
			if(content == "") {
				alert("내용을 입력하세요");
				return;
			}
			
			//파일 선택되지 않았을 경우의 유효성 검사
			if(file.files.length == 0){
				alert("파일을 선택하세요");
				return ;
			}
			
			
			
			var formData = new FormData();//폼데이터라는 객체활용
			formData.append("content", content);//append로 추가하기
			formData.append("file", file.files[0]);//files라는 배열안에 들어있는거
			
			$.ajax({//api호출 api문서보면서하기
				type:"post"
				, url : "/post/create"
				, data : formData
				, enctype :"multipart/form-data" //인코딩타입
				, processData:false
				, contentType:false
				, success:function(data){
					if(data.result == "success") {
						location.reload();//새로고침하기 가능하면 이렇게하면 깔끔함
					} else{
						alert("글쓰기 실패");
					}
					
					
				}
				, error :function(){
					alert("글쓰기 실패");
				}
				
				
				
			});
			
			
			
		});
		
		
	});

	

	</script>

</body>
</html>