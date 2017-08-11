<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../include/a_header.jsp" %>
<!-- 해더  삽입  [지우지마세여]------------------------------------------------------------------------------------------------->

<!-- 페이지 헤드 라인 : 제목 -->

<head>
     <title>스펙 목록</title>
     
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
             	스펙 목록
          </h1>
         <br>
         <ol class="breadcrumb">
          <!-- 페이지 기록 : 메인에서 부터 현재 페이지 까지의 경로 나열 -->
            <li><a href="#"><i class="fa fa-dashboard"></i> 관리자 메인</a></li>
            <li><a href="#"> 사이트 관리</a></li>
            <li class="active"> 스펙 목록</li>
          </ol>
        </section>
<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
        <section class="content">
          <!-- 세로 길이 수정 -->
          <div class="row">
           <!-- 너비 사이즈 수정  : col-->
           <div class="col-md-11">
           
           <!-- jqgrid 사용  -->
		      <table id="gridList"></table>
		      <div id="gridPager"></div>
		       
              </div><!-- /.col -->
           </div><!-- /.row -->
        </section><!-- /. 작업 공간 끝! -->
<!------------------------------------------------------------------------------------------------------------------->        
      </div><!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->
<!-- 페이징 관련 폼 ----------------------------------------------------------------------->
<!-- 페이징 관련 폼 여기까지입니다. ----------------------------------------------------------------------------------- -->
<!-- 글 읽기 -->
<form name="read" method="post" action="/myPage/AspecList">
	<input type="hidden" name="c_num" value="${read.c_num }" id="c_num"/>
	<input type="hidden" name="nowPage" value="${paging.nowPage}"/>
	<input type="hidden" name="nowBlock" value="${paging.nowBlock}"/>
	<input type="hidden" name="keyfield" value="keyfield" /> 
	<input type="hidden" name="keyword" value="keyword" />
</form>
<form name="frmRead" method="post" action="/myPage/AspecList">
		<input type="hidden" name="c_num" /> <input type="hidden"
			name="keyfield" value="keyfield" /> <input type="hidden"
			name="keyword" value="keyword" />

	</form>	
	
	
<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------> 
<%@ include file="../include/footer.jsp" %>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
<script src="../resources/plugins/jQueryUI/jquery-ui.min.js" type="text/javascript"></script>
<script src="../resources/plugins/jqgrid/js/i18n/grid.locale-kr.js" type="text/javascript"></script>
<script src="../resources/plugins/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<!-- --------------------------------------------------------------------------------------------------- -->
<script>
$().ready(function (){
	$("#gridList")
	.jqGrid({
	        url:'/myPage/specList',
	        datatype: "json",
	        mtype:'POST',
	        //로딩중일때 출력시킬 로딩내용
	        loadtext : '로딩중..',
	        colNames:['자격증 번호','자격증 이름', '발급 기관', '보상 포인트',''],
	        colModel:[
	                {name:'c_num', index:'c_num', width:100, key:true, editable:false}, //primary key가 되는 부분에 설정해 줘야함
	                {name:'c_name', index:'c_name', width:100, editable:true, edittype:"text"},
	                {name:'c_agency', index:'c_agency', width:100, editable:true, edittype:"text"},
	                {name:'c_point', index:'c_point', width:100, editable:true, edittype:"text"},
	                {name:'myac', width:100, search:false, fixed:true, formatter:'actions', formatoptions:{keys:true}},
	        ],
	        //한 페이지에서 보여줄 데이터 갯수
	        rowNum:20,
	        autowidth: true,
	        rowList:[20,30,40,50],
	        pager: $('#gridPager'),
	        sortname: 'c_num',
	        width:'100%',
	        height: '100%',
	        multiselect: true,
		    viewrecords: true,
		    sortorder: "desc",
	    	caption:"자격증 목록",
	    	refresh:true,
	    	editurl:"/myPage/specListEdit",
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
	        cellurl:'/myPage/specListEdit',
	      	beforeSubmitCell : function(rowid, cellname, value) {   // submit 전
	            return {"id":rowid, "cellName":cellname, "cellValue":value}
	        },
	        afterSubmitCell : function(res) {   
	        	//이미 JSON 처리된 데이터는 parseJSON 안해 줘도 됨
	        	alert(res.responseText);
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
			{excel:true,add:true,edit:true,view:true,del:true,search:true,refresh:true},
			{closeAfterAdd:true,reloadAfterSubmit:false,closeOnEscape:true},
			{reloadAfterSubmit:false,closeOnEscape:true},
			{multipleSearch:true,multipleGroup:true, showQuery:true, closeOnEscape:true, onSearch:function(){}},
			{closeOnEscape:true});
	//.jqGrid('filterToolbar',{stringResult:true, searchOnEnter:true, defaultSearch:"cn"}); //검색 필터링 가능
});
</script>


