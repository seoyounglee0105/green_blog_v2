<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>form �׽�Ʈ</h1>
		<form action="/temp/join2" method="post">
			<div class="form-group">
				<label for="username">username : </label>
				<input type="text" id="username" name="username" class="form-control" value="��1">
			</div>
			<div class="form-group">
				<label for="password">password : </label>
				<input type="password" id="password" name="password" class="form-control" value="1234">
			</div>
			<div class="form-group">
				<label for="email">email : </label>
				<input type="text" id="email" name="email" class="form-control" value="os01031@naver.com">
			</div>
		</form>
			<button class="btn btn-primary" id="join--submit">ȸ������</button>
			<button class="btn btn-primary" id="update--submit">ȸ����������</button>
			<button class="btn btn-primary" id="delete--submit">ȸ��Ż��</button>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#update--submit").on("click", () => {
				let data = {
						password : $("#password").val(),
						id : 3
				};
				$.ajax({
					type: 'PUT',
					url: '/temp/update',
					contentType: 'application/json; charset=utf-8',
					data: JSON.stringify(data),
					dataType: 'json'
					
				}).done((response) => {
					alert("��� ����");
					
				}).fail((error) => {
					alert("��� ����");
					
				});
			});
			
			$("#delete--submit").on("click", () => {
				$.ajax({
					type: 'DELETE',
					url: `/temp/delete?id=3`,
					contentType: 'application/json; charset=utf-8'
					
				}).done((response) => {
					alert("��� ����");
					
				}).fail((error) => {
					alert("��� ����");
					
				});
			});
			
		});
	
	</script>
	
	
	
	<script>
	
		$(document).ready(function() {
			
			$("#join--submit").on("click", () => {
				// MIME TYPE : application/json
				// js --> json ���ڿ��� �����ϴ� ����
				// :: object --> JSON ���ڿ��� ����
				let data = {
						username : $("#username").val(),
						password : $("#password").val(),
						email : $("#email").val()
				};
				console.log(JSON.stringify(data)); // JSON ���ڿ��� ��ȯ��
				
				$.ajax({
					type: 'POST',
					url: '/temp/join2',
					contentType: 'application/json; charset=utf-8',
					data: JSON.stringify(data), // body�� ���� �� 
					dataType: 'json'
					
				}).done(function(response) {
					console.log(response);
					console.log(typeof response);
					alert("��� ����");
					location.href = "/temp/index";
					
				}).fail(function(error) {
					alert("��� ����");
					
				});
				
			});
			
		});
	</script>
	
	
	
</body>
</html>