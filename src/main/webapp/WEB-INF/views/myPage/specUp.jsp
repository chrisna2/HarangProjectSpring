<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../include/header.jsp"%>
<!-- 해더  삽입  [지우지마세여]------------------------------------------------------------------------------------------------->
<!-- 페이지 헤드 라인 : 제목 -->
<head>
<title>스펙 업!</title>

<style type="text/css">
.table-striped>tbody>tr:nth-of-type(odd):hover {
	background-color: white;
	cursor:pointer;
}
</style>

</head>
<!-- 메인 페이지 구역 , 즉 작업 구역 -->
<div class="content-wrapper">
	<!----------------------------------- 메인페이지 헤더 [작업 제목] ------------------------------------------------------------->
	<section class="content-header">
		<h1>
			스펙 업! <small>스펙도 올리고! 포인트도 올리고!</small>
		</h1>
		<ol class="breadcrumb">
			<!-- 페이지 기록 : 메인에서 부터 현재 페이지 까지의 경로 나열 -->
			<li><a href="#"><i class="fa fa-dashboard"></i> 메인</a></li>
			<li><a href="#"> 마이페이지</a></li>
			<li class="active">스펙 업!</li>
		</ol>
	</section>
	<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
	<section class="content">
		<!-- 세로 길이 수정 -->
		<div class="row">
			<!-- 너비 사이즈 수정  : col-->
			<div class="col-md-9">
                <!-- /.box -->
                <!-- 리스트 사용시  -->
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">하랑 도전 리스트!</h3>
                        <div class="box-tools">
                            <div class="input-group">
                                <form action="/myPage/specUp" name="search" method="post">
                                	<button class="btn btn-sm btn-default pull-right">
                                    	<i class="fa fa-search"></i>
                                    </button>
                                    <input type="text" name="keyword"
                                        class="form-control input-sm pull-right" style="width: 150px;"
                                        placeholder="Search" /> 
                                    <select class="form-control input-sm pull-right" name='keyfield' style="width: 150px;">
                                        <option value="c_num" ${keyfield eq 'c_num' ? 'selected' : null }>자격증 번호</option>
                                        <option value="c_name" ${keyfield eq 'c_name' ? 'selected' : null }>자격증 이름</option> 
                                        <option value="c_agency" ${keyfield eq 'c_agency' ? 'selected' : null }>자격증 기관</option>
                                        <option value="c_point" ${keyfield eq 'c_point' ? 'selected' : null }>획득 포인트</option>
                                    </select>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>도전 번호</th>
                                    <th>도전 이름</th>
                                    <th>도전 기관</th>
                                    <th>도전 보상</th>
                                    <th>성공 여부</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${fn:length(aspeclist) eq 0}">
                                    </c:when>
                                    <c:otherwise>
                                    	<!-- java : aspeclist => jsp : aspec -->
                                        <c:forEach var="aspec" items="${requestScope.aspeclist}">
                                                  <c:if test="${aspec.cm_iscomplete eq 'none'}">
                      								<tr class="switchColor" onclick = "wait('${aspec.c_num}')">
		                                                <td>${aspec.c_num}</td>
		                                                <td id="name${aspec.c_num}">${aspec.c_name }</td>
		                                                <td id="agency${aspec.c_num}">${aspec.c_agency}</td>
		                                                <td id="point${aspec.c_num}">${aspec.c_point}</td><!-- 숫자가 들어 가야 하는 자리에 P가 있었음... -->                                                  	 
	                                                    <td>
	                                                    	<input type="hidden" id="image${aspec.c_num}" value="${aspec.cm_image}">
	                                                        <span class="label label-warning">심사 중</span><br><font class="text-yellow" id="regdate${aspec.c_num}">${aspec.cm_regdate}</font>
	                                                    </td>
	                                                </tr>
                                                  </c:if>
                                                  <c:if test="${aspec.cm_iscomplete eq 'complete'}">
                      								<tr class="switchColor" onclick = "check('${aspec.c_num}')">
		                                                <td>${aspec.c_num}</td>
		                                                <td id="name${aspec.c_num}">${aspec.c_name }</td>
		                                                <td id="agency${aspec.c_num}">${aspec.c_agency}</td>
		                                                <td id="point${aspec.c_num}">${aspec.c_point}</td><!-- 숫자가 들어 가야 하는 자리에 P가 있었음... -->                                                     
	                                                    <td>
	                                                       <input type="hidden" id="image${aspec.c_num}" value="${aspec.cm_image}">
	                                                       <span class="label label-success">도전 완료</span><br><font class="text-green" id="comdate${aspec.c_num}">${aspec.cm_completedate}</font>
	                                                    </td>
	                                                <tr>
                                                  </c:if>
                                                  <c:if test="${aspec.cm_iscomplete eq 'return'}">
                      								<tr class="switchColor" onclick = "rechallange('${aspec.c_num}')">
		                                                <td>${aspec.c_num}</td>
		                                                <td id="name${aspec.c_num}">${aspec.c_name }</td>
		                                                <td id="agency${aspec.c_num}">${aspec.c_agency}</td>
		                                                <td id="point${aspec.c_num}">${aspec.c_point}</td><!-- 숫자가 들어 가야 하는 자리에 P가 있었음... -->                                                     
	                                                    <td>
	                                                       <input type="hidden" id="image${aspec.c_num}" value="${aspec.cm_image}">
	                                                       <span class="label label-waring">지급 거부</span><br><font class="text-warning" id="comdate${aspec.c_num}">${aspec.cm_completedate}</font>
	                                                    </td>
	                                                <tr>	                                                
                                                  </c:if>
                                                  <c:if test="${aspec.cm_iscomplete eq null}">
                      								<tr class="switchColor" onclick = "challange('${aspec.c_num}')">                                                  
		                                                <td>${aspec.c_num}</td>
		                                                <td id="name${aspec.c_num}">${aspec.c_name }</td>
		                                                <td id="agency${aspec.c_num}">${aspec.c_agency}</td>
		                                                <td id="point${aspec.c_num}">${aspec.c_point}</td><!-- 숫자가 들어 가야 하는 자리에 P가 있었음... -->                                                     
                                                      	<td class="text-danger"><span class="label label-danger">도전 중</span></td>
                                                    <tr>
                                                  </c:if>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer clearfix">
                     <ul class="pagination pagination-sm no-margin pull-right">
						<c:if test="${pageMaker.prev}">
	                            <li><a href="/myPage/specUp${pageMaker.makeQuery(pageMaker.startPage-1)}">&laquo;</a></li>
	                    </c:if>
	                    <c:forEach begin="${pageMaker.startPage}" 
	                    		   end="${pageMaker.endPage}" 
	                               var="idx">
	                            <li value="${pageMaker.cri.page == idx ? 'class=active' : ''}">
	                          		<a href="/myPage/specUp?page=${idx}">
	                          			${idx}
	                        	   	</a>
	                             </li>
	                   	</c:forEach>
	                    <c:if test="${pageMaker.next && pageMaker.endPage>0}">
	                      <li><a href="/myPage/specUp${pageMaker.makeQuery(pageMaker.endPage+1)}">&raquo;</a></li>
	                    </c:if>
                    </ul>
                    </div>
                </div>
                <!-- /.box -->
                
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</section>
	<!-- /. 작업 공간 끝! -->
	
	
	<!-- 입력 및 수정 자세한정보 확인 -->
	<div class="modal fade" id="mdl_spec_info" data-backdrop="static">
	    <div class="modal-dialog" style="width:80%">
	        <div class="modal-content">
	            <div class="modal-header" align="center">
	                <h5 class="box-title">스펙 정보</h5>
	            </div>
	            <form role="form2" name="challenge" action="/myPage/specUpProc" onsubmit="return checkform()" method="post" enctype="multipart/form-data">
	            <div class="modal-body">
					<input type="hidden" name="m_id" value="${member.m_id}">
	 				<div class="box-body">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-bolt"></i>
								도전 이름</span> <input type="text" name="c_name" class="form-control"
								value="" readonly="readonly">
						</div>
						<div class="input-group">
							<span class="input-group-addon">
							   <i class="fa fa-sort-numeric-desc"></i> 도전 번호
							</span> 
							   <input type="text" name="c_num" class="form-control" value="" readonly="readonly">
						</div>
						<div class="input-group">
							<span class="input-group-addon">
							   <i class="fa fa-money"></i>
								도전 보상
						    </span> 
						    <input type="text" name="c_point" class="form-control" value="" readonly="readonly">
						    <span class="input-group-addon">포인트</span>
						</div>
						<div class="input-group">
							<span class="input-group-addon"><i
								class="fa fa-exclamation-circle"></i> 자격증 기관</span> 
								<input type="text" name="c_agency" class="form-control" value="" readonly="readonly">
						</div>
						<hr>
						<div class="input-group">
							<span class="input-group-addon">
							   <i class="fa fa-file-text"></i> 자격증명서
						    </span> 
						    <span class="input-group-addon"> 
						        <input type="file" id="imgInp" name="file" required="required">
							</span> 
							<span class="input-group-addon bg-gray"> 
							   <img src="../resources/dist/img/noImage.jpg" id="local" class="img-responsive" alt="User Image" />
							   <input type="hidden" id="cm_image" name="cm_image" value="">
							</span>
						</div>
						<br>
					</div>
	            </div>
	            <div class="modal-footer">
	            	<input type="hidden" name="check">
					<input type="submit" class="btn btn-primary" id="btn_submit" value="">
	                <button class="btn btn-primary" data-dismiss="modal">닫기</button>
	            </div>
	            </form>	   
	        </div>
	    </div>
	</div>
	
	<!-- 큰이미지 확인용 모달창 -->
	<div class="modal fade" id="mdl_img_big" data-backdrop="static">
	    <div class="modal-dialog" style="width:80%">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h3>원본 이미지 확인</h3>
	            </div>
	            <div class="modal-body" align="center">
	            	<img id="bigImage" width="100%">
	            </div>
	            <div class="modal-footer">
	                <button class="btn btn-primary" data-dismiss="modal">닫기</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- 모달 끝 -->
	<!------------------------------------------------------------------------------------------------------------------->
</div>
<!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->
<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------>
<%@ include file="../include/footer.jsp"%>
<script>
	///////////////// 페이지 관련 javascript function////////////////////
    function checkform(){
        var challenge =
             confirm("해당 자격증을 올리시 겠습니까? \n 해당 자격증을 심사하는 기간은 \n 최소 일주일, 최대 2주일 정도가 소요 됩니다.");
        if(challange){
           	return true;}
        else{
            return false;}
    }
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

	  //로컬 업로드 사진 불러오기
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#local').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    $("#imgInp").change(function(){
        readURL(this);
    });
    
    //[image] 원본(큰이미지)의 이미지 이름 불러오기
    function getImageLink(fileName){
		
    	var front = fileName.substr(0,12);
    	var end = fileName.substr(14);
    	return front + end;
    }

    function challange(num) {
    	
    	$("#mdl_spec_info").modal('toggle');
    	$("#btn_submit").show();
    	$("#btn_submit").val("스펙등록")
    	
		challenge.c_num.value = num;
		challenge.check.value = "insert";
	    challenge.c_name.value = $("#name"+num).text();
	    challenge.c_point.value = $("#point"+num).text();
	    challenge.c_agency.value = $("#agency"+num).text();
	    
	}
    
    function rechallange(num) {
    	/* 아래 삽질은 초기화 함수로 대체 함
        $("#challenge-box").slideUp();
        $("#check-box").slideUp();
        $("#wait-box").slideUp();
        $("#challenge-box").slideDown();
        */
    	$("#mdl_spec_info").modal('toggle');
        $("#btn_submit").show();
    	$("#btn_submit").val("스펙등록")
        
        challenge.c_num.value = num;
        challenge.check.value = "update";
        challenge.c_name.value = $("#name"+num).text();
        challenge.c_point.value = $("#point"+num).text();
        challenge.c_agency.value = $("#agency"+num).text();
        var $image = $("#image"+num).val();
        challenge.cm_image.value = $image;
        //[image] 작은 이미지 불러 오기
	    $("#local").attr("src", "/displayFile?fileName="+$image);
	  	//[image] 큰 이미지 불러 오기
	    $("#bigImage").attr("src",  "/displayFile?fileName="+getImageLink($image));
	  	
    }
	
    function check(num) {
    	/*
		$("#check-box").slideUp();
		$("#challenge-box").slideUp();
		$("#wait-box").slideUp();
		$("#check-box").slideDown();
		*/
    	$("#mdl_spec_info").modal('toggle');
    	$("#btn_submit").hide();
		
		checkIt.c_num.value = num;
		checkIt.c_name.value = $("#name"+num).text();
	    checkIt.c_point.value = $("#point"+num).text();
	    checkIt.c_agency.value = $("#agency"+num).text();
	    checkIt.cm_completedate.value = $("#comdate"+num).text();
	    var $image = $("#image"+num).val();
	    $("#checkimg").attr("src", "/displayFile?fileName="+$image); 
	    $("#bigImage").attr("src",  "/displayFile?fileName="+getImageLink($image));
	}
	
	
    function wait(num) {
    	/*
		$("#wait-box").slideUp();
		$("#challenge-box").slideUp();
		$("#check-box").slideUp();
		$("#wait-box").slideDown();
		*/
    	$("#mdl_spec_info").modal('toggle');
    	$("#btn_submit").hide();
		
		waitIt.c_num.value = num;
		waitIt.c_name.value = $("#name"+num).text();
	    waitIt.c_point.value = $("#point"+num).text();
	    waitIt.c_agency.value = $("#agency"+num).text();
	    waitIt.cm_regdate.value = $("#regdate"+num).text();
	    var $image = $("#image"+num).val();
	    $("#waitimg").attr("src", "/displayFile?fileName="+$image);
	    $("#bigImage").attr("src",  "/displayFile?fileName="+getImageLink($image));
	}
    
	//[image] 이미지 창을 띄울 모달창 불러오기
    $("#local").click(
	  function(){
	  	 $("#mdl_img_big").modal('toggle');
	});
    $("#checkimg").click(
      function(){
         $("#mdl_img_big").modal('toggle');
    });
    $("#waitimg").click(
      function(){
        $("#mdl_img_big").modal('toggle');
    });
	
</script>
