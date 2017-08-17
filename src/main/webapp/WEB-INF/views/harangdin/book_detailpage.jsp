<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp" %>
<!-- 해더  삽입  [지우지마세여]------------------------------------------------------------------------------------------------->
<!-- 페이지 헤드 라인 : 제목 -->
<head>
     <title>기본 값 페이지</title>
     <script>
     	function formcheck(){
     		var max_point = apply.max_point.value;
     		var inputpoint = apply.bh_want.value;
     		if(0 > inputpoint - max_point){
     			alert("최고포인트보다 입력포인트가 작습니다");
     			return false;
     		}
     		return true;
     	}
     	function checkpoint(){
     		var max_point = apply.max_point.value;
     		if(max_point == 0){
     			alert("아직 구매자가 없습니다.");
     			return false;
     		}
     		return true;
     	}
     </script>
</head>
	  <!-- 메인 페이지 구역 , 즉 작업 구역 -->
      <div class="content-wrapper">
<!----------------------------------- 메인페이지 헤더 [작업 제목] ------------------------------------------------------------->
        <section class="content-header">
          <h1>
             	중고도서 상세페이지
            <small>거래중인 중고도서의 상세정보를 알 수 있습니다.</small>
          </h1>
          <ol class="breadcrumb">
          <!-- 페이지 기록 : 메인에서 부터 현재 페이지 까지의 경로 나열 -->
            <li><a href="#"><i class="fa fa-dashboard"></i> 메인</a></li>
            <li class="active">하랑딘24</li>
            <li class="active">중고도서 상세페이지</li>
          </ol>
        </section>
<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
        <section class="content">
          <!-- 세로 길이 수정 -->
          <div class="row">
           <!-- 너비 사이즈 수정  : col-->
           <div class="col-md-10">
	        
	        <div class="box box-black">
	            <div class="box-header">
	              <h3 class="box-title">중고도서 상세페이지</h3>
	            </div>
	            <!-- /.box-header -->
	            <div class="box-body">
	              <form action="/harangdin/trade" method="post" name="trade" onsubmit="return checkpoint()">
	                <!-- text input -->
	                <!-- select -->
	                <div class="row">
	                	<div class="col-sm-12 form-group">
	                 	 <label>도서명</label>
	                	 <input type="text" class="form-control" value="${i.b_name }" name="b_name" readonly="readonly">
	             	   </div>
	                </div>
	                <div class="row">
		                <div class="col-sm-6 form-group">
		                  <label>도서 사진</label><br>
		                  <c:choose>
		                  	<c:when test="${i.b_photo == null || i.b_photo eq ''}">
		                  		<img src="../resources/dist/img/noImage.jpg" class="img-rounded" height="100%" width="100%">
		                  	</c:when>
		                  	<c:otherwise>
		                  		<img src="/displayFile?fileName=${i.b_photo}" class="img-rounded" height="100%" width="100%">
		                  	</c:otherwise>
		                  </c:choose>
		                </div>
		                <div class="col-sm-6 form-group">
		                 
		                  <label>중고도서 거래번호</label>
		                  <input type="text" class="form-control" name="b_num" value="${i.b_num }" readonly="readonly">
		                  <label>학번</label>
		                  <input type="text" class="form-control" name="m_id" value="${i.m_id }" readonly="readonly">
		                  <label>저자</label>
		                  <input type="text" class="form-control" value="${i.b_writer }" readonly="readonly">
		                  <label>출판사</label>
		                  <input type="text" class="form-control" value="${i.b_pub }" readonly="readonly">
		                  <label>재고수량</label>
		                  <input type="text" class="form-control" value="${i.b_stock }" readonly="readonly">
		                  <label>판매자 희망 포인트</label>
		                  <input type="text" class="form-control" value="${i.b_want }" readonly="readonly">
		                  <label>등록날짜</label>
			                  <div class="input-group">
			                      <div class="input-group-addon">
			                        <i class="fa fa-calendar"></i>
			                      </div>
			                      <input type="text" class="form-control pull-right" value="${i.b_regdate }" readonly="readonly"/>
			                 </div>
		                  <label>현시각 최고 포인트</label>
			       	 		 <div class="input-group">
		                  		<input type="text" class="form-control" value="${max_point}" name="max_point" readonly="readonly">
		                 		<c:if test="${member.m_id == i.m_id || member.m_id eq i.m_id}">
		                 		<span class="input-group-btn">
		                 			<c:if test="${i.b_iscomplete eq '거래'}">						
										<button type="submit" class="btn btn-block btn-success">이 사람과 거래</button>	
									</c:if>
									<c:if test="${i.b_iscomplete eq '거래중' }">
										<button type="button" class="btn btn-block btn-warning" disabled="disabled">거래중</button>
									</c:if>
									<c:if test="${i.b_iscomplete eq '완료' }">
										<button type="button" class="btn btn-block btn-danger" disabled="disabled">완료</button>
									</c:if>
									<c:if test="${i.b_iscomplete eq '기부중' }">
										<button type="button" class="btn btn-block btn-warning" disabled="disabled">기부중</button>
									</c:if>
									<c:if test="${i.b_iscomplete eq '기부완료' }">
										<button type="button" class="btn btn-block btn-danger" disabled="disabled">기부완료</button>
									</c:if>
								</span>
								</c:if>
							</div>
		                </div> 
		                <div class="col-sm-12 form-group">
		                  <label>도서 정보</label>
		                  <textarea class="form-control" rows="5" readonly="readonly">${i.b_content}</textarea>
		                </div>
	                </div>
	              </form>
	            </div><!-- /.box-body -->
	            <div class="box-footer" align="right">
	            	<c:if test="${member.m_id != i.m_id || !member.m_id eq i.m_id}">
	            		<c:if test="${i.b_iscomplete eq '거래'}">						
							<input type="button" class="btn btn-primary" id="btnPopup" value="포인트 경매">
						</c:if>
						<c:if test="${i.b_iscomplete eq '거래중' }">
							<input type="button" class="btn btn-warning" value="거래중"  disabled="disabled">
						</c:if>
						<c:if test="${i.b_iscomplete eq '완료' }">
							<input type="button" class="btn btn-danger" value="거래 완료"  disabled="disabled">
						</c:if>
					</c:if>
					<c:if test="${member.m_id == i.m_id || member.m_id eq i.m_id}">
						<input type="button" class="btn btn-success" onclick="fnUpdate('${i.b_num}')" value="도서 정보 수정">
						<input class="btn btn-danger" onclick="fnDelete('${i.b_num}')" value="삭제">
					</c:if>	
					<input class="btn btn-warning" value="이전">
	            </div>
	          </div>
              </div><!-- /.col -->
           </div><!-- /.row -->
        </section><!-- /. 작업 공간 끝! -->
        
         <!-- 모달 : 뒷 페이지 배경을 눌러도 꺼지지 않음 -->
                <div class="modal fade" id="theModal" data-backdrop="static">
                    <div class="modal-dialog">
                        <form name="apply" action="/harangdin/b_hunter" method="post" onsubmit="return formcheck()">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3>구매희망</h3>
                            </div>
                            <div class="modal-body">
                              <div class="form-group">
                              	 <label>중고도서 거래번호</label>
                  				<input type="text" class="form-control" name="b_num" value="${i.b_num }" readonly="readonly">
                              	<label>도서명</label>
                				<input type="text" class="form-control" value="${i.b_name }" readonly="readonly">
                				<label>저자</label>
				                <input type="text" class="form-control" value="${i.b_writer }" readonly="readonly">
				                <label>출판사</label>
				                <input type="text" class="form-control" value="${i.b_pub }" readonly="readonly">
				                <label>판매자 희망 포인트</label>
                  				<input type="text" class="form-control" value="${i.b_want }" readonly="readonly">
				                <label>현시각 최고 포인트</label>
                  				<input type="text" name="max_point" class="form-control" value="${max_point }" readonly="readonly">
				                
                              </div>
                              <br>
                              <div style="width:100%; height:100px; overflow:auto">
                                <span class="input-group-addon bg-gray">입찰 포인트</span>
                                	<input type="text" class="form-control" name="bh_want" required="required">
                              </div>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-default" data-dismiss="modal">닫기</button>
                                <button class="btn btn-primary" type="submit">입찰</button>
                            </div>
                        </div>
                        </form>
                    </div>
                </div><!-- 모달 끝 -->
                
<!-- 글 삭제 -->
<form name="del" method="post" action="/harangdin/delete" >
	<input type="hidden" name="b_num" value="" id="b_num"/>
</form>
<form name="update" method="post" action="/harangdin/update" >
	<input type="hidden" name="b_num" value="" id="b_num"/>
</form>
                
<!------------------------------------------------------------------------------------------------------------------->        
      </div><!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->
      
<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------> 
<%@ include file="../include/footer.jsp" %>
<!-- ---------------------------------------------------------------- -->
<script>
$(function(){
//모달 접근 바로 하기
	$("#btnPopup").click(
	    function(){
	        //alert("버튼 눌림");
	        $("#theModal").modal('toggle');
	    
	});
});
function fnDelete(b_num){
	del.b_num.value=b_num;
	document.del.submit();
}
function fnUpdate(b_num){
	update.b_num.value=b_num;
	document.update.submit();
}
</script>
