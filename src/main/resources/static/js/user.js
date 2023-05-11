let index = {
	init: function() {
		$("#btn--save").on("click", () => {
			this.save();
		});
		
		$("#btn--login").on("click", () => {
			this.login();
		});
	},
	
	save: function() {
		// 회원가입
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		console.log(data);
		
		$.ajax({
			type: 'POST',
			url: '/api/user',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(data), // HTTP 바디에 담을 데이터
			dataType: 'json' // 응답 시 데이터 타입
			
		}).done(function(res) {
			console.log(res);
			if (res.status == "OK") {
				alert("회원가입 성공");
				// 화면 이동 - 로그인 페이지
				location.href="/loginPage"
			}
			
			// 중복된 사용자 이름이라면
			// 이런 식으로 경우에 따라 다른 값이 반환하도록 할 수 있음
			
		}).fail(function(error) {
			console.log(error);
			
		});
	},
	
	login: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		}
		
		$.ajax({
			type: 'POST',
			url: '/api/user/login',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(data),
			dataType: 'json'
			
		}).done(function(res) {
			// 여기서 아이디/비밀번호 맞는지 등을 확인해서 보낼 것
			if (res.status == "OK") {
				alert("로그인이 완료되었습니다.");
				location.href="/";
			}
			
		}).fail(function(error) {
			alert("로그인이 실패했습니다.");
			
		});
	}
};

index.init();