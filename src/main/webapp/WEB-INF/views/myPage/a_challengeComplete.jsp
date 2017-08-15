<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result eq 'complete'}">
    <script type="text/javascript">
    alert("해당 회원에게 포인트가 지급 되었습니다.");
    //새로 접속 해줘야 하는 이유.. forward에 작업 기록이 남는다.
    location.href = "/myPage/Achallenge";
    </script>
</c:if>   
<c:if test="${result eq 'return'}">
    <script type="text/javascript">
    alert("해당 대상의 자격증을 등록 취소했습니다.");
    location.href = "/myPage/Achallenge";
    </script>
</c:if>
</body>
</html>