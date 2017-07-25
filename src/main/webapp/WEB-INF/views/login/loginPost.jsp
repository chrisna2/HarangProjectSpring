<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		var loginType = "${loginType}";
		var loginError = "${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}";
		
		if(loginType == "member"){
			alert("회원으로 로그인 되셨습니다.");
			self.location = "/login/main";
		}
		if(loginType == "admin"){
			alert("관리자로 로그인 되셨습니다.");
			self.location = "/login/a_main";
		}
		if(loginType == "newbee"){
			alert("신입은 개인정보를 입력해 주세요");
			self.location = "/login/regform";
		}
		if(loginType == "confirm"){
			alert("회원 등록이 완료 되었습니다.\n 다시 로그인해 주세요.");
			self.location = "/";
		}
		if(loginType == "bad"){
			if(loginError == "Maximum sessions of 1 for this principal exceeded"){
				alert("중복 로그인입니다.")
			}
			else{
				alert("아이디와 패스워드를 확인하새요.")
			}
			
			self.location = "/";
		}
	</script>
	
	<h1>로그인 확인 페이지!</h1>
	
</body>
</html>