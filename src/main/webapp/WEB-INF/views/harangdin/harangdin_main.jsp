<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%@ include file="../include/header.jsp"%>
<!-- 해더  삽입  [지우지마세여]------------------------------------------------------------------------------------------------->
<!-- 페이지 헤드 라인 : 제목 -->
<head>
<title>기본 값 페이지</title>
</head>
<!-- 메인 페이지 구역 , 즉 작업 구역 -->
<div class="content-wrapper">
	<!----------------------------------- 메인페이지 헤더 [작업 제목] ------------------------------------------------------------->
	<section class="content-header">
		<h1>중고도서 거래 게시판</h1>
		<ol class="breadcrumb">
			<!-- 페이지 기록 메인에서 부터 현재 페이지 까지의 경로 나열 -->
			<li><a href="#"><i class="fa fa-dashboard"></i> 메인</a></li>
			<li class="active">하랑딘 24</li>
		</ol>
	</section>
	<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header">
						<div class="input-group pull-right">
							<form action="/harangdin/regist" method="post">
								<button type="submit" class="btn btn-block btn-default">등록</button>
							</form>
						</div>
						<!-- 도서 검색창 -->
						<div class="input-group pull-left">
							<form action="/harangdin/main" name="search" method="post">
								<select class="form-control" name="keyfield" style="width: 90px;" placeholder="Search">
									<option value="b_name" ${keyfield eq 'b_name' ? 'selected' : null }>도서명</option>
									<option value="b_writer" ${keyfield eq 'b_writer' ? 'selected' : null }>저자</option>
									<option value="b_pub" ${keyfield eq 'b_pub' ? 'selected' : null }>출판사</option>
								</select> 
								<input type="text" name="keyword" value='${keyword}' class="form-control" style="width: 300px;"	placeholder="Search">
								<button type="submit" class="btn btn-info.btn-flat">
									<i class="fa fa-search"></i>
								</button>
							</form>
						</div>
					</div>
					<!-- box-header -->
					<div class="box-body">
						<div class="row">
							<div class="col-sm-12">
								<!-- 도서 테이블 -->
								<table class="table table-bordered table-hover dataTable">
									<tr role="row">
										<th style="width: 50px">거래번호</th>
										<th style="width: 40%">도서명</th>
										<th>저자</th>
										<th>출판사</th>
										<th>희망 포인트</th>
									</tr>

									<c:forEach items="${harangdinmain}" var="i" varStatus="k">

										<tr>
											<td>${i.b_num }</td>
											<td><a style="cursor: pointer;"	onclick="fnRead('${i.b_num}')">${i.b_name}</a></td>
											<td>${i.b_writer }</td>
											<td>${i.b_pub }</td>
											<td>${i.b_want }</td>
										</tr>

									</c:forEach>
								</table>
							</div>
						</div>
						<!-- row -->
						<div class="row">
							<div class="col-sm-5"></div>
							<div class="col-sm-7">
								<!-- 페이징 버튼 -->
								<div class="box-footer clearfix">

									<ul class="pagination pagination-sm no-margin pull-right">
										<c:if test="${pageMaker.prev}">
											<li><a
												href="/harangdin/main${pageMaker.makeQuery(pageMaker.startPage-1)}">&laquo;</a></li>
										</c:if>
										<c:forEach begin="${pageMaker.startPage}"
											end="${pageMaker.endPage}" var="idx">
											<li
												value="${pageMaker.cri.page == idx ? 'class=active' : ''}">
												<a href="/harangdin/main?page=${idx}"> ${idx} </a>
											</li>
										</c:forEach>
										<c:if test="${pageMaker.next && pageMaker.endPage>0}">
											<li><a
												href="/harangdin/main${pageMaker.makeQuery(pageMaker.endPage+1)}">&raquo;</a></li>
										</c:if>
									</ul>

								</div>
								<!-- 페이징 버튼 -->
							</div>
						</div>
					</div>
					<!-- body -->
				</div>
			</div>
		</div>
		<!-- row -->
	</section>

	<!-- /. 작업 공간 끝! -->
	<!-- 글 읽기 -->
<form name="read" method="post" action="/harangdin/bdetail">
	<input type="hidden" name="b_num" value="" id="b_num"/>
	<input type="hidden" name="page" value="${pageMaker.cri.page}"/>
</form>
	<!------------------------------------------------------------------------------------------------------------------->
</div>
<!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->

<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------>
<%@ include file="../include/footer.jsp"%>

<script>
	function fnRead(b_num) {
		document.getElementById("b_num").value = b_num;
		read.submit();
		//alert(b_num);
	}
</script>