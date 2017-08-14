<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		window.onload =  function () {

			var msg = "${msg}";
			
			if("complete" == msg){
				alert("신규 회원이 등록 되었습니다.");
				location.href = "/myPage/AmemList";
			}
			else if("error" == msg){
				alert("신규 회원 등록 시 오류 가 발생 했습니다. 다시 등록 해 주세여");
				location.href = "/myPage/Anewmem";
			}
		}
	</script>
</body>
</html>