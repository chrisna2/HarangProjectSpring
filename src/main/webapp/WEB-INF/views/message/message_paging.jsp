<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- ----------------------------------------------------------------------- -->
				
				<div class="mailbox-controls">
                    <!-- Check all button -->
                    <button class="btn btn-default btn-sm checkbox-toggle" onclick="fnCheck()"><i class="fa fa-square-o"></i></button>                    
                    <div class="btn-group">
                      <button class="btn btn-default btn-sm" onclick="fnDel('${tab}')"><i class="fa fa-trash-o"></i></button>
                    </div><!-- /.btn-group -->
                    <button class="btn btn-default btn-sm" onclick="fnRefresh('${tab}')"><i class="fa fa-refresh"></i></button>
                    <!-- 페이징 버튼 -->
               </div>
               
<!-- ------------------------------------------------------------------------------------- -->     

<form name="del" id="del" method="post" action="">
	<input type="hidden" name="tab" value="${tab}"/>
	<input type="hidden" name="deleteList" value="" id="deleteList"/>
</form>
<!-- -------------------------------------------------------------------------------------- -->
<script>
function fnRefresh(tab){
	location.href="/message/"+tab;
}

function fnCheck(){
	$(":checkbox").attr('checked','checked');
}
function fnDel(tab){
	if(confirm("정말 삭제하시겠습니까?") == true){
		var arr=[];
		$(":checkbox:checked").each(function(){
			arr.push($(this).val());
		});
		document.getElementById("deleteList").value = arr;
		document.del.action ="/message/delete"+tab;
		$("#del").submit();
	}else{
		return;
	}
}
</script>          