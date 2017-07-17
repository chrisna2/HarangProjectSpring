<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp" %>
<!-- 해더  삽입  [지우지마세여]------------------------------------------------------------------------------------------------->
<!-- 페이지 헤드 라인 : 제목 -->
<head>
     <title>기본 값 페이지</title>
</head>
<!-- 메인 페이지 구역 , 즉 작업 구역 -->
<div class="content-wrapper">
<!----------------------------------- 메인페이지 헤더 [작업 제목] ------------------------------------------------------------->
<section class="content-header">
	<h1>
		중고도서 거래 /기부 등록
		<small>중고도서를 거래 혹은 기부하여 포인트를 얻을 수 있습니다</small>
	</h1>
	<ol class="breadcrumb">
	<!-- 페이지 기록 : 메인에서 부터 현재 페이지 까지의 경로 나열 -->
		<li><a href="#"><i class="fa fa-dashboard"></i> 메인</a></li>
        <li class="active">하랑딘24</li>
        <li class="active">중고도서 거래/기부 등록</li>
    </ol>
</section>
<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
<section class="content">
	<!-- 세로 길이 수정 -->
    <div class="row">
    	<!-- 너비 사이즈 수정  : col-->
	    <div class="col-md-10">
			<div class="box box-warning">
				<div class="box-header with-border">
					<h3 class="box-title">중고도서 등록</h3>
	            </div>
	           	<!-- /.box-header -->
					<form role="form" action="/HarangProject/harangdin?cmd=regist_proc" method="post"  enctype="multipart/form-data">
				<div class="box-body">
					<input type="hidden" name="m_id" value="${member.m_id}">
						<!-- text input -->
	               		<!-- select -->
						<div class="row">
							<div class="col-sm-2 form-group">
								<label>머릿말</label>
								<select name="b_choice" class="form-control">
									<option value="판매">판매</option>
									<option value="기부">기부</option>
								</select>
							</div>
						</div>
	 
						<div class="form-group">
							<label>도서명</label>
							<input type="text" name="b_name" class="form-control" placeholder="도서명">
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>저자</label>
								<input type="text" name="b_writer" class="form-control" placeholder="저자">
							</div>
							<div class="col-sm-6 form-group">
								<label>출판사</label>
								<input type="text" name="b_pub" class="form-control" placeholder="출판사">
							</div>
						</div>
	                
						<div class="row">
								
							<div class="col-sm-6 form-group">
								<label>재고수량</label>
								<input type="text" name="b_stock" class="form-control" placeholder="ex)1,2,3,...">
							</div>
							<div class="col-sm-6 form-group">
								<label>판매자 희망 포인트</label>
								<input type="text" name="b_want" class="form-control" placeholder="ex)3000">
							</div>
						</div>

	
						<!-- textarea -->
						<div class="form-group">
							<label>판매 도서 정보</label>
							<textarea name="b_content" class="form-control" rows="5" 
								placeholder="도서에 관한 간단한 소개 혹은 도서의 상태에 관한 정보 등을 작성해 주세요."></textarea>
						</div>
						<div class="form-group">
							<label for="exampleInputFile">판매 도서 사진</label>
							<input type="file" id="imgInp" name="upFile" required="required">
								<p class="help-block">해당 도서의 표지 사진을 올려주세요 </p>
								<img src="" id="local" class="img-rounded" height="120" width="90" alt="User Image"/>
						</div>
				</div>
				<!-- /.box-body --> 
				
					<div class="col-sm-4"></div>
					<div class="col-sm-2">
						<button type="submit" class="btn btn-block btn-primary">등록</button>
					</div>
				</form>
					
					<form action="/HarangProject/harangdin?cmd=main" method="post">
						<div class="col-sm-2">	
							<button type="submit" class="btn btn-block">취소</button>
						</div>
					</form>
			</div>
		</div><!-- /.col -->
	</div><!-- /.row -->
</section><!-- /. 작업 공간 끝! -->
<!------------------------------------------------------------------------------------------------------------------->        
      </div><!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->
      
<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------> 
<%@ include file="../include/footer.jsp" %>

<script>
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
</script>