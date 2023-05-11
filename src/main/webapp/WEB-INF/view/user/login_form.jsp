<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
	<h1>여기는 로그인 화면</h1>
	
	<div class="container">
		<form action="#" method="post">
			<div class="form-group">
				<label for="username">username : </label>
				<input type="text" id="username" name="username" class="form-control" value="양이">
			</div>
			<div class="form-group">
				<label for="password">password : </label>
				<input type="password" id="password" name="password" class="form-control" value="1234">
			</div>
		</form>
			<button class="btn btn-primary" id="btn--login">로그인</button>
	</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>