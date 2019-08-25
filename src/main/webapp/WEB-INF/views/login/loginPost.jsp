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
		var sessionId = "${sessionScope.m_id}"
		var dest = "${sessionScope.dest}"
		
		if(loginType == "member"){
			alert("회원으로 로그인 되셨습니다."+"\nID:"+sessionId);
			if(dest!=null&&dest!=""){
				self.location = dest;
			}
			else{
				self.location = "/base/main";
			}
		}
		if(loginType == "admin"){
			alert("관리자로 로그인 되셨습니다."+"\nID:"+sessionId);
			if(dest!=null&&dest!=""){
				self.location = dest;
			}
			else{
				self.location = "/base/a_main";
			}			
		}
		if(loginType == "newbee"){
			alert("신입은 개인정보를 입력해 주세요"+"\nID:"+sessionId);
			self.location = "/base/regform";
		}
		if(loginType == "confirm"){
			alert("회원 등록이 완료 되었습니다.\n 다시 로그인해 주세요."+"\nID:"+sessionId);
			self.location = "/";
		}
		if(loginType == "bad"){
			if(loginError == "Maximum sessions of 1 for this principal exceeded"){
				alert("중복 로그인입니다. \n"+loginError+"\nID:"+sessionId)
				self.location = "/";
			}
			else{
			    alert("로그인에 실패 하셨습니다. 아이디와 패스워드를 확인해주세요. \n"+loginError+"\nID:"+sessionId);
				self.location = "/";
			}
		}
	</script>
</body>
</html>