<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
	<h1>여기는 회원가입 화면</h1>
	
		<div class="container">
		<form action="/auth/userProc" method="post">
			<div class="form-group">
				<label for="username">username : </label>
				<input type="text" id="username" name="username" class="form-control" value="양이">
			</div>
			<div class="form-group">
				<label for="password">password : </label>
				<input type="password" id="password" name="password" class="form-control" value="1234">
			</div>
			<div class="form-group">
				<label for="email">email : </label>
				<input type="text" id="email" name="email" class="form-control" value="os01031@naver.com">
			</div>
			<button type="submit" id="btn--save" class="btn btn-primary">회원가입</button>
		</form>
	</div>
	
<!-- <script src="/js/user.js"></script> -->
	
<%@ include file="../layout/footer.jsp" %>