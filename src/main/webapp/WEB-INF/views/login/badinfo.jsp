<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
window.onload = function(){
	var loginType = "${loginType}";
	var loginError = "${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}";
	var sessionId = "${sessionScope.m_id}"
	
	if(loginType == "bad"){
		if(loginError == "Maximum sessions of 1 for this principal exceeded"){
			alert("중복 로그인입니다. \n"+loginError+"\nID:"+sessionId);
		}
		else{
		    alert("로그인에 실패 하셨습니다. 아이디와 패스워드를 확인해주세요. \n"+loginError+"\nID:"+sessionId);
		}
	}
	else{
		alert(loginError);
	}
	self.location = "/";
}
</script>
</head>
</html>