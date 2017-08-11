<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

	var check = "${check}";
	
	if(check == "complete"){
	    alert("해당 식권을 구매 완료 하였습니다.\n사용하시려면 식권 목록에서 선택해 주세요.");
	    self.location = "/food/ticket";
	}
	else{
		alert("해당 식권을 구매시 오류가 발생 했습니다. 다시 구매 해 주세요");
	    self.location = "/food/menu";
	}

</script>
</head>
</html>