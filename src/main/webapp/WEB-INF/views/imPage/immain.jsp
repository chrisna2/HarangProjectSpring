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
		<h1>
			강의평가 메인페이지 <small></small>
		</h1>
		<ol class="breadcrumb">
			<!-- 페이지 기록 메인에서 부터 현재 페이지 까지의 경로 나열 -->
			<li><a href="#"><i class="fa fa-dashboard"></i> 메인</a></li>
			<li class="active">강의평가 메인페이지[현재 페이지]</li>
		</ol>
	</section>
	<!------------------------------------ 메인페이지 바디 [작업 내용] ------------------------------------------------------------>
	<section class="content">

		<div class="row">
			<div class="col-md-10">

				<div class="col-md-10">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">내 강의평가</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table class="table table-bordered">
								<tr>
									<th>강의번호</th>
									<th>강의명</th>
									<th>교수이름</th>
									<th>평가</th>
								</tr>




								<c:forEach var="im" items="${imlist}">
									<tr>

										<td>${im.l_num}</td>
										<td>${im.l_name }</td>
										<td>${im.l_teacher}</td>
										<c:if test="${im.tt_iscomplete eq 'Y'}">
											<td><a
												href="/impage/update?l_num=${im.l_num}">평가완료</a>
											</td>
										</c:if>

										<c:if test="${im.tt_iscomplete eq 'N'}">
											<td><a
												href="/impage/insert?l_num=${im.l_num}">평가미완료</a>
											</td>
										</c:if>
									</tr>
								</c:forEach>





							</table>
						</div>
						<!-- /.box-body -->

					</div>
				</div>



				<div class="col-md-10">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">전체 강의평가 게시글</h3>

							<!-- select -->
							<div class="box-tools">

								<div class="input-group">

									<form action="/impage/main" name="search" method="post">

										<input type="text" name="keyword"
											class="form-control input-sm pull-right"
											style="width: 150px;" placeholder="Search" value='${keyword}' }/>
										<select class="form-control input-sm pull-right"
											name="keyfield" style="width: 80px; heigh: 30px;">


											<option value="l_name"
												${keyfield eq 'l_name' ? 'selected' : null}> 강의명</option>
											<option value="l_teacher"
												${keyfield eq 'l_teacher' ? 'selected' : null}> 교수명</option>
											<option value="m_name"
												${keyfield eq 'm_name' ? 'selected' : null}> 작성자 </option>






										</select>
										<div class="input-group-btn">
											<button type="submit" class="btn btn-sm btn-default">
												<i class="fa fa-search"></i>
											</button>
										</div>
									</form>
								</div>







							</div>
						</div>
						<!-- /.box-header -->
						<div class="box-body no-padding">
							<table class="table">
								<tr>
									<th>평가번호</th>
									<th>강의명</th>
									<th>만족도</th>
									<th>강의년도</th>
									<th>강의학기</th>
									<th>교수명</th>
									<th>작성자</th>
								</tr>
								<tbody>



									<c:forEach var="all" items="${alllist}">

										<tr>

											<td><a
												href="/impage/read?lm_num=${all.lm_num}">
													${all.lm_num}</a></td>
											<td>${all.l_name }</td>
											<td>${all.lm_star}</td>
											<td>${all.lm_year}</td>
											<td>${all.lm_term}</td>
											<td>${all.l_teacher}</td>
											<td>${all.m_name}</td>



										</tr>
									</c:forEach>


								</tbody>






							</table>
							<div class="box-footer clearfix">
								<ul class="pagination pagination-sm no-margin pull-right">
									<c:if test="${pageMaker.prev}">
										<li><a
											href="/impage/main${pageMaker.makeQuery(pageMaker.startPage-1)}">&laquo;</a></li>
									</c:if>
									<c:forEach begin="${pageMaker.startPage}"
										end="${pageMaker.endPage}" var="idx">
										<li value="${pageMaker.cri.page == idx ? 'class=active' : ''}">
											<a href="/impage/main?page=${idx}"> ${idx} </a>
										</li>
									</c:forEach>
									<c:if test="${pageMaker.next && pageMaker.endPage>0}">
										<li><a
											href="/impage/main${pageMaker.makeQuery(pageMaker.endPage+1)}">&raquo;</a></li>
									</c:if>
								</ul>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
				</div>

			</div>
			<!-- /.box -->

		</div>



	</section>
	<!-- /. 작업 공간 끝! -->
	<!------------------------------------------------------------------------------------------------------------------->
</div>
<!-- /. 전체를 감싸주는 틀입니다. 지우지 마세여. -->


<!-- 페이징 관련 폼 ----------------------------------------------------------------------->
<!-- 페이징 : 이전 블록으로 이동하는 폼 -->
<form id="prevPage" method="post"
	action="/HarangProject/impage?cmd=main">
	<input type="hidden" name="nowPage"
		value="${paging.pagePerBlock * (paging.nowBlock-1)}" /> <input
		type="hidden" name="nowBlock" value="${paging.nowBlock-1}" />
</form>
<!-- 페이징 : 다음 블록으로 이동하는 폼 -->
<form id="nextPage" method="post"
	action="/HarangProject/impage?cmd=main">
	<input type="hidden" name="nowPage"
		value="${paging.pagePerBlock * (paging.nowBlock+1)}" /> <input
		type="hidden" name="nowBlock" value="${paging.nowBlock+1}" />
</form>
<!-- 페이징 : 해당 페이지로 이동하는 폼 -->
<form id="goPage" method="post" action="/HarangProject/impage?cmd=main">
	<input type="hidden" name="nowPage" value="" id="page" /> <input
		type="hidden" name="nowBlock" value="${paging.nowBlock}" />
</form>






<!-- 푸터(footer) 삽입 [지우지 마세여] ------------------------------------------------------------------------------------------------------>
<%@ include file="../include/footer.jsp"%>


<script>
	///////////////// 페이지 관련 javascript function////////////////////
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
</script>



