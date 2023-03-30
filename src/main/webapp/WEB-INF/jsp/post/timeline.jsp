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
								
								<c:if test="${userId eq post.userId }"><!-- 게시글 유저아이디 로그인아이디 일치여부 확인 -->
								<!-- data-toggle="modal" data-target="#moreModal" --><!-- 이렇게하면 남이쓴거는 ...이안보인다. -->
									
									<div><i class="bi bi-three-dots more-icon" data-post-id="${post.id}" data-toggle="modal" data-target="#moreModal"></i></div><!-- i태그만 넣으면 안됨 -->
								</c:if>																					<!-- 이렇게 아이디 값 부여해야줘야 나온다. -->
							
							</div>
							
							<div><!-- 이미지태그 -->
								<img width=100% src="${post.imagePath }"><!-- 갑자기 이거까지하니 잘된다?? -->
							</div>
							<!-- 하트아이콘 -->
							<div class="p-2"> <!-- 객체화시키기 여러개 할려면? 클래스에다가 속성 부여. --><!-- 태그에다가 값 부여할려면? 역시나 data 여기다가 이름 부여해당게시글 post id -->
								<!-- 빈하트인지아닌지 판단 --><!-- 하트 빨갛게 -->	
								<c:choose>
									<c:when test="${post.like}">								<!-- 속성에 숫자나 대문자가 들어가면?? -->
										<i  class="unlike-icon bi bi-heart-fill text-danger" data-post-id="${post.id }" ></i>좋아요 ${post.likeCount }개
									</c:when>			
																	
									<c:otherwise>
										<i  class="like-icon bi bi-heart" data-post-id="${post.id }" ></i>좋아요 ${post.likeCount }개 
									</c:otherwise>
									
								</c:choose>
								
								<!-- 여기다가 좋아요 ${post.likeCount }개  할것. -->
							</div>
							<!-- 한번 누르면 검정하트 리무브 두번 누르면 하얀하트  -->
					
							
							
							<div class="p-2">
								<b>${post.loginId }</b> ${post.content } 
							</div>
							
						
							
							
							<!-- 댓글 박스 -->
							
							<div class="small">
							
								<div class="p-2">댓글</div><!-- 설명및 아이디 --><!-- 모델안의 객체를 불러오는것 PostDetail.java-->
								<c:forEach var="comment" items="${post.commentList }"><!-- 리스트 안에있는 객체체 안에있는 그값?? -->
									<div class="px-2"><!-- 댓글내역이 각 포스트 마다 똑같이 뜬다?? 그러면 어떡하나? 그리고 로그인한 아이디의 댓글만 보인다 -->
										<b>${comment.userLoginId }</b>${comment.content }<!-- 댓글내용 --><!-- 게시물 별로 댓글 조회하기 -->
									</div>
								
								</c:forEach>
								<div class="d-flex mt-2">
									<input type="text" class="form-control comment-input" id ="commentInput${post.id}">
									<button type="button" id="commentBtn"class="btn btn-info btn-sm comment-btn" data-post-id="${post.id }">게시</button>
								</div>
								
								</div>
								
						</div>
					
						</c:forEach>
					
					<!-- / 댓글 박스 -->
					
					<!-- /게시글 카드 -->
					
					<!-- 게시글 카드 -->
					
					<!-- /게시글 카드 -->
					
				</div>
				
				
				<!-- /게시글 카드 리스트 -->
				
				
				
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp"/>
		
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-post-id="${post.id }" data-bs-toggle="modal" data-target="#moreModal"><!-- 버튼태그의 두개의 속성을 아이디에 잘맞춰주면 동작하는구나 -->
		  Launch demo modal
		</button>
		
		<!-- Modal -->
		<div class="modal fade" id="moreModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		    
		
		      <div class="modal-body text-center">
		       	<a href="#" id="deleteBtn">삭제하기</a> <!-- 동떨어진 하나의 태그기때문에 쓸수 있는정보가 암것도 없다. -->
		      </div><!-- 객체화시켜야 하므로 아이디 부여 --><!-- 속성을 동적으로 추가할려면? -->
		      
		   
		    </div>
		  </div>
		</div>

	<script>
	$(document).ready(function(){
		
		var ths = $(ths);
		
		ths.parents("");
		
		var id = "id";
		
		
		$("#deleteBtn").on("click", function(){//모달 삭제 버튼
			let postId = $(this).data("post-id");
		
			$.ajax({//디비, 실제사진파일까지 삭제됐는지 확인
				
				type:"get"
				, url:"/post/delete"
				, data:{"postId":postId}
				, success:function(data) {
					if(data.result == "success"){
						location.reload();
					} else {
						alert("삭제 실패");
					}
					
				}
				, error:function(){
					alert("삭제 에러");
				}
			
				
			});
			
		});
		
		
		
		//삭제
		$(".more-icon").on("click", function(){
			
			let postId = $(this).data("post-id");//클릭한 그값
	
			
			// data-post-id = "postId" 
			$("#deleteBtn").data("post-id", postId);// , 뒤에 저장하고픈 값 추가  삭제버튼에 추가
			//

			<!--모달 하느라 삭제기능은 당분간 없애기 -->
			/*
		
			
			*/
			
		});
		
		
		//댓글
		$(".comment-btn").on("click", function(){//id기반이아닌 클래스기반으로 할것. 왜냐면 버튼이여러개라서 사실상 댓글이 제일 어렵다고 함 좋아요부터 생각해보기.

			let postId = $(this).data("post-id");
			
			//let comment = $(".comment-input").val();//이렇게 하면 같은 알람이 뜨게 됨
			// 버튼에 매칭 된 input 태그를 객체화 시켜라!!
			
			// 버튼 바로 앞 태그를 객체화 한다
			let comment = $(this).prev().val();
			//id 를 하자면? 당연히 정상작동안됨 하나만 정상작동 그럼 안똑같게 값을 부여 하자면? ${post.id} 이렇게 id값 부여
			//let comment = ("#commentInput" + postId).val(); //여기도 마찬가지로 값을 다르게 해야 한다. postId 셀렉터 부여
					
			
			
			alert(comment);
			
			$.ajax({
				type:"post"
				, url:"/post/comment/create"
				, data:{"postId":postId, "content":comment}
				, success:function(data) {
					if(data.result == "success"){
						location.reload();
					} else {
						alert("댓글 쓰기 실패");
					}
					
				}
				, error:function(){
					alert("댓글 쓰기 에러");
				}
				
				
			});
			
			
			

		});
		
		//싫어요
		$(".unlike-icon").on("click", function(){
			let postId = $(this).data("post-id");//데이터 메소드는 데이터로
			
			$.ajax({
				
				type:"get"
				, url : "/post/unlike"
				, data:{"postId" : postId}
				, success:function(data){
					
					if(data.result == "success"){
						location.reload();//리프레쉬 시키기
					} else {
						alert("좋아요 취소 실패");
					}
					
				}
				, error:function(){
					alert("좋아요 취소 에러");
				}
			});
			
			
			
		});
		
		
		//좋아요
		$(".like-icon").on("click", function(){//하나가아닌 여러개일수 있기땜에 배열형태로. 여러태그의 이벤트 모두 등록
			
			let postId = $(this).data("post-id");//지금 클릭한것을 객체화 this 키워드로 현재 클릭된 태그를 객체화
			
			
			alert(postId);//내가 클릭한 그 값
			
			//if else 하지말란뜻.
		
				//채워진하트냐 안채워진 하트냐 조회하는것부터 구현 ㄱㄱ.
				
				//getAttribute null = 무조건 로그인 안되서 생긴 에러.
				$.ajax({//api호출 api문서보면서하기
					type:"get"
					, url : "/post/like"
					, data:{"postId" : postId} //사실상 글올리기랑 유사하다 함
					, success:function(data){
						if(data.result == "success") {
							location.reload();//새로고침하기 가능하면 이렇게하면 깔끔함
						} else{
							alert("좋아요 실패");
						}			
					}
					, error : function(){
						alert("좋아요 에러");
					}					
					
				});//괄호문제 땜에 좋아요가 안먹혔던것. 가장 근본적인 부분 이 여기라서 여기를 확인했어야 했다는것.
			
				
				
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