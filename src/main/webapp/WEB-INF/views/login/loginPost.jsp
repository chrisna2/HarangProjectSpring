<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		var loginType = "${loginType}"
		
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
		if(loginType == "bad"){
			alert("로그인 정보가 잘못 되었습니다. 다시 입력해 주세요");
			self.location = "/";
		}
	</script>
</body>
</html>