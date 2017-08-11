<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <script type="text/javascript">
    var result = "${result}";
    
    if(result == "refund"){
    	alert("해당 식권이 환불 되었습니다.");
        location.href="/food/ticket";
    }
    else if(result == "used"){
    	alert("해당 식권이 사용 되었습니다.");
    	location.href="/food/ticket";
    	
    }
    else if(result == "error"){
    	alert("처리과정중 오류가 발생햇습니다.");
    	location.href="/food/ticket";
    }
    </script>
</body>
</html>