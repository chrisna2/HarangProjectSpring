<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../include/a_header.jsp" %>
<!-- 해더  삽입  [지우지마세여]------------------------------------------------------------------------------------------------->
<!-- 페이지 헤드 라인 : 제목 -->
<head>
     <title>수업 등록 수정</title>
<c:if test="${result eq 'insert_complete'}">
    <script type="text/javascript">
    alert("신규 수업이 등록 되었습니다.");
    </script>
</c:if>   
<c:if test="${result eq 'insert_fail'}">
    <script type="text/javascript">
    alert("신규 수업이 등록 실패 했습니다.");
    </script>
</c:if>
<c:if test="${result eq 'update_complete'}">
    <script type="text/javascript">
    alert("해당 수업이 수정 되었습니다.");
    </script>
</c:if>
<c:if test="${result eq 'update_fail'}">
    <script type="text/javascript">
    alert("해당 수업이 수정 실패 했습니다.");
    </script>
</c:if> 
<!-- jqgrid CSS 파일 링크 -->
<!-- 테마 css -->
	<link rel="stylesheet" type="text/css" media="screen" href="../resources/plugins/jQueryUI/themes/cupertino/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="../resources/plugins/jqgrid/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="../resources/plugins/jqgrid/css/ui.jqgrid-bootstrap-ui.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="../resources/plugins/jqgrid/css/ui.jqgrid-bootstrap.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="../resources/plugins/jqgrid/src/css/ui.multiselect.css" />
</head>
	  <!-- 메인 페이지 구역 , 즉 작업 구역 -->
      <div class="content-wrapper">
<!----------------------------------- 메인페이지 헤더 [작업 제목] ------------------------------------------------------------->
        <section class="content-header">
          <h1>
             	수업 등록 수정
          </h1>
         <br>
         <form name="newlesson" action="/myPage/Anewlesson" method="post">
            <input type="submit" class="btn btn-success col-md-9 col-xs-9" value="수업 신규 등록">
         </form>
         <br>
         <br>
         <ol class="breadcrumb">
          <!-- 페이지 기록 : 메인에서 부터 현재 페이지 까지의 경로 나열 -->
            <li><a href="#"><i class="fa fa-dashboard"></i> 관리자 메인</a></li>
            <li><a href="#"> 사이트 관리</a></li>
            <li class="active"> 수업 등록 수정 </li>
          </ol>
        </section>
<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
        <section class="content">
          <!-- 세로 길이 수정 -->
          <div class="row">
           <div class="col-md-9">
		          
		      <!-- jqgrid 사용  -->
		      <table id="gridList"></table>
		      <div id="gridPager"></div>
		          
              </div><!-- /.col -->
           </div><!-- /.row -->
        </section><!-- /. 작업 공간 끝! -->
<!------------------------------------------------------------------------------------------------------------------->        
      </div><!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->
<!-- 수업 수정 : 수업 시간 수정 -->
<form id="update" name="update" method="post" action="/HarangProject/myPage?cmd=Aupdatelesson">
    <input type="hidden" name="l_num" value=""> 
</form>
      
<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------> 
<%@ include file="../include/footer.jsp" %>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
<script src="../resources/plugins/jQueryUI/jquery-ui.min.js" type="text/javascript"></script>
<script src="../resources/plugins/jqgrid/js/i18n/grid.locale-kr.js" type="text/javascript"></script>
<script src="../resources/plugins/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script>
//페이징
function prevPage() {
    document.getElementById("prevPage").submit();
}
function nextPage() {
    document.getElementById("nextPage").submit();
}
function goPage(nowPage) {
    document.getElementById("page").value = nowPage;
    document.getElementById("goPage").submit();
}
function updatelesson(l_num) {
	update.l_num.value = l_num;
    update.submit();
}


$().ready(function (){
	$("#gridList")
	.jqGrid({
	        url:'/myPage/aLessonList',
	        datatype: "json",
	        mtype:'POST',
	        //로딩중일때 출력시킬 로딩내용
	        loadtext : '로딩중..',
	        colNames:['수업 번호','주요 학과', '필수', '수업명', '학년', '학기', '요일', '시간', '교수님', '강의실', '학점', ''],
	        colModel:[
	                {name:'l_num', 		index:'l_num', key:true, editable:false, width:120}, //primary key가 되는 부분에 설정해 줘야함
	                {name:'l_dept', 	index:'l_dept', editable:true, edittype:"select", editoptions:{value:"국어국문학과:국어국문학과;컴퓨터공학과:컴퓨터공학과;산업디자인학과:산업디자인학과;수학과:수학과;경영학과:경영학과;교양학과:교양학과"}, width:150},
	                {name:'l_ismust', 	index:'l_ismust', editable:true, edittype:"select", editoptions:{value:"필수:필수;선택:선택"}, width:70},
	                {name:'l_name', 	index:'l_name', editable:true, edittype:"text", width:150},
	                {name:'l_grade', 	index:'l_grade', editable:true, edittype:"select", editoptions:{value:"1:1;2:2;3:3;4:4"}, width:50},
	                {name:'l_term', 	index:'l_term', editable:true, edittype:"select", editoptions:{value:"1:1;2:2"}, width:50},
	                {name:'l_day', 		index:'l_day', editable:true, edittype:"select", editoptions:{value:"월:월;화:화;수:수;목:목;금:금"}, width:50},
	                {name:'l_time', 	index:'l_time', editable:true, edittype:"text", width:50},
	                {name:'l_teacher', 	index:'l_teacher', editable:true, edittype:"text", width:70},
	                {name:'l_room', 	index:'l_room', editable:true, edittype:"text", width:150},
	                {name:'l_credit', 	index:'l_credit', editable:true, edittype:"text", width:50},
	                {name:'myac', 		search:false, formatter:'actions', formatoptions:{keys:true}, width:100},
	        ],
	        //한 페이지에서 보여줄 데이터 갯수
	        rowNum:20,
	        autowidth: true,
	        rowList:[20,30,40,50],
	        pager: $('#gridPager'),
	        sortname: 'l_num',
	        width:'100%',
	        height: '100%',
	        multiselect: true,
		    viewrecords: true,
		    sortorder: "desc",
	    	caption:"수업 목록 수정",
	    	refresh:true,
	    	editurl:"/myPage/aLessonListEdit",
	    	afterSubmit : function(res){ 
	    		
	    	//이미 JSON 처리된 데이터는 parseJSON 안해 줘도 됨
	        	alert(res.responseText);
	        	// 변경 후
	           	var aResult = res.responseText;
	        	var userMsg = "수정시 오류가 발생 했습니다.";
	        	
	        	if((aResult == "success")){
	        		userMsg = "수정되었습니다.";
	        	}
	        	
	        	return [(aResult == "success") ? true : false, userMsg];

	    	}, 
	    	//하나의 샐 만 수정 할 경우 : 새로운 방식
	    	//이슈 1] 새로 만든 키 값같은 경우는 입력하고 바로 나타 나지 않는다.
	    	cellEdit:true,
	    	cellsubmit:'remote',
	        cellurl:'/myPage/aLessonListEdit',
	      	beforeSubmitCell : function(rowid, cellname, value) {   // submit 전
	            return {"id":rowid, "cellName":cellname, "cellValue":value}
	        },
	        afterSubmitCell : function(res) {   
	        	//이미 JSON 처리된 데이터는 parseJSON 안해 줘도 됨
	        	//alert(res.responseText);
	        	// 변경 후
	           	var aResult = res.responseText;
	        	var userMsg = "수정시 오류가 발생 했습니다.";
	        	
	        	if((aResult == "success")){
	        		userMsg = "수정되었습니다.";
	        	}
	        	
	        	return [(aResult == "success") ? true : false, userMsg];

	       }
	})
	.jqGrid('navButtonAdd','#gridPager',
			{caption:"Excel",onClickButton: function(){$("#gridList").jqGrid('excelExport',{url:'/myPage/specList/excel'})} //엑셀 출력기능(아직 미구현)
	})
	.jqGrid('navGrid','#gridPager',
			{excel:true,edit:true,view:true,del:true,search:true,refresh:true},
			{closeAfterAdd:true,reloadAfterSubmit:false,closeOnEscape:true},
			{reloadAfterSubmit:false,closeOnEscape:true},
			{multipleSearch:true,multipleGroup:true, showQuery:true, closeOnEscape:true, onSearch:function(){}},
			{closeOnEscape:true});
	//.jqGrid('filterToolbar',{stringResult:true, searchOnEnter:true, defaultSearch:"cn"}); //검색 필터링 가능
});



</script>