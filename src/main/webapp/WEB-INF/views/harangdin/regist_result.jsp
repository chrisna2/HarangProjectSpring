<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script>
	var result = "${result}";

	if("sell_complete" == result){
		alert("판매 신청이 되었습니다.");
		location.href="/harangdin/main";
	}
	else if("sell_fail" == result){
		alert("판매 신청 과정 중 오류가 발생 했습니다.");
		location.href="/harangdin/main";
	}
	else if("donate_complete" == result){
		alert("기부 신청이 되었습니다");
		location.href="/harangdin/main";
	}
	else if("donate_fail" == result){
		alert("기부 신청 과정 중 오류가 발생 했습니다.");
		location.href="/harangdin/main";
	}
	if("hunt_complete" == result){
		alert("구매 신청이 되었습니다.");
		location.href="/harangdin/main";
	}
	else if("hunt_fail" == result){
		alert("구매 신청 과정 중 오류가 발생 했습니다.");
		location.href="/harangdin/main";
	}
	if("delete_complete" == result){
		alert("삭제가 완료 되었습니다.");
		location.href="/harangdin/main";
	}
	else if("delete_fail" == result){
		alert("이미 거래가 진행중인 도서 입니다.");
		location.href="/harangdin/main";
	}
	if("trade1_complete" == result){
		alert("거래 신청이 완료 되었습니다.");
		location.href="/harangdin/main";
	}
	else if("trade1_fail" == result){
		alert("거래 신청중 오류가 발샐했습니다.");
		location.href="/harangdin/main";
	}
	if("trade2_complete" == result){
		alert("최종 거래가 완료 되었습니다.");
		location.href="/harangdin/main";
	}
	else if("trade2_fail" == result){
		alert("최종 거래중 오류가 발샐했습니다.");
		location.href="/harangdin/main";
	}
	if("sell_update_complete" == result){
		alert("판매 수정이 완료 되었습니다.");
		location.href="/harangdin/main";
	}
	else if("sell_update_fail" == result){
		alert("판매 수정중 오류가 발샐했습니다.");
		location.href="/harangdin/main";
	}
	if("donate_update_complete" == result){
		alert("기부 수정이 완료 되었습니다.");
		location.href="/harangdin/main";
	}
	else if("donate_update_fail" == result){
		alert("기부 수정중 오류가 발샐했습니다.");
		location.href="/harangdin/main";
	}
	if("admin_delete_complete" == result){
		alert("삭제가 완료 되었습니다.");
		location.href="/harangdin/adminMain";
	}
	else if("admin_delete_fail" == result){
		alert("이미 거래 중입니다. 삭제가 불가 합니다.");
		location.href="/harangdin/adminMain";
	}
	if("admin_donate_delete_complete" == result){
		alert("삭제가 완료 되었습니다.");
		location.href="/harangdin/adminDonate";
	}
	else if("admin_donate_delete_fail" == result){
		alert("삭제가 불가 합니다.");
		location.href="/harangdin/adminDonate";
	}
	if("donate_complete" == result){
		alert("기부가 완료 되었습니다.");
		location.href="/harangdin/adminDonate";
	}
	else if("donate_fail" == result){
		alert("기부중 오류가 발생 했습니다.");
		location.href="/harangdin/adminDonate";
	}
	
</script>
</body>
</html>
