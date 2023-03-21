<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header class="d-flex align-items-center justify-content-between">
		<h1 class="ml-3">megagram</h1>
		
		<c:if test = "${not empty userName}">
		<div class="mr-3">   						<!-- 자바코드였다면 세션 객체를 통해서 getAttribute 메소드를 통해서 값을 얻어옴-->
			${userName } 님 <a href="/user/signout">로그아웃</a><!-- jsp 안에서 세션을 간단하게 가져오는법 el태그-->
		</div>	<!-- 세션삭제및 로그인페이지 이동 -->
		</c:if>
		
		</header>

</body>
</html>